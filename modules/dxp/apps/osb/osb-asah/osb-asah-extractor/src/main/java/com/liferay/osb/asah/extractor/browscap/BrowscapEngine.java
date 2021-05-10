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

package com.liferay.osb.asah.extractor.browscap;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

import java.io.IOException;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 * @author André Miranda
 */
@Component
public class BrowscapEngine {

	public BrowscapDevice getDevice(String userAgent) {
		return new BrowscapDevice(_parser.parse(userAgent));
	}

	@PostConstruct
	protected void init() {
		try {
			UserAgentService userAgentService = new UserAgentService();

			_parser = userAgentService.loadParser(
				Arrays.asList(
					BrowsCapField.BROWSER, BrowsCapField.BROWSER_TYPE,
					BrowsCapField.DEVICE_TYPE, BrowsCapField.IS_CRAWLER,
					BrowsCapField.PLATFORM));
		}
		catch (IOException | ParseException e) {
			_logger.error("Unable to load Browscap parser data", e);

			throw new IllegalStateException(e);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BrowscapEngine.class);

	private UserAgentParser _parser;

}