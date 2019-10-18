package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// authors
//      authorid - long primary key
//      lastname - String last name of the author
//      firstname - String first name of the author

// There is a many to many relationship between authors and books.
// A book may have many authors while an author may write many books.

// adding custom swagger documentation in models
@ApiModel(value = "Authors", description = "The Authors Entity")

@Loggable
@Entity
@Table(name = "author")
public class Author extends Auditable
{
    // Fields

    // authorid
    // swagger documentation
    @ApiModelProperty(name = "authorid",
            value = "The primary key for Author",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    // firstname
    // swagger documentation
    @ApiModelProperty(name = "firstname",
            value = "Author's First Name",
            required = true,
            example = "Alice")
    private String fname;

    // lastname
    // swagger documentation
    @ApiModelProperty(name = "lastname",
            value = "Author's Last Name",
            required = true,
            example = "Cooper")
    private String lname;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    private List<Wrote> wrote = new ArrayList<>();

    // default constructor
    public Author() {

    }

    // constructor
    public Author(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    // getters and setters
    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Wrote> getwrote() {
        return wrote;
    }

    public void setwrote(List<Wrote> wrote) {
        this.wrote = wrote;
    }

    // toString
    @Override
    public String toString() {
        return "Author{" +
                "authorid=" + authorid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", wrote=" + wrote +
                '}';
    }
}