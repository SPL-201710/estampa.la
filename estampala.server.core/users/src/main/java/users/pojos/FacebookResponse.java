package users.pojos;

public class FacebookResponse {
	
	private FacebookData data;
	private FacebookError error;
	
	public FacebookData getData() {
		return data;
	}
	public void setData(FacebookData data) {
		this.data = data;
	}
	public FacebookError getError() {
		return error;
	}
	public void setError(FacebookError error) {
		this.error = error;
	}	
}
