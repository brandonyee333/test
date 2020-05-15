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

package com.liferay.osb.asah.stream.curator.model;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseClickModel extends BaseAssetModel {

	public void addClicks(long clicks) {
		_clicks += clicks;
	}

	public long getClicks() {
		return _clicks;
	}

	public String getElementText() {
		return _elementText;
	}

	public String getElementType() {
		return _elementType;
	}

	public String getElementURL() {
		return _elementURL;
	}

	public void setClicks(long clicks) {
		_clicks = clicks;
	}

	public void setElement(Map<String, String> eventProperties) {
		String elementText = eventProperties.get("text");

		if (elementText == null) {
			elementText = "";
		}

		setElementText(elementText);

		String elementType = eventProperties.get("tagName");

		if (Objects.equals(elementType, "a")) {
			elementType = "link";
		}
		else if (Objects.equals(elementType, "img")) {
			elementType = "image";
		}
		else {
			elementType = "other";
		}

		setElementType(elementType);

		String elementURL = eventProperties.get("href");

		if (StringUtils.isEmpty(elementURL)) {
			elementURL = eventProperties.get("src");

			if (StringUtils.isEmpty(elementURL)) {
				elementURL = "";
			}
		}

		setElementURL(elementURL);
	}

	public void setElementText(String elementText) {
		_elementText = elementText;
	}

	public void setElementType(String elementType) {
		_elementType = elementType;
	}

	public void setElementURL(String elementURL) {
		_elementURL = elementURL;
	}

	private long _clicks;
	private String _elementText;
	private String _elementType;
	private String _elementURL;

}