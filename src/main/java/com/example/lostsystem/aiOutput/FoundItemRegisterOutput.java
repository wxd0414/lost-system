package com.example.lostsystem.aiOutput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class FoundItemRegisterOutput {

    @Description("大模型对用户的输出")
    private String  output;

    @Description("用户姓名")
    private String userName;

    @Description("用户手机号")
    private String phone;

    @Description("捡到的失物名称")
    private String lostItemName;

    @Description("捡到的失物描述")
    private String lostItemDescription;

    @Description("是否完成登记")
    private Boolean isComplete;

    @Description("捡到失物登记数据表主键ID")
    private Long id;
}
