package com.yruns.test;

import com.yruns.mapper.BrandMapper;
import com.yruns.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MybatisTest
 */
public class MybatisTest {
    @Test
    public void testSelectAll() throws Exception {
        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        // 5.释放资源
        sqlSession.close();
    }


    @Test
    public void testSelectById() throws Exception {
        int id = 1;

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        Brand brands = brandMapper.selectById(id);
        System.out.println(brands);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws Exception {
        int status = 1;
        String companyName = "小米";
        String brandName = "小米";

        // 参数处理，为后续like做准备
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
        Map map = new HashMap();
//        map.put("status", status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws Exception {
        int status = 1;
        String companyName = "小米";
        String brandName = "小米";

        // 参数处理，为后续like做准备
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testInsert() throws Exception {
        int status = 1;
        int ordered = 4;
        String companyName = "一加科技有限公司";
        String brandName = "一加";
        String description = "一家手机公司";

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setOrdered(ordered);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setDescription(description);

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        brandMapper.add(brand);
        // 手动提交事务
        sqlSession.commit();

        int id = brand.getId();
        System.out.println("ID: " + id);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws Exception {
        int status = 1;
        int ordered = 4;
//        String companyName = "一加科技有限公司";
//        String brandName = "一加";
//        String description = "一家科技手机公司";
        int id = 5;

        Brand brand = new Brand();
        brand.setId(id);
        brand.setStatus(status);
        brand.setOrdered(ordered);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
//        brand.setDescription(description);

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        int count = brandMapper.update(brand);
        // 手动提交事务
        sqlSession.commit();

        System.out.println(count);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws Exception {
        int id = 5;

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        brandMapper.deleteById(id);
        // 手动提交事务
        sqlSession.commit();


        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws Exception {
        int[] ids = {1, 2};

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4.执行方法
        brandMapper.deleteByIds(ids);
        // 手动提交事务
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }
}
