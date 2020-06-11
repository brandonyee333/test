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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class Keyword {

	public Keyword() {
	}

	public Keyword(String type, String value) {
		_type = type;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Keyword)) {
			return false;
		}

		Keyword keyword = (Keyword)obj;

		if (Objects.equals(_type, keyword._type) &&
			Objects.equals(_value, keyword._value)) {

			return true;
		}

		return false;
	}

	public String getType() {
		return _type;
	}

	@JsonProperty("keyword")
	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_type, _value);
	}

	public void setType(String type) {
		_type = type;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _type;
	private String _value;

}