package com.example.mapper;

import com.example.pojo.shipins;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShipinsMapper {
    //插入
    @Insert({"insert into shipins (name,lujing,url) values (#{name},#{lujing},#{url})"})
    public int insertUrl(@Param("name")String name, @Param("lujing")String lujing, @Param("url")String url);

    //查询
    @Select("select * from shipins")
    public List<shipins> selectShipin();

}
