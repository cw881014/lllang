package com.gitdog.lllang.clas;

import com.gitdog.lllang.exceptions.runtime.CannotFoundFuncException;
import com.gitdog.lllang.exceptions.syntax.WrongSyntaxException;
import com.gitdog.lllang.execute.SimpleExecute;
import com.gitdog.lllang.function.BaseFunction;

public class BaseAnalyser {
	public BaseAnalyser(BaseClass bc) throws WrongSyntaxException, CannotFoundFuncException{
		this.bc = bc;
		this.runMainFunction();
	}

	
	public void runMainFunction() throws WrongSyntaxException, CannotFoundFuncException{
		this.runFunction("main");
	}
	
	public void runFunction(String funcName) throws WrongSyntaxException, CannotFoundFuncException{
		BaseFunction func = null; 
		for(int i = 0; i < bc.getFunctions().size(); i++){
			if(funcName.equals(bc.getFunctions().get(i).getName())){
				func = bc.getFunctions().get(i);
				break;
			}
		}
		if(func == null)throw new CannotFoundFuncException();
		SimpleExecute se = new SimpleExecute(func.getTokens(), this);
		this.executeResult = se.getExecuteResult();
	}
	
	public String getExecuteResult(){
		return this.executeResult;
	}
	

	private String executeResult;
	
	private BaseClass bc;
}
