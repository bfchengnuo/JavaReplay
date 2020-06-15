package com.bfchengnuo.diveinspring.metadata;

import com.bfchengnuo.diveinspring.commons.entry.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * SpringBean 配置元信息
 *
 * @author 冰封承諾Andy
 * @date 2020/6/12
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        // 通用型 BeanDefinition 声明
        BeanDefinition bd = new GenericBeanDefinition();
        bd.setAttribute("name", "mps");

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        builder.addPropertyValue("name", "skye");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // 附加属性，不会影响 Bean 的实例化，辅助作用
        beanDefinition.setAttribute("name", "mps");
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);


        // BeanFactory 的默认实现为 DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册 BeanDefinition
        beanFactory.registerBeanDefinition("person", beanDefinition);
        // 添加后置处理器
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("person", beanName)
                        && Person.class.equals(bean.getClass())) {
                    // 取出附加信息
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    System.out.println("PostProcessor - bd - name: " + bd.getAttribute("name"));
                    // 这里可以通过 bean 对象来修改属性
                }
                // 可以返回一个修改后的 bean 或者新 bean（wrap、代理）
                // 返回 null 表示不做修改
                return bean;
            }
        });

        // 依赖查找
        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);
    }

    private void readXml() {
        // 创建 IoC 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/xxx.xml");
        // ...
    }
}
