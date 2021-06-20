package com.shy.View;

import com.imooc.weather.DayWeather;
import com.imooc.weather.HourWeather;
import com.imooc.weather.WeatherUtils;
import com.imooc.weather.impl.WeatherUtilsImpl;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Weather extends JFrame {

    private JPanel contentPane;
    private JTextField con_et;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Weather frame = new Weather();
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
    public Weather() {
        setTitle("\u5929\u6C14\u67E5\u8BE2");
        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 990, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JRadioButton h24 = new JRadioButton("24\u5C0F\u65F6");
        h24.setFont(new Font("萍方粗", Font.PLAIN, 25));

        JRadioButton t3 = new JRadioButton("3\u5929");
        t3.setFont(new Font("萍方粗", Font.PLAIN, 25));

        JRadioButton t7 = new JRadioButton("7\u5929");
        t7.setFont(new Font("萍方粗", Font.PLAIN, 25));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(h24);
        buttonGroup.add(t3);
        buttonGroup.add(t7);

        h24.setSelected(true);


        JLabel text1 = new JLabel("\u67E5\u8BE2\u5B9E\u65F6\u5929\u6570");
        text1.setFont(new Font("黑体", Font.PLAIN, 25));

        JLabel text = new JLabel("\u8BF7\u8F93\u5165\u57CE\u5E02");
        text.setFont(new Font("黑体", Font.PLAIN, 25));

        con_et = new JTextField();
        con_et.setColumns(10);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 950, 250);
        contentPane.add(scrollPane);

        table = new JTable();


        JButton select = new JButton("\u70B9\u51FB\u67E5\u8BE2");
        select.setFont(new Font("隶书", Font.PLAIN, 30));
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (h24.getModel().isSelected()) {
                    WeatherUtils weatherUtils = new WeatherUtilsImpl();
                    List<HourWeather> weatherList = weatherUtils.w24h("e8e17d73c85f4019887d3faecfea4ada", con_et.getText());
                    if (weatherList.size() == 0) {
                        JOptionPane.showMessageDialog(null, "对不起，请检查您输入内容");
                    } else {
                        table.setModel(new DefaultTableModel(
                                get24Data(con_et.getText()),
                                new String[]{"月份", "日期", "小时", "风向", "天气", "温度"}
                        ));
                        scrollPane.setViewportView(table);

                    }
                } else if (t3.getModel().isSelected()) {
                    WeatherUtils weatherUtils = new WeatherUtilsImpl();
                    List<DayWeather> weatherList = weatherUtils.w3d("e8e17d73c85f4019887d3faecfea4ada", con_et.getText());
                    if (weatherList.size() == 0) {
                        JOptionPane.showMessageDialog(null, "对不起，请检查您输入内容");
                    } else {
                        table.setModel(new DefaultTableModel(
                                get3Day(con_et.getText()),
                                new String[]{"年份","月份", "日期", "早上气温", "早上天气","晚上气温","晚上天气"}
                        ));
                        scrollPane.setViewportView(table);

                    }
                } else if (t7.getModel().isSelected()) {
                    WeatherUtils weatherUtils = new WeatherUtilsImpl();
                    List<DayWeather> weatherList = weatherUtils.w7d("e8e17d73c85f4019887d3faecfea4ada", con_et.getText());
                    if (weatherList.size() == 0) {
                        JOptionPane.showMessageDialog(null, "对不起，请检查您输入内容");
                    } else {
                        table.setModel(new DefaultTableModel(
                                get7Day(con_et.getText()),
                                new String[]{"年份","月份", "日期", "早上气温", "早上天气","晚上气温","晚上天气"}
                        ));
                        scrollPane.setViewportView(table);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择");
                }

            }
        });

        JButton index = new JButton("\u56DE\u5230\u4E3B\u9875");
        index.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Index().setVisible(true);
            }
        });
        index.setFont(new Font("萍方粗", Font.PLAIN, 30));


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(336)
                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(359, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(33)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(text, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                                .addGap(54)
                                                .addComponent(con_et, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                                                .addGap(85)
                                                .addComponent(select, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(text1)
                                                .addGap(98)
                                                .addComponent(h24, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                                .addComponent(t3, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                                .addGap(99)
                                                .addComponent(t7, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                                .addGap(135))))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(23, Short.MAX_VALUE)
                                .addComponent(table, GroupLayout.PREFERRED_SIZE, 923, GroupLayout.PREFERRED_SIZE)
                                .addGap(20))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(54)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(text, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(14)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(select, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(con_et, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
                                .addGap(41)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(h24, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(text1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(t7, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                                .addGap(32)
                                .addComponent(table, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    public Object[][] get24Data(String c) {
        WeatherUtils weatherUtils = new WeatherUtilsImpl();
        //调用连接数库查询全部数据的方法
        List<HourWeather> weatherList = weatherUtils.w24h("e8e17d73c85f4019887d3faecfea4ada", c);
        Object[][] data = new Object[weatherList.size()][6];
        //循环遍历list，给二维数组赋值
        for (int i = 0; i < weatherList.size(); i++) {
            data[i][0] = weatherList.get(i).getMonth();
            data[i][1] = weatherList.get(i).getDay();
            data[i][2] = weatherList.get(i).getHour();
            data[i][3] = weatherList.get(i).getWindDirection();
            data[i][4] = weatherList.get(i).getWeather();
            data[i][5] = weatherList.get(i).getTemperature();
        }
        return data;
    }
    public Object[][] get3Day(String c) {
        WeatherUtils weatherUtils = new WeatherUtilsImpl();
        //调用连接数库查询全部数据的方法
        List<DayWeather> weatherList = weatherUtils.w3d("e8e17d73c85f4019887d3faecfea4ada", c);
        Object[][] data = new Object[weatherList.size()][7];
        //循环遍历list，给二维数组赋值
        for (int i = 0; i < weatherList.size(); i++) {
            data[i][0] = weatherList.get(i).getYear();
            data[i][1] = weatherList.get(i).getMonth();
            data[i][2] = weatherList.get(i).getDay();
            data[i][3] = weatherList.get(i).getDayAirTemperature();
            data[i][4] = weatherList.get(i).getDayWeather();
            data[i][5] = weatherList.get(i).getNightAirTemperature();
            data[i][6] = weatherList.get(i).getNightWeather();

        }
        return data;
    }
    public Object[][] get7Day(String c) {
        WeatherUtils weatherUtils = new WeatherUtilsImpl();
        //调用连接数库查询全部数据的方法
        List<DayWeather> weatherList = weatherUtils.w7d("e8e17d73c85f4019887d3faecfea4ada", c);
        Object[][] data = new Object[weatherList.size()][7];
        //循环遍历list，给二维数组赋值
        for (int i = 0; i < weatherList.size(); i++) {
            data[i][0] = weatherList.get(i).getYear();
            data[i][1] = weatherList.get(i).getMonth();
            data[i][2] = weatherList.get(i).getDay();
            data[i][3] = weatherList.get(i).getDayAirTemperature();
            data[i][4] = weatherList.get(i).getDayWeather();
            data[i][5] = weatherList.get(i).getNightAirTemperature();
            data[i][6] = weatherList.get(i).getNightWeather();

        }
        return data;
    }
}
