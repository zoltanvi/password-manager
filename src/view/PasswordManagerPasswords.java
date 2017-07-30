package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Password;
import model.PasswordTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.TableDataModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;


public class PasswordManagerPasswords {

    private JTable tablePasswords;
    JPanel panelPasswords;
    public JButton btnNewButton;
    private PasswordTableModel model;
    private static int counter = 0;


    private List<TableDataModel> tableList = new ArrayList<TableDataModel>();
    private JTextField txtSearch;
    private JPanel panel_1;
    private static List<Password> pass = null;

    private TableRowSorter<TableModel> rowSorter;


    public PasswordManagerPasswords(PasswordManagerGUI gui) {
        counter++;
        panelPasswords = new JPanel();
        panelPasswords.setLayout(new BorderLayout(0, 0));

        tablePasswords = new JTable();
        JScrollPane panelScroll = new JScrollPane();
        panelPasswords.add(panelScroll, BorderLayout.CENTER);

        panelScroll.setViewportView(tablePasswords);

        panel_1 = new JPanel();
        panelPasswords.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel_1.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        btnNewButton = new JButton(Labels.PASS_ADD_BUTTON);

        JPanel small = new JPanel();
        small.setLayout(new BorderLayout());
        //panel.add(btnNewButton, BorderLayout.EAST);
        panel.add(small, BorderLayout.EAST);
        small.add(btnNewButton, BorderLayout.CENTER);

        btnNewButton.addActionListener(e -> {
            AddNewPass dialog = new AddNewPass(gui);
        });
        small.add(new JLabel("          "), BorderLayout.WEST);
        small.add(new JLabel("          "), BorderLayout.EAST);

        txtSearch = new JTextField();
        panel.add(txtSearch, BorderLayout.CENTER);
        txtSearch.setColumns(10);


        try {
            if (counter > 1) {
                pass.clear();
            }
            pass = gui.getController().getPassw(gui.getSessionUsername());
            model = new PasswordTableModel(pass);
            tablePasswords.setModel(model);

            rowSorter = new TableRowSorter<>(tablePasswords.getModel());
            tablePasswords.setRowSorter(rowSorter);

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


        } catch (Exception xx) {
            xx.printStackTrace();

        }


        JLabel lblSearch = new JLabel("   Search   ");
        panel.add(lblSearch, BorderLayout.WEST);

    }


    public List<Password> getPass() {
        return pass;
    }
}
