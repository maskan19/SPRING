package kr.or.ddit.enumpkg;

public enum OsType {
	ANDROID("안드로이드"), NT("윈도우"), MAC("IOS"), OTHER("기타등등");
	OsType(String osName){
		this.osName = osName;
	}
	private String osName;
	public String getOsName(){
		return this.osName;
	}
	
	public static String getOsName(String agent) {
		agent = agent.toUpperCase();
		OsType searched = OTHER;
		for(OsType tmp : values()){
			if(agent.contains(tmp.name())){
				searched = tmp;
				break;
			}
		}
		String name = searched.getOsName();
		return name;
	}
}
