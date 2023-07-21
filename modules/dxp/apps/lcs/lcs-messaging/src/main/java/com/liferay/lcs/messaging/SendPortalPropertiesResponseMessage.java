/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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