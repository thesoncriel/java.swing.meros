package hr;
import java.io.*;
import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;

import member.Horse;
import member.HorseRace;
import member.HorseType;
import member.IMember;
import member.Player;
import member.Result;



/**
 * <h2>MemberManager - HorseRace Edition ver1.0</h2> �渶 �����͸� �����ϴ� Ŭ����.<br>
 * �پ��� �˻� ����� ������ �ִ�.
 * 
 * @author TheSON -_-v;;
 * @see {@link Player}, {@link Horse}, {@link Result}, {@link Racer},
 *      {@link Vector}, {@link ListArray}, {@link Iterator}
 * 
 */
public class MemberManager {
	public static final String DEFAULT_SAVE_FILE = "horserace.hrm";
	public static final ClassType DEFAULT_USER_SELECTION = ClassType.PLAYER;
	public final String ROW_KEY = "����";
	private static Vector<List> vec = null;
	private static ClassType userSelection = null;

	/**
	 * ������. Vector �ʱ�ȭ ����� ����.
	 */
	@SuppressWarnings("unchecked")
	public MemberManager() {
		if(vec == null) clear();
	}

	/**
	 * ������. vectorPath���� �ش�Ǵ� ����� ������ Vector�� �����Ű�� ����� ����.<br>
	 * ���� �ҷ����⿡ �����ϸ� �ʱ�ȭ�� �õ���.
	 * 
	 * @param vectorPath
	 *            : String - Vector�� �����ų Object������ ���
	 */
	@SuppressWarnings("unchecked")
	public MemberManager(String vectorPath) {
		if (this.load(vectorPath) == false) {
			clear();
		}
	}

	/**
	 * ���� Vector�� ����� �߰� ��Ų��.<br>
	 * IMember �������̽��� ��ӹ޾� ������ Ŭ������� ��� ���� �����ϴ�.<br>
	 * �� �� �� Ŭ�������� �����Ͽ� �˾Ƽ� �� List�� �߰� ��Ų�� :)
	 * 
	 * @param imbr
	 *            : Player - �߰��� ��ü
	 */
	public void add(Player mbr) {
		vec.get(0).add(mbr);
	}
	public void add(Horse mbr){
		vec.get(1).add(mbr);
	}
	public void add(Result mbr){
		vec.get(2).add(mbr);
	}
	public void add(HorseRace hr){
		add(hr.getPlayers());
		add(hr.getHorses());
		add(hr.getResults());
	}
	public void add(List<IMember> list){
		for(IMember mbr: list){
			if(mbr.getClass() == Player.class)
				vec.get(0).add((Player)mbr);
			else if(mbr.getClass() == Horse.class)
				vec.get(1).add((Horse)mbr);
			else if(mbr.getClass() == Result.class)
				vec.get(2).add((Result)mbr);
		}
	}
	

	/**
	 * ���� Vector�� ����� �߰� ��Ų��.<br>
	 * String�� �迭�� �޾� �� ������ �̿��Ͽ� �����͸� �߰� ��Ų��.<br>
	 * �� �� �� Ŭ�������� �����Ͽ� �˾Ƽ� �� List�� �߰� ��Ų�� ;)
	 * 
	 * @param str
	 *            : String[] - �߰��ϰ� ���� ��ü�� �����Ͱ� �ִ� ���ڿ� �迭
	 */
	public void add(String[] str) {
		switch (str.length) {
		case 4:
			this.add(new Result(str[0], str[1], Double.parseDouble(str[2]),
					Integer.parseInt(str[3])));
			break;
		case 8:
			this.add(new Player(str[0], str[1], Integer.parseInt(str[2]),
					Boolean.parseBoolean(str[3]), Double.parseDouble(str[4]),
					Double.parseDouble(str[5]), str[6], str[7]));
			break;
		case 9:
			this.add(new Horse(str[0], str[1], Integer.parseInt(str[2]),
					Boolean.parseBoolean(str[3]), Double.parseDouble(str[4]),
					Double.parseDouble(str[5]), str[6], str[7], HorseType
							.fromValue(str[8])));
			break;
		}
	}
	public void remove(Player mbr){
		vec.get(0).remove(mbr);
	}
	public void remove(Horse mbr){
		vec.get(1).remove(mbr);
	}
	public void remove(Result mbr){
		vec.get(2).remove(mbr);
	}

	/**
	 * �Է��� ���� ��η� ���� ���� ������ �ҷ��´�.<br>
	 * 
	 * @param filePath
	 *            : String - �ҷ����� ���� ������ ���
	 * @return boolean - ���������� ������ �ҷ��鿴�ٸ� true, �ƴϸ� false�� ��ȯ�Ѵ�.
	 */
	public boolean load(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			vec = (Vector<List>) ois.readObject();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return false;
	}
	public boolean load(){
		return this.load(DEFAULT_SAVE_FILE);
	}
	public boolean load(String filePath){
		return this.load(new File(filePath));
	}

