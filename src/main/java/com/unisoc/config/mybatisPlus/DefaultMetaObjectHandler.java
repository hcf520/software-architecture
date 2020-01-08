package com.unisoc.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * TODO 配置 MetaObjectHandler 使用，可以做到自动填充字段的效果
 * @author Chuanfei.he
 *
 */
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", (int) System.currentTimeMillis() / 1000, metaObject);
        this.setFieldValByName("updateTime", (int) System.currentTimeMillis() / 1000, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", (int) System.currentTimeMillis() / 1000, metaObject);
    }
}
