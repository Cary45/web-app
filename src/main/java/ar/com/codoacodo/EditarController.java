package ar.com.codoacodo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MSQLDAOImpl;
import ar.com.codoacodo.oop.articulo;

@WebServlet("/Editar-Articulo")
public class EditarController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new MSQLDAOImpl();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            articulo articulo = dao.getbyId(null);
            req.setAttribute("articulo", articulo);
        } catch (Exception e) {
            req.setAttribute("error", "No se ha encontrado el artículo");
            articulo nuevo = new articulo(null, getServletInfo(), getServletInfo(), getServletName(), null, false, getServletInfo()); 
            
            req.setAttribute("articulo", nuevo);
        }
        getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // tendria que tener los parametros del front (<form>)
        long id = Long.parseLong(req.getParameter("id"));
        String titulo = req.getParameter("nombre");
        String precioString = req.getParameter("precio");
        Double precio = Double.parseDouble(precioString);
        String autor = req.getParameter("autor");
        String codigo = req.getParameter("codigo");
        articulo nuevo = new articulo(id, titulo, (titulo + ".jpg"), autor, precio, false, codigo);
        DAO dao = new MSQLDAOImpl();
        try {
            dao.update(nuevo);
            req.setAttribute("success", "Se ha editado el articulo " + nuevo.getId());
            req.getRequestDispatcher("/Lista-Articulos").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "No se ha editado el articulo");
            req.setAttribute("articulo", nuevo);
            req.getRequestDispatcher("/editar.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}