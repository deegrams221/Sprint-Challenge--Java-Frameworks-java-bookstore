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
@Table(name = "authors")
public class Authors
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
    private String firstname;

    // lastname
    // swagger documentation
    @ApiModelProperty(name = "lastname",
            value = "Author's Last Name",
            required = true,
            example = "Cooper")
    private String lastname;

    // Many to many - authors to books
    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    // default constructor
    public Authors()
    {
    }

    // constructors
    public Authors(String firstname, String lastname, List<Book> books)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.books = books;
    }

    // getters and setters
    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;

    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
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
    @Override
    public String toString()
    {
        return "Authors{" +
                "authorid=" + authorid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", books=" + books +
                '}';
    }
}
