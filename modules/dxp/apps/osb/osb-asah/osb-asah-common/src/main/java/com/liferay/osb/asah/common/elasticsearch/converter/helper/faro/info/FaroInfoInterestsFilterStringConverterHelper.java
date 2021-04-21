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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoInterestsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
		String fieldName, String operator, String valueString) {

		if (fieldName.equalsIgnoreCase("name")) {
			return BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", StringUtil.unquote(valueString))
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			);
		}

		if (!fieldName.equalsIgnoreCase("score")) {
			throw new IllegalArgumentException(
				"Invalid field name :" + fieldName);
		}

		AsahMarker asahMarker = _asahMarkerDog.getAsahMarker(
			"IndividualInterestScoresNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"dateRecorded",
				asahMarkerContextJSONObject.getString("lastSuccessfulDay"))
		).filter(
			QueryBuilders.rangeQuery(
				"score"
			).gte(
				Double.valueOf(valueString)
			)
		);
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

}