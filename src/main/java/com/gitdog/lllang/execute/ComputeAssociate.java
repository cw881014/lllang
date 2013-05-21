package com.gitdog.lllang.execute;

import java.util.ArrayList;
import java.util.List;

import com.gitdog.lllang.exceptions.syntax.WrongSyntaxException;
import com.gitdog.lllang.state.TokenType;
import com.gitdog.lllang.token.Token;

public class ComputeAssociate extends BaseAssociate {

	public ComputeAssociate(List<Token> tokens, int pointer) throws WrongSyntaxException {
		super(tokens, pointer);
		if(tokens.get(++pointer).getTokenType() != TokenType.LEFT_PAREN) throw new WrongSyntaxException();
		if(tokens.get(++pointer).getTokenType() != TokenType.RIGHT_PAREN){
			boolean hasString = false;
			boolean hasOperator = false;
			List<Token> operationWords = new ArrayList<Token>();
			List<String> operators = new ArrayList<String>();
			Token token;
			while((token = tokens.get(pointer)).getTokenType() != TokenType.RIGHT_PAREN){
				if(token.getTokenType() == TokenType.STRING){
					hasString = true;
				}
				if(token.getTokenType() == TokenType.OPERATOR){
					operators.add(token.getToken());
					hasOperator = true;
				}else{
					if(hasOperator && "-".equals(tokens.get(pointer-1).getToken())){
						operationWords.add(new Token("-" + token.getToken(), TokenType.NUMBER));
					}else{
						operationWords.add(token);
					}
				}
				pointer++;
			}
			if(!hasOperator){
				this.associateReturn.setAssociateResult(operationWords.get(0).getToken());
			}else{
				if(hasString){
					StringBuffer sb = new StringBuffer();
					for(Token tempToken:operationWords){
						sb.append(tempToken.getToken());
					}
					this.associateReturn.setAssociateResult(sb.toString());
				}else{
/*					double result = 0;
					for(Token tempToken:operationWords){
						result += Double.parseDouble(tempToken.getToken());
					}*/
					
					this.associateReturn.setAssociateResult(new Compute(operationWords, operators).getResult());
				}
			}
			
		}else{
			this.associateReturn.setAssociateResult(null);
		}
		this.associateReturn.setPointer(pointer);
	}

}
