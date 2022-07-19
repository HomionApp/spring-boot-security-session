package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission_name")
    private String permissionName;

}
