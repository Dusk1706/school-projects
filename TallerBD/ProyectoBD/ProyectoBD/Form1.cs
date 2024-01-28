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
    public partial class Form1 : Form
    {
        BaseDeDatos db;
        public Form1()
        {
            InitializeComponent();
        }
        private void btnCrearSp_Click(object sender, EventArgs e)
        {
        }

        private void btnConectar_Click(object sender, EventArgs e)
        {
            if (Validar())
            {
                BorrarError();
                
                try
                {
                    db = new BaseDeDatos(txtServidor.Text, txtBaseDatos.Text, txtInicioSesion.Text, txtContraseña.Text);
                    bool valor = db.TestConection();
                    if (valor == true)
                    {
                        lblMensaje.Visible = true;
                        lblMensaje.Text = "Conexión Exitosa";
                        lblMensaje.ForeColor = Color.Green;
                        btnCaptura.Visible = true;
                        btnConsulta.Visible = true;
                        txtBaseDatos.Enabled = false;
                        txtContraseña.Enabled = false;
                        txtInicioSesion.Enabled = false;
                        txtServidor.Enabled = false;
                    }
                    else
                    {
                        string text = "Login failed for user '" + txtInicioSesion + "' ";
                        throw new Exception(text);
                    }
                }
                catch (Exception ex)
                {
                    lblMensaje.Visible = true;
                    lblMensaje.Text = "Conexión Fallida";
                    lblMensaje.ForeColor = Color.Red;
                    btnConsulta.Visible = false;
                    btnCaptura.Visible = false;
                }
            }

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            txtServidor.Text= "DESKTOP-VJLGCHD";
            txtBaseDatos.Text="VentasProyectoBD";
            txtInicioSesion.Text="sa";
            txtContraseña.Text="123";
            btnCaptura.Visible = false;
            btnConsulta.Visible = false;
            lblMensaje.Visible = false;
        }

        private void btnCaptura_Click(object sender, EventArgs e)
        {            
            db = new BaseDeDatos(txtServidor.Text, txtBaseDatos.Text, txtInicioSesion.Text, txtContraseña.Text);
               
            try
            {
                Captura a = new Captura(txtServidor.Text, txtBaseDatos.Text, txtInicioSesion.Text, txtContraseña.Text);
                a.ShowDialog();
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "No tienes permiso para insertar";
                lblMensaje.ForeColor = Color.Red;
            }            
        }

        private void btnConsulta_Click(object sender, EventArgs e)
        {
            Consulta a = new Consulta(txtServidor.Text, txtBaseDatos.Text, txtInicioSesion.Text, txtContraseña.Text);
            a.ShowDialog();
                 
          
        }

        private bool Validar()
        {
            bool ok = true;
            //comienza a validar espacios en blanco

            if (String.IsNullOrEmpty(txtServidor.Text))
            {
                errorProvider1.SetError(txtServidor, "Campo Vacio");
                ok = false;
            }
            if (String.IsNullOrEmpty(txtBaseDatos.Text))
            {
                errorProvider1.SetError(txtBaseDatos, "Campo Vacio");
                ok = false;
            }
            if (String.IsNullOrEmpty(txtInicioSesion.Text))
            {
                errorProvider1.SetError(txtInicioSesion, "Campo Vacio");
                ok = false;
            }
            if (String.IsNullOrEmpty(txtContraseña.Text))
            {
                errorProvider1.SetError(txtContraseña, "Campo Vacio");
                ok = false;
            }
            return ok;
        }

        private void BorrarError()
        {
            errorProvider1.SetError(txtServidor, "");
            errorProvider1.SetError(txtBaseDatos, "");
            errorProvider1.SetError(txtInicioSesion, "");
            errorProvider1.SetError(txtContraseña, "");
        }

        private void txtContraseña_TextChanged(object sender, EventArgs e)
        {

        }
    }

}

