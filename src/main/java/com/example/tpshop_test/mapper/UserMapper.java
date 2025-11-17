package com.example.tpshop_test.mapper;



import com.example.tpshop_test.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 替代XML中的findByUname查询
    @Select("SELECT * FROM user WHERE uname = #{uname}")
    User findByUname(String uname);

    // 替代XML中的findByUnameAndPassword查询
    @Select("SELECT * FROM user WHERE uname = #{uname} AND password = #{password}")
    User findByUnameAndPassword(String uname, String password);

    // 替代XML中的insertUser插入
    @Insert("INSERT INTO user (uname, password) VALUES (#{uname}, #{password})")
    int insertUser(User user);
}
