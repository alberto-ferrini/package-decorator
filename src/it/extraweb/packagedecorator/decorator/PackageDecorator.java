package it.extraweb.packagedecorator.decorator;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import it.extraweb.packagedecorator.activator.Activator;
import it.extraweb.packagedecorator.dto.Preference;
import it.extraweb.packagedecorator.dto.Preferences;
import it.extraweb.packagedecorator.preference.PreferenceManager;
import it.extraweb.packagedecorator.util.PackageDecoratorUtils;

public class PackageDecorator extends LabelProvider implements ILabelDecorator{

	@Override
	public Image decorateImage(Image image, Object element) {
		Image toReturn=null;
		ILog log = Activator.getDefault().getLog();
		try{
			IJavaElement javaElement=(IJavaElement)element;
			if(javaElement.getElementType()==4){
				Preferences preferences=PreferenceManager.loadPreferences();
				for(Preference preference:preferences.getPreferences()){
					if(isPreferenceValid(preference,javaElement)){
						ImageData imageData=PackageDecoratorUtils.getPackageIcon();
						imageData=PackageDecoratorUtils.colorize(imageData, preference.getColor());
						toReturn=new Image(image.getDevice(),imageData);
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
			// Name is valid 
			if(preference.getEmptyPackages()==true){
				toReturn=true;
			}
			else{
				IResource resource=javaElement.getCorrespondingResource();
				if(resource!=null){
					String path=resource.getRawLocation().toPortableString();
					if(path!=null){
						File file=new File(path);
						if(file.exists()&&file.canRead()&&file.isDirectory()){
							String[] children=file.list(new FilenameFilter() {
								@Override
								public boolean accept(File dir, String name) {
									if(name.equals(".")||name.equals("..")){
										return false;
									}
									else{
										return true;
									}
								}
							});
							if(children.length>0){
								toReturn=true;
							}
						}
					}
				}
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
