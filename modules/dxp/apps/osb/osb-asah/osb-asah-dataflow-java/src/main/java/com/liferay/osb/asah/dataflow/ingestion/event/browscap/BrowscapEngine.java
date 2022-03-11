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

package com.liferay.osb.asah.dataflow.ingestion.event.browscap;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

import java.io.IOException;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 * @author André Miranda
 */
public class BrowscapEngine {

	public static BrowscapDevice getDevice(String userAgent) {
		if (StringUtils.isBlank(userAgent)) {
			return null;
		}

		return new BrowscapDevice(_browscapEngine._parser.parse(userAgent));
	}

	private BrowscapEngine() {
		try {
			UserAgentService userAgentService = new UserAgentService();

			_parser = userAgentService.loadParser(
				Arrays.asList(
					BrowsCapField.BROWSER, BrowsCapField.BROWSER_TYPE,
					BrowsCapField.DEVICE_TYPE, BrowsCapField.IS_CRAWLER,
					BrowsCapField.PLATFORM));
		}
		catch (IOException | ParseException exception) {
			_log.error("Unable to load Browscap parser data", exception);

			throw new IllegalStateException(exception);
		}

		if (_log.isInfoEnabled()) {
			_log.info("BrowscapEngine loaded successfully");
		}
	}

	private static final Log _log = LogFactory.getLog(BrowscapEngine.class);

	private static final BrowscapEngine _browscapEngine = new BrowscapEngine();

	private final UserAgentParser _parser;

}