package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static com.tts.book.DBO.con;

public class BookInsertSWT {

    public BookInsertSWT(final Display display) {
        final int result = 0;
        final Shell shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setText("Book Insert");
        shell.setMaximized(true);

        Label label = new Label(shell, SWT.BORDER);
        label.setText("Enter the book details:");
        label.setBounds(80, 50, 180, 30);
        label.setBackground(new Color(display, 32, 178, 170));

        Label label1 = new Label(shell, SWT.BORDER);
        label1.setText("Enter Author name");
        label1.setBounds(50, 100, 150, 30);
        final Text txt1 = new Text(shell, SWT.BORDER);
        txt1.setBounds(250, 100, 150, 30);

        Label label2 = new Label(shell, SWT.BORDER);
        label2.setText("Enter Book name");
        label2.setBounds(50, 150, 150, 30);
        final Text txt2 = new Text(shell, SWT.BORDER);
        txt2.setBounds(250, 150, 150, 30);

        Label label3 = new Label(shell, SWT.BORDER);
        label3.setText("enter ISBN");
        label3.setBounds(50, 200, 150, 30);
        final Text txt3 = new Text(shell, SWT.BORDER);
        txt3.setBounds(250, 200, 150, 30);
        txt3.setTextLimit(13);

        txt3.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String allowedCharacters = "0123456789-";
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

        Label label4 = new Label(shell, SWT.BORDER);
        label4.setText("Book type");
        label4.setBounds(50, 250, 150, 30);

        final Combo cbox1 = new Combo(shell, SWT.READ_ONLY);
        final String items1[] = {"Academic", "Non-Academic"};
        cbox1.setItems(items1);
        cbox1.setBounds(250, 250, 150, 30);

        Label label5 = new Label(shell, SWT.BORDER);
        label5.setText("Enter Stock");
        label5.setBounds(50, 300, 150, 30);
        final Text txt5 = new Text(shell, SWT.BORDER);
        txt5.setBounds(250, 300, 150, 30);
        txt5.setTextLimit(13);

        Label label6 = new Label(shell, SWT.BORDER);
        label6.setText("Enter Book Id");
        label6.setBounds(50, 360, 150, 30);
        final Text txt6 = new Text(shell, SWT.BORDER);
        txt6.setBounds(250, 360, 150, 30);
        txt6.setTextLimit(5);

        txt5.addVerifyListener(new VerifyListener() {
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

        Button button = new Button(shell, SWT.PUSH);
        button.setText("Add Book");
        button.setBounds(90, 450, 100, 40);

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String name = txt1.getText();
                String bookname = txt2.getText();
                String isbn = txt3.getText();
                //  long isbn1=Long.parseLong(isbn);
                String academic = cbox1.getText();
                String stock = txt5.getText();
                String id = txt6.getText();
                if (name.isEmpty() || bookname.isEmpty() ||
                        academic.isEmpty() || stock.isEmpty() || id.isEmpty()) {
                    MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                    msg1.setText("Warning");
                    msg1.setMessage("All fields are Mandatory");
                    msg1.open();
                }
                try {
                    Connection con = DBO.getCon();
                    String query = "insert into bookrental(authname, title, isbn, academic, totalCount,book_id) values(?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setString(2, bookname);
                    ps.setString(3, isbn);
                    ps.setString(4, academic);
                    ps.setString(5, stock);
                    ps.setString(6, id);
                    int k = ps.executeUpdate();
                    String query1 = "update bookrental set count_of_rent=totalCount";
                    PreparedStatement ps1 = con.prepareStatement(query1);
                    ps1.executeUpdate();
                    if (k == 1) {
                        MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                        msg.setMessage("Book Inserted successfully");
                        msg.open();
                    } else {
                        MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                        msg.setMessage("Book Not Inserted successfully");
                        msg.open();
                    }
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    cbox1.deselectAll();
                    txt5.setText("");
                    txt6.setText("");
                    shell.dispose();

                } catch (Exception ex) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                    msg.setMessage("ISBN & BookID should be unique");
                    msg.open();
                    txt3.setText("");
                    txt6.setText("");

                }
            }
        });

        txt1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    txt2.setFocus();
                }
            }
        });

        txt2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    txt3.setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    txt1.setFocus();

                }
            }
        });

        txt3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    cbox1.setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    txt2.setFocus();

                }
            }
        });
        cbox1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    txt5.setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    txt3.setFocus();

                }
            }
        });

        txt5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    txt6.setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    txt3.setFocus();

                }
            }
        });

        txt6.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.ESC) {
                    txt5.setFocus();

                }
            }
        });

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(400, 450, 100, 40);
        homebtn.setText("HomePage");
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });
        shell.open();
        shell.layout();

    }


}

