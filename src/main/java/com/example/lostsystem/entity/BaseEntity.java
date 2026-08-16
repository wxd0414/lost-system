package com.example.lostsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("主键ID")
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @Comment("创建时间")
    private Date createdDate;

    @UpdateTimestamp
    @Comment("更新时间")
    private Date updatedDate;
}