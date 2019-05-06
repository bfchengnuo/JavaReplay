package com.bfchengnuo.diveinspring.assemble.spring.assemble;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by 冰封承諾Andy on 2019/5/3.
 *
 * 模块装配的方式之选择器-编程方式；
 * 可以返回 null，表示一个也不匹配
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
