/**
 * 
 */
package com.shouleta.azure.cli.cmd;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

/**
 * @author liang
 */
public class CmdCtx {
	
	private CloudBlobClient client;
	
	private CloudBlobContainer container;
	
	private Path path = Paths.get("/");
	
	/**
	 * Singleton holder
	 * @author liang
	 *
	 */
	private static class Inner {
		private static CmdCtx instance = new CmdCtx();
	}
	
	/**
	 * Hide the default constructor
	 */
	private CmdCtx() {}
	
	/**
	 * 
	 * @return
	 */
	public static CmdCtx get() {
		return Inner.instance;
	}
	
	/**
	 * Set client
	 * @param client
	 */
	public void setClient(CloudBlobClient client) {
		this.client = client;
	}
	
	/**
	 * Get the cloud blob client
	 * @return
	 */
	public CloudBlobClient getClient() {
		return this.client;
	}
	
	/**
	 * 
	 * @param containerName
	 * @throws StorageException 
	 * @throws URISyntaxException 
	 */
	public void changeContainer(String containerName) throws URISyntaxException, StorageException {
		this.container = this.client.getContainerReference(containerName);
		this.changeDirectory("/");
	}
	
	/**
	 * Change current path (Just like "cd")
	 * @param path
	 */
	public void changeDirectory(String path) {
		this.path = Paths.get(this.path.toString(), path).normalize();
	}
	
	/**
	 * Get child path
	 * @param fileName
	 * @return
	 */
	public String getChildPath(String fileName) {
		return Paths.get(this.path.toString(), fileName).normalize().toString();
	}

	/**
	 * Get current container
	 * @return
	 */
	public CloudBlobContainer getContainer() {
		return container;
	}
	
	/**
	 * Check if the context is ready to do any operations
	 * @throws IllegalArgumentException if client or contaner is null
	 */
	public void checkIsReady() {
		if (this.client == null || this.container == null) {
			throw new IllegalArgumentException("Client or Container is not ready.");
		}
	}

	/**
	 * Get the current path
	 * @return
	 */
	public String getPath() {
		return this.path.toString();
	}
	
}
