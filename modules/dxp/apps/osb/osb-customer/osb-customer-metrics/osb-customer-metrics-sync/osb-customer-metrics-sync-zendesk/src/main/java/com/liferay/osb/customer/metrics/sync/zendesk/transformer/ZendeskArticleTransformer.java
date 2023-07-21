/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.transformer;

import com.liferay.osb.customer.metrics.sync.service.SyncStateLocalService;
import com.liferay.osb.customer.metrics.sync.zendesk.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskArticle;
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
	immediate = true, property = "topic.pattern=zendesk.metrics.article.update",
	service = ZendeskArticleTransformer.class
)
public class ZendeskArticleTransformer extends BaseTransformer {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();

		JSONArray jsonArray = jsonObject.getJSONArray("articles");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject articleJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> iterator = articleJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				Object value = articleJSONObject.get(key);

				columnMap = insertColumnMapValue(
					columnMap, articleJSONObject, key, value);
			}

			JSONObject metricsJSONObject = buildMetricsJSONObject(
				"ZendeskArticle", columnMap);

			_messagePublisherUtil.sendMetricsMessage(metricsJSONObject);
		}

		_syncStateLocalService.updateSyncState(
			ZendeskArticle.class.getName(), jsonObject.getLong("end_time"));

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
				ZendeskRESTEndpoints.INCREMENTAL_ARTICLES;

		Map<String, String> parameters = new HashMap<>();

		String startTime = nextPage.substring(
			nextPage.indexOf("start_time") + 11);

		parameters.put("start_time", startTime);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null,
			"zendesk.metrics.article.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

}