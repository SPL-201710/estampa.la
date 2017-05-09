package users.pojos;

public class FacebookResponse {
	
	private String name;
	private String id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
}
