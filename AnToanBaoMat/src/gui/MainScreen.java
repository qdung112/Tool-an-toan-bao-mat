package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cipher.CurrentCipher;
import hash.CurrentHash;

public class MainScreen extends JFrame 	{
	
	private static final long serialVersionUID = 1L;

	private JLabel mainLabel= new JLabel();
	private JLabel titlelabel = new JLabel();
	private JLabel buttonChooser; 
	private RoundedButton homeButton,textEncryptButton,fileEncryptButton,fileCheckSumButton, textHashButton,fileHashButton; 

	private TextEncryptLabel textEncryptLabel = new TextEncryptLabel();
	private FileEncryptLabel fileEncryptLabel = new FileEncryptLabel();
	private FileCheckSumLabel fileCheckSumLabel = new FileCheckSumLabel();
	private TextHashLabel textHashLabel = new TextHashLabel();
	private FileHashLabel fileHashLabel = new FileHashLabel();
	
	private String[] ciphers = {"DES", "BLOWFISH", "AES", "RC5", "TEA", "RSA", "HILL", "VIGENERE"};
	private String[] hashs = {"MD2", "MD5", "SHA256"};
	private String[] keyOptions = {"Tạo key mới", "Xem key", "Nhập key"};
	private String[] alphabetOptions = {"Tiếng việt","Tiếng anh"};
	JComboBox<String> comboBoxAlphabet = new JComboBox<>(alphabetOptions);
	private String cipher;
	private String hash;
	
	MainScreen(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		setLocationRelativeTo(null);
		this.setLocation(300, 100);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(Main.class.getResource("/image/icon3.PNG"));
		ImageIcon backGround = new ImageIcon(Main.class.getResource("/image/backgroundImage.png"));
		
		setIconImage(icon.getImage());
		setContentPane(new JLabel(backGround)); 
		
		mainLabel.setSize(868, 546);
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setText("Tool an toàn và bảo mật");
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(250, 200, 568, 146);
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
		mainLabel.add(welcomeLabel);
		
		
		titlelabel.setText("Home");
		titlelabel.setBounds(500, 1, 200, 100);
		titlelabel.setForeground(Color.WHITE);

		
			 
		 CurrentCipher.setCurrentCipher("DES");
		 CurrentHash.setCurrentHash("MD2");
		 
		 setUpButtonChooser();
		 setUpComboBoxCiphers();
		 setUpComboBoxHash();
		 setUpComboBoxKey();
		 setUpAlphabetComboBox();
		
		 openFileEncryptLabel();
		 openFileHashLabel();
		 openMainLabel();
		 openTextEncryptLabel();
		 openTextHashLabel();
		 openFileCheckSumLabel();
		 
		add(titlelabel);
		add(mainLabel);
		add(textEncryptLabel);
		add(fileEncryptLabel);
		add(textHashLabel);
		add(fileHashLabel);
		add(fileCheckSumLabel);
		pack();
		
	}
	

	private void setUpButtonChooser() {
		buttonChooser = new JLabel();
		homeButton = new RoundedButton("Home",9);
		textEncryptButton = new RoundedButton("Mã hóa văn bản",9);
		fileEncryptButton = new RoundedButton("Mã hóa tệp",9);
		textHashButton = new RoundedButton("Băm văn bản",9);
		fileHashButton = new RoundedButton("Băm tệp",9);
		fileCheckSumButton = new RoundedButton("Chữ ký điện tử",9);
		
		GridLayout layout = new GridLayout(6, 1);
		layout.setHgap(15); 
		layout.setVgap(15);
		
		buttonChooser.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonChooser.setLayout(layout);
		buttonChooser.setSize(150,500);
		buttonChooser.add(homeButton);
		buttonChooser.add(textEncryptButton);
		buttonChooser.add(fileEncryptButton);
		buttonChooser.add(textHashButton);
		buttonChooser.add(fileHashButton);
		buttonChooser.add(fileCheckSumButton);
		
		add(buttonChooser);
	}
	
