import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.sql.*;

public class SignUp extends JFrame implements ActionListener{
    
    
    JButton sign = new JButton("Create new Account");
    JLabel l1 = new JLabel("Create your User id : ");
    JLabel l2 = new JLabel("Create your Password : ");
    
    String uid = "root",pass = "rushikesh@2703",url = "jdbc:mysql://localhost:3306/Omkar";;
    String username ;
    String password ;
    JTextField t1,t2;
    Connection con;
    public SignUp()
    {
        t1 = new JTextField(10);
        t2 = new JTextField(10);
        setVisible(true);
        setSize(430,200);
        setLayout(null);
        setTitle("Sign Up Page");
        l1.setBounds(50,30,120,30);
        l2.setBounds(50,60,150,30);
        t1.setBounds(200,30,170,25);
        t2.setBounds(200,60,170,25);
        sign.setBounds(50,100,320,30);
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(sign);
        sign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                username = t1.getText();
                password = t2.getText();
                connectToDB(uid, pass, url);
                String insert = "Insert into loginInfo values (?,?);";
                PreparedStatement st = con.prepareStatement(insert);
                st.setString(1,username);
                st.setString(2,password);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Account Created SuccessFull...!");

                con.close();
                }catch(Exception e){System.out.println(e);}
            }
        }); 
    }
    void connectToDB(String uid, String pass , String url) 
    {
        try{
            con = DriverManager.getConnection(url, uid, pass);
            System.out.println("Cnnected...!");

        }catch(Exception e){System.out.println(e);}
    }
   
}
