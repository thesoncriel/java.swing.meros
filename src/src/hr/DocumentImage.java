package hr;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Canvas;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.multi.MultiButtonUI;
import com.sun.java.swing.plaf.motif.MotifButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DocumentImage extends JPanel implements IDocumentField{

	private static final long serialVersionUID = 1L;
	private final int PANE_NORMAL_WIDTH = 200;
	private final int PANE_NORMAL_HEIGHT = 250;
	private final int PANE_LIST_WIDTH = 160;
	private final int PANE_LIST_HEIGHT = 160;
	private final int IMG_NORMAL_WIDTH = 150;
	private final int IMG_NORMAL_HEIGHT = 200;
	private final int IMG_LIST_WIDTH = 90;
	private final int IMG_LIST_HEIGHT = 120;
	private final String PLEASE_TEXT = "변경시 클릭";
	private JButton btnImage = null;
	private int mode;
	private String value = null;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public DocumentImage() {
		this(DocumentView.MODE_DOCUMENT);
	}
	public DocumentImage(int mode){
		super();
		this.mode = mode;
		initialize();
		if(mode == DocumentView.MODE_LIST) setListMode();
	}
	protected void setListMode(){
		btnImage.setSize(IMG_LIST_WIDTH, IMG_LIST_HEIGHT);
		this.setPreferredSize(new Dimension(PANE_LIST_WIDTH, PANE_LIST_HEIGHT));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(PANE_NORMAL_WIDTH, PANE_NORMAL_HEIGHT);
		this.setPreferredSize(new Dimension(PANE_NORMAL_WIDTH, PANE_NORMAL_HEIGHT));
		this.setLayout(null);
		this.setBackground(new Color(188, 238, 238));
		this.setOpaque(false);
		this.add(getBtnImage(), null);
	}

	/**
	 * This method initializes btnImage	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnImage() {
		if (btnImage == null) {
			btnImage = new JButton();
			btnImage.setBorder(BorderFactory.createLineBorder(new Color(65, 170, 224), 3));
			btnImage.setUI(new BasicButtonUI());
			btnImage.setBackground(new Color(240, 248, 255));
			btnImage.setBounds(new Rectangle(29, 20, 150, 200));
			btnImage.setText(PLEASE_TEXT);
			btnImage.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 1) && (mode != DocumentView.MODE_LIST)){
						imageChange();
					}
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
			btnImage.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					//System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == e.VK_ENTER){
						imageChange();
					}
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
				}
			});
		}
		return btnImage;
	}
	protected void imageChange(){
		String[] filter = {"jpg", "gif", "png"};
		File file = enableFileDialog("이미지 찾기", "이미지 파일(*.jpg, *.gif, *.png)", filter, JFileChooser.OPEN_DIALOG);
		if(file == null) return;
		setValue(file.getPath());
	}
	protected void setImage(Image image){
		int newWidth, newHeight;
		if(mode == DocumentView.MODE_LIST){
			newWidth = DocumentView.IMAGE_SMALL_WIDTH;
			newHeight = DocumentView.IMAGE_SMALL_HEIGHT;
		}
		else{
			newWidth = DocumentView.IMAGE_LARGE_WIDTH;
			newHeight = DocumentView.IMAGE_LARGE_HEIGHT;
		}
		Image img = ImageSaver.getResizedImage(image, newWidth, newHeight);
		ImageIcon icon = new ImageIcon(img);
		getBtnImage().setIcon(icon);
		getBtnImage().setText("");
	}
	protected void setImage(File file){
		Image image = new Canvas().getToolkit().getImage(file.getPath());
		this.setImage(image);
		//this.setValue("file:///" + file.getPath());
	}
	/**
	 * 
	 * DocumentView.MODE_DOCUMENT
	 * DocumentView.MODE_LIST
	 */
	public int getMode(int mode){
		return mode;
	}
	protected File enableFileDialog(String title, String filterDesc, String[] filter, int dialogType){
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(title);
		fc.setDialogType(dialogType);
		fc.setSize(MainForm.DIALOG_SIZE_WIDTH, MainForm.DIALOG_SIZE_HEIGHT);
		fc.setFileFilter(new FileNameExtensionFilter(filterDesc, filter));
		/*
		fDialog = new FileDialog(this, title, dialogType);
		fDialog.setSize(this.DIALOG_SIZE_WIDTH, this.DIALOG_SIZE_HEIGHT);
		fDialog.setFilterExtensions(filter);
		fDialog.setVisible(true);
		*/
		String buttonLabel = (dialogType == FileDialog.LOAD)? "불러오기" : "저장";
		fc.showDialog(this, buttonLabel);
		return fc.getSelectedFile();/*fDialog.getDirectory() + fDialog.getFile();*/
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		if(value != ""){
			try{
				if(value.contains("://")){
					this.value = value;
					this.setImage(new Canvas().getToolkit().createImage(new URL(value)));
				}
				else{
					this.value = "file:///" + value;
					this.setImage(new Canvas().getToolkit().createImage(value));
				}
				this.setVisible(true);
			}
			catch(MalformedURLException e){
				System.out.println(value + "\n경로가 잘 못 되었습니당 ㅡ.ㅡ");
			}
		}
		else{
			clear();
		}
	}
	public void clear(){
		this.value = null;
		this.btnImage.setIcon(null);
		this.btnImage.setText(PLEASE_TEXT);
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if( super.isVisible()) this.setSize(1, 1);
		else{
			if(mode == DocumentView.MODE_LIST){
				this.setPreferredSize(new Dimension(PANE_LIST_WIDTH, PANE_LIST_HEIGHT));
			}
			else{
				this.setPreferredSize(new Dimension(PANE_NORMAL_WIDTH, PANE_NORMAL_HEIGHT));
			}
		}
	}
	@Override
	public void setEditMode() {
		// TODO Auto-generated method stub
		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
