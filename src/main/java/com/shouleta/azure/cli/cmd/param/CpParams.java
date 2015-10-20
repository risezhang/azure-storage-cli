package com.shouleta.azure.cli.cmd.param;

import com.shouleta.azure.cli.cmd.CmdCtx;

/**
 * Parameters for the command 'cp'
 * 
 * @author liang
 *
 */
public class CpParams extends Params {

	public static enum Key {
		source, target
	}

	public CpParams(CmdCtx ctx) {
		super(ctx);
	}

	@Override
	protected String[] getParamNames() {
		return getParamNames(Key.values());
	}

}
