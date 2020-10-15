package com.example.service;

import com.example.pojo.shipins;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShiPinService {
    public int insertUrl(@Param("name")String name, @Param("lujing")String lujing, @Param("url")String url);

    public List<shipins> selectShipin();

}
