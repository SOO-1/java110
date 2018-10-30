// 가능한 servlet과 관련된 것을 controller에서 없애기!

package bitcamp.java110.cms.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.service.ManagerService;

@Controller // managerListController (맨 앞글자만 소문자)
@RequestMapping("/manager") // 공통경로 위에 설정
public class ManagerController { 

    ManagerService managerService;  // Component가 되면서 주입받을 수 있게 됨.
    ServletContext sc; // ioc에게 servletcontext 뽑아달라

    // 기본생성자가 없어야 하고, 생성자가 이것 단 하나만 있다면,
    // @Autowired를 필드에 붙이지 않아도 꽂아줌.
    // 생성자에 의존객체가 있어야 동작된다는 걸 알 수 있음.
    public ManagerController(
            ManagerService managerService, 
            ServletContext sc) {
        this.managerService = managerService;
        this.sc = sc;
    }

    @GetMapping("list")
    public void list( // InternalResourceViewResolver가 설정된 상황에서만 return이 없다면, webapp쪽에서 경로가 같은 것을 찾는다.
            @RequestParam(defaultValue="1") int pageNo, // value지정하지 않으면, 파라미터명과 변수명 일치한 것으로 쓴다
            @RequestParam(defaultValue="3") int pageSize,
            Model model){

        if( pageNo < 1)
            pageNo = 1;
        if( pageSize < 3 || pageSize > 10)
            pageNo = 3;

        List<Manager> list = managerService.list(pageNo, pageSize);
        model.addAttribute("list", list); // front Controller가 map에 담은걸 꺼내서 옮김
    }

    @GetMapping("detail")
    public void detail(
            int no,
            Model model){
        
        Manager m = managerService.get(no);
        model.addAttribute("manager", m);
    }
    
    @GetMapping("form")
    public void form() {
        
        
    }

    @PostMapping("add")
    public String add(  // property이름으로 꽂을 것이기 때문에, property와 parameter 이름이 같아야 함!
            Manager manager,
            MultipartFile file1) throws Exception { // file을 받는 변수명은 파일로 따로 지정해야함!(photo X)

        // 사진 데이터 처리
        if (file1.getSize() > 0) {
            String filename = UUID.randomUUID().toString();
            file1.transferTo(new File(sc.getRealPath("/upload/" + filename)));
            manager.setPhoto(filename);
        }
        managerService.add(manager);
        return "redirect:list"; // list는 redirect 하라는 뜻이다.
    }

    @GetMapping("delete")
    public String delete(int no) throws Exception{

        managerService.delete(no);
        return "redirect:list";
    }

}