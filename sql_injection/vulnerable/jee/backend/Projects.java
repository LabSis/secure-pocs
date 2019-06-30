import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projects extends HttpServlet {

    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccesoDatos ad = new AccesoDatos();
        ArrayList<Proyecto> proyectos = ad.getProyectos();
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Proyecto p : proyectos) {
            sb.append("{");
            sb.append("\"code\":\"").append(p.codigo).append("\",");
            sb.append("\"name\":\"").append(p.nombre).append("\",");
            sb.append("\"type\":\"").append(p.tipo).append("\",");
            sb.append("\"level\":\"").append(p.nivel).append("\",");
            sb.append("\"owner\":\"").append(p.apellido + ", " + p.nombre).append("\"}");
            if (i < proyectos.size() - 1) {
                sb.append(",");
            }
            i++;
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"status\": \"ok\", \"data\":{\"projects\": [" + sb.toString() + "]}}");
     }

    public void destroy() {
    }

}
