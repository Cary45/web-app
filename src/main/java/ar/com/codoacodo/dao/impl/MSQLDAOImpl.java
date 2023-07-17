package ar.com.codoacodo.dao.impl;

//JDBC
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ar.codoacodo.db.administradorDeConexiones;
import ar.com.codoacodo.oop.articulo;

//cumplo el contrato
public class MSQLDAOImpl implements DAO {

    // atributos
    private final String TABLE_NAME = "articulos";

  
    public articulo getById(Long id) throws Exception {
        String sql = "select * from " + TABLE_NAME + " where id = ?";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setLong(1, id);
        articulo articulo = null;
        ResultSet res = pst.executeQuery();
        if (res.next()) {
            String titulo = res.getString(2);
            String imagen = res.getString(3);
            String autor = res.getString(4);
            Double precio = res.getDouble(5);
            boolean novedad = res.getBoolean(6);
            String codigo = res.getString(7);
            Date fechaCreacion = res.getDate(8);
            articulo = new articulo(id, titulo, imagen, autor, precio, novedad, codigo);
        }
        return articulo;
    }

    @Override
    public void delete(Long id) throws Exception {
        String sql = "delete from " + TABLE_NAME + " where id = ? ";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setLong(1, id);
        pst.execute();
    }

    @Override
    public ArrayList<articulo> findAll() throws Exception {
        String sql = "select * from " + TABLE_NAME + "";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        return obtenerLsta(pst);
    }

    @Override
    public ArrayList<articulo> findAllByTitle(String clave) throws Exception {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE LOWER(titulo) LIKE LOWER(?)";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "%" + clave + "%");
        return obtenerLsta(pst);
    }

    private ArrayList<articulo> obtenerLsta(PreparedStatement pst) throws SQLException {
        ResultSet res = pst.executeQuery();
        ArrayList<articulo> listado = null;
        if (res.next()) {
            listado = new ArrayList<>();
            do {
                Long id = res.getLong(1);
                String titulo = res.getString(2);
                String imagen = res.getString(3);
                String autor = res.getString(4);
                Double precio = res.getDouble(5);
                boolean novedad = res.getBoolean(6);
                String codigo = res.getString(7);
                Date fechaCreacion = res.getDate(8);
                listado.add(new articulo(id, titulo, imagen, autor, precio, novedad, codigo, fechaCreacion));
            } while (res.next());
        }
        return listado;
    }

    @Override
    public void update(articulo articulo) throws SQLException {
        String sql = "update " + TABLE_NAME;
        sql += " set titulo= ?, imagen= ?, precio= ?, autor= ?";
        sql += "where id = ?";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, articulo.getTitulo());
        pst.setString(2, articulo.getImagen());
        pst.setDouble(3, articulo.getPrecio());
        pst.setString(4, articulo.getAutor());
        pst.setDouble(5, articulo.getId());
        pst.execute();

    }

    @Override
    public void create(articulo articulo) throws Exception {
        String sql = "insert into " + TABLE_NAME;
        sql += " (titulo,autor,precio,novedad,codigo) ";
        sql += "values (?,?,?,?,?) ";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, articulo.getTitulo());
        pst.setString(2, articulo.getAutor());
        pst.setDouble(3, articulo.getPrecio());
        pst.setInt(4, articulo.isNovedad() ? 1 : 0);
        pst.setString(5, articulo.getCodigo());
        pst.execute();
    }

    public boolean existeCodigo(String codigo) throws Exception {
        String sql = "SELECT EXISTS(SELECT 1 FROM " + TABLE_NAME + " WHERE codigo = ?)";
        Connection con = administradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet res = pst.executeQuery();
        if (res.next()) {
            int exists = res.getInt(1);
            return exists == 1;
        }
        return false;
    }

    @Override
    public articulo getbyId(Long Id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'getbyId'");
    }

    
    

}
