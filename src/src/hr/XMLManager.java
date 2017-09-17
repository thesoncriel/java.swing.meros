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
 * 객체를 XML로 (Marshalling) 혹은 XML을 객체로 (Unmarshalling) 손쉽게 변환하기 위해 만들어진 클래스 입니다.<br>
 * javax.xml.bind의 JAXB를 이용하여 구현 하였습니다 :)
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
	 * 생성자. HorseRace타입인 hr을 새로 생성하고 JAXBContext타입인 jc를 초기화 한다.
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
	 * 생성자. HorseRace타입인 hr을 새로 생성하고 JAXBContext타입인 jc를 초기화 한다.<br>
	 * XMLManager 생성시 HorseRace 데이터를 Vector 데이터로 채우고자 할 때 사용.
	 * @param vec :
	 * {@link Vector} - 채우고 싶은 데이터가 있는 Vector
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
			System.out.println("변수 imbr의 형식이 잘못 되었습니다 ㅠㅠ");
		}
	}
	/**
	 * 
	 * Vector를 HorseRace 형태로 변환하고자 할 때 쓰임.
	 * Vector의 데이터는 그냥 XML로 바로 전환되지 못한다.<br>
	 * 따라서 JAXB에 알맞은 데이터형으로 바꿔줄 필요가 있다.<br>
	 * @param vec :
	 * {@link Vector} - 변환하고자 하는 벡터
	 * @return
	 * {@link HorseRace} - 변환된 데이터
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
	 * XMLManager가 갖고 있는 HorseRace 데이터를 가지고자 할 때 쓰임.
	 * @return
	 * {@link HorseRace} - 받는 데이터 'ㅅ';;
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
	 * XMLManager가 갖고 있는 HorseRace 데이터 중 경주선수(Player)에 관한 데이터를 List 형태로 받고자 할 때 쓰임
	 * @return
	 * {@link List} - {@link Player}타입으로 뭉쳐 있음
	 */
	public List<Player> getPlayers(){
		return hr.getBody().getPlayers().getPlayer();
	}
	/**
	 * XMLManager가 갖고 있는 HorseRace 데이터 중 경주말(Horse)에 관한 데이터를 List 형태로 받고자 할 때 쓰임
	 * @return
	 * {@link List} - {@link Horse}타입으로 뭉쳐 있음
	 */
	public List<Horse> getHorses(){
		return hr.getBody().getHorses().getHorse();
	}
	/**
	 * XMLManager가 갖고 있는 HorseRace 데이터 중 경주 결과(Result)에 관한 데이터를 List 형태로 받고자 할 때 쓰임
	 * @return
	 * {@link List} - {@link Result}타입으로 뭉쳐 있음
	 */
	public List<Result> getResults(){
		return hr.getBody().getResults().getResult();
	}
	/**
	 * XML파일을 객체로 읽어오는 메서드.
	 * XML파일을 클라이언트의 파일 시스템에서 읽어오고자 할 때 쓰임.<br>
	 * 파일경로를 문자열 형태로 지정.
	 * @param filePath
	 * {@link String} - 파일 경로
	 * @return
	 * {@link HorseRace} - 반환 형태
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
			System.out.println("널포인터");
			return null;//TODO 음 -_-
		}
		catch(JAXBException e){
			System.out.println("JAXB오류");
			return null;
		}
	}
	
	/**
	 * 객체를 XML로 변환하여 저장하고자 할 때 쓰이는 메서드.<br>
	 * 저장되는 객체는 XMLManager에 이미 설정되어 있는 것을 사용하게 된다.
	 * @param filePath :
	 * {@link String} - 저장하고픈 경로
	 * @return
	 * {@link boolean} - 저장이 잘되었으면 true, 문제가 있었다면 false.
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
			//System.out.println("체크1");
			mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//System.out.println("체크2" + new File(filePath).getParentFile().exists());
			FileOutputStream fos = new FileOutputStream(file);
			mar.marshal(hr, fos);
			fos.close();
			//System.out.println("체크3");
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {}
		finally{
			return false;
		}
	}
	/**
	 * Player와 Horse의 이미지 경로 데이터를 변경한다.<br>
	 * 웹에서 쓸 상대경로로 바꾸며  xml이 저장될(혹은 저장된) 폴더를 기준으로 적용된다.<br>
	 * 
	 * @param subDir : subDir은 자식 폴더의 이름이 된다.<br>
	 * 만약 subDir 값 마지막에 /나 \\가 없다면 뒤에 덧붙이고<br>
	 * 있다면 그대로 쓰고 각 클래스 id 값에 이미지 확장자를 붙인 형태로 새로 적용시킨다.
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
	 * 이전에 XML로 저장할 때 생성했던 이미지의 경로 데이터가 상대경로로 지정되어 있을 때<br>
	 * 다시 절대경로로 바꿔준다.<br>
	 * @param file : 불러온 XML파일 객체. 
	 * @param hr : 적용시키고픈 데이터가 포함된 객체.
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
	 * 내보내진 XML파일과 함께 첨부하여 일반적인 문서 양식으로 볼 수 있게 해주는 xsl파일을 저장한다.<br>
	 * xsl 파일은 xml 파일이 저장된 곳과 동일한 경로에 저장된다.
	 * @return
	 * {@link boolean} - 저장이 잘되었으면 true, 문제가 있었다면 false.
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
			//System.out.println(XMLPath + XSLFileName + " - XSL저장성공");
			return true;
		}
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
		finally {return false;}
	}
	
	
	/**
	 * XML 저장 경로를 세팅한다.<br>
	 * exportXSL() 또는 exportPicture() 메서드를 사용하기 전 미리 써줘야할 기능이다.
	 * @param filePath :
	 * {@link String} - 적용시키고 싶은 파일의 경로
	 */
	public void checkDir(File file){
		this.makeDir(file);
		if(file.getPath().contains(".")){
			this.makeDir(file);
			//System.out.println(file.getParent() + "파일 포함" + XMLPath);
		}else{
			//System.out.println("뭐임");
			this.makeDir(file);
		}
	}
	/**
	 * 디렉토리의 존재 유무를 확인한다. 만약 없다면, 상위 디렉토리 존재 유무를 확인,
	 * 그래도 없다면 그것을 만들고, 있다면 자식 디렉토리를 만든다.
	 * @param filePath :
	 * {@link String} - 적용하고 싶은 디렉토리의 경로
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
			System.out.println(file.getName() + " 파일을 찾을 수 없다네요 ㅠ.ㅠ 확인점..");
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
		"			font-family:맑은 고딕, 굴림;\r\n"+
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
		"			font-family:맑은 고딕, 굴림;"+
		"			font-size:15px;"+
		"			text-align:right;"+
		"		}"+
		"		.fieldValue{"+
		"			width:190px;"+
		"			font-style:italic;"+
		"			font-family:맑은 고딕, 굴림;"+
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
		"	<div class=\"title\">:::: 문서 보기 ::::</div>";
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
		"		<span class=\"fieldName\">선수 식별 번호 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">이름 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/name\"/></span><br/>"+
		"		<span class=\"fieldName\">나이 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/age\"/></span><br/>"+
		"		<span class=\"fieldName\">성별 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">신장(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/height\"/></span><br/>"+
		"		<span class=\"fieldName\">몸무게(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/players/player/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">경주 파트너 :</span>"+
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
		"		<span class=\"fieldName\">말 식별 번호 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/@id\"/></span><br/>"+
		"		<span class=\"fieldName\">이름 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/name\"/></span><br/>"+
		"		<span class=\"fieldName\">나이 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/age\"/></span><br/>"+
		"		<span class=\"fieldName\">성별 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/gender\"/></span><br/>"+
		"		<span class=\"fieldName\">신장(m) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/height\"/></span><br/>"+
		"		<span class=\"fieldName\">몸무게(kg) :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/weight\"/></span><br/>"+
		"		<span class=\"fieldName\">별명 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/nick\"/></span><br/>"+
		"		<span class=\"fieldName\">말 종류 :</span>"+
		"		<span class=\"fieldValue\"><xsl:value-of select=\"body/horses/horse/type\"/></span><br/>";
}