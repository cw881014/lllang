package com.gitdog.lllang.token;

import java.util.ArrayList;
import java.util.List;

import com.gitdog.lllang.exceptions.LLException;
import com.gitdog.lllang.exceptions.runtime.NoSourceException;
import com.gitdog.lllang.exceptions.syntax.LLSyntaxException;
import com.gitdog.lllang.exceptions.syntax.WrongNumberException;
import com.gitdog.lllang.state.CharToken;
import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.state.TokenizeState;

public class TokenBaseParser {

	public TokenBaseParser(String source) {
		this.source = source;
	}

	public List<Token> getTokenTree() throws LLException {

		if (source == null) {
			throw new NoSourceException();
		}

		// token缓冲区，用来临时记录token
		StringBuffer token = new StringBuffer();
		// token树，用来记录所有的token和token类型
		List<Token> tokenTree = new ArrayList<Token>();
		// 设置默认状态
		TokenizeState state = TokenizeState.DEFAULT;
		// 将源代码插入到exception中，如果出异常了，会表示位置
		LLSyntaxException.setSource(source);
		String charTokens = CharToken.get();
		boolean hasDot = false;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch (state) {
			case DEFAULT:
				if (charTokens.indexOf(c) != -1) {
					tokenTree.add(new Token(Character.toString(c), CharToken
							.getTokenTypes()[charTokens.indexOf(c)]));
				} else if (Character.isLetter(c)) {
					token.append(c);
					state = TokenizeState.WORD;
				} else if (Character.isDigit(c)) {
					token.append(c);
					state = TokenizeState.NUMBER;
				} else if (c == '"') {
					state = TokenizeState.STRING;
				} else if (c == '#') {
					// tokenTree.add(new Token("", TokenType.COMMENT));
					state = TokenizeState.COMMENT;
				}
				break;
			case WORD:
				if (Character.isLetterOrDigit(c)) {
					token.append(c);
				} else {
					tokenTree.add(new Token(token.toString(), TokenType.WORD));
					token.setLength(0);
					state = TokenizeState.DEFAULT;
					i--;
				}
				break;
			case NUMBER:
				if (Character.isDigit(c)) {
					token.append(c);
				} else if (c == '.') {
					if (hasDot) {
						throw new WrongNumberException(i);
					} else {
						token.append(c);
						hasDot = true;
					}
				} else {
					tokenTree
							.add(new Token(token.toString(), TokenType.NUMBER));
					token.setLength(0);
					state = TokenizeState.DEFAULT;
					hasDot = false;
					i--;
				}
				break;
			case STRING:
				if (c == '"') {
					tokenTree
							.add(new Token(token.toString(), TokenType.STRING));
					token.setLength(0);
					state = TokenizeState.DEFAULT;
				} else {
					token.append(c);
				}
				break;
			case COMMENT:
				if (c == '\n') {
					state = TokenizeState.DEFAULT;
				}
				break;
			}
		}
		return tokenTree;
	}

	private String source = null;
}
