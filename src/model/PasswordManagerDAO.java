package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordManagerDAO{

    private static final String DB_URL = "jdbc:sqlite:";
    private static final String DB_FILEPATH = System.getenv("APPDATA");
    private static final String DB_FILE = "/pwmanager.db";
    private static final String DB_DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTION_STRING = DB_URL + DB_FILEPATH + DB_FILE;

    private static final String CREATE_TABLES = "CREATE TABLE ACCOUNTS " +
            "( USERNAME     TEXT    NOT NULL   PRIMARY KEY," +
            "  PASSWORD     TEXT    NOT NULL);"
            + "CREATE TABLE PASSWORDS "
            +"( USERNAME        TEXT,"
            + " WEBPAGE    TEXT,"
            + " P_USERNAME   TEXT,"
            + " P_PASSWORD       TEXT,"
            + "FOREIGN KEY(USERNAME) REFERENCES ACCOUNTS(USERNAME));";
    private static final String EXIST_USER = "SELECT USERNAME FROM ACCOUNTS WHERE USERNAME = ?";
    private static final String ADD_ACCOUNT = "INSERT INTO ACCOUNTS(username, password) VALUES(?, ?);";
    private static final String ADD_PASSWORD = "INSERT INTO PASSWORDS(username, webpage, p_username, p_password)" +
            "VALUES(?, ?, ?, ?);";
    private static final String GET_ALL_USER = "SELECT * FROM ACCOUNTS";
    private static final String GET_USERS_PASSWORDS = "SELECT * FROM PASSWORDS WHERE USERNAME = ?";
    private static final  String GET_SINGLE_PASSWORD = "SELECT PASSWORD FROM ACCOUNTS WHERE USERNAME = ?";
    private static final String HAS_ANY_PASSWORD = "SELECT P_PASSWORD FROM PASSWORDS WHERE USERNAME = ?";

    /** Initializes the database if needed  */
    public PasswordManagerDAO(){

        ResultSet rs = null;
        loadDriver();
        try(
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                Statement stmt =
                        conn.createStatement()
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            DatabaseMetaData metadata = conn.getMetaData();
            rs = metadata.getTables(null, null, "ACCOUNTS", null);

            // if the database needs to be initialized first
            if (!rs.isBeforeFirst() ) {
                System.out.println("Initializing database... ");
                stmt.executeUpdate(CREATE_TABLES);
                System.out.println("Initialization is complete.");
            } else {
                System.out.println("The database is already created.");
            }
        } catch ( Exception e ) {
            System.out.println("Unable to access the database!\nExit...");
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close result set when initializing database.");
                e.printStackTrace();
            }
        }
    }

    /** Loads the SQLite JDBC driver */
    private void loadDriver(){
        try {
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("Failed to load SQLite JDBC driver.");
            e.printStackTrace();
        }
    }

    /**
     * Checks whether a given user exists
     * @param username is the account's username that needs to be checked
     * @return true if it exists, false if not exists
     */
    public boolean existsUser(String username){

        ResultSet rs = null;
        boolean exist = true;
        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(EXIST_USER)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (!rs.isBeforeFirst() ) {
                exist = false;
            }
        } catch (SQLException e){
            System.out.println("Can't find " + username + "!");
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close result set when checking the user.");
                e.printStackTrace();
            }
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

        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(ADD_ACCOUNT)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            int i = 1;
            pst.setString(i++, account.getUsername());
            pst.setString(i++, account.getPassword());

            int rowsAffected = pst.executeUpdate();

            if(rowsAffected == 1){
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Failed to register " + account.getUsername() + "!");
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

        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(ADD_PASSWORD)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
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
            System.out.println("Failed to add the Password!");
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Returns all accounts as a list
     * @return the accounts from the database
     */
    public List<Account> getAllUser() {
        List<Account> accounts = new ArrayList<>();
        ResultSet rs = null;

        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                Statement st =
                        conn.createStatement()
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            rs = st.executeQuery(GET_ALL_USER);

            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println("Failed to list all users! ");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
            } catch (SQLException x){
                System.out.println("Failed to close result set when listing all users!");
                x.printStackTrace();
            }
        }
        return accounts;
    }

    /**
     * Returns a list of the users passwords
     * @param username is the username of the Passwords (primary key)
     * @return the Passwords as a list
     */
    public List<Password> getUserPasswords(String username){
        List<Password> passwords = new ArrayList<>();
        ResultSet rs = null;
        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(GET_USERS_PASSWORDS)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            pst.setString(1, username);
            rs = pst.executeQuery();

            while (rs.next()) {
                Password password = new Password();
                password.setUsername(rs.getString("username"));
                password.setWebpage(rs.getString("webpage"));
                password.setP_username(rs.getString("p_username"));
                password.setP_password(rs.getString("p_password"));
                passwords.add(password);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get " + username + "'s passwords!");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
            }catch (SQLException x){
                System.out.println("Failed to close result set when listing the user's passwords!");
                x.printStackTrace();
            }
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
        ResultSet rs = null;

        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(GET_SINGLE_PASSWORD)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            pst.setString(1, username);
            rs = pst.executeQuery();
            password = rs.getString("password");

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("No such user!");
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
            }catch (SQLException x){
                System.out.println("Failed to close result set when getting user's password!");
                x.printStackTrace();
            }
        }
        return password;
    }

    /**
     * Checks whether a given user has any passwords
     * @param username is the account's username that needs to be checked
     * @return true if the user has, false if not
     */
    public boolean hasAnyPassword(String username){

        ResultSet rs = null;
        boolean hasAny = true;
        try (
                Connection conn =
                        DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pst =
                        conn.prepareStatement(HAS_ANY_PASSWORD)
        ){
            if (conn != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("The connection could not be established.");
            }
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (!rs.isBeforeFirst() ) {
                hasAny = false;
            }
        } catch (SQLException e){
            System.out.println("Can't find " + username + "!");
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close result set when checking the passwords.");
                e.printStackTrace();
            }
        }
        return hasAny;
    }

}