package hr;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import member.Horse;
import member.HorseRace;
import member.HorseType;
import member.IMember;
import member.Player;
import member.Result;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

public class MainForm extends JFrame implements WindowListener{
	public static final int DIALOG_SIZE_WIDTH = 400;
	public static int DIALOG_SIZE_HEIGHT = 300;
	public static final String DIALOG_FILE_XML = "�ؽ�Ʈ ����(*.txt), XML ����(*.xml)";
	public final String DIALOG_FILE_HRM = "�渶 ���� ����(*.hrm)";  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private JMenuBar mnuBar = null;
	private JMenu mnuFile = null;
	private JMenu mnuManage = null;
	private JMenu mnuQuit = null;
	private JMenuItem mnuiNew = null;
	private JMenuItem mnuiLoad = null;
	private JMenuItem mnuiSave = null;
	private JMenuItem mnuiImport = null;
	private JMenuItem mnuiExport = null;
	private JMenuItem mnuiPlayer = null;
	private JMenuItem mnuiHorse = null;
	private JMenuItem mnuiResult = null;
	private JMenuItem mnuiBlink = null;
	private JMenuItem mnuiExit = null;
	private JTabbedPane tabPan = null;
	private TableView tableView = null;
	private JPanel conPan = null;
	private SearchBar searchBar = null;
	//private JButton searchButton = null;
	private JPanel countPan = null;
	private JLabel lblCount = null;
	private JTextField txtCount = null;
	
	private MemberManager mm = null;  //  @jve:decl-index=0:
	private XMLManager xmlm = null;  //  @jve:decl-index=0:
	private TextManager txtm = null;  //  @jve:decl-index=0:
	private ListView listView = null;
	private JScrollPane scrlNew = null;
	private DocumentView docNew = null;
	/**
	 * This is the default constructor
	 */
	public MainForm() {
		super();
		initialize();
		//TODO �ӽ� ���� ������
		/*mm.add(new Result("3040", "2009-10-10", 34.22, 17.2));
		mm.add(new Result("3040", "2009-10-11", 23.12, 76.3));
		mm.add(new Result("3040", "2009-10-12", 45.99, 45.2));
		mm.add(new Result("3051", "2009-10-10", 22.87, 22.3));
		mm.add(new Result("3051", "2009-10-11", 39.28, 87.7));
		mm.add(new Result("3051", "2009-10-12", 38.83, 35.1));
		mm.add(new Player("3040", "��ö��", 34, true, 174.2, 70.6, "C:/00works/My Pictures/friends.jpg", "2090-3"));
		mm.add(new Player("3051", "������", 32, false, 169.5, 67.3, "C:/00works/My Pictures/coba.jpg", "2090-5"));
		mm.add(new Horse("2090-3", "����", 32, false, 201.5, 92.3, "C:/00works/My Pictures/25289_11257511520.jpg", "����", HorseType.ARABIAN));
		mm.add(new Horse("2090-5", "�漺��", 32, false, 203.5, 91.4, "C:/00works/My Pictures/1265651166211cnddyd_0.jpg", "���", HorseType.LIPIZZANER));
		*/
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(646, 594);
		this.setContentPane(getConPan());
		this.setJMenuBar(getMenuBars());
		this.setTitle("�޷ν�(Meroz) - �渶 ���� �ý��� - ver 1.0");
		this.mm = new MemberManager(MemberManager.DEFAULT_SAVE_FILE);
		this.xmlm = new XMLManager();
		this.txtm = new TextManager();
		this.addWindowListener(this);
	}

