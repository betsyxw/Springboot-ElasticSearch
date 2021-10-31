package com.xuwen.esblog.controller;

import com.xuwen.esblog.entity.mysql.MysqlBlog;
import com.xuwen.esblog.repository.es.EsBlogRepository;
import com.xuwen.esblog.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * author:xuwen
 * Created on 2021/10/31
 */
@RestController
public class DataController {


    @Autowired
    private MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;



    @GetMapping("/blogs")
    public Object blog(){
        List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryAll();

        return mysqlBlogs;
    }

    @PostMapping("/search")
    public Object search(@RequestBody Param param){
        HashMap<String ,Object> map = new HashMap<>();
        //统计耗时stopwatch--spring自带
        StopWatch watch = new StopWatch();
        watch.start();
        String type = param.getType();
        if(type.equalsIgnoreCase("mysql")){
            //传入的是mysql
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlogs(param.getKeyword());
            map.put("list",mysqlBlogs);

        }else if(type.equalsIgnoreCase("es")){
            //传入的是es
            //把案例传入这里，看这些
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title",param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content",param.getKeyword()));
            String s = builder.toString();
            System.out.println(s);
            //esBlogRepository.search(builder);


        }else{
            return "i dont understand!";
        }

        watch.stop();
        long totalTimeMillis = watch.getTotalTimeMillis();
        map.put("duration",totalTimeMillis);

        return map;

    }

    @Data
    public static class Param{
        //mysql?es?
        private String type;
        //用户传入的数据
        private String keyword;

    }


    @GetMapping("/blogs/{id}")
    public Object blod(@PathVariable("id") Integer id){
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        MysqlBlog blog = byId.get();
        return blog;
    }
}
