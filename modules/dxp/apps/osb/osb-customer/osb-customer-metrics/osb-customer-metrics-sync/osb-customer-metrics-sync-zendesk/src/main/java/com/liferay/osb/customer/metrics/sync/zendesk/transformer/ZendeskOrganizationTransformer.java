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
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
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
	property = "routing.key=zendesk.metrics.organization.update",
	service = ZendeskOrganizationTransformer.class
)
public class ZendeskOrganizationTransformer extends BaseTransformer {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, String> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("organizations");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject organizationJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = organizationJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				if (key.equals("organization_fields")) {
					JSONObject fieldsJSONObject =
						organizationJSONObject.getJSONObject(key);

					Iterator<String> fieldIterator = fieldsJSONObject.keys();

					while (fieldIterator.hasNext()) {
						String fieldKey = fieldIterator.next();

						columnMap.put(
							fieldKey, fieldsJSONObject.getString(fieldKey));
					}
				}
				else {
					columnMap.put(key, organizationJSONObject.getString(key));
				}
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskOrganization", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}

		_syncStateLocalService.updateSyncState(
			ZendeskOrganization.class.getName(),
			jsonObject.getLong("end_time"));

		String nextPage = jsonObject.getString("next_page");

		if (Validator.isNotNull(nextPage)) {
			_getNextPage(nextPage);
		}
	}

	private void _getNextPage(String nextPage) throws PortalException {
		String endTime = nextPage.substring(
			nextPage.indexOf("end_time") + 9,
			nextPage.indexOf(StringPool.AMPERSAND));
		String startTime = nextPage.substring(
			nextPage.indexOf("start_time") + 11);

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.INCREMENTAL_ORGANIZATIONS;

		Map<String, String> parameters = new HashMap<>();

		parameters.put("end_time", endTime);
		parameters.put("start_time", startTime);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null,
			"zendesk.metrics.organization.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

}