package hr;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import java.awt.Dimension;

import member.Horse;
import member.IMember;
import member.Player;
import member.Result;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

public class DocumentView extends JPanel implements MouseListener{

	public static final int MODE_DOCUMENT = 0;
	public static final int MODE_LIST = 1;
	public static final int MODE_EDIT = 2;
	public static final int IMAGE_LARGE_WIDTH = 150;
	public static final int IMAGE_LARGE_HEIGHT = 200;
	public static final int IMAGE_SMALL_WIDTH = 90;
	public static final int IMAGE_SMALL_HEIGHT = 120;
	public static final int FIELD_WIDTH = 305;
	public static final int FIELD_HEIGHT = 22;
	public static final String CRITERIA_FOR_IMAGE = "사진";  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private TableView tableView = null;
	private JPanel panRight = null;
	private int mode;
	private MemberManager mm;  //  @jve:decl-index=0:
	private DocumentImage documentImage = null;
	private JPanel panField = null;
	private JPanel panGap = null;  //  @jve:decl-index=0:
	private IMember imbr = null;  //  @jve:decl-index=0:
	private MemberFocusTraversalPolicy policy = null;  //  @jve:decl-index=0:
	private List<IDocumentField> fieldList;  //  @jve:decl-index=0:
	private List<JFreeChart> chartList;  //  @jve:decl-index=0:
	private List<JButton> chartButtons;  //  @jve:decl-index=0:
	private JButton btnMod = null;  //  @jve:decl-index=0:visual-constraint="354,331"
	private JPanel panLine = null;
	private JButton btnNew = null;  //  @jve:decl-index=0:visual-constraint="250,330"
	private JButton btnCls = null;  //  @jve:decl-index=0:visual-constraint="458,332"
	private JButton btnDel = null;  //  @jve:decl-index=0:visual-constraint="561,332"
	private JRootPane gPan = null;
	private JLabel lblResult = null;  //  @jve:decl-index=0:visual-constraint="90,341"
	//private SearchBar searchBar = null;
	/**
	 * This is the default constructor
	 */
	public DocumentView() {
		this(DocumentView.MODE_DOCUMENT);
	}
	public DocumentView(int mode){
		super();
		this.mode = mode;
		initialize();
	}
	public void setTableView(TableView tableView){
		this.tableView = tableView;
	}
	/*public DocumentView(int mode, SearchBar searchBar){
		this(mode);
		this.searchBar = searchBar;
	}*/

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(730, 315);
		this.setAutoscrolls(true);
		this.add(getDocumentImage(), BorderLayout.WEST);
		this.add(getPanRight(), BorderLayout.CENTER);
		mm = new MemberManager();
		fieldList = new ArrayList<IDocumentField>();
		chartList = new ArrayList<JFreeChart>();
		chartButtons = new ArrayList<JButton>();
		if(mode != DocumentView.MODE_LIST)
			getPanLine().setBackground(new Color(238, 238, 238));
		//this.getRootPane().addAncestorListener(null);
	}

	/**
	 * This method initializes panRight	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanRight() {
		if (panRight == null) {
			panRight = new JPanel();
			panRight.setLayout(new BorderLayout());
			panRight.setName("jPanel2");
			panRight.setPreferredSize(new Dimension(300, 20));
			panRight.setMinimumSize(new Dimension(300, 1));
			panRight.setBackground(new Color(238, 238, 238));
			panRight.setAutoscrolls(true);
			panRight.add(getPanField(), BorderLayout.WEST);
		}
		return panRight;
	}
	
	/**
	 * This method initializes documentImage	
	 * 	
	 * @return hr.DocumentImage	
	 */
	private DocumentImage getDocumentImage() {
		if (documentImage == null) {
			documentImage = new DocumentImage(mode);
			documentImage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			//documentImage.setPreferredSize(new Dimension(200, 250));
			documentImage.setAutoscrolls(false);
			documentImage.setVisible(false);
			//documentImage.setVisible(true);
		}
		return documentImage;
	}

	/**
	 * This method initializes panField	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanField() {
		if (panField == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(0);
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setHgap(0);
			panField = new JPanel();
			panField.setBackground(new Color(238, 169, 238));
			panField.setAutoscrolls(true);
			panField.setOpaque(false);
			panField.setLayout(flowLayout);
			panField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			panField.setPreferredSize(new Dimension(300, 20));
			panField.add(getPanGap(), null);
		}
		return panField;
	}
	/**
	 * This method initializes panGap	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanGap() {
		if (panGap == null) {
			panGap = new JPanel();
			panGap.setLayout(new FlowLayout());
			panGap.setPreferredSize(new Dimension(300, 20));
			panGap.add(getPanLine(), null);
		}
		return panGap;
	}
	/**
	 * This method initializes panGap	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel createPanGap() {
		JPanel panGap;
		panGap = new JPanel();
		panGap.setLayout(new GridBagLayout());
		panGap.setPreferredSize(new Dimension(300, 20));
		return panGap;
	}
	public void setReady(){
		if(this.mode == DocumentView.MODE_EDIT){
			this.clear();
			documentImage.setVisible(false);
			this.imbr = mm.newMember();
			String[] header = this.imbr.getColumnName();
			Vector<Component> docFields = new Vector<Component>(header.length);

			for(int i = 0; i < header.length; i++){
				if(header[i].equals(DocumentView.CRITERIA_FOR_IMAGE)){
					documentImage.setVisible(true);
					fieldList.add(documentImage);
				}
				else{
					DocumentField df = new DocumentField(mode, header[i], "");
					df.getTxtValue().addMouseListener(this);
					getPanField().add(df);
					docFields.add(df.getTxtValue());
					fieldList.add(df);
				}
			}
			getPanField().add(createPanGap());
			getPanField().add(getBtnNew());
			getPanField().add(getBtnCls());
			getPanField().add(getLblResult());
			docFields.add(getBtnMod());
			docFields.add(getBtnCls());
			getBtnNew().setVisible(true);
			getBtnCls().setVisible(true);
			getLblResult().setVisible(false);
			policy = new MemberFocusTraversalPolicy(docFields);
		}
	}
	/**
	 * 문서보기 객체에 데이터를 심어줄 때 사용합니다.<br>
	 * 심겨진 데이터는 화면 중앙에 스택 형식으로 자료들을 쌓아 줍니다.<br>
	 * 또한 해당 자료 중 이미지가 포함되어 있을 경우 좌측 패널에 이미지를 표현 합니다.<br>
	 * 
	 * @param imbr - 문서 보기에 표현할 객체 데이터 모임. {@link IMember}
	 */
	public void setData(IMember imbr){
		this.clear();
		this.imbr = imbr;
		//Object[] data = imbr.toArray();
		String[] data = imbr.toStrings();
		String[] header = imbr.getColumnName();
		Vector<Component> docFields = new Vector<Component>(data.length);

		for(int i = 0; i < data.length; i++){
			if(documentImage.getValue() == null && (header[i].equals(DocumentView.CRITERIA_FOR_IMAGE))){
				//documentImage.setImage((Image)data[i]);
				documentImage.setValue(data[i]);
				//docFields.add(documentImage);
				fieldList.add(documentImage);
			}
			else{
				DocumentField df = new DocumentField(mode, header[i], data[i].toString());
				df.getTxtValue().addMouseListener(this);
				getPanField().add(df);
				docFields.add(df.getTxtValue());
				fieldList.add(df);
				//TODO : System.out.println(df.toString() + "캬캬" + i);
			}
			//TODO : System.out.println(this.getHeight());
			//TODO : System.out.println(this.getPanField().getSize().getHeight());
		}
		if(mode != DocumentView.MODE_EDIT){
			getBtnNew().setVisible(false);
			getBtnMod().setVisible(false);
			getBtnCls().setVisible(false);
			getBtnDel().setVisible(false);
		}
		getPanField().add(createPanGap());
		getPanField().add(getBtnMod());
		getPanField().add(getBtnDel());
		getPanField().add(getLblResult());
		docFields.add(getBtnMod());
		docFields.add(getBtnDel());
		getLblResult().setVisible(false);

		policy = new MemberFocusTraversalPolicy(docFields);
	}
	public void setGraphData(DefaultCategoryDataset dataset, String title, String xAxisLabel, String yAxisLabel, int chartType){
		GraphMaker gm = new GraphMaker(dataset, title, xAxisLabel, yAxisLabel);
		JButton chartButton = gm.getComponent(chartType);
		getPanField().add(chartButton);
		getPanField().add(createPanGap());
		chartButtons.add(chartButton);
		chartList.add(gm.getChart());
	}
	public void setViewOption(int viewOption){
		int viewCount = 0;
		for(int i = 0; i < this.fieldList.size(); i++){
			Component df = (Component)fieldList.get(i); 
			////TODO : System.out.println( ((viewOption & (1<<i)) == 1) + " : " + (1<<i));
			int mask = 1<<i;
			if((viewOption & mask) == mask){
				df.setVisible(true);
				viewCount++;
			}
			else{
				df.setVisible(false);
			}
		}
		////TODO : System.out.println(viewCount);
		int w = getPanField().getWidth();
		//int h = getPanField().getHeight();
		getPanRight().setPreferredSize(new Dimension(w, 20+ viewCount * DocumentView.FIELD_HEIGHT));
		//getPanField().setSize(w,20 + (viewCount * DocumentView.FIELD_HEIGHT));
	}
	/**
	 * This method initializes btnNew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setText("작성 완료");
			btnNew.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			btnNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					////TODO : System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String[] value = new String[fieldList.size()];
					int i = 0;
					try{
						for(i = 0; i < fieldList.size(); i++){
							value[i] = fieldList.get(i).getValue();
						}
						imbr.setData(value);
						mm.add(imbr);
						getLblResult().setVisible(true);
						getLblResult().setText("데이터 입력이 완료 되었습니다 :)");
						if(tableView != null) tableView.refresh();
					}
					catch(java.lang.NumberFormatException ex){
						JOptionPane.showMessageDialog(btnNew, "입력한 데이터 중 지정된 형식(예:숫자)으로 바꿀 수 없는 것이 있습니다.\n다시 입력 해 주세요 ^^;");
					}
					catch(java.lang.NullPointerException ex){
						JOptionPane.showMessageDialog(btnNew, "입력 하지 않은 데이터가 있습니다.\n확인 바랍니다 ^.^)/");
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(btnNew, "자료의 형식이 잘못 되었습니다.\n예-날짜) yyyy-mm-dd 형식을 지켰는지 확인 해 주세요 :)");
						//TODO : System.out.println(e);
					}
				}
			});
		}
		return btnNew;
	}
	/**
	 * This method initializes btnMod	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnMod() {
		if (btnMod == null) {
			btnMod = new JButton();
			btnMod.setText("변경 완료");
			btnMod.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			btnMod.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//mm.getMembersById(((JTextField)docFields.elementAt(0)).getText(), ClassType.);
					String[] data = new String[fieldList.size()];
					int i = 0;
					for(Object com : fieldList.toArray()){
						if(com.getClass() == DocumentField.class){
							data[i] = ((DocumentField)com).getValue();
						}
						else if(com.getClass() == DocumentImage.class){
							data[i] = ((DocumentImage)com).getValue();
						}
						i++;
					}
					imbr.setData(data);
					getLblResult().setVisible(true);
					getLblResult().setText("데이터 변경이 완료 되었습니다 :)");
					//searchBar.setDataToViewer(mm.getMembers());
					////TODO : System.out.println(((Player)mm.getPlayers().get(0)).getName()); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnMod;
	}
	
	/**
	 * This method initializes btnCls	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCls() {
		if (btnCls == null) {
			btnCls = new JButton();
			btnCls.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			btnCls.setText("새로 작성");
			btnCls.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					for(IDocumentField df : fieldList){
						df.setValue("");
					}
				}
			});
		}
		return btnCls;
	}
	/**
	 * This method initializes btnDel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton();
			btnDel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			btnDel.setText("삭제");
			btnDel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mm.getMembers().remove(imbr);
					getLblResult().setVisible(true);
					getLblResult().setText("데이터 삭제가 완료 되었습니다 :)");
				}
			});
		}
		return btnDel;
	}
	public void clear(){
		//for(int i = 1; i < getPanField().getComponents().length; i++)
		getPanField().removeAll();
		
		documentImage.setValue("");
		getPanField().add(getPanGap());
		fieldList = new ArrayList<IDocumentField>();
		chartList = new ArrayList<JFreeChart>();
		chartButtons = new ArrayList<JButton>();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		////TODO : System.out.println(mode != DocumentView.MODE_LIST);
		if(mode == DocumentView.MODE_DOCUMENT){
			for(int i = 0; i < fieldList.size(); i++){
				IDocumentField field = fieldList.get(i);
				field.setEditMode();
			}
			this.btnMod.setVisible(true);
			this.btnDel.setVisible(true);
			for(JButton jb : chartButtons){
				jb.setVisible(false);
			}
			mode = DocumentView.MODE_EDIT;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method initializes panLine	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanLine() {
		if (panLine == null) {
			panLine = new JPanel();
			panLine.setLayout(new GridBagLayout());
			panLine.setBackground(new Color(51, 51, 51));
			panLine.setPreferredSize(new Dimension(300, 1));
		}
		return panLine;
	}
	
	public JFreeChart[] getChart(){
		JFreeChart[] chart = this.chartList.toArray(new JFreeChart[0]);
		return (chartList.size() != 0)? chart : null;
	}
	public IMember getData(){
		return imbr;
	}
	/**
	 * This method initializes lblResult	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLblResult() {
		if (lblResult == null) {
			lblResult = new JLabel();
			lblResult.setText("JLabel");
			lblResult.setVisible(false);
		}
		return lblResult;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
