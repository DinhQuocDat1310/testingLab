/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.Serializable;

/**
 *
 * @author Welcome
 */
public class SubjectDTO implements Serializable {

    private String SubjectID, SubjectName, SubjectContent;

    public SubjectDTO() {
    }

    public SubjectDTO(String SubjectID, String SubjectName, String SubjectContent) {
        this.SubjectID = SubjectID;
        this.SubjectName = SubjectName;
        this.SubjectContent = SubjectContent;
    }

    /**
     * @return the SubjectID
     */
    public String getSubjectID() {
        return SubjectID;
    }

    /**
     * @param SubjectID the SubjectID to set
     */
    public void setSubjectID(String SubjectID) {
        this.SubjectID = SubjectID;
    }

    /**
     * @return the SubjectName
     */
    public String getSubjectName() {
        return SubjectName;
    }

    /**
     * @param SubjectName the SubjectName to set
     */
    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    /**
     * @return the SubjectContent
     */
    public String getSubjectContent() {
        return SubjectContent;
    }

    /**
     * @param SubjectContent the SubjectContent to set
     */
    public void setSubjectContent(String SubjectContent) {
        this.SubjectContent = SubjectContent;
    }

}
