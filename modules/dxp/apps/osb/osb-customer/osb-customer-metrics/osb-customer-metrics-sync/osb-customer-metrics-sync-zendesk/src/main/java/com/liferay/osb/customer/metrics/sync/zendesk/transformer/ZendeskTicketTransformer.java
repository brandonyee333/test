/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.transformer;

import com.liferay.osb.customer.metrics.sync.service.SyncStateLocalService;
import com.liferay.osb.customer.metrics.sync.zendesk.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.metrics.ticket.update",
	service = ZendeskTicketTransformer.class
)
public class ZendeskTicketTransformer extends BaseTransformer {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.TICKET_FIELDS;

		JSONObject response = _zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		JSONArray jsonArray = response.getJSONArray("ticket_fields");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			long id = jsonObject.getLong("id");
			String title = jsonObject.getString("title");

			_ticketFields.put(id, title);
		}
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("tickets");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject ticketJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = ticketJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				if (ArrayUtil.contains(_EXCLUDED_FIELDS, key)) {
					continue;
				}

				if (key.equals("custom_fields")) {
					JSONArray fieldsJSONArray = ticketJSONObject.getJSONArray(
						key);

					for (int j = 0; j < fieldsJSONArray.length(); j++) {
						JSONObject fieldJSONObject =
							fieldsJSONArray.getJSONObject(j);

						long id = fieldJSONObject.getLong("id");
						Object value = fieldJSONObject.get("value");

						columnMap = insertColumnMapValue(
							columnMap, fieldJSONObject, _getTicketFieldName(id),
							value);
					}
				}
				else {
					Object value = ticketJSONObject.get(key);

					columnMap = insertColumnMapValue(
						columnMap, ticketJSONObject, key, value);
				}
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskTicket", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}

		_syncStateLocalService.updateSyncState(
			ZendeskTicket.class.getName(), jsonObject.getLong("end_time"));

		if (jsonObject.getInt("count") >= 1000) {
			_processNextPage(jsonObject.getString("next_page"));
		}
	}

	private String _getTicketFieldName(long id) throws PortalException {
		String name = _ticketFields.get(id);

		name = StringUtil.replace(
			name,
			new String[] {
				StringPool.DASH, StringPool.SPACE, StringPool.OPEN_PARENTHESIS,
				StringPool.CLOSE_PARENTHESIS
			},
			new String[] {
				StringPool.UNDERLINE, StringPool.UNDERLINE, StringPool.BLANK,
				StringPool.BLANK
			});

		return StringUtil.toLowerCase(name);
	}

	private void _processNextPage(String nextPage) throws PortalException {
		if (Validator.isNull(nextPage)) {
			return;
		}

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.INCREMENTAL_TICKETS;

		Map<String, String> parameters = new HashMap<>();

		String startTime = nextPage.substring(
			nextPage.indexOf("start_time") + 11);

		parameters.put("start_time", startTime);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null, "zendesk.metrics.ticket.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	private static final String[] _EXCLUDED_FIELDS = {"fields"};

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

	private final Map<Long, String> _ticketFields = new HashMap<>();

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}