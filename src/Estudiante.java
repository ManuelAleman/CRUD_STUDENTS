import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Estudiante extends JFrame{
    Connection con;
    PreparedStatement ps;
    DefaultListModel model = new DefaultListModel();
    Statement st;
    ResultSet rs;


    private JPanel pnl;
    private JTextField idText;
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField edadText;
    private JTextField carreraText;
    private JTextField telefonoText;
    private JButton insertBt;
    private JButton showBt;
    private JList lista;
    private JButton deleteBt;
    private JTextField idDeleteTextTextField;


    public static void main(String[] args) {
        Estudiante f = new Estudiante();
        f.setContentPane(new Estudiante().pnl);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.pack();
    }


    public void showData() throws SQLException {
        connect();
        lista.setModel(model);
        st = con.createStatement();
        rs = st.executeQuery("SELECT id, nombre, apellido FROM Estudiante");
        model.removeAllElements();

        while ( rs.next() ) {
            model.addElement(rs.getString(1) + " "
                    + rs.getString(2)+ " "
                    + rs.getString(3))
            ;
        }

    }


    public void insertData() throws SQLException {
        connect();
        ps = con.prepareStatement("INSERT INTO Estudiante VALUES (?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(idText.getText()));
        ps.setString(2, nombreText.getText());
        ps.setString(3, apellidoText.getText());
        ps.setInt(4, Integer.parseInt(edadText.getText()));
        ps.setString(5, telefonoText.getText());
        ps.setString(6, carreraText.getText());

        if ( ps.executeUpdate() > 0 ) {
            lista.setModel(model);
            model.removeAllElements();
            model.addElement("Succesfully inserted");

            idText.setText("");
            nombreText.setText("");
            apellidoText.setText("");
            edadText.setText("");
            telefonoText.setText("");
            carreraText.setText("");
            return;
        }
        model.addElement("Failed to insert");

    }

    public void deleteData() throws SQLException {
        connect();
        ps = con.prepareStatement("DELETE FROM Estudiante WHERE id = ?");
        ps.setInt(1, Integer.parseInt(idDeleteTextTextField.getText()));

        if ( ps.executeUpdate() > 0 ) {
            lista.setModel(model);
            model.removeAllElements();
            model.addElement("Succesfully deleted");

            idDeleteTextTextField.setText("");
            return;
        }
        model.addElement("Failed to delete");
    }

    public Estudiante() {
        showBt.addActionListener(e -> {
            try {
                showData();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        insertBt.addActionListener(e -> {
            try {
                insertData();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteBt.addActionListener( e -> {
            try {
                deleteData();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learning", "root", "4CM13v1y");
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar", e);
        }
    }

}
