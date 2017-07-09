package it.extraweb.packagedecorator.plugin.decorator;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import it.extraweb.packagedecorator.plugin.activator.Activator;
import it.extraweb.packagedecorator.plugin.dto.Preference;
import it.extraweb.packagedecorator.plugin.dto.Preferences;
import it.extraweb.packagedecorator.plugin.preference.PreferenceManager;
import it.extraweb.packagedecorator.plugin.util.PackageDecoratorUtils;

public class PackageDecorator extends LabelProvider implements ILabelDecorator{

	@Override
	public Image decorateImage(Image originalImage, Object element) {
		Image toReturn=null;
		ILog log = Activator.getDefault().getLog();
		try{
			IJavaElement javaElement=(IJavaElement)element;
			if(javaElement.getElementType()==4){
				Preferences preferences=PreferenceManager.loadPreferences();
				for(Preference preference:preferences.getPreferences()){
					if(isPreferenceValid(preference,javaElement)){
						ImageData packageIcon=PackageDecoratorUtils.getPackageIcon();
						ImageData colorized=PackageDecoratorUtils.colorize(originalImage.getImageData(),packageIcon, preference.getColor());
						toReturn=new Image(originalImage.getDevice(),colorized);
						break;
					}
				}
			}
		}catch(Exception e){
			log.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getLocalizedMessage(),e));
		}
		return toReturn;
	}

	private boolean isPreferenceValid(Preference preference, IJavaElement javaElement) throws Exception{
		boolean toReturn=false;
		String name=javaElement.getElementName();
		if(name.endsWith("."+preference.getPackageName())||(preference.getSubPackages()==true&&name.contains("."+preference.getPackageName()+"."))){
			if(javaElement.getJavaModel().hasChildren()){
				toReturn=true;
			}
		}
		return toReturn;
	}

	public String decorateText(String text, Object element) {
		return null;
	}


	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

}
