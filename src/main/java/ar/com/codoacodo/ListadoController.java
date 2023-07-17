//paquete: ar/com/codo
package ar.com.codoacodo;

import java.io.IOException;
import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MSQLDAOImpl;
import java.util.ArrayList;
import ar.com.codoacodo.oop.articulo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//App.java = Clase java
@WebServlet("/Lista-Articulos")
public class ListadoController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesarSolicitud(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesarSolicitud(req, resp);
    }

    private void procesarSolicitud(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DAO dao = new MSQLDAOImpl();
        ArrayList<articulo> listado = null;
        try {
            listado = dao.findAll();
        } catch (Exception e) {
            req.setAttribute("error", "Fallo la conexión con la base de datos ");
        } finally {
            req.setAttribute("articulos", listado);
            getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
        }

    } 
}