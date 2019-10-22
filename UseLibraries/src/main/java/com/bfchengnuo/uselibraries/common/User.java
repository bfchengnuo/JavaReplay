package com.bfchengnuo.uselibraries.common;

import com.bfchengnuo.uselibraries.spring.web.validator.MyValidator;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 供测试的一个简单的实体对象
 *
 * @author Created by 冰封承諾Andy on 2019/6/30.
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String desc;

    public User(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    @JsonView(UserSimpleView.class)
    private String no;

    @JsonView(UserSimpleView.class)
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @JsonView(UserDetailView.class)
    @MyValidator
    @ApiModelProperty("用户密码")
    private String pwd;

    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    public User(String no, @NotBlank(message = "用户名不能为空") String userName, String pwd, @Past(message = "生日必须是过去的时间") Date birthday) {
        this.no = no;
        this.userName = userName;
        this.pwd = pwd;
        this.birthday = birthday;
    }
}
