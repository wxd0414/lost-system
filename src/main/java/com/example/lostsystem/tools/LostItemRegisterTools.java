package com.example.lostsystem.tools;


import com.example.lostsystem.entity.LostItemRegister;
import com.example.lostsystem.repository.LostItemRegisterRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LostItemRegisterTools {

    @Autowired
    private LostItemRegisterRepository lostItemRegisterRepository;

    @Tool(name = "根据用户提供的手机号查询失物登记信息", value = "当用户需要查询失物登记记录或招领进度时，根据用户提供的手机号查询其名下所有失物登记记录")
    public List<LostItemRegister> queryLostItemRegisterByPhone(@P("用户登记时使用的手机号") String phone) {
        log.info("根据用户提供的手机号查询失物登记信息: {}", phone);
        return lostItemRegisterRepository.queryLostItemRegisterByPhoneOrderByUpdatedDateDesc(phone);
    }

}
