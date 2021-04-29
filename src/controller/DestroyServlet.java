package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.DBUtil;
import model.tasktask;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();


            tasktask m = em.find(tasktask.class, (Integer)(request.getSession().getAttribute("task_id")));

            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除が完了しました。");
            em.close();


            request.getSession().removeAttribute("task_id");


            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}