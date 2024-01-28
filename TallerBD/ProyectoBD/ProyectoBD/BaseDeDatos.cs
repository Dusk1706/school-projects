using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;
using System.Configuration;

namespace ProyectoBD
{
    class BaseDeDatos
    {
        private string connectionString;
        private SqlConnection connection;

        public BaseDeDatos(string servidor, string baseDatos, string usuario, string pass)
        {         
            this.connectionString = $"Data Source={servidor};Initial Catalog={baseDatos};User ID={usuario};Password={pass}";
            this.connection = new SqlConnection(connectionString);
        }

        public bool TestConection()
        {
            try
            {
                connection.Open();
                connection.Close();
                return true;
            }

            catch (Exception ex)
            {
                Console.WriteLine("Error al conectar la base de datos" + ex.Message);
                return false;
            }
        }

        public SqlConnection GetConnection()
        {
            if (TestConection())
            {
                return connection;
            }
            else
            {
                return null;
            }
        }

        public void CloseConnection()
        {
            if (connection.State == ConnectionState.Open)
            {
                connection.Close();
            }
        }

        public DataTable Consultar()
        {
            DataTable dataTable = new DataTable();
            try
            {
                using (this.connection = GetConnection())
                {
                    connection.Open();
                    SqlCommand cmd;
                    string query = "SELECT a.artId,a.artnombre,a.artdescripcion,a.artprecio,'Familia'=f.famnombre " +
                            "FROM articulos a " +
                            "inner join familias f " +
                            "on a.famid=f.famid ";
                    using (cmd = new SqlCommand(query, connection))
                    {
                        using (SqlDataReader reader = cmd.ExecuteReader())
                        {
                            dataTable.Load(reader);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al consultar datos" + ex.Message);
            }
            CloseConnection();
            return dataTable;
        }

        public List<string> ConsultarCMB()
        {
            List<string> familias = new List<string>();
            try
            {
                using (this.connection = GetConnection())
                {
                    connection.Open();
                    SqlCommand cmd;
                    using (cmd = new SqlCommand("SELECT famnombre FROM Familias", connection))
                    {
                        using (SqlDataReader reader = cmd.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                string nombre = reader.GetString(0);
                                familias.Add(nombre);
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al consultar datos" + ex.Message);
            }
            CloseConnection();
            return familias;
        }

    }
}
