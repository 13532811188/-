package com.example.tpshop_test.service.serviceImpl;


import com.example.tpshop_test.domain.User;
import com.example.tpshop_test.mapper.UserMapper;
import com.example.tpshop_test.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl implements UserService {
    @Resource
    private UserMapper userMapper;

//    @Override
//    public User loginService(String uname, String password) {
//        User user = userMapper.findByUnameAndPassword(uname, password);
//        // 重要信息置空
//        if(user != null){
//            user.setPassword("");
//        }
//        return user;
//    }
@Override
public User loginService(String uname, String password) {
    // 1. 校验账号长度（例如：3-20个字符）
    if (uname == null || uname.trim().isEmpty()) {
        throw new IllegalArgumentException("用户名不能为空");
    }
    if (uname.length() < 3 || uname.length() > 20) {
        throw new IllegalArgumentException("用户名长度必须在3-20个字符之间");
    }

    // 2. 校验密码长度（例如：6-30个字符）
    if (password == null || password.trim().isEmpty()) {
        throw new IllegalArgumentException("密码不能为空");
    }
    if (password.length() < 6 || password.length() > 30) {
        throw new IllegalArgumentException("密码长度必须在6-30个字符之间");
    }

    // 3. 校验通过后，执行数据库查询
    User user = userMapper.findByUnameAndPassword(uname, password);

    // 4. 敏感信息脱敏（密码置空）
    if (user != null) {
        user.setPassword("");
    }

    return user;
}




    @Override
    public User registService(User user) {
        //当新用户的用户名已存在时
        if(userMapper.findByUname(user.getUname())!=null){
            // 无法注册
            return null;
        }else{
            // 插入用户到数据库
            int result = userMapper.insertUser(user);
            if (result > 0) {
                //返回创建好的用户对象
                user.setPassword("");
                return user;
            } else {
                return null;
            }
        }
    }
}
