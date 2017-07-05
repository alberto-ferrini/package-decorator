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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import it.extraweb.packagedecorator.activator.Activator;
import it.extraweb.packagedecorator.util.MaterialColorPalette;
import it.extraweb.packagedecorator.util.PackageDecoratorUtils;

public class PackageDecorator implements ILabelDecorator{

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image decorateImage(Image image, Object element) {
		Image toReturn=null;

		ILog log = Activator.getDefault().getLog();
		try{
			IJavaElement javaElement=(IJavaElement)element;
			if(javaElement.getElementType()==4){
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
								log.log(new Status(IStatus.INFO, Activator.PLUGIN_ID, javaElement.getElementName()));
								ImageData imageData=PackageDecoratorUtils.getPackageIcon();
								String name=javaElement.getElementName();
								if(name.contains(".service.")||name.endsWith(".service")){
									imageData=PackageDecoratorUtils.colorize(imageData, MaterialColorPalette.GREEN_500);
								}
								else if(name.contains(".business.")||name.endsWith(".business")){
									imageData=PackageDecoratorUtils.colorize(imageData, MaterialColorPalette.INDIGO_500);
								}
								else if(name.contains(".integration.")||name.endsWith(".integration")){
									imageData=PackageDecoratorUtils.colorize(imageData, MaterialColorPalette.LIGHTBLUE_500);
								}
								else if(name.contains(".exception.")||name.endsWith(".exception")){
									imageData=PackageDecoratorUtils.colorize(imageData, MaterialColorPalette.RED_500);
								}
								else if(name.contains(".dto.")||name.endsWith(".dto")){
									imageData=PackageDecoratorUtils.colorize(imageData, MaterialColorPalette.YELLOW_500);
								}
								toReturn=new Image(image.getDevice(),imageData);
							}
						}
					}
				}
			}
		}catch(Exception e){
			log.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getLocalizedMessage()));
		}
		return toReturn;
	}

	@Override
	public String decorateText(String text, Object element) {
		// TODO Auto-generated method stub
		return null;
	}


}
