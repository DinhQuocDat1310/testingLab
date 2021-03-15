/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class QuestionDTO implements Serializable {

    private String QuestionID;
    private String QuestionContent;
    private Date CreateDate;
    private String SubjectID;
    private boolean Status;

    public QuestionDTO() {
    }

    public QuestionDTO(String QuestionID, String QuestionContent, Date CreateDate, String SubjectID, boolean Status) {
        this.QuestionID = QuestionID;
        this.QuestionContent = QuestionContent;
        this.CreateDate = CreateDate;
        this.SubjectID = SubjectID;
        this.Status = Status;
    }

    /**
     * @return the QuestionID
     */
    public String getQuestionID() {
        return QuestionID;
    }

    /**
     * @param QuestionID the QuestionID to set
     */
    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    /**
     * @return the QuestionContent
     */
    public String getQuestionContent() {
        return QuestionContent;
    }

    /**
     * @param QuestionContent the QuestionContent to set
     */
    public void setQuestionContent(String QuestionContent) {
        this.QuestionContent = QuestionContent;
    }

    /**
     * @return the CreateDate
     */
    public Date getCreateDate() {
        return CreateDate;
    }

    /**
     * @param CreateDate the CreateDate to set
     */
    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
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
     * @return the Status
     */
    public boolean isStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(boolean Status) {
        this.Status = Status;
    }

   
}
