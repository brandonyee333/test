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

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class SendPortalPropertiesResponseMessage extends ResponseMessage {

	public String getHashCode() {
		return _hashCode;
	}

	public Map<String, String> getPortalProperties() {
		return _portalProperties;
	}

	public void setHashCode(String hashCode) {
		_hashCode = hashCode;
	}

	public void setPortalProperties(Map<String, String> portalProperties) {
		_portalProperties = portalProperties;
	}

	private String _hashCode;
	private Map<String, String> _portalProperties =
		new HashMap<String, String>();

}