package com.gitdog.lllang.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.gitdog.lllang.clas.BaseClass;
import com.gitdog.lllang.exceptions.syntax.WrongSyntaxException;
import com.gitdog.lllang.function.BaseFunction;
import com.gitdog.lllang.function.NormalFunction;
import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.token.Token;

public abstract class BaseParser {
	
	
	
	public BaseParser(List<Token> tokenTree) {
		// TODO Auto-generated constructor stub
		this.tokenTree = tokenTree;
		position = 0;
	}
	
	protected Token get(int offset){
		if(position + offset >= tokenTree.size()){
			return new Token("", TokenType.OVER);
		}
		return tokenTree.get(position + offset);
	}
	
	protected Token last(int offset){
		return tokenTree.get(position - offset);
	}
	
	protected boolean match(TokenType type){
		if(get(0).getTokenType() != type)return false;
		position++;
		return true;
	}
	
	protected boolean match(String name){
		if(get(0).getTokenType() != TokenType.WORD)return false;
		if(!name.equals(get(0).getToken()))return false;
		position++;
		return true;
	}
	
	protected boolean match(TokenType type1, TokenType type2){
		if(get(0).getTokenType() != type1)return false;
		if(get(1).getTokenType() != type2)return false;
		position += 2;
		return true;
	}
	
	public void findFunction(BaseClass bc) throws WrongSyntaxException{
		for(int i = position; i < tokenTree.size(); i ++){
			if("func".equals(tokenTree.get(i).getToken())){
				NormalFunction func = new NormalFunction();
				func.setName(tokenTree.get(++i).getToken());//其实在名字这里也应该有异常的可能性的
				if(tokenTree.get(++i).getTokenType() != TokenType.LEFT_PAREN)throw new WrongSyntaxException();
				if(tokenTree.get(++i).getTokenType() != TokenType.RIGHT_PAREN){
					--i;
					List<String> paramNames = new LinkedList<String>();
					while(tokenTree.get(++i).getTokenType() != TokenType.RIGHT_PAREN){
						//这里获取参数
						paramNames.add(tokenTree.get(i).getToken());
					}
					func.setParamNames(paramNames);
				}else{
					func.setParamNames(null);
				}
				List<Token> tempTokens = new ArrayList<Token>();
				while(tokenTree.get(++i).getTokenType() != TokenType.RIGHT_BRACE){
					Token tempToken = tokenTree.get(i);
					if(tempToken.getTokenType() != TokenType.LEFT_BRACE)tempTokens.add(tempToken);
					
				}
				func.setTokens(tempTokens);
				bc.getFunctions().add(func);
			}
		}
	}
	
	
	public void next(){
		position++;
	}
	
	
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<Token> getTokenTree() {
		return tokenTree;
	}



	private final List<Token> tokenTree;
	private int position;
}
