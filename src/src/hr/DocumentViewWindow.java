package hr;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import org.jfree.chart.JFreeChart;
import member.IMember;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DocumentViewWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private DocumentView documentView = null;
	private MemberManager mm = null;
	private JScrollPane scrlPan = null;
	private JPanel docPan = null;
	private JButton jButton = null;
	private JMenuBar menuBar = null;
	private JMenu mnuFile = null;
	private JMenuItem mnuiText = null;
	private JMenuItem mnuiXML = null;
	/**
	 * @param owner
	 */
	public DocumentViewWindow(Frame owner) {
		super(owner, true);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(530, 700);
		this.setJMenuBar(getMenuBar());
		this.setPreferredSize(new Dimension(311, 1));
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.setTitle("::::: 문서 보기 :::::");
		this.setModal(true);
		this.setContentPane(getJContentPane());
		this.mm = new MemberManager();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setAutoscrolls(true);
			jContentPane.add(getScrlPan(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes documentView	
	 * 	
	 * @return hr.DocumentView	
	 */
	private DocumentView getDocumentView() {
		if (documentView == null) {
			documentView = new DocumentView(DocumentView.MODE_DOCUMENT);
			documentView.setPreferredSize(new Dimension(17, 17));
			documentView.setName("documentView");
		}
		return documentView;
	}
	
	public void setData(IMember imbr){
		documentView.setData(imbr);
		if(mm.getUserSelection() == ClassType.PLAYER){
			List<IMember> list = mm.getMembers(0, 0, imbr.getId(), ClassType.RESULT);
			MemberModel mbrModel = new MemberModel(list);
			documentView.setGraphData(mbrModel.getDataset(2, "선수"),"베팅률 변화","시간","베팅률",GraphMaker.CHART_LINE);
			documentView.setGraphData(mbrModel.getDataset(3, "선수"),"승률 변화","시간","승률",GraphMaker.CHART_BAR);
		}
		System.out.println(documentView.getHeight());
	}

	/**
	 * This method initializes scrlPan	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrlPan() {
		if (scrlPan == null) {
			scrlPan = new JScrollPane();
			scrlPan.setViewportView(getDocPan());
		}
		return scrlPan;
	}

	/**
	 * This method initializes docPan	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDocPan() {
		if (docPan == null) {
			docPan = new JPanel();
			docPan.setLayout(new BorderLayout());
			docPan.add(getDocumentView(), BorderLayout.CENTER);
			docPan.add(getJButton(), BorderLayout.SOUTH);
		}
		return docPan;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
		}
		return jButton;
	}

	/**
	 * This method initializes menuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnuFile());
		}
		return menuBar;
	}

	/**
	 * This method initializes mnuFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMnuFile() {
		if (mnuFile == null) {
			mnuFile = new JMenu();
			mnuFile.setText("파일");
			mnuFile.add(getMnuiText());
			mnuFile.add(getMnuiXML());
		}
		return mnuFile;
	}

	/**
	 * This method initializes mnuiText	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiText() {
		if (mnuiText == null) {
			mnuiText = new JMenuItem();
			mnuiText.setText("텍스트로 저장");
			mnuiText.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TextManager txtm = new TextManager(documentView.getData());
					GraphMaker gm = new GraphMaker();
					String[] filter = {"txt"};//"텍스트 문서(*.txt), XML 문서(*.xml)";
					File file = MainForm.enableFileDialog(menuBar.getParent(), "파일로 저장하기", "Text 문서(*.txt)", filter, FileDialog.SAVE);
					if(file == null) return;
					if(file.getName().contains(".txt") == false){
						file = new File(file.getAbsolutePath() + ".txt");
						System.out.println(file.getAbsolutePath());
					}
					txtm.writeWithPictures(file);
					JFreeChart[] chart = documentView.getChart();
					if(chart != null){
						for(int i = 0; i < chart.length; i++){
							gm.setChart(chart[i]);
							gm.saveChart(file.getParent() + "/pic/chart" + i + ".png");
						}
					}
				}
			});
		}
		return mnuiText;
	}

	/**
	 * This method initializes mnuiXML	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMnuiXML() {
		if (mnuiXML == null) {
			mnuiXML = new JMenuItem();
			mnuiXML.setText("XML로 저장");
			mnuiXML.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					XMLManager xmlm = new XMLManager(documentView.getData());
					GraphMaker gm = new GraphMaker();
					String[] filter = {"xml"};//"텍스트 문서(*.txt), XML 문서(*.xml)";
					File file = MainForm.enableFileDialog(menuBar.getParent(), "파일로 저장하기", "XML 문서(*.xml)", filter, FileDialog.SAVE);
					if(file == null) return;
					if(file.getName().contains(".xml") == false){
						file = new File(file.getAbsolutePath() + ".xml");
						System.out.println(file.getAbsolutePath());
					}
					xmlm.writeWithPictures(file);
					if(mm.getUserSelection() != ClassType.RESULT) xmlm.exportXSL(mm.getUserSelection());
					JFreeChart[] chart = documentView.getChart();
					if(chart != null){
						for(int i = 0; i < chart.length; i++){
							gm.setChart(chart[i]);
							gm.saveChart(file.getParent() + "/pic/chart" + i + ".png");
						}
					}
					System.out.println("XML 저장 성공"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return mnuiXML;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
