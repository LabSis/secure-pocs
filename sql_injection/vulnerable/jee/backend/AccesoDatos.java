import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class AccesoDatos {

    private Connection conexion;
    private String usuarioDB = "";
    private String passwordDB = "";
    private String bd = "";

    public AccesoDatos() {
        /*File archivoConfig = new File("config");
        try {
            Scanner sc = new Scanner(archivoConfig);
            int i = 0;
            while (i < 2 && sc.hasNextLine()) {
                String bd = sc.nextLine();
                String usuario = sc.nextLine();
                String clave = sc.nextLine();
                this.bd = bd;
                this.usuarioDB = usuario;
                this.passwordDB = clave;
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/nsa";
            String usuarioDB = "root";
            String passwordDB = "12345678";
            this.conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Proyecto> getProyectos() {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        try {
            conectar();
            Statement st = this.conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT proyectos.codigo, proyectos.nombre AS proyecto, agentes.nombre, agentes.apellido, tipos.id, tipos.nombre AS tipo, niveles.nombre AS nivel FROM proyectos INNER JOIN agentes ON (proyectos.id_agente = agentes.id) INNER JOIN tipos ON (proyectos.id_tipo = tipos.id) INNER JOIN niveles ON (proyectos.id_nivel = niveles.id) WHERE proyectos.id_nivel != (SELECT id FROM niveles WHERE nombre='Top Secret')");
            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.codigo = rs.getString("codigo");
                p.proyecto = rs.getString("proyecto");
                p.nombre = rs.getString("nombre");
                p.apellido = rs.getString("apellido");
                p.tipo = rs.getString("tipo");
                p.nivel = rs.getString("nivel");
                proyectos.add(p);
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }
    
    public ArrayList<Proyecto> getProyectosVulnerable(String tipo) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        try {
            conectar();
            Statement st = this.conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT proyectos.codigo, proyectos.nombre AS proyecto, agentes.nombre, agentes.apellido, tipos.id, tipos.nombre AS tipo, niveles.nombre AS nivel FROM proyectos INNER JOIN agentes ON (proyectos.id_agente = agentes.id) INNER JOIN tipos ON (proyectos.id_tipo = tipos.id) INNER JOIN niveles ON (proyectos.id_nivel = niveles.id) WHERE tipos.id = " + tipo + " AND proyectos.id_nivel != (SELECT id FROM niveles WHERE nombre='Top Secret')");
            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.codigo = rs.getString("codigo");
                p.proyecto = rs.getString("proyecto");
                p.nombre = rs.getString("nombre");
                p.apellido = rs.getString("apellido");
                p.tipo = rs.getString("tipo");
                p.nivel = rs.getString("nivel");
                proyectos.add(p);
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }

    public ArrayList<Proyecto> getProyectosSeguro(String tipo) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        try {
            conectar();
            String query = "SELECT proyectos.codigo, proyectos.nombre AS proyecto, agentes.nombre, agentes.apellido, tipos.id, tipos.nombre AS tipo, niveles.nombre AS nivel FROM proyectos INNER JOIN agentes ON (proyectos.id_agente = agentes.id) INNER JOIN tipos ON (proyectos.id_tipo = tipos.id) INNER JOIN niveles ON (proyectos.id_nivel = niveles.id) WHERE tipos.id = ? AND proyectos.id_nivel != (SELECT id FROM niveles WHERE nombre='Top Secret')";
            PreparedStatement ps = this.conexion.prepareStatement(query);
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.codigo = rs.getString("codigo");
                p.proyecto = rs.getString("proyecto");
                p.nombre = rs.getString("nombre");
                p.apellido = rs.getString("apellido");
                p.tipo = rs.getString("tipo");
                p.nivel = rs.getString("nivel");
                proyectos.add(p);
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }
}
