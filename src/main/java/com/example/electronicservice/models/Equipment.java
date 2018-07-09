package com.example.electronicservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 45)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updatedAt", nullable = false)
//    private Date updatedAt;

    @Column(name = "description")
    @Size(max = 200)
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private Set<Attribute> attributes;

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Date getupdatedAt() {
//        return updatedAt;
//    }
//
//    public void setupdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
