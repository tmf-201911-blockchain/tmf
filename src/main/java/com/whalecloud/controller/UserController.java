package com.whalecloud.controller;

import com.google.gson.Gson;
import com.whalecloud.domain.re.User;
import com.whalecloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制
 *
 * @author zhaoyanac
 * @date 2019/10/17
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *
     * 登陆
     *
     * @param userName
     * @param password
     * @param response
     * @return
     */
    @RequestMapping(value = "login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String userName, String password, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");

        Boolean login = userService.login(userName, password);

        // 后期如果分角色的话还需要再改  不同的用户进入不同的页面
        User one = userService.getOne(userName, password);

        Gson gson = new Gson();
        String jsonString = gson.toJson(one);

        if (login) {
            return jsonString;
        } else {
            return "登陆失败";
        }

    }


}


