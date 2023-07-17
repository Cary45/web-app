package ar.codoacodo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class administradorDeConexiones {

    /*stactic permite usar Clase.metodo
     * administradores de conexiones.getCconnection()     * sin necesidad de instanciar un objeto
     */
    /**
     * @return
     */
    public static Connection getConnection(){
        String hosts = "127.0.0.1";        //local host
        String port = "3306";
        String pasword = "";
        String username = "root";

        //driver de conexion a la base de datos
        String driverClassName = "comision 23049";

        //nombre de la base de datos
        String dbName = "comision 23049";

        //no voy a explicar to cath solo lo vamos a usar
        Connection connection;
        try {
            Class.forName(driverClassName);

            String url = " jdbc:mysql://" + hosts + ":" + port +"/"+dbName+ "?allowPublickeyRetrieval=true&serverTimeZone=UTC&useSSL=false";

            connection = DriverManager.getConnection(url,username,pasword);
        }catch(Exception e) {
        connection = null;
        }

        return connection;
        
    }
}
