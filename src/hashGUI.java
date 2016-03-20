
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.lang.*;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;

public class hashGUI {

    public JFrame frame;
    public JLabel label;
    public static JLabel message;
    private JTextField textField;
    private JTextField DtextField;
    private JTextField textField_2;
    private JTextField DtextField_2;
    private JFileChooser fileChooser = new JFileChooser();
    private File fi;
    int returnVal;
    String ipp = "";

    public hashGUI() {
        initialize();
    }

    //Initialize the contents of the frame.
    private void initialize() {
        //Creating the Window
        frame = new JFrame("#Sqaure - Crypto Program");
        //Setting Size and position
        frame.setBounds(40, 5, 1300, 660);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //Setting BG color
        frame.getContentPane().setBackground(Color.decode("#ecf0f1"));

        //Setting Up Label
        JLabel lblProjectHashsquare = new JLabel("Project #Square");
        //Setting fonts and size
        lblProjectHashsquare.setFont(new Font("Arial", Font.BOLD, 26));
        //Setting text color
        lblProjectHashsquare.setForeground(Color.decode("#34495e"));
        //Setting position
        lblProjectHashsquare.setBounds(560, 11, 232, 50);
        //Adding frame to the window
        frame.getContentPane().add(lblProjectHashsquare);

        //Setting Up Label
        JLabel encrypt = new JLabel("Encryption");
        //Setting fonts and size
        encrypt.setFont(new Font("Arial", Font.BOLD, 20));
        //Setting text color
        encrypt.setForeground(Color.decode("#c0392b"));
        //Setting position
        encrypt.setBounds(270, 50, 232, 50);
        //Adding frame to the window
        frame.getContentPane().add(encrypt);

        //Setting Up Label
        JLabel decrypt = new JLabel("Decryption");
        //Setting fonts and size
        decrypt.setFont(new Font("Arial", Font.BOLD, 20));
        //Setting text color
        decrypt.setForeground(Color.decode("#c0392b"));
        //Setting position
        decrypt.setBounds(950, 50, 232, 50);
        //Adding frame to the window
        frame.getContentPane().add(decrypt);

        //Setting Up Message Label
        message = new JLabel("");
        message.setForeground(Color.decode("#c0392b"));
        //Text Font
        message.setFont(new Font("Arial", Font.BOLD, 20));
        //Setting Position
        message.setBounds(530, 450, 500, 50);
        //Adding Label to the window
        frame.getContentPane().add(message);

        //Setting Up Label
        JLabel lblKey = new JLabel("Enter KEY [ Max Len:5 ] :");
        //Text color
        lblKey.setForeground(Color.decode("#34495e"));
        //Text Font
        lblKey.setFont(new Font("Arial", Font.BOLD, 16));
        //Setting Position
        lblKey.setBounds(30, 110, 200, 50);
        //Adding Label to the window
        frame.getContentPane().add(lblKey);

        //Setting Up Label
        JLabel lblEnterTextTo = new JLabel("Choose file to be  Encrypted :");
        //Text color
        lblEnterTextTo.setForeground(Color.decode("#34495e"));
        //Text font
        lblEnterTextTo.setFont(new Font("Arial", Font.BOLD, 16));
        //Setting Position
        lblEnterTextTo.setBounds(30, 160, 300, 50);
        //Adding Label to the window
        frame.getContentPane().add(lblEnterTextTo);

        //Setting Up Button
        JButton btnEncrpyt = new JButton("Encrypt");
        //Button Color
        btnEncrpyt.setForeground(Color.decode("#ecf0f1"));
        btnEncrpyt.setBackground(Color.decode("#c0392b"));
        //Adding Listner
        btnEncrpyt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = textField.getText();
                if ("".equals(key) && "".equals(ipp)) {
                    message.setText("Encryption : Invalid Key and Input");
                } else if ("".equals(key) || key.length() > 5) {
                    message.setText("Encryption : Invalid Key");
                } else if ("".equals(ipp)) {
                    message.setText("Encryption : No Input");
                } else {
                    hashEncrypt hs = new hashEncrypt(ipp, key);
                    hs.encryptKey();
                    try {
                        //Getting Path of the Source File
                        String absolutePath = fi.getAbsolutePath();
                        //Taking on the dir address
                        String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                        String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1, absolutePath.length() - 4);
                        File textFile = new File(filePath + "\\" + fileName + "_ept.txt");
                        FileWriter fw = new FileWriter(textFile.getAbsolutePath());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(hs.encrypt);
                        bw.close();
                        message.setText("Encryption Done");
                    } catch (IOException err) {
                        message.setText("Encryption : Error in File Writing");
                        err.printStackTrace();
                    }
                }
            }
        });
        //Setting Up Button
        btnEncrpyt.setFont(new Font("Arial", Font.BOLD, 16));
        //Button Position
        btnEncrpyt.setBounds(200, 310, 150, 50);
        //Adding Button to Window
        frame.getContentPane().add(btnEncrpyt);

        textField = new JTextField();
        textField.setFont(new Font("Serif", Font.ITALIC, 17));
        textField.setBounds(270, 120, 210, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Serif", Font.ITALIC, 14));
        textField_2.setBounds(270, 170, 210, 30);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JButton btnBrowse = new JButton("BROWSE");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    fi = fileChooser.getSelectedFile();
                    textField_2.setText(fileChooser.getSelectedFile().toString());
                    String currentLine;
                    try {
                        ipp = "";
                        BufferedReader br = new BufferedReader(new FileReader(fi));
                        while ((currentLine = br.readLine()) != null) {
                            ipp += currentLine;
                        }
                    } catch (IOException ex) {

                    }

                }
            }
        });
        //Button Font 
        btnBrowse.setFont(new Font("Arial", Font.BOLD, 16));
        //Button BG
        btnBrowse.setForeground(Color.decode("#34495e"));
        btnBrowse.setBackground(Color.decode("#ecf0f1"));
        //Setting Position
        btnBrowse.setBounds(270, 210, 210, 30);
        //Adding Button to the window
        frame.getContentPane().add(btnBrowse);

        //**********************************************************************
        //Decryption 
        //Setting Up Label
        JLabel lblKey_1 = new JLabel("Enter KEY [ Max Len:5 ] :");
        //Text color
        lblKey_1.setForeground(Color.decode("#34495e"));
        //Text Font
        lblKey_1.setFont(new Font("Arial", Font.BOLD, 16));
        //Setting Position
        lblKey_1.setBounds(710, 110, 200, 50);
        //Adding Label to the window
        frame.getContentPane().add(lblKey_1);

        //Setting Up Label
        JLabel decryptLabel = new JLabel("Choose file to be Decrypted :");
        //Text color
        decryptLabel.setForeground(Color.decode("#34495e"));
        //Text font
        decryptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        //Setting Position
        decryptLabel.setBounds(710, 160, 300, 50);
        //Adding Label to the window
        frame.getContentPane().add(decryptLabel);
        //Setting Up Button
        JButton btnDecrpyt = new JButton("Decrypt");
        //Button Color
        btnDecrpyt.setForeground(Color.decode("#ecf0f1"));
        btnDecrpyt.setBackground(Color.decode("#c0392b"));
        //Adding Listner
        btnDecrpyt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = DtextField.getText();
                if ("".equals(key) && "".equals(ipp)) {
                    message.setText("Decryption : Invalid Key and Input");
                } else if ("".equals(key) || key.length() > 5) {
                    message.setText("Decryption : Invalid Key");
                } else if ("".equals(ipp)) {
                    message.setText("Decryption : No Input");
                } else {
                    hashDecrypt d = new hashDecrypt(ipp, key);
                    d.decrypt();
                    if (hashDecrypt.validateBool) {
                        try {
                            //Getting Path of the Source File
                            String absolutePath = fi.getAbsolutePath();
                            //Taking on the dir address
                            String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                            String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1, absolutePath.length() - 4);
                            File textFile = new File(filePath + "\\" + fileName + "_dpt.txt");
                            FileWriter fw = new FileWriter(textFile.getAbsolutePath());
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(d.finalDecrypt);
                            bw.close();
                            message.setText("Decryption Done");
                        } catch (Exception err) {
                            message.setText("Decryption : Error in File Writing ");
                            err.printStackTrace();
                        }
                    }
                }
            }
        });
        //Setting Up Button
        btnDecrpyt.setFont(new Font("Arial", Font.BOLD, 16));
        //Button Position
        btnDecrpyt.setBounds(890, 310, 150, 50);
        //Adding Button to Window
        frame.getContentPane().add(btnDecrpyt);

        DtextField = new JTextField();
        DtextField.setFont(new Font("Serif", Font.ITALIC, 17));
        DtextField.setBounds(950, 120, 210, 30);
        frame.getContentPane().add(DtextField);
        DtextField.setColumns(10);

        DtextField_2 = new JTextField();
        DtextField_2.setFont(new Font("Serif", Font.ITALIC, 14));
        DtextField_2.setBounds(950, 170, 210, 30);
        frame.getContentPane().add(DtextField_2);
        DtextField_2.setColumns(10);

        JButton btnDBrowse = new JButton("BROWSE");
        btnDBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    fi = fileChooser.getSelectedFile();
                    DtextField_2.setText(fileChooser.getSelectedFile().toString());
                    String currentLine;
                    try {
                        ipp = "";
                        BufferedReader br = new BufferedReader(new FileReader(fi));
                        while ((currentLine = br.readLine()) != null) {
                            ipp += currentLine;
                        }
                    } catch (IOException ex) {

                    }

                }
            }
        });
        //Button Font 
        btnDBrowse.setFont(new Font("Arial", Font.BOLD, 16));
        //Button BG
        btnDBrowse.setForeground(Color.decode("#34495e"));
        btnDBrowse.setBackground(Color.decode("#ecf0f1"));
        //Setting Position
        btnDBrowse.setBounds(950, 210, 210, 30);
        //Adding Button to the window
        frame.getContentPane().add(btnDBrowse);

    }
}
