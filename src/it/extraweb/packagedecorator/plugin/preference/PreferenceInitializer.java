package it.extraweb.packagedecorator.plugin.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import it.extraweb.packagedecorator.plugin.activator.Activator;
import it.extraweb.packagedecorator.plugin.util.PackageDecoratorConstants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PackageDecoratorConstants.PREFERENCES_NAME,PreferenceManager.encodePreferences(PreferenceManager.generateDefaultPreferences()));
	}

}
