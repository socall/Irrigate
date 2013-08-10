/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cyril
 *
 */
public class DBConnection {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connect() throws Exception {
        if (connect == null || connect.isClosed()) {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/irrig?"
                    + "user=root&password=pass123");
        }
    }
    
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    void updateUser(String p1, String p2) {
//        try {
//            connect();
//            // PreparedStatements can use variables and are more efficient
//            preparedStatement = connect
//                    .prepareStatement("UPDATE squeens.users SET played=played+1 WHERE username=(?);");
//            // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
//            // Parameters start with 1
//            preparedStatement.setString(1, p1);
//            preparedStatement.executeUpdate();
//
//            // Statements allow to issue SQL queries to the database
//            statement = connect.createStatement();
//            // Result set get the result of the SQL query
//            resultSet = statement
//                    .executeQuery("select * from users");
//            writeResultSet(resultSet);
//
//            preparedStatement = connect
//                    .prepareStatement("UPDATE squeens.users SET played=played+1 WHERE username=(?);");
//            // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
//            // Parameters start with 1
//            preparedStatement.setString(1, p2);
//            preparedStatement.executeUpdate();
//
//            // Statements allow to issue SQL queries to the database
//            statement = connect.createStatement();
//            // Result set get the result of the SQL query
//            resultSet = statement
//                    .executeQuery("select * from users");
//            writeResultSet(resultSet);
//
//            preparedStatement = connect
//                    .prepareStatement("UPDATE squeens.users SET won=won+1 WHERE username=(?);");
//            // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
//            // Parameters start with 1
//            preparedStatement.setString(1, p1);
//            preparedStatement.executeUpdate();
//
//            // Statements allow to issue SQL queries to the database
//            statement = connect.createStatement();
//            // Result set get the result of the SQL query
//            resultSet = statement
//                    .executeQuery("select * from users");
//            writeResultSet(resultSet);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            close();
//        }
//    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            connect();
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  squeens.users values (?, ?, ?, ?, ?);");
            // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
            // Parameters start with 1
            preparedStatement.setString(1, "TestEMAIL");
            preparedStatement.setString(2, "TestPassword");
            preparedStatement.setString(3, "TestNickname");
            preparedStatement.setString(4, "0");
            preparedStatement.setString(5, "0");
            preparedStatement.executeUpdate();

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from users");
            writeResultSet(resultSet);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close();
        }
        
        return users;

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("username");
            String password = resultSet.getString("passw");
            String nickname = resultSet.getString("nickname");
            System.out.println("User: " + user);
            System.out.println("password: " + password);
            System.out.println("nickname: " + nickname);
        }
    }



//    String login(String name, String pswd) {
//        boolean nameIsLegal = isLegal(name);
//        boolean pswdIsLegal = isLegal(pswd);
//        String result = "<login>false</login>";
//        if (nameIsLegal && pswdIsLegal) {
//
//            try {
//                connect();
//                // Statements allow to issue SQL queries to the database
//                statement = connect.createStatement();
//                // Result set get the result of the SQL query
//                String query = "select * from users ";
//                query += "where username = '" + name + "'";
//                resultSet = statement.executeQuery(query);
//
//                if (resultSet.next()) {
//                    if (resultSet.getString("passw").equals(pswd)) {
//                        result = "<login>true</login>\n";
//                    } else {
//                        result = "<login>false</login>\n";
//                        result += "<username>true</username>\n";
//                        result += "<password>false</password>\n";
//                    }
//                } else {
//                    result = "<login>false</login>\n";
//                    result += "<username>false</username>\n";
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            } finally {
//                close();
//            }
//        }
//        return result;
//    }

//    String register(String name, String pswd, String nick) {
//        boolean nameIsLegal = isLegal(name);
//        boolean pswdIsLegal = isLegal(pswd);
//        boolean nickIsLegal = isLegal(nick);
//
//        // Check if name, pswd and nick are legal
//        if (nameIsLegal && pswdIsLegal && nickIsLegal) {
//            try {
//                connect();
//                boolean uniqueName = nameUnique(name);
//                boolean uniqueNick = nickUnique(nick);
//
//                if (uniqueName && uniqueNick) {
//                    // PreparedStatements can use variables and are more efficient
//                    preparedStatement = connect
//                            .prepareStatement("insert into  squeens.users values (?, ?, ? ,? ,?);");
//                    // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
//                    // Parameters start with 1
//                    preparedStatement.setString(1, name);
//                    preparedStatement.setString(2, pswd);
//                    preparedStatement.setString(3, nick);
//                    preparedStatement.setString(4, "0");
//                    preparedStatement.setString(5, "0");
//                    preparedStatement.executeUpdate();
//
//                    return "<register>true</register>\n";
//                } else {
//                    String res = "<Register>false</Register>\n";
//                    res += "<username>" + uniqueName + "</username>\n";
//                    res += "<nickname>" + uniqueNick + "</nickname>\n";
//                    return res;
//                }
//
//            } catch (Exception e) {
//                System.out.println(e);
//            } finally {
//                close();
//            }
//        }
//        return "<register>false</register>\n";
//    }

    // Check if string is a-z,A-Z,0-9 or @#$% 
    // of 4 < length < 20
//    private boolean isLegal(String str) {
//        return str.matches("[\\w@#$%]{1,20}");
//    }

//    // Check if name doesn't exist in DB
//    private boolean nameUnique(String name)
//            throws Exception {
//        // Statements allow to issue SQL queries to the database
//        statement = connect.createStatement();
//        // Result set get the result of the SQL query
//        resultSet = statement
//                .executeQuery("select username from users where username = '" + name + "'");
//        return !(resultSet.next());
//    }
//
//    // Check if nick doesn't exist in DB
//    private boolean nickUnique(String nick)
//            throws Exception {
//        // Statements allow to issue SQL queries to the database
//        statement = connect.createStatement();
//        // Result set get the result of the SQL query
//        resultSet = statement
//                .executeQuery("select nickname from users where nickname = '" + nick + "'");
//        return !(resultSet.next());
//    }

//    public void saveStats(String name, boolean victory) {
//        try {
//            connect();
//            // Statements allow to issue SQL queries to the database
//            statement = connect.createStatement();
//            // Result set get the result of the SQL query
//            String query = "select * from users ";
//            query += "where username = '" + name + "'";
//            resultSet = statement.executeQuery(query);
//            if (resultSet.next()) {
//                String won = resultSet.getString("won");
//                String played = resultSet.getString("played");
//                if (victory) {
//                    won = Integer.toString(Integer.parseInt(won) + 1);
//                    resultSet.updateString("won", won);
//                }
//                played = Integer.toString(Integer.parseInt(played) + 1);
//                resultSet.updateString("played", played);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            close();
//        }
//    }

    String getStats(String name) {
        String result = "";

        try {
            connect();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select * from users ";
            query += "where username = '" + name + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                result += "<Name>" + name + "</Name>\n";
                result += "<Played>" + resultSet.getString("played") + "</Played>\n";
                result += "<Won>" + resultSet.getString("won") + "</Won>\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close();
        }

        return result;
    }
}
