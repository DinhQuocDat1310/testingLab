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
public class AnswerDTO implements Serializable {

    private String AnswerID, AnswerContent, QuestionID;
    private boolean Status;

    public AnswerDTO() {
    }

    public AnswerDTO(String AnswerID, String AnswerContent, String QuestionID, boolean Status) {
        this.AnswerID = AnswerID;
        this.AnswerContent = AnswerContent;
        this.QuestionID = QuestionID;
        this.Status = Status;
    }

    /**
     * @return the AnswerID
     */
    public String getAnswerID() {
        return AnswerID;
    }

    /**
     * @param AnswerID the AnswerID to set
     */
    public void setAnswerID(String AnswerID) {
        this.AnswerID = AnswerID;
    }

    /**
     * @return the AnswerContent
     */
    public String getAnswerContent() {
        return AnswerContent;
    }

    /**
     * @param AnswerContent the AnswerContent to set
     */
    public void setAnswerContent(String AnswerContent) {
        this.AnswerContent = AnswerContent;
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
