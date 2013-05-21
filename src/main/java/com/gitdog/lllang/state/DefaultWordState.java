package com.gitdog.lllang.state;

public enum DefaultWordState {
	O, IF, ELSE, PRINT, println, func;
	public static DefaultWordState compare(String token){
		return valueOf(token);
	}
}
