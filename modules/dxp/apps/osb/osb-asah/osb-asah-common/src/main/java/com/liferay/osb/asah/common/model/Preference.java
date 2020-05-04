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

package com.liferay.osb.asah.common.model;

import java.util.Objects;

import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class Preference {

	public Preference(JSONObject jsonObject) {
		_id = jsonObject.getString("id");
		_key = jsonObject.getString("key");
		_value = jsonObject.getString("value");
	}

	public Preference(String key, String value) {
		_key = key;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Preference)) {
			return false;
		}

		Preference preference = (Preference)obj;

		if (Objects.equals(_id, preference._id) &&
			Objects.equals(_key, preference._key) &&
			Objects.equals(_value, preference._value)) {

			return true;
		}

		return false;
	}

	public String getId() {
		return _id;
	}

	public String getKey() {
		return _key;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _key, _value);
	}

	public void setId(String id) {
		_id = id;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _id;
	private String _key;
	private String _value;

}