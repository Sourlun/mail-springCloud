package com.sour.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sour.mall.product.entity.BrandEntity;
import com.sour.mall.product.service.IBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    IBrandService brandService;

    @Test
    void create() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("测试6");
        brandEntity.setName("名称6");
        brandEntity.setShowStatus(1);
        brandEntity.setSort(0);
        brandService.save(brandEntity);
        System.out.println("创建成功, " + brandEntity.toString());
    }

    @Test
    void update() {
        BrandEntity brandEntity = brandService.getById(8);
        brandEntity.setName("测试测试");
        brandService.updateById(brandEntity);
        System.out.println("更新成功, " + brandEntity.toString());
    }

    @Test
    void list() {
        // 查询条件
        QueryWrapper<BrandEntity> like = new QueryWrapper<BrandEntity>().like("name", "小");
        List<BrandEntity> list = brandService.list(like);
        list.forEach(item -> {
            System.out.println(item);
        });
        System.out.println("列表完成!");
    }
}
