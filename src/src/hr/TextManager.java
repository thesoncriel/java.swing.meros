package hr;
import hr.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
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


public class TextManager{
	public static final String DEFAULT_SEPRATOR = ",";
	private HorseRace hr;
	private String seprator;
	public static final String DEFAULT_PATH = "C:/hr.xml";
	public final String SUB_FOLDER_NAME = "pic";

	public TextManager(){
		this.hr = new HorseRace();
		this.hr.createChilds();
		this.seprator = DEFAULT_SEPRATOR;
	}

	public TextManager(HorseRace hr){
		this.hr = hr.clone();
	}
	public TextManager(IMember imbr){
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

	public List<Player> getPlayers(){
		return hr.getBody().getPlayers().getPlayer();
	}

	public List<Horse> getHorses(){
		return hr.getBody().getHorses().getHorse();
	}

	public List<Result> getResults(){
		return hr.getBody().getResults().getResult();
	}
	
	public HorseRace read(String filePath){
		return this.read(new File(filePath));
	}
	public HorseRace read(File file){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			List<String> data = new ArrayList<String>();
			
			String tmp = "";
			while((tmp = br.readLine()) != null){
				data.add(tmp);
			}
			br.close();
			
			for(String str : data){
				Scanner scn = new Scanner(str);
				scn.useDelimiter(seprator);
				List<String> tList = new ArrayList<String>();
				while(scn.hasNext()){
					tList.add(scn.next());
				}
				System.out.println(tList.size());
				switch(tList.size()){
				case 8:
					Player p = new Player();
					p.setData(tList.toArray(new String[0]));
					hr.getPlayers().add(p);
					break;
				case 9:
					Horse h = new Horse();
					h.setData(tList.toArray(new String[0]));
					hr.getHorses().add(h);
					break;
				case 4:
					Result r = new Result();
					r.setData(tList.toArray(new String[0]));
					hr.getResults().add(r);
					break;
				}
			}
			return this.fixPicURL(file, hr);
		}catch(FileNotFoundException e){
			System.out.println(file.getName() + " 파일을 찾을 수 없다네요 ㅠ.ㅠ 확인점..");
		}catch(IOException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println(e);
		}
		return hr;
	}

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
		ImageSaver is = new ImageSaver(this.hr, file.getParent());
		
		is.write();
		this.modPicURL(this.SUB_FOLDER_NAME);
		return this.writeCommon(this.hr, file);
	}
	protected boolean writeCommon(HorseRace hr, File file){
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			StringBuffer sb = null;
			
			for(Object obj : hr.getPlayers()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			for(Object obj : hr.getHorses()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			for(Object obj : hr.getResults()){
				IMember mbr = (IMember)obj;
				sb = new StringBuffer();
				for(String tmp : mbr.toStrings()){
					sb.append(tmp + DEFAULT_SEPRATOR);
				}
				sb.deleteCharAt(sb.length()-1);
				bw.write(sb.toString());
				bw.newLine();
			}
			bw.close();
			return true;
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
		return false;
	}

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