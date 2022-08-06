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
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
			condition = _getFilterByCountFunctionCondition(
				arguments, "activities");
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
			String fieldName, String operator, String valueString)
		throws Exception {

		//if (fieldName.startsWith(_BEHAVIORAL_CRITERIA_FIELD_NAME_PREFIX) &&

		// 	_isEqualityOperator(operator)) {

		//
		//	return _getBehavioralCriteriaCondition(
		//		(String)StringUtil.toObject(valueString), fieldName,
		//		operator.equalsIgnoreCase("ne"));
		//}

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

	private Condition _getFilterByCountFunctionCondition(
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
			if (type.equals("activities")) {
				return DSL.noCondition();
			}
		}

		String operator = argumentValues[1];

		_checkSurroundingQuotes(operator);

		operator = StringUtil.unquoteAndDecodeInnerQuotes(argumentValues[1]);

		if (!_allowedOperators.contains(operator)) {
			throw new IllegalArgumentException("Unknown operator: " + operator);
		}

		BigDecimal value = new BigDecimal(argumentValues[2]);

		if (value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0) {
			if (_log.isWarnEnabled()) {
				_log.warn("Invalid value: " + value);
			}

			value = BigDecimal.valueOf(Integer.MAX_VALUE);
		}

		boolean checkEqualityOnly = _isEqualityOperator(operator);

		int minDocCount;

		if (operator.equals("gt") || operator.equals("le")) {
			minDocCount = value.intValue() + 1;
		}
		else {
			minDocCount = value.intValue();
		}

		if (minDocCount <= 0) {
			if (checkEqualityOnly) {
				if (minDocCount == 0) {
					Condition condition = null;

					// if (type.equals("activities")) {
					// 	condition = _getActivitiesFilterFunctionCondition(
					// 	filterString);
					// }

					if (operator.equals("ne")) {
						return condition;
					}

					return DSL.not(condition);
				}

				if (operator.equals("ne")) {
					return DSL.noCondition();
				}

				return DSL.not(DSL.noCondition());
			}

			if (operator.equals("ge") || operator.equals("gt")) {
				return DSL.noCondition();
			}

			return DSL.not(DSL.noCondition());
		}

		boolean negate = false;

		if (operator.equals("le") || operator.equals("lt") ||
			operator.equals("ne")) {

			negate = true;
		}

		// TODO Fix _getActivitiesFilterByCountFunctionCondition

		//		if (type.equals("activities")) {
		//			return _getActivitiesFilterByCountFunctionCondition(
		//				checkEqualityOnly, filterString, minDocCount, negate,
		//				operator, value.intValue());
		//		}

		return DSL.noCondition();
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

		//		if (type.equals("activities")) {
		//			return _getActivitiesFilterFunctionCondition(filterString);
		//		}

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
			_asahMarkerDog.fetchAsahMarker("IndividualInterestScoresNanite");

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

		List<Long> individualIds = _interestDog.getOwnerIds(
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
			List<Organization> organizations =
				_organizationDog.searchOrganizations(filterString, page++, 500);

			if (organizations.isEmpty()) {
				break;
			}

			for (Organization organization : organizations) {
				condition = condition.or(
					DSL.field(
						DSL.cast(
							DSL.array(DSL.field("individual.organizationids")),
							Long[].class)
					).contains(
						DSL.cast(DSL.array(organization.getId()), Long[].class)
					));
			}
		}

		if (condition != DSL.noCondition()) {
			Long individualId = IndividualIdThreadLocal.getIndividualId();

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

	private static final Log _log = LogFactory.getLog(
		IndividualsFilterStringConverterHelper.class);

	private static final Pattern _pattern = Pattern.compile(
		".*(score eq '(false|true)').*");

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

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private OrganizationsFilterStringConverterHelper
		_organizationsFilterStringConverterHelper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}