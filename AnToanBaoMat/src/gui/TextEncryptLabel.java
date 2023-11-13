package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import cipher.CurrentCipher;

public class TextEncryptLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane editorDeOrEncryptPane;
	private JEditorPane editorResultPane;
	private RoundedButton encryptBtn;
	private RoundedButton decryptBtn;
	
	public TextEncryptLabel() {
		init();
	}
	
	public void init() {
		setVisible(false);
		
		GridLayout layout = new GridLayout(3,1);
		setLayout(layout);
		
		layout.setVgap(15);
		
		setBounds(200, 90, 730, 400);
		encryptBtn = new RoundedButton("Mã hóa",10);
		decryptBtn = new RoundedButton("Giải mã",10);
		decryptBtn.setBounds(130,20,100,50);
		encryptBtn.setBounds(0,20,100,50);
		
		JLabel btnLabel = new JLabel();
		btnLabel.add(encryptBtn);
		btnLabel.add(decryptBtn);
		add(btnLabel);
		
		TitledBorder titleEncryptBoder = new TitledBorder("Nhập văn bản bạn muốn mã hóa/giải mã");
		editorDeOrEncryptPane = new JEditorPane();
		editorDeOrEncryptPane.setSize(568,100);
		editorDeOrEncryptPane.setBorder(titleEncryptBoder);
		editorDeOrEncryptPane.setEditable(true);
		
		JScrollPane editorEncryptScrollPane = new JScrollPane(editorDeOrEncryptPane);
		editorEncryptScrollPane.getVerticalScrollBar().setValue(0);
		editorEncryptScrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 
		editorEncryptScrollPane.setPreferredSize(new Dimension(250, 145));
		editorEncryptScrollPane.setMinimumSize(new Dimension(10, 10));
		 
		add(editorDeOrEncryptPane);
		
		TitledBorder titleDecryptBoder = new TitledBorder("Kết quả");
		editorResultPane = new JEditorPane();
		editorResultPane.setSize(568,100);
		editorResultPane.setBorder(titleDecryptBoder);
		JScrollPane editorDecryptScrollPane = new JScrollPane(editorResultPane);
		editorDecryptScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorDecryptScrollPane.setPreferredSize(new Dimension(250, 145));
		editorDecryptScrollPane.setMinimumSize(new Dimension(10, 10));
		add(editorResultPane);
		
		setUpEventEncryptBtn();
		setUpEventDecryptBtn();
	}
	private void setUpEventEncryptBtn() {
		encryptBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = editorDeOrEncryptPane.getText();
				try {
					editorResultPane.setText(CurrentCipher.encrypt(inputText));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void setUpEventDecryptBtn() {
		decryptBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = editorDeOrEncryptPane.getText();
				try {
					editorResultPane.setText(CurrentCipher.decrypt(inputText));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
}
