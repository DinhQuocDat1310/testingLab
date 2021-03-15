/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhnln.DAO.AnswerDAO;
import minhnln.DAO.QuestionDAO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {

    private final String DISPLAY_SERVLET = "DisplayQuestionServlet";
    private final String ERROR_PAGE = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String questionContent = request.getParameter("txtQuestionContent");
        String subject = request.getParameter("cboSubject");
        String[] answerContentArray = request.getParameterValues("txtAnswerContent");
        Date createDate = (Date) Calendar.getInstance().getTime();
        java.sql.Date createDateSQL = new java.sql.Date(createDate.getTime());
        String url = ERROR_PAGE;
        try {
            QuestionDAO Qdao = new QuestionDAO();
            AnswerDAO Adao = new AnswerDAO();
            //Xử lý question ID
            String questionID = Qdao.getLastQuestionIDBaseSubject(subject);
            if (questionID.isEmpty()) {
                questionID = subject + "-0001";
            } else {
                String[] arrayQuestionID = questionID.split("-");
                questionID = subject + "-" + String.format("%04d", (Integer.parseInt(arrayQuestionID[1]) + 1));
            }
            if (Qdao.createQuestion(questionID, questionContent, createDateSQL, subject)) {
                for (int i = 0; i < answerContentArray.length; i++) {
                    //Xử lý dữ liệu của Answer
                    String answerID = questionID + "-" + (i + 1);
                    String answerContent = answerContentArray[i];
                    boolean status = Boolean.parseBoolean(request.getParameter("rbtStatus" + (i + 1)));
                    if (Adao.createAnswer(answerID, answerContent, questionID, status)) {
                        url = DISPLAY_SERVLET;
                        System.out.println(i);
                        System.out.println(questionID);
                        System.out.println(subject);
                        System.out.println(answerID + "--" + i);
                        System.out.println(answerContent + "--" + i);
                        System.out.println(questionID + "--" + i);
                        System.out.println(request.getParameter("rbtStatus" + i + 1));
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CreateQuestionServlet: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
