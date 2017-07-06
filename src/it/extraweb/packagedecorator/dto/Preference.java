package it.extraweb.packagedecorator.dto;

public class Preference extends PackageDecoratorDto{
	private static final long serialVersionUID = -6617837485927764136L;
	private String packageName;
	private String color;
	private Boolean subPackages;
	private Boolean emptyPackages;
	
	public Preference(){
		super();
	}
	
	public Preference(String packageName, String color, Boolean subPackages, Boolean emptyPackages) {
		super();
		this.packageName = packageName;
		this.color = color;
		this.subPackages = subPackages;
		this.emptyPackages = emptyPackages;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getSubPackages() {
		return subPackages;
	}
	public void setSubPackages(Boolean subPackages) {
		this.subPackages = subPackages;
	}
	public Boolean getEmptyPackages() {
		return emptyPackages;
	}
	public void setEmptyPackages(Boolean emptyPackages) {
		this.emptyPackages = emptyPackages;
	}

	@Override
	public String toString() {
		return "Preference [packageName=" + packageName + ", color=" + color + ", subPackages=" + subPackages
				+ ", emptyPackages=" + emptyPackages + "]";
	}
}
