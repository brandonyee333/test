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

import com.liferay.osb.customer.metrics.sync.model.SyncState;
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

import java.time.Instant;

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
	immediate = true, property = "routing.key=zendesk.metrics.ticket.update",
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
			JSONObject ticketFieldJSONObject = jsonArray.getJSONObject(i);

			long id = ticketFieldJSONObject.getLong("id");
			String title = ticketFieldJSONObject.getString("title");

			_ticketFields.put(id, title);
		}
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, String> columnMap = new HashMap<>();

		SyncState syncState = _syncStateLocalService.fetchSyncState(
			ZendeskTicket.class.getName());

		JSONArray jsonArray = jsonObject.getJSONArray("tickets");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject ticketJSONObject = jsonArray.getJSONObject(i);

			String updatedAt = ticketJSONObject.getString("updated_at");

			if (_isSystemUpdate(syncState.getLastRunTime(), updatedAt)) {
				continue;
			}

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
						String value = fieldJSONObject.getString("value");

						columnMap.put(_getTicketFieldName(id), value);
					}
				}
				else {
					columnMap.put(key, ticketJSONObject.getString(key));
				}
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskTicket", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}

		_syncStateLocalService.updateSyncState(
			ZendeskTicket.class.getName(), jsonObject.getLong("end_time"));

		_processNextPage(jsonObject.getString("next_page"));
	}

	private String _getTicketFieldName(long id) throws PortalException {
		String name = _ticketFields.get(id);

		name = StringUtil.replace(
			name, new String[] {StringPool.DASH, StringPool.SPACE},
			new String[] {StringPool.UNDERLINE, StringPool.UNDERLINE});

		return StringUtil.toLowerCase(name);
	}

	private boolean _isSystemUpdate(long startTime, String updatedAt)
		throws Exception {

		Instant instant = Instant.parse(updatedAt);

		if (instant.getEpochSecond() < startTime) {
			return true;
		}

		return false;
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

		if (nextPage.contains("end_time")) {
			String endTime = nextPage.substring(
				nextPage.indexOf("end_time") + 9,
				nextPage.indexOf(StringPool.AMPERSAND));

			parameters.put("end_time", endTime);
		}

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