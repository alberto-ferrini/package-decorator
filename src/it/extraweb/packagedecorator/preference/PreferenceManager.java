package it.extraweb.packagedecorator.preference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;

import it.extraweb.packagedecorator.activator.Activator;
import it.extraweb.packagedecorator.dto.Preference;
import it.extraweb.packagedecorator.dto.Preferences;
import it.extraweb.packagedecorator.util.PackageDecoratorConstants;

public class PreferenceManager {
	private static Preferences preferences;

	public static Preferences generateDefaultPreferences() {
		Preferences toReturn=new Preferences();
		toReturn.add(new Preference("service", "GREEN_500", true, false));
		toReturn.add(new Preference("business", "ORANGE_500", true, false));
		toReturn.add(new Preference("integration", "LIGHTBLUE_500", true, false));
		toReturn.add(new Preference("dto", "YELLOW_500", true, false));
		toReturn.add(new Preference("exception", "RED_500", true, false));
		toReturn.add(new Preference("client", "PURPLE_500", true, false));
		toReturn.add(new Preference("test", "BLUE_500", true, false));
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

	public static Preferences loadPreferences() {
		if(preferences==null){
			preferences=decodePreferences(Activator.getDefault().getPreferenceStore().getString(PackageDecoratorConstants.PREFERENCES_NAME));
		}
		return preferences;
	}

	public static void savePreferences(Preferences preferences) {
		try{
			Activator.getDefault().getPreferenceStore().setValue(PackageDecoratorConstants.PREFERENCES_NAME,encodePreferences(preferences));
			PreferenceManager.preferences=preferences;
			PlatformUI.getWorkbench().getDecoratorManager().update("package-decorator.decorator");
		}catch(Exception e){
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR,Activator.PLUGIN_ID , e.getLocalizedMessage(),e));
		}
	}
}