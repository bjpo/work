package com.hrbsys.middleware.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Li
 */
@Entity
@Table(name = "XSXX")
public class Student implements Serializable {

    private String name;
    private String number;
    private String squadName;

    public Student() {
    }

    public Student(String number) {
        this.number = number;
    }

    @Column(name = "ZSXM")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "XH")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "BJMC")
    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }
}