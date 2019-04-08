package com.example.security.jwt.util;

import org.springframework.stereotype.Component;

@Component(value = "hibernateFramework")
public class HibernateFramework implements ORM_Framework {

    @Override
    public String writeFrameworkName() {
        return "Hibernate Framework";
    }
}
