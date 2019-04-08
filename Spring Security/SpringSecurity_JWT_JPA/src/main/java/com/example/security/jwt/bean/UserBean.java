package com.example.security.jwt.bean;

import com.example.security.jwt.entity.Role;
import com.example.security.jwt.entity.RoleName;
import com.example.security.jwt.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserBean {

    @Bean
    public User createUserBean() {
        User userBean = new User();
        userBean.setId(123L);
        userBean.setName("User Bean");
        userBean.setEmail("userbean@gmail.com");
        userBean.setUsername("user-bean");
        userBean.setPassword("123");
        Role roleAdmin = new Role();
        roleAdmin.setId(1L);
        roleAdmin.setName(RoleName.ROLE_ADMIN);
        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setName(RoleName.ROLE_USER);
        Set<Role> roleList = new HashSet<>();
        roleList.add(roleAdmin);
        roleList.add(roleUser);
        userBean.setRoles(roleList);
        return userBean;
    }
}
