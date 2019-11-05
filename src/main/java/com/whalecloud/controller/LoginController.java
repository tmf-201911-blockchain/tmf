package com.whalecloud.controller;


import com.whalecloud.domain.re.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆
 *
 * @author zhaoyanac
 * @date 2019/10/12
 */
@Controller
@RequestMapping("/hello/")
public class LoginController {

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        user.setUserName("15637138736");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;

    }

}


