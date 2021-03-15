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

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoAssetDog extends BaseFaroInfoDog {

	public void deleteAsset(
		JSONObject assetJSONObject, String deletionDayDateString) {

		String assetId = assetJSONObject.getString("id");

		elasticsearchInvoker.delete("assets", assetId);

		JSONArray keywordsJSONArray = assetJSONObject.optJSONArray("keywords");

		if (keywordsJSONArray != null) {
			for (int i = 0; i < keywordsJSONArray.length(); i++) {
				JSONObject jsonObject = keywordsJSONArray.getJSONObject(i);

				elasticsearchInvoker.delete(
					"interests",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"dateRecorded"
						).gte(
							deletionDayDateString
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.termQuery(
							"name", jsonObject.getString("keyword"))
					));

				elasticsearchInvoker.delete(
					"visited-pages",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"day"
						).gte(
							deletionDayDateString
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.termQuery(
							"interestName", jsonObject.getString("keyword"))
					));
			}
		}

		_addOSBAsahTask(
			"IndividualInterestScoresNanite", deletionDayDateString);
	}

	public List<String> getKeywords() {
		Set<String> keywords = new TreeSet<>();

		JSONArray assetsJSONArray = elasticsearchInvoker.get(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", "Page")));

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			JSONArray keywordsJSONArray = assetJSONObject.getJSONArray(
				"keywords");

			for (int j = 0; j < keywordsJSONArray.length(); j++) {
				JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
					j);

				keywords.add(keywordJSONObject.getString("keyword"));
			}
		}

		return new ArrayList<>(keywords);
	}

	private void _addOSBAsahTask(String className, String dayDateString) {
		_asahTaskDog.scheduleAsahTask(
			className, JSONUtil.put("reprocessDay", dayDateString));
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}