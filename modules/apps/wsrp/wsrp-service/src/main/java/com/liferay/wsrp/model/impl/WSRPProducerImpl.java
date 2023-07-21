/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.model.impl;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerImpl extends WSRPProducerBaseImpl {

	public WSRPProducerImpl() {
	}

	@Override
	public String getURL(String prefixURL) {
		return prefixURL + "/wsdl/" + getUuid();
	}

}