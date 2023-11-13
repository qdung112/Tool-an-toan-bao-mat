package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import cipher.CurrentCipher;

public class FileEncryptLabel  extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private JFileChooser encryptFileChooser;
	private JFileChooser decryptFileChooser;
	private RoundedButton encryptBtn,encryptFileChooseBtn;
	private RoundedButton decryptBtn,decryptFileChooseBtn;
	
	private JTextField textDecryptOrEncryptFile;
	private JTextField textResultFile;
	
	private JPanel encryptFilePanel;
	private JPanel decryptFilePanel;
	
	
	public FileEncryptLabel() {
		init();
	}
	
	public void init() {
		setVisible(false);
		
		GridLayout layout = new GridLayout(3,1);
		setLayout(layout);
		
		//layout.setHgap(15); 
		layout.setVgap(15);
		
		setBounds(200, 90, 730, 246);
		
		encryptBtn = new RoundedButton("Mã hóa",10);
		decryptBtn = new RoundedButton("Giải mã",10);
		decryptBtn.setBounds(130,20,100,50);
		encryptBtn.setBounds(0,20,100,50);
		
		encryptFileChooseBtn = new RoundedButton("Chọn tệp",10);
		decryptFileChooseBtn = new RoundedButton("Chọn thư mục",10);
		decryptFileChooseBtn.setSize(100,50);
		encryptFileChooseBtn.setSize(100,50);
		
		JLabel btnLabel = new JLabel();
		btnLabel.add(encryptBtn);
		btnLabel.add(decryptBtn);
		add(btnLabel);
		
		TitledBorder titleEncryptBoder = new TitledBorder("Chọn hoặc kéo thả tệp bạn muốn giải mã/mã hóa");
		TitledBorder titleDecryptBoder = new TitledBorder("Chọn thư mục chứa tệp kết quả");
		textResultFile = new JTextField(50);
		textDecryptOrEncryptFile = new JTextField(50);
		
		encryptFilePanel = new JPanel();
		encryptFilePanel.setSize(500,150);
		encryptFilePanel.setLayout(new FlowLayout());
		encryptFilePanel.setBorder(titleEncryptBoder);
		encryptFilePanel.add(encryptFileChooseBtn);
		encryptFilePanel.add(textDecryptOrEncryptFile);
		
		decryptFilePanel = new JPanel();
		decryptFilePanel.setSize(500,150);
		decryptFilePanel.setLayout(new FlowLayout());
		decryptFilePanel.setBorder(titleDecryptBoder);
		decryptFilePanel.add(decryptFileChooseBtn);
		decryptFilePanel.add(textResultFile);
		
		
		encryptFileChooseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				encryptFileChooser = new JFileChooser();
				encryptFileChooser.setSize(568,100);
				encryptFileChooser.setDialogTitle("Chọn tệp");
				
				int response = encryptFileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					textDecryptOrEncryptFile.setText(encryptFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		decryptFileChooseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				decryptFileChooser = new JFileChooser();
				decryptFileChooser.setSize(568,100);
				decryptFileChooser.setDialogTitle("Chọn thư mục");
				decryptFileChooser.setAcceptAllFileFilterUsed(false);
			
				int response = decryptFileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					textResultFile.setText(decryptFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		add(encryptFilePanel);
		add(decryptFilePanel);
		
		setUpEventFileDecryptBtn();
		setUpEventFileEncryptBtn();
		setUpDragAndDropFile();
	}

	private void setUpEventFileEncryptBtn() {
		encryptBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = textDecryptOrEncryptFile.getText();
				String des = textResultFile.getText();
				try {
					CurrentCipher.encryptFile(src, des);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void setUpEventFileDecryptBtn() {
		decryptBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = textDecryptOrEncryptFile.getText();
				String des = textResultFile.getText();
				try {
					CurrentCipher.decryptFile(src, des);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void setUpDragAndDropFile() {
		encryptFilePanel.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            if (!droppedFiles.isEmpty()) {
		                File file = droppedFiles.get(0);
		                textDecryptOrEncryptFile.setText(file.getAbsolutePath());
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
	}
}