	private void setUpComboBoxCiphers() {
	      JComboBox<String> comboBox = new JComboBox<>(ciphers);
	      comboBox.setBounds(200,10,100,30);
	      comboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent event) {
	                if (event.getStateChange() == ItemEvent.SELECTED) {
	                    cipher = (String) event.getItem();
	                    if(cipher.equals("HILL") || cipher.equals("VIGENERE")) {
	                    	comboBoxAlphabet.setVisible(true);
	                    } else {
	                    	comboBoxAlphabet.setVisible(false);
	                    }
	                    CurrentCipher.setCurrentCipher(cipher);
	                }
	            }
	        });
	      add(comboBox);
	}
	
	private void setUpComboBoxKey() {
	      JComboBox<String> comboBox = new JComboBox<>(keyOptions);
	      comboBox.setBounds(330,10,100,30);
	      comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
                	if(((String) comboBox.getSelectedItem()).equals("Tạo key mới")) {
                		try {
							CurrentCipher.createKey();
						} catch (Exception e) {
							e.printStackTrace();
						}
                	} else if(((String) comboBox.getSelectedItem()).equals("Xem key")) {
                		try {
                		
							OptionPane.showKey();
						} catch (Exception e) {
							e.printStackTrace();
						}
                	} else {
                		try {
							OptionPane.enterKey();
						} catch (Exception e) {
							e.printStackTrace();
						}
                	}
                }
		});
	      add(comboBox);
	}
	
	private void setUpComboBoxHash() {
	      JComboBox<String> comboBox = new JComboBox<>(hashs);
	      comboBox.setBounds(200,60,100,30);
	      comboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent event) {
	                if (event.getStateChange() == ItemEvent.SELECTED) {
	                	hash = (String) event.getItem();
	                	CurrentHash.setCurrentHash(hash);
	                }
	            }
	        });
	      add(comboBox);
	}
	
	private void openMainLabel() {
		homeButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					titlelabel.setText("Home");
					mainLabel.setVisible(true);
					fileEncryptLabel.setVisible(false);
					textEncryptLabel.setVisible(false);
					fileHashLabel.setVisible(false);
					textHashLabel.setVisible(false);
					fileCheckSumLabel.setVisible(false);
				  	revalidate();
	                repaint();
			}
		});
	}
	
	private void openFileEncryptLabel() {
		fileEncryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titlelabel.setText("Mã hóa tệp");
				mainLabel.setVisible(false);
				textEncryptLabel.setVisible(false);
				fileEncryptLabel.setVisible(true);
				fileHashLabel.setVisible(false);
				textHashLabel.setVisible(false);
				fileCheckSumLabel.setVisible(false);
			  	revalidate();
                repaint();
			}
		});
	}
	
	private void openTextEncryptLabel() {
		textEncryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titlelabel.setText("Mã hóa văn bản");
				mainLabel.setVisible(false);
				fileEncryptLabel.setVisible(false);
				textEncryptLabel.setVisible(true);
				fileHashLabel.setVisible(false);
				textHashLabel.setVisible(false);
				fileCheckSumLabel.setVisible(false);
				revalidate();
                repaint();
			}
		});
	}
	
	private void openFileCheckSumLabel() {
		fileCheckSumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				titlelabel.setText("Chữ ký điện tử");
				mainLabel.setVisible(false);
				fileEncryptLabel.setVisible(false);
				textEncryptLabel.setVisible(false);
				fileHashLabel.setVisible(false);
				textHashLabel.setVisible(false);
				fileCheckSumLabel.setVisible(true);
			  	revalidate();
                repaint();
	
			}
		});
	}
	
	private void openTextHashLabel() {
		textHashButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titlelabel.setText("Băm văn bản");
				mainLabel.setVisible(false);
				fileEncryptLabel.setVisible(false);
				textEncryptLabel.setVisible(false);
				fileHashLabel.setVisible(false);
				textHashLabel.setVisible(true);
				fileCheckSumLabel.setVisible(false);
			  	revalidate();
                repaint();
			}
		});
	}
	
	private void openFileHashLabel() {
		fileHashButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titlelabel.setText("Băm tập");
				mainLabel.setVisible(false);
				fileEncryptLabel.setVisible(false);
				textEncryptLabel.setVisible(false);
				fileHashLabel.setVisible(true);
				textHashLabel.setVisible(false);
				fileCheckSumLabel.setVisible(false);
			  	revalidate();
                repaint();
			}
		});
	}
	
	private void setUpAlphabetComboBox() {	
		  
		comboBoxAlphabet.setBounds(330,60,100,30);
		comboBoxAlphabet.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                	String option = (String) event.getItem();
                	if(option.equals("Tiếng việt")) {
                		CurrentCipher.setVietNameseAlphabet();
                	} else {
                		CurrentCipher.setEnglishAlphabet();
                	}
                }
            }
        });
		comboBoxAlphabet.setVisible(false);
	    add(comboBoxAlphabet);
	}

	
}
