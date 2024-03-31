package com.codifacil.codifacilbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "mergefield")
public class MergeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Name no puede estar vacío")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Type no puede estar vacío")
    private String type;

    @Transient
    private String value;

    @JsonIgnoreProperties(value = {"mergeFields"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quicktext_id")
    private QuickText quickText;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QuickText getQuickText() {
        return quickText;
    }

    public void setQuickText(QuickText quickText) {
        this.quickText = quickText;
    }
}
