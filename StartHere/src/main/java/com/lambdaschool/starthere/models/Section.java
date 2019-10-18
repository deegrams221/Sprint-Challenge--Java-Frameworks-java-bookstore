package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;
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

@Loggable
@Entity
@Table(name = "sections")
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
    private String sectionname;


    // one to many relationship between sections and books
    @OneToMany(mappedBy = "section",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "section")
    List<Book> books = new ArrayList<>();

    // default constructor
    public Section()
    {
    }

    // constructor
    public Section(String sectionname, List<Book> books)
    {
        this.sectionname = sectionname;
        this.books = books;
    }

    // getters and setters
    public long getSectionid()
    {
        return sectionid;
    }

    public void setSectionid(long sectionid)
    {
        this.sectionid = sectionid;
    }

    public String getSectionname()
    {
        return sectionname;
    }

    public void setSectionname(String sectionname)
    {
        this.sectionname = sectionname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    // toString

}
