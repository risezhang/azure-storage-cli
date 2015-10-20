package com.shouleta.azure.cli.cmd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class TestCp {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void textChageDirectoryRoot() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Cp cp = new Cp();
		Method method = cp.getClass().getDeclaredMethod("copy", new Class[]{CmdCtx.class,String.class, String.class});
		method.setAccessible(true);
		method.invoke(cp, new Object[] {null, "/hahah/foo", "/Users/xxx/Applications/play-1.3.1/"});
	}

}
