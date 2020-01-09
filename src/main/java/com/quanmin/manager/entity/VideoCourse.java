package com.quanmin.manager.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class VideoCourse {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "introduction", nullable = true, length = 255)
    private String introduction;
    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;
    @Basic
    @Column(name = "subjectId", nullable = true)
    private Integer subjectId;
    @Basic
    @Column(name = "teacher", nullable = true, length = 255)
    private String teacher;
    @Basic
    @Column(name = "teacherInstroduction", nullable = true, length = 255)
    private String teacherInstroduction;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherInstroduction() {
        return teacherInstroduction;
    }

    public void setTeacherInstroduction(String teacherInstroduction) {
        this.teacherInstroduction = teacherInstroduction;
    }
}
