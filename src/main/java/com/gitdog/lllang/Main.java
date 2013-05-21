package com.gitdog.lllang;


import java.util.List;

import com.gitdog.lllang.exceptions.LLException;
import com.gitdog.lllang.io.LLFileReader;
import com.gitdog.lllang.parser.SemanticParser;
import com.gitdog.lllang.token.Token;
import com.gitdog.lllang.token.TokenBaseParser;

public class Main {

	/**
	 * @param args
	 * @throws LLException
	 */
	
	public static void main(String[] args) throws LLException{
		
		String source = LLFileReader.getResouce("samples/test.ll");
		TokenBaseParser tbp = new TokenBaseParser(source);
		List<Token> tokenTree = tbp.getTokenTree();
/*		int i = 0;
		for(Token t:tokenTree){
			System.out.println(i + " ===> " + t.getToken() + " <==> " + t.getTokenType());
			i++;
		}*/
		
		System.out.println("========================================================");
		
		new SemanticParser(tokenTree);
		
		/*
		 * 
		 * 要搞一个指令是
		 * which classroom have already occupied
		 * 返回哪些教室被占了，以及哪些位置被占了
		 * 
		 * 
		 * 查询教室或者位置是否被占了
		 * which sits or classroom have already occupied in classroom O
		 * 返回
		 * 		情况一：未找到教室或座位
		 * 			i can't find classroom x or sit x
		 * 		情况二：
		 * 			你查的这个确实被占了
		 * 		情况三：
		 * 			你查的这个确实没被占，你可以进去happy了
		 *
		 * 
		 */
	}
	

}
