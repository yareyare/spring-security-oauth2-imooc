/**
 * Copyright
 */
package com.ivy.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ivy.security.validator.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author ivy on 2019-07-11.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @JsonView(UserSimpleView.class)
    private Integer id;
    ;
    @MyConstraint(message = "这是一个测试")
    @JsonView(UserSimpleView.class)
    private String username;
    ;
    @NotBlank(message = "密码不能为空")// message属性不写则系统会提示：password must not be blank
    @JsonView(UserDetailView.class)
    private String password;
    @Past(message = "生日必须是过去的时间")
    @JsonView(UserSimpleView.class)
    private Date birthday;

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }
}
