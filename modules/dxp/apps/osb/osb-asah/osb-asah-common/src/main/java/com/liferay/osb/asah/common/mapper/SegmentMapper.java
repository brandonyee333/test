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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.SegmentDTO;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Segment;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class SegmentMapper extends Mapper<Segment, SegmentDTO> {

	@Override
	protected SegmentDTO toDTO(Segment segment) {
		return new SegmentDTO(segment);
	}

	@Override
	protected Segment toModel(JSONObject jsonObject) {
		Segment segment = new Segment();

		if (jsonObject.has("activeIndividualCount") &&
			!jsonObject.isNull("activeIndividualCount")) {

			segment.setActiveIndividualCount(
				jsonObject.getLong("activeIndividualCount"));
		}

		if (jsonObject.has("activitiesCount") &&
			!jsonObject.isNull("activitiesCount")) {

			segment.setActivitiesCount(jsonObject.getLong("activitiesCount"));
		}

		if (jsonObject.has("anonymousIndividualCount") &&
			!jsonObject.isNull("anonymousIndividualCount")) {

			segment.setAnonymousIndividualCount(
				jsonObject.getLong("anonymousIndividualCount"));
		}

		if (jsonObject.has("author") && !jsonObject.isNull("author")) {
			JSONObject authorJSONObject = jsonObject.getJSONObject("author");

			if (authorJSONObject.has("id") && !authorJSONObject.isNull("id")) {
				segment.setAuthorId(authorJSONObject.getLong("id"));
			}

			if (authorJSONObject.has("name") &&
				!authorJSONObject.isNull("name")) {

				segment.setAuthorName(authorJSONObject.getString("name"));
			}
		}

		if (jsonObject.has("channelId") && !jsonObject.isNull("channelId")) {
			segment.setChannelId(jsonObject.getLong("channelId"));
		}

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			segment.setCreateDate(toUTCDate(jsonObject.get("createDate")));
		}
		else if (jsonObject.has("dateCreated") &&
				 !jsonObject.isNull("dateCreated")) {

			segment.setCreateDate(toUTCDate(jsonObject.get("dateCreated")));
		}

		if (jsonObject.has("filter") && !jsonObject.isNull("filter")) {
			segment.setFilter(jsonObject.getString("filter"));
		}

		if (jsonObject.has("filterMetadata") &&
			!jsonObject.isNull("filterMetadata")) {

			segment.setFilterMetadata(jsonObject.getString("filterMetadata"));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			segment.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("includeAnonymousUsers") &&
			!jsonObject.isNull("includeAnonymousUsers")) {

			segment.setIncludeAnonymousUsers(
				jsonObject.getBoolean("includeAnonymousUsers"));
		}

		if (jsonObject.has("individualCount") &&
			!jsonObject.isNull("individualCount")) {

			segment.setIndividualCount(jsonObject.getLong("individualCount"));
		}

		if (jsonObject.has("knownIndividualCount") &&
			!jsonObject.isNull("knownIndividualCount")) {

			segment.setKnownIndividualCount(
				jsonObject.getLong("knownIndividualCount"));
		}

		if (jsonObject.has("lastActivityDate") &&
			!jsonObject.isNull("lastActivityDate")) {

			segment.setLastActivityDate(
				toUTCDate(jsonObject.get("lastActivityDate")));
		}

		if (jsonObject.has("modifiedDate") &&
			!jsonObject.isNull("modifiedDate")) {

			segment.setModifiedDate(toUTCDate(jsonObject.get("modifiedDate")));
		}
		else if (jsonObject.has("dateModified") &&
				 !jsonObject.isNull("dateModified")) {

			segment.setModifiedDate(toUTCDate(jsonObject.get("dateModified")));
		}

		if (jsonObject.has("name") && !jsonObject.isNull("name")) {
			segment.setName(jsonObject.getString("name"));
		}

		if (jsonObject.has("referencedAssetDataSourceIds") &&
			!jsonObject.isNull("referencedAssetDataSourceIds")) {

			segment.setReferencedAssetDataSourceIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedAssetDataSourceIds")));
		}

		if (jsonObject.has("referencedAssetIds") &&
			!jsonObject.isNull("referencedAssetIds")) {

			segment.setReferencedAssetIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedAssetIds")));
		}

		if (jsonObject.has("referencedFieldMappingIds") &&
			!jsonObject.isNull("referencedFieldMappingIds")) {

			segment.setReferencedFieldMappingIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedFieldMappingIds")));
		}

		if (jsonObject.has("referencedGroupIds") &&
			!jsonObject.isNull("referencedGroupIds")) {

			segment.setReferencedGroupIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedGroupIds")));
		}

		if (jsonObject.has("referencedOrganizationIds") &&
			!jsonObject.isNull("referencedOrganizationIds")) {

			segment.setReferencedOrganizationIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedOrganizationIds")));
		}

		if (jsonObject.has("referencedTeamIds") &&
			!jsonObject.isNull("referencedTeamIds")) {

			segment.setReferencedTeamIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedTeamIds")));
		}

		if (jsonObject.has("referencedUserGroupIds") &&
			!jsonObject.isNull("referencedUserGroupIds")) {

			segment.setReferencedUserGroupIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedUserGroupIds")));
		}

		if (jsonObject.has("referencedUserIds") &&
			!jsonObject.isNull("referencedUserIds")) {

			segment.setReferencedUserIds(
				JSONUtil.toLongSet(
					jsonObject.getJSONArray("referencedUserIds")));
		}

		if (jsonObject.has("scope") && !jsonObject.isNull("scope")) {
			segment.setScope(jsonObject.getString("scope"));
		}

		if (jsonObject.has("state") && !jsonObject.isNull("state")) {
			segment.setState(jsonObject.getString("state"));
		}

		if (jsonObject.has("status") && !jsonObject.isNull("status")) {
			segment.setStatus(jsonObject.getString("status"));
		}

		if (jsonObject.has("type") && !jsonObject.isNull("type")) {
			segment.setType(Segment.Type.valueOf(jsonObject.getString("type")));
		}
		else if (jsonObject.has("segmentType") &&
				 !jsonObject.isNull("segmentType")) {

			segment.setType(
				Segment.Type.valueOf(jsonObject.getString("segmentType")));
		}

		return segment;
	}

}