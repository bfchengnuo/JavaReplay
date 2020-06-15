package com.bfchengnuo.diveinspring.metadata;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 外部化配置相关;
 * 具有顺序性，因为使用了 arrayList 先来的优先级高;
 * 除了 property 格式，还支持 yaml 格式 {@link org.springframework.beans.factory.config.YamlProcessor}
 *
 * @author 冰封承諾Andy
 * @date 2020/6/13
 */
// @org.springframework.context.annotation.PropertySource("classpath:/xxx.xml")
// @org.springframework.context.annotation.PropertySource(name = "xx",
//         value = "classpath:/xxx.yaml",
//         factory = PropertySourceDemo.YamlPropertySourceFactory.class)
public class PropertySourceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PropertySourceDemo.class);

        // 扩展 Environment 中的 PropertySource
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mps");
        PropertySource propertySource = new MapPropertySource("my-property", map);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);

        applicationContext.refresh();
        System.out.println(applicationContext.getEnvironment().getPropertySources());
        applicationContext.close();
    }

    /**
     * yaml 扩展
     */
    static class YamlPropertySourceFactory implements PropertySourceFactory {
        @Override
        public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
            YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
            yamlPropertiesFactoryBean.setResources(resource.getResource());
            Properties yamlProperties = yamlPropertiesFactoryBean.getObject();
            return new PropertiesPropertySource(name, yamlProperties);
        }
    }
}
