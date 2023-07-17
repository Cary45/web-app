package ar.com.codoacodo;

import java.io.IOException;
import java.util.ArrayList;
import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MSQLDAOImpl;
import ar.com.codoacodo.oop.articulo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Buscar-Articulo")
public class BuscadorController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String clave = req.getParameter("clave");
        DAO dao = new MSQLDAOImpl();
        ArrayList<articulo> listado = null;
        try {
            listado = dao.findAllByTitle(clave);
        } catch (Exception e) {
            req.setAttribute("error", "Fallo la conexión con la base de datos ");
        } finally {
            req.setAttribute("articulos", listado);
            getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
        }

    }

}
