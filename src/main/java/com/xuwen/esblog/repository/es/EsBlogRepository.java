package com.xuwen.esblog.repository.es;


import com.xuwen.esblog.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,Integer> {

}
