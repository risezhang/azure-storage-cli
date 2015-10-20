package com.shouleta.azure.cli.cmd;

import com.shouleta.azure.cli.cmd.param.CcParams.Key;
import com.shouleta.azure.cli.cmd.param.Params;

/**
 * CMD: cc
 * change current container
 * @author liang
 *
 */
public class Cc implements Cmd {

	@Override
	public CmdResult invoke(Params params) throws Exception {
		CmdCtx.get().changeContainer(params.get(Key.container.toString()));
		return null;
	}

}
