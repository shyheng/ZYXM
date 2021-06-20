package com.shy.View;


import com.shy.JDBC.DBConnection;
import com.shy.JDBC.User;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField Login_username_et;
    private JPasswordField Login_password_et;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public static String name;
    public static String pass;
    public static String sex;
    public static String age;
    public static Integer id;

    public Login() {
        setTitle("登录");
        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\图标\\ico_love.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 786, 511);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel Login_username = new JLabel("用户名");
        Login_username.setFont(new Font("黑体", Font.PLAIN, 25));

        Login_username_et = new JTextField();
        Login_username_et.setColumns(10);

        JLabel Login_passwod = new JLabel("密码");
        Login_passwod.setFont(new Font("黑体", Font.PLAIN, 25));

        JButton Login_Index = new JButton("登录");
        Login_Index.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement ps = null;
                ResultSet resultSet = null;
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
                    String msg = null;
                    for (int i = 0; i < loginList.size(); i++) {
                        if (loginList.get(i).getUsername().equals(Login_username_et.getText())) {
                            if (loginList.get(i).getPassword().equals(Login_password_et.getText())) {
                                name = Login_username_et.getText();
                                pass = Login_password_et.getText();
                                sex = loginList.get(i).getSex();
                                age = loginList.get(i).getBirthday();
                                id = loginList.get(i).getId();
                                dispose();
                                new Index().setVisible(true);
                                return;
                            } else {

                                msg = "输入密码错误";
                                break;
                            }
                        } else {

                            msg = "输入账号错误";
                            continue;
                        }

                    }
                    JOptionPane.showMessageDialog(null, msg);


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        Login_Index.setFont(new Font("宋体", Font.PLAIN, 25));

        JButton Login_Reg = new JButton("注册");
        Login_Reg.addActionListener(new ActionListener() {
            //			注册按钮
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reg().setVisible(true);
            }
        });
        Login_Reg.setFont(new Font("宋体", Font.PLAIN, 25));

        JLabel Login_title = new JLabel("欢迎来到Shyheng");
        Login_title.setFont(new Font("楷体", Font.PLAIN, 35));

        Login_password_et = new JPasswordField();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(162)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(14)
                                                                .addComponent(Login_Index, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(45)
                                                                .addComponent(Login_Reg, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(Login_username, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Login_passwod, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(114)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(Login_password_et)
                                                                        .addComponent(Login_username_et, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(220)
                                                .addComponent(Login_title, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(200, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(60)
                                .addComponent(Login_title)
                                .addGap(72)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(Login_username, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Login_username_et, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                .addGap(43)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(Login_passwod, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Login_password_et, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(Login_Reg, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Login_Index, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                .addGap(53))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
