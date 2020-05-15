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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoEngagementDog extends BaseFaroInfoDog {

	@CacheEvict(
		allEntries = true,
		value = {
			"getEngagements", "getEngagementTransformations",
			"getSegmentEngagements"
		}
	)
	public void clearCache() {
	}

	public void deleteScore(
		String dayDateString, String ownerId, String ownerType) {

		elasticsearchInvoker.delete(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	public void saveAssetEngagement(
		String dayDateString, String emailAddress,
		JSONArray individualSegmentIds, String name, String assetId,
		double score) {

		saveEngagement(
			dayDateString, emailAddress, individualSegmentIds, name, assetId,
			"asset", score);
	}

	public void saveEngagement(
		String dayDateString,
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		String emailAddress, JSONArray individualSegmentIds, String name,
		String ownerId, String ownerType, double score) {

		JSONObject engagementJSONObject = elasticsearchInvoker.fetch(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		if (engagementJSONObject == null) {
			elasticsearchBulkRequestBuilder.add(
				"engagements",
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"emailAddress", emailAddress
				).put(
					"individualSegmentIds", individualSegmentIds
				).put(
					"name", name
				).put(
					"ownerId", ownerId
				).put(
					"ownerType", ownerType
				).put(
					"score", score
				));
		}
		else {
			elasticsearchBulkRequestBuilder.update(
				"engagements",
				JSONUtil.put(
					"id", engagementJSONObject.getString("id")
				).put(
					"score", score
				));
		}
	}

	public void saveEngagement(
		String dayDateString, String emailAddress,
		JSONArray individualSegmentIds, String name, String ownerId,
		String ownerType, double score) {

		JSONObject engagementJSONObject = elasticsearchInvoker.fetch(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		if (engagementJSONObject == null) {
			elasticsearchInvoker.add(
				"engagements",
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"emailAddress", emailAddress
				).put(
					"individualSegmentIds", individualSegmentIds
				).put(
					"name", name
				).put(
					"ownerId", ownerId
				).put(
					"ownerType", ownerType
				).put(
					"score", score
				));
		}
		else {
			elasticsearchInvoker.update(
				"engagements", engagementJSONObject.getString("id"),
				JSONUtil.put("score", score));
		}
	}

	public void saveIndividualEngagement(
		String dayDateString,
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		String emailAddress, JSONArray individualSegmentIds, String name,
		String individualId, double score) {

		saveEngagement(
			dayDateString, elasticsearchBulkRequestBuilder, emailAddress,
			individualSegmentIds, name, individualId, "individual", score);
	}

}