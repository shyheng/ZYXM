package com.shy.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.swetake.util.Qrcode;


import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.HashMap;

public class QR extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QR frame = new QR();
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
    public QR() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u56FE\u6807\\ico_love.png"));
        setTitle("\u5236\u4F5C\u81EA\u5DF1\u4E8C\u7EF4\u7801");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 954, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel text = new JLabel("\u8F93\u5165\u5185\u5BB9(\u6587\u5B57)");
        text.setFont(new Font("黑体", Font.PLAIN, 25));

        JEditorPane con_et = new JEditorPane();

        JLabel img_zx = new JLabel();
        JLabel img_qr = new JLabel();

        JButton zxing = new JButton("zxing");
        zxing.setFont(new Font("宋体", Font.PLAIN, 30));
        zxing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = 150;
                int height = 150;
                String format = "png";
                String content = con_et.getText();

                if (con_et.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请填写信息");
                } else {
                    //        定义二维码的参数
                    HashMap hints = new HashMap();
                    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//定义字符集
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级
                    hints.put(EncodeHintType.MARGIN, 2);

//        生成二维码
                    try {
                        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                        Path file = new File("E:\\ZYXM_img\\1.png").toPath();
                        MatrixToImageWriter.writeToPath(bitMatrix, format, file);

                    } catch (WriterException | IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        Icon icon = new ImageIcon(ImageIO.read(new File("E:\\ZYXM_img\\1.png")));
                        img_zx.setIcon(icon);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }


            }
        });

        JButton qr = new JButton("QR");
        qr.setFont(new Font("宋体", Font.PLAIN, 30));
        qr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (con_et.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请填写信息");
                } else {
                    Qrcode x = new Qrcode();
                    x.setQrcodeErrorCorrect('M');//纠错等级
                    x.setQrcodeEncodeMode('B');//N数字，A字母
                    x.setQrcodeVersion(7);//版本
                    String qrData = con_et.getText();
                    int width = 67 + 12 * (7 - 1);
                    int height = 67 + 12 * (7 - 1);

                    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                    Graphics2D gs = bufferedImage.createGraphics();
                    gs.setBackground(Color.WHITE);
                    gs.setColor(Color.BLACK);
                    gs.clearRect(0, 0, width, height);

                    int pixoff = 2;

                    byte[] d = new byte[0];
                    try {
                        d = qrData.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }
                    if (d.length > 0 && d.length < 120) {
                        boolean[][] s = x.calQrcode(d);

                        for (int i = 0; i < s.length; i++) {
                            for (int j = 0; j < s.length; j++) {
                                if (s[j][i]) {
                                    gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                                }
                            }
                        }
                    }
                    gs.dispose();
                    bufferedImage.flush();

                    try {
                        ImageIO.write(bufferedImage, "png", new File("E:\\ZYXM_img\\2.png"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    Icon icon = null;
                    try {
                        icon = new ImageIcon(ImageIO.read(new File("E:\\ZYXM_img\\2.png")));
                        img_qr.setIcon(icon);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

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
                                .addGap(76)
                                .addComponent(text, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(con_et, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(79, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(67)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(img_zx, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(zxing, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(qr, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(img_qr, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                .addGap(167))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(304)
                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(355, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(40)
                                                .addComponent(text, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(32)
                                                .addComponent(con_et, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(zxing, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(qr, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(46)
                                                .addComponent(img_zx, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(35)
                                                .addComponent(img_qr, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(index, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addGap(20))
        );
        contentPane.setLayout(gl_contentPane);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {

            }
        });
    }
}
