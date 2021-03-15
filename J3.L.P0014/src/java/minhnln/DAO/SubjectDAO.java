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
import minhnln.DTO.SubjectDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class SubjectDAO implements Serializable {

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

    List<SubjectDTO> list;

    public List<SubjectDTO> getSubject() throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "SELECT SubjectID,SubjectName,SubjectContent "
                    + "FROM tblSubject";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new SubjectDTO(rs.getString("SubjectID"), rs.getString("SubjectName"), rs.getString("SubjectContent")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }
}
