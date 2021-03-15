package com.sour.mall.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义校验
 *
 * @author xgl
 * @date 2021/3/13 16:12
 **/
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set = new HashSet<>();

    /**
     * 初始化方法
     *
     * @author xgl
     * @date 2021/3/13 16:14
     **/
    @Override
    public void initialize(ListValue constraintAnnotation) {
        // 把字段注解上面的value放到里面
        int[] value = constraintAnnotation.value();
        for (int i = 0; i < value.length; i++) {
            set.add(value[i]);
        }
    }

    /**
     * 需要校验的值
     *
     * @param context 上下文信息
     *
     * @author xgl
     * @date 2021/3/13 16:16
     **/
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
