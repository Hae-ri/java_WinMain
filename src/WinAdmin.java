import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class WinAdmin extends JDialog {
	private JTable table;
	private JTable table_1;

	
	/**
	 * Create the dialog.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public WinAdmin() throws ClassNotFoundException, SQLException {
		setTitle("Administrator display");
		setBounds(100, 100, 538, 430);
			
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
			
				//���̺� ����
				Vector<String> columnNames = new Vector<>();
				columnNames.add("���̵�");
				columnNames.add("��ȣ");
				columnNames.add("�̸�");
				columnNames.add("�ּ�");
				columnNames.add("��ȭ��ȣ");
				columnNames.add("��������");
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=1234");
				
				String sql = "select * from membertbl";
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				Vector records = new Vector<>();
				while(rs.next()) {
					Vector cols = new Vector<>();				
						cols.add(rs.getString("id"));
						cols.add(rs.getString("password"));
						cols.add(rs.getString("name"));
						cols.add(rs.getString("address"));
						cols.add(rs.getString("tel"));
						cols.add(rs.getString("mdate"));							
					records.add(cols);
				}
				rs.close();
				stmt.close();
				con.close();
				
				DefaultTableModel dtm = new DefaultTableModel(records,columnNames);
				table = new JTable(dtm);		
				
				/*
				Vector data = null;
				// DB ����
				try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=1234");
		            System.out.println("DB ����...");
		            
		            String sql = "select * from membertbl";
						
		            Statement stmt = con.createStatement();
		            ResultSet rs = stmt.executeQuery(sql);
		            
		            data = new Vector<>();
		            while (rs.next()) {
		            	Vector row = new Vector<>();
		            	for(int i=0; i<6 ; i++) {
		    				row.add(rs.getString(i+1));
		    		}
		    			data.add(row);
		    		}
		         		       
		         } catch (ClassNotFoundException | SQLException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		         }    

				DefaultTableModel dtm = new DefaultTableModel(data,columnName);
				table = new JTable(dtm);		
				*/
				
				
				scrollPane.setViewportView(table);
			}
		}
	}

}
