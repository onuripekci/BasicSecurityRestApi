package com.works.customerrestapibasicsecurity.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Fault extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;

    private int id;
    private String faultdetail;
    private Integer cost;

}
