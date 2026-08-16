package com.example.lostsystem.tools;


import com.example.lostsystem.entity.FoundItemRegister;
import com.example.lostsystem.entity.LostItemRegister;
import com.example.lostsystem.repository.FoundItemRegisterRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FoundItemRegisterTools {

    @Autowired
    private FoundItemRegisterRepository  foundItemRegisterRepository;

    @Tool(name = "根据用户提供的手机号查询捡到失物登记信息", value = "当用户需要查询或修改捡到失物登记记录时，根据用户提供的手机号查询其名下所有捡到失物登记记录")
    public List<FoundItemRegister> queryFoundItemRegisterByPhone(@P("用户登记时使用的手机号") String phone) {
        log.info("根据用户提供的手机号查询捡到失物登记信息: {}", phone);
        return foundItemRegisterRepository.queryFoundItemRegisterByPhoneOrderByUpdatedDateDesc(phone);
    }
}
