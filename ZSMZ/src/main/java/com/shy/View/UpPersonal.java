package com.shy.View;


import com.shy.JDBC.DBConnection;
import com.shy.JDBC.User;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpPersonal extends JFrame {

	private JPanel contentPane;
	private JTextField up_username_tf;
	private JTextField up_name_tf;
	private JTextField up_data_tf;
	private JPasswordField up_password_tf;
	private JPasswordField up_passwordqr_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpPersonal frame = new UpPersonal();
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
	public UpPersonal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
		setTitle("\u66F4\u6539\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel up_data = new JLabel("\u751F\u65E5");
		up_data.setFont(new Font("黑体", Font.PLAIN, 25));

		JLabel up_sex = new JLabel("\u6027\u522B");
		up_sex.setFont(new Font("黑体", Font.PLAIN, 25));

		JLabel up_password = new JLabel("\u5BC6\u7801");
		up_password.setFont(new Font("黑体", Font.PLAIN, 25));

		JLabel up_username = new JLabel("\u7528\u6237\u540D");
		up_username.setFont(new Font("黑体", Font.PLAIN, 25));

		JLabel up_name = new JLabel("\u771F\u5B9E\u59D3\u540D");
		up_name.setFont(new Font("黑体", Font.PLAIN, 25));

		JLabel up_passwordqr = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		up_passwordqr.setFont(new Font("黑体", Font.PLAIN, 25));

		up_username_tf = new JTextField();
		up_username_tf.setColumns(10);

		up_name_tf = new JTextField();
		up_name_tf.setColumns(10);

		up_data_tf = new JTextField();
		up_data_tf.setColumns(10);

		up_password_tf = new JPasswordField();

		up_passwordqr_tf = new JPasswordField();

		JRadioButton up_sex_nan = new JRadioButton("\u7537");
		up_sex_nan.setFont(new Font("萍方粗", Font.PLAIN, 25));

		JRadioButton up_sex_nv = new JRadioButton("\u5973");
		up_sex_nv.setFont(new Font("萍方粗", Font.PLAIN, 25));

		JRadioButton up_sex_bm = new JRadioButton("\u4FDD\u5BC6");
		up_sex_bm.setFont(new Font("萍方粗", Font.PLAIN, 25));

		up_sex_bm.setSelected(true);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(up_sex_bm);
		buttonGroup.add(up_sex_nan);
		buttonGroup.add(up_sex_nv);

		JButton up_Login = new JButton("\u66F4\u6539\u5B8C\u6210");
		up_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection;
				PreparedStatement ps;
				ResultSet resultSet;
				List<User> loginList = new ArrayList<>();
				try {
					String sql = "select * from user";
					connection = DBConnection.getConnection();
					ps = connection.prepareStatement(sql);
					resultSet = ps.executeQuery();
					while (resultSet.next()) {
						User user = new User();
						user.setId(resultSet.getInt("id"));
						user.setUsername(resultSet.getString("username"));
						user.setPassword(resultSet.getString("password"));
						user.setSex(resultSet.getString("sex"));
						user.setBirthday(resultSet.getString("birthday"));
						loginList.add(user);
					}
					for (int i = 0; i < loginList.size(); i++) {
						if (loginList.get(i).getUsername().equals(up_username_tf.getText()) && !loginList.get(i).getUsername().equals(Login.name)) {
							JOptionPane.showMessageDialog(null, "账号已经存在，请重写");
							break;
						} else if (up_username_tf.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "请填写账号");
							break;
						} else {
							if (up_password_tf.getText().length() != 0) {
								if (up_password_tf.getText().equals(up_passwordqr_tf.getText())) {
									if (0 < up_data_tf.getText().length() && up_data_tf.getText().length() < 3) {
										String sex;
										if (up_sex_nan.getModel().isSelected()) {
											sex = "男";
										} else if (up_sex_nv.getModel().isSelected()) {
											sex = "女";
										} else {
											sex = "保密";
										}
										String sql1 = "update user set username=?,password=?,sex=?,birthday=? WHERE id = ?";

										Connection connection1 = null;
										PreparedStatement ps1 = null;
										try {
											connection1 = DBConnection.getConnection();
											ps1 = connection1.prepareStatement(sql1);
											ps1.setString(1, up_username_tf.getText());
											ps1.setString(2, up_password_tf.getText());
											ps1.setString(3, sex);
											ps1.setString(4, up_data_tf.getText());
											ps1.setInt(5, Login.id);
											int i1 = ps1.executeUpdate();
											if (i1 > 0) {
												JOptionPane.showMessageDialog(null, "更改成功");
												dispose();
												new Personal().setVisible(true);
												return;
											} else {
												JOptionPane.showMessageDialog(null, "更改失败，请重试");
												break;
											}
										} catch (SQLException throwables) {
											throwables.printStackTrace();
										} finally {
											if (connection1 != null) {
												try {
													connection.close();
												} catch (SQLException throwables) {
													throwables.printStackTrace();
												}
											}
											if (ps1 != null) {
												try {
													ps.close();
												} catch (SQLException throwables) {
													throwables.printStackTrace();
												}
											}
										}
									} else {
										JOptionPane.showMessageDialog(null, "年龄长度有误");
										break;
									}
								} else {
									JOptionPane.showMessageDialog(null, "两个密码不一样，请重写");
									break;
								}
							}else {
								JOptionPane.showMessageDialog(null, "请填写密码");
								break;
							}
						}
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		up_Login.setFont(new Font("隶书", Font.PLAIN, 30));

		JButton up_Login_1 = new JButton("\u9000\u51FA");
		up_Login_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Personal().setVisible(true);
			}
		});
		up_Login_1.setFont(new Font("隶书", Font.PLAIN, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(143)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_username, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
																.addGap(104)
																.addComponent(up_username_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
																.addGap(79)
																.addComponent(up_name_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_password, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
																.addGap(104)
																.addComponent(up_password_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_passwordqr, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
																.addGap(79)
																.addComponent(up_passwordqr_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_sex, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
																.addGap(104)
																.addComponent(up_sex_nan, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
																.addGap(2)
																.addComponent(up_sex_nv, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
																.addGap(2)
																.addComponent(up_sex_bm, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(up_data, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
																.addGap(104)
																.addComponent(up_data_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(182)
												.addComponent(up_Login, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
												.addGap(49)
												.addComponent(up_Login_1, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(61)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_username, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(up_username_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addGap(5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_name, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(1)
												.addComponent(up_name_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
								.addGap(4)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_password, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(2)
												.addComponent(up_password_tf, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
								.addGap(5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_passwordqr, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(1)
												.addComponent(up_passwordqr_tf, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
								.addGap(6)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_sex, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(3)
												.addComponent(up_sex_nan, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(3)
												.addComponent(up_sex_nv, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(3)
												.addComponent(up_sex_bm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_data, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(5)
												.addComponent(up_data_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
								.addGap(35)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(up_Login, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(up_Login_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
