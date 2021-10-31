package com.xuwen.esblog.controller;

import com.xuwen.esblog.entity.mysql.MysqlBlog;
import com.xuwen.esblog.repository.mysql.MysqlBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * author:xuwen
 * Created on 2021/10/31
 */
@Controller
public class IndexController {
    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @RequestMapping({"/","/index"})
    public String index(){
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        System.out.println(all.size());

        return "index.html";
    }
}
