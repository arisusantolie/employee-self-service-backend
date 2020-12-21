package com.project.ess.entity.security;

import com.project.ess.entity.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = -7630541240164240251L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false,length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;


    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name="roles_authorities",
            joinColumns =@JoinColumn(name ="roles_id" ,referencedColumnName ="id" ) ,
            inverseJoinColumns = @JoinColumn(name = "authorities_id",referencedColumnName = "id"))
    private List<AuthorityEntity> authorities;

    public RoleEntity(String name) {
        this.name=name;
    }

    public RoleEntity() {

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
