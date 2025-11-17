package com.example.tpshop_test.controller;

import com.example.tpshop_test.domain.User;
import com.example.tpshop_test.entity.JwtUtil;
import com.example.tpshop_test.entity.Result;
import com.example.tpshop_test.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<String> loginController(@RequestBody User newUser) {
        try {
            User user = userService.loginService(newUser.getUname(), newUser.getPassword());
            if (user != null) {
                // 登录成功，生成Token
                String token = JwtUtil.generateToken(user.getUid());
                return Result.success(token, "登录成功！");
            } else {
                return Result.error("403", "账号或密码错误！");
            }
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        } catch (Exception e) {
            return Result.error("500", "服务器内部错误，请稍后重试");
        }
    }

    @PostMapping("/register")
    public Result<User> registController(@RequestBody User newUser) {
        User user = userService.registService(newUser);
        if (user != null) {
            return Result.success(user, "注册成功！");
        } else {
            return Result.error("403", "用户名已存在！");
        }
    }
}