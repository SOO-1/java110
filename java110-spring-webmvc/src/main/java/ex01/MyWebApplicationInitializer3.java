package ex01;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class MyWebApplicationInitializer3 
    /*implements WebApplicationInitializer*/ {
    
    public void onStartup(ServletContext servletContext) throws ServletException {
        
        System.out.println("MyWebApplicationInitializer3");
    }

}
