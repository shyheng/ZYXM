package com.shy.View;



import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;

public class Index extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setTitle("\u4E3B\u754C\u9762");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1152, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel index_tile = new JLabel("\u6B22\u8FCE\u6765\u5230Shyheng");
		index_tile.setFont(new Font("隶书", Font.PLAIN, 40));

		JButton chat = new JButton("\u5E7F\u57DF\u7F51\u804A\u5929");
		chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket = null;
				try {
					socket = new Socket("127.0.0.1",5555);
					dispose();
					new Chat(socket).setVisible(true);
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(null,"服务器连接失败");

				}

			}
		});
		chat.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		JButton QR = new JButton("\u505A\u81EA\u5DF1\u7684\u4E8C\u7EF4\u7801");
		QR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new QR().setVisible(true);
			}
		});
		QR.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		JButton weather = new JButton("\u5B9E\u65F6\u5929\u6C14\u9884\u62A5");
		weather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Weather().setVisible(true);
			}
		});
		weather.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		JButton main = new JButton("\u610F\u89C1\u53CD\u9988");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Opinion().setVisible(true);
			}
		});
		main.setFont(new Font("宋体", Font.PLAIN, 30));

		JButton login = new JButton("\u9000\u51FA\u767B\u5F55");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		login.setFont(new Font("宋体", Font.PLAIN, 30));

		JButton personal = new JButton("\u7528\u6237\u4FE1\u606F");
		personal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Personal().setVisible(true);
			}
		});
		personal.setFont(new Font("宋体", Font.PLAIN, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(354)
								.addComponent(index_tile, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
								.addGap(58)
								.addComponent(personal, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(155, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(87)
								.addComponent(main, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 484, Short.MAX_VALUE)
								.addComponent(login, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addGap(171))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(19)
								.addComponent(chat, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGap(245)
								.addComponent(QR, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
								.addComponent(weather, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
								.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(17, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(personal, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
										.addComponent(index_tile, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGap(55)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(chat, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
										.addComponent(QR, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
										.addComponent(weather, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(main, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
										.addComponent(login, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addGap(42))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
