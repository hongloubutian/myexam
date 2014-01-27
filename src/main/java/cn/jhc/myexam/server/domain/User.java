package cn.jhc.myexam.server.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import cn.jhc.myexam.annotation.Description;
import cn.jhc.myexam.annotation.ImportColumn;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaEntity(sequenceName = "user_seq")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

	/**
     */
    @NotNull
    @Column(unique = true)
    @Size(max = 64)
    @Description("用户名")
    @ImportColumn
    private String username;

    /**
     */
    @NotNull
    @Size(max = 1024)
    @Description("密码")
    @ImportColumn
    private String password;

    /**
     */
    @Column(columnDefinition="bit default '1'", length=1)
    @Description("是否启用")
    private Boolean enabled;

    /**
     */
    @Size(max = 128)
    @Description("姓名")
    @ImportColumn
    private String displayName;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Role> roles = new ArrayList<Role>();
}
