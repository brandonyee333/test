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

import com.liferay.osb.asah.common.util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Marcellus Tavares
 */
public class PropertyFilter {

	public static PropertyFilter of(Map<String, Object> propertyFilter) {
		return new PropertyFilter(
			MapUtil.get(propertyFilter, "filter"),
			MapUtil.get(propertyFilter, "negate"));
	}

	public PropertyFilter(String filterString, boolean negate) {
		_negate = negate;

		List<String> filterTokens = _getFilterTokens(filterString);

		_operator = filterTokens.get(1);
		_propertyName = filterTokens.get(0);
		_propertyValue = filterTokens.get(2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PropertyFilter)) {
			return false;
		}

		PropertyFilter propertyFilter = (PropertyFilter)obj;

		if (Objects.equals(_negate, propertyFilter._negate) &&
			Objects.equals(_operator, propertyFilter._operator) &&
			Objects.equals(_propertyName, propertyFilter._propertyName) &&
			Objects.equals(
				_propertyNamespace, propertyFilter._propertyNamespace) &&
			Objects.equals(_propertyValue, propertyFilter._propertyValue)) {

			return true;
		}

		return false;
	}

	public String getOperator() {
		return _operator;
	}

	public String getPropertyName() {
		return _propertyNamespace + _propertyName;
	}

	public String getPropertyNamespace() {
		return _propertyNamespace;
	}

	public String getPropertyValue() {
		return _propertyValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_operator, _negate, _propertyName, _propertyNamespace,
			_propertyValue);
	}

	public boolean isNegate() {
		return _negate;
	}

	public void setPropertyNamespace(String propertyNamespace) {
		_propertyNamespace = propertyNamespace;
	}

	private static List<String> _getFilterTokens(String filterString) {
		List<String> tokens = new ArrayList<>();

		for (String token : filterString.split(" ")) {
			if (!StringUtils.isBlank(token)) {
				tokens.add(token);
			}
		}

		if (tokens.size() != 3) {
			throw new IllegalArgumentException(
				"Invalid filter " + filterString);
		}

		return tokens;
	}

	private final boolean _negate;
	private final String _operator;
	private final String _propertyName;
	private String _propertyNamespace = "";
	private final String _propertyValue;

}