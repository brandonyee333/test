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

import com.liferay.osb.asah.extractor.constants.FiftyOneDegreesPropertyNames;

import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.entities.Values;

import fiftyone.properties.MatchMethods;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Inácio Nery
 */
public class FiftyOneDegreesDevice {

	public FiftyOneDegreesDevice(Match match) {
		_match = match;
	}

	public String getBrowserName() {
		return _getValueString(FiftyOneDegreesPropertyNames.BROWSER_NAME, true);
	}

	public String getCrawler() {
		return _getValueString(FiftyOneDegreesPropertyNames.IS_CRAWLER, false);
	}

	public String getDeviceType() {
		return _getValueString(FiftyOneDegreesPropertyNames.DEVICE_TYPE, true);
	}

	public String getPlatformName() {
		return _getValueString(
			FiftyOneDegreesPropertyNames.PLATFORM_NAME, true);
	}

	private String _getValueString(String propertyName, boolean skipMatchNone) {
		String value = _VALUE_UNKNOWN;

		if (skipMatchNone && (_match.getMethod() == MatchMethods.NONE)) {
			return value;
		}

		try {
			Values values = _match.getValues(propertyName);

			if (values != null) {
				String matchValue = String.valueOf(values);

				if (!matchValue.equals(_VALUE_UNKNOWN)) {
					value = matchValue;
				}
			}
		}
		catch (IOException ioe) {
			if (_logger.isWarnEnabled()) {
				_logger.warn(
					"Unable to get string value for property name: " +
						propertyName,
					ioe);
			}
		}

		return value;
	}

	private static final String _VALUE_UNKNOWN = "Unknown";

	private static final Logger _logger = LoggerFactory.getLogger(
		FiftyOneDegreesDevice.class);

	private final Match _match;

}