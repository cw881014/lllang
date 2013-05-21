package com.gitdog.lllang.clas;

import java.util.List;

import com.gitdog.lllang.function.BaseFunction;
import com.gitdog.lllang.token.Token;

public abstract class BaseClass {
	private String className;
	protected List<BaseFunction> functions;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<BaseFunction> getFunctions() {
		return functions;
	}
	public void setFunctions(List<BaseFunction> functions) {
		this.functions = functions;
	}
	
	public void toPrintln(){
		System.out.println(this.className);
		for(BaseFunction func: this.functions){
			String params = "";
			if(func.getParamNames() != null){
				for(String s:func.getParamNames()){
					params += s + ",";
				}
				params = params.substring(0, params.length() - 1);
			}
			System.out.println("func " + func.getName() + "(" + params + ")");
			for(Token token:func.getTokens()){
				System.out.println(token.toString());
			}
			System.out.println("----------------------------------------------");
		}
	}
	
}
