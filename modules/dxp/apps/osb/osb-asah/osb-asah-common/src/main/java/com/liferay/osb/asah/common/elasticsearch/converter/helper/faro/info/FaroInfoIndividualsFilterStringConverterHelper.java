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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

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

		if (customFunctionName.equals("accounts.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "accounts");
		}
		else if (customFunctionName.equals("accounts.filterByCount")) {
			queryBuilder = _getFilterByCountFunctionQueryBuilder(
				arguments, "accounts");
		}
		else if (customFunctionName.equals("activities.filter")) {
			queryBuilder = _getFilterFunctionQueryBuilder(
				arguments, "activities");
		}
		else if (customFunctionName.equals("activities.filterByCount")) {
			queryBuilder = _getFilterByCountFunctionQueryBuilder(
				arguments, "activities");
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

		if (fieldName.startsWith(_BEHAVIORAL_CRITERIA_FIELD_NAME_PREFIX) &&
			_isEqualityOperator(operator)) {

			return _getBehavioralCriteriaQueryBuilder(
				(String)StringUtil.toObject(valueString), fieldName,
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("accountId") && _isEqualityOperator(operator)) {
			return _getAccountIdQueryBuilder(
				(String)StringUtil.toObject(valueString), null,
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("dataSourceAccountPKs/accountPKs") &&
			_isEqualityOperator(operator)) {

			return _getAccountIdQueryBuilder(
				null, (String)StringUtil.toObject(valueString),
				operator.equalsIgnoreCase("ne"));
		}

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
				(String)StringUtil.toObject(valueString));
		}

		return null;
	}

	private void _checkSurroundingQuotes(String s) {
		if (!StringUtil.isQuoted(s)) {
			throw new IllegalArgumentException(
				"Expected " + s + " to be fully surrounded by single quotes");
		}
	}

	private QueryBuilder _getAccountIdQueryBuilder(
		String accountId, String accountPK, boolean negate) {

		if ((accountId == null) && (accountPK == null)) {
			if (negate) {
				return QueryBuilders.nestedQuery(
					"dataSourceAccountPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.existsQuery(
							"dataSourceAccountPKs.dataSourceId")),
					ScoreMode.None);
			}

			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.nestedQuery(
					"dataSourceAccountPKs",
					QueryBuilders.existsQuery(
						"dataSourceAccountPKs.dataSourceId"),
					ScoreMode.None));
		}

		Account account = null;

		if (accountId != null) {
			Optional<Account> accountOptional = _accountRepository.findById(
				Long.valueOf(accountId));

			account = accountOptional.orElse(null);
		}
		else {
			Optional<Account> accountOptional =
				_accountRepository.findByAccountPK(accountPK);

			account = accountOptional.orElse(null);
		}

		if (account == null) {
			return null;
		}

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.nestedQuery(
					"dataSourceAccountPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"dataSourceAccountPKs.accountPKs",
							account.getAccountPK())
					).filter(
						QueryBuilders.termQuery(
							"dataSourceAccountPKs.dataSourceId",
							account.getDataSourceId())
					),
					ScoreMode.None));
		}

		return QueryBuilders.nestedQuery(
			"dataSourceAccountPKs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"dataSourceAccountPKs.accountPKs", account.getAccountPK())
			).filter(
				QueryBuilders.termQuery(
					"dataSourceAccountPKs.dataSourceId",
					account.getDataSourceId())
			),
			ScoreMode.None);
	}

	private QueryBuilder _getAccountsFilterByCountFunctionQueryBuilder(
			boolean checkEqualityOnly, int minDocCount, boolean negate,
			QueryBuilder queryBuilder, int value)
		throws Exception {

		List<String> individualSegmentNames = new LinkedList<>();

		JSONArrayIterator.of(
			"accounts", _faroInfoElasticsearchInvoker,
			accountJSONObject -> {
				individualSegmentNames.add(
					"Account: " + accountJSONObject.getString("id"));

				return null;
			}
		).setQueryBuilder(
			queryBuilder
		).iterate();

		List<Long> individualIds = _membershipDog.getIndividualIds(
			_segmentDog.getSegmentIds(individualSegmentNames, "INACTIVE"),
			value, minDocCount, checkEqualityOnly);

		QueryBuilder accountsFilterByCountFunctionQueryBuilder =
			QueryBuilders.termsQuery(
				"id", ListUtil.map(individualIds, String::valueOf));

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				accountsFilterByCountFunctionQueryBuilder);
		}

		return accountsFilterByCountFunctionQueryBuilder;
	}

	private QueryBuilder _getAccountsFilterFunctionQueryBuilder(
			String filterString)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if (queryBuilder == null) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		JSONArrayIterator.of(
			"accounts", _faroInfoElasticsearchInvoker,
			accountJSONObject -> {
				boolQueryBuilder.should(
					QueryBuilders.nestedQuery(
						"dataSourceAccountPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termsQuery(
								"dataSourceAccountPKs.accountPKs",
								accountJSONObject.getString("accountPK"))
						).filter(
							QueryBuilders.termQuery(
								"dataSourceAccountPKs.dataSourceId",
								accountJSONObject.getString("dataSourceId"))
						),
						ScoreMode.None));

				return null;
			}
		).setQueryBuilder(
			queryBuilder
		).iterate();

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
	}

	private QueryBuilder _getActivitiesFilterByCountFunctionQueryBuilder(
		boolean checkEqualityOnly, int minDocCount, boolean negate,
		QueryBuilder queryBuilder, int value) {

		QueryBuilder activitiesFilterByCountFunctionQueryBuilder =
			QueryBuilders.termsQuery(
				"id",
				_faroInfoActivityDog.getOwnerIds(
					checkEqualityOnly, minDocCount, queryBuilder, value));

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				activitiesFilterByCountFunctionQueryBuilder);
		}

		return activitiesFilterByCountFunctionQueryBuilder;
	}

	private QueryBuilder _getActivitiesFilterFunctionQueryBuilder(
			String filterString)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoActivitiesFilterStringConverterHelper);

		if (queryBuilder == null) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}

		List<String> ownerIds = _faroInfoActivityDog.getOwnerIds(queryBuilder);

		if (!ownerIds.isEmpty()) {
			return QueryBuilders.termsQuery("id", ownerIds);
		}

		return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
	}

	private QueryBuilder _getBehavioralCriteriaQueryBuilder(
			String activityKey, String fieldName, boolean negate)
		throws Exception {

		List<String> individualIds = _getIndividualIds(activityKey, fieldName);

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", individualIds));
		}

		return QueryBuilders.termsQuery("id", individualIds);
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

	private QueryBuilder _getFilterByCountFunctionQueryBuilder(
			List<String> arguments, String type)
		throws Exception {

		String[] argumentValues = FilterUtil.getArgumentValues(
			arguments, new String[] {"filter", "operator", "value"});

		if ((argumentValues[1] == null) ^ (argumentValues[2] == null)) {
			throw new IllegalArgumentException(
				"The arguments operator and value must either both be set or " +
					"both be null");
		}

		String filterString = argumentValues[0];

		if (filterString != null) {
			_checkSurroundingQuotes(filterString);

			filterString = StringUtil.unquoteAndDecodeInnerQuotes(filterString);
		}

		if (argumentValues[1] == null) {
			if (type.equals("accounts")) {
				return _getAccountsFilterFunctionQueryBuilder(filterString);
			}

			if (type.equals("activities")) {
				return _getActivitiesFilterFunctionQueryBuilder(filterString);
			}
		}

		String operator = argumentValues[1];

		_checkSurroundingQuotes(operator);

		operator = StringUtil.unquoteAndDecodeInnerQuotes(argumentValues[1]);

		if (!_allowedOperators.contains(operator)) {
			throw new IllegalArgumentException("Unknown operator: " + operator);
		}

		int value = Integer.parseInt(argumentValues[2]);

		boolean checkEqualityOnly = _isEqualityOperator(operator);

		int minDocCount;

		if (operator.equals("gt") || operator.equals("le")) {
			minDocCount = value + 1;
		}
		else {
			minDocCount = value;
		}

		if (minDocCount <= 0) {
			if (checkEqualityOnly) {
				if (minDocCount == 0) {
					QueryBuilder queryBuilder = null;

					if (type.equals("accounts")) {
						queryBuilder = _getAccountsFilterFunctionQueryBuilder(
							filterString);
					}
					else if (type.equals("activities")) {
						queryBuilder = _getActivitiesFilterFunctionQueryBuilder(
							filterString);
					}

					if (operator.equals("ne")) {
						return queryBuilder;
					}

					return BoolQueryBuilderUtil.mustNot(queryBuilder);
				}

				if (operator.equals("ne")) {
					return QueryBuilders.matchAllQuery();
				}

				return BoolQueryBuilderUtil.mustNot(
					QueryBuilders.matchAllQuery());
			}

			if (operator.equals("ge") || operator.equals("gt")) {
				return QueryBuilders.matchAllQuery();
			}

			return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
		}

		boolean negate = false;

		if (operator.equals("le") || operator.equals("lt") ||
			operator.equals("ne")) {

			negate = true;
		}

		QueryBuilder queryBuilder = null;

		if (type.equals("activities")) {
			queryBuilder = FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper);
		}
		else {
			queryBuilder = FilterStringToQueryBuilderConverter.convert(
				filterString);
		}

		if (queryBuilder == null) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}

		if (type.equals("accounts")) {
			return _getAccountsFilterByCountFunctionQueryBuilder(
				checkEqualityOnly, minDocCount, negate, queryBuilder, value);
		}

		if (type.equals("activities")) {
			return _getActivitiesFilterByCountFunctionQueryBuilder(
				checkEqualityOnly, minDocCount, negate, queryBuilder, value);
		}

		return queryBuilder;
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

		if (type.equals("accounts")) {
			return _getAccountsFilterFunctionQueryBuilder(filterString);
		}

		if (type.equals("activities")) {
			return _getActivitiesFilterFunctionQueryBuilder(filterString);
		}

		if (type.equals("interests")) {
			return _getInterestsFilterFunctionQueryBuilder(filterString);
		}

		if (type.equals("organizations")) {
			return _getOrganizationsFilterFunctionQueryBuilder(filterString);
		}

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoSessionsFilterStringConverterHelper);

		if (queryBuilder == null) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}

		return _getSessionsFilterFunctionQueryBuilder(queryBuilder);
	}

	private List<String> _getIndividualIds(String activityKey, String fieldName)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("activityKey", activityKey));

		String timeFrame = fieldName.substring(
			_BEHAVIORAL_CRITERIA_FIELD_NAME_PREFIX.length());

		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		if (timeFrame.equalsIgnoreCase("ever")) {
		}
		else if (timeFrame.equalsIgnoreCase("last7Days")) {
			localDateTime = localDateTime.minusDays(7);

			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					localDateTime.toString()
				).timeZone(
					TimeZoneDogUtil.getTimeZoneId()
				));
		}
		else if (timeFrame.equalsIgnoreCase("last30Days")) {
			localDateTime = localDateTime.minusDays(30);

			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					localDateTime.toString()
				).timeZone(
					TimeZoneDogUtil.getTimeZoneId()
				));
		}
		else if (timeFrame.equalsIgnoreCase("lastYear")) {
			localDateTime = localDateTime.minusDays(365);

			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					localDateTime.toString()
				).timeZone(
					TimeZoneDogUtil.getTimeZoneId()
				));
		}
		else if (timeFrame.equalsIgnoreCase("today")) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gte(
					localDateTime.toString()
				).timeZone(
					TimeZoneDogUtil.getTimeZoneId()
				));
		}
		else {
			throw new Exception("Invalid time frame: " + timeFrame);
		}

		return _faroInfoActivityDog.getOwnerIds(boolQueryBuilder);
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
			_asahMarkerDog.fetchAsahMarker(
				"InterestThresholdScoreNanite",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

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
			_asahMarkerDog.fetchAsahMarker(
				"IndividualInterestScoresNanite",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

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

		List<String> individualIds = new ArrayList<>();

		JSONArrayIterator.of(
			"interests", _faroInfoElasticsearchInvoker,
			interestJSONObject -> {
				individualIds.add(interestJSONObject.getString("ownerId"));

				return null;
			}
		).setQueryBuilder(
			FilterStringToQueryBuilderConverter.convert(
				filterString.replaceAll(matcher.group(1), "score eq " + value),
				_faroInfoInterestsFilterStringConverterHelper)
		).iterate();

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
			return boolQueryBuilder;
		}

		return BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery());
	}

	private QueryBuilder _getSessionsFilterFunctionQueryBuilder(
		QueryBuilder queryBuilder) {

		List<String> individualIds = new ArrayList<>();

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"user-sessions",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"individualIds"
					).field(
						"individualId"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("individualIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			individualIds.add(bucket.getKeyAsString());
		}

		return QueryBuilders.termsQuery("id", individualIds);
	}

	private QueryBuilder _getUserIdQueryBuilder(boolean negate, String userId) {
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

	private static final String _BEHAVIORAL_CRITERIA_FIELD_NAME_PREFIX =
		"activities/";

	private static final Pattern _pattern = Pattern.compile(
		".*(score eq '(false|true)').*");

	@Autowired
	private AccountRepository _accountRepository;

	private final Set<String> _allowedOperators = new HashSet<String>() {
		{
			add("eq");
			add("ge");
			add("gt");
			add("le");
			add("lt");
			add("ne");
		}
	};

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoInterestsFilterStringConverterHelper
		_faroInfoInterestsFilterStringConverterHelper;

	@Autowired
	private FaroInfoOrganizationsFilterStringConverterHelper
		_faroInfoOrganizationsFilterStringConverterHelper;

	@Autowired
	private FaroInfoSessionsFilterStringConverterHelper
		_faroInfoSessionsFilterStringConverterHelper;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}