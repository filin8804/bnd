package org.osgi.service.indexer.impl;

import java.util.List;

import org.junit.Ignore;
import org.osgi.service.indexer.Capability;
import org.osgi.service.indexer.Requirement;
import org.osgi.service.indexer.Resource;
import org.osgi.service.indexer.ResourceAnalyzer;

@Ignore
public class BadAnalyzer implements ResourceAnalyzer {

	public void analyzeResource(Resource resource, List<Capability> capabilities, List<Requirement> requirements)
			throws Exception {
		throw new IllegalStateException("Bwa Ha Ha Ha!");
	}

}
