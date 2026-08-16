package com.example.lostsystem.repository;

import com.example.lostsystem.entity.LostItemRegister;

import java.util.List;

public interface LostItemRegisterRepository extends BaseRepository<LostItemRegister> {


    List<LostItemRegister> queryLostItemRegisterByPhoneOrderByUpdatedDateDesc(String phone);
}
