package com.gitdog.lllang.exceptions.syntax;

public class WrongNumberException extends LLSyntaxException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -449881665060543804L;

	public WrongNumberException(int start){
		super.printErr(start);
	}
}
