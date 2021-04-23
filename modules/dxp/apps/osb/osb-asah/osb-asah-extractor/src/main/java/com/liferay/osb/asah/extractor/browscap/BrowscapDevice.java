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
import com.blueconic.browscap.Capabilities;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author Inácio Nery
 * @author André Miranda
 */
public class BrowscapDevice {

	public BrowscapDevice(Capabilities capabilities) {
		_capabilities = capabilities;
	}

	public String getBrowserName() {
		return _capabilities.getBrowser();
	}

	public String getCrawler() {
		Map<BrowsCapField, String> values = _capabilities.getValues();

		return StringUtils.capitalize(values.get(BrowsCapField.IS_CRAWLER));
	}

	public String getDeviceType() {
		return _capabilities.getDeviceType();
	}

	public String getPlatformName() {
		return _capabilities.getPlatform();
	}

	private final Capabilities _capabilities;

}