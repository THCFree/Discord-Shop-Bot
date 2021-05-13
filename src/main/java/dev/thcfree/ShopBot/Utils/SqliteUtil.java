package dev.thcfree.ShopBot.Utils;

import dev.thcfree.ShopBot.Item;
import dev.thcfree.ShopBot.ItemManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class SqliteUtil {
    private static Connection connect(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return c;
    }
    public static void createTable() {
        Connection c = connect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
        String sqlStmt = "CREATE TABLE ITEMS " +
                "(NAME TEXT NOT NULL, " +
                " PRICE  DOUBLE NOT NULL, " +
                " MSGID TEXT, " +
                " CHANID TEXT)";
        stmt.executeUpdate(sqlStmt);
        stmt.close();
        c.close();
        } catch (SQLException ignored) {}
    }

    public static void addItem(Item i) {
        Connection c = connect();
        PreparedStatement pStmt = null;
        try {
            pStmt = c.prepareStatement("INSERT INTO ITEMS VALUES(?, ?, ?, ?)");

        pStmt.setString(1,i.getName());
        pStmt.setDouble(2,i.getPrice());
        pStmt.setString(3,i.getMsgId());
        pStmt.setString(4,i.getChannelId());
        pStmt.executeUpdate();
        c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void delItem(String msgId) {
        Connection c = connect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
        stmt.executeUpdate("DELETE from ITEMS where MSGID="+msgId);
        stmt.close();
        c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void registerItems() {
        Connection c = connect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS;");
        while (rs.next()){
            ItemManager.loadItem(new Item(rs.getString("NAME"),rs.getDouble("PRICE"),rs.getString("MSGID"),rs.getString("CHANID")));
        }
        rs.close();
        stmt.close();
        c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

