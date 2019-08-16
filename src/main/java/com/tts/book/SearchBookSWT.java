package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;


public class SearchBookSWT {
    public SearchBookSWT(final Display display) {
        final Shell shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setText("Search by book name");
        shell.setMaximized(true);

        Button showbtn = new Button(shell, SWT.PUSH);
        showbtn.setText("Show all Existing Books");
        showbtn.setBounds(200, 150, 200, 30);

        showbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                // new BookListDB1(display);
                new DisplayBooksSWT(display);
            }
        });
        Label l1 = new Label(shell, SWT.BORDER);
        l1.setText("Enter the Book name to search");
        l1.setBounds(200, 200, 210, 30);
        l1.setBackground(new Color(display, 32, 178, 170));

        final Text text = new Text(shell, SWT.BORDER);
        text.setBounds(200, 250, 210, 30);
        text.setFocus();

        Button button = new Button(shell, SWT.PUSH);
        button.setText("Search");
        button.setBounds(250, 300, 100, 40);
        button.setBackground(new Color(display, 255, 0, 255));
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                String name = text.getText();
                if (name.isEmpty()) {
                    MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                    msg1.setText("Warning");
                    msg1.setMessage("Enter the Book Name!!");
                    msg1.open();

                } else {
                    BookListDB book = new BookListDB();
                    book.searchbook(name, display);
                    text.setText("");
                }
            }
        });

        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                String name = text.getText();
                if (key.keyCode == SWT.CR) {
                    BookListDB book = new BookListDB();
                    book.searchbook(name, display);
                    text.setText("");
                }
            }
        });

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(400, 300, 100, 40);
        homebtn.setText("prev page");
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });
        shell.open();

    }

}



