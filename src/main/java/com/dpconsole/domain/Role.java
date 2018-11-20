package com.dpconsole.domain;

public enum Role {
	
	ROLE_ADMIN("ROLE_ADMIN", "Admin");
    
    private final String code;
	private final String text;
	
	Role(String code,String text)
	{
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
}
