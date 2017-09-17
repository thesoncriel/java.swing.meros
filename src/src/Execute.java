
import hr.*;
/*
class DOMTraverse{
	static boolean setValidation = false;
	static String uri;
	Document doc;
	
	public DOMTraverse(String uri, boolean validation){
		try{
			this.setValidation = validation;
			this.uri = uri;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(uri);
			traverse(doc.getDocumentElement(), " ");
		}
		catch(FactoryConfigurationError e){}
		catch(ParserConfigurationException e){}
		catch(SAXException e){}
		catch(IOException e){}
	}
	
	public void traverse(Node node, String indent){
		if (node == null) return;
		
		int type = node.getNodeType();
		
		switch(type){
		case Node.DOCUMENT_NODE:
			System.out.println(indent + "[Document] " + node.getNodeName());
			break;
		case Node.ENTITY_NODE:
			System.out.println(indent + "[ENTITY] " + node.getNodeName());
			break;
		case Node.ELEMENT_NODE:
			System.out.println(indent + "[Element] " + node.getNodeName());
			break;
		case Node.ENTITY_REFERENCE_NODE:
			System.out.println(indent + "[ENTITY_REFERENCE] " + node.getNodeName());
			break;
		case Node.CDATA_SECTION_NODE:
			System.out.print(indent + "[CDATA_SECTION] ");
			System.out.print(node.getNodeName());
			System.out.println(" " + node.getNodeValue());
			break;
		case Node.COMMENT_NODE:
			System.out.print(indent + "[COMMENT] ");
			System.out.print(node.getNodeName());
			System.out.println(" " + node.getNodeValue());
			break;
		case Node.TEXT_NODE:
			System.out.print(indent + "[TEXT] ");
			System.out.print(node.getNodeName());
			System.out.println(" " + node.getNodeValue());
			break;
		}
		
		NodeList children = node.getChildNodes();
		if(children != null){
			int len = children.getLength();
			for(int i = 0; i < len; i++){
				traverse(children.item(i), indent + " ");
			}
		}
	}
	
	public String getNodeValue(String nodeName, int index){
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse("C:/login.xml");
		}
		catch(FactoryConfigurationError e){}
		catch(ParserConfigurationException e){}
		catch(SAXException e){}
		catch(IOException e){}
		
		Node node = doc.getDocumentElement();
		NodeList list = doc.getElementsByTagName(nodeName);
		
		for(int i = 0; i < list.getLength(); i++){
			System.out.println(list.item(i).getTextContent());
		}
		
		return list.item(index).getNodeValue();
	}
}


/*
class TestForm extends JFrame{
	public TestForm(Member mbr){
		Container ct = this.getContentPane();
		//JPanel pan = new JPanel();
		JButton btn = new JButton(new ImageIcon(mbr.getPicURL()));
		ct.add(btn);
		ct.setLayout(null);
		btn.setSize(200, 200);
		btn.setLocation(0,0);
		this.setSize(400,400);
		this.setLocation(0,0);
		this.setVisible(true);
	}
}
*/
public class Execute {
	public static void main(String[] args){
		
		MainForm frmMain = new MainForm();
		frmMain.setVisible(true);
		/*
		Horse h = new Horse("2090-3", "오새", 32, false, 201.5, 92.3, "C:/00works/My Pictures/25289_11257511520.jpg", "오새", HorseType.ARABIAN);
		Object obj = h;
		IMember im = (IMember)obj;
		Horse h2 = (Horse)im;
		
		System.out.println(h2.getNick());
		for(String str: im.toStrings()){
			System.out.println(str);
		}
		//XMLManager mm = new XMLManager();
		/*
		DatasetManager dm = new DatasetManager(132, new Integer(32), new Double(10.1), new Double(11.2));
		dm.add(133, new Integer(30), new Double(10.2), new Double(11.3));
		dm.add(132, new Integer(32), new Double(10.1), new Double(11.2));
		dm.add(132, new Integer(32), new Double(10.1), new Double(35.7));
		dm.add(132, new Integer(32), new Double(10.1), new Double(75.2));
		//System.out.println("=" + (11.2 + 11.2 + 35.7 + 75.2)/4.0);
		for(String str: dm.getTuple(1)){
			System.out.println(str);
			//System.out.println(dm.getTuple().length);
		}
		/*dm.setAverage(0);
		for(String str: dm.getTuple(0)){
			System.out.println(str);
			//System.out.println(dm.getTuple().length);
		}
		*/
		//GraphMaker gm = new GraphMaker();
		/*
		//URL url = new URL("file:///c:/haha.jpg");
		//xmlm.getPlayer().add(new Player("3020", "호호걸", 32, true, 170.5, 71.3, "file:///c:/haha.gif", "020513-698-2"));
		//xmlm.getPlayer().add(new Player("3030", "차차맨", 32, false, 170.5, 71.3, "file:///c:/menubg.gif", "020513-698-4"));
		//xmlm.getResult().add(new Result("3020", "2010-03-22", 54.3, 2));
		//System.out.println(xmlm.read("C:/hoho.xml").getPlayer(0).getName());
		//xmlm.setXMLPath("C:/windows/hh.exe");
		//System.out.println(xmlm.getXMLPath());
		
		//IMember imbr = xmlm.getPlayer().get(0);
		//System.out.println(imbr.getClass() == Player.class);
		
		MemberManager mm = new MemberManager();
		mm.add(new Result("3040", "2009-10-10", 34.22, 3));
		mm.add(new Result("3040", "2009-10-11", 23.12, 4));
		mm.add(new Result("3040", "2009-10-12", 10.99, 2));
		mm.add(new Result("3051", "2009-10-10", 22.87, 1));
		mm.add(new Result("3051", "2009-10-11", 39.28, 2));
		mm.add(new Result("3051", "2009-10-12", 38.83, 3));
		mm.add(new Player("3040", "이철원", 34, true, 174.2, 70.6, "C:/00works/My Pictures/friends.jpg", "2090-3"));
		mm.add(new Player("3051", "통태지", 32, false, 169.5, 67.3, "C:/00works/My Pictures/coba.jpg", "2090-5"));
		mm.add(new Horse("2090-3", "오새", 32, false, 201.5, 92.3, "C:/00works/My Pictures/25289_11257511520.jpg", "오새", HorseType.ARABIAN));
		mm.add(new Horse("2090-5", "충성이", 32, false, 203.5, 91.4, "C:/00works/My Pictures/1265651166211cnddyd_0.jpg", "충심", HorseType.LIPIZZANER));
		
		//HorseRace hr = XMLManager.();
		//hr.setPlayer(mm.getPlayers());
		//hr.setHorse(mm.getHorses());
		//hr.setResult(mm.getResults());
		
		XMLManager xmlm = new XMLManager();
		xmlm.getHorseRace().setPlayer(mm.getPlayers());
		xmlm.getHorseRace().setHorse(mm.getHorses());
		xmlm.getHorseRace().setResult(mm.getResults());
		System.out.println(xmlm.getHorseRace().getPlayer(1).getHId());
		xmlm.write("C:/gogo.xml");
		mm.setRacers();
		//System.out.println(mm.getRacers().get(0).getId());
		//Iterator<Racer> it = mm.getRacersByAvgBetRate(25, 4).iterator();
		/*Iterator<IMember> it = mm.getIMembersById(((Player)mm.getMembersByName("이철원", Player.class).get(0)).getHId(), Horse.class).iterator();
		while(it.hasNext()){
			Horse r = (Horse)it.next();
			//System.out.println(r.getId() + " = " + r.getAvgBetRate());
			System.out.println(r.getName());
		}
		
		//xmlm.setXMLPath("C:/mv/");
		//xmlm.exportPicture();
		
		xmlm.write("C:/zz/char.xml");
		xmlm.exportXSL();
		int[] rank = new int[10];
		rank[0] = 1;
		rank[1] = 3;
		rank[2] = 6;
		rank[3] = 2;
		rank[4] = 1;
		rank[5] = 13;
		rank[6] = 9;
		rank[7] = 8;
		rank[8] = 4;
		rank[9] = 2;
		for(int i = 0; i < rank.length; i++){
			rank[i] = 2;
		}
		double sum = 0; 
		//tmp += 100 - (m.getRank()-1) * (100/13);
		for(int n: rank){
			sum += n;
		}
		System.out.println(100.0 - (sum/rank.length - 1) * (100.0/13.0));
		
		sum = 0; 
		//tmp += 100 - (m.getRank()-1) * (100/13);
		for(int n: rank){
			sum += 100.0 - (n - 1) * (100.0/13.0);
		}
		System.out.println(sum/rank.length);*/
		//System.out.println(mm.compareDouble(23.4444444443, 23.4444444443, 2));
		/*try{
			String argv = "C:/ex9-2.xml";
			DOMTraverse dom = new DOMTraverse(argv, true);
			dom.getNodeValue("name", 0);
			//dom.setValidation = true;
			//dom.uri = argv;
			
			XMLManager xmlm = new XMLManager();
			Player[] p = new Player[2];
			Canvas can = new Canvas();
			URL url;
			url = new URL("file:///c:/haha.jpg");
			
			p[0] = new Player("3020", "호호걸", 32, true, 170.5, 71.3, url.getPath(), "020513-698-2");

			TestForm frm = new TestForm(p[0]);
			
			JAXBContext jc = JAXBContext.newInstance("hr");
			
			Marshaller mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//mar.marshal(p[0], new FileOutputStream("C:/kaka.xml"));
			//ObjectFactory fac = new ObjectFactory();
			HorseRace hor = new HorseRace();
			hor.setBody(new Body());
			hor.getBody().setPlayers(new Players());
			hor.getBody().getPlayers().add(p[0]);
			mar.marshal(hor, new FileOutputStream("C:/kaka_2.xml"));
			
			System.out.println("성공?");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("무야 ㅠㅠ");
		}
		catch(JAXBException e){
			e.printStackTrace();
			System.out.println("뭥미");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}*/
	}
}
