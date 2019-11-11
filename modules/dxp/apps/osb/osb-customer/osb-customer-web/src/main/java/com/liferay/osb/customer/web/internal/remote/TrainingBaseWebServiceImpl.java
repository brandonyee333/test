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

package com.liferay.osb.customer.web.internal.remote;

import com.liferay.osb.customer.service.TrainingBaseWebService;
import com.liferay.osb.customer.web.internal.configuration.CustomerWebConfigurationValues;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.nio.reactor.IOReactorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = TrainingBaseWebService.class)
public class TrainingBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements TrainingBaseWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public JSONObject post(Map<String, String> parameters)
		throws PortalException {

		String response = null;

		try {
			String url = _ENDPOINT;

			if (!parameters.isEmpty()) {
				String queryString = URLEncodedUtils.format(
					toNameValuePairs(parameters), Charset.forName("UTF-8"));

				url += "?" + queryString;
			}

			HttpPost httpPost = new HttpPost(url);

			addHeaders(httpPost, _headers);

			response = execute(httpPost);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(
			CustomerWebConfigurationValues.
				REMOTE_REST_SERVICE_API_TRAINING_HOST);
		setHostPort(Http.HTTPS_PORT);
		setProtocol(Http.HTTPS);

		return super.execute(httpRequestBase);
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static final String _ENDPOINT = "/o/training/provision_skilljar";

	private static final Map<String, String> _headers =
		new HashMap<String, String>() {
			{
				put(
					"Authorization",
					"Bearer" + StringPool.SPACE +
						CustomerWebConfigurationValues.
							REMOTE_REST_SERVICE_API_TRAINING_TOKEN);
			}
		};

}