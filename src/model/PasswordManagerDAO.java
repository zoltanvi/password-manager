package model;

import java.sql.*;

public class PasswordManagerDAO {

    private static Connection conn;
    private static String filepath = System.getenv("APPDATA");

    public PasswordManagerDAO(){
        conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + filepath + "/pwmanager.db");
            //hide db file
             //Runtime.getRuntime().exec("attrib +H Manager.db");
            if (this.conn != null) {
                System.out.println("Successfully connected to the database!");
            }

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "ACCOUNTS", null);
            // if the database needs to be initialized first
            if (!rs.isBeforeFirst() ) {
                System.out.println("Initializing database... (Creating tables)");
                Statement stmt = null;
                stmt = conn.createStatement();
                String sql = "CREATE TABLE ACCOUNTS " +
                        "( USERNAME     TEXT    NOT NULL   PRIMARY KEY," +
                        "  PASSWORD     TEXT    NOT NULL);"
                        + "CREATE TABLE PASSWORDS "
                        +"( USERNAME        TEXT,"
                        + " WEBPAGE    TEXT,"
                        + " P_USERNAME   TEXT,"
                        + " P_PASSWORD       TEXT,"
                        + "FOREIGN KEY(USERNAME) REFERENCES ACCOUNTS(USERNAME));";

                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Initialization is complete.");
            } else {
                System.out.println("The database is already created.");
            }


        } catch ( Exception e ) {
            System.out.println("Unable to access the database!" +
                    "\nExit...");
            e.printStackTrace();
            System.exit(1);

        }
    }

    public void closeConnection(){
        try {
            conn.close();
            if (conn.isClosed()) {
                System.out.println("The database connection closed successfully!");
            } else {
                System.out.println("Failed to close the database connection!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() ); //TODO
        }
    }


    public boolean existUser(String username){
        boolean exist = true;

        String query = "SELECT USERNAME FROM ACCOUNTS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst() ) {
                exist = false;
            }
        } catch (SQLException e){
            System.out.println("Can't find this username!");
        }
        return exist;
    }


    public int newAccount(String username, String password){
        int success = 0;
        String query = "INSERT INTO ACCOUNTS(username, password) VALUES(?, ?);";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            success = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to create this account!");
            e.printStackTrace();
        }
        return success;
    }


    public void newPassword(String username, String webpage, String p_username, String p_password){
        String query = "INSERT INTO PASSWORDS(username, webpage, p_username, p_password) VALUES(?, ?, ?, ?);";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, webpage);
            pst.setString(3, p_username);
            pst.setString(4, p_password);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to add the record!");
            e.printStackTrace();
        }
    }

    public void printAllUser() {

        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNTS");

            System.out.println("#####################\nUSERNAME\t\tPASSWORD");

            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");

                System.out.println("\t" + username + "\t\t" + password);
            }
        } catch (Exception e) {
            System.out.println("Failed to print the users! ");
            e.printStackTrace();
        }

    }

    public void printHisData(String who){
        String query = "SELECT * FROM PASSWORDS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {
            pst.setString(1, who);
            ResultSet rs = pst.executeQuery();
            System.out.println();
            while (rs.next()) {
                System.out.print(rs.getString("USERNAME") + "\t");
                System.out.print(rs.getString("WEBPAGE") + "\t");
                System.out.print(rs.getString("P_USERNAME") + "\t");
                System.out.print(rs.getString("P_PASSWORD") + "\n");
            }

        } catch (SQLException e) {
            System.out.println("Failed to print the record!  ");
            e.printStackTrace();
        }

    }

    public String getPassword(String username){
        String password = null;
        String query = "SELECT PASSWORD FROM ACCOUNTS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            password = rs.getString("PASSWORD");

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("No such user!");
        }
        return password;
    }
}
