package com.shouleta.azure.cli.cmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.shouleta.azure.cli.cmd.param.CpParams;
import com.shouleta.azure.cli.cmd.param.Params;

public class Cp implements Cmd {

	private final static Logger LOGGER = LoggerFactory.getLogger(Cp.class);

	@Override
	public CmdResult invoke(Params params) throws IOException {
		CmdCtx ctx = params.getCtx();
		ctx.checkIsReady();

		String source = params.get(CpParams.Key.source.toString());
		String target = params.get(CpParams.Key.target.toString());
		
		if (StringUtils.isNotBlank(target)) {
			target = ctx.getChildPath(target);
		} else {
			target = ctx.getPath();
		}

		copy(ctx, target, source);
		return null;
	}

	/**
	 * 
	 * @param ctx
	 * @param target
	 * @param source
	 * @throws IOException
	 */
	private void copy(final CmdCtx ctx, String target, String source)
			throws IOException {	
		final Path toPath = Paths.get(target);
		final Path fromPath = Paths.get(source);
		Files.walkFileTree(fromPath, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				Objects.requireNonNull(file);
				Objects.requireNonNull(attrs);

				if (Files.isDirectory(file)) {
					return FileVisitResult.CONTINUE;
				}

				try {
					cp(ctx, toPath.toString() + "/" + fromPath.getName(fromPath.getNameCount() - 1).toString() + Paths.get(toPath.toString(), fromPath.relativize(file).toString()), file.toFile());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (StorageException e) {
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;
			}

			private void cp(CmdCtx ctx, String blobAddress, File src) throws URISyntaxException, StorageException, FileNotFoundException, IOException {
				CloudBlockBlob blob = ctx.getContainer().getBlockBlobReference(blobAddress);
				blob.upload(new FileInputStream(src), src.length());
				System.out.println(String.format("copied from: %s \r\n         to: 【%s】%s", src.getAbsolutePath(), ctx.getContainer().getName(), blobAddress));
			}

		});
	}

}
