package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SearchHOME {

    public SearchHOME(final Display display) {
        final Shell shell = new Shell();
        shell.setText("Search book");
        shell.setSize(700, 700);
        shell.setMaximized(true);


        Button namebtn = new Button(shell, SWT.PUSH);
        namebtn.setText("Search by BookName");
        namebtn.setBounds(100, 200, 200, 40);
        namebtn.setBackground(new Color(display, 255, 0, 255));

        Button bookbtn = new Button(shell, SWT.PUSH);
        bookbtn.setText("Search by Author Name");
        bookbtn.setBounds(100, 400, 200, 40);
        bookbtn.setBackground(new Color(display, 5, 224, 252));


        namebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new SearchBookSWT(display);
            }
        });

        bookbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new SearchAuthorSWT(display);

            }
        });
        final Button btnclose = new Button(shell, SWT.PUSH);
        btnclose.setText("prev page");
        btnclose.setBounds(150, 500, 100, 40);
        btnclose.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.close();

            }
        });
        shell.open();
    }

}
