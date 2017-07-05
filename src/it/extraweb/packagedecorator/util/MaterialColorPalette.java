package it.extraweb.packagedecorator.util;

import java.util.LinkedHashMap;

import org.eclipse.swt.graphics.RGB;

public class MaterialColorPalette {
	public static final RGB RED_500=PackageDecoratorUtils.hex2Rgb("F44336");
	public static final RGB PINK_500=PackageDecoratorUtils.hex2Rgb("E91E63");
	public static final RGB PURPLE_500=PackageDecoratorUtils.hex2Rgb("9C27B0");
	public static final RGB DEEPPURPLE_500=PackageDecoratorUtils.hex2Rgb("673AB7");
	public static final RGB INDIGO_500=PackageDecoratorUtils.hex2Rgb("3F51B5");
	public static final RGB BLUE_500=PackageDecoratorUtils.hex2Rgb("2196F3");
	public static final RGB LIGHTBLUE_500=PackageDecoratorUtils.hex2Rgb("03A9F4");
	public static final RGB CYAN_500=PackageDecoratorUtils.hex2Rgb("00BCD4");
	public static final RGB TEAL_500=PackageDecoratorUtils.hex2Rgb("009688");
	public static final RGB GREEN_500=PackageDecoratorUtils.hex2Rgb("4CAF50");
	public static final RGB LIGHTGREEN_500=PackageDecoratorUtils.hex2Rgb("8BC34A");
	public static final RGB LIME_500=PackageDecoratorUtils.hex2Rgb("CDDC39");
	public static final RGB YELLOW_500=PackageDecoratorUtils.hex2Rgb("FFEB3B");
	public static final RGB AMBER_500=PackageDecoratorUtils.hex2Rgb("FFC107");
	public static final RGB ORANGE_500=PackageDecoratorUtils.hex2Rgb("FF9800");
	public static final RGB DEEPORANGE_500=PackageDecoratorUtils.hex2Rgb("FF5722");
	//public static final RGB BROWN_500=PackageDecoratorUtils.hex2Rgb("795548");
	//public static final RGB GREY_500=PackageDecoratorUtils.hex2Rgb("9E9E9E");
	//public static final RGB BLUEGREY_500=PackageDecoratorUtils.hex2Rgb("607D8B");

	private LinkedHashMap<String, RGB> map;
	
	public MaterialColorPalette(){
		map=new LinkedHashMap<>();
		map.put("RED_500", RED_500);
		map.put("PINK_500", PINK_500);
		map.put("PURPLE_500", PURPLE_500);
		map.put("DEEPPURPLE_500", DEEPPURPLE_500);
		map.put("INDIGO_500", INDIGO_500);
		map.put("BLUE_500", BLUE_500);
		map.put("LIGHTBLUE_500", LIGHTBLUE_500);
		map.put("CYAN_500", CYAN_500);
		map.put("TEAL_500", TEAL_500);
		map.put("GREEN_500", GREEN_500);
		map.put("LIGHTGREEN_500", LIGHTGREEN_500);
		map.put("LIME_500", LIME_500);
		map.put("YELLOW_500", YELLOW_500);
		map.put("AMBER_500", AMBER_500);
		map.put("ORANGE_500", ORANGE_500);
		map.put("ORANGE_500", ORANGE_500);
		map.put("ORANGE_500", ORANGE_500);
		map.put("DEEPORANGE_500", DEEPORANGE_500);
		//map.put("BROWN_500", BROWN_500);
		//map.put("GREY_500", GREY_500);
		//map.put("BLUEGREY_500", BLUEGREY_500);
	}

	public LinkedHashMap<String, RGB> getMap() {
		return map;
	}
	
}
