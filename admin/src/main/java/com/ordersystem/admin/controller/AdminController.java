package com.ordersystem.admin.controller;

import com.ordersystem.admin.request.LoginUserRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginUserRequest loginUserRequest){
        System.out.println(loginUserRequest);
        return "admin/index";
    }
}
