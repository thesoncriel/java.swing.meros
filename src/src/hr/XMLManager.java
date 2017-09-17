package hr;
import hr.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import member.IMember;
import member.Member;
import member.Horse;
import member.HorseRace;
import member.Player;
import member.Result;

/**
 * <p>HorseRace Edition v1.0</p>
 * <p>
 * ��ü�� XML�� (Marshalling) Ȥ�� XML�� ��ü�� (Unmarshalling) �ս��� ��ȯ�ϱ� ���� ������� Ŭ���� �Դϴ�.<br>
 * javax.xml.bind�� JAXB�� �̿��Ͽ� ���� �Ͽ����ϴ� :)
 * </p>
 * @author TheSON
 * 
 * @see {@link JAXBContext}, {@link Marshaller}, {@link Unmarshaller}
 * 
 * 
 *
 */
public class XMLManager{
	private String XSLFileName = "/hr_form.xsl";
	//private String XMLPath;
	private File XMLFile;
	private HorseRace hr;
	private JAXBContext jc;
	public static final String DEFAULT_PATH = "C:/hr.xml";
	public static final String SUB_FOLDER_NAME = "pic";
	
	/**
	 * ������. HorseRaceŸ���� hr�� ���� �����ϰ� JAXBContextŸ���� jc�� �ʱ�ȭ �Ѵ�.
	 */
	public XMLManager(){
		this.hr = new HorseRace();
		this.hr.createChilds();
		try{
			jc = JAXBContext.newInstance("member");
		}catch(JAXBException e){
			jc = null;
		}
	}
	/**
	 * ������. HorseRaceŸ���� hr�� ���� �����ϰ� JAXBContextŸ���� jc�� �ʱ�ȭ �Ѵ�.<br>
	 * XMLManager ������ HorseRace �����͸� Vector �����ͷ� ä����� �� �� ���.
	 * @param vec :
	 * {@link Vector} - ä��� ���� �����Ͱ� �ִ� Vector
	 */
	public XMLManager(HorseRace hr){
		this.hr = hr.clone();
		try{
			jc = JAXBContext.newInstance("member");
		}catch(JAXBException e){
			jc = null;
		}
	}
	public XMLManager(IMember imbr){
		this();
		Class cls = imbr.getClass();
		if(cls == Player.class){
			hr.getBody().getPlayers().getPlayer().add((Player)imbr.clone());
		}
		else if(cls == Horse.class){
			hr.getBody().getHorses().getHorse().add((Horse)imbr.clone());
		}
		else if(cls == Result.class){
			hr.getBody().getResults().getResult().add((Result)imbr.clone());
		}
		else{
			System.out.println("���� imbr�� ������ �߸� �Ǿ����ϴ� �Ф�");
		}
	}
	/**
	 * 
	 * Vector�� HorseRace ���·� ��ȯ�ϰ��� �� �� ����.
	 * Vector�� �����ʹ� �׳� XML�� �ٷ� ��ȯ���� ���Ѵ�.<br>
	 * ���� JAXB�� �˸��� ������������ �ٲ��� �ʿ䰡 �ִ�.<br>
	 * @param vec :
	 * {@link Vector} - ��ȯ�ϰ��� �ϴ� ����
	 * @return
	 * {@link HorseRace} - ��ȯ�� ������
	 */
	public HorseRace toHorseRace(List<Player> list1, List<Horse> list2, List<Result> list3){
		HorseRace hr = new HorseRace();
		hr.createChilds();
		hr.getBody().getPlayers().setPlayer(list1);
		hr.getBody().getHorses().setHorse(list2);
		hr.getBody().getResults().setResult(list3);

		return hr;
	}
	/**
	 * XMLManager�� ���� �ִ� HorseRace �����͸� �������� �� �� ����.
	 * @return
	 * {@link HorseRace} - �޴� ������ '��';;
	 */
	public HorseRace getHorseRace(){
		return hr;
	}
	public void setData(List list){
		Class cls = list.get(0).getClass();
		if(cls == Player.class){
			hr.setPlayer(list);
		}
		else if(cls == Horse.class){
			hr.setHorse(list);
		}
		else if(cls == Result.class){
			hr.setResult(list);
		}
	}
	/**
	 * XMLManager�� ���� �ִ� HorseRace ������ �� ���ּ���(Player)�� ���� �����͸� List ���·� �ް��� �� �� ����
	 * @return
	 * {@link List} - {@link Player}Ÿ������ ���� ����
	 */
	public List<Player> getPlayers(){
		return hr.getBody().getPlayers().getPlayer();
	}
	/**
	 * XMLManager�� ���� �ִ� HorseRace ������ �� ���ָ�(Horse)�� ���� �����͸� List ���·� �ް��� �� �� ����
	 * @return
	 * {@link List} - {@link Horse}Ÿ������ ���� ����
	 */
	public List<Horse> getHorses(){
		return hr.getBody().getHorses().getHorse();
	}
	/**
	 * XMLManager�� ���� �ִ� HorseRace ������ �� ���� ���(Result)�� ���� �����͸� List ���·� �ް��� �� �� ����
	 * @return
	 * {@link List} - {@link Result}Ÿ������ ���� ����
	 */
	public List<Result> getResults(){
		return hr.getBody().getResults().getResult();
	}
	/**
	 * XML������ ��ü�� �о���� �޼���.
	 * XML������ Ŭ���̾�Ʈ�� ���� �ý��ۿ��� �о������ �� �� ����.<br>
	 * ���ϰ�θ� ���ڿ� ���·� ����.
	 * @param filePath
	 * {@link String} - ���� ���
	 * @return
	 * {@link HorseRace} - ��ȯ ����
	 */
	public HorseRace read(String filePath){
		try{
			return this.read(new File(filePath));
		}catch(Exception e){
			return null;
		}
	}
	public HorseRace read(File file){
		try{
			Unmarshaller umar = jc.createUnmarshaller();
			hr = (HorseRace)umar.unmarshal(file);

			return this.fixPicURL(file, hr);
		}
		catch(NullPointerException e){
			System.out.println("��������");
			return null;//TODO �� -_-
		}
		catch(JAXBException e){
			System.out.println("JAXB����");
			return null;
		}
	}
	
