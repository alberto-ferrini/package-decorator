package it.extraweb.packagedecorator.util;

import java.util.LinkedHashMap;

import org.eclipse.swt.graphics.RGB;

public class MaterialColorPalette {
	public static final String DEFAULT_COLOR="RED_500";
	
	public static final String RED_500="F44336";
	public static final String PINK_500="E91E63";
	public static final String PURPLE_500="9C27B0";
	public static final String DEEPPURPLE_500="673AB7";
	public static final String INDIGO_500="3F51B5";
	public static final String BLUE_500="2196F3";
	public static final String LIGHTBLUE_500="03A9F4";
	public static final String CYAN_500="00BCD4";
	public static final String TEAL_500="009688";
	public static final String GREEN_500="4CAF50";
	public static final String LIGHTGREEN_500="8BC34A";
	public static final String LIME_500="CDDC39";
	public static final String YELLOW_500="FFEB3B";
	public static final String AMBER_500="FFC107";
	public static final String ORANGE_500="FF9800";
	public static final String DEEPORANGE_500="FF5722";
	//public static final RGB BROWN_500=PackageDecoratorUtils.hex2Rgb("795548");
	//public static final RGB GREY_500=PackageDecoratorUtils.hex2Rgb("9E9E9E");
	//public static final RGB BLUEGREY_500=PackageDecoratorUtils.hex2Rgb("607D8B");

	private LinkedHashMap<String, RGB> map;
	private LinkedHashMap<String, Integer> indexMap;
	private static MaterialColorPalette instance;
	
	
	public MaterialColorPalette(){
		map=new LinkedHashMap<>();
		map.put("RED_500",PackageDecoratorUtils.hex2Rgb(RED_500));
		map.put("PINK_500",PackageDecoratorUtils.hex2Rgb(PINK_500));
		map.put("PURPLE_500",PackageDecoratorUtils.hex2Rgb(PURPLE_500));
		map.put("DEEPPURPLE_500", PackageDecoratorUtils.hex2Rgb(DEEPPURPLE_500));
		map.put("INDIGO_500", PackageDecoratorUtils.hex2Rgb(INDIGO_500));
		map.put("BLUE_500", PackageDecoratorUtils.hex2Rgb(BLUE_500));
		map.put("LIGHTBLUE_500", PackageDecoratorUtils.hex2Rgb(LIGHTBLUE_500));
		map.put("CYAN_500", PackageDecoratorUtils.hex2Rgb(CYAN_500));
		map.put("TEAL_500", PackageDecoratorUtils.hex2Rgb(TEAL_500));
		map.put("GREEN_500",PackageDecoratorUtils.hex2Rgb( GREEN_500));
		map.put("LIGHTGREEN_500", PackageDecoratorUtils.hex2Rgb(LIGHTGREEN_500));
		map.put("LIME_500", PackageDecoratorUtils.hex2Rgb(LIME_500));
		map.put("YELLOW_500", PackageDecoratorUtils.hex2Rgb(YELLOW_500));
		map.put("AMBER_500", PackageDecoratorUtils.hex2Rgb(AMBER_500));
		map.put("ORANGE_500", PackageDecoratorUtils.hex2Rgb(ORANGE_500));
		map.put("DEEPORANGE_500",PackageDecoratorUtils.hex2Rgb( DEEPORANGE_500));
		
		indexMap=new LinkedHashMap<>();
		indexMap.put("RED_500", 0);
		indexMap.put("PINK_500", 1);
		indexMap.put("PURPLE_500", 2);
		indexMap.put("DEEPPURPLE_500", 3);
		indexMap.put("INDIGO_500", 4);
		indexMap.put("BLUE_500", 5);
		indexMap.put("LIGHTBLUE_500", 6);
		indexMap.put("CYAN_500", 7);
		indexMap.put("TEAL_500", 8);
		indexMap.put("GREEN_500", 9);
		indexMap.put("LIGHTGREEN_500", 10);
		indexMap.put("LIME_500", 11);
		indexMap.put("YELLOW_500", 12);
		indexMap.put("AMBER_500", 13);
		indexMap.put("ORANGE_500", 14);
		indexMap.put("DEEPORANGE_500", 15);
		//map.put("BROWN_500", BROWN_500);
		//map.put("GREY_500", GREY_500);
		//map.put("BLUEGREY_500", BLUEGREY_500);
	}

	public LinkedHashMap<String, RGB> getMap() {
		return map;
	}

	public LinkedHashMap<String, Integer> getIndexMap() {
		return indexMap;
	}
	
	public static MaterialColorPalette getInstance(){
		if(instance==null){
			instance=new MaterialColorPalette();
		}
		return instance;
	}
	
}
