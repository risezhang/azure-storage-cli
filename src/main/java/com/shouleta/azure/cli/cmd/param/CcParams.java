package com.shouleta.azure.cli.cmd.param;

import com.shouleta.azure.cli.cmd.CmdCtx;

/**
 * Parameters for the command 'cc'
 * 
 * @author liang
 *
 */
public class CcParams extends Params {

	public static enum Key {
		container
	}

	public CcParams(CmdCtx ctx) {
		super(ctx);
	}

	@Override
	protected String[] getParamNames() {
		return getParamNames(Key.values());
	}

}
