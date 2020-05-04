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

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class AssetId {

	public static AssetId of(String fieldName, String fieldValue) {
		return new AssetId(fieldName, fieldValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetId)) {
			return false;
		}

		AssetId assetId = (AssetId)obj;

		if (Objects.equals(_fieldName, assetId._fieldName) &&
			Objects.equals(_fieldValue, assetId._fieldValue)) {

			return true;
		}

		return false;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getFieldValue() {
		return _fieldValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_fieldName, _fieldValue);
	}

	private AssetId(String fieldName, String fieldValue) {
		_fieldName = fieldName;
		_fieldValue = fieldValue;
	}

	private final String _fieldName;
	private final String _fieldValue;

}