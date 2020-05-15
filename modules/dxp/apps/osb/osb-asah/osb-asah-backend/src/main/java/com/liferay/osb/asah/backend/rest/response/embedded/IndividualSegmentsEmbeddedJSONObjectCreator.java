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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class IndividualSegmentsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public IndividualSegmentsEmbeddedJSONObjectCreator(
		ElasticsearchInvoker dxpRawElasticsearchInvoker,
		ElasticsearchInvoker faroInfoElasticsearchInvoker, String expand) {

		_dxpRawElasticsearchInvoker = dxpRawElasticsearchInvoker;
		_faroInfoElasticsearchInvoker = faroInfoElasticsearchInvoker;
		_expand = expand;
	}

	@Override
	public JSONObject create(String id) {
		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		JSONObject embeddedJSONObject = new JSONObject();

		String[] expandParts = _expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("referenced-objects")) {
				embeddedJSONObject.put(
					expandPart, _getReferencedObjectsJSONObject(id));
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		if (embeddedJSONObject.length() > 0) {
			return embeddedJSONObject;
		}

		return null;
	}

	private void _addReferencedObject(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		JSONArray referencedIdsJSONArray,
		JSONObject referencedObjectsJSONObject) {

		if ((referencedIdsJSONArray == null) ||
			(referencedIdsJSONArray.length() == 0)) {

			referencedObjectsJSONObject.put(collectionName, new JSONArray());

			return;
		}

		referencedObjectsJSONObject.put(
			collectionName,
			elasticsearchInvoker.get(
				collectionName,
				QueryBuilders.termsQuery(
					"id", JSONUtil.toStringList(referencedIdsJSONArray))));
	}

	private JSONObject _getReferencedObjectsJSONObject(String id) {
		JSONObject individualSegmentJSONObject =
			_faroInfoElasticsearchInvoker.fetch("individual-segments", id);

		try {
			JSONObject jsonObject = new JSONObject();

			_addReferencedObject(
				"assets", _faroInfoElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray("referencedAssetIds"),
				jsonObject);
			_addReferencedObject(
				"field-mappings", _faroInfoElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray(
					"referencedFieldMappingIds"),
				jsonObject);
			_addReferencedObject(
				"groups", _dxpRawElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray("referencedGroupIds"),
				jsonObject);
			_addReferencedObject(
				"organizations", _faroInfoElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray(
					"referencedOrganizationIds"),
				jsonObject);
			_addReferencedObject(
				"roles", _dxpRawElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray("referencedRoleIds"),
				jsonObject);
			_addReferencedObject(
				"teams", _dxpRawElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray("referencedTeamIds"),
				jsonObject);
			_addReferencedObject(
				"user-groups", _dxpRawElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray(
					"referencedUserGroupIds"),
				jsonObject);
			_addReferencedObject(
				"users", _dxpRawElasticsearchInvoker,
				individualSegmentJSONObject.optJSONArray("referencedUserIds"),
				jsonObject);

			return jsonObject;
		}
		catch (Exception e) {
			return null;
		}
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _dxpRawElasticsearchInvoker;
	private final String _expand;
	private final ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}