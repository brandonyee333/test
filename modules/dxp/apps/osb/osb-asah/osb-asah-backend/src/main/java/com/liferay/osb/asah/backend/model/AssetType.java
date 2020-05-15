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

package com.liferay.osb.asah.backend.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Marcellus Tavares
 */
public enum AssetType {

	BLOG("blog"), CUSTOM("custom"), DOCUMENT("document"), FORM("form"),
	INDIVIDUAL_METRIC("individualMetric"), JOURNAL("journal"), PAGE("page"),
	SITE("site");

	public static AssetType of(String value) {
		return Optional.ofNullable(
			_assetTypes.get(value)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	public String getValue() {
		return _value;
	}

	private AssetType(String value) {
		_value = value;
	}

	private static Map<String, AssetType> _assetTypes = new HashMap<>();

	static {
		for (AssetType assetType : values()) {
			_assetTypes.put(assetType.getValue(), assetType);
		}
	}

	private final String _value;

}