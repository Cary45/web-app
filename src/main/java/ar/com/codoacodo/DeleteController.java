package ar.com.codoacodo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MSQLDAOImpl;

@WebServlet("/Delete-Articulo")
public class DeleteController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DAO dao = new MSQLDAOImpl();
        try {
            dao.delete(Long.parseLong(id));
            String location = req.getContextPath() + "/Lista-Articulos";
            resp.sendRedirect(location);
        } catch (Exception e) {
            req.setAttribute("error", "Fallo la conexión con la base de datos");
            getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
