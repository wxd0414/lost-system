package com.example.lostsystem.tools;


import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class TestTools {

    @Tool
    public void test(@P("userMessage") String  userMessage){
        System.out.println("测试调用 test tools" + userMessage );
    }

    // 工具1：获取用户班级
    @Tool("获取用户所在班级")
    public String getUserClass(String userName){
        System.out.println("------"+userName);
        return null;
    }

    // 工具2：查询天气
    @Tool("获取今天的天气")
    public String getWeather(String address){
        System.out.println("------"+address);
        return "天气晴朗";
    }
}
