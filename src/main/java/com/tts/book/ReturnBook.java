package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ReturnBook {
    String string;
    String string1;

    public ReturnBook(final Display display) {
        final Shell shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setText("Return Book");

        Label lbl = new Label(shell, SWT.NONE);
        lbl.setText("Enter Book id");
        lbl.setBounds(100, 100, 150, 30);
        lbl.setBackground(new Color(display, 255, 182, 193));

        final Text firsttxt = new Text(shell, SWT.NONE);
        firsttxt.setBounds(100, 150, 300, 30);
        firsttxt.setTextLimit(13);

       /* Label lbl2 = new Label(shell, SWT.NONE);
        lbl2.setText("Enter the LastName of the Customer");
        lbl2.setBounds(50, 150, 300, 30);
        lbl2.setBackground(new Color(display,255, 182, 193));

        final Text secondtxt = new Text(shell, SWT.NONE);
        secondtxt.setBounds(50, 200, 300, 30);*/

        Button btn = new Button(shell, SWT.PUSH);
        btn.setBounds(100, 250, 100, 40);
        btn.setText("Return");
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                string = firsttxt.getText();
                //string1 = secondtxt.getText();
                if (string.isEmpty()) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                    msg.setText("Warning!!");
                    msg.setMessage("Please enter all Details");
                    msg.open();
                } else {
                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/book", "root", "root");
                        String query =
                                "UPDATE bookrental SET count_of_rent=count_of_rent+1 WHERE book_id=? && count_of_rent<totalCount;";
                        PreparedStatement ps = con.prepareStatement(query);

                        ps.setString(1, string);
                        int result = ps.executeUpdate();
                        if (result >= 1) {
                            MessageBox msg = new MessageBox(shell, SWT.ICON_WORKING);
                            msg.setMessage("Book Returned successfully..");
                            msg.open();
                            firsttxt.setText("");
                            // secondtxt.setText("");
                        } else {
                            MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                            msg.setText("warning!!!");
                            msg.setMessage("Rent book before return");
                            msg.open();
                            firsttxt.setText("");

                        }
                        ps.close();
                        shell.dispose();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button showAllbtn = new Button(shell, SWT.PUSH);
        showAllbtn.setText("Show all Borrowers List");
        showAllbtn.setBounds(400, 250, 200, 40);
        showAllbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new BorrowerListSWT(display);
            }
        });

        Button back = new Button(shell, SWT.PUSH);
        back.setBounds(250, 250, 100, 40);
        back.setText("Home page");
        back.setBackground(new Color(display, 255, 0, 255));
        back.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        shell.open();
    }

}



