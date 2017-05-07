package users.services;

public enum AuthenticationMethods {
	SYSTEM,
	FACEBOOK,
	TWITTER,
	NONE;
	
	public static AuthenticationMethods parse(String name){
		
		for(AuthenticationMethods social : AuthenticationMethods.values()){
			if (social.name().equalsIgnoreCase(name)){
				return social;
			}
		}
		
		return AuthenticationMethods.NONE;
	}
}