	/**
	 * ��ü�� XML�� ��ȯ�Ͽ� �����ϰ��� �� �� ���̴� �޼���.<br>
	 * ����Ǵ� ��ü�� XMLManager�� �̹� �����Ǿ� �ִ� ���� ����ϰ� �ȴ�.
	 * @param filePath :
	 * {@link String} - �����ϰ��� ���
	 * @return
	 * {@link boolean} - ������ �ߵǾ����� true, ������ �־��ٸ� false.
	 */
	public boolean write(String filePath){
		return this.write(new File(filePath));
	}
	public boolean write(File file){
		this.checkDir(file);
		return this.writeCommon(this.hr, file);
	}
	public boolean writeWithPictures(String filePath){
		return this.writeWithPictures(new File(filePath));
	}
	public boolean writeWithPictures(File file){
		this.checkDir(file);
		//System.out.println(((Player)this.hr.getPlayers().get(0)).getPicURL());
		ImageSaver is = new ImageSaver(this.hr, file.getParent());
		
		is.write();
		this.modPicURL(this.SUB_FOLDER_NAME);
		return this.writeCommon(this.hr, file);
	}
	protected boolean writeCommon(HorseRace hr, File file){
		Marshaller mar = null;
		XMLFile = file;
		try {
			//System.out.println("üũ1");
			mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//System.out.println("üũ2" + new File(filePath).getParentFile().exists());
			FileOutputStream fos = new FileOutputStream(file);
			mar.marshal(hr, fos);
			fos.close();
			//System.out.println("üũ3");
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {}
		finally{
			return false;
		}
	}
	/**
	 * Player�� Horse�� �̹��� ��� �����͸� �����Ѵ�.<br>
	 * ������ �� ����η� �ٲٸ�  xml�� �����(Ȥ�� �����) ������ �������� ����ȴ�.<br>
	 * 
	 * @param subDir : subDir�� �ڽ� ������ �̸��� �ȴ�.<br>
	 * ���� subDir �� �������� /�� \\�� ���ٸ� �ڿ� �����̰�<br>
	 * �ִٸ� �״�� ���� �� Ŭ���� id ���� �̹��� Ȯ���ڸ� ���� ���·� ���� �����Ų��.
	 */
	protected void modPicURL(String subDir){
		List mList1 = this.hr.getPlayers();
		List mList2 = this.hr.getHorses();
		subDir = ( (subDir.indexOf("/") > 0) || subDir.contains("\\"))? subDir : subDir+"/" ;
		for(Object m: mList1){
			Player p = (Player)m;
			p.setPicURL(subDir + p.getId() + ".png");
		}
		for(Object m: mList2){
			Horse h = (Horse)m;
			h.setPicURL(subDir + h.getId() + ".png");
		}
	}
	/**
	 * ������ XML�� ������ �� �����ߴ� �̹����� ��� �����Ͱ� ����η� �����Ǿ� ���� ��<br>
	 * �ٽ� �����η� �ٲ��ش�.<br>
	 * @param file : �ҷ��� XML���� ��ü. 
	 * @param hr : �����Ű���� �����Ͱ� ���Ե� ��ü.
	 * @return
	 */
	protected HorseRace fixPicURL(File file, HorseRace hr){
		List<Player> list = hr.getPlayers();
		for(Player p : list){
			String url = p.getPicURL();
			if(url.startsWith(XMLManager.SUB_FOLDER_NAME))
				url = file.getParent() + "/" + url;
			if(url.contains("://") == false)
				p.setPicURL("file:///" + url);
		}
		List<Horse> list2 = hr.getHorses();
		for(Horse p : list2){
			String url = p.getPicURL();
			if(url.startsWith(XMLManager.SUB_FOLDER_NAME))
				url = file.getParent() + "/" + url;
			if(url.contains("://") == false)
				p.setPicURL("file:///" + url);
		}
		return hr;
	}

	
	/**
	 * �������� XML���ϰ� �Բ� ÷���Ͽ� �Ϲ����� ���� ������� �� �� �ְ� ���ִ� xsl������ �����Ѵ�.<br>
	 * xsl ������ xml ������ ����� ���� ������ ��ο� ����ȴ�.
	 * @return
	 * {@link boolean} - ������ �ߵǾ����� true, ������ �־��ٸ� false.
	 */
	@SuppressWarnings("finally")
	public boolean exportXSL(ClassType classType){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(XMLFile.getParent() + XSLFileName), "UTF8"));
			XSLSample xsl = new XSLSample();
			//System.out.println(xsl.getXSL());
			bw.write(xsl.getXSL(classType));
			xsl.includeXSL(XMLFile);
			bw.close();
			//System.out.println(XMLPath + XSLFileName + " - XSL���强��");
			return true;
		}
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
		finally {return false;}
	}
	
	
	/**
	 * XML ���� ��θ� �����Ѵ�.<br>
	 * exportXSL() �Ǵ� exportPicture() �޼��带 ����ϱ� �� �̸� ������� ����̴�.
	 * @param filePath :
	 * {@link String} - �����Ű�� ���� ������ ���
	 */
	public void checkDir(File file){
		this.makeDir(file);
		if(file.getPath().contains(".")){
			this.makeDir(file);
			//System.out.println(file.getParent() + "���� ����" + XMLPath);
		}else{
			//System.out.println("����");
			this.makeDir(file);
		}
	}
	/**
	 * ���丮�� ���� ������ Ȯ���Ѵ�. ���� ���ٸ�, ���� ���丮 ���� ������ Ȯ��,
	 * �׷��� ���ٸ� �װ��� �����, �ִٸ� �ڽ� ���丮�� �����.
	 * @param filePath :
	 * {@link String} - �����ϰ� ���� ���丮�� ���
	 */
	protected void makeDir(File file){
		if(!file.exists()){
			if(file.getPath().contains(".")){
				File pf = file.getParentFile();
				if(!pf.exists()){
					pf.mkdirs();
				}
			}
			else{
				file.mkdirs();
			}
		}
	}

}

