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

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import hash.CurrentHash;

public class FileCheckSumLabel extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private JFileChooser hashFileChooser;
	private RoundedButton checkBtn,hashFileChooseBtn;
	
	private JTextField textHashFile;
	
	private JPanel hashFilePanel;
	private JEditorPane editorHashPane;
	
	public FileCheckSumLabel() {
		init();
	}
	
	public void init() {
		setVisible(false);
		
		GridLayout layout = new GridLayout(3,1);
		setLayout(layout);
	
		layout.setVgap(15);
		
		setBounds(200, 90, 730, 246);
		
		checkBtn = new RoundedButton("Kiểm tra",10);
		checkBtn.setBounds(0,20,100,50);
		
		hashFileChooseBtn = new RoundedButton("Chọn tệp",10);
		hashFileChooseBtn.setSize(100,50);
		
		JLabel btnLabel = new JLabel();
		btnLabel.add(checkBtn);
		add(btnLabel);
		
		TitledBorder titleHashBoder = new TitledBorder("Chọn hoặc kéo thả tệp bạn muốn kiểm tra");
		TitledBorder titleResultBoder = new TitledBorder("Chuỗi băm");
		textHashFile = new JTextField(50);
		
		hashFilePanel = new JPanel();
		hashFilePanel.setSize(500,150);
		hashFilePanel.setLayout(new FlowLayout());
		hashFilePanel.setBorder(titleHashBoder);
		hashFilePanel.add(hashFileChooseBtn);
		hashFilePanel.add(textHashFile);
		
		editorHashPane = new JEditorPane();
		editorHashPane.setSize(500,150);
		editorHashPane.setBorder(titleResultBoder);
		
		hashFileChooseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				hashFileChooser = new JFileChooser();
				hashFileChooser.setSize(568,100);
				hashFileChooser.setDialogTitle("Chọn tệp");
				
				int response = hashFileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					textHashFile.setText(hashFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		add(hashFilePanel);
		add(editorHashPane);	
		setUpEventFileHashBtn();
		setUpDragAndDropFile();
	}

	private void setUpEventFileHashBtn() {
		checkBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = textHashFile.getText();
				try {
					if(editorHashPane.getText().equals(CurrentHash.getHashFromFile(new File(src)))) {
						OptionPane.showInfo("Kiểm tra","Chính xác");
					} else {
						OptionPane.showWarning("Kiểm tra","Không chính xác");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void setUpDragAndDropFile() {
		hashFilePanel.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            if (!droppedFiles.isEmpty()) {
		                File file = droppedFiles.get(0);
		                textHashFile.setText(file.getAbsolutePath());
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
	}
}

