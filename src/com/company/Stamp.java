package com.company;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Services.Stamp.SWStampService;
import Utils.Responses.IResponse;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by cristianricardobarustasanchez on 24/02/17.
 */
public class Stamp {
    private JButton btn_stamp;
    private JPanel panelStamp;
    JFileChooser fs = new JFileChooser();
    String xml = "";


    public Stamp() {
        btn_stamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fs.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    java.io.File file = fs.getSelectedFile();

                    try {
                        Scanner input = new Scanner(file);
                        while (input.hasNext()){
                            xml+=input.nextLine();
                        }

                        SWStampService stamp = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
                        IResponse res = stamp.Stamp(xml,"v1");
                        JSONObject parser = new JSONObject(res.Data);
                        JOptionPane.showMessageDialog(null,parser.getString("tfd"));
                        xml = "";
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (GenaralException e1) {
                        e1.printStackTrace();
                        xml = "";
                    } catch (AuthException e1) {
                        e1.printStackTrace();
                        xml = "";
                    }
                }
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Stamp");
        frame.setContentPane(new Stamp().panelStamp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
