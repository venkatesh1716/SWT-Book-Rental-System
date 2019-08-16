package com.tts.book;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBO {
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book",
                    "root", "root");
/*
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vicky18",
                    "root","root");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}
