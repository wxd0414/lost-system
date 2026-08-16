package com.example.lostsystem.repository;

import com.example.lostsystem.entity.FoundItemRegister;
import com.example.lostsystem.entity.LostItemRegister;

import java.util.List;

public interface FoundItemRegisterRepository extends BaseRepository<FoundItemRegister> {

    List<FoundItemRegister> queryFoundItemRegisterByPhoneOrderByUpdatedDateDesc(String phone);
}
