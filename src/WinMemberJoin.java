import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinMemberJoin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtPW;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtTel;
	Connection con;
	
	public void DB_Connection() throws ClassNotFoundException, SQLException {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=1234");
			System.out.println("DB connected...");               
	}
	
	public void DB_Close() throws SQLException {
		con.close();
	}

	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public WinMemberJoin() {
		initGUI();
		
		try {
			DB_Connection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private void initGUI() {
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setBounds(100, 100, 446, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setBounds(50, 30, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			txtID = new JTextField();
			txtID.setBounds(165, 30, 116, 21);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		{
			JLabel Password = new JLabel("Password");
			Password.setBounds(50, 60, 78, 15);
			contentPanel.add(Password);
		}
		{
			txtPW = new JTextField();
			txtPW.setColumns(10);
			txtPW.setBounds(165, 60, 116, 21);
			contentPanel.add(txtPW);
		}
		{
			JLabel lblNewLabel = new JLabel("\uC774\uB984");
			lblNewLabel.setBounds(50, 90, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(165, 90, 116, 21);
			contentPanel.add(txtName);
		}
		{
			JLabel lblNewLabel = new JLabel("\uC8FC\uC18C");
			lblNewLabel.setBounds(50, 120, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			txtAddress = new JTextField();
			txtAddress.setColumns(10);
			txtAddress.setBounds(165, 120, 116, 21);
			contentPanel.add(txtAddress);
		}
		{
			JLabel lblNewLabel = new JLabel("\uC804\uD654\uBC88\uD638");
			lblNewLabel.setBounds(50, 150, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			txtTel = new JTextField();
			txtTel.setColumns(10);
			txtTel.setBounds(165, 150, 116, 21);
			contentPanel.add(txtTel);
		}
		{
			JButton btnDupCheck = new JButton("\uC911\uBCF5\uD655\uC778");
			btnDupCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = con.createStatement();
						String sql = "select * from memberTBL where id = '";
						sql = sql + txtID.getText() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						
						if(rs.next()) { // 중복된 아이디가 있으면
							JOptionPane.showMessageDialog(null, "아이디 중복","중복확인",JOptionPane.ERROR_MESSAGE);
							txtID.setText("");
							txtID.requestFocus();
						}else {
							JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnDupCheck.setBounds(311, 30, 97, 23);
			contentPanel.add(btnDupCheck);
		}
		{
			JButton btnInsert = new JButton("\uAC00\uC785\uD558\uAE30");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = con.createStatement();
						
						String m_id = txtID.getText();
			            String m_pw = txtPW.getText();
			            String m_name = txtName.getText();
			            String m_address = txtAddress.getText();
			            String m_tel = txtTel.getText();
			            
						
						String sql = "insert into membertbl values('"+m_id+"','" +m_pw+ "',' " +m_name+ "',' " +m_address+ "','" +m_tel+ "',CURDATE())";
				        //System.out.println(sql);    
				        
				        if(stmt.executeUpdate(sql)>0)
				        	System.out.println("가입 성공");
				        stmt.close();
				        
					}catch (SQLException e1) {
			              e1.printStackTrace();
			           } 
				}
			});

			btnInsert.setBounds(99, 200, 242, 23);
			contentPanel.add(btnInsert);
		}
	}

}
