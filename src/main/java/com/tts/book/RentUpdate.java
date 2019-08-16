package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


import static com.tts.book.DBO.con;

public class RentUpdate {


    String str1;
    String str2;
    String str3;
    String str4;
    String str5;


    Label[] labels = new Label[7];  // array of labels
    final Text[] textbox = new Text[7];//array of text boxes

    final Shell shell = new Shell();

    public void store() {
        str1 = textbox[0].getText();
        str2 = textbox[1].getText();
        str3 = textbox[2].getText();
        str4 = textbox[3].getText();
        str5 = textbox[4].getText();


        if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty()
                || str4.isEmpty() || str5.isEmpty()) {
            MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
            msg.setText("Warning!!!");
            msg.setMessage("Please enter all details");
            msg.open();

        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/book", "root", "root");
                Statement statement = connection.createStatement();
                String query = "insert into borrowerfile(firstname, lastname, email,book_id,noofdaysloan) values(?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, str1);
                ps.setString(2, str2);
                ps.setString(3, str3);
                ps.setString(4, str4);
                ps.setString(5, str5);

                int count = ps.executeUpdate();
                if (count != 0) {
                    MessageBox msg = new MessageBox(shell, SWT.OK);
                    msg.setMessage("Book Rented Successfully...");
                    msg.open();
                    textbox[0].setText("");
                    textbox[1].setText("");
                    textbox[2].setText("");
                    textbox[3].setText("");
                    textbox[4].setText("");
                }
                //shell.dispose();
            } catch (Exception e) {
                MessageBox msg = new MessageBox(shell, SWT.OK);
                msg.setMessage("No book in the catalogue");
                msg.open();
                e.printStackTrace();
            }
        }
    }

    public RentUpdate(final Display display) {

        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setText("Rent Book");

        Button button = new Button(shell, SWT.PUSH);
        button.setText("Rent");
        button.setBounds(200, 400, 100, 40);
        button.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

        labels[0] = new Label(shell, SWT.BORDER);
        labels[0].setText("Enter First name");
        labels[0].setBounds(30, 40, 160, 30);
        textbox[0] = new Text(shell, SWT.BORDER);
        textbox[0].setBounds(200, 40, 200, 30);
        textbox[0].setFocus();

        labels[1] = new Label(shell, SWT.BORDER);
        labels[1].setText("Enter Last Name");
        labels[1].setBounds(30, 100, 160, 30);
        textbox[1] = new Text(shell, SWT.BORDER);
        textbox[1].setBounds(200, 100, 200, 30);

        labels[2] = new Label(shell, SWT.BORDER);
        labels[2].setText("Enter Email Id:");
        labels[2].setBounds(30, 160, 160, 30);
        textbox[2] = new Text(shell, SWT.BORDER);
        textbox[2].setBounds(200, 160, 200, 30);
        final Label lbl = new Label(shell, SWT.BORDER);
        lbl.setBounds(420, 160, 150, 30);
        lbl.setForeground(new Color(display, 252, 5, 16));
        textbox[2].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent verifyEvent) {

                boolean status = EmailValidation.email_Valid(textbox[2].getText());
                if (status) {
                    lbl.setText("Email is accepted");
                } else {
                    lbl.setText("Email is not accepted");
                }
            }
        });

        labels[3] = new Label(shell, SWT.BORDER);
        labels[3].setText("Enter Book Id:*");
        labels[3].setBounds(30, 220, 160, 30);
        textbox[3] = new Text(shell, SWT.BORDER);
        textbox[3].setBounds(200, 220, 200, 30);
        textbox[3].setTextLimit(5);
        textbox[3].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String allowedCharacters = "0123456789";
                String text = e.text;
                for (int index = 0; index < text.length(); index++) {
                    char character = text.charAt(index);
                    boolean isAllowed = allowedCharacters.indexOf(character) > -1;
                    if (!isAllowed) {
                        e.doit = false;
                        return;
                    }

                }
            }
        });


        labels[4] = new Label(shell, SWT.BORDER);
        labels[4].setText("Enter Rent days:");
        labels[4].setBounds(30, 300, 160, 30);
        textbox[4] = new Text(shell, SWT.BORDER);
        textbox[4].setBounds(200, 300, 200, 30);
        textbox[4].setTextLimit(5);


        textbox[4].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String allowedCharacters = "0123456789";
                String text = e.text;
                for (int index = 0; index < text.length(); index++) {
                    char character = text.charAt(index);
                    boolean isAllowed = allowedCharacters.indexOf(character) > -1;
                    if (!isAllowed) {
                        e.doit = false;
                        return;
                    }
                }
            }
        });

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                str1 = textbox[0].getText();
                str2 = textbox[1].getText();
                str3 = textbox[2].getText();
                str4 = textbox[3].getText();
                str5 = textbox[4].getText();

                if (str1.isEmpty()) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                    msg.setText("Warning!!!");
                    msg.setMessage("Please enter all details");
                    msg.open();

                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/book", "root", "root");
                        String query = "update bookrental set count_of_rent=count_of_rent-1 where book_id =?";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, str4);
                        ps.executeUpdate();
                        /* String query2="select * from bookrental";
                           rs= ps.executeQuery(query2);*/
                        /* new DisplayBooksSWT(display);*/
                        store();
                        //connection.close();
                        shell.dispose();
                    } catch (Exception e) {
                        MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                        msg.setText("Warning");
                        msg.setMessage("Book is not available ");
                        msg.open();

                    }
                }
            }
        });


        textbox[0].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {

                if (key.keyCode == SWT.CR) {
                    textbox[1].setFocus();
                }
            }
        });

        textbox[1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    textbox[2].setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    textbox[0].setFocus();
                }
            }
        });

        textbox[2].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    textbox[3].setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    textbox[1].setFocus();
                }
            }
        });
        final Label lblMandatory = new Label(shell, SWT.NONE);
        lblMandatory.setBounds(30, 260, 300, 30);
        lblMandatory.setForeground(new Color(display, 252, 5, 16));
        textbox[3].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                str4 = textbox[3].getText();

                if (!str4.isEmpty()) {
                    if (key.keyCode == SWT.CR) {
                        textbox[4].setFocus();
                    } else if (key.keyCode == SWT.ESC) {
                        textbox[2].setFocus();
                    }
                } else {
                    lblMandatory.setText("* Marked fields are mandatory");

                }

            }
        });

        textbox[4].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.ESC) {
                    textbox[3].setFocus();

                }
            }
        });

        Button showallbtn = new Button(shell, SWT.PUSH);
        showallbtn.setText("Show the Borrowers list");
        showallbtn.setBounds(350, 400, 200, 40);
        showallbtn.setBackground(new Color(display, 198, 113, 113));

        showallbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new BorrowerListSWT(display);
            }
        });
        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(600, 400, 100, 40);
        homebtn.setText("HomePage");
        homebtn.setBackground(new Color(display, 255, 0, 255));

        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

       /* Button rent = new Button(shell, SWT.PUSH);
        rent.setBounds(750, 400, 100, 40);
        rent.setText("rent book");
        rent.setBackground(new Color(display, 255, 0, 255));

        rent.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/book", "root", "root");
                    String query = "update bookrental set count_of_rent=count_of_rent-1 where book_id =?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, str4);
                    int c=ps.executeUpdate();
                    if(c==0){
                        MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                        msg.setText("Warning");
                        msg.setMessage("Book is not available  in store");
                        msg.open();
                    }

                        *//* String query2="select * from bookrental";
                           rs= ps.executeQuery(query2);*//*
         *//* new DisplayBooksSWT(display);*//*
                    store();
                    //connection.close();
                    shell.dispose();
                } catch (Exception e) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                    msg.setText("Warning");
                    msg.setMessage("Book is not available ");
                    msg.open();

                }

            }
        });*/
        shell.open();
    }

}

