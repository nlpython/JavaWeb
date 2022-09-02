package com.yruns.mapper;

import com.yruns.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return List<Brand>
     */
    List<Brand> selectAll();

    /**
     * 查看详情
     * @return Brand
     */
    Brand selectById(int id);
}
