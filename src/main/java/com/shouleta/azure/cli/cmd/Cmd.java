package com.shouleta.azure.cli.cmd;

import com.shouleta.azure.cli.cmd.param.Params;

/**
 * Cmd interface
 * @author liang
 *
 */
public interface Cmd {
	
	/**
	 * Invoke by params
	 * @param params
	 * @return a result (optional)
	 */
	public CmdResult invoke(Params params) throws Exception;

}
