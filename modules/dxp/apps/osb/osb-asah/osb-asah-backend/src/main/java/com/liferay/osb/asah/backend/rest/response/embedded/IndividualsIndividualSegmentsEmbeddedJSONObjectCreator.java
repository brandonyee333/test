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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public class IndividualsIndividualSegmentsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public IndividualsIndividualSegmentsEmbeddedJSONObjectCreator(
		ElasticsearchInvoker elasticsearchInvoker, String expand,
		String individualId, MembershipDog membershipDog,
		ObjectMapper objectMapper) {

		_elasticsearchInvoker = elasticsearchInvoker;
		_expand = expand;
		_individualId = individualId;
		_membershipDog = membershipDog;
		_objectMapper = objectMapper;
	}

	@Override
	public Map<String, JSONObject> create(JSONArray jsonArray) {
		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		String[] expandParts = _expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("active-membership")) {
				return _getMembershipJSONObjects(jsonArray);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		return null;
	}

	@Override
	public JSONObject create(String id) throws Exception {
		return create(id, _elasticsearchInvoker.get("individual-segments", id));
	}

	private Map<String, JSONObject> _getMembershipJSONObjects(
		JSONArray individualSegmentsJSONArray) {

		Map<String, JSONObject> membershipJSONObjects = new HashMap<>();

		List<Long> individualSegmentIds = JSONUtil.toLongList(
			individualSegmentsJSONArray, "id");

		List<Membership> memberships = _membershipDog.getActiveMemberships(
			Long.valueOf(_individualId), individualSegmentIds);

		Map<String, JSONObject> individualSegmentJSONObjects = new HashMap<>();

		for (Membership membership : memberships) {
			individualSegmentJSONObjects.put(
				String.valueOf(membership.getIndividualSegmentId()),
				_objectMapper.convertValue(membership, JSONObject.class));
		}

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			String id = individualSegmentJSONObject.getString("id");

			if (!individualSegmentJSONObjects.containsKey(id)) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get active membership for individual " +
							_individualId + "and individual segment " + id);
				}

				continue;
			}

			JSONObject membershipJSONObject = individualSegmentJSONObjects.get(
				id);

			membershipJSONObject.remove("dateModified");
			membershipJSONObject.remove("dateRemoved");
			membershipJSONObject.remove("id");

			membershipJSONObjects.put(
				id, JSONUtil.put("active-membership", membershipJSONObject));
		}

		return membershipJSONObjects;
	}

	private static final Log _log = LogFactory.getLog(
		IndividualsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;
	private final String _individualId;
	private final MembershipDog _membershipDog;
	private final ObjectMapper _objectMapper;

}