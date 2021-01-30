package com.bfchengnuo.uselibraries.spring;

import com.bfchengnuo.uselibraries.common.User;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Spring 中自带的非常实用的工具类
 * TBD StreamUtils
 *
 * MVC 文件上传下载还可以参考 {@link com.bfchengnuo.uselibraries.spring.web.FileController}
 * @author Created by 冰封承諾Andy on 2019/6/30.
 */
@Slf4j
public class ToolsDemo {
    public static void main(String[] args) throws Exception {
        assertDemo();
        beanCopy();
        fileUtil();
        propertiesUtil();
    }

    /**
     * 断言工具类的使用
     * 达不到预期直接抛出异常
     */
    private static void assertDemo() {
        // 对象必须为 true
        Assert.isTrue(true, "object must be true");

        // 不为 null 且必须至少包含一个非空格的字符
        Assert.hasText("", "text must not be empty");

        // 对象非空
        Assert.notNull(null, "object is required");

        //  集合非空
        Assert.notEmpty(new ArrayList<>(), "collection must not be empty");

        // 字符不为 null 且字符长度不为 0
        Assert.hasLength("", "text must be specified");

        // obj 必须能被正确造型成为 clazz 指定的类
        Assert.isInstanceOf(Object.class, new Object(), "clazz must be of type [clazz]");
    }

    /**
     * Spring 提供的简单属性拷贝
     * <p>
     * 本质是使用 getter 和 setter 方法，相比 Apache 的，没有复杂的格式转换与校验等异常处理
     * 所以效率会很高，当然局限性也大，但是一般以效率优先，这个就足够了
     */
    private static void beanCopy() {
        User user1 = User.builder()
                .name("skye")
                .age(12)
                .desc("test")
                .build();
        User user2 = new User();

        // 本质是使用 getter 和 setter 方法，源对象的 null 值也会被 copy 注意！
        BeanUtils.copyProperties(user1, user2);

        System.out.println(user2);
    }

    /**
     * Spring 提供的对文件读取 IO 相关的便捷工具类
     * 主要：
     *      ResourceUtils： 读取文件相关
     *      ClassPathResource： 主要用于读取 classpath 下的文件，以类路径的方式进行访问
     *      FileCopyUtils： 文件的拷贝与转换处理
     *      PathMatchingResourcePatternResolver： 可以用来解析资源文件，主要是用来解析类路径下的资源文件，支持 classpath 标识
     *      lombok注解：@Cleanup： 自动关闭流
     * <p>
     * 最大特点支持字符串 classpath 标识
     *
     * @see ToolsDemo#downloadDemo1(String)
     * @see ToolsDemo#downloadDemo2(String)
     * @see ToolsDemo#downloadDemo3(String)
     */
    private static void fileUtil() throws Exception {
        String path = ResourceUtils.getFile("classpath:application.properties").getAbsolutePath();

        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        String filename = classPathResource.getFilename();
        @Cleanup
        InputStream inputStream = classPathResource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

        System.out.println("ResourceUtils: " + path);
        System.out.println("ClassPathResource: " + new String(bytes));
    }

    /**
     * 用来读取 properties 文件的工具类
     * @throws IOException 读取异常
     */
    private static void propertiesUtil() throws IOException {
        Properties props = PropertiesLoaderUtils.loadAllProperties("application.properties");
        System.out.println(props.getProperty("data.user"));
    }


    /**
     * 下载静态文件示例代码，适用于 SpringMVC
     *
     * @param templateName 文件名
     * @return 文件响应流
     * @throws IOException 读取异常
     */
    private ResponseEntity<byte[]> downloadDemo1(String templateName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(templateName);
        String filename = classPathResource.getFilename();
        @Cleanup InputStream inputStream = classPathResource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        // 为了解决中文名称乱码问题
        String fileName = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
    }

    /**
     * 下载静态文件示例代码，适用于 SpringMVC
     *
     * @param path 文件名路径，相对于 classpath
     * @return 文件响应流
     * @throws IOException 读取异常
     */
    private ResponseEntity<byte[]> downloadDemo2(String path) throws Exception {
        //下载文件路径
        String absPath = ResourceUtils.getFile("classpath:" + path).getAbsolutePath();
        File file = new File(absPath);
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment", new String("导入模板.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // apache: FileUtils.readFileToByteArray(file)
        return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * 下载静态文件示例代码，适用于 SpringMVC
     *
     * @param path 文件名路径，相对于 classpath
     * @return 文件流
     * @throws IOException 读取异常
     */
    private static InputStream downloadDemo3(String path) throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(path);
        if (resources.length > 0) {
            return  resources[0].getInputStream();
        } else {
            log.error("file not found." );
            return null;
        }
    }
}
