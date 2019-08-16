package com.tts.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BarrowerFile {
    private static Connection con = DBO.getCon();
    Statement st = null;
    int count = 0;
    String query = null;
    public String firstName;
    public String lastName;
    public String email;
    public String bookName;
    public static int noOfDaysLoan;

    public BarrowerFile() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoOfDaysLoan() {
        return noOfDaysLoan;
    }

    public void setNoOfDaysLoan(int noOfDaysLoan) {
        this.noOfDaysLoan = noOfDaysLoan;
    }

    @Override
    public String toString() {
        return "BarrowerFile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }


    public void addingBorrower() {
        try {

            String query = "insert into borrowerfile values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.getFirstName());
            ps.setString(2, this.getLastName());
            ps.setString(3, this.getEmail());
            ps.setString(4, getBookName());
            ps.setInt(5, getNoOfDaysLoan());
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

}



