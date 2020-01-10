package com.quanmin.manager.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AdminRole {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @OneToMany(mappedBy = "adminRole", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<AdminRole2Permission> adminRole2PermissionSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AdminRole2Permission> getAdminRole2PermissionSet() {
        return adminRole2PermissionSet;
    }

    public void setAdminRole2PermissionSet(Set<AdminRole2Permission> adminRole2PermissionSet) {
        this.adminRole2PermissionSet = adminRole2PermissionSet;
    }
}
