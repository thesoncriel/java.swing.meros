package hr;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import member.Horse;
import member.HorseRace;
import member.Player;

public class ImageSaver {
	public static final int MOD_IMAGE_WIDTH = 150;
	public static final int MOD_IMAGE_HEIGHT = 200;
	public static final String MOD_IMAGE_TYPE = "png";
	private HorseRace hr = null;
	private String dirPath = null;
	
	public ImageSaver(HorseRace hr, String dirPath){
		this.hr = hr;
		this.dirPath = dirPath;
	}
	/**
	 * ��ü�� ������ picURL�� ���� �̹����� �����ϰ� ����ũ��� �ٲ۴��� ���˵� �����Ѵ�.(�⺻ png ����)<br>
	 * ����Ǵ� �̹����� ��δ� �Ϲ������� dirPath�� ���� ������ pic�̴�.
	 */
	public void write(){
		
		File file;
		FileOutputStream fos;
		
		List<Player> mList1 = hr.getPlayers();
		for(Player p: mList1){
			try{
				System.out.println(dirPath + p.getPicURL() + "---");
				String picPath = p.getPicURL();
				URL url = null;
				if(picPath.contains("://") == false)
					url = new URL("file:///" + picPath);
				else
					url = new URL(picPath);
				pngSaver(url, p.getId());
			}
			catch (IOException e){
				e.printStackTrace();
			}
			
		}
		
		List<Horse> mList2 = hr.getHorses();
		for(Horse h: mList2){
			try{
				pngSaver(new URL(h.getPicURL()), h.getId());
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * �ҷ��� �̹��� ������ ũ�⸦ �����ϰ� png �������� �ٲ۴�.<br>
	 * �̹����� ũ��� MOD_IMAGE_WIDTH ����� MOD_IMAGE_HEIGHT ����� ������ ���� �ȴ�.
	 * @param picURL :
	 * {@link URL} - �ҷ����� ���� �̹����� ���
	 * @param id :
	 * {@link String} - �����Ű���� �ϴ� �̹����� ���� �̸�
	 */
	protected void pngSaver(URL picURL, String id){
		try{
			int newWidth = this.MOD_IMAGE_WIDTH;
			int newHeight = this.MOD_IMAGE_HEIGHT;
			String type = this.MOD_IMAGE_TYPE;
			File subDir = new File(dirPath + "/pic/");
			if(subDir.exists() == false){
				subDir.mkdirs();
			}
				
			Image imgSource = new Canvas().getToolkit().getImage(picURL);
			BufferedImage bi = getResizedImage(imgSource, newWidth, newHeight);
			FileOutputStream fos = new FileOutputStream(dirPath + "/pic/" + id + "." + type);
			
			ImageIO.write(bi, type, fos);
			//System.out.println("����" + XMLPath + "/pic/" + id + "." + type);
			fos.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
	}
	/**
	 * 
	 * @param imgSource
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static BufferedImage getResizedImage(Image imgSource, int newWidth, int newHeight){
		try {
			Image imgTarget = imgSource.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			
			int pixels[] = new int[newWidth * newHeight];
	
			PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, newWidth, newHeight, pixels, 0, newWidth);
			
			pg.grabPixels();
			BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			bi.setRGB(0, 0, newWidth, newHeight, pixels, 0, newWidth);
			return bi;
		}
		catch (InterruptedException e) {
			return null;
		}
	}
}
