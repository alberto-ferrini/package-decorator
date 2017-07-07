package it.extraweb.packagedecorator.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MaterialColorPalette {
	private List<String> names;
	private List<String> hexes;
	
	private LinkedHashMap<String, String> nameToHex;
	private LinkedHashMap<String, String> hexToName;
	private static MaterialColorPalette instance;
	
	
	public MaterialColorPalette(){
		names=new ArrayList<String>();
		hexes=new ArrayList<String>();
		nameToHex=new LinkedHashMap<String, String>();
		hexToName=new LinkedHashMap<String, String>();
		
		names.add("Red 500");
		names.add("Pink 500");
		names.add("Purple 500");
		names.add("Deep Purple 500");
		names.add("Indigo 500");
		names.add("Blue 500");
		names.add("Light Blue 500");
		names.add("Cyan 500");
		names.add("Teal 500");
		names.add("Green 500");
		names.add("Light Green 500");
		names.add("Lime 500");
		names.add("Yellow 500");
		names.add("Amber 500");
		names.add("Orange 500");
		names.add("Deep Orange 500");
		
		hexes.add("F44336");
		hexes.add("E91E63");
		hexes.add("9C27B0");
		hexes.add("673AB7");
		hexes.add("3F51B5");
		hexes.add("2196F3");
		hexes.add("03A9F4");
		hexes.add("00BCD4");
		hexes.add("009688");
		hexes.add("4CAF50");
		hexes.add("8BC34A");
		hexes.add("CDDC39");
		hexes.add("FFEB3B");
		hexes.add("FFC107");
		hexes.add("FF9800");
		hexes.add("FF5722");
		
		for(int i=0;i<names.size();i++){
			nameToHex.put(names.get(i), hexes.get(i));
			hexToName.put(hexes.get(i), names.get(i));
		}
	}
	
	public List<String> getNames() {
		return names;
	}

	public List<String> getHexes() {
		return hexes;
	}

	public LinkedHashMap<String, String> getNameToHex() {
		return nameToHex;
	}

	public LinkedHashMap<String, String> getHexToName() {
		return hexToName;
	}

	public static MaterialColorPalette getInstance(){
		if(instance==null){
			instance=new MaterialColorPalette();
		}
		return instance;
	}
	
}
