package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import hash.CurrentHash;

public class TextHashLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane editorHashPane;
	private JEditorPane editorResultPane;
	private RoundedButton hashBtn;
	
	public TextHashLabel() {
		init();
	}
	
	public void init() {
		setVisible(false);
		
		GridLayout layout = new GridLayout(3,1);
		setLayout(layout);
		
		//layout.setHgap(15); 
		layout.setVgap(15);
		
		setBounds(200, 90, 730, 400);
		
		hashBtn = new RoundedButton("Băm",10);
		hashBtn.setBounds(0,20,100,50);

		JLabel btnLabel = new JLabel();
		btnLabel.add(hashBtn);
		add(btnLabel);
		
		TitledBorder titleEncryptBoder = new TitledBorder("Nhập văn bản bạn muốn băm");
		editorHashPane = new JEditorPane();
		editorHashPane.setSize(568,100);
		editorHashPane.setBorder(titleEncryptBoder);
		JScrollPane editorEncryptScrollPane = new JScrollPane(editorHashPane);
		/*
		 * editorEncryptScrollPane.setVerticalScrollBarPolicy(
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 */
		/*
		 * editorEncryptScrollPane.setPreferredSize(new Dimension(250, 145));
		 * editorEncryptScrollPane.setMinimumSize(new Dimension(10, 10));
		 */
		add(editorHashPane);
		
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
		setUpEventHashBtn();
	}
	
	private void setUpEventHashBtn() {
		hashBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = editorHashPane.getText();
				try {
					editorResultPane.setText(CurrentHash.getHashFromText(inputText));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
}

