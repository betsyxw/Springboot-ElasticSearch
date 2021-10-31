package com.xuwen.esblog;

import com.xuwen.esblog.repository.es.EsBlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsBlogApplicationTests {

    @Autowired
    EsBlogRepository esBlogRepository;


    @Test
    void contextLoads() {
    }

}
