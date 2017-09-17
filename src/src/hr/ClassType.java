package hr;


public enum ClassType{
	PLAYER(0),
	HORSE(1),
	RESULT(2);
	
	private final int value;
    ClassType(int v) {
        value = v;
    }
    public int value() {
        return value;
    }
}