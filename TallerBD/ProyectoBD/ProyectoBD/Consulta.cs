using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;


namespace ProyectoBD
{
    public partial class Consulta : Form
    {
        private BaseDeDatos db;
        private string servidor, baseDatos, usuario, pass;
        List<string> cbm;
        public Consulta(string servidor, string baseDatos, string usuario, string pass)
        {
            InitializeComponent();
            this.servidor = servidor;
            this.baseDatos = baseDatos;
            this.usuario = usuario;
            this.pass = pass;
            db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
        }

        private void btnBuscar_Click(object sender, EventArgs e)
        {
            // Crea la cadena de conexión
            db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
            if (string.IsNullOrEmpty(txtClave.Text) && string.IsNullOrEmpty(txtNombre.Text) && string.IsNullOrEmpty(txtDescripcion.Text) && string.IsNullOrEmpty(txtPrecio.Text) && string.IsNullOrEmpty(cmbFamilia.Text))
            {

                string query = "SELECT a.artId,a.artnombre,a.artdescripcion,a.artprecio,'Familia'=f.famnombre " +
                            "FROM articulos a " +
                            "inner join familias f " +
                            "on a.famid=f.famid";
                using (SqlConnection connection = db.GetConnection())
                {

                    connection.Open();
                    try
                    {
                        SqlCommand command = new SqlCommand(query, connection);
                        SqlDataReader reader = command.ExecuteReader();

                        DataTable dataTable = new DataTable();
                        dataTable.Load(reader);

                        dataGridView1.DataSource = dataTable;
                    }
                    catch(Exception ex)
                    {
                        lblMensaje.Text = "No tienes permiso Select";
                        lblMensaje.ForeColor = Color.Red;
                    }
                    
                }
            }
            else
            {
                db = new BaseDeDatos(servidor, baseDatos, usuario, pass);


                using (SqlConnection connection = db.GetConnection())
                {

                    connection.Open();


                    string query = "SELECT a.artId,a.artnombre,a.artdescripcion,a.artprecio,'Familia'=f.famnombre " +
                            "FROM articulos a " +
                            "inner join familias f " +
                            "on a.famid=f.famid " +
                            "where ";
                    bool first = true;

                    if (!string.IsNullOrEmpty(txtClave.Text))
                    {
                        query += "artid LIKE @Clave";
                        first = false;
                    }

                    if (!string.IsNullOrEmpty(txtNombre.Text))
                    {
                        if (!first)
                        {
                            query += " AND ";
                        }

                        query += "artnombre LIKE @Nombre";
                        first = false;
                    }

                    if (!string.IsNullOrEmpty(txtDescripcion.Text))
                    {
                        if (!first)
                        {
                            query += " AND ";
                        }

                        query += "artdescripcion LIKE @descripcion";
                        first = false;
                    }

                    if (!string.IsNullOrEmpty(txtPrecio.Text))
                    {
                        if (!first)
                        {
                            query += " AND ";
                        }

                        query += "artprecio LIKE @precio";
                        first = false;
                    }

                    if (!string.IsNullOrEmpty(cmbFamilia.Text))
                    {
                        if (!first)
                        {
                            query += " AND ";
                        }

                        query += "f.famnombre LIKE @famid";
                        first = false;
                    }

                    SqlCommand command = new SqlCommand(query, connection);

                    if (!string.IsNullOrEmpty(txtClave.Text))
                    {
                        command.Parameters.AddWithValue("@Clave", "%" + txtClave.Text + "%");
                    }

                    if (!string.IsNullOrEmpty(txtNombre.Text))
                    {
                        command.Parameters.AddWithValue("@Nombre", "%" + txtNombre.Text + "%");
                    }

                    if (!string.IsNullOrEmpty(txtDescripcion.Text))
                    {
                        command.Parameters.AddWithValue("@descripcion", "%" + txtDescripcion.Text + "%");
                    }

                    if (!string.IsNullOrEmpty(txtPrecio.Text))
                    {
                        command.Parameters.AddWithValue("@precio", "%" + txtPrecio.Text + "%");
                    }

                    if (!string.IsNullOrEmpty(cmbFamilia.Text))
                    {
                        command.Parameters.AddWithValue("@famid", "%" + cmbFamilia.Text + "%");
                    }
                    try
                    {
                        SqlDataReader reader = command.ExecuteReader();
                        DataTable dataTable = new DataTable();
                        dataTable.Load(reader);

                        dataGridView1.DataSource = dataTable;
                    }
                    catch(Exception ex) 
                    {
                        lblMensaje.Text = "No tienes permiso Select";
                        lblMensaje.ForeColor = Color.Red;
                    }
                        
                }
            }
        }

        private void Consulta_Load(object sender, EventArgs e)
        {
            GetData();
            
        }

        private void txtClave_TextChanged(object sender, EventArgs e)
        {
            if (txtClave.Text.Length == 0) return;
            // Crea la cadena de conexión
            db = new BaseDeDatos(servidor, baseDatos, usuario, pass);

            // Crea un objeto SqlConnection utilizando la cadena de conexión
            using (SqlConnection connection = db.GetConnection())
            {
                // Abre la conexión
                connection.Open();

                // Escribe la consulta SQL utilizando los valores de los TextBoxs
                string query = "SELECT * FROM articulos WHERE ";

                if (!string.IsNullOrEmpty(txtClave.Text))
                {
                    query += "artid LIKE @Clave";
                    try
                    {
                        SqlCommand command = new SqlCommand(query, connection);
                        command.Parameters.AddWithValue("@Clave", "%" + txtClave.Text + "%");
                        SqlDataReader reader = command.ExecuteReader();
                        if (reader.Read())
                        {
                            txtClave.Text = reader[0].ToString();
                            txtNombre.Text = reader[1].ToString();
                            txtDescripcion.Text = reader[2].ToString();
                            txtPrecio.Text = reader[3].ToString();
                            cmbFamilia.Text = cbm[Int32.Parse(reader[4].ToString()) - 1];
                        }
                    }
                    catch (Exception ex)
                    {
                        lblMensaje.Text = "No tienes permiso de select";
                        lblMensaje.ForeColor = Color.Red;
                    }

                }
            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void cmbFamilia_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtClave.Text = "";
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            txtPrecio.Text = "";
            cmbFamilia.Text = "";
        }

        private void GetData()
        {
            try
            {
                db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                DataTable dt = db.Consultar();
                dataGridView1.DataSource = dt;
                db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                cbm = db.ConsultarCMB();

                foreach (var item in cbm)
                {
                    cmbFamilia.Items.Add(item);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            
        }

        private int familias(string fam)
        {
            for (int i = 0; i < cbm.Count; i++)
            {
                if (fam == cbm[i])
                {
                    return i+1;
                    break;
                }
            }
            return -1; //No hay familias
        }

    }
}
