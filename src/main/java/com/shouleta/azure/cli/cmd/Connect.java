package com.shouleta.azure.cli.cmd;

import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.shouleta.azure.cli.cmd.param.Params;
import com.shouleta.azure.cli.util.ClientBuilder;

import static com.shouleta.azure.cli.cmd.param.ConnectParams.Key;

/**
 * CMD: connect
 * Connect to the Azure storage cloud with a 
 * @author liang
 *
 */
public class Connect implements Cmd {

	@Override
	public CmdResult invoke(Params params) throws Exception {
		CloudBlobClient client = ClientBuilder
				.get()
				.setEndpoint(params.get(Key.endpoint.toString()))
				.setEndpointProtocol(
						params.get(Key.endpointProtocol.toString()))
				.setUsername(params.get(Key.username.toString()))
				.setPassword(params.get(Key.password.toString())).build();
		CmdCtx.get().setClient(client);
		
		return null;
	}

}
