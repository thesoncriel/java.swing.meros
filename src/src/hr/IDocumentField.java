package hr;

public interface IDocumentField {
	public abstract void setValue(String value);
	public abstract String getValue();
	public abstract void setVisible(boolean visible);
	public abstract boolean isVisible();
	public abstract void setEditMode();
}
