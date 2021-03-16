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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class MembershipDog extends BaseFaroInfoDog {

	public JSONObject addMembership(JSONObject membershipJSONObject)
		throws Exception {

		membershipJSONObject = elasticsearchInvoker.add(
			"memberships", membershipJSONObject);

		String status = membershipJSONObject.getString("status");

		if (!status.equals("ACTIVE")) {
			return membershipJSONObject;
		}

		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", membershipJSONObject.getString("individualId"));

		if (individualJSONObject == null) {
			return null;
		}

		String individualSegmentId = membershipJSONObject.getString(
			"individualSegmentId");

		_faroInfoIndividualDog.addIndividualSegmentId(
			individualJSONObject, individualSegmentId);

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentId);

		long knownIndividualCount =
			_faroInfoIndividualDog.getKnownIndividualCount(individualSegmentId);

		long individualCount = 0;

		if (individualSegmentJSONObject.optBoolean("includeAnonymousUsers")) {
			individualCount = _getIndividualCount(individualSegmentId);
		}
		else {
			individualCount = knownIndividualCount;
		}

		_updateIndividualSegment(
			individualCount, individualSegmentId, knownIndividualCount);

		_addMembershipChange(
			membershipJSONObject, individualJSONObject, individualCount,
			knownIndividualCount, "ADDED");

		return membershipJSONObject;
	}

	public JSONArray addMemberships(JSONArray membershipsJSONArray)
		throws Exception {

		boolean succeeded = elasticsearchInvoker.add(
			"memberships", membershipsJSONArray);

		if (!succeeded) {
			throw new Exception(
				"Unable to add memberships " + membershipsJSONArray);
		}

		if (membershipsJSONArray.length() == 0) {
			return membershipsJSONArray;
		}

		JSONObject membershipJSONObject = membershipsJSONArray.getJSONObject(0);

		String individualSegmentId = membershipJSONObject.getString(
			"individualSegmentId");

		_faroInfoIndividualDog.addIndividualSegmentIds(
			JSONUtil.toStringList(membershipsJSONArray, "individualId"),
			individualSegmentId);

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentId);

		boolean includeAnonymousUsers = individualSegmentJSONObject.optBoolean(
			"includeAnonymousUsers");

		long knownIndividualCount =
			_faroInfoIndividualDog.getKnownIndividualCount(individualSegmentId);

		long individualCount = 0;

		if (includeAnonymousUsers) {
			individualCount = _getIndividualCount(individualSegmentId);
		}
		else {
			individualCount = knownIndividualCount;
		}

		_updateIndividualSegment(
			individualCount, individualSegmentId, knownIndividualCount);

		_addMembershipChanges(
			membershipsJSONArray, includeAnonymousUsers,
			individualCount - membershipsJSONArray.length(),
			knownIndividualCount -
				_faroInfoIndividualDog.getKnownIndividualCount(
					membershipsJSONArray));

		return membershipsJSONArray;
	}

	public JSONObject deactivateMembership(
			String dateString, String individualId, String individualSegmentId)
		throws Exception {

		JSONObject membershipJSONObject = elasticsearchInvoker.fetch(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("individualId", individualId)
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentId", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));

		if (membershipJSONObject == null) {
			return null;
		}

		membershipJSONObject = elasticsearchInvoker.update(
			"memberships", membershipJSONObject.getString("id"),
			JSONUtil.put(
				"dateModified", dateString
			).put(
				"dateRemoved", dateString
			).put(
				"status", "INACTIVE"
			));

		JSONObject individualJSONObject = elasticsearchInvoker.fetch(
			"individuals", individualId);

		if (individualJSONObject != null) {
			_faroInfoIndividualDog.removeIndividualSegmentId(
				individualJSONObject, individualSegmentId);
		}

		long individualCount = 0;
		long knownIndividualCount = 0;

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.fetch(
			"individual-segments", individualSegmentId);

		if (individualSegmentJSONObject != null) {
			knownIndividualCount = _faroInfoIndividualDog.getKnownIndividualCount(
				individualSegmentId);

			if (individualSegmentJSONObject.optBoolean(
					"includeAnonymousUsers")) {

				individualCount = _getIndividualCount(individualSegmentId);
			}
			else {
				individualCount = knownIndividualCount;
			}

			_updateIndividualSegment(
				individualCount, individualSegmentId, knownIndividualCount);
		}

		if (individualJSONObject == null) {
			_addMembershipChangeForDeletedIndividual(
				membershipJSONObject, individualId, individualCount,
				knownIndividualCount);
		}
		else {
			_addMembershipChange(
				membershipJSONObject, individualJSONObject, individualCount,
				knownIndividualCount, "REMOVED");
		}

		return membershipJSONObject;
	}

	public List<String> getIndividualSegmentIndividualIds(
		JSONObject individualSegmentJSONObject) {

		JSONArray membershipsJSONArray = elasticsearchInvoker.get(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentId",
					individualSegmentJSONObject.getString("id"))
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));

		return JSONUtil.toStringList(membershipsJSONArray, "individualId");
	}

	public boolean isMember(String individualId, String individualSegmentId) {
		return elasticsearchInvoker.exists(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("individualId", individualId)
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentId", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));
	}

	private void _addMembershipChange(
		JSONObject membershipJSONObject, JSONObject individualJSONObject,
		long individualsCount, long knownIndividualsCount, String operation) {

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		elasticsearchInvoker.add(
			"membership-changes",
			JSONUtil.put(
				"dateChanged", membershipJSONObject.getString("dateModified")
			).put(
				"dateFirst", membershipJSONObject.getString("dateCreated")
			).put(
				"individualDeleted", false
			).put(
				"individualEmail",
				FaroInfoIndividualUtil.getIndividualEmail(
					demographicsJSONObject)
			).put(
				"individualId", membershipJSONObject.getString("individualId")
			).put(
				"individualName",
				FaroInfoIndividualUtil.getIndividualName(demographicsJSONObject)
			).put(
				"individualsCount", individualsCount
			).put(
				"individualSegmentId",
				membershipJSONObject.getString("individualSegmentId")
			).put(
				"knownIndividualsCount", knownIndividualsCount
			).put(
				"operation", operation
			));
	}

	private void _addMembershipChangeForDeletedIndividual(
		JSONObject membershipJSONObject, String individualId,
		long individualsCount, long knownIndividualsCount) {

		String individualEmail = null;
		String individualName = null;

		JSONArray membershipChangesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"membership-changes",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						QueryBuilders.termQuery("individualId", individualId));
					searchSourceBuilder.size(1);
				}));

		if (membershipChangesJSONArray.length() > 0) {
			JSONObject membershipChangeJSONObject =
				membershipChangesJSONArray.getJSONObject(0);

			individualEmail = membershipChangeJSONObject.optString(
				"individualEmail", null);
			individualName = membershipChangeJSONObject.optString(
				"individualName", null);
		}

		elasticsearchInvoker.add(
			"membership-changes",
			JSONUtil.put(
				"dateChanged", membershipJSONObject.getString("dateModified")
			).put(
				"dateFirst", membershipJSONObject.getString("dateCreated")
			).put(
				"individualDeleted", true
			).put(
				"individualEmail", individualEmail
			).put(
				"individualId", individualId
			).put(
				"individualName", individualName
			).put(
				"individualsCount", individualsCount
			).put(
				"individualSegmentId",
				membershipJSONObject.getString("individualSegmentId")
			).put(
				"knownIndividualsCount", knownIndividualsCount
			).put(
				"operation", "REMOVED"
			));
	}

	private void _addMembershipChanges(
		JSONArray membershipsJSONArray, boolean includeAnonymousUsers,
		long individualsCount, long knownIndividualsCount) {

		JSONArray membershipChangesJSONArray = new JSONArray();

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			String individualId = membershipJSONObject.getString(
				"individualId");

			JSONObject individualJSONObject = elasticsearchInvoker.get(
				"individuals", individualId);

			JSONObject demographicsJSONObject =
				individualJSONObject.optJSONObject("demographics");

			String individualEmail = FaroInfoIndividualUtil.getIndividualEmail(
				demographicsJSONObject);

			if (individualEmail != null) {
				knownIndividualsCount++;
			}

			if (includeAnonymousUsers ||
				(!includeAnonymousUsers && (individualEmail != null))) {

				individualsCount++;
			}

			membershipChangesJSONArray.put(
				JSONUtil.put(
					"dateChanged",
					membershipJSONObject.getString("dateModified")
				).put(
					"dateFirst", membershipJSONObject.getString("dateCreated")
				).put(
					"individualDeleted", false
				).put(
					"individualEmail", individualEmail
				).put(
					"individualId", individualId
				).put(
					"individualName",
					FaroInfoIndividualUtil.getIndividualName(
						demographicsJSONObject)
				).put(
					"individualsCount", individualsCount
				).put(
					"individualSegmentId",
					membershipJSONObject.getString("individualSegmentId")
				).put(
					"knownIndividualsCount", knownIndividualsCount
				).put(
					"operation", "ADDED"
				));
		}

		elasticsearchInvoker.add(
			"membership-changes", membershipChangesJSONArray);
	}

	private long _getIndividualCount(String individualSegmentId) {
		return elasticsearchInvoker.count(
			"memberships",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentId", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));
	}

	private void _updateIndividualSegment(
			long individualCount, String individualSegmentId,
			long knownIndividualCount)
		throws Exception {

		_faroInfoIndividualSegmentDog.updateIndividualSegment(
			individualSegmentId,
			JSONUtil.put(
				"anonymousIndividualCount",
				individualCount - knownIndividualCount
			).put(
				"individualCount", individualCount
			).put(
				"knownIndividualCount", knownIndividualCount
			));
	}

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;
}