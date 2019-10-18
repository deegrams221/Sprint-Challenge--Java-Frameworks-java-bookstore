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
@Table(name = "book")
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
    private String title;

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


    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<Wrote> wrote = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("books")
    private Section section;

    // default constructor
    public Book() {

    }

    // constructor
    public Book(String title, String ISBN, Integer copy, Section section) {
        this.title = title;
        this.ISBN = ISBN;
        this.copy = copy;
        this.section = section;
    }

    // getters and setters
    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(Integer copy) {
        this.copy = copy;
    }

    public List<Wrote> getwrote() {
        return wrote;
    }

    public void setwrote(List<Wrote> wrote) {
        this.wrote = wrote;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    // toString
    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", copy=" + copy +
                ", wrote=" + wrote +
                ", section=" + section +
                '}';
    }
}