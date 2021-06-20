package com.shy.View;

import com.shy.mail.Auth;

import java.awt.EventQueue;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class Opinion extends JFrame {

	private JPanel contentPane;
	private JTextField title_et;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opinion frame = new Opinion();
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
	public Opinion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
		setTitle("\u610F\u89C1\u53CD\u9988");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("\u6807\u9898");
		title.setFont(new Font("黑体", Font.PLAIN, 25));
		
		JLabel con = new JLabel("\u5185\u5BB9");
		con.setFont(new Font("黑体", Font.PLAIN, 25));
		
		title_et = new JTextField();
		title_et.setColumns(10);
		
		JEditorPane con_et = new JEditorPane();
		
		JButton ok = new JButton("\u610F\u89C1\u63D0\u4EA4");
		ok.setFont(new Font("宋体", Font.PLAIN, 25));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Properties properties = new Properties();
				properties.setProperty("mail.home","smtp.aliyun.com");
				properties.setProperty("mail.transport.protocol","smtp");
				properties.setProperty("mail.smtp.auth","true");
				Auth auth = new Auth();
				Session session = Session.getDefaultInstance(properties,auth);
				Transport ts = null;
				try {
					ts = session.getTransport();
					ts.connect("smtp.aliyun.com","shy2210278285@aliyun.com","shy011115");
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress("shy2210278285@aliyun.com"));
					/*单发*/
					message.setRecipient(Message.RecipientType.TO, new InternetAddress("2787075649@qq.com"));
					message.setSubject(title.getText());
					message.setContent(con.getText(),"text/html;charset=utf-8");
					ts.sendMessage(message,message.getAllRecipients());
					ts.close();
				} catch (Exception Exception) {
					Exception.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null,"提交成功");
			}
		});
		
		JButton index = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		index.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Index().setVisible(true);
			}
		});
		index.setFont(new Font("宋体", Font.PLAIN, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(148)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(con, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(con_et, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(90)
							.addComponent(title_et, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(159)
					.addComponent(ok, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
					.addComponent(index, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(133))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(title_et, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(con_et, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(con, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ok, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(index, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
