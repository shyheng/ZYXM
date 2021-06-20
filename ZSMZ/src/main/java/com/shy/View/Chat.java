package com.shy.View;


import com.shy.socket.ClientW;
import netscape.javascript.JSObject;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chat extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Socket socket = null;
                    Chat frame = new Chat(socket);
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
    List<String> stringsD = new ArrayList<>();
    List<String> stringsZ = new ArrayList<>();
    Socket socket;
    boolean flg = false;
    JList list_d;
    public Chat(Socket socket) {

        this.socket = socket;

        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\图标\\ico_love.png"));
        setTitle("聊天窗口");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 911, 561);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JList list_z = new JList();

        list_d = new JList();

        Thread threadShyR = new Thread(new ClientR(socket));
        threadShyR.start();

        JEditorPane ed_texr = new JEditorPane();

        JOptionPane.showMessageDialog(null,"点击确定，请等待连接成功");

        JButton send = new JButton("\u53D1\u9001");
        send.setFont(new Font("萍方粗", Font.PLAIN, 40));
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread threadShyW = new Thread(new ClientW(socket, ed_texr.getText()));
                threadShyW.start();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                stringsZ.add(time + ": " + ed_texr.getText());
                String[] strings = new String[stringsZ.size()];

                for (int i = 0; i < stringsZ.size(); i++) {
                    strings[i] = stringsZ.get(i);
                }

                list_z.setListData(strings);
            }
        });

        JButton index = new JButton("\u56DE\u5230\u4E3B\u9875");
        index.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"退出就断开连接");
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                dispose();
                new Index().setVisible(true);
            }
        });
        index.setFont(new Font("萍方粗", Font.PLAIN, 30));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(list_d, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(list_z, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                                .addGap(18))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(18)
                                .addComponent(ed_texr, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
                                .addGap(51)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(index, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(send, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(list_d, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(list_z, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                                .addComponent(ed_texr, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                .addGap(40))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(26)
                                                .addComponent(send, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {

            }
        });

    }


    class ClientR implements Runnable {
        Socket socket;

        public ClientR(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] bytes = new byte[1024];
            int len;
            while (true) {
                try {
                    len = inputStream.read(bytes);
                } catch (IOException e) {
                    return;
                }
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                try {
                    stringsD.add(time + ": " + new String(bytes, 0, len));
                }catch (StringIndexOutOfBoundsException s){
                    try {
                        socket.close();
                        JOptionPane.showMessageDialog(null,"对方已退出");
                        dispose();
                        new Index().setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;

                }
                String[] string_d = new String[stringsD.size()];

                for (int i = 0; i < stringsD.size(); i++) {
                    string_d[i] = stringsD.get(i);
                }
                list_d.setListData(string_d);
            }

        }
    }
}
