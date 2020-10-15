package com.example.service.Impl;

import com.example.mapper.ShipinsMapper;
import com.example.pojo.shipins;
import com.example.service.ShiPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShiPinServiceImpl implements ShiPinService {
    @Resource
    private ShipinsMapper shipinsMapper;
    @Override
    public int insertUrl(String name, String lujing, String url) {
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=lujing=="+lujing+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=shipinsMapper.insertUrl(name,lujing,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;

    }

    @Override
    public List<shipins> selectShipin() {
        List<shipins> shipins = shipinsMapper.selectShipin();
        return shipins;
    }
}
