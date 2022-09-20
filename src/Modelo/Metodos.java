/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergi
 */
public class Metodos extends Estudiante{
    private int id;
    Conexion cn;

    public Metodos() {
    }

    public Metodos(String carnet, String nombres, String apellidos, String direccion, String telefono, String genero, String email, String f_nacimiento) {
        super(carnet, nombres, apellidos, direccion, telefono, genero, email, f_nacimiento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void agregar(){
        try {
            PreparedStatement parametro;
            String query = "INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento)\n" +"VALUES (?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, Vista.frm_estudiante.txt_apellidos1.getText());
            parametro.setString(2, Vista.frm_estudiante.txt_nombres.getText());
            parametro.setString(3, Vista.frm_estudiante.txt_apellidos.getText());
            parametro.setString(4, Vista.frm_estudiante.txt_direccion.getText());
            parametro.setString(5, Vista.frm_estudiante.txt_telefono.getText());
            parametro.setString(6, Vista.frm_estudiante.txt_genero.getText());
            parametro.setString(7, Vista.frm_estudiante.txt_mail.getText());
            parametro.setString(8, Vista.frm_estudiante.txt_fn.getText());
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registros nuevos ingresados", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }
    public DefaultTableModel leer(){
         DefaultTableModel tabla = new DefaultTableModel();
         try{
             cn = new Conexion();
             cn.abrir_conexion();
             String query;
             query = "Select id_estudiantes as id,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento from estudiantes";
             ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
             String encabezado[] = {"id", "Nit", "Nombres", "Apellidos", "Direccion", "Telefono","Genero","Email", "F_Nacimiento"};
             tabla.setColumnIdentifiers(encabezado);
             
             String datos[] = new String[9];
             while (consulta.next()){
                 datos [0] = consulta.getString("id");
                 datos [1] = consulta.getString("carnet");
                 datos [2] = consulta.getString("nombres");
                 datos [3] = consulta.getString("apellidos");
                 datos [4] = consulta.getString("direccion");
                 datos [5] = consulta.getString("telefono");
                 datos [6] = consulta.getString("genero");
                 datos [7] = consulta.getString("email");
                 datos [8] = consulta.getString("fecha_nacimiento");
                 tabla.addRow(datos);
             }
             cn.cerrar_conexion();
             
         }catch (SQLException ex){
             cn.cerrar_conexion();
             System.out.println("Error..." + ex.getMessage());
         }
         return tabla;
         
    }
    public void actualizar(){
        try {
            PreparedStatement parametro;
            String query = "update estudiantes set carnet= ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,genero=?,email=?,fecha_nacimiento= ? "+"where id_estudiantes = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, Vista.frm_estudiante.txt_apellidos1.getText());
            parametro.setString(2, Vista.frm_estudiante.txt_nombres.getText());
            parametro.setString(3, Vista.frm_estudiante.txt_apellidos.getText());
            parametro.setString(4, Vista.frm_estudiante.txt_direccion.getText());
            parametro.setString(5, Vista.frm_estudiante.txt_telefono.getText());
            parametro.setString(6, Vista.frm_estudiante.txt_genero.getText());
            parametro.setString(7, Vista.frm_estudiante.txt_mail.getText());
            parametro.setString(8, Vista.frm_estudiante.txt_fn.getText());
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registros actualizados", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
        
    }
    public void eliminar(){
        try {
            PreparedStatement parametro;
            String query = "delete from estudiantes where id_estudiantes = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, Vista.frm_estudiante.lbl_id1.getText());
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Eliminado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }
}
