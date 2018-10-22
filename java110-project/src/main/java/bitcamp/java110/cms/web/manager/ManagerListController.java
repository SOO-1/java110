package bitcamp.java110.cms.web.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.mvc.RequestMapping;
import bitcamp.java110.cms.service.ManagerService;

@Component // managerListController (맨 앞글자만 소문자)
public class ManagerListController { 
    
    @Autowired
    ManagerService managerService;  // Component가 되면서 주입받을 수 있게 됨.
    
    @RequestMapping("/manager/list")
    public String list(
            HttpServletRequest request, 
            HttpServletResponse response){
        
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
}