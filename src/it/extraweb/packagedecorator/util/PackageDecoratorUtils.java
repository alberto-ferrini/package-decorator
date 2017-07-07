package it.extraweb.packagedecorator.util;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

public class PackageDecoratorUtils {
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
		return new ImageData(PackageDecoratorUtils.class.getResourceAsStream("/icons/package_default.png"));
	}

	public static ImageData getPackageWhiteIcon(){
		return new ImageData(PackageDecoratorUtils.class.getResourceAsStream("/icons/package_default_d.png"));
	}

	public static ImageData generateColorizedPackage(String colorHex){
		return PackageDecoratorUtils.colorize(PackageDecoratorUtils.getPackageIcon(),null,colorHex);
	}

}
