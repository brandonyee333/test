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

import com.liferay.osb.customer.metrics.sync.service.SyncStateLocalService;
import com.liferay.osb.customer.metrics.sync.zendesk.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskTicketEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
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
	immediate = true,
	property = "routing.key=zendesk.metrics.ticket.event.update",
	service = ZendeskTicketEventTransformer.class
)
public class ZendeskTicketEventTransformer extends BaseTransformer {

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
			JSONObject ticketFieldJSONObject = jsonArray.getJSONObject(i);

			long id = ticketFieldJSONObject.getLong("id");
			String title = ticketFieldJSONObject.getString("title");

			_ticketFields.put(id, title);
		}
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONArray jsonArray = jsonObject.getJSONArray("ticket_events");

		_processEvents(jsonArray);

		_syncStateLocalService.updateSyncState(
			ZendeskTicketEvent.class.getName(), jsonObject.getLong("end_time"));

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
			}
		);

		return StringUtil.toLowerCase(name);
	}

	private void _processEvents(JSONArray jsonArray) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject eventJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = eventJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				if (key.equals("child_events")) {
					JSONArray childEventJSONArray = eventJSONObject.getJSONArray(
						key);

					_processEvents(childEventJSONArray);
				}
				else if (key.equals("custom_ticket_fields")) {
					JSONObject ticketFieldsJSONObject = eventJSONObject.getJSONObject(
						"custom_ticket_fields");

					Iterator<String> iterator2 = ticketFieldsJSONObject.keys();

					while (iterator2.hasNext()) {
						String key2 = iterator2.next();

						Object value = ticketFieldsJSONObject.get(key2);

						columnMap = insertColumnMapValue(
							columnMap, ticketFieldsJSONObject,
							_getTicketFieldName(Long.valueOf(key2)),
							_transformValue(key, value));
					}
				}
				else {
					Object value = eventJSONObject.get(key);

					columnMap = insertColumnMapValue(
						columnMap, eventJSONObject, key,
						_transformValue(key, value));
				}
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskTicketEvent", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}
	}

	private void _processNextPage(String nextPage) throws PortalException {
		if (Validator.isNull(nextPage)) {
			return;
		}

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.INCREMENTAL_TICKET_EVENTS;

		Map<String, String> parameters = new HashMap<>();

		parameters.put("include", "comment_events");

		String endTime = nextPage.substring(
			nextPage.indexOf("end_time") + 9,
			nextPage.indexOf(StringPool.AMPERSAND));

		parameters.put("end_time", endTime);

		String startTime = nextPage.substring(
			nextPage.indexOf("start_time") + 11);

		parameters.put("start_time", startTime);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null,
			"zendesk.metrics.ticket.event.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	private Object _transformValue(String key, Object value) {
		if (key.equals("internal_target_date")) {
			if (value.equals(StringPool.BLANK)) {
				return null;
			}
		}
		else if (key.equals("is_public")) {
			if (value.equals("t")) {
				return true;
			}
			else {
				return false;
			}
		}

		return value;
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

	private final Map<Long, String> _ticketFields = new HashMap<>();

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}