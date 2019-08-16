package com.tts.book;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Label;

public class Options {
    public static void main(String[] args) {

        final Display display = Display.getDefault();
        Shell shell = new Shell();
        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setText("Book Rental System");

        Label wel = new Label(shell, SWT.BORDER);
        wel.setText("Welcome to the Book Management System");
        wel.setBounds(200, 50, 300, 30);
        wel.setBackground(new Color(display, 60, 179, 113));

        Label select = new Label(shell, SWT.PUSH);
        select.setText("Select one option below");
        select.setBounds(250, 150, 200, 25);
        select.setBackground(new Color(display, 32, 178, 170));

        Button searchBtn = new Button(shell, SWT.PUSH);
        searchBtn.setText("Search a book");
        searchBtn.setVisible(true);
        searchBtn.setSelection(false);
        searchBtn.setBounds(250, 200, 200, 30);

        Button AddBookbtn = new Button(shell, SWT.PUSH);
        AddBookbtn.setText("Add a new Book");
        AddBookbtn.setBounds(250, 250, 200, 30);
        AddBookbtn.setVisible(true);
        AddBookbtn.setBackground(new Color(display, 0, 128, 128));

        Button deletebtn = new Button(shell, SWT.PUSH);
        deletebtn.setText("Delete a book");
        deletebtn.setVisible(true);
        deletebtn.setBounds(250, 300, 200, 30);
        deletebtn.setBackground(new Color(display, 255, 0, 255));

        Button listbtn = new Button(shell, SWT.PUSH);
        listbtn.setText("List of books");
        listbtn.setVisible(true);
        listbtn.setBounds(250, 350, 200, 30);
        listbtn.setBackground(new Color(display, 60, 179, 113));

        Button rentBtn = new Button(shell, SWT.PUSH);
        rentBtn.setText("Rent a book");
        rentBtn.setVisible(true);
        rentBtn.setBounds(250, 400, 200, 30);
        rentBtn.setBackground(new Color(display, 0, 0, 205));

        Button returnBtn = new Button(shell, SWT.PUSH);
        returnBtn.setText("Return a book");
        returnBtn.setVisible(true);
        returnBtn.setBounds(250, 450, 200, 30);
        returnBtn.setBackground(new Color(display, 148, 0, 211));

        Button Blistbtn = new Button(shell, SWT.PUSH);
        Blistbtn.setText("Print borrowers list");
        Blistbtn.setVisible(true);
        Blistbtn.setBounds(250, 500, 200, 30);
        Blistbtn.setBackground(new Color(display, 65, 105, 225));

        Button Exitbtn = new Button(shell, SWT.PUSH);
        Exitbtn.setBounds(225, 600, 250, 40);
        Exitbtn.setText("Exit the Application");
        Exitbtn.setBackground(new Color(display, 5, 224, 252));
        Exitbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                display.dispose();
            }
        });

       /* ProgressBar progressBar3 = new ProgressBar(shell,SWT.INDETERMINATE);
        progressBar3.setBounds(50, 600, 850, 20);*/

        searchBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEven) {

                //  new SearchBookSWT(display);
                new SearchHOME(display);
            }
        });
        AddBookbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new BookInsertSWT(display);
            }
        });
        deletebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new DeleteBookSWT(display);
            }
        });
        listbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent Event) {
                new DisplayBooksSWT(display);
            }
        });

        rentBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent Event) {
                new RentUpdate(display);
            }
        });

        returnBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent Event) {
                new ReturnBook(display);
            }
        });

        Blistbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new BorrowerListSWT(display);
            }
        });


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
