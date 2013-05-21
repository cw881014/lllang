package com.gitdog.lllang.parser;

import java.util.List;

import com.gitdog.lllang.clas.BaseAnalyser;
import com.gitdog.lllang.clas.NormalClass;
import com.gitdog.lllang.exceptions.LLException;
import com.gitdog.lllang.exceptions.runtime.CannotFoundFuncException;
import com.gitdog.lllang.exceptions.syntax.WrongSyntaxException;
import com.gitdog.lllang.execute.SimpleExecute;
import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.token.Token;

public class SemanticParser extends BaseParser {
	NormalClass nc = null;
	
	public SemanticParser(List<Token> tokenTree) throws LLException{
		super(tokenTree);
		this.runTest();
		
	}
	
	public void runTest() throws WrongSyntaxException, CannotFoundFuncException{
/*		for(Token t:getTokenTree()){
			System.out.println(t.toString());
		}*/
		System.out.println("========================================================");
		//while(true){
			//while(match(TokenType.END) || match(TokenType.LINE));
			if(match(TokenType.WORD)){
				goWord();
			}
		//}
	}
	
	
	
	public void goWord() throws WrongSyntaxException, CannotFoundFuncException{
		String nowToken = last(1).getToken();
		if("class".equals(nowToken)){
			defineAClassAndNameIs(get(0).getToken());
		}
		//nc.toPrintln();
		new BaseAnalyser(nc);
	}
	
	public void defineAClassAndNameIs(String className) throws WrongSyntaxException{
		nc = new NormalClass(className);
		this.findFunction(nc);
	}
}
