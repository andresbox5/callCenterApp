package com.co.almundo.callcenter.utilities;

public enum Rol {
		
	OPERADOR("OPERADOR"),
	
	/** The supervisor. */
	SUPERVISOR("SUPERVISOR"),
	
	/** The director. */
	DIRECTOR("DIRECTOR");
	
	
	private String code;
	
	Rol(String code) {
        this.code = code;
    }

	public String getCode() {
		return code;
	}	

}
