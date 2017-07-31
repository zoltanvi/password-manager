package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Password;
import model.PasswordTableModel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class PasswordManagerPasswords {

    private JTable tablePasswords;
    JPanel panelPasswords;
    public JButton btnNewButton;
    private PasswordTableModel model;

    private JTextField txtSearch;
    private JPanel panel_1;
    private List<Password> pass = new ArrayList<>();

    private TableRowSorter<TableModel> rowSorter;


    public PasswordManagerPasswords(PasswordManagerGUI gui) {

        panelPasswords = new JPanel();
        panelPasswords.setLayout(new BorderLayout(0, 0));
        panelPasswords.setBorder(new EmptyBorder(5, 5, 5 ,5));

        tablePasswords = new JTable();
        JScrollPane panelScroll = new JScrollPane();
        panelPasswords.add(panelScroll, BorderLayout.CENTER);

        panelScroll.setViewportView(tablePasswords);
        panelScroll.setViewportBorder(null);
        panelScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel_1 = new JPanel();
        panelPasswords.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel_1.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBorder(new EmptyBorder(5, 5, 5 ,5));
        panel.setBackground(UIManager.getColor("CheckBox.light"));
        btnNewButton = new JButton(Labels.PASS_ADD_BUTTON);

        JPanel small = new JPanel();
        small.setLayout(new BorderLayout());
        small.setBackground(UIManager.getColor("CheckBox.light"));
        //panel.add(btnNewButton, BorderLayout.EAST);
        panel.add(small, BorderLayout.EAST);
        small.add(btnNewButton, BorderLayout.CENTER);

        btnNewButton.addActionListener(e -> {
            gui.switchToAdd();

        });
        small.add(new JLabel("          "), BorderLayout.WEST);
        small.add(new JLabel("          "), BorderLayout.EAST);

        txtSearch = new JTextField();
        panel.add(txtSearch, BorderLayout.CENTER);
        txtSearch.setColumns(10);


        try {

            pass.addAll(gui.getController().getPassw(gui.getSessionUsername()));

            model = new PasswordTableModel(pass);
            tablePasswords.setModel(model);

            rowSorter = new TableRowSorter<>(tablePasswords.getModel());
            tablePasswords.setRowSorter(rowSorter);

            if(gui.getController().hasAnyPassword(gui.getSessionUsername())) {
                txtSearch.getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String text = txtSearch.getText();

                        if (text.trim().length() == 0) {
                            rowSorter.setRowFilter(null);
                        } else {
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String text = txtSearch.getText();

                        if (text.trim().length() == 0) {
                            rowSorter.setRowFilter(null);
                        } else {
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                });
            }

        }catch (Exception y){
           y.printStackTrace();
        }


        JLabel lblSearch = new JLabel("   Search   ");
        panel.add(lblSearch, BorderLayout.WEST);

    }

}
