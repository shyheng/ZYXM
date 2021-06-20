package com.shy.View;

import com.shy.JDBC.DBConnection;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePersonal extends JFrame {

	private JPanel contentPane;
	private JPasswordField password_et;
	private JButton out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePersonal frame = new DeletePersonal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeletePersonal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
		setTitle("\u6CE8\u9500\u8D26\u53F7");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel password = new JLabel("\u5BC6\u7801");
		password.setFont(new Font("黑体", Font.PLAIN, 25));
		
		password_et = new JPasswordField();
		
		JButton ok = new JButton("\u5B8C\u6210\u6CE8\u9500");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (password_et.getText().equals(Login.pass)){
					Connection connection = null;
					PreparedStatement ps = null;
					String sql ="delete from user where id = ?";
					try {
						connection = DBConnection.getConnection();
						ps = connection.prepareStatement(sql);
						ps.setInt(1,Login.id);
						ps.executeUpdate();

						dispose();
						JOptionPane.showMessageDialog(null,"删除成功，谢谢您的使用");
						new Login().setVisible(true);

					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (SQLException throwables) {
								throwables.printStackTrace();
							}
						}
						if (ps != null) {
							try {
								ps.close();
							} catch (SQLException throwables) {
								throwables.printStackTrace();
							}
						}
					}
				}

			}
		});
		ok.setFont(new Font("萍方粗", Font.PLAIN, 30));
		
		out = new JButton("\u9000\u51FA");
		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Personal().setVisible(true);
			}
		});
		out.setFont(new Font("萍方粗", Font.PLAIN, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(165)
							.addComponent(password, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(104)
							.addComponent(password_et, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(ok, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(out, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(password_et, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ok, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(out, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(64))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
