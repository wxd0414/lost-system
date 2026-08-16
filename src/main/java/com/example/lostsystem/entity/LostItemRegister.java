package com.example.lostsystem.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

@EqualsAndHashCode(callSuper = true)
@Table(name = "lost_item_register")
@Entity
@Data
@Comment("丢失登记")
public class LostItemRegister extends BaseEntity {


    @Comment("失主姓名")
    private String userName;

    @Comment("失主手机号")
    private String phone;

    @Comment("失物名称")
    private String lostItemName;

    @Comment("失物描述（颜色、材质、尺寸、特殊标识等特征）")
    private String lostItemDescription;
}
