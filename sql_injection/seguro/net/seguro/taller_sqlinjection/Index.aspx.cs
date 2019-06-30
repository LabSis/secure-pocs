using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;
using System.Web.Script.Services;
using MySql.Data.MySqlClient;

namespace taller_sqlinjection
{
    public partial class Index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        [WebMethod]
        [ScriptMethod(UseHttpGet = true)]
        public static string GetProjects()
        {
            string salida = "";
            try
            {
                MySqlConnection connection = new MySqlConnection("Database=nsa;Data Source=localhost;User Id=root;Password=");
                connection.Open();
                MySqlCommand command = connection.CreateCommand();
                command.CommandText = "SELECT p.codigo AS code, p.nombre AS name, a.nombre AS owner, t.nombre AS type, n.nombre AS level " +
                    "FROM proyectos AS p " +
                    "INNER JOIN agentes AS a ON a.id = p.id_agente " +
                    "INNER JOIN niveles AS n ON n.id = p.id_nivel " +
                    "INNER JOIN tipos AS t ON t.id = p.id_tipo " +
                    "WHERE n.id >= 2 " +
                    "ORDER BY p.codigo " +
                    ";";
                MySqlDataReader reader = command.ExecuteReader();
                string proyectos = "[";
                while (reader.Read())
                {
                    string proyecto = "";
                    string codigo = reader.GetString("code");
                    string nombre = reader.GetString("name");
                    string agente = reader.GetString("owner");
                    string tipo = reader.GetString("type");
                    string nivel = reader.GetString("level");
                    proyecto += "{ " +
                        "\"code\": \"" + codigo + "\"," +
                        "\"name\": \"" + nombre + "\"," +
                        "\"owner\": \"" + agente + "\"," +
                        "\"type\": \"" + tipo + "\"," +
                        "\"level\": \"" + nivel + "\"" +
                        " },";
                    proyectos += proyecto;
                }
                proyectos = proyectos.Substring(0, proyectos.Length-1);
                proyectos += "]";
                reader.Close();
                salida = "{ \"status\": \"ok\", \"data\": { \"projects\": " + proyectos + " } }";
            }
            catch (Exception ex)
            {
                salida = "{ \"status\": \"error\" }";
            }
            return salida;
        }

        [WebMethod]
        [ScriptMethod(UseHttpGet = true)]
        public static string FilterProjects(string type)
        {
            string salida = "";
            try
            {
                MySqlConnection connection = new MySqlConnection("Database=nsa;Data Source=localhost;User Id=root;Password=");
                connection.Open();
                MySqlCommand command = connection.CreateCommand();
                command.CommandText = "SELECT p.codigo AS code, p.nombre AS name, a.nombre AS owner, t.nombre AS type, n.nombre AS level " +
                "FROM proyectos AS p " +
                "INNER JOIN agentes AS a ON a.id = p.id_agente " +
                "INNER JOIN niveles AS n ON n.id = p.id_nivel " +
                "INNER JOIN tipos AS t ON t.id = p.id_tipo " +
                "WHERE n.id >= 2 AND t.id = @type " +
                "ORDER BY p.codigo " +
                ";";
                command.Parameters.AddWithValue("@type", type);
                MySqlDataReader reader = command.ExecuteReader();
                string proyectos = "[";
                while (reader.Read())
                {
                    string proyecto = "";
                    string codigo = reader.GetString("code");
                    string nombre = reader.GetString("name");
                    string agente = reader.GetString("owner");
                    string tipo = reader.GetString("type");
                    string nivel = reader.GetString("level");
                    proyecto += "{ " +
                        "\"code\": \"" + codigo + "\"," +
                        "\"name\": \"" + nombre + "\"," +
                        "\"owner\": \"" + agente + "\"," +
                        "\"type\": \"" + tipo + "\"," +
                        "\"level\": \"" + nivel + "\"" +
                        " },";
                    proyectos += proyecto;
                }
                if (proyectos.Length > 1)
                {
                    proyectos = proyectos.Substring(0, proyectos.Length - 1);
                }
                proyectos += "]";
                reader.Close();
                salida = "{ \"status\": \"ok\", \"data\": { \"projects\": " + proyectos + " } }";
            }
            catch (Exception ex)
            {
                salida = "{ \"status\": \"error\" }";
            }
            return salida;
        }
    }
}