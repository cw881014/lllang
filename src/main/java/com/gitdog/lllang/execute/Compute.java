package com.gitdog.lllang.execute;

import java.util.List;

import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.token.Token;

public class Compute {
	
	public Compute(List<Token> operationWords, List<String> operators){
		this.operationWords = operationWords;
		this.operators = operators;
		this.computePower();
		this.computeMultipliedAndDiv();
		this.computePlusAndMinus();
	}
	
	public String getResult(){
		return this.getOperationWords().get(0).getToken();
	}
	
	public List<Token> getOperationWords() {
		return operationWords;
	}

	public List<String> getOperators() {
		return operators;
	}




	private List<Token> operationWords;
	private List<String> operators;
	
	private void computePower(){
		for(int i = operators.size() - 1; i >= 0 ; i--){
			if("^".equals(operators.get(i))){
				double a = Double.parseDouble(operationWords.get(i).getToken());
				double b = Double.parseDouble(operationWords.get(i+1).getToken());
				operationWords.set(i+1, new Token(Math.pow(a,b) + "",TokenType.NUMBER));
				operationWords.remove(i);
				operators.remove(i);
			}
		}
		if(find("^"))this.computePower();
	}
	
	private void computeMultipliedAndDiv(){
		for(int i = 0; i < operators.size(); i++){
			if("*".equals(operators.get(i))){
				double a = Double.parseDouble(operationWords.get(i).getToken());
				double b = Double.parseDouble(operationWords.get(i+1).getToken());
				operationWords.set(i, new Token((a*b) + "",TokenType.NUMBER));
				operationWords.remove(i+1);
				operators.remove(i);
			}else if("/".equals(operators.get(i))){
				double a = Double.parseDouble(operationWords.get(i).getToken());
				double b = Double.parseDouble(operationWords.get(i+1).getToken());
				operationWords.set(i, new Token((a/b) + "",TokenType.NUMBER));
				operationWords.remove(i+1);
				operators.remove(i);
			}
		}
		if(find("*", "-"))computeMultipliedAndDiv();	
	}
	
	
	private void computePlusAndMinus(){
		for(int i = 0; i < operators.size(); i++){
			if("+".equals(operators.get(i)) || "-".equals(operators.get(i))){
				double a = Double.parseDouble(operationWords.get(i).getToken());
				double b = Double.parseDouble(operationWords.get(i+1).getToken());
				operationWords.set(i, new Token((a+b) + "",TokenType.NUMBER));
				operationWords.remove(i+1);
				operators.remove(i);
			}
		}
		if(find("+", "-"))this.computePlusAndMinus();
		
	}
	
	private boolean find(String operator1, String operator2){
		for(String s:operators){
			if(s.equals(operator1) || s.equals(operator2))return true;
		}
		return false;
	}
	
	private boolean find(String operator){
		for(String s:operators){
			if(s.equals(operator))return true;
		}
		return false;
	}
}
