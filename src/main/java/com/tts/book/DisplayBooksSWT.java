package com.tts.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import javax.swing.undo.AbstractUndoableEdit;
import java.sql.*;

public class DisplayBooksSWT {
    private Table tab;

    public DisplayBooksSWT(Display display) {

        final Shell shell = new Shell(Display.getDefault());
        shell.setSize(1000, 700);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("list of books");
        shell.setMaximized(true);
        {
            tab = new Table(shell, SWT.VIRTUAL | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
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
                                "select * from bookrental");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            TableItem item = new TableItem(tab, SWT.NONE);
                            item.setText(new String[]
                                    {rs.getString(1), rs.getString(2),
                                            rs.getString(3), rs.getString(4),
                                            rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                TableColumn Author_name = new TableColumn(tab, SWT.NONE);
                Author_name.setWidth(150);
                Author_name.setText("Author Name");
            }

            {
                TableColumn title = new TableColumn(tab, SWT.NONE);
                title.setWidth(150);
                title.setText("Book Title");
            }

            {
                TableColumn isbn = new TableColumn(tab, SWT.NONE);
                isbn.setWidth(150);
                isbn.setText("ISBN");
            }

            {
                TableColumn BookType = new TableColumn(tab, SWT.NONE);
                BookType.setWidth(150);
                BookType.setText("Academic or Not");
            }

            {
                TableColumn stock1 = new TableColumn(tab, SWT.NONE);
                stock1.setWidth(150);
                stock1.setText("Original Stock");
            }
            {
                TableColumn stock2 = new TableColumn(tab, SWT.NONE);
                stock2.setWidth(150);
                stock2.setText("Stock after Rent");
            }

            {
                TableColumn book_id = new TableColumn(tab, SWT.NONE);
                book_id.setWidth(150);
                book_id.setText("Book ID");
            }
            {
                TableColumn status = new TableColumn(tab, SWT.NONE);
                status.setWidth(150);
                status.setText("status");
            }
        }

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(250, 380, 100, 40);
        homebtn.setText("prev page");
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

