package hr;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FocusTraversalPolicy;
import java.awt.Component;
import java.awt.Container;

public class DocumentField extends JPanel implements IDocumentField{

	private static final long serialVersionUID = 1L;
	private JLabel label = null;
	private JTextField txtValue = null;
	private JLabel lblCol = null;
	private Border defaultBorder = null;  //  @jve:decl-index=0:
	private int mode;

	/**
	 * This is the default constructor
	 */
	public DocumentField() {
		super();
		initialize();
	}
	public DocumentField(int mode){
		this();
		this.mode = mode;
		if(mode == DocumentView.MODE_EDIT) setEditMode();
	}
	public DocumentField(int mode, String labelValue, String textValue){
		this(mode);
		setLabel(labelValue);
		setValue(textValue);
	}
	public void setEditMode(){
		mode = DocumentView.MODE_EDIT;
		txtValue.setEnabled(true);
		txtValue.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		txtValue.setBackground(Color.WHITE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblCol = new JLabel();
		lblCol.setText(" : ");
		lblCol.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(0);
		label = new JLabel();
		label.setText("가나다라마");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setName("lable");
		label.setPreferredSize(new Dimension(110, 18));
		label.setFont(new Font("돋움", Font.BOLD | Font.ITALIC, 14));
		this.setLayout(flowLayout);
		this.setSize(DocumentView.FIELD_WIDTH, DocumentView.FIELD_HEIGHT);
		this.setPreferredSize(new Dimension(DocumentView.FIELD_WIDTH, DocumentView.FIELD_HEIGHT));
		this.setLocation(new Point(2, 2));
		this.add(label, null);
		this.add(lblCol, null);
		this.add(getTxtValue(), null);
		/*this.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if(mode != DocumentView.MODE_LIST){
					setEditMode();
					txtValue.requestFocus();
					//txtValue.request
				}
				System.out.println("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
		});*/
		defaultBorder = new SoftBevelBorder(2);
		this.mode = DocumentView.MODE_DOCUMENT;
	}

	/**
	 * This method initializes txtValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtValue() {
		if (txtValue == null) {
			txtValue = new JTextField();
			txtValue.setPreferredSize(new Dimension(200, 22));
			txtValue.setName("text");
			txtValue.setFont(new Font("Dialog", Font.ITALIC, 14));
			txtValue.setText("가나다라마 abcd ABCD");
			txtValue.setEnabled(false);
			txtValue.setBackground(new Color(238, 238, 238));
			txtValue.setColumns(15);
			txtValue.setEditable(true);
			txtValue.setDisabledTextColor(new Color(51, 51, 51));
			txtValue.setFocusTraversalPolicy(new FocusTraversalPolicy(){

	public Component getComponentAfter(Container aContainer,
			Component aComponent) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public Component getComponentBefore(Container aContainer,
			Component aComponent) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public Component getDefaultComponent(Container aContainer) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public Component getFirstComponent(Container aContainer) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public Component getLastComponent(Container aContainer) {
		// TODO Auto-generated method stub
		return null;
		
	}});
			txtValue.setBorder(new EmptyBorder(0,0,0,0));
			txtValue.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(mode != DocumentView.MODE_LIST){
						setEditMode();
						txtValue.requestFocus();
						txtValue.setSelectionStart(0);
						txtValue.setSelectionEnd(txtValue.getText().length());
					}
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
			txtValue.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if(mode != DocumentView.MODE_EDIT){
						//setEditMode();
					}
					//System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
				}
			});
			
		}
		return txtValue;
	}
	
	public void setLabel(String str){
		label.setText(str);
	}
	public String getLebel(){
		return label.getText();
	}
	public void setValue(String val){
		txtValue.setText(val);
	}
	public String getValue(){
		return txtValue.getText();
	}

}  //  @jve:decl-index=0:visual-constraint="10,14"
