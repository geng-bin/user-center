import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-05-15 15:57:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "")
public class User {
    /**
    * 主键
    **/
    private Long id;
    /**
    * 用户名
    **/
    private String userName;
    /**
    * 密码
    **/
    private String userPassword;
    /**
    * 用户头像地址
    **/
    private String avatarUrl;
    /**
    * 性别
    **/
    private Integer gender;
    /**
    * 电话
    **/
    private String phone;
    /**
    * 邮箱
    **/
    private String email;
    /**
    * 用户状态
    **/
    private Integer userStatus;
    /**
    * 创建时间
    **/
    private Date createTime;
    /**
    * 更新时间
    **/
    private Date updateTime;
    /**
    * 是否删除
    **/
    private Integer deleted;
    /**
    * 角色 0 普通用户 1 管理员
    **/
    private Integer role;
}

