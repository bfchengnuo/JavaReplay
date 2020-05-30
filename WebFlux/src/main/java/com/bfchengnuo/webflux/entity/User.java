package com.bfchengnuo.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    @NotBlank
    private String name;

    private Integer age;
}
