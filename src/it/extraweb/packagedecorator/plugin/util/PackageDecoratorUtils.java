package it.extraweb.packagedecorator.plugin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

public class PackageDecoratorUtils {
	private static byte[] imgData;

	public static ImageData colorize(ImageData current, ImageData template , String colorHex){
		return colorize(current,template, PackageDecoratorUtils.hex2Rgb(colorHex));
	}

	private static ImageData colorize(ImageData current, ImageData template, RGB rgb){
		float[] baseHsb=rgb.getHSB();
		for(int i=0;i<current.width;i++){
			for(int j=0;j<current.height;j++){
				int currentPixel=current.getPixel(i, j);
				RGB currentPixelColor=current.palette.getRGB(currentPixel);
				float[] hsb=currentPixelColor.getHSB();
				RGB colorized=new RGB(baseHsb[0], baseHsb[1], hsb[2]);
				if(template!=null){
					int templatePixel=template.getPixel(i, j);
					RGB templatePixelColor=template.palette.getRGB(templatePixel);
					if(currentPixelColor.equals(templatePixelColor)){
						current.setPixel(i, j, current.palette.getPixel(colorized));
					}
				}
				else{
					current.setPixel(i, j, current.palette.getPixel(colorized));
				}
			}
		}
		return current;
	}

	public static RGB hex2Rgb(String hex) {
		if(hex.startsWith("#")){
			hex=hex.substring(1);
		}
		return new RGB(
				Integer.valueOf( hex.substring( 0, 2 ), 16 ),
				Integer.valueOf( hex.substring( 2, 4 ), 16 ),
				Integer.valueOf( hex.substring( 4, 6 ), 16 ) );
	}

	public static ImageData getPackageIcon(){
		ImageData toReturn=null;
		try {
			if(imgData==null) {
				InputStream is = PackageDecoratorUtils.class.getResourceAsStream("/icons/package_default.png");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int nRead;
				byte[] readedData = new byte[4096];

				while ((nRead = is.read(readedData, 0, readedData.length)) != -1) {
					outputStream.write(readedData, 0, nRead);
				}
				outputStream.flush();
				imgData=outputStream.toByteArray();
				outputStream.close();
				is.close();
			}
			ByteArrayInputStream inputStream=new ByteArrayInputStream(imgData);
			toReturn=new ImageData(inputStream);
			inputStream.close();
		}catch(Exception e) {
			throw new RuntimeException("Exception in getPackageIcon",e);
		}
		return toReturn;
	}

	public static ImageData getPackageWhiteIcon(){
		return new ImageData(PackageDecoratorUtils.class.getResourceAsStream("/icons/package_default_d.png"));
	}

	public static ImageData generateColorizedPackage(String colorHex){
		return PackageDecoratorUtils.colorize(PackageDecoratorUtils.getPackageIcon(),null,colorHex);
	}

}
