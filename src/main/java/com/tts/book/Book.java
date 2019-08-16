package com.tts.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Book {
    private static Connection con = DBO.getCon();
    private String authorName;
    private String BookTitle;
    private int isbn;
    private String academic;
    private int stock;
    Statement st = null;
    int count = 0;
    String query = null;

    public Book(String authorName, String bookTitle, int isbn, String academic, int stock) {
        this.authorName = authorName;
        BookTitle = bookTitle;
        this.isbn = isbn;
        this.academic = academic;
        this.stock = stock;
    }


    public Book() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorName='" + authorName + '\'' +
                ", BookTitle='" + BookTitle + '\'' +
                ", isbn=" + isbn +
                ", academic='" + academic + '\'' +
                ", stock=" + stock +
                '}';
    }

    public void addingBooks() {
        try {

            String query = "insert into bookrental values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getAuthorName());
            ps.setString(2, this.getBookTitle());
            ps.setInt(3, this.getIsbn());
            ps.setString(4, getAcademic());
            ps.setInt(5, getStock());
            if (st != null)
                count = st.executeUpdate(query);
            if (count == 0) {
                System.out.println("records not inserted ");

            } else
                System.out.println("records inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String s) {

        try {

            query = "select * from bookrental where booktitle=" + BookTitle;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getAuthorName());
            ps.setString(2, this.getBookTitle());
            ps.setInt(3, this.getIsbn());
            ps.setString(4, this.getAcademic());
            ps.setInt(5, this.getStock());
            if (st != null)
                count = st.executeUpdate(query);
            if (count == 0) {
                System.out.println("records not found for deletion ");

            } else
                System.out.println("record deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
