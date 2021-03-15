/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.AnswerDAO;
import minhnln.DAO.QuestionDAO;
import minhnln.DTO.AnswerDTO;
import minhnln.DTO.QuestionDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "TakeAQuizSevlet", urlPatterns = {"/TakeAQuizSevlet"})
public class TakeAQuizSevlet extends HttpServlet {

    private final String DO_QUIZ_PAGE = "doquiz.jsp";
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        final int pagingSize = 1; //tổng câu hỏi của 1 trang
        int SumofQuestion;// tổng số lượng câu hỏi
        int NumofPage;//Số trang sẽ được tạo
        int pageNo = 1;//Mặc định sẽ là trang đầu tiên (pageNo=1)
        String timeString = request.getParameter("cbbTime");
        String subjectID = request.getParameter("txtSubjectID");
        String[] QuestionIDArray = null;
        try {
            url = DO_QUIZ_PAGE;
            QuestionDAO Qdao = new QuestionDAO();
            AnswerDAO Adao = new AnswerDAO();
            //Về Số lượng câu hỏi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            int NumOfQuestionBaseTime = 0;
            if (timeString.equals("60:00")) {
                NumOfQuestionBaseTime = 60;
            } else if (timeString.equals("45:00")) {
                NumOfQuestionBaseTime = 45;
            } else if (timeString.equals("30:00")) {
                NumOfQuestionBaseTime = 30;
            } else if (timeString.equals("15:00")) {
                NumOfQuestionBaseTime = 15;
            } else if (timeString.equals("10:00")) {
                NumOfQuestionBaseTime = 10;
            } else if (timeString.equals("05:00")) {
                NumOfQuestionBaseTime = 5;
            }
            //Về thời gian
            String[] timeArray = timeString.split(":");
            Calendar now = Calendar.getInstance();///Thời gian hiện tại
            now.add(Calendar.MINUTE, Integer.parseInt(timeArray[0]));//Thời gian hiện tại + n phút
            Date QuizTime = now.getTime();//Chuyển từ dạng calendar sang Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy hh:mm:ss");//Đổi sang định dạng Date cho JavaScript
            String QuizTimeString = dateFormat.format(QuizTime);
            //Về số lượng trang~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            if (request.getParameter("pageNo") != null) {
//                pageNo = Integer.parseInt(request.getParameter("pageNo"));
//            }
//            SumofQuestion = NumOfQuestionBaseTime;
//            NumofPage = SumofQuestion / pagingSize;
//            if (SumofQuestion > pagingSize * NumofPage) {//Nếu tổng sản phẩm lớn hơn tổng số lượng sản phẩm trong các trang
//                NumofPage += 1;
//            }
            //Về hiển thị~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            QuestionIDArray = Qdao.getRandomQuestionID(NumOfQuestionBaseTime, subjectID);
            for (int i = 0; i < QuestionIDArray.length; i++) {
                if (Qdao.setRandomQuestionID(i + 1, QuestionIDArray[i])) {
                    url = DO_QUIZ_PAGE;
                } else {
                    request.setAttribute("ERROR", "Error occured when taking quiz!!");
                    url = ERROR_PAGE;
                }
            }
            int RandomID = 1;
            List<QuestionDTO> QuizList = Qdao.displayAllQuestionInQuiz(RandomID);
            Map<String, List<AnswerDTO>> questionmap = new HashMap<>();
            for (int i = 0; i < QuizList.size(); i++) {
                String QuestionID = QuizList.get(i).getQuestionID();
                List<AnswerDTO> Alist = Adao.displayAnswerPerQuestion(QuestionID);
                questionmap.put(QuestionID, Alist);
            }
            HttpSession session = request.getSession();
            session.setAttribute("QUIZLIST", QuizList);
            session.setAttribute("MAP", questionmap);
            session.setAttribute("QUIZPOS", RandomID);
            session.setAttribute("QUIZTIME", QuizTimeString);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TakeAQuizSevlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TakeAQuizSevlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
