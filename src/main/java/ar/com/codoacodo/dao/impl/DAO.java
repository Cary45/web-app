package ar.com.codoacodo.dao.impl;

import java.util.ArrayList;

import ar.com.codoacodo.oop.articulo;

//el contrato es dao
//no se puede instanciar
public interface DAO {
    
    //el que debe hacer el que quiera usar el contrato DAO
    public articulo getbyId(Long Id) throws Exception;
    public void delete(Long id) throws Exception; //la PK de la tabla
    public ArrayList<articulo> findAll() throws Exception; // lista
    public void update(articulo articulo) throws Exception;//tiene id
    public void create(articulo articulo) throws Exception; //no tiene ID
    public ArrayList<articulo> findAllByTitle(String clave) throws Exception;
    public boolean existeCodigo(String codigo) throws Exception;
}
 