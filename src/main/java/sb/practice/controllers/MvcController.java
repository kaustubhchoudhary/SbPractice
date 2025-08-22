package sb.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class MvcController {

    @GetMapping("/openHomePage")
    public String openHomePage(){
        System.out.println("getHomePage() is executed");
        return "home_page";
    }

    @GetMapping("/openUsersPage")
    public String getUsersPage(){
        System.out.println("getUsersPage() is executed");
        return "user_page";
    }


}
