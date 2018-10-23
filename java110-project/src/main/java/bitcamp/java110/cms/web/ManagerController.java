// 가능한 servlet과 관련된 것을 controller에서 없애기!

package bitcamp.java110.cms.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.service.ManagerService;

@Controller // managerListController (맨 앞글자만 소문자)
public class ManagerController { 

    @Autowired
    ManagerService managerService;  // Component가 되면서 주입받을 수 있게 됨.
    
    // spring web mvc 쓸 때, servletContext는 파라미터값으로가 아니라, 이렇게 주입받아야만 함!
    @Autowired
    ServletContext sc; // ioc에게 servletcontext 뽑아달라

    @RequestMapping("/manager/list")
    public String list(
            @RequestParam(value="pageNo", defaultValue="1") int pageNo,
            @RequestParam(value="pageSize", defaultValue="3") int pageSize,
            Map<String, Object> map){

        if( pageNo < 1)
            pageNo = 1;
        if( pageSize < 3 || pageSize > 10)
            pageNo = 3;

        List<Manager> list = managerService.list(pageNo, pageSize);
        map.put("list", list); // front Controller가 map에 담은걸 꺼내서 옮김
        return "/manager/list.jsp";
    }

    @RequestMapping("/manager/detail")
    public String detail(
            int no,
            Map<String, Object> map){
        
        Manager m = managerService.get(no);
        map.put("manager", m);
        return "/manager/detail.jsp";
    }

    @RequestMapping("/manager/add")
    public String add(  // property이름으로 꽂을 것이기 때문에, property와 parameter 이름이 같아야 함!
            Manager manager,
            HttpServletRequest request) throws Exception {

        if(request.getMethod().equals("GET")) {

            return "/manager/form.jsp";
        }

        // 사진 데이터 처리
        Part part = request.getPart("file1");
        if (part.getSize() > 0) {
            String filename = UUID.randomUUID().toString();
            part.write(sc.getRealPath("/upload/" + filename));
            manager.setPhoto(filename);
        }
        managerService.add(manager);
        return "redirect:list"; // list는 redirect 하라는 뜻이다.
    }

    @RequestMapping("/manager/delete")
    public String delete(int no) throws Exception{

        managerService.delete(no);
        return "redirect:list";
    }

}