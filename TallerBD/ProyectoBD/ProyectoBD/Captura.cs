using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ProyectoBD
{
    public partial class Captura : Form
    {
        private BaseDeDatos db;
        private string servidor, baseDatos, usuario, pass;
        List<string> cbm;

        public Captura(string servidor, string baseDatos, string usuario, string pass)
        {
            InitializeComponent();
            this.servidor = servidor;
            this.baseDatos = baseDatos;
            this.usuario = usuario;
            this.pass = pass;
            db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
        }

        private void rbnNuevo_CheckedChanged(object sender, EventArgs e)
        {
            btnEliminar.Enabled = false;
            txtClave.Text = "*";
            txtClave.Enabled = false;
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            txtPrecio.Text = "";
            cmbFamilia.Text = "";
            btnGrabar.Text = "Grabar";
        }

        private void rbnModificar_CheckedChanged(object sender, EventArgs e)
        {
            txtClave.Text = "";
            txtClave.Enabled = true;
            btnGrabar.Text = "Modificar";
            btnEliminar.Enabled = true;
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            txtPrecio.Text = "";
            cmbFamilia.Text = "";

        }

        private void btnLimpiar_Click(object sender, EventArgs e)
        {
            if (rbnNuevo.Checked)
            {
                txtClave.Text = "*";
            }
            if (rbnModificar.Checked)
            {
                txtClave.Text = "";
            }
            txtNombre.Text = "";
            txtDescripcion.Text = "";
            txtPrecio.Text = "";
            cmbFamilia.Text = "";
        }

        private void btnGrabar_Click(object sender, EventArgs e)
        {     
            if (rbnNuevo.Checked)
            {
                
                if (Validar())              
                {
                    db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                    string nombre = txtNombre.Text, descripcion = txtDescripcion.Text;
                    string precio = txtPrecio.Text;


                    string query = "SP_CAPTURA ";

                    using (SqlConnection connection = db.GetConnection())
                    {
                        using (SqlCommand command = new SqlCommand(query, connection))
                        {
                            command.CommandType = CommandType.StoredProcedure;

                            SqlParameter artIdParam = new SqlParameter("@artID", SqlDbType.Int);
                            artIdParam.Direction = ParameterDirection.Output;

                            SqlParameter nombreParam = new SqlParameter("@artnombre", SqlDbType.VarChar, 40);
                            nombreParam.Value = nombre;

                            SqlParameter descripcionParam = new SqlParameter("@artdescripcion", SqlDbType.VarChar, 40);
                            descripcionParam.Value = descripcion;

                            SqlParameter precioParam = new SqlParameter("@artprecio", SqlDbType.Decimal);
                            precioParam.Value = precio;

                            SqlParameter famIdParam = new SqlParameter("@famID", SqlDbType.Int);
                            famIdParam.Value = familias(cmbFamilia.Text);
                                                           
                            command.Parameters.Add(artIdParam);
                            command.Parameters.Add(nombreParam);
                            command.Parameters.Add(descripcionParam);
                            command.Parameters.Add(precioParam);
                            command.Parameters.Add(famIdParam);
                            try
                            {
                                connection.Open();
                                command.ExecuteNonQuery();

                                int nuevoArtId = Convert.ToInt32(command.Parameters["@artID"].Value);
                                txtClave.Text = nuevoArtId.ToString();
                                lblMensaje.Text = "Articulo agregado con id " + nuevoArtId;
                                lblMensaje.ForeColor = Color.Green;
                            }
                            catch (Exception ex)
                            {
                                lblMensaje.Text = "No se pudo grabar el campo";
                                lblMensaje.ForeColor = Color.Red;
                            }
                        }
                        
                        txtNombre.Clear();
                        txtPrecio.Clear();
                        txtDescripcion.Clear();
                        cmbFamilia.Text = " ";
                        db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                        dtvProd.DataSource = db.Consultar();

                        txtClave.Clear();
                    }
                }
            }
            else if (rbnModificar.Checked)
            {
                if (Validar())                
                {
                    db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                    string nombre = txtNombre.Text, descripcion = txtDescripcion.Text;
                    string precio = txtPrecio.Text;
                    int id = Convert.ToInt32(txtClave.Text);


                    string query = "SP_CAPTURA ";

                    using (SqlConnection connection = db.GetConnection())
                    {
                        using (SqlCommand command = new SqlCommand(query, connection))
                        {
                            command.CommandType = CommandType.StoredProcedure;


                            SqlParameter artIdParam = new SqlParameter("@artID", SqlDbType.Int);
                            artIdParam.Value = id;

                            SqlParameter nombreParam = new SqlParameter("@artnombre", SqlDbType.VarChar, 40);
                            nombreParam.Value = nombre;

                            SqlParameter descripcionParam = new SqlParameter("@artdescripcion", SqlDbType.VarChar, 40);
                            descripcionParam.Value = descripcion;

                            SqlParameter precioParam = new SqlParameter("@artprecio", SqlDbType.Decimal);
                            precioParam.Value = precio;

                            SqlParameter famIdParam = new SqlParameter("@famID", SqlDbType.Int);
                            famIdParam.Value = familias(cmbFamilia.Text);

                            command.Parameters.Add(artIdParam);
                            command.Parameters.Add(nombreParam);
                            command.Parameters.Add(descripcionParam);
                            command.Parameters.Add(precioParam);
                            command.Parameters.Add(famIdParam);
                            try
                            {
                                connection.Open();
                                command.ExecuteNonQuery();

                                int nuevoArtId = Convert.ToInt32(command.Parameters["@artID"].Value);
                                txtClave.Text = nuevoArtId.ToString();
                                lblMensaje.Text = "Articulo modificado" + nuevoArtId;
                                lblMensaje.ForeColor = Color.Green;
                            }
                            catch (Exception ex)
                            {
                                lblMensaje.Text = "No se pudo actualizar el campo";
                                lblMensaje.ForeColor = Color.Red;
                            }
                        }
                        txtNombre.Clear();
                        txtPrecio.Clear();
                        txtDescripcion.Clear();
                        cmbFamilia.Text = "";
                        db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                        dtvProd.DataSource = db.Consultar();
                    }
                }
            }
        }

        private void txtClave_TextChanged(object sender, EventArgs e)
        {
            if(txtClave.Text.Length == 0) return;
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

        private void dtvProd_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (rbnModificar.Checked )
            {
                if (dtvProd.RowCount > 1)
                {
                    int rowIndex = 0;
                    if (dtvProd.SelectedRows.Count > 0)
                    {
                        rowIndex = dtvProd.SelectedRows[0].Index;
                    }
                    else
                    {
                        rowIndex = dtvProd.CurrentCell.RowIndex;
                    }
                    txtClave.Text = dtvProd.Rows[rowIndex].Cells[0].Value.ToString();
                    txtNombre.Text = dtvProd.Rows[rowIndex].Cells[1].Value.ToString();
                    txtDescripcion.Text = dtvProd.Rows[rowIndex].Cells[2].Value.ToString();
                    txtPrecio.Text = dtvProd.Rows[rowIndex].Cells[3].Value.ToString();
                    cmbFamilia.SelectedIndex = Convert.ToInt32(dtvProd.Rows[rowIndex].Cells[4].Value.ToString()) - 1;
                }
            }
        }      

        private void Captura_Load(object sender, EventArgs e)
        {
            GetData();
            txtClave.Enabled = false;

        }

        private void GetData()
        {
            try
            {
                db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                DataTable dt = db.Consultar();
                dtvProd.DataSource = dt;
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

        private void cmbFamilia_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnEliminar_Click(object sender, EventArgs e)
        {
            db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
            int id = Convert.ToInt32(txtClave.Text);
            string query = " Delete from articulos where artId= @artId ";

            using (SqlConnection connection = db.GetConnection())
            {
                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@artId", id);
                    try
                    {
                        connection.Open();
                        command.ExecuteNonQuery();
                        MessageBox.Show("Articulo eliminado", "Mensaje", MessageBoxButtons.OK);
                        db = new BaseDeDatos(servidor, baseDatos, usuario, pass);
                        dtvProd.DataSource = db.Consultar();

                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Articulo no eliminado", "Mensaje", MessageBoxButtons.OK);
                        MessageBox.Show(ex.Message);
                    }
                }
            }

            txtClave.Clear();
            txtNombre.Clear();
            txtPrecio.Clear();
            txtDescripcion.Clear();
            cmbFamilia.Text = "";
        }

        private int familias(string fam)
        {
            for (int i = 0; i < cbm.Count; i++)
            {
                if (fam == cbm[i])
                {
                    return i + 1;
                    break;
                }
            }
            return -1; //No hay familias
        }

        private bool Validar()
        {
            bool ok = true;
            //comienza a validar espacios en blanco
            
             if (String.IsNullOrEmpty(txtNombre.Text))
            {
                errorProvider1.SetError(txtNombre, "Campo Vacio");
                ok = false;
            }
            if (String.IsNullOrEmpty(txtDescripcion.Text))
            {
                errorProvider1.SetError(txtDescripcion, "Campo Vacio");
                ok = false;
            }
           if (String.IsNullOrEmpty(txtPrecio.Text))
            {
                errorProvider1.SetError(txtPrecio, "Campo Vacio");
                ok = false;
            }
            if (String.IsNullOrEmpty(cmbFamilia.Text))
            {
                errorProvider1.SetError(cmbFamilia, "Campo Vacio");                
                ok = false;
            }
            return ok;
        }

        private void BorrarError()
        {
            errorProvider1.SetError(txtDescripcion, "");
            errorProvider1.SetError(txtNombre, "");
            errorProvider1.SetError(txtPrecio, "");
            errorProvider1.SetError(cmbFamilia, "");
        }
    }
}
