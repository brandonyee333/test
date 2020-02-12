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

package com.liferay.osb.customer.metrics.sync.zendesk.transformer;

import com.liferay.osb.customer.metrics.sync.zendesk.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "routing.key=zendesk.metrics.group.update",
	service = ZendeskGroupTransformer.class
)
public class ZendeskGroupTransformer extends BaseTransformer {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("groups");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject metricsJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = metricsJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				Object value = metricsJSONObject.get(key);

				columnMap = insertColumnMapValue(
					columnMap, metricsJSONObject, key, value);
			}

			JSONObject groupJSONObject = buildMetricsJSONObject(
				"ZendeskGroup_", columnMap);

			_messagePublisherUtil.sendMetricsMessage(groupJSONObject);
		}

		if (jsonObject.getInt("count") >= 100) {
			_processNextPage(jsonObject.getString("next_page"));
		}
	}

	private void _processNextPage(String nextPage) throws PortalException {
		if (Validator.isNull(nextPage)) {
			return;
		}

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + ZendeskRESTEndpoints.GROUPS;

		Map<String, String> parameters = new HashMap<>();

		int pos = nextPage.indexOf("page");

		parameters.put("page", nextPage.substring(pos + 5));

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null, "zendesk.metrics.group.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}