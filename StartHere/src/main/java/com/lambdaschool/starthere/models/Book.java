package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// book
//    bookid - long primary key
//    booktitle - String the title of the book. Cannot be null.
//    ISBN - String the ISBN number of the book. Cannot be null. Must be unique
//    copy - Int the year the book was published (copyright date)


// adding custom swagger documentation in models
@ApiModel(value = "Book", description = "The Book Entity")

@Loggable
@Entity
@Table(name = "books")
public class Book extends Auditable
{
    // Fields

    // bookid
    // swagger documentation
    @ApiModelProperty(name = "bookid",
            value = "Primary key for the book",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    // booktitle
    // swagger documentation
    @ApiModelProperty(name = "booktitle",
            value = "Book Title",
            required = true,
            example = "Something%20Wicked%20This%20Way%20Comes")
    @Column(nullable = false)
    private String booktitle;

    // ISBN
    // swagger documentation
    @ApiModelProperty(name = "ISBN",
            value = "The ISBN number of the book",
            example = "139780380729401")
    @Column(nullable = false,
            unique = true)
    private String ISBN;

    // copy
    // swagger documentation
    @ApiModelProperty(name = "copy",
            value = "The year the book was published (copyright date)",
            example = "1962")
    private int copy;

    // join author and book columns
    @ManyToMany
    @JsonIgnoreProperties(value = "books")
    @JoinTable(name = "author_book",
            joinColumns = {
            @JoinColumn(name = "bookid")},
            inverseJoinColumns = {
            @JoinColumn(name = "authorid")})
    List<Authors> authors = new ArrayList<>();

    // default constructor
    public Book()
    {
    }

    // constructor
    public Book(String booktitle, String ISBN, int copy, List<Authors> authors, List<UserRoles> userroles)
    {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
        this.authors = authors;
    }

    // setters and getters
    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Authors> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Authors> authors)
    {
        this.authors = authors;
    }

    // toString
    @Override
    public String toString()
    {
        return "Book{" +
                "bookid=" + bookid +
                ", booktitle='" + booktitle + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", copy=" + copy +
                ", authors=" + authors +
                '}';
    }
}
