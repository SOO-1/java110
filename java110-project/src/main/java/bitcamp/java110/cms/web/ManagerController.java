package bitcamp.java110.cms.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.mvc.RequestMapping;
import bitcamp.java110.cms.service.ManagerService;

@Component // managerListController (맨 앞글자만 소문자)
public class ManagerController { 

    @Autowired
    ManagerService managerService;  // Component가 되면서 주입받을 수 있게 됨.
    
    // spring web mvc 쓸 때, servletContext는 파라미터값으로가 아니라, 이렇게 주입받아야만 함!
    @Autowired
    ServletContext sc; // ioc에게 servletcontext 뽑아달라

    @RequestMapping("/manager/list")
    public String list(HttpServletRequest request){

        int pageNo = 1;
        int pageSize = 3;

        if(request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
            if(pageNo<1)
                pageNo = 1;
        }

        if(request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
            if(pageSize < 3 || pageSize >10)
                pageNo = 3;
        }

        List<Manager> list = managerService.list(pageNo, pageSize);

        request.setAttribute("list", list);

        return "/manager/list.jsp";
    }

    @RequestMapping("/manager/detail")
    public String detail(HttpServletRequest request){

        int no = Integer.parseInt(request.getParameter("no"));
        Manager m = managerService.get(no);
        request.setAttribute("manager", m);
        return "/manager/detail.jsp";
    }

    @RequestMapping("/manager/add")
    public String add(HttpServletRequest request) throws Exception {

        if(request.getMethod().equals("GET")) {

            return "/manager/form.jsp";
        }

        request.setCharacterEncoding("UTF-8");

        Manager m = new Manager();
        m.setName(request.getParameter("name"));
        m.setEmail(request.getParameter("email"));
        m.setPassword(request.getParameter("password"));
        m.setTel(request.getParameter("tel"));
        m.setPosition(request.getParameter("position"));

        Part part = request.getPart("file1");
        if (part.getSize() > 0) {
            String filename = UUID.randomUUID().toString();
            part.write(sc.getRealPath("/upload/" + filename));
            m.setPhoto(filename);
        }
        managerService.add(m);
        return "redirect:list"; // list는 redirect 하라는 뜻이다.
    }

    @RequestMapping("/manager/delete")
    public String delete( HttpServletRequest request) throws Exception{

        int no = Integer.parseInt(request.getParameter("no"));

        managerService.delete(no);
        return "redirect:list";
    }

}