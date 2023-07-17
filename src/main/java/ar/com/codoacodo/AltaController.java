package ar.com.codoacodo;

import java.io.IOException;

import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MSQLDAOImpl;
import ar.com.codoacodo.oop.articulo;

/**
 app java - clase java
 */
@WebServlet("/AltaController")
public class AltaController extends HttpServlet {
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    
    //CREATE CONTROLLER

  
      //tendria q tener los parametros del front(<form>)
      String titulo = req.getParameter("titulo");
      double precio = Double.parseDouble(req.getParameter("precio"));
      String autor =req.getParameter("autor");
      String codigo = req.getParameter("codigo");
      String isnb = req.getParameter("isnb");
      LocalDateTime fechaCreacion = LocalDateTime.now();
       
      //instanciar un articulo
    
      articulo nuevo = new articulo(titulo, titulo + ".jpg", autor, precio,false, codigo);

       //interfase  nombre= claseQueImplementaLaInterfase()
      DAO dao = new MSQLDAOImpl();
      

      //puedo usar los metodos que tienen DAO , sin saber quien cumple el contrato
      try {
        dao.create(nuevo); 
    } catch (Exception e) {
        e.printStackTrace();
    }//try/catch/finally
      
      System.out.println(nuevo);
      }


}   