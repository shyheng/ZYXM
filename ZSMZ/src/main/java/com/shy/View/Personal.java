package com.shy.View;

import com.imooc.weather.HourWeather;
import com.imooc.weather.WeatherUtils;
import com.imooc.weather.impl.WeatherUtilsImpl;
import com.shy.JDBC.DBConnection;
import com.shy.JDBC.User;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personal extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Personal frame = new Personal();
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
    public Personal() {

        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
        setTitle("\u67E5\u770B\u4E2A\u4EBA\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 937, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton up = new JButton("\u66F4\u6539\u4E2A\u4FE1\u606F");
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UpPersonal().setVisible(true);
            }
        });
        up.setFont(new Font("宋体", Font.PLAIN, 30));

        JButton del = new JButton("\u6CE8\u9500\u8D26\u53F7");
        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DeletePersonal().setVisible(true);
            }
        });
        del.setFont(new Font("宋体", Font.PLAIN, 30));

        JButton index = new JButton("\u56DE\u5230\u4E3B\u9875");
        index.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Index().setVisible(true);
            }
        });
        index.setFont(new Font("宋体", Font.PLAIN, 30));

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(
                getData(),
                new String[]{"您的ID", "用户名", "性别", "年龄"}
        ));
        scrollPane.setViewportView(table);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(63)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(table, GroupLayout.PREFERRED_SIZE, 814, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(up, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                .addGap(107)
                                                .addComponent(del, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                .addGap(40))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(142)
                                .addComponent(table, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(up, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(del, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(index, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                .addGap(74))
        );
        contentPane.setLayout(gl_contentPane);
    }

    public Object[][] getData() {

        //调用连接数库查询全部数据的方法
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        Object[][] data = new Object[0][];
        String sql = "select * from user";
        try {
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
                users.add(user);
            }
            
            for (int i = 0; i < users.size(); i++) {
                if (Login.name.equals(users.get(i).getUsername()) && Login.pass.equals(users.get(i).getPassword())) {
                    data = new Object[1][6];
                    //循环遍历list，给二维数组赋值

                    data[0][0] = users.get(i).getId();
                    data[0][1] = users.get(i).getUsername();
                    data[0][3] = users.get(i).getSex();
                    data[0][2] = users.get(i).getBirthday();

                    return data;
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return data;
    }
}
