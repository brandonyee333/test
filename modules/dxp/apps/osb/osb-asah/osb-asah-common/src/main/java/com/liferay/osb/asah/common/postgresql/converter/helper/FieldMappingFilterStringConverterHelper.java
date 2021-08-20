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
import com.liferay.osb.asah.common.util.StringUtil;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 * @author Rachael Koestartyo
 */
public class FieldMappingFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (!fieldName.startsWith("dataSourceFieldNames/")) {
			return null;
		}

		String[] fieldNames = fieldName.split("/");

		fieldName = fieldNames[1];

		Field<Object> field = DSL.field("datasourcefieldmapping.datasourceid");

		Condition condition = null;

		if (!StringUtil.isNull(fieldName)) {
			condition = field.eq(Long.valueOf(fieldName));

			if (operator.equalsIgnoreCase("eq") &&
				StringUtil.isNull(valueString)) {

				condition = DSL.notExists(
					DSL.selectOne(
					).from(
						"DataSourceFieldMapping"
					).where(
						condition
					));
			}
			else if (operator.equalsIgnoreCase("ne") &&
					 StringUtil.isNull(valueString)) {

				condition = DSL.and(
					DSL.exists(
						DSL.selectOne(
						).from(
							"DataSourceFieldMapping"
						).where(
							condition
						)),
					condition);
			}
		}
		else if (operator.equalsIgnoreCase("eq")) {
			if (StringUtil.isNull(valueString)) {
				condition = field.isNull();
			}
			else {
				condition = field.eq(Long.valueOf(fieldName));
			}
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (StringUtil.isNull(valueString)) {
				condition = field.isNotNull();
			}
			else {
				condition = field.ne(Long.valueOf(fieldName));
			}
		}

		return condition;
	}

}