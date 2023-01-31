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

package com.liferay.osb.asah.common.postgresql.converter.helper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.BQIdentityInterestScoreDog;
import com.liferay.osb.asah.common.dog.BQOrganizationDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;

import java.text.ParseException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class IndividualsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Condition getCustomFunctionCondition(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception {

		Condition condition = null;

		if (customFunctionName.equals("activities.filter")) {
			condition = _getFilterFunctionCondition(arguments, "activities");
		}
		else if (customFunctionName.equals("activities.filterByCount")) {

			// TODO Implement filter by count

			condition = DSL.noCondition();
		}
		else if (customFunctionName.equals("interests.filter")) {
			condition = _getFilterFunctionCondition(arguments, "interests");
		}
		else if (customFunctionName.equals("organizations.filter")) {
			condition = _getFilterFunctionCondition(arguments, "organizations");
		}
		else if (customFunctionName.equals("sessions.filter")) {
			condition = _getFilterFunctionCondition(arguments, "sessions");
		}
		else {
			return null;
		}

		if (negated) {
			return DSL.not(condition);
		}

		return condition;
	}

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		Map<String, String> map = new HashMap<>();

		map.put("channelIds", "IdentityActivity.channelId");
		map.put("email", "Individual.emailAddress");
		map.put("lastEnrichmentDate", "Individual.modifiedDate");

		return map;
	}

	@Override
	public String getFilterType() {
		return "individual";
	}

	@Override
	public Condition getInferredCondition(String fieldName) {
		if (!fieldName.startsWith("demographics") ||
			!fieldName.endsWith("value")) {

			return null;
		}

		String[] fieldNames = fieldName.split("\\.");

		fieldName = fieldNames[1];

		Field<Object> nameField = DSL.field("field.name");

		return nameField.eq(fieldName);
	}

	@Override
	public Condition getLogicFunctionCondition(
			String fieldName, String operator, boolean processString,
			String valueString)
		throws Exception {

		// TODO Add activities criteria condition

		if (fieldName.equals("dataSourceId") && _isEqualityOperator(operator)) {
			return _getDataSourceIdCondition(
				(String)StringUtil.toObject(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("dataSourceIndividualPKs/individualPKs") &&
			_isEqualityOperator(operator)) {

			return _getUserPKCondition(
				(String)StringUtil.toObject(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("userId") && _isEqualityOperator(operator)) {
			return _getUserIdCondition(
				operator.equalsIgnoreCase("ne"),
				Long.parseLong((String)StringUtil.toObject(valueString)));
		}

		return null;
	}

	@Override
	public String getTableName() {
		return "Individual";
	}

	@Override
	public Object processValue(String fieldName, String valueString) {
		if (fieldName.equalsIgnoreCase("Individual.modifiedDate") &&
			!StringUtils.isBlank(valueString)) {

			try {
				return DateUtils.parseDate(valueString, "yyyy-MM-dd");
			}
			catch (ParseException parseException) {
				throw new IllegalArgumentException(parseException);
			}
		}

		return null;
	}

	private void _checkSurroundingQuotes(String s) {
		if (!StringUtil.isQuoted(s)) {
			throw new IllegalArgumentException(
				"Expected " + s + " to be fully surrounded by single quotes");
		}
	}

	private Condition _getDataSourceIdCondition(
		String dataSourceId, boolean negate) {

		if (dataSourceId == null) {
			if (negate) {
				return DSL.exists(
					DSL.selectOne(
					).from(
						"BQDataSourceUser"
					).where(
						DSL.field(
							"bqdatasourceuser.userpks"
						).isNotNull()
					));
			}

			return DSL.notExists(
				DSL.selectOne(
				).from(
					"BQDataSourceUser"
				).where(
					DSL.field(
						"bqdatasourceuser.userpks"
					).isNotNull()
				));
		}

		if (negate) {
			return DSL.not(
				DSL.field(
					"bqdatasourceuser.datasourceid"
				).eq(
					dataSourceId
				));
		}

		return DSL.field(
			"bqdatasourceuser.datasourceid"
		).eq(
			dataSourceId
		);
	}

	private Condition _getFilterFunctionCondition(
			List<String> arguments, String type)
		throws Exception {

		String[] argumentValues = FilterUtil.getArgumentValues(
			arguments, new String[] {"filter"});

		String filterString = argumentValues[0];

		if (filterString != null) {
			_checkSurroundingQuotes(filterString);

			filterString = StringUtil.unquoteAndDecodeInnerQuotes(filterString);
		}

		// TODO Add activities filter function condition

		if (type.equals("interests")) {
			return _getInterestsFilterFunctionCondition(filterString);
		}

		if (type.equals("organizations")) {
			return _getOrganizationsFilterFunctionCondition(filterString);
		}

		List<Long> individualIds = _userSessionDog.getIndividualIds(
			filterString);

		if (individualIds.isEmpty()) {
			return DSL.noCondition();
		}

		return DSL.field(
			"individual.id"
		).in(
			individualIds
		);
	}

	private Condition _getInterestCriteriaConditionWhenNoInterests(
		boolean score, double value) {

		if (score == (value <= 0)) {
			return DSL.noCondition();
		}

		return DSL.not(DSL.noCondition());
	}

	private Condition _getInterestsFilterFunctionCondition(String filterString)
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
			return DSL.not(DSL.noCondition());
		}

		JSONObject interestThresholdScoreNaniteAsahMarkerContextJSONObject =
			interestThresholdScoreNaniteAsahMarker.getContextJSONObject();

		if (!interestThresholdScoreNaniteAsahMarkerContextJSONObject.has(
				"score")) {

			return DSL.not(DSL.noCondition());
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
			_asahMarkerDog.fetchAsahMarker("IdentityInterestScoresNanite");

		if (individualInterestScoresNaniteAsahMarker == null) {
			return _getInterestCriteriaConditionWhenNoInterests(
				interested, value);
		}

		JSONObject individualInterestScoresNaniteAsahMarkerContextJSONObject =
			individualInterestScoresNaniteAsahMarker.getContextJSONObject();

		if (!individualInterestScoresNaniteAsahMarkerContextJSONObject.has(
				"lastSuccessfulDay")) {

			return _getInterestCriteriaConditionWhenNoInterests(
				interested, value);
		}

		if (interested && (value <= 0)) {
			return DSL.noCondition();
		}

		if (!interested && (value < 0)) {
			return DSL.not(DSL.noCondition());
		}

		List<String> individualIds =
			_bqIdentityInterestScoreDog.getIndividualIds(
				filterString.replaceAll(matcher.group(1), "score eq " + value),
				IndividualIdThreadLocal.getIndividualId());

		if (individualIds.isEmpty()) {
			return DSL.noCondition();
		}

		if (!interested) {
			return DSL.not(
				DSL.field(
					"individual.id"
				).in(
					individualIds
				));
		}

		return DSL.field(
			"individual.id"
		).in(
			individualIds
		);
	}

	private Condition _getOrganizationsFilterFunctionCondition(
			String filterString)
		throws Exception {

		Condition condition = ConditionUtil.toCondition(
			filterString, _organizationsFilterStringConverterHelper);

		int page = 0;

		while (true) {
			List<BQOrganization> bqOrganizations =
				_bqOrganizationDog.searchBQOrganizations(
					filterString, page++, 500);

			if (bqOrganizations.isEmpty()) {
				break;
			}

			for (BQOrganization bqOrganization : bqOrganizations) {
				condition = condition.or(
					DSL.field(
						DSL.cast(
							DSL.array(DSL.field("individual.organizationids")),
							Long[].class)
					).contains(
						DSL.cast(
							DSL.array(bqOrganization.getId()), Long[].class)
					));
			}
		}

		if (condition != DSL.noCondition()) {
			String individualId = IndividualIdThreadLocal.getIndividualId();

			if (individualId != null) {
				condition = DSL.and(
					condition,
					DSL.field(
						"id"
					).eq(
						individualId
					));
			}

			return condition;
		}

		return DSL.not(DSL.noCondition());
	}

	private Condition _getUserIdCondition(boolean negate, long userId) {
		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap("id", userId), DXPEntity.Type.USER);

		if (dxpEntity == null) {
			return null;
		}

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		Condition condition = DSL.and(
			DSL.field(
				"bqdatasourceuser.datasourceid"
			).eq(
				dxpEntity.getDataSourceId()
			),
			DSL.field(
				DSL.cast(
					DSL.array(DSL.field("bqdatasourceuser.userpks")),
					String[].class)
			).contains(
				DSL.cast(
					DSL.array(fieldsJSONObject.getString("uuid")),
					String[].class)
			));

		if (negate) {
			return DSL.not(condition);
		}

		return condition;
	}

	private Condition _getUserPKCondition(String individualPK, boolean negate) {
		if (negate) {
			return DSL.not(
				DSL.field(
					DSL.cast(
						DSL.array(DSL.field("bqdatasourceuser.userpks")),
						String[].class)
				).contains(
					DSL.cast(DSL.array(individualPK), String[].class)
				));
		}

		return DSL.field(
			DSL.cast(
				DSL.array(DSL.field("bqdatasourceuser.userpks")),
				String[].class)
		).contains(
			DSL.cast(DSL.array(individualPK), String[].class)
		);
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
	private BQIdentityInterestScoreDog _bqIdentityInterestScoreDog;

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private OrganizationsFilterStringConverterHelper
		_organizationsFilterStringConverterHelper;

	@Autowired
	private UserSessionDog _userSessionDog;

}