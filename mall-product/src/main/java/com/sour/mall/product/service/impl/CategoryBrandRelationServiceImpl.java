package com.sour.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sour.mall.product.dao.IBrandDao;
import com.sour.mall.product.dao.ICategoryDao;
import com.sour.mall.product.entity.BrandEntity;
import com.sour.mall.product.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.dao.ICategoryBrandRelationDao;
import com.sour.mall.product.entity.CategoryBrandRelationEntity;
import com.sour.mall.product.service.ICategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<ICategoryBrandRelationDao, CategoryBrandRelationEntity> implements ICategoryBrandRelationService {

    @Autowired
    private IBrandDao brandDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        // 品牌id
        Long brandId = categoryBrandRelation.getBrandId();
        // 分类id
        Long catelogId = categoryBrandRelation.getCatelogId();

        // 品牌
        BrandEntity brandEntity = brandDao.selectById(brandId);
        // 分类
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String brandName) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setBrandId(brandId);
        entity.setBrandName(brandName);

        UpdateWrapper wrapper = new UpdateWrapper<>();
        wrapper.eq("brand_id", brandId);

        this.update(entity, wrapper);
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId, name);
    }

}