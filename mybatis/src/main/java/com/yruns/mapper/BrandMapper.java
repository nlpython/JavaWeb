package com.yruns.mapper;

import com.yruns.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 条件查询：
     *      1. 散装参数：如果含有多个参数，注解中名称必须保持一致。
     *      2. 对象参数：对象的属性名称必须和SQL占位符名称一致。
     *      3. map参数：map集合的键的名称必须和SQL占位符名称一致。
     */
    List<Brand> selectByCondition(@Param("status")int status, @Param("companyName")String companyName,
                            @Param("brandName")String brandName);   // 使用@Param注解定位占位符
    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map map);

    /**
     * 单条件动态查询
     */
    List<Brand> selectByConditionSingle(Brand brand);

    /**
     * 添加数据
     */
    void add(Brand brand);

    /**
     * 修改数据
     */
    int update(Brand brand);

    /**
     * 单个删除数据
     */
    void deleteById(int id);

    /**
     * 批量删除
     */
    void deleteByIds(@Param("ids")int[] ids);

}
