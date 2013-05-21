package com.gitdog.lllang.token;

import com.gitdog.lllang.state.TokenType;

public class Token {

	public Token(String token, TokenType tokenType) {
		this.tokenType = tokenType;
		switch(this.tokenType){
		case END:
			this.token = "";
			break;
		case LINE:
			this.token = "";
			break;
		default:
			this.token = token;
		}
		
	}
	

	public TokenType getTokenType() {
		return tokenType;
	}
	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String toString(){
		if(this.tokenType == TokenType.END || this.tokenType == TokenType.LINE){
			return this.tokenType.toString();
		}
		return this.token + " ==> " + this.tokenType;
	}

	private TokenType tokenType;
	private String token;

}
