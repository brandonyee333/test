/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "topic.pattern=zendesk.metrics.ticket.metrics.update",
	service = ZendeskTicketMetricsTransformer.class
)
public class ZendeskTicketMetricsTransformer extends BaseTransformer {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("ticket_metrics");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject metricsJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = metricsJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				Object value = metricsJSONObject.get(key);

				columnMap = insertColumnMapValue(
					columnMap, metricsJSONObject, key, value);
			}

			JSONObject ticketMetricsJSONObject = buildMetricsJSONObject(
				"ZendeskTicketMetrics", columnMap);

			_messagePublisherUtil.sendMetricsMessage(ticketMetricsJSONObject);
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
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.TICKET_METRICS;

		Map<String, String> parameters = new HashMap<>();

		int pos = nextPage.indexOf("page");

		parameters.put("page", nextPage.substring(pos + 5));

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null,
			"zendesk.metrics.ticket.metrics.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}