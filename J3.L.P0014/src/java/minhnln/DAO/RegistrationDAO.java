/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class RegistrationDAO implements Serializable{

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void CloseConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean signup(String email, String name, String password) throws Exception {
        boolean status = false;
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRegistration(Email,Name,Password,RoleID,Status) VALUES(?,?,?,'2','New')";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setString(3, password);
            int row = stm.executeUpdate();
            if (row > 0) {
                status = true;
            }
        } finally {
            CloseConnection();
            return status;
        }
    }

    public boolean login(String email, String password) throws Exception {
        boolean status = false;
        try {
            con = db.openConnection();
            String sql = "SELECT Email,Password "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? AND Password = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                status = true;
            }
        } finally {
            CloseConnection();
            return status;
        }
    }

    public String getRolebyEmail(String email) throws Exception {
        String role = "";
        try {
            con = db.openConnection();
            String sql = "SELECT RoleID "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String roleid = rs.getString("RoleID");
                if (roleid.equals("1")) {
                    role = "Admin";
                } else if (roleid.equals("2")) {
                    role = "Student";
                }
            }
        } finally {
            CloseConnection();
            return role;
        }
    }

    public String getNamebyEmail(String email) throws Exception {
        String name = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Name "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getString("Name");
            }
        } finally {
            CloseConnection();
            return name;
        }
    }
    public String getPassword(String email) throws Exception {
        String password = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Password "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                password = rs.getString("Password");
            }
        } finally {
            CloseConnection();
            return password;
        }
    }
}
