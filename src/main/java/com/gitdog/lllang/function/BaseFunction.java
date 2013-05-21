package com.gitdog.lllang.function;

import java.util.List;

import com.gitdog.lllang.token.Token;

public abstract class BaseFunction {
	private String name;
	private List<String> paramNames;
	private List<Token> tokens;
	
	public List<String> getParamNames() {
		return paramNames;
	}
	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
}
