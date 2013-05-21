package com.gitdog.lllang.clas;

import java.util.ArrayList;

import com.gitdog.lllang.function.BaseFunction;

public class NormalClass extends BaseClass {

	public NormalClass(String className){
		this.functions = new ArrayList<BaseFunction>();
		this.setClassName(className);
	}
}
