package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigTests {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserDao dao;

    @Test
    public void findAllWithMapper() {
        dao.findUserWithDevices();
    }
}
