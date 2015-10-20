package com.shouleta.azure.cli.util;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;

public class ClientBuilder {

	private static final String PATTERN_CONNECTION = "DefaultEndpointsProtocol=%s;"
			+ "EndpointSuffix=%s;" + "AccountName=%s;" + "AccountKey=%s";

	/**
	 * Get a new builder
	 * 
	 * @return a builder
	 */
	public static ClientBuilder get() {
		return new ClientBuilder();
	}

	// account username
	private String username;

	// account password
	private String password;

	// endpoint
	private String endpoint = "core.chinacloudapi.cn";

	// endpoint protocol
	private String endpointProtocol = "http";

	/**
	 * Set account username
	 * 
	 * @param username
	 * @return self
	 */
	public ClientBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	/**
	 * Set account passowrd
	 * 
	 * @param password
	 * @return self
	 */
	public ClientBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Set endpoint domain. The default value is core.cinacloudapi.cn
	 * 
	 * @param endpoint
	 * @return self
	 */
	public ClientBuilder setEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	/**
	 * set endpoint protocol. The default value is "http"
	 * 
	 * @param endpointProtocol
	 * @return self
	 */
	public ClientBuilder setEndpointProtocol(String endpointProtocol) {
		this.endpointProtocol = endpointProtocol;
		return this;
	}

	/**
	 * Build a cloud blob client
	 * @return a cloud blob client
	 * @throws URISyntaxException
	 *             when endpoint is wrong
	 * @throws InvalidKeyException
	 *             when user credentials are wrong
	 */
	public CloudBlobClient build() throws InvalidKeyException, URISyntaxException {
		return createClient(getConnectParam());
	}

	/**
	 * Get a string of connect parameters
	 * @return a string of connect parameters
	 */
	private String getConnectParam() {
		return String.format(PATTERN_CONNECTION, this.endpointProtocol,
				this.endpoint, this.username, this.password);
	}

	/**
	 * Create a cloud blob client by connection parameters
	 * 
	 * @param connectParam
	 * @return a cloud blob client
	 * @throws URISyntaxException
	 *             when endpoint is wrong
	 * @throws InvalidKeyException
	 *             when user credentials are wrong
	 */
	private CloudBlobClient createClient(String connectParam) throws InvalidKeyException, URISyntaxException {
		CloudBlobClient serviceClient = null;
		CloudStorageAccount account = CloudStorageAccount
				.parse(connectParam);
		serviceClient = account.createCloudBlobClient();

		return serviceClient;
	}

}
