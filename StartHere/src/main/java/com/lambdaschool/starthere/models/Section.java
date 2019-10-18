package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// section
//    sectionid - long primary key
//    sectionname - String the name of section. Cannot be null. Must be unique

// The is a one to many relationship between sections and books.
// One section can hold many books while a book can only be in one section.


// adding custom swagger documentation in models
@ApiModel(value = "Section", description = "The Section Entity")

@Entity
@Table(name = "section")
public class Section extends Auditable
{

// Fields

    // sectionid
    // swagger documentation
    @ApiModelProperty(name = "sectionid",
            value = "Primary key for the section",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    // sectionname
    // swagger documentation
    @ApiModelProperty(name = "sectionname",
            value = "Section Name",
            required = true,
            example = "Fiction")
    @Column(nullable = false)
    private String name;



    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("sections")
    private List<Book> books = new ArrayList<>();

    // default constructor
    public Section() {

    }

    // constructor
    public Section(String name) {
        this.name = name;
    }

    // constructor
    public Section(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    // getters and setters
    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // toString
    @Override
    public String toString() {
        return "Section{" +
                "sectionid=" + sectionid +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}