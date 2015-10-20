package com.shouleta.azure.cli.cmd.param;

import com.shouleta.azure.cli.cmd.CmdCtx;

/**
 * Parameters for the command 'cp'
 * 
 * @author liang
 *
 */
public class ConnectParams extends Params {

	public static enum Key {
		username, password, endpoint, endpointProtocol
	}

	public ConnectParams(CmdCtx ctx) {
		super(ctx);
	}

	@Override
	protected String[] getParamNames() {
		return getParamNames(Key.values());
	}

}