	/**
	 * ���͸� �Է��� ���� ��η� �����Ѵ�.<br>
	 * 
	 * @param vectorPath
	 *            : String - ������ �����ϰ� ���� ���
	 * @return boolean - ���� ������ ���������� ���� �Ǿ��ٸ� true, �ƴϸ� false�� ��ȯ�Ѵ�.
	 */
	public boolean save(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(vec);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� ������ -��-");
		} catch (IOException e) {
			System.out.println("��� �Ұ�;;");
		}
		return false;
	}
	public boolean save(){
		return this.save(DEFAULT_SAVE_FILE);
	}
	public boolean save(String filePath){
		return this.save(new File(filePath));
	}
	public IMember newMember(){
		return this.newMember(userSelection);
	}
	public IMember newMember(ClassType classType){
		IMember imbr = null;
		switch(classType){
		case PLAYER:
			imbr = new Player();
			break;
		case HORSE:
			imbr = new Horse();
			break;
		case RESULT:
			imbr = new Result();
			break;
		}
		return imbr;
	}
	public void add(IMember imbr){
		switch(userSelection){
		case PLAYER:
			add((Player)imbr);
			break;
		case HORSE:
			add((Horse)imbr);
			break;
		case RESULT:
			add((Result)imbr);
			break;
		}
	}
	public List getMembers(){
		switch(getUserSelection()){
		case PLAYER:
			return getPlayers();
		case HORSE:
			return getHorses();
		case RESULT:
			return getResults();
		default:
			return null;
		}
	}
	
	public List<IMember> getMembers(int attIndex, int operator, String stdValue){
		return this.getMembers(attIndex, operator, stdValue, userSelection);
	}
	public List<IMember> getMembers(int attIndex, int operator, String stdValue, ClassType classType){
		List<IMember> list = null;
		switch(classType){
		case PLAYER:
			list = getPlayers();
			break;
		case HORSE:
			list = getHorses();
			break;
		case RESULT:
			list = getResults();
			break;
		}
		
		String[] attSample = list.get(0).getColumnName();
		
		Class cls = list.get(0).toArray()[attIndex].getClass();
		if(cls == XMLGregorianCalendar.class){
			stdValue = stdValue.replace("-", "");
			cls = Integer.class;
		}
		return this.getMembers(attIndex, operator, stdValue, list, cls);
	}
	
	public List<IMember> getMembers(String stdAtt, int operator, String stdValue){
		return getMembers(stdAtt, operator, stdValue, userSelection);
	}
	public List<IMember> getMembers(String stdAtt, int operator, String stdValue, ClassType classType){
		List<IMember> list = null;
		switch(classType){
		case PLAYER:
			list = getPlayers();
			break;
		case HORSE:
			list = getHorses();
			break;
		case RESULT:
			list = getResults();
			break;
		}
		
		String[] attSample = list.get(0).getColumnName();
		int attIndex = -1;
		for(int i = 0; i < attSample.length; i++){
			if(stdAtt.equals(attSample[i])){
				attIndex = i;
				break;
			}
		}
		
		

		Class cls = list.get(0).toArray()[attIndex].getClass();
		if(cls == XMLGregorianCalendar.class){
			stdValue = stdValue.replace("-", "");
			cls = Integer.class;
		}
		
		
		return this.getMembers(attIndex, operator, stdValue, list, cls);
	}
	protected List<IMember> getMembers(int attIndex, int operator, String stdValue, List<IMember> list, Class cls){
		List<IMember> result = new ArrayList<IMember>();

		System.out.println(attIndex);
		System.out.println(operator);
		System.out.println(stdValue);
		System.out.println(cls);
		
		if(cls == Integer.class || cls == Double.class){
			for(IMember imbr : list){
				double leftVal = Double.parseDouble(imbr.toStrings()[attIndex]);
				double rightVal = Double.parseDouble(stdValue);
				
				switch(operator){
				case 0:
					if(leftVal > rightVal) result.add(imbr);
					break;
				case 1:
					if(leftVal >= rightVal) result.add(imbr);
					break;
				case 2:
					if(leftVal == rightVal) result.add(imbr);
					break;
				case 3:
					if(leftVal <= rightVal) result.add(imbr);
					break;
				case 4:
					if(leftVal < rightVal) result.add(imbr);
					break;
				case 5:
					if(leftVal != rightVal) result.add(imbr);
					break;
				}
			}
		}
		else{
			for(IMember imbr : list){
				String leftVal = imbr.toStrings()[attIndex];
				String rightVal = stdValue;
				if(operator != 5){
					if(leftVal.contains(rightVal) == true) result.add(imbr);
				}
				else{
					if(leftVal.contains(rightVal) == false) result.add(imbr);
				}
			}
		}
		
		return result;
		
		/*switch(stdAtt){
		case ID:
			return getMembersById(stdValue, type);
		case NAME:
			return getMembersByName(stdValue, type);
		case AGE:
			return getMembersByAge(Integer.parseInt(stdValue), operator, type);
		case GENDER:
			return getMembersByGender(Boolean.parseBoolean(stdValue), type);
		case HEIGHT:
			return getMembersByHeight(Double.parseDouble(stdValue), operator, type);
		case WEIGHT:
			return getMembersByWeight(Double.parseDouble(stdValue), operator, type);
		case HID:
			return getPlayersByHId(stdValue);
		case NICK:
			return getHorsesByNick(stdValue);
		case TYPE:
			return getHorsesByType(HorseType.fromValue(stdValue));
		case DATE:
			return getResultsByDate(stdValue, operator);
		case BET_RATE:
			return getResultsByBetRate(Double.parseDouble(stdValue), operator);
		case WIN_RATE:
			return getRacersByWinRate(Double.parseDouble(stdValue), operator);
		case AVG_BET:
			return getRacersByAvgBetRate(Double.parseDouble(stdValue), operator);
		default:
			return null;
		}*/
	}

	/**
	 * ��� ���� ���� �����͸� �����´�.
	 * 
	 * @return {@link List}&lt;{@link Player}>
	 */
	public List getPlayers() {
		return vec.get(0);
	}

	/**
	 * ��� ���ָ� �����͸� �����´�.
	 * 
	 * @return {@link List}&lt;{@link Horse}>
	 */
	public List getHorses() {
		return vec.get(1);
	}

	/**
	 * ��� ���� ��� �����͸� �����´�.
	 * 
	 * @return {@link List}&lt;{@link Result}>
	 */
	public List getResults() {
		return vec.get(2);
	}
	/*
	public DefaultCategoryDataset getDatasetYear(int fieldIndex){
		List<Result> mList = vec.get(2); 
		DatasetManager dm = new DatasetManager();
		for(Result m: mList){
			dm.add(m.getDate().getYear() + "", CalcUtil.convertToWinRate(m.getRank()), m.getBetRate());
		}
		dm.setAverageAll();
		return dm.getDataset(fieldIndex, ROW_KEY);
	}
	public DefaultCategoryDataset getDatasetMonth(int fieldIndex, int year){
		List<Result> mList = vec.get(2); 
		DatasetManager dm = new DatasetManager();
		for(Result m: mList){
			if(m.getDate().getYear() == year){
				dm.add(m.getDate().getMonth() + "", CalcUtil.convertToWinRate(m.getRank()), m.getBetRate());
			}
		}
		dm.setAverageAll();
		return dm.getDataset(fieldIndex, ROW_KEY);
	}
	public DefaultCategoryDataset getDatasetDay(int fieldIndex, int year, int month){
		List<Result> mList = vec.get(2); 
		DatasetManager dm = new DatasetManager();
		for(Result m: mList){
			if( (m.getDate().getYear() == year) && (m.getDate().getMonth() == month) ){
				dm.add(m.getDate().getMonth() + "", CalcUtil.convertToWinRate(m.getRank()), m.getBetRate());
			}
		}
		dm.setAverageAll();
		return dm.getDataset(fieldIndex, ROW_KEY);
	}
	public DefaultCategoryDataset getDatasetDayAll(int fieldIndex){
		List<Result> mList = vec.get(2); 
		DatasetManager dm = new DatasetManager();
		int i = 0;
		for(Result m: mList){
			i++;
			dm.add(i + "", CalcUtil.convertToWinRate(m.getRank()), m.getBetRate());
		}
		dm.setAverageAll();
		return dm.getDataset(fieldIndex, ROW_KEY);
	}
	*/
	
	

	public ClassType getUserSelection() {
		if(userSelection == null){
			userSelection = DEFAULT_USER_SELECTION;
		}
		return userSelection;
	}

	public void setUserSelection(ClassType userSelection) {
		this.userSelection = userSelection;
	}

	/**
	 * ���� Vector�� �ʱ�ȭ ��Ų��.
	 */
	public void clear() {
		vec = new Vector(3, 3);
		List<Player> list1 = new ArrayList<Player>();
		List<Horse> list2 = new ArrayList<Horse>();
		List<Result> list3 = new ArrayList<Result>();
		vec.add(new ArrayList<Player>());
		vec.add(new ArrayList<Horse>());
		vec.add(new ArrayList<Result>());
	}
}