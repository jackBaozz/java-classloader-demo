package com.bzz.classloader;

import com.bzz.classloader.ActionInterface;

public class TestAction implements ActionInterface {

	@Override
	public String action() {
		return "this ActionTest class";
	}

}
