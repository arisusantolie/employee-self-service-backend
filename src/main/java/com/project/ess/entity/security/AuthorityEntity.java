package com.project.ess.entity.security;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "authorities")
public class AuthorityEntity {

    private static final long serialVersionUID = 2442333718322546785L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 20)
    private String name;

    @ManyToMany(mappedBy = "authorities",cascade = CascadeType.PERSIST)
    private Collection<RoleEntity> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
