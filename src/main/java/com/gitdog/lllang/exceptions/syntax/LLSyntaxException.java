package com.gitdog.lllang.exceptions.syntax;

import com.gitdog.lllang.exceptions.LLException;

public abstract class LLSyntaxException extends LLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7518228792959799581L;
	private static String source;
	public static void setSource(String source){
		LLSyntaxException.source = source;
	}
	
	protected static void printErr(int start){
		String result = "";
		int backNum = getBackNum(start);
		for(int i = start - backNum; i <= start; i ++){
			result += source.charAt(i);
		}
		String backSpace = "";
		for(int i = 0; i < backNum; i++){
			backSpace += " ";
		}
		
		
		System.err.println(result);
		System.err.println(backSpace + "^");
	}
	
	private static int getBackNum(int start){
		for(int i = start; i >= 0; i--){
			if(source.charAt(i) == '\n'){
				return --i;
			}
		}
		return start;
	}
}
