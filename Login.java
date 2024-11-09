import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.*;
public class Login extends JFrame implements ActionListener{
    JButton login = new JButton("Login");
    JButton sign = new JButton("Sign up");
    JLabel l1 = new JLabel("Enter User id : ");
    JLabel l2 = new JLabel("Enter Password : ");
    String u1 = "jdbc:mysql://localhost:3306/Omkar";
    JTextField t1;
    JPasswordField t2;
    String u;
    Connection con;
    public Login()
    {
        t1 = new JTextField();
        t2 = new JPasswordField();
        setVisible(true);
        setSize(430,200);
        setLayout(null);
        setTitle("Login Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1.setBounds(50,30,100,30);
        l2.setBounds(50,60,150,30);
        t1.setBounds(200,30,170,25);
        t2.setBounds(200,60,170,25);
        login.setBounds(50,100,140,30);
        sign.setBounds(200,100,170,30);
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(login);
        add(sign); 
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                u = t1.getText();
                String pa = t2.getText();
                connectToDB("root", "rushikesh@2703", u1);
                String p = "Select * from loginInfo where uid = ? And pass = ? ;";
                PreparedStatement st = con.prepareStatement(p);
                st.setString(1, u);
                st.setString(2, pa);
                ResultSet rs = st.executeQuery();
                
                if(rs.next())
                {
                    JOptionPane.showMessageDialog(null, "Login SuccessFull...!");
                }else
                {
                    JOptionPane.showMessageDialog(null, "Login Failed...!");
                }
                
                }catch(Exception e){System.out.println(e);}
            }
        });
        sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
                new SignUp();
            }
        }); 
    }
    void connectToDB(String uid, String pass , String url) 
    {
        try{
            con = DriverManager.getConnection(url, uid, pass);
        }catch(Exception e){System.out.println(e);}
    }
    public static void main(String[] args) throws Exception {
        Login l = new Login();
        l.connectToDB("root", "rushikesh@2703",l.u1);
    }
}