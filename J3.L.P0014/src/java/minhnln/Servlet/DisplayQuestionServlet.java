/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.AnswerDAO;
import minhnln.DAO.QuestionDAO;
import minhnln.DAO.SubjectDAO;
import minhnln.DTO.AnswerDTO;
import minhnln.DTO.QuestionDTO;
import minhnln.DTO.SubjectDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "DisplayQuestionServlet", urlPatterns = {"/DisplayQuestionServlet"})
public class DisplayQuestionServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String SEARCH_PAGE = "search.jsp";

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
        String url = ERROR_PAGE;
        final int pagingSize = 5; //tổng câu hỏi của 1 trang
        int SumofQuestion;// tổng số lượng câu hỏi
        int NumofPage;//Số trang sẽ được tạo
        int pageNo = 1;//Mặc định sẽ là trang đầu tiên (pageNo=1)
        try {
            QuestionDAO Qdao = new QuestionDAO();
            AnswerDAO Adao = new AnswerDAO();
            SubjectDAO Sdao = new SubjectDAO();
            //Về số lượng trang~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            if (request.getParameter("pageNo") != null) {
//                pageNo = Integer.parseInt(request.getParameter("pageNo"));
//            }
//            SumofQuestion = Qdao.getTotalOfQuestion();
//            NumofPage = SumofQuestion / pagingSize;
//            if (SumofQuestion > pagingSize * NumofPage) {//Nếu tổng sản phẩm lớn hơn tổng số lượng sản phẩm trong các trang
//                NumofPage += 1;
//            }
            //Về hiển thị~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            List<QuestionDTO> Qlist = Qdao.displayAllQuestionWithPaging(pageNo, pagingSize);
            Map<String, List<AnswerDTO>> questionmap = new HashMap<>();
            for (int i = 0; i < Qlist.size(); i++) {
                String QuestionID = Qlist.get(i).getQuestionID();
                List<AnswerDTO> Alist = Adao.displayAnswerPerQuestion(QuestionID);
                questionmap.put(QuestionID, Alist);
            }
            List<SubjectDTO> Slist = Sdao.getSubject();
            HttpSession session = request.getSession();
            session.setAttribute("SLIST", Slist);
            session.setAttribute("QLIST", Qlist);
            session.setAttribute("MAP", questionmap);
            url = SEARCH_PAGE;
        } catch (Exception e) {
            log("Error at Logout Servlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
