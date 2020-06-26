package com.bfchengnuo.uselibraries.json;

import com.bfchengnuo.uselibraries.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Jackson 解析示例
 * @author 冰封承諾Andy
 * @date 2020/6/21
 */
public class JacksonDemo {
    private final ObjectMapper objectMapper;

    public JacksonDemo(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String obj2Str() {
        User user = new User();
        try {
            //写到文件中
            objectMapper.writeValue( new FileOutputStream("data/output-2.json"), user);
            // 写到字符串
            return objectMapper.writeValueAsString(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public void str2Obj(String str) {
        try {
            objectMapper.readValue(str, User.class);
            // 其他集合类型，list、map 等
            objectMapper.readValue(str, new TypeReference<List<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
