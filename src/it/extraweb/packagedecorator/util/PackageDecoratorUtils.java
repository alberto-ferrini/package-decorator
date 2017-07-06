package it.extraweb.packagedecorator.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

import it.extraweb.packagedecorator.activator.Activator;
import it.extraweb.packagedecorator.dto.Preference;
import it.extraweb.packagedecorator.dto.Preferences;

public class PackageDecoratorUtils {
	private static MaterialColorPalette palette=new MaterialColorPalette();

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

	public static Preferences generateDefaultPreferences() {
		Preferences toReturn=new Preferences();
		toReturn.add(new Preference("business", "ORANGE_500", true, false));
		return toReturn;
	}

	public static String encodePreferences(Preferences preferences){
		String toReturn="";
		try{
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(baos);
			oos.writeObject(preferences);
			byte[] data=baos.toByteArray();
			byte[] encoded=Base64.getEncoder().encode(data);
			toReturn=new String(encoded,"UTF-8");
		}catch(Exception e){
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR,Activator.PLUGIN_ID , e.getLocalizedMessage(),e));
		}
		return toReturn;
	}

	public static Preferences decodePreferences(String preferences){
		Preferences toReturn=null;
		try{
			byte[] decoded=Base64.getDecoder().decode(preferences.getBytes("UTF-8"));
			ByteArrayInputStream bais=new ByteArrayInputStream(decoded);
			ObjectInputStream ois=new ObjectInputStream(bais);
			toReturn=(Preferences)ois.readObject();
		}catch(Exception e){
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR,Activator.PLUGIN_ID , e.getLocalizedMessage(),e));
		}
		return toReturn;
	}
	
	public static ImageData generateColorizedPackage(String color){
		return PackageDecoratorUtils.colorize(PackageDecoratorUtils.getPackageIcon(),palette.getMap().get(color));
	}
	
	public static ImageData generateColorizedPackage(RGB color){
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
