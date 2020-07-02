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