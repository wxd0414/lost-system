package com.example.lostsystem.aiOutput;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntentionOutput {

    @Description("意图分析 1:丢失信息登记 2:捡到失物登记 3:失物登记查询 4:捡到失物登记查询  5:其他")
    private Integer intention;

    @Description("大模型对用户端输出")
    private String output;
}
