package com.gitdog.lllang.execute;

import java.util.List;

import com.gitdog.lllang.clas.BaseAnalyser;
import com.gitdog.lllang.exceptions.runtime.CannotFoundFuncException;
import com.gitdog.lllang.exceptions.syntax.WrongSyntaxException;
import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.token.Token;

public class SimpleExecute {
	public SimpleExecute(List<Token> tokens, BaseAnalyser ba) throws WrongSyntaxException, CannotFoundFuncException{
		for(int i = 0; i < tokens.size(); i++){
			if("println".equals(tokens.get(i).getToken())){
				i = this.goPrintln(tokens, i).getPointer();
			/*}else if("var".equals(tokens.get(i).getToken())){
				AssociateReturn ar = this.goPrintln(tokens, i);
				i = ar.getPointer();*/
			}else if(tokens.get(i).getTokenType() == TokenType.WORD){
				ba.runFunction(tokens.get(i).getToken());
				//System.out.println(ba.getExecuteResult());
			}
		}
		
	}
	
	
	private AssociateReturn goVar(List<Token> tokens, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String executeResult;


	private AssociateReturn goPrintln(List<Token> tokens, int pointer) throws WrongSyntaxException{
		ComputeAssociate ca = new ComputeAssociate(tokens, pointer);
		//加这句是为了让即使执行了println也能让executeResult获得最后一次执行println的结果
		executeResult = ca.getAssociateReturn().getAssociateResult();
		if(ca.getAssociateReturn().getAssociateResult()!=null){
			this.executePrintln(ca.getAssociateReturn().getAssociateResult());
		}else{
			executePrintln();
		}
		return ca.getAssociateReturn();
	}
	
	
	
	private void executePrintln(Object o){
		System.out.println(o);
	}
	
	private void executePrintln(){
		System.out.println();
	}


	public String getExecuteResult() {
		return executeResult;
	}

}
