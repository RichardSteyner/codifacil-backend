package com.codifacil.codifacilbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quicktext")
public class QuickText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title no puede estar vacío")
    private String title;

    private String description;

    @Column(columnDefinition = "TEXT NOT NULL")
    @NotEmpty(message = "Body no puede estar vacío")
    private String body;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    //@JsonIgnoreProperties({"hibernateLaziInitializer", "handler"})
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "quicktext_id", nullable = false)
    @JsonIgnoreProperties(value = {"quickText"}, allowSetters = true)
    @OneToMany(mappedBy = "quickText", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MergeField> mergeFields = new ArrayList<>();

    @JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<MergeField> getMergeFields() {
        return mergeFields;
    }

    public void setMergeFields(List<MergeField> mergeFields) {
        this.mergeFields.clear();
        mergeFields.forEach(mf -> this.addMergeField(mf));
        //this.mergeFields = mergeFields;
    }

    public void addMergeField(MergeField mergeField) {
        this.mergeFields.add(mergeField);
        mergeField.setQuickText(this);
    }

    /*Esto se podría utilizar para remover los registros que ya no deben estar en la BD (ej. microservicios examenes)
    public void removeMergeField(MergeField mergeField) {
        this.mergeFields.remove(mergeField);
        mergeField.setQuickText(null);
    }*/

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
