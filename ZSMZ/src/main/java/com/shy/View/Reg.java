package com.shy.View;


import com.shy.JDBC.DBConnection;
import com.shy.JDBC.User;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reg extends JFrame {

    private JPanel contentPane;
    private JTextField Reg_username_tf;
    private JTextField Reg_name_tf;
    private JTextField Reg_data_tf;
    private JPasswordField Reg_password_tf;
    private JPasswordField Reg_passwordqr_tf;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reg frame = new Reg();
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
    public Reg() {
        setTitle("\u6CE8\u518C");
        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 743, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel Reg_username = new JLabel("\u7528\u6237\u540D");
        Reg_username.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel Reg_name = new JLabel("\u771F\u5B9E\u59D3\u540D");
        Reg_name.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel Reg_password = new JLabel("\u5BC6\u7801");
        Reg_password.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel Reg_passwordqr = new JLabel("\u786E\u8BA4\u5BC6\u7801");
        Reg_passwordqr.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel Reg_sex = new JLabel("\u6027\u522B");
        Reg_sex.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel Reg_data = new JLabel("年龄");
        Reg_data.setFont(new Font("黑体", Font.PLAIN, 25));

        Reg_username_tf = new JTextField();
        Reg_username_tf.setColumns(10);

        Reg_name_tf = new JTextField();
        Reg_name_tf.setColumns(10);

        Reg_data_tf = new JTextField();
        Reg_data_tf.setColumns(10);

        Reg_password_tf = new JPasswordField();

        Reg_passwordqr_tf = new JPasswordField();

        JRadioButton Reg_sex_nan = new JRadioButton("\u7537");
        Reg_sex_nan.setFont(new Font("萍方粗", Font.PLAIN, 25));

        JRadioButton Reg_sex_nv = new JRadioButton("\u5973");
        Reg_sex_nv.setFont(new Font("萍方粗", Font.PLAIN, 25));

        JRadioButton Reg_sex_bm = new JRadioButton("\u4FDD\u5BC6");
        Reg_sex_bm.setFont(new Font("萍方粗", Font.PLAIN, 25));

        Reg_sex_bm.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(Reg_sex_bm);
        buttonGroup.add(Reg_sex_nan);
        buttonGroup.add(Reg_sex_nv);

        JButton Reg_Login = new JButton("\u6CE8\u518C\u5B8C\u6210");
        Reg_Login.addActionListener(new ActionListener() {
            //			注册按钮
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
                        if (loginList.get(i).getUsername().equals(Reg_username_tf.getText())) {
                            JOptionPane.showMessageDialog(null, "账号已经存在，请重写");
                            break;
                        } else if (Reg_username_tf.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "请填写账号");
                            break;
                        } else {
                            if (Reg_password_tf.getText().length() != 0) {
                                if (Reg_password_tf.getText().equals(Reg_passwordqr_tf.getText())) {
                                    if (0 < Reg_data_tf.getText().length() && Reg_data_tf.getText().length() < 3) {
                                        String sex;
                                        if (Reg_sex_nan.getModel().isSelected()) {
                                            sex = "男";
                                        } else if (Reg_sex_nv.getModel().isSelected()) {
                                            sex = "女";
                                        } else {
                                            sex = "保密";
                                        }
                                        String sql1 = "insert into user(username,password,sex,birthday)" + "values (?,?,?,?)";
                                        Connection connection1 = null;
                                        PreparedStatement ps1 = null;
                                        try {
                                            connection1 = DBConnection.getConnection();
                                            ps1 = connection1.prepareStatement(sql1);
                                            ps1.setString(1, Reg_username_tf.getText());
                                            ps1.setString(2, Reg_password_tf.getText());
                                            ps1.setString(3, sex);
                                            ps1.setString(4, Reg_data_tf.getText());
                                            int i1 = ps1.executeUpdate();
                                            if (i1 > 0) {
                                                JOptionPane.showMessageDialog(null, "注册成功");
                                                dispose();
                                                new Login().setVisible(true);
                                                return;
                                            } else {
                                                JOptionPane.showMessageDialog(null, "注册失败，请重试");
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
        Reg_Login.setFont(new Font("隶书", Font.PLAIN, 30));

        JButton Reg_index = new JButton("\u5DF2\u6709\u8D26\u53F7");
        Reg_index.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });
        Reg_index.setFont(new Font("隶书", Font.PLAIN, 30));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(143)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(Reg_data, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_sex, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_password, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_username, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_name, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                                        .addComponent(Reg_passwordqr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(79)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(Reg_username_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_name_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_data_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_password_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_passwordqr_tf, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(Reg_sex_nan, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(Reg_sex_nv, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(Reg_sex_bm))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(104)
                                                .addComponent(Reg_Login, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
                                                .addGap(76)
                                                .addComponent(Reg_index, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(93, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(64)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(Reg_username_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(Reg_name_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(Reg_password_tf, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(Reg_passwordqr_tf, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addGap(9)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(Reg_sex_nan, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_sex_nv, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Reg_sex_bm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addComponent(Reg_data_tf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(Reg_username, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(Reg_name, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(Reg_password, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(Reg_passwordqr, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(Reg_sex, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(Reg_data, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(Reg_Login, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Reg_index, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGap(29))
        );
        contentPane.setLayout(gl_contentPane);
    }
}