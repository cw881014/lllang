package com.gitdog.lllang.execute;

import java.util.List;

import com.gitdog.lllang.token.Token;

public abstract class BaseAssociate {
	public BaseAssociate(List<Token> tokens, int pointer){
		this.tokens = tokens;
		this.pointer = pointer;
		associateReturn = new AssociateReturn();
	}
	
	
	
	public AssociateReturn getAssociateReturn() {
		return associateReturn;
	}



	protected List<Token> tokens;
	protected int pointer;
	protected AssociateReturn associateReturn;
}
