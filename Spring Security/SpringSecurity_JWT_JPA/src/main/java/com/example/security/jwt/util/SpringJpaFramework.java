package com.example.security.jwt.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "springJpaFramework")
public class SpringJpaFramework implements ORM_Framework {

    @Override
    public String writeFrameworkName() {
        return "Spring JPA Framework";
    }
}