class XSLSample
{
	private StringBuffer xml;
	public XSLSample(){
		xml = new StringBuffer();
	}
	public void includeXSL(File file){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			
			String tmp = "";
			int c = 0;
			while((tmp = br.readLine()) != null){
				xml.append(tmp + "\r\n");
				if(c == 0) xml.append("<?xml:stylesheet type=\"text/xsl\" href=\"hr_form.xsl\"?>");
				c++;
			}
			br.close();
			//xml.insert("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>".length(), );
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
			
			bw.write(xml.toString());
			bw.close();
		}catch(FileNotFoundException ex){
			System.out.println(file.getName() + " ������ ã�� �� ���ٳ׿� ��.�� Ȯ����..");
		}catch(IOException ex){
			System.out.println(ex);
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	public String getXSL(ClassType classType){
		if(classType == ClassType.PLAYER)
			return xslCommon1 + xslPlayer + xslCommon2;
		else
			return xslCommon1 + xslHorse + xslCommon2;
		
	}
	private String xslCommon1 =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+
		"<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\r\n"+
		"<xsl:template match=\"/horseRace\">\r\n"+
		"<html>\r\n"+
		"	<head>\r\n"+
		"		<title>\r\n"+
		"		<xsl:value-of select=\"header/author\"/>\r\n"+
		"	</title>\r\n"+
		"	<style type=\"text/css\">\r\n"+
		"		body{background-color:#eeeeee;}\r\n"+
		"		.title{\r\n"+
		"			font-size:26px;\r\n"+
		"			font-weight:bold;\r\n"+
		"			font-family:���� ���, ����;\r\n"+
		"			letter-spacing:1.2em;\r\n"+
		"		}\r\n"+
		"		.leftPane{\r\n"+
		"			width:200px;\r\n"+
		"			padding-top:2em;\r\n"+
		"			text-align:center;\r\n"+
		"			float:left;\r\n"+
		"		}\r\n"+
		"		.image{\r\n"+
		"			width:150px;\r\n"+
		"			height:200px;\r\n"+
		"			border-color:#41aae0;\r\n"+
		"			border-width:2px;\r\n"+
		"			border-style:solid;\r\n"+
		"		}"+
		"		.rightPane{"+
		"			width:310px;"+
		"			padding-top:30px;"+
		"			text-align:left;"+
		"			float:left;"+
		"			line-height:1.7em;"+
		"		}"+
		"		.fieldName{"+
		"			width:110px;"+
		"			font-style:italic;"+
		"			font-weight:bold;"+
		"			font-family:���� ���, ����;"+
		"			font-size:15px;"+
		"			text-align:right;"+
		"		}"+
		"		.fieldValue{"+
		"			width:190px;"+
		"			font-style:italic;"+
		"			font-family:���� ���, ����;"+
		"			font-size:15px;"+
		"			text-align:left;"+
		"		}"+
		"		.graph{"+
		"			width:250px;"+
		"			height:200px;"+
		"			border-color:#41aa41;"+
		"			border-width:2px;"+
		"			border-style:solid;"+
		"		}"+
		"	</style>"+
		"</head>"+
		"<body>"+
		"	<div class=\"title\">:::: ���� ���� ::::</div>";
	private String xslCommon2 =
		"	</div>"+
		"</body>"+
		"</html>"+
		"</xsl:template>"+
		"</xsl:stylesheet>";
	private String xslPlayer =
		"	<div class=\"leftPane\">"+
		"		<img class=\"image\">"+
		"			<xsl:attribute name=\"src\">"+
		"				<xsl:value-of select=\"body/players/player/picURL\"/>"+
		"			</xsl:attribute>"+
		"		</img>"+
		"	</div>"+
		"	<div class=\"rightPane\">"+
		"		<span class=\"fieldName\">���� �ĺ� ��ȣ :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">�̸� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/name\"/></span><br/>"+
		"		<span class=\"fieldName\">���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/age\"/></span><br/>"+
		"		<span class=\"fieldName\">���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">����(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/height\"/></span><br/>"+
		"		<span class=\"fieldName\">������(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">���� ��Ʈ�� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/hId\"/></span><br/>"+
		"		<br/>"+
		"		<img src=\"pic/chart0.png\" class=\"graph\"></img>"+
		"		<br/>"+
		"		<br/>"+
		"		<img src=\"pic/chart1.png\" class=\"graph\"></img>";
	private String xslHorse =
		"	<div class=\"leftPane\">"+
		"		<img class=\"image\">"+
		"			<xsl:attribute name=\"src\">"+
		"				<xsl:value-of select=\"body/horses/horse/picURL\"/>"+
		"			</xsl:attribute>"+
		"		</img>"+
		"	</div>"+
		"	<div class=\"rightPane\">"+
		"		<span class=\"fieldName\">�� �ĺ� ��ȣ :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">�̸� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/name\"/></span><br/>"+
		"		<span class=\"fieldName\">���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/age\"/></span><br/>"+
		"		<span class=\"fieldName\">���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">����(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/height\"/></span><br/>"+
		"		<span class=\"fieldName\">������(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/nick\"/></span><br/>"+
		"		<span class=\"fieldName\">�� ���� :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/type\"/></span><br/>";
}