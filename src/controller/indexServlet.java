package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class indexServlet
 */
@WebServlet("/index")
public class indexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<tasktask> tasks = em.createNamedQuery("getAllTasks"
                + "", tasktask.class).getResultList();

        em.close();

        request.setAttribute("tasks", tasks);

        if(request.getSession().getAttribute("flush") != null) {

            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }




        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ttask/index.jsp");
        rd.forward(request, response);
    }

}
