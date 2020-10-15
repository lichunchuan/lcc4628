package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipins")
public class shipins {
    @javax.persistence.Id
    @Column(name = "id")
    private Integer Id;
    private String url;
    private String name;
    private String lujing;
}
