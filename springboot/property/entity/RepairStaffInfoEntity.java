package com.eworldlink.api.property.entity;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "tab_repair_employee")
@Data
public class RepairStaffInfoEntity {
    private int id;
    private String name;
    private String mobile;
    private String park;
    private String build;
    private String createtime;
}
