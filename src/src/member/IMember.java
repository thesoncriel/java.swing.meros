package member;

/**
 * <h2>public interface <i>IMember</i></h2>
 * <br>
 * Player와 Horse, Result 등의 서로 다른 데이터들을 포괄적으로 다루기 위해 설계된 인터페이스 이다.<br>
 * @author TheSON ^-^v
 * @see
 * {@link Memeber}, {@link Player}, {@link Horse}, {@link Result}
 */
public interface IMember extends Cloneable{
	public abstract String getId();
	public abstract void setId(String id);
	public abstract void setData(String... str);
	public abstract String[] toStrings();
	public abstract Object[] toArray();
	public abstract String[] getColumnName();
	public IMember clone();
	//public Class[] getSchema();
}