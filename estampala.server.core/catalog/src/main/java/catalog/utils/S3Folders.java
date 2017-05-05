package catalog.utils;

public enum S3Folders {

	PRINTS("prints"), SHIRT_STYLES("shirtStyles");
	
	private String folderName;
	private S3Folders(String name){
		this.folderName = name;
	}
	
	public String getFolderName(){
		return folderName;
	}
}
