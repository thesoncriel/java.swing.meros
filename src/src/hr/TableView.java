package hr;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import member.*;

import javax.swing.JButton;



public class TableView extends JPanel {

	private MemberModel mModel = null;
	private MemberManager mm = null;  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private JScrollPane scrlPan = null;
	private JTable table = null;
	private ListView listView = null;
	private JTextField txtCount = null;
	/**
	 * This is the default constructor
	 */
	public TableView() {
		super();
		initialize();
	}
	public TableView(ListView listView, JTextField txtCount){
		this();
		this.listView = listView;
		this.txtCount = txtCount;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.weightx = 1.0;
		this.setSize(442, 333);
		this.setLayout(new GridBagLayout());
		this.add(getScrlPan(), gridBagConstraints);
		this.mm = new MemberManager();
	}

	/**
	 * This method initializes scrlPan	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrlPan() {
		if (scrlPan == null) {
			scrlPan = new JScrollPane();
			scrlPan.setViewportView(getTable());
		}
		return scrlPan;
	}

	/**
	 * This method initializes table	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new java.awt.event.MouseListener() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					try{
						DocumentViewWindow dvw = new DocumentViewWindow(new JFrame());
						dvw.setData((IMember)mm.getMembers().get(table.getSelectedRow()));
						dvw.setVisible(true);
						refresh();
					}
					catch(IndexOutOfBoundsException ex){}
				}
				public void mousePressed(java.awt.event.MouseEvent e) {
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
			});
		}
		return table;
	}
	public void showDocument(IMember imbr){
		//DocumentViewWindow dv = new DocumentViewWindow(this);
	}
	public void setData(List<IMember> list){
		Object[] schemaSample = null;
		try{
			schemaSample = list.get(0).toArray();
		}
		catch(IndexOutOfBoundsException e){}
		
		
		
		mModel = new MemberModel(list);
		//System.out.println(mModel.getValueAt(0, 1));
		//this.table.setVisible(false);
		this.table.setModel(mModel);
		//this.table.setModel(dm);
		//System.out.println(dm.getTuple(0)[0] + " = " + dm.getColumnName(1));
		//this.table = new JTable(dm);
		//this.remove(this.scrlPan);
		//this.initialize();
		/*
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.weightx = 1.0;
		JScrollPane jp = this.scrlPan;
		this.remove(jp);
		this.scrlPan = new JScrollPane(table);
		this.add(getScrlPan(), gridBagConstraints);
		//scrlPan.requestFocus();
		jp.setVisible(false);
		this.scrlPan.setVisible(true);
		//this.scrlPan.setVisible(true);
		
		//this.table.setVisible(true);
		*/
	}
	
	public void refresh(){
		this.setData(mm.getMembers());
		if(listView != null) listView.refresh();
		if(txtCount != null) txtCount.setText(mm.getMembers().size() + " °Ç");
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
