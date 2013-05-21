package com.gitdog.lllang.state;

public class CharToken {
	public static String get(){
		return "^=+-*/(){}";
	}
	
	public static TokenType[] getTokenTypes(){
		TokenType[] tokenTypes = {TokenType.OPERATOR, TokenType.EQUALS,TokenType.OPERATOR, TokenType.OPERATOR, TokenType.OPERATOR,TokenType.OPERATOR,TokenType.LEFT_PAREN, TokenType.RIGHT_PAREN, TokenType.LEFT_BRACE, TokenType.RIGHT_BRACE};
		return tokenTypes;
	}
}
