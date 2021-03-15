/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import minhnln.DTO.QuestionDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class QuestionDAO implements Serializable {

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
    List<QuestionDTO> list;

    public int getTotalOfQuestion() throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(QuestionID) AS TOTAL "
                    + "FROM tblQuestion "
                    + "WHERE Status = 1";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public List<QuestionDTO> displayAllQuestionWithPaging(int pageNo, int itemPerPage) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY QuestionContent DESC) as STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "				FROM tblQuestion "
                    + "                         WHERE Status = 1)\n"
                    + "SELECT STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, pageNo);
            stm.setInt(2, itemPerPage);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<QuestionDTO> searchQuestionwithPaging(String keyword, boolean status, String SubjectID, int pageNo, int itemPerPage) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY QuestionContent DESC) as STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "				FROM tblQuestion "
                    + "                         WHERE QuestionContent LIKE ? AND Status LIKE ? AND SubjectID LIKE ? )\n"
                    + "SELECT STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setBoolean(2, status);
            stm.setString(3, SubjectID);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            stm.setInt(6, itemPerPage);
            stm.setInt(7, pageNo);
            stm.setInt(8, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getTotalOfQuestionAfterSearch(String keyword, boolean status, String SubjectID) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(QuestionID) AS TOTAL "
                    + "FROM tblQuestion "
                    + "WHERE QuestionContent LIKE ? AND Status LIKE ? AND SubjectID LIKE ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setBoolean(2, status);
            stm.setString(3, SubjectID);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public String getLastQuestionIDBaseSubject(String subjectID) throws Exception {
        String lastquestionID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 QuestionID "
                    + "FROM tblQuestion "
                    + "WHERE SubjectID = ? "
                    + "ORDER BY QuestionID DESC";
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectID);
            rs = stm.executeQuery();
            if (rs.next()) {
                lastquestionID = rs.getString("QuestionID");
            }
        } finally {
            CloseConnection();
            return lastquestionID;
        }
    }

    public boolean createQuestion(String QuestionID, String QuestionContent, Date CreateDate, String SubjectID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) "
                    + "VALUES(?,?,?,?,1)";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
            stm.setString(2, QuestionContent);
            stm.setDate(3, CreateDate);
            stm.setString(4, SubjectID);
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

    public boolean deleteQuestion(String QuestionID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblQuestion \n"
                    + "SET Status = 0\n"
                    + "WHERE QuestionID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
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

    public boolean updateQuestion(String QuestionID, String QuestionContent) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblQuestion \n"
                    + "	SET QuestionContent = ?\n"
                    + "	WHERE QuestionID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionContent);
            stm.setString(2, QuestionID);
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

    public boolean setRandomQuestionID(int RandomID, String questionID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRandomQuestioninQuiz(RandomID,QuestionID) VALUES(?,?)";
            stm = con.prepareStatement(sql);
            stm.setInt(1, RandomID);
            stm.setString(2, questionID);
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

    public String[] getRandomQuestionID(int NumOfQuestion, String subjectID) throws Exception {
        String questionID = "";
        String[] questionIDArray = null;
        List<String> questionList = null;
        try {
            con = db.openConnection();
            String sql = "SELECT TOP (?) QuestionID "
                    + "FROM tblQuestion "
                    + "WHERE SubjectID = ? "
                    + "ORDER BY NEWID() ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, NumOfQuestion);
            stm.setString(2, subjectID);
            rs = stm.executeQuery();
            questionList = new ArrayList<>();
            while (rs.next()) {
                questionID = rs.getString("QuestionID");
                questionList.add(questionID);
            }
            questionIDArray = questionList.toArray(new String[0]);
        } finally {
            CloseConnection();
            return questionIDArray;
        }
    }

    public List<QuestionDTO> displayAllQuestionInQuiz(int RandomID) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "SELECT Q.QuestionID,Q.QuestionContent,Q.CreateDate,Q.SubjectID,Q.Status \n"
                    + "FROM tblRandomQuestioninQuiz RQ JOIN tblQuestion Q ON RQ.QuestionID = Q.QuestionID "
                    + "WHERE RQ.RandomID = ? ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, RandomID);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getLastIDOfRandom() throws Exception {
        int LastRandomID = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 RandomID\n"
                    + "FROM tblRandomQuestioninQuiz \n"
                    + "ORDER BY RandomID DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                LastRandomID = rs.getInt("RandomID");
            }
        } finally {
            CloseConnection();
            return LastRandomID;
        }
    }
}
