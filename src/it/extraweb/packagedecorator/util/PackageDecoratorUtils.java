package it.extraweb.packagedecorator.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

import it.extraweb.packagedecorator.activator.Activator;

public class PackageDecoratorUtils {
	public static ImageData colorize(ImageData image, String color){
		return colorize(image, MaterialColorPalette.getInstance().getMap().get(color));
	}
	
	private static ImageData colorize(ImageData image, RGB rgb){
		ImageData toReturn=null;
		toReturn=new ImageData(image.width, image.height, image.depth, image.palette);
		toReturn.alphaData=image.alphaData;
		if(rgb==null){
			rgb=MaterialColorPalette.getInstance().getMap().get(MaterialColorPalette.DEFAULT_COLOR);
			Activator.getDefault().getLog().log(new Status(IStatus.WARNING,Activator.PLUGIN_ID , "Color not supported ("+rgb.toString()+"): use default value."));
		}
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

	
	public static ImageData generateColorizedPackage(String color){
		return PackageDecoratorUtils.colorize(PackageDecoratorUtils.getPackageIcon(),color);
	}
	
	public static String decodeColorName(String colorName){
		String toReturn=colorName;
		toReturn=toReturn.replace('_', ' ');
		toReturn=toReturn.substring(0,1).toUpperCase()+toReturn.substring(1).toLowerCase();
		return toReturn;
	}
	
	public static String encodeColorName(String colorName){
		String toReturn=colorName.toUpperCase();
		toReturn=toReturn.replace(' ', '_');
		return toReturn;
	}
}
