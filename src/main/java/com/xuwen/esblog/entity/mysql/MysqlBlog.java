package com.xuwen.esblog.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * author:xuwen
 * Created on 2021/10/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_blog")
@Entity
public class MysqlBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String author;

    @Column(columnDefinition = "mediumtext")
    private String content;

    private Date createTime;

    private Date updateTime;

}
