package hr;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.util.EventListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;
import javax.xml.datatype.XMLGregorianCalendar;

import member.IMember;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel{

	private static final long serialVersionUID = 1L;
	private JComboBox cmbField = null;
	private JComboBox cmbCompare = null;
	private JTextField txtSearch = null;
	private JButton btnSearch = null;
	
	private MemberManager mm = null;
	private TableView tableView = null;
	private ListView listView = null;
	private JTextField txtCount = null;
	
	private String searchText = "";
	/**
	 * This is the default constructor
	 */
	public SearchBar() {
		super();
		initialize();
		mm = new MemberManager();
	}
	public SearchBar(TableView tableView, ListView listView, JTextField txtCount){
		this();
		this.tableView = tableView;
		this.listView = listView;
		this.txtCount = txtCount;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(562, 34);
		this.setLayout(new FlowLayout());
		this.add(getCmbField(), null);
		this.add(getCmbCompare(), null);
		this.add(getTxtSearch(), null);
		this.add(getBtnSearch(), null);
	}

	/**
	 * This method initializes cmbField	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbField() {
		if (cmbField == null) {
			cmbField = new JComboBox();
			cmbField.setPreferredSize(new Dimension(100, 22));
			cmbField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		}
		return cmbField;
	}

	/**
	 * This method initializes cmbCompare	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbCompare() {
		if (cmbCompare == null) {
			cmbCompare = new JComboBox();
			cmbCompare.setPreferredSize(new Dimension(60, 22));
			cmbCompare.setFont(new Font("Dialog", Font.BOLD, 14));
			cmbCompare.addItem(">");
			cmbCompare.addItem(">=");
			cmbCompare.addItem("=");
			cmbCompare.addItem("<=");
			cmbCompare.addItem("<");
			cmbCompare.addItem("Not");
		}
		return cmbCompare;
	}

	/**
	 * This method initializes txtSearch	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtSearch() {
		if (txtSearch == null) {
			txtSearch = new JTextField();
			txtSearch.setPreferredSize(new Dimension(200, 22));
			txtSearch.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return txtSearch;
	}

	/**
	 * This method initializes btnSearch	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton();
			btnSearch.setPreferredSize(new Dimension(65, 22));
			btnSearch.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			btnSearch.setText("°Ë»ö");
			btnSearch.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						List<IMember> list = mm.getMembers(cmbField.getSelectedItem().toString(), 
										cmbCompare.getSelectedIndex(),
										txtSearch.getText());
						setDataToViewer(list);
					}
					catch(IndexOutOfBoundsException ex){}
				}
			});
		}
		return btnSearch;
	}
	public void setDataToViewer(List<IMember> list){
		tableView.setData(list);
		listView.setData(list);
		listView.setViewOption(mm.getUserSelection());
		txtCount.setText(list.size() + " °Ç");
	}
	public void setAttribute(IMember imbr){
		try{
			String[] columnName = imbr.getColumnName();
			//Object[] obj = imbr.toArray();
			this.cmbField.removeAllItems();
			for(int i = 0; i < columnName.length; i++){
				this.cmbField.addItem(columnName[i]);
			}
		}catch(Exception e){
			System.out.println("Çæ -_-");
		}
	}
	public String getSearchText(){
		return this.txtSearch.getText();
	}
}
