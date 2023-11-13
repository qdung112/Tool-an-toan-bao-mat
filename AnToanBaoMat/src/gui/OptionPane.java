package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cipher.CurrentCipher;

public class OptionPane extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OptionPane() {
		init();
	}
	
	public void init() {
		
	}
	
	public static void showError(String title,String content) {
		showMessageDialog(null,content,title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInfo(String title,String content) {
		showMessageDialog(null,content,title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showWarning(String title,String content) {
		showMessageDialog(null,content,title, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void enterKey() throws Exception {
		if(CurrentCipher.getCurrentCipher().equals("HILL")) {
			enterHillKey();
			return;
		}
		
		JTextField textField = new JTextField(10);
		int res = JOptionPane.showOptionDialog(null,textField,"Nhập " + CurrentCipher.getCurrentCipher() + " Key",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,
                new Object[]{"Đồng ý", "Thoát"}, null);
		if(res == JOptionPane.OK_OPTION) {
			CurrentCipher.setKey(textField.getText());
		}
	}
	
	public static void enterHillKey() throws Exception {
		
		JTextField textField1 = new JTextField(1);
		JTextField textField2 = new JTextField(1);
		JTextField textField3 = new JTextField(1);
		JTextField textField4 = new JTextField(1);
		
		JPanel panel = new JPanel();
		GridLayout girLayout = new GridLayout(2, 2);
		panel.setLayout(girLayout);
		panel.add(textField1);
		panel.add(textField2);
		panel.add(textField3);
		panel.add(textField4);
		
		int res = JOptionPane.showOptionDialog(null,panel,"Nhập " + CurrentCipher.getCurrentCipher() + " Key",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,
                new Object[]{"Đồng ý", "Thoát"}, null);
		if(res == JOptionPane.OK_OPTION) {
			CurrentCipher.setHillKey(textField1.getText(),textField2.getText(),textField3.getText(),textField3.getText());
		}
	}
	
	public static void showKey() throws Exception {
		
		if(CurrentCipher.getCurrentCipher().equals("RSA")) {
			showRSAKey();
			return;
		}
		
		JPanel panel = new JPanel();
		JLabel titlelabel = new JLabel(CurrentCipher.getCurrentCipher() + " Key");
		JTextField textField = new JTextField(20);
		textField.setText(CurrentCipher.showKey()==null?"Key chưa được tạo":CurrentCipher.showKey());
		textField.setEditable(false);
		panel.setSize(200,50);
		panel.add(titlelabel);
		panel.add(textField);
		JOptionPane.showConfirmDialog(null,panel,"Xem key",JOptionPane.DEFAULT_OPTION);
	}
	
	public static void showRSAKey() throws Exception {
		JPanel panel = new JPanel();
		JLabel titlelabel = new JLabel(CurrentCipher.getCurrentCipher() + " Key");
		JTextField textFieldPublicKey = new JTextField(20);
		textFieldPublicKey.setText(CurrentCipher.showRSAPrivateKey()==null?"Key chưa được tạo":CurrentCipher.showKey());
		textFieldPublicKey.setEditable(false);
		
		JTextField textFieldPrivateKey  = new JTextField(20);
		textFieldPrivateKey.setText(CurrentCipher.showRSAPublicKey()==null?"Key chưa được tạo":CurrentCipher.showKey());
		textFieldPrivateKey.setEditable(false);
		panel.setSize(200,50);
		panel.add(titlelabel);
		panel.add(textFieldPublicKey);
		panel.add(textFieldPrivateKey);
		JOptionPane.showConfirmDialog(null,panel,"Xem key",JOptionPane.DEFAULT_OPTION);
	}
	
	
}
