import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class WinLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinLogin dialog = new WinLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WinLogin() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Login");
		setBounds(100, 100, 422, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setBounds(46, 38, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Password");
			lblNewLabel_1.setBounds(46, 93, 71, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtID = new JTextField();
			txtID.setBounds(155, 35, 116, 21);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		{
			txtPW = new JTextField();
			txtPW.setBounds(155, 90, 116, 21);
			contentPanel.add(txtPW);
			txtPW.setColumns(10);
		}
		{
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=1234");
						Statement stmt = con.createStatement();
						String sql = "select * from memberTBL where id = '";
						sql = sql + txtID.getText() + "' and password = '";
						sql = sql + txtPW.getText() + "'";
						
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							if(rs.getString("id").equals("admin") && rs.getString("password").equals("1234")) { // 관리자
								WinAdmin dlg = new WinAdmin();
								dlg.setVisible(true);
								setVisible(false);
							}else{ // 일반회원
								WinMain dlg = new WinMain(txtID.getText());
								dlg.setVisible(true);
								setVisible(false);
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "접속 실패", "로그인", JOptionPane.ERROR_MESSAGE);
							txtID.setText("");
							txtPW.setText("");
							txtID.requestFocus();
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("DB connected...");         
					
				}
			});
			btnLogin.setBounds(286, 35, 97, 23);
			contentPanel.add(btnLogin);
		}
		{
			JButton btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785 ..");
			btnJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinMemberJoin dlg = new WinMemberJoin();
					dlg.setModal(true);
					dlg.setVisible(true);
				}
			});
			btnJoin.setBounds(286, 90, 97, 23);
			contentPanel.add(btnJoin);
		}
	}

}
