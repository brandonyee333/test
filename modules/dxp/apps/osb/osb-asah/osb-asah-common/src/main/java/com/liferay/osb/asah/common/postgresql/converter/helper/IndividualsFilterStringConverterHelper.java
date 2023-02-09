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
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.util.StringUtil;

import java.text.ParseException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.jooq.Condition;
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

	@Autowired
	private DXPEntityDog _dxpEntityDog;

}