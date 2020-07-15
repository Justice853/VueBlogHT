package com.cxp.bloght;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class BloghtApplication {

    public static void main (String[] args) {
        SpringApplication.run ( BloghtApplication.class, args );
    }

}
