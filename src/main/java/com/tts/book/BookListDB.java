package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BookListDB {
    private Table tab;
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    Boolean flag = false;

    void searchbook(String name, final Display display) {
        final String Aname = name;
        final Shell shell = new Shell();
        shell.setSize(700, 700);
        shell.setMaximized(true);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("list of books");
        {
            //tab = new Table(shell, SWT.VIRTUAL);
            tab = new Table(shell, SWT.VIRTUAL | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
            tab.setVisible(true);
            tab.setLinesVisible(true);
            tab.setHeaderVisible(true);
            tab.setItemCount(1);
            tab.addListener(SWT.SetData, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    try {
                        con = DBO.getCon();
                        ps = con.prepareStatement(
                                "select * from bookrental where title=?");
                        ps.setString(1, Aname);
                        rs = ps.executeQuery();
                        if (rs != null) {
                            while (rs.next()) {
                                TableItem item = new TableItem(tab, SWT.NONE);
                                item.setText(new String[]
                                        {rs.getString(1), rs.getString(2),
                                                rs.getString(3), rs.getString(4),
                                                rs.getString(5)});
                                flag = true;
                            }
                        }
                        if (!flag) {
                            new DisplayBooksSWT(display);
                            shell.close();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Menu menu = new Menu(shell, SWT.POP_UP);
            tab.setMenu(menu);
            MenuItem items = new MenuItem(menu, SWT.PUSH);
            items.setText("Delete this Book");
            items.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    // tab.remove(tab.getSelectionIndices());
                    int[] options = tab.getSelectionIndices();
                    TableItem item;
                    for (int i = 0; i < options.length; i++) {
                        int selectionIndex = tab.getSelectionIndex();
                        item = tab.getItem(selectionIndex);
                        String isbn = item.getText(2);
                        // long id = Long.parseLong(isbn);
                        tab.remove(tab.getSelectionIndices());
                        try {
                            Connection con = DBO.getCon();
                            //String query1 = "delete from bookrental where isbn=?";
                            String query1 = "update bookrental set status='deleted' where isbn=?";
                            PreparedStatement ps = con.prepareStatement(query1);
                            ps.setString(1, isbn);
                            ps.executeUpdate();
                            String query2 = "update bookrental set totalCount=totalCount-1";
                            PreparedStatement ps1 = con.prepareStatement(query2);
                            int k = ps1.executeUpdate();
                            if (k == 1) {
                                MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                                msg.setMessage("Book Inserted successfully");
                                msg.open();
                            } else {
                                MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                                msg.setMessage("Book deleted successfully");
                                msg.open();
                            }
                            // "UPDATE bookrental SET count_of_rent=count_of_rent+1 WHERE book_id=? " +
                            //  "&& count_of_rent<totalCount;

                          /* String query2="select * from bookrental";
                           rs= ps.executeQuery(query2);*/
                            /* new DisplayBooksSWT(display);*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Button btn1 = new Button(shell, SWT.PUSH);
            btn1.setText("Show list of Books");
            btn1.setBounds(400, 200, 100, 40);
            btn1.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    new DisplayBooksSWT(display);
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
        homebtn.setText("Prev page");
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        shell.open();
    }
}




