package model;


public class TableDataModel implements Comparable<TableDataModel> {

    private String webpage;
    private String p_username;
    private String p_password;

    private TableDataModel() {
        //dont allow creation of Object without elements
    }



    public String getWebpage() {
        return webpage;
    }



    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }



    public String getUsername() {
        return p_username;
    }



    public void setUsername(String username) {
        this.p_username = username;
    }



    public String getPassword() {
        return p_password;
    }



    public void setPassword(String password) {
        this.p_password = password;
    }


    public static TableDataModel newInstance(Password password) {
        TableDataModel model = new TableDataModel();

        model.setWebpage(password.getWebpage());
        model.setUsername(password.getP_username());
        model.setPassword(password.getP_password());

        return model;
    }

    @Override
    public int compareTo(TableDataModel o) {
        return this.getWebpage().compareTo(o.getWebpage());
    }

    public Object[] getDataAsObjectList() {
        Object[] columnVals = new Object[3];
        columnVals[0] = this.getWebpage();
        columnVals[1] = this.getUsername();
        columnVals[2] = this.getPassword();

        return columnVals;
    }

}

