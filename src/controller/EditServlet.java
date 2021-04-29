package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.DBUtil;
import model.tasktask;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        tasktask m = em.find(tasktask.class, Integer.parseInt(request.getParameter("id")));

        em.close();


        request.setAttribute("task", m);
        request.setAttribute("_token", request.getSession().getId());

        if(m != null) {
        request.getSession().setAttribute("task_id", m.getId());}

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ttask/edit.jsp");
        rd.forward(request, response);
    }
}