	/**
	 * This method initializes menuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenuBars() {
		if (mnuBar == null) {
			mnuBar = new JMenuBar();
			mnuBar.add(getMnuFile());
			mnuBar.add(getMnuManage());
			mnuBar.add(getMnuQuit());
		}
		return mnuBar;
	}

	/**
	 * This method initializes mnuFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMnuFile() {
		if (mnuFile == null) {
			mnuFile = new JMenu();
			mnuFile.setText("����");
			mnuFile.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
			mnuFile.add(getMnuiNew());
			mnuFile.addSeparator();
			mnuFile.add(getMnuiLoad());
			mnuFile.add(getMnuiSave());
			mnuFile.addSeparator();
			mnuFile.add(getMnuiImport());
			mnuFile.add(getMnuiExport());
		}
		return mnuFile;
	}

	/**
	 * This method initializes mnuManage	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMnuManage() {
		if (mnuManage == null) {
			mnuManage = new JMenu();
			mnuManage.setText("����");
			mnuManage.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
			mnuManage.add(getMnuiPlayer());
			mnuManage.add(getMnuiHorse());
			mnuManage.add(getMnuiResult());
		}
		return mnuManage;
	}

	/**
	 * This method initializes mnuQuit	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMnuQuit() {
		if (mnuQuit == null) {
			mnuQuit = new JMenu();
			mnuQuit.setText("������");
			mnuQuit.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
			mnuQuit.add(getMnuiBlink());
			mnuQuit.add(getMnuiExit());
		}
		return mnuQuit;
	}

	/**
	 * This method initializes mnuiNew	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiNew() {
		if (mnuiNew == null) {
			mnuiNew = new JMenuItem();
			mnuiNew.setText("���� �����");
			mnuiNew.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(mnuiNew, "�� ����� �����ϸ� �۾��ϴ� ������ ��� ������ϴ�.\r\n��� �Ͻðڽ��ϱ�?", 
							"���", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if(result == JOptionPane.OK_OPTION) mm.clear();
					
					tableView.refresh();
					listView.refresh();
					docNew.clear();
				}
			});
		}
		return mnuiNew;
	}

	/**
	 * This method initializes mnuiLoad	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiLoad() {
		if (mnuiLoad == null) {
			mnuiLoad = new JMenuItem();
			mnuiLoad.setText("���� �ҷ�����");
			mnuiLoad.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiLoad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String[] filter = {"txt", "xml"};
					File file = enableFileDialog(mnuiLoad.getParent(), "�Է��� ���� ã��", DIALOG_FILE_XML, filter, FileDialog.LOAD);
					HorseRace hr = null;
					if(file == null) return;
					if(file.getName().contains(".txt")){
						hr = txtm.read(file);
					}else if(file.getName().contains(".xml")){
						hr = xmlm.read(file);
					}
					mm.add(hr);
					tableView.refresh();
				}
			});
		}
		return mnuiLoad;
	}

	/**
	 * This method initializes mnuiSave	
	 * 	
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getMnuiSave() {
		if (mnuiSave == null) {
			mnuiSave = new JMenuItem();
			mnuiSave.setText("���� ����");
			mnuiSave.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String[] filter = {"txt", "xml"};
					File file = enableFileDialog(mnuiSave.getParent(), "���Ϸ� �����ϱ�", DIALOG_FILE_XML, filter, FileDialog.SAVE);
					if(file == null) return;
					if(file.getName().contains(".txt")){
						txtm.setData(mm.getPlayers());
						txtm.setData(mm.getHorses());
						txtm.setData(mm.getResults());
						txtm.write(file);
						//System.out.println(mm.getPlayers().)
					}else if(file.getName().contains(".xml")){
						xmlm.setData(mm.getPlayers());
						xmlm.setData
						
						(mm.getHorses());
						xmlm.setData(mm.getResults());
						xmlm.writeWithPictures(file);
					}
				}
			});
		}
		return mnuiSave;
	}

	/**
	 * This method initializes mnuiImport	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiImport() {
		if (mnuiImport == null) {
			mnuiImport = new JMenuItem();
			mnuiImport.setText("���� ���� ��������");
			mnuiImport.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String[] filter = {"hrm"};
					File file = enableFileDialog(mnuiImport.getParent(), "������ ���� ���� ã��", DIALOG_FILE_HRM, filter, FileDialog.LOAD);
					if(file == null) return;
					
					if(mm.load(file) == false) JOptionPane.showConfirmDialog(mnuiImport, "���� �б⿡ ���� �߽��ϴ�.\n������ �߸��Ǿ��� ���ɼ��� �ֽ��ϴ�.");
					
					tableView.refresh();
					docNew.setReady();
				}
			});
			mnuiImport.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		}
		return mnuiImport;
	}

	/**
	 * This method initializes mnuiExport	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiExport() {
		if (mnuiExport == null) {
			mnuiExport = new JMenuItem();
			mnuiExport.setText("���� ���� ��������");
			mnuiExport.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiExport.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String[] filter = {"hrm"};
					File file = enableFileDialog(mnuiExport.getParent(), "���� ���� ��������", DIALOG_FILE_HRM, filter, FileDialog.SAVE);
					if(file == null) return;
					if(file.getName().contains("." + filter[0]) == false){
						file = new File(file.getPath() + "." + filter[0]);
					}
					mm.save(file);
				}
			});
		}
		return mnuiExport;
	}

	/**
	 * This method initializes mnuiPlayer	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiPlayer() {
		if (mnuiPlayer == null) {
			mnuiPlayer = new JMenuItem();
			mnuiPlayer.setText("���� ������");
			mnuiPlayer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiPlayer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mm.setUserSelection(ClassType.PLAYER);
					try{
						searchBar.setAttribute((IMember)mm.getMembers().get(0));
					}catch(IndexOutOfBoundsException ex){}
					setCountText(mm.getPlayers().size());
					tableView.setData(mm.getPlayers());
					listView.setData(mm.getPlayers());
					listView.setViewOption(ListView.VIEW_OPTION_PLAYER);
					docNew.setReady();
				}
			});
		}
		return mnuiPlayer;
	}

	/**
	 * This method initializes mnuiHorse	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiHorse() {
		if (mnuiHorse == null) {
			mnuiHorse = new JMenuItem();
			mnuiHorse.setText("���ָ� ������");
			mnuiHorse.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiHorse.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mm.setUserSelection(ClassType.HORSE);
					try{
						searchBar.setAttribute((IMember)mm.getHorses().get(0));
					}catch(IndexOutOfBoundsException ex){}
					tableView.setData(mm.getHorses());
					listView.setData(mm.getHorses());
					listView.setViewOption(ListView.VIEW_OPTION_HORSE);
					setCountText(mm.getHorses().size());
					docNew.setReady();
				}
			});
		}
		return mnuiHorse;
	}

	/**
	 * This method initializes mnuiResult	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiResult() {
		if (mnuiResult == null) {
			mnuiResult = new JMenuItem();
			mnuiResult.setText("���� ��� ������");
			mnuiResult.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiResult.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mm.setUserSelection(ClassType.RESULT);
					try{
						searchBar.setAttribute((IMember)mm.getResults().get(0));
					}catch(IndexOutOfBoundsException ex){}
					tableView.setData(mm.getResults());
					listView.setData(mm.getResults());
					listView.setViewOption(ListView.VIEW_OPTION_RESULT);
					setCountText(mm.getResults().size());
					docNew.setReady();
				}
			});
		}
		return mnuiResult;
	}

	/**
	 * This method initializes mnuiBlink	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiBlink() {
		if (mnuiBlink == null) {
			mnuiBlink = new JMenuItem();
			mnuiBlink.setText("������");
			mnuiBlink.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiBlink.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(mnuiBlink, "������ : Team Blink - 1st members\n[�泲�������ش��� - ���ͳ���������� 2�г�]\n������::����\n�赵��::����������\n������::����\n�̵���::����", ":: Team Blink ::", JOptionPane.OK_OPTION);
				}
			});
		}
		return mnuiBlink;
	}

	/**
	 * This method initializes mnuiExit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiExit() {
		if (mnuiExit == null) {
			mnuiExit = new JMenuItem();
			mnuiExit.setText("����");
			mnuiExit.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			mnuiExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exit();
				}
			});
		}
		return mnuiExit;
	}

	/**
	 * This method initializes tabPan	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabPan() {
		if (tabPan == null) {
			tabPan = new JTabbedPane();
			tabPan.setName("tabPan");
			tabPan.addTab("ǥ ����", null, getTableView(), null);
			tabPan.addTab("��� ����", null, getListView(), null);
			tabPan.addTab("�� ������ �߰�", null, getScrlNew(), null);
		}
		return tabPan;
	}

	/**
	 * This method initializes tableView	
	 * 	
	 * @return hr.TableView	
	 */
	private TableView getTableView() {
		if (tableView == null) {
			tableView = new TableView(getListView(), getTxtCount());
		}
		return tableView;
	}

