package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookListDB1 {
    private Table tab;

    public BookListDB1(String name, final Display display) {
        final String Aname = name;
        final Shell shell = new Shell();
        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("List of Books");
        {
            tab = new Table(shell, SWT.VIRTUAL);
            tab.setVisible(true);
            tab.setLinesVisible(true);
            tab.setHeaderVisible(true);
            tab.setItemCount(1);
            tab.addListener(SWT.SetData, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    try {
                        Connection con = DBO.getCon();
                        PreparedStatement ps = con.prepareStatement(
                                "select * from bookrental where authname=?");
                        //ps.setString(1,);
                        ps.setString(1, Aname);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            TableItem item = new TableItem(tab, SWT.NONE);
                            item.setText(new String[]
                                    {rs.getString(1), rs.getString(2),
                                            rs.getString(3), rs.getString(4),
                                            rs.getString(5)});

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                TableColumn column1 = new TableColumn(tab, SWT.NONE);
                column1.setWidth(100);
                column1.setText("AuthorName");
            }

            {
                TableColumn column2 = new TableColumn(tab, SWT.NONE);
                column2.setWidth(100);
                column2.setText("BookTitle");
            }

            {
                TableColumn column3 = new TableColumn(tab, SWT.NONE);
                column3.setWidth(100);
                column3.setText("ISBN");
            }

            {
                TableColumn column4 = new TableColumn(tab, SWT.NONE);
                column4.setWidth(150);
                column4.setText("Academic or Not");
            }

            {
                TableColumn column5 = new TableColumn(tab, SWT.NONE);
                column5.setWidth(100);
                column5.setText("Stock");
            }
        }

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(250, 200, 100, 40);
        homebtn.setText("Prev Page");
        homebtn.setBackground(new Color(display, 255, 0, 255));

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



