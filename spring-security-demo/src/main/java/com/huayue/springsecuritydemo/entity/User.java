package com.huayue.springsecuritydemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/21.
 */
@Data
@Entity
@Table(name = "sys_user")
public class User implements Serializable {
    @Id
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String roles;
}
