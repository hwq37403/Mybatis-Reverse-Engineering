package com.example.demo.Controller;

import com.springboot.dao.entity.userEntity;
import com.springboot.dao.userEntityMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/test")
public class showController {
    @RequestMapping("/show")
    public @ResponseBody userEntity  find() throws IOException {
        // TODO Auto-generated method stub
        //读取配置文件 mybatis-config.xml
        InputStream config=Resources.getResourceAsStream("mybatis-config.xml");
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建sqlSession
        SqlSession sqlSession=sessionFactory.openSession();
        //SqlSession执行映射文件中的SQL，并返回映射结果
        /*		mapper.UserMapper.selectUserById为UserMapper.xml中的命名空间+select的id*/
        userEntity userEntity=sqlSession.selectOne("com.springboot.dao.userEntityMapper.selectByPrimaryKey", 1);
        System.out.println(userEntity.getId());
        return userEntity;
    }
}
