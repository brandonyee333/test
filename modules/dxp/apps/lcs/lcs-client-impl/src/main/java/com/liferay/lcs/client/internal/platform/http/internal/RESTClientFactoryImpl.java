/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.http.internal;

import com.liferay.lcs.client.internal.platform.http.RESTClient;
import com.liferay.lcs.client.internal.platform.http.RESTClientFactory;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(service = RESTClientFactory.class)
public class RESTClientFactoryImpl implements RESTClientFactory {

	@Override
	public RESTClient getInstance(
			Map<String, Object> properties, boolean oAuthEnabled)
		throws Exception {

		RESTClientImpl jsonWebServiceClientImpl = null;

		if (oAuthEnabled) {
			jsonWebServiceClientImpl = new OAuthRESTClientImpl();
		}
		else {
			jsonWebServiceClientImpl = new RESTClientImpl();
		}

		jsonWebServiceClientImpl.activate(properties);

		return jsonWebServiceClientImpl;
	}

}