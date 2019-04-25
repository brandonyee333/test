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
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
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
	immediate = true, property = "routing.key=zendesk.metrics.user.update",
	service = ZendeskUserTransformer.class
)
public class ZendeskUserTransformer extends BaseTransformer {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("users");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = userJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				Object value = userJSONObject.get(key);

				columnMap = insertColumnMapValue(
					columnMap, userJSONObject, key, value);
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskUser", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}

		_syncStateLocalService.updateSyncState(
			ZendeskUser.class.getName(), jsonObject.getLong("end_time"));

		if (jsonObject.getInt("count") >= 1000) {
			_processNextPage(jsonObject.getString("next_page"));
		}
	}

	private void _processNextPage(String nextPage) throws PortalException {
		if (Validator.isNull(nextPage)) {
			return;
		}

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.INCREMENTAL_USERS;

		Map<String, String> parameters = new HashMap<>();

		String startTime = nextPage.substring(
			nextPage.indexOf("start_time") + 11);

		parameters.put("start_time", startTime);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null, "zendesk.metrics.user.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

}