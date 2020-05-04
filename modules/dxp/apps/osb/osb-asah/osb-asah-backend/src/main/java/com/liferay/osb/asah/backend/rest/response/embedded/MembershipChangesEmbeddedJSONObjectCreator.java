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

package com.liferay.osb.asah.backend.rest.response.embedded;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
public class MembershipChangesEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public MembershipChangesEmbeddedJSONObjectCreator(
		ElasticsearchInvoker elasticsearchInvoker, String expand) {

		_elasticsearchInvoker = elasticsearchInvoker;
		_expand = expand;
	}

	@Override
	public Map<String, JSONObject> create(JSONArray jsonArray) {
		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		String[] expandParts = _expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("account-names")) {
				return _getAccountNamesJSONObjects(jsonArray);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		return null;
	}

	@Override
	public JSONObject create(String id) throws Exception {
		return create(id, _elasticsearchInvoker.get("membership-changes", id));
	}

	private Map<String, JSONObject> _getAccountNamesJSONObjects(
		JSONArray membershipChangesJSONArray) {

		Map<String, JSONObject> accountNamesJSONObjects = new HashMap<>();

		Set<String> individualIds = new HashSet<>();

		for (int i = 0; i < membershipChangesJSONArray.length(); i++) {
			JSONObject membershipChangeJSONObject =
				membershipChangesJSONArray.getJSONObject(i);

			individualIds.add(
				membershipChangeJSONObject.getString("individualId"));
		}

		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("id", individualIds)));

		Map<String, JSONObject> individualAccountNamesJSONObjects =
			FaroInfoIndividualUtil.getIndividualAccountNamesJSONObjects(
				_elasticsearchInvoker, individualsJSONArray);

		for (int i = 0; i < membershipChangesJSONArray.length(); i++) {
			JSONObject membershipChangeJSONObject =
				membershipChangesJSONArray.getJSONObject(i);

			String individualId = membershipChangeJSONObject.getString(
				"individualId");

			JSONObject individualAccountNamesJSONObject =
				individualAccountNamesJSONObjects.get(individualId);

			accountNamesJSONObjects.put(
				membershipChangeJSONObject.getString("id"),
				individualAccountNamesJSONObject);
		}

		return accountNamesJSONObjects;
	}

	private static final Log _log = LogFactory.getLog(
		MembershipChangesEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;

}