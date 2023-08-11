package com.mvc.sample.javacore.mvcmodel.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ResourceBundle;

public class ConnectionUtils {

    private ConnectionUtils() {
    }

    private static Connection conn = null;

    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    private static Connection getConnection() throws SQLException {
        String url = StringUtils.join("jdbc:mysql://", bundle.getString("db.host"), ":",
                bundle.getString("db.port"), "/", bundle.getString("db.name"),
                "?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, bundle.getString("db.username"), bundle.getString("db.password"));
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery();
    }

    public static void executeUpdate(String sql, Object... params) throws SQLException {
        conn = getConnection();
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public static Long executeUpdateForIdentity(String sql, Object... params) throws SQLException {
        conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert failed, no rows affected.");
            }
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("Insert failed, no ID obtained.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}