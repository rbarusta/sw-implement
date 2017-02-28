package com.company;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cristianricardobarustasanchez on 24/02/17.
 */
public class App {
    private JPanel panel1;
    private JButton btn_login;
    private JTextField txt_user;
    private JPasswordField txt_pwd;

    public App() {
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txt_user.getText().toString();
                String pwd = txt_pwd.getText().toString();
                SWAuthenticationService auth = new SWAuthenticationService(user, pwd,"http://services.test.sw.com.mx");
                try {
                    IResponse res = auth.Token();
                    JSONObject parser = new JSONObject(res.Data);
                    JOptionPane.showMessageDialog(null,parser.getString("token"));
                    txt_user.setText("");
                    txt_pwd.setText("");
                } catch (GenaralException e1) {
                    e1.printStackTrace();
                } catch (AuthException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
