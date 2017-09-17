package hr;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import member.IMember;

class MemberModel extends AbstractTableModel{
	private Object[][] data = null;
	private String[] header = null;
	
	public MemberModel(List<IMember> list){
		if(list.size() == 0){
			data = new Object[1][1];
			data[0][0] = "데이터가 존재하지 않습니다 ^^;;";
			header = new String[1];
			header[0] = "";
			return;
		}
		header = list.get(0).getColumnName();
		data = new Object[list.size()][];
		int i = 0;
		for(IMember imbr: list){
			data[i] = imbr.toStrings();
			i++;
		}
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return data[arg0][arg1];
	}
	public String getColumnName(int columnIndex){
		return header[columnIndex];
	}
	public DefaultCategoryDataset getDataset(int fieldIndex, String rowKey){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try{System.out.println(data.length + "길이");
			for(int i = 0; i < data.length; i++){
				String field = (String)this.data[i][fieldIndex];
				System.out.println(field);
				if(field.contains(".")){
					dataset.addValue(Double.parseDouble(field), rowKey, i + "");
				}
				else{
					dataset.addValue(Integer.parseInt(field), rowKey, i + "");
				}
				//System.out.println(field.toString());
			}
			return dataset;
		}
		catch(ClassCastException e){
			return null;
		}
		catch(Exception e){
			return null;
		}
	}
}