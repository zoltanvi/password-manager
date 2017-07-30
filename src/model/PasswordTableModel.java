package model;

import view.Labels;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PasswordTableModel extends AbstractTableModel{

    private static final int WEBPAGE_COLUMN = 0;
    private static final int P_USERNAME_COLUMN = 1;
    private static final int P_PASSWORD_COLUMN = 2;

    private List<Password> passwords;

    private String[] columnNames = {Labels.PASS_WEBPAGE, Labels.PASS_USERNAME, Labels.PASS_PASSWORD};


    public PasswordTableModel(List<Password> passwords){
        this.passwords = passwords;
    }



    @Override
    public int getRowCount() {
        return passwords.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Password tempPass = passwords.get(rowIndex);

        switch (columnIndex){
            case WEBPAGE_COLUMN:
                return tempPass.getWebpage();
            case P_USERNAME_COLUMN:
                return tempPass.getP_username();
            case P_PASSWORD_COLUMN:
                return tempPass.getP_password();
            default:
                return tempPass.getWebpage();
        }
    }

    @Override
    public String toString() {
        String aa = null;
        for (Password a: passwords) {
            aa = aa + a.toString();
        }
        return aa;
    }

    @Override
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
}

