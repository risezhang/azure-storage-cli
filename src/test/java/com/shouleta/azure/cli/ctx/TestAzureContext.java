/**
 * 
 */
package com.shouleta.azure.cli.ctx;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.shouleta.azure.cli.cmd.CmdCtx;

/**
 * @author liang
 *
 */
public class TestAzureContext {
	
	private CmdCtx azureContext;

	@Before
	public void setUp() throws Exception {
		azureContext = CmdCtx.get();
		azureContext.changeDirectory("/");
	}

	@Test
	public void textChageDirectoryRoot() {
		azureContext.changeDirectory("/");
		assertEquals(azureContext.getChildPath("abc"), "/abc");
	}
	
	@Test
	public void testchageDirectorySub() {
		azureContext.changeDirectory("foo");
		azureContext.changeDirectory("bar");
		assertEquals(azureContext.getChildPath("abc"), "/foo/bar/abc");
	}

	@Test
	public void testChageDirectoryUp() {
		azureContext.changeDirectory("foo");
		azureContext.changeDirectory("bar");
		azureContext.changeDirectory("..");
		System.out.println(azureContext.getChildPath("abc"));
		assertEquals(azureContext.getChildPath("abc"), "/foo/abc");
	}

}
