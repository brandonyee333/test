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
import java.util.Map;

/**
 * @author Leslie Wong
 */
public abstract class OSBAsahErrorAttributes {

	public abstract Map<String, Object> getErrorAttributes();

	public void setErrorAttribute(String attributeName, Object attributeValue) {
		errorAttributes.put(attributeName, attributeValue);
	}

	public void setErrorAttributes(Map<String, Object> errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	protected Map<String, Object> errorAttributes = new HashMap<>();

}