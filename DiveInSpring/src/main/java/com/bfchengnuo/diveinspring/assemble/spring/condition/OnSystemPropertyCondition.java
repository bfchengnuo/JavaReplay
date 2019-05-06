package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> attributesMap = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String sysVal = System.getProperty(String.valueOf(attributesMap.get("name")));
        return sysVal.equals(attributesMap.get("val"));
    }
}
