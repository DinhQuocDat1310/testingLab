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
import java.util.ArrayList;
import java.util.List;
import minhnln.DTO.AnswerDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class AnswerDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

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

    List<AnswerDTO> list;

    public List<AnswerDTO> displayAnswerPerQuestion(String QuestionID) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "SELECT AnswerID,AnswerContent,QuestionID,Status "
                    + "FROM tblAnswer "
                    + "WHERE QuestionID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new AnswerDTO(rs.getString("AnswerID"), rs.getString("AnswerContent"), rs.getString("QuestionID"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public String getLastAnswerIDbaseQuestionID(String questionID) throws Exception {
        String lastanswerID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 AnswerID \n"
                    + "FROM tblAnswer\n"
                    + "WHERE QuestionID = ?\n"
                    + "ORDER BY AnswerID DESC";
            stm = con.prepareStatement(sql);
            stm.setString(1, questionID);
            rs = stm.executeQuery();
            if (rs.next()) {
                lastanswerID = rs.getString("AnswerID");
            }
        } finally {
            CloseConnection();
            return lastanswerID;
        }
    }

    public boolean createAnswer(String AnswerID, String AnswerContent, String QuestionID, boolean Status) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES (?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, AnswerID);
            stm.setString(2, AnswerContent);
            stm.setString(3, QuestionID);
            stm.setBoolean(4, Status);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            CloseConnection();
        }
    }

    public boolean updateAnswer(String AnswerID, String AnswerContent, boolean Status) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblAnswer\n"
                    + "	SET AnswerContent = ? , Status = ?\n"
                    + "	WHERE AnswerID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, AnswerContent);
            stm.setBoolean(2, Status);
            stm.setString(3, AnswerID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            CloseConnection();
        }
    }
}
