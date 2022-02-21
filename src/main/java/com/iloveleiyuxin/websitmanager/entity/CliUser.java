package com.iloveleiyuxin.websitmanager.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CliUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("userName")
    private String username;

    @TableField("nickName")
    private String nickname;

    @TableField("userPassword")
    private String userpassword;

    private String sex;

    private LocalDate birthday;

    private String phone;

    @TableField("cliRole")
    private Integer clirole;

    private Integer locate;

    /**
     * 健康状态：0为健康，1为有基础疾病，2为有特殊疾病，99为涉疫报备健康结果
     */
    @TableField("healthState")
    private Integer healthstate;

    /**
     * 隔离状态：0为正常，1为被集中隔离，2为居家隔离，3为健康监测
     */
    @TableField("quarantineState")
    private Integer quarantinestate;

    /**
     * 是否常住：1为常住，0为非常住
     */
    @TableField("permanentResidence")
    private Integer permanentresidence;

    /**
     * 是否享受低保：0为不享受，1为享受
     */
    @TableField("lowIncome")
    private Integer lowincome;

    @TableField("registerDate")
    private LocalDate registerdate;


    public CliUser(Integer id,String username, String nickname, String userpassword, String sex, LocalDate birthday, String phone, Integer clirole, Integer locate, Integer healthstate, Integer quarantinestate, Integer permanentresidence, Integer lowincome, LocalDate registerdate) {
        this.setId(id);
        this.username = username;
        this.nickname = nickname;
        this.userpassword = userpassword;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.clirole = clirole;
        this.locate = locate;
        this.healthstate = healthstate;
        this.quarantinestate = quarantinestate;
        this.permanentresidence = permanentresidence;
        this.lowincome = lowincome;
        this.registerdate = registerdate;
    }
}
