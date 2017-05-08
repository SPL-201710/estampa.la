package users.pojos;

/**
 * @author jorge
 *
 */
public class UserAuthData {
	
	private String originalPwd;
	
	private String newPwd;
	
	public UserAuthData() {
		
	}

	public String getOriginalPwd() {
		return originalPwd;
	}

	public void setOriginalPwd(String originalPwd) {
		this.originalPwd = originalPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
}
