package com.example.lostsystem.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Data
public class ChatHistory {

    private Long id;

    private Date createdDate;

    private Date updatedDate;

    private String sessionId;

    private String role;

    private String content;
}
