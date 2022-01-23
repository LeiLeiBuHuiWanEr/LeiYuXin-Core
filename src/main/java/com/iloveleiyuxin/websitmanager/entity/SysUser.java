package com.iloveleiyuxin.websitmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-02
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {
    public SysUser(Integer id,String username, String userpassword, String usersex, Boolean userstate, LocalDate userbirthday, String userphone, Integer userrole) {
        this.setId(id);
        this.username = username;
        this.userpassword = userpassword;
        this.usersex = usersex;
        this.userstate = userstate;
        this.userbirthday = userbirthday;
        this.userphone = userphone;
        this.userrole = userrole;
    }

    private static final long serialVersionUID = 1L;

    @TableField("userName")
    private String username;

    @TableField("userPassword")
    private String userpassword;

    @TableField("userSex")
    private String usersex;

    @TableField("userState")
    private Boolean userstate;

    @TableField("userBirthday")
    private LocalDate userbirthday;

    @TableField("userPhone")
    private String userphone;

    @TableField("userRole")
    private Integer userrole;


}
