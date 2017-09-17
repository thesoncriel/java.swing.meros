package hr;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import member.Horse;
import member.IMember;
import member.Player;
import member.Result;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;

import java.awt.Font;
import javax.swing.JList;

public class ListView extends JPanel implements javax.swing.event.AncestorListener{

	private static final long serialVersionUID = 1L;
	public static final int VIEW_OPTION_PLAYER = 1+2+4+8+64;
	public static final int VIEW_OPTION_HORSE = 1+2+4+64+128+256;
	public static final int VIEW_OPTION_RESULT = 1+2+4+8;
	private JScrollPane scrlPan = null;
	private JPanel panOption = null;
	private JPanel panList = null;
	private int viewOption;
	private MemberManager mm;
	//private List<DocumentView> docList = null;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public ListView() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(457, 241);
		this.setLayout(new BorderLayout());
		this.add(getPanOption(), BorderLayout.NORTH);
		this.add(getScrlPan(), java.awt.BorderLayout.CENTER);
		viewOption = Integer.MAX_VALUE;
		this.mm = new MemberManager();
	}
	private void initData(){
		this.panList.removeAll();
		this.panOption.removeAll();
	}

	/**
	 * This method initializes scrlPan	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrlPan() {
		if (scrlPan == null) {
			scrlPan = new JScrollPane();
			scrlPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrlPan.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			scrlPan.setViewportView(getPanList());
		}
		return scrlPan;
	}

	/**
	 * This method initializes panOption	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanOption() {
		if (panOption == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(0);
			panOption = new JPanel();
			panOption.setAlignmentX(0.5F);
			panOption.setAutoscrolls(true);
			panOption.setLayout(flowLayout);
			panOption.setPreferredSize(new Dimension(100, 75));
			panOption.setBorder(BorderFactory.createTitledBorder(null, "ÇÊµå ¿É¼Ç", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		}
		return panOption;
	}

	private JCheckBox makeCheckBox(String label) {
		JCheckBox chk = new JCheckBox();
		chk.setText(label);
		chk.setSelected(true);
		chk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				chkBoxChanged((JCheckBox)e.getSource());
				//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
			}
		});
		return chk;
	}
	
	private void chkBoxChanged(JCheckBox chk){
		Component[] arrChk = this.panOption.getComponents();
		for(int i = 0; i < arrChk.length; i++){
			JCheckBox tChk = (JCheckBox)arrChk[i]; 
			if(tChk.equals(chk)){
				this.viewOption = viewOption ^ (1<<i);
				this.setViewOption(viewOption);
				return;
			}
		}
	}

	

	/**
	 * This method initializes panList	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanList() {
		if (panList == null) {
			panList = new JPanel();
			panList.setLayout(new BoxLayout(getPanList(), BoxLayout.Y_AXIS));
		}
		return panList;
	}
	
	
	public void setData(List<IMember> list){
		initData();
		//System.out.println(new MemberManager().getPlayers().size() + "Ä¾Ä¾");
		try{
			String[] label = list.get(0).getColumnName();
			for(int i = 0; i < label.length; i++){
				JCheckBox chk = this.makeCheckBox(label[i]);
				panOption.add(chk);
			}
			for(int i = 0; i < list.size(); i++){
				DocumentView dv = new DocumentView(DocumentView.MODE_LIST);
				dv.setData(list.get(i));
				getPanList().add(dv);
			}
		}
		catch(IndexOutOfBoundsException e){System.out.println("À½?");}
		//System.out.println(new MemberManager().getPlayers().size() + "Å¯Å¯");
	}
	public void setViewOption(ClassType classType){
		if(classType == ClassType.PLAYER){
			this.setViewOption(this.VIEW_OPTION_PLAYER);
		}
		else if(classType == ClassType.HORSE){
			this.setViewOption(this.VIEW_OPTION_HORSE);
		}
		else if(classType == ClassType.RESULT){
			this.setViewOption(this.VIEW_OPTION_RESULT);
		}
	}
	public void setViewOption(int viewOption){
		this.viewOption = viewOption;
		Component[] chk = this.getPanOption().getComponents();
		Component[] doc = this.getPanList().getComponents();
		for(int i = 0; i < doc.length; i++){
			((DocumentView)doc[i]).setViewOption(viewOption);
		}
		for(int i = 0; i < chk.length; i++){
			if((viewOption & (1<<i)) == (1<<i)){
				((JCheckBox)chk[i]).setSelected(true);
			}
			else{
				((JCheckBox)chk[i]).setSelected(false);
			}
		}
	}
	
	public void refresh(){
		this.setData(mm.getMembers());
		setViewOption(mm.getUserSelection());
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		System.out.println("ÇÏÇÏ");
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
