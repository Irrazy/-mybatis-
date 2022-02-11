package myexception_demo1.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * user1
 * @author lianzhengying
 * @date 2022-01-27 14:42:07
 */
@Table(name = "user1")
@Data
public class User implements Serializable {
    /**
     */
    @Id
    @Min( value = 34,message = "id不能为空")
    private Long id;

    /**
     */
    @NotEmpty(message = "用户名不能为空")
    private String account;

    /**
     */
    private String password;

    private static final long serialVersionUID = 1L;
}