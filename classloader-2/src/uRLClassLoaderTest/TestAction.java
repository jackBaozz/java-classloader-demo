package uRLClassLoaderTest;

import uRLClassLoaderTest.ActionInterface;

public class TestAction implements ActionInterface {

	@Override
	public String action() {
		return "this ActionTest class";
	}

}
