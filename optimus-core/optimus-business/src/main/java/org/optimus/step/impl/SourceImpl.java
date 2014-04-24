package org.optimus.step.impl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.model.event.GenericEvent;
import org.optimus.step.AbstractStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceImpl extends AbstractStep {
	final static Logger logger = LoggerFactory.getLogger(SourceImpl.class);

	private String basedir;
	private boolean recurse;

	/**
	 * Recursive listing with SimpleFileVisitor in JDK 7.
	 **/
	public final class FileListingVisitor {

		String root;
		List<GenericEvent> result;

		public void walkFileTree(String root, List<GenericEvent> result, boolean recurse) throws IOException {
			this.root = root;
			this.result = result;
			FileVisitor<Path> fileProcessor = new ProcessFile();
			Files.walkFileTree(Paths.get(root), fileProcessor);
		}

		private final class ProcessFile extends SimpleFileVisitor<Path> {
			@Override
			public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException {
				logger.info("Processing file {}", aFile);
				GenericEvent event = new GenericEvent(new HashMap());
				event.put("file", aFile.toAbsolutePath());
				result.add(event);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path aDir, BasicFileAttributes aAttrs) throws IOException {
				logger.info("Processing directory {}", aDir);
				return FileVisitResult.CONTINUE;
			}
		}
	}

	@Override
	public void init(Map map) {
		super.init(map);
		this.basedir = (String) map.get("basedir");
		this.recurse = (Boolean) map.get("recurse");
	}

	@Override
	public void execute(List<GenericEvent> in, List<GenericEvent> out) throws TechnicalException, FunctionnalException {
		try {
			(new FileListingVisitor()).walkFileTree(basedir, out, recurse);
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}

	@Override
	public String toString() {
		return "SourceImpl [basedir=" + basedir + ", recurse=" + recurse + ", getOutputs()=" + getOutputs() + ", getInputs()=" + getInputs() + "]";
	}
}
