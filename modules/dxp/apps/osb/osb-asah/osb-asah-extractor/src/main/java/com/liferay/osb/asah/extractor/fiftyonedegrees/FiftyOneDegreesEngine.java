/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.extractor.fiftyonedegrees;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.factories.StreamFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.zip.GZIPInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class FiftyOneDegreesEngine {

	public FiftyOneDegreesDevice getDevice(String userAgent) {
		try {
			Match match = _provider.match(userAgent);

			return new FiftyOneDegreesDevice(match);
		}
		catch (IOException ioe) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"Unable to get 51Degrees device for " + userAgent, ioe);
			}

			return null;
		}
	}

	@PreDestroy
	protected void destroy() throws IOException {
		_provider = null;

		if (_dataset != null) {
			_dataset.close();
		}
	}

	@PostConstruct
	protected void init() {
		try (InputStream inputStream = new GZIPInputStream(
				_getDatasetInputStream())) {

			_dataset = StreamFactory.create(IOUtils.toByteArray(inputStream));

			_provider = new Provider(_dataset);
		}
		catch (IOException ioe) {
			_logger.error("Unable to load 51Degrees provider data", ioe);

			throw new IllegalStateException(ioe);
		}
	}

	private InputStream _getDatasetInputStream() throws IOException {
		if (_resource.length > 0) {
			return _resource[0].getInputStream();
		}

		String classpath = System.getProperty("java.class.path");

		for (String path : classpath.split(File.pathSeparator)) {
			if (path.matches(".*?(51degrees.*gz)$")) {
				return new FileInputStream(path);
			}
		}

		throw new RuntimeException("Unable to load 51Degrees dataset");
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		FiftyOneDegreesEngine.class);

	private Dataset _dataset;
	private Provider _provider;

	@Value("classpath*:META-INF/com.51degrees.dat.enterprise-*.gz")
	private Resource[] _resource;

}