	/**
	 * This method initializes mm	
	 * 	
	 * @return hr.MemberManager	
	 */
	private MemberManager getMm() {
		if (mm == null) {
			mm = new MemberManager();
		}
		return mm;
	}

	/**
	 * This method initializes conPan	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getConPan() {
		if (conPan == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(1);
			borderLayout.setVgap(1);
			conPan = new JPanel();
			conPan.setLayout(borderLayout);
			conPan.add(getSearchBar(), BorderLayout.NORTH);
			conPan.add(getTabPan(), BorderLayout.CENTER);
			conPan.add(getCountPan(), BorderLayout.SOUTH);
		}
		return conPan;
	}

	/**
	 * This method initializes searchBar	
	 * 	
	 * @return hr.SearchBar	
	 */
	private SearchBar getSearchBar() {
		if (searchBar == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(1);
			flowLayout.setHgap(1);
			searchBar = new SearchBar(getTableView(), getListView(), getTxtCount());
			searchBar.setLayout(flowLayout);
			searchBar.setName("searchBar");
		}
		return searchBar;
	}

	/**
	 * This method initializes countPan	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCountPan() {
		if (countPan == null) {
			lblCount = new JLabel();
			lblCount.setText("�˻� ��� : ");
			lblCount.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			countPan = new JPanel();
			countPan.setLayout(new FlowLayout());
			countPan.add(lblCount, null);
			countPan.add(getTxtCount(), null);
		}
		return countPan;
	}

	/**
	 * This method initializes txtCount	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCount() {
		if (txtCount == null) {
			txtCount = new JTextField();
			txtCount.setText("0 ��");
			txtCount.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			txtCount.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			txtCount.setBackground(new Color(238, 238, 238));
			txtCount.setEditable(false);
			txtCount.setColumns(5);
		}
		return txtCount;
	}
	
	public static File enableFileDialog(Component parent, String title, String filterDesc, String[] filter, int dialogType){
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(title);
		fc.setDialogType(dialogType);
		fc.setSize(DIALOG_SIZE_WIDTH, DIALOG_SIZE_HEIGHT);
		fc.setFileFilter(new FileNameExtensionFilter(filterDesc, filter));
		String buttonLabel = (dialogType == FileDialog.LOAD)? "�ҷ�����" : "����";
		fc.showDialog(parent, buttonLabel);
		return fc.getSelectedFile();/*fDialog.getDirectory() + fDialog.getFile();*/
	}
	protected void setCountText(int value){
		getTxtCount().setText(value + " ��");
	}

	/**
	 * This method initializes listView	
	 * 	
	 * @return hr.ListView	
	 */
	private ListView getListView() {
		if (listView == null) {
			listView = new ListView();
			listView.setName("");
		}
		return listView;
	}

	/**
	 * This method initializes scrlNew	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrlNew() {
		if (scrlNew == null) {
			scrlNew = new JScrollPane();
			scrlNew.setName("");
			scrlNew.setViewportView(getDocNew());
		}
		return scrlNew;
	}

	/**
	 * This method initializes docNew	
	 * 	
	 * @return hr.DocumentView	
	 */
	private DocumentView getDocNew() {
		if (docNew == null) {
			docNew = new DocumentView(DocumentView.MODE_EDIT);
			docNew.setTableView(getTableView());
		}
		return docNew;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		exit();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
	
	public void exit(){
		int result = JOptionPane.showConfirmDialog(mnuiNew, "���� �Ͻʴϱ�?\n����� �۾��Ͻô� ������ �ڵ����� ���� �˴ϴ� ^^", 
				"����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(result == JOptionPane.OK_OPTION){
			mm.save();
			System.exit(0);
		}
		return;
	}

}  //  @jve:decl-index=0:visual-constraint="1,12"
