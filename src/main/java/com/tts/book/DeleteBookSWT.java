package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class DeleteBookSWT {
    String string;
    Text txt;
    Shell shell;

    /*public void delete() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/book", "root", "root");
          *//*  Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/book", "root", "root");*//*

            String query = " DELETE FROM Bookrental WHERE title=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, string);
            int result = ps.executeUpdate();
            if (result >= 1) {
                MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.ABORT);
                msg.setMessage("Book deleted...");
                msg.open();
                txt.setText("");
            } else {
                MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.ABORT);
                msg.setText("warning");
                msg.setMessage("Book is not available");
                msg.open();
            }
            ps.close();

            shell.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public DeleteBookSWT(final Display display) {

        shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setText("Delete a book");

        Label lbl = new Label(shell, SWT.NONE);
        lbl.setText("Enter the Book name to delete");
        lbl.setBounds(150, 100, 220, 30);
        lbl.setBackground(new Color(display, 255, 182, 193));

        txt = new Text(shell, SWT.NONE);
        txt.setBounds(150, 150, 300, 30);

        Label F1 = new Label(shell, SWT.BORDER);
        F1.setText("Forgot Author name and Book Name");
        F1.setBounds(150, 400, 280, 30);
        F1.setForeground(new Color(display, 255, 0, 0));
        Button showbtn = new Button(shell, SWT.PUSH);
        showbtn.setText("Click here");
        showbtn.setBounds(250, 450, 100, 40);

        showbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                super.widgetDefaultSelected(selectionEvent);

                new DisplayBooksSWT(display);

            }
        });
        Button listbooks = new Button(shell, SWT.PUSH);
        listbooks.setBounds(150, 200, 100, 40);
        listbooks.setText("Search");
        listbooks.setBackground(new Color(display, 255, 0, 255));
        listbooks.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                String name = txt.getText();
                if (name.isEmpty()) {
                    MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                    msg1.setMessage("Please enter Book Name");
                    msg1.setText("Warning");
                    msg1.open();
                } else {
                    // new SearchBookSWT(display);
                    BookListDB book = new BookListDB();
                    book.searchbook(name, display);
                    txt.setText("");
                }
            }
        });

        lbl.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {

                String name = txt.getText();
                if (name.isEmpty()) {
                    MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                    msg1.setMessage("Please enter Book Name");
                    msg1.setText("Warning");
                    msg1.open();
                }


            }
        });

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(400, 450, 100, 40);
        homebtn.setText("HomePage");
        homebtn.setBackground(new Color(display, 255, 0, 255));
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });
        shell.open();
    }
}





