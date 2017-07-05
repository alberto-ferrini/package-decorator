package it.extraweb.packagedecorator.util;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

public class PackageDecoratorUtils {
	
	public static ImageData colorize(ImageData image, RGB rgb){
		ImageData toReturn=null;
		toReturn=new ImageData(image.width, image.height, image.depth, image.palette);
		toReturn.alphaData=image.alphaData;
		float[] baseHsb=rgb.getHSB();
		for(int i=0;i<toReturn.width;i++){
			for(int j=0;j<toReturn.height;j++){
				int pixel=image.getPixel(i, j);
				RGB color=image.palette.getRGB(pixel);
				float[] hsb=color.getHSB();
				RGB colorized=new RGB(baseHsb[0], baseHsb[1], hsb[2]);
				toReturn.setPixel(i, j, image.palette.getPixel(colorized));
			}
		}
		return toReturn;
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
		return new ImageData(PackageDecoratorUtils.class.getResourceAsStream("/icons/package_default_d.png"));
	}
}
