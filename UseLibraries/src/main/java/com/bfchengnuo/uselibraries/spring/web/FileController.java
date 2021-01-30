package com.bfchengnuo.uselibraries.spring.web;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 处理文件上传下载等请求
 *
 * 参考：Java中的文件拷贝 - https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/%E5%9F%BA%E7%A1%80/Java%E4%B8%AD%E7%9A%84%E6%96%87%E4%BB%B6%E6%8B%B7%E8%B4%9D.md
 * @author Created by 冰封承諾Andy on 2019/7/14.
 * 其他文件下载写法参考 {@link com.bfchengnuo.uselibraries.spring.ToolsDemo}
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @PostMapping
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println("Name:" + multipartFile.getName());
        System.out.println("OriginalFilename:" + multipartFile.getOriginalFilename());
        System.out.println("Size:" + multipartFile.getSize());

        File localFile = new File(ResourceUtils.getFile("classpath:upload/"), Objects.requireNonNull(multipartFile.getOriginalFilename()));

        multipartFile.transferTo(localFile);

        Map<String, Object> result = new HashMap<>();
        result.put("path", localFile.getAbsolutePath());

        return result;
    }

    /**
     * 仅供测试，此方法使用 @RestController、@ResponseBody 后会报错，要避免使用
     */
    @GetMapping("/{name}")
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = ResourceUtils.getFile("classpath:upload/" + name);
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup OutputStream os = response.getOutputStream();

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        // 或者使用 Apache 的 IOUtils.copy(inputStream, outputStream);
        FileCopyUtils.copy(is, os);
        os.flush();
    }

    /**
     * 文件下载的另一种方式
     * 使用路径正则匹配来获取完整的文件后缀信息
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.fileProcessing(fileName, response, request, false);
    }

    /**
     * 公共的文件下载处理
     * @param fileName 文件名
     * @param isPreview 是否是预览
     * @throws IOException IO异常
     */
    private void fileProcessing(String fileName,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                boolean isPreview) throws IOException {
        File file = new File(
                ResourceUtils.getFile("classpath:doc/"),
                fileName
        );

        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }

        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup OutputStream os = response.getOutputStream();

        // 自动推断文件格式
        String contentType = request.getServletContext().getMimeType(file.getAbsolutePath());
        if (StringUtils.isEmpty(contentType)) {
            contentType = "application/octet-stream";
        }

        response.setContentType(contentType);
        if (!isPreview) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        }
        FileCopyUtils.copy(is, os);
        os.flush();
    }
}
