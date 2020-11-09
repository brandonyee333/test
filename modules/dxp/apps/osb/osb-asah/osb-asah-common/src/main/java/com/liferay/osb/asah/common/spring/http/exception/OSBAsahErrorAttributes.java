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

package com.liferay.osb.asah.common.spring.http.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leslie Wong
 */
public abstract class OSBAsahErrorAttributes {

	public Map<String, Object> getErrorAttributes() {
		Map<String, Object> filteredErrorAttributes = new HashMap<>();

		List<String> errorAttributeFilterList = getErrorAttributeFilterList();

		for (Map.Entry<String, Object> entry : _errorAttributes.entrySet()) {
			String attributeName = entry.getKey();

			if (errorAttributeFilterList.contains(attributeName)) {
				filteredErrorAttributes.put(attributeName, entry.getValue());
			}
		}

		return filteredErrorAttributes;
	}

	public void setErrorAttribute(String attributeName, Object attributeValue) {
		_errorAttributes.put(attributeName, attributeValue);
	}

	public void setErrorAttributes(Map<String, Object> errorAttributes) {
		_errorAttributes = errorAttributes;
	}

	protected abstract List<String> getErrorAttributeFilterList();

	private Map<String, Object> _errorAttributes = new HashMap<>();

}