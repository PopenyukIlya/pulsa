package pulse.payload.response;

import net.minidev.json.JSONObject;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private JSONObject resp;
	private String username;
	private List<String> roles;

	public JwtResponse(String accessToken, JSONObject resp, Long id, String username, List<String> roles) {
		this.resp=resp;
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JSONObject getResp() {
		return resp;
	}

	public void setResp(JSONObject resp) {
		this.resp = resp;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
