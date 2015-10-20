/**
 * 
 */
package com.shouleta.azure.cli.util;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.junit.Test;

import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

/**
 * @author liang
 *
 */
public class TestClientBuilder {

	@Test
	public void textChageDirectoryRoot() throws InvalidKeyException,
			URISyntaxException {
		OperationContext.setLoggingEnabledByDefault(true);
		CloudBlobClient client = ClientBuilder
				.get()
				.setUsername("xxxx")
				.setPassword("xxxxx")
				.build();
		assertNotNull(client);
		
		CloudBlobContainer container;
		try {
			container = client.getContainerReference("eos-test");
			container.createIfNotExists();
		} catch (StorageException e) {
			e.printStackTrace();
			fail("Failed to connect to azure");
		}
	}

}
