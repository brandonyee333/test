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
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoIndividualsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getCustomFunctionQueryBuilder(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception {

		QueryBuilder queryBuilder = null;

		if (customFunctionName.equals("activities.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "activities");
		}
		else if (customFunctionName.equals("activities.filterByCount")) {

			// TODO Implement filter by count

			return null;
		}
		else if (customFunctionName.equals("interests.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "interests");
		}
		else if (customFunctionName.equals("organizations.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "organizations");
		}
		else if (customFunctionName.equals("sessions.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "sessions");
		}
		else {
			return null;
		}

		if (negated) {
			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
			String fieldName, String operator, String valueString)
		throws Exception {

		// TODO Add activities criteria condition

		if (fieldName.equals("dataSourceId") && _isEqualityOperator(operator)) {
			return _getDataSourceIdQueryBuilder(
				(String)StringUtil.toObject(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("dataSourceIndividualPKs/individualPKs") &&
			_isEqualityOperator(operator)) {

			return _getIndividualPKQueryBuilder(
				(String)StringUtil.toObject(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("userId") && _isEqualityOperator(operator)) {
			return _getUserIdQueryBuilder(
				operator.equalsIgnoreCase("ne"),
				Long.parseLong((String)StringUtil.toObject(valueString)));
		}

		return null;
	}

	private void _checkSurroundingQuotes(String s) {
		if (!StringUtil.isQuoted(s)) {
			throw new IllegalArgumentException(
				"Expected " + s + " to be fully surrounded by single quotes");
		}
	}

	private QueryBuilder _getDataSourceIdQueryBuilder(
		String dataSourceId, boolean negate) {

		if (dataSourceId == null) {
			if (negate) {
				return QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.existsQuery(
							"dataSourceIndividualPKs.dataSourceId")),
					ScoreMode.None);
			}

			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.existsQuery(
						"dataSourceIndividualPKs.dataSourceId"),
					ScoreMode.None));
		}

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"dataSourceIndividualPKs.dataSourceId",
							dataSourceId)),
					ScoreMode.None));
		}

		return QueryBuilders.nestedQuery(
			"dataSourceIndividualPKs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceIndividualPKs.dataSourceId", dataSourceId)),
			ScoreMode.None);
	}

	private QueryBuilder _getFilterFunctionQueryBuilder(
			List<String> arguments, String type)
		throws Exception {

		String[] argumentValues = FilterUtil.getArgumentValues(
			arguments, new String[] {"filter"});

		String filterString = argumentValues[0];

		if (filterString != null) {
			_checkSurroundingQuotes(filterString);

			filterString = StringUtil.unquoteAndDecodeInnerQuotes(filterString);
		}

		// TODO Add activities filter function query builder

		if (type.equals("interests")) {
			return _getInterestsFilterFunctionQueryBuilder(filterString);
		}

		if (type.equals("organizations")) {
			return _getOrganizationsFilterFunctionQueryBuilder(filterString);
		}

		return QueryBuilders.termsQuery(
			"id", _userSessionDog.getIndividualIds(filterString));
	}

	private QueryBuilder _getIndividualPKQueryBuilder(
		String individualPKs, boolean negate) {

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"dataSourceIndividualPKs.individualPKs",
							individualPKs)),
					ScoreMode.None));
		}

		return QueryBuilders.nestedQuery(
			"dataSourceIndividualPKs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"dataSourceIndividualPKs.individualPKs", individualPKs)),
			ScoreMode.None);
	}

	private QueryBuilder _getInterestCriteriaQueryBuilderWhenNoInterests(
		boolean score, double value) {

		if (score == (value <= 0)) {
			return QueryBuilders.matchAllQuery();
		}

		return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
	}

	private QueryBuilder _getInterestsFilterFunctionQueryBuilder(
			String filterString)
		throws Exception {

		if (!filterString.contains("name eq")) {
			throw new IllegalArgumentException("Expecting name filter");
		}

		if (!filterString.contains("score eq")) {
			throw new IllegalArgumentException("Expecting score filter");
		}

		AsahMarker interestThresholdScoreNaniteAsahMarker =
			_asahMarkerDog.fetchAsahMarker("InterestThresholdScoreNanite");

		if (interestThresholdScoreNaniteAsahMarker == null) {
			return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
		}

		JSONObject interestThresholdScoreNaniteAsahMarkerContextJSONObject =
			interestThresholdScoreNaniteAsahMarker.getContextJSONObject();

		if (!interestThresholdScoreNaniteAsahMarkerContextJSONObject.has(
				"score")) {

			return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
		}

		Matcher matcher = _pattern.matcher(filterString);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid score filter");
		}

		boolean interested = Boolean.parseBoolean(matcher.group(2));

		double value =
			interestThresholdScoreNaniteAsahMarkerContextJSONObject.optDouble(
				"score", 0.0);

		AsahMarker individualInterestScoresNaniteAsahMarker =
			_asahMarkerDog.fetchAsahMarker("IndividualInterestScoresNanite");

		if (individualInterestScoresNaniteAsahMarker == null) {
			return _getInterestCriteriaQueryBuilderWhenNoInterests(
				interested, value);
		}

		JSONObject individualInterestScoresNaniteAsahMarkerContextJSONObject =
			individualInterestScoresNaniteAsahMarker.getContextJSONObject();

		if (!individualInterestScoresNaniteAsahMarkerContextJSONObject.has(
				"lastSuccessfulDay")) {

			return _getInterestCriteriaQueryBuilderWhenNoInterests(
				interested, value);
		}

		if (interested && (value <= 0)) {
			return QueryBuilders.matchAllQuery();
		}

		if (!interested && (value < 0)) {
			return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
		}

		Set<String> individualIds = new HashSet<>();

		for (Long ownerId :
				_interestDog.getOwnerIds(
					filterString.replaceAll(
						matcher.group(1), "score eq " + value),
					IndividualIdThreadLocal.getIndividualId())) {

			individualIds.add(String.valueOf(ownerId));
		}

		if (!interested) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", individualIds));
		}

		return QueryBuilders.termsQuery("id", individualIds);
	}

	private QueryBuilder _getOrganizationsFilterFunctionQueryBuilder(
			String filterString)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoOrganizationsFilterStringConverterHelper);

		if (queryBuilder == null) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		JSONArrayIterator.of(
			"organizations", _faroInfoElasticsearchInvoker,
			organizationJSONObject -> {
				boolQueryBuilder.should(
					QueryBuilders.termQuery(
						"organizationIds",
						organizationJSONObject.getString("id")));

				return null;
			}
		).setQueryBuilder(
			queryBuilder
		).iterate();

		if (boolQueryBuilder.hasClauses()) {
			Long individualId = IndividualIdThreadLocal.getIndividualId();

			if (individualId != null) {
				boolQueryBuilder.filter(
					QueryBuilders.termQuery("id", individualId));
			}

			return boolQueryBuilder;
		}

		return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
	}

	private QueryBuilder _getUserIdQueryBuilder(boolean negate, Long userId) {
		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap("id", userId), DXPEntity.Type.USER);

		if (dxpEntity == null) {
			return null;
		}

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		QueryBuilder queryBuilder = QueryBuilders.nestedQuery(
			"dataSourceIndividualPKs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceIndividualPKs.dataSourceId",
					dxpEntity.getDataSourceId())
			).filter(
				QueryBuilders.termsQuery(
					"dataSourceIndividualPKs.individualPKs",
					fieldsJSONObject.getString("uuid"))
			),
			ScoreMode.None);

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

	private boolean _isEqualityOperator(String operator) {
		if (operator.equalsIgnoreCase("eq") ||
			operator.equalsIgnoreCase("ne")) {

			return true;
		}

		return false;
	}

	private static final Pattern _pattern = Pattern.compile(
		".*(score eq '(false|true)').*");

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoOrganizationsFilterStringConverterHelper
		_faroInfoOrganizationsFilterStringConverterHelper;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}