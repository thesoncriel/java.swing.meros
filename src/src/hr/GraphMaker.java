package hr;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import org.jfree.chart.*;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphMaker {
	public static final String SUB_FOLDER = "/chart";
	public static final int CHART_BAR = 0;
	public static final int CHART_LINE = 1;
	private final int GRAPH_WIDTH = 250;
	private final int GRAPH_HEIGHT = 200;
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;
	private String title;
	private String xAxisLabel;
	private String yAxisLabel;
	
	public GraphMaker(){
		dataset = new DefaultCategoryDataset();
	}
	public GraphMaker(DefaultCategoryDataset dataset, String title, String xAxisLabel, String yAxisLabel){
		this.dataset = dataset;
		this.title = title;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
	}
	public void setDataset(DefaultCategoryDataset dataset){
		this.dataset = dataset;
	}
	public DefaultCategoryDataset getDataset(){
		return dataset;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getxAxisLabel() {
		return xAxisLabel;
	}
	public void setxAxisLabel(String xAxisLabel) {
		this.xAxisLabel = xAxisLabel;
	}
	public String getyAxisLabel() {
		return yAxisLabel;
	}
	public void setyAxisLabel(String yAxisLabel) {
		this.yAxisLabel = yAxisLabel;
	}
	public JFreeChart createBarChart(){
		chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, false, true, false);
		return this.setKoreanFont(chart);
	}
	public JFreeChart createLineChart(){
		chart = ChartFactory.createLineChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, false, true, false);
		return this.setKoreanFont(chart);
	}
	
	public void setChart(JFreeChart chart){
		this.chart = chart;
	}
	public JFreeChart getChart(){
		return this.chart;
	}
	public JButton getComponent(int chartType){
		if(chartType == GraphMaker.CHART_LINE){
			this.createLineChart();
		}
		else{
			this.createBarChart();
		}
		return this.getComponent();
	}
	public JButton getComponent(){
		BufferedImage bi = chart.createBufferedImage(GRAPH_WIDTH, GRAPH_HEIGHT);
		ImageIcon icon = new ImageIcon(bi);
		JButton btn = new JButton(icon);
		btn.setBorder(BorderFactory.createLineBorder(new Color(65, 170, 65), 2));
		btn.setUI(new BasicButtonUI());
		btn.setText("");
		btn.setEnabled(false);
		btn.setDisabledIcon(icon);
		return btn;
	}
	public BufferedImage getImage(){
		return chart.createBufferedImage(GRAPH_WIDTH, GRAPH_HEIGHT);
	}
	public BufferedImage getImage(int width, int height){
		return chart.createBufferedImage(width, height);
	}
	
	public boolean saveChart(String filePath){
		return saveChart(filePath, GRAPH_WIDTH, GRAPH_HEIGHT);
	}
	public boolean saveChart(String filePath, int width, int height){
		try {
			File file = new File(filePath);
			File subDir = file.getParentFile();
			if(subDir.exists() == false) subDir.mkdirs();
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			ChartUtilities.saveChartAsPNG(file, chart, width, height, info);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	protected JFreeChart setKoreanFont(JFreeChart chart){
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		//차트 제목
		chart.getTitle().setFont(this.toKoreanFont(chart.getTitle().getFont(), "굴림"));
		//가로축 제목
		plot.getDomainAxis().setLabelFont(this.toKoreanFont(plot.getDomainAxis().getLabelFont(), "돋움"));
		//가로축 값에 대한 레이블
		plot.getDomainAxis().setTickLabelFont(this.toKoreanFont(plot.getDomainAxis().getTickLabelFont(), "돋움"));
		//세로축 제목
		plot.getRangeAxis().setLabelFont(this.toKoreanFont(plot.getRangeAxis().getLabelFont(), "돋움"));
		//세로축 값에 대한 레이블
		plot.getRangeAxis().setTickLabelFont(this.toKoreanFont(plot.getRangeAxis().getTickLabelFont(), "돋움"));
		//범례
		try{
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
		}catch(NullPointerException e){
			System.out.println("널포인트 예외 발행 ㅡ.ㅡ");
		}
		
		return chart;
	}
	protected Font toKoreanFont(Font font, String family){
		return new Font(family, font.getStyle(), font.getSize());
	}
}