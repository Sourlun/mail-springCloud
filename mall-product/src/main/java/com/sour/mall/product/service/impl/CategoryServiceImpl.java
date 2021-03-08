package com.sour.mall.product.service.impl;

import com.sun.deploy.ui.DialogTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.dao.ICategoryDao;
import com.sour.mall.product.entity.CategoryEntity;
import com.sour.mall.product.service.ICategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<ICategoryDao, CategoryEntity> implements ICategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {

        // 1,查出所有分类
        List<CategoryEntity> categories = baseMapper.selectList(null);

        // 2,组装成父子树形结构

        // 2.1,查出所有一级分类 & 设置children
        List<CategoryEntity> level1 = categories.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == 0;
        }).map((item) -> {
            item.setChildren(getChildren(item, categories));
            return item;
        }).sorted((item1, item2) -> {
            return item1.getSort() - item2.getSort();
        }).collect(Collectors.toList());

        return level1;
    }

    @Override
    public void removeMenuByIds(List<Long> ids) {
        // TODO 1, 检查当前删除的菜单是否在其他地方引用
        baseMapper.deleteBatchIds(ids);
    }

    /**
     * 递归查找菜单的子菜单
     *
     * @param root .
     * @param all  .
     * @return 菜单的子菜单
     *
     * @author xgl
     * @date 2021/2/21 20:28
     **/
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {

        List<CategoryEntity> children = all.stream().filter(item -> {
            return item.getParentCid().equals(root.getCatId());
        }).map(item -> {
            item.setChildren(getChildren(item, all));
            return item;
        }).sorted((item1, item2) -> {
            return item1.getSort() - item2.getSort();
        }).collect(Collectors.toList());

        return children;
    }

}