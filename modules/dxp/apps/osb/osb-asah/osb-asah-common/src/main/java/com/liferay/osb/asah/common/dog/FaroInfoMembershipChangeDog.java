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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class FaroInfoMembershipChangeDog extends BaseFaroInfoDog {

	public void addMembershipChange(
		Membership membership, JSONObject individualJSONObject,
		long individualsCount, long knownIndividualsCount, String operation) {

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		elasticsearchInvoker.add(
			"membership-changes",
			JSONUtil.put(
				"dateChanged",
				DateUtil.toUTCString(membership.getModifiedDate())
			).put(
				"dateFirst", DateUtil.toUTCString(membership.getCreateDate())
			).put(
				"individualDeleted", false
			).put(
				"individualEmail",
				FaroInfoIndividualUtil.getIndividualEmail(
					demographicsJSONObject)
			).put(
				"individualId", String.valueOf(membership.getIndividualId())
			).put(
				"individualName",
				FaroInfoIndividualUtil.getIndividualName(demographicsJSONObject)
			).put(
				"individualsCount", individualsCount
			).put(
				"individualSegmentId",
				String.valueOf(membership.getIndividualSegmentId())
			).put(
				"knownIndividualsCount", knownIndividualsCount
			).put(
				"operation", operation
			));
	}

	public void addMembershipChangeForDeletedIndividual(
		Membership membership, Long individualId, long individualsCount,
		long knownIndividualsCount) {

		String individualEmail = null;
		String individualName = null;

		JSONArray membershipChangesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"membership-changes",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						QueryBuilders.termQuery(
							"individualId", String.valueOf(individualId)));
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
				"dateChanged",
				DateUtil.toUTCString(membership.getModifiedDate())
			).put(
				"dateFirst", DateUtil.toUTCString(membership.getCreateDate())
			).put(
				"individualDeleted", true
			).put(
				"individualEmail", individualEmail
			).put(
				"individualId", String.valueOf(individualId)
			).put(
				"individualName", individualName
			).put(
				"individualsCount", individualsCount
			).put(
				"individualSegmentId",
				String.valueOf(membership.getIndividualSegmentId())
			).put(
				"knownIndividualsCount", knownIndividualsCount
			).put(
				"operation", "REMOVED"
			));
	}

	public void addMembershipChanges(
		List<Membership> memberships, boolean includeAnonymousUsers,
		long individualsCount, long knownIndividualsCount) {

		JSONArray membershipChangesJSONArray = new JSONArray();

		for (Membership membership : memberships) {
			Long individualId = membership.getIndividualId();

			JSONObject individualJSONObject = elasticsearchInvoker.get(
				"individuals", String.valueOf(individualId));

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
					DateUtil.toUTCString(membership.getModifiedDate())
				).put(
					"dateFirst",
					DateUtil.toUTCString(membership.getCreateDate())
				).put(
					"individualDeleted", false
				).put(
					"individualEmail", individualEmail
				).put(
					"individualId", String.valueOf(individualId)
				).put(
					"individualName",
					FaroInfoIndividualUtil.getIndividualName(
						demographicsJSONObject)
				).put(
					"individualsCount", individualsCount
				).put(
					"individualSegmentId",
					String.valueOf(membership.getIndividualSegmentId())
				).put(
					"knownIndividualsCount", knownIndividualsCount
				).put(
					"operation", "ADDED"
				));
		}

		elasticsearchInvoker.add(
			"membership-changes", membershipChangesJSONArray);
	}

}