package com.okulbilisim.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.okulbilisim.conf.Config;

/**
 *
 * @author hmert
 */
public class DBConnectionManager {

    private Connection conn;

    /**
     * Database single connection döndürür
     *
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
        if (conn == null || conn.isClosed()) {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup(Config.JNDI_NAME);
            conn = ds.getConnection(); // ile connection alır.
            conn.setAutoCommit(false);
            return conn;
        } else {
            return conn;
        }
    }

    public void rollBack() {
        try {
            conn.rollback();
        } catch (Exception ex) {
        }
    }

    /**
     * Açık olan single connection 'ı kapar
     *
     * @throws java.sql.SQLException
     */
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Açık olan preparedstatement 'ı kapar
     *
     * @throws java.sql.SQLException
     */
    public void closePSStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }

        } catch (Exception ex) {
        }
    }

    public void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }

        } catch (Exception ex) {
        }
    }

    /**
     * Açık olan ResultSet 'ı kapar
     *
     * @throws java.sql.SQLException
     */
    public void closeResultSet(ResultSet rset) {
        try {
            if (rset != null) {
                rset.close();
            }

        } catch (Exception ex) {
        }
    }
}
