package com.bfchengnuo.diveinspring.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/**
 * spring 资源管理
 *
 * classpath：只会到你的 class 路径中查找找文件；
 * classpath*：不仅包含 class 路径，还包括 jar 文件中( class 路径)进行查找.
 *
 * @see org.springframework.util.StreamUtils
 * @author 冰封承諾Andy
 * @date 2020/6/13
 */
public class InjectingResourceDemo {
    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("${user.dir}")
    private String currentPath;

    @PostConstruct
    private void init() {
        System.out.println(ResourceUtils.getContent(this.resource));
        System.out.println(currentPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);
        context.refresh();
        context.close();
    }
}
