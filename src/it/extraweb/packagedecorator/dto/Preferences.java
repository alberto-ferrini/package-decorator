package it.extraweb.packagedecorator.dto;

import java.util.ArrayList;
import java.util.List;

public class Preferences extends PackageDecoratorDto{

	private static final long serialVersionUID = -8704029583584633L;
	
	private List<Preference> preferences;
	
	public Preferences(){
		preferences=new ArrayList<Preference>();
	}

	public Preference get(int i){
		return preferences.get(i);
	}
	
	public void add(Preference preference){
		preferences.add(preference);
	}
	
	public Preference remove(int i){
		return preferences.remove(i);
	}

	@Override
	public String toString() {
		return "Preferences [preferences=" + preferences + "]";
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void add(int index,Preference preference) {
		preferences.add(index, preference);
		
	}

	public int size() {
		return preferences.size();
	}
}
