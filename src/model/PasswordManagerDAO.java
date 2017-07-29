package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordManagerDAO {

    private static Connection conn;
    private static String filepath = System.getenv("APPDATA");

    List<Account> accounts = new ArrayList<>();
    List<Password> passwords = new ArrayList<>();

    /** PasswordManagerDAO constructor */
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

    /** Closes the database connection */
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

    /**
     * Checks whether a given user exists
     * @param username is the account's username that needs to be checked
     * @return true if it exists, false if not exists
     */
    public boolean existsUser(String username){
        boolean exist = true;
        String query = "SELECT USERNAME FROM ACCOUNTS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            //if there is no data, the user is not exist
            if (!rs.isBeforeFirst() ) {
                exist = false;
            }
        } catch (SQLException e){
            System.out.println("Can't find this username!");
        }
        return exist;
    }

    /**
     * Add an account into the database
     * @param account is the account you want to add into the database
     * @return true if it was successful, false if it was unsuccessful
     */
    public boolean addAccount(Account account) {
        boolean success = false;
        String query = "INSERT INTO ACCOUNTS(username, password) VALUES(?, ?);";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {
            int i = 1;
            pst.setString(i++, account.getUsername());
            pst.setString(i++, account.getPassword());

            int rowsAffected = pst.executeUpdate();

            if(rowsAffected == 1){
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Failed to register this account.");
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Add a password into the database
     * @param password is the password you want to add into the database
     * @return true if it was successful, false if it was unsuccessful
     */
    public boolean addPassword(Password password){
        boolean success = false;
        String query = "INSERT INTO PASSWORDS(username, webpage, p_username, p_password)" +
                "VALUES(?, ?, ?, ?);";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {
            int i = 1;
            pst.setString(i++, password.getUsername());
            pst.setString(i++, password.getWebpage());
            pst.setString(i++, password.getP_username());
            pst.setString(i++, password.getP_password());

            int rowsAffected = pst.executeUpdate();

            if(rowsAffected == 1){
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Failed to add the record!");
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Returns all accounts as a list
     * @return the accounts from the database
     */
    public List<Account> getAllUser() {
        try (Statement st = this.conn.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNTS");

            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println("Failed to get AllUser! ");
            e.printStackTrace();
        }
        return accounts;
    }

    /**
     * Returns a list of the users passwords
     * @param username is the username of the Passwords (primary key)
     * @return the Passwords as a list
     */
    public List<Password> getUserPasswords(String username){
        String query = "SELECT * FROM PASSWORDS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Password password = new Password();
                password.setUsername(rs.getString("username"));
                password.setWebpage(rs.getString("webpage"));
                password.setP_username(rs.getString("p_username"));
                password.setP_password(rs.getString("p_password"));
                passwords.add(password);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get UserPasswords!  ");
            e.printStackTrace();
        }
        return passwords;
    }

    /**
     * Returns the user's password
     * @param username is the account's username
     * @return the password as a string
     */
    public String getPassword(String username){
        String password = null;
        String query = "SELECT PASSWORD FROM ACCOUNTS WHERE USERNAME = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            password = rs.getString("password");

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("No such user!");
        }
        return password;
    }



}
