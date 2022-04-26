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

package com.liferay.osb.asah.common.repository.util;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.tools.StringUtils;

/**
 * @author Rachael Koestartyo
 */
public class ConditionUtil {

	public static Condition toCondition(String filterString) {
		return toCondition(
			filterString, new DefaultFilterStringConverterHelper());
	}

	public static Condition toCondition(
		String filterString,
		FilterStringConverterHelper filterStringConverterHelper) {

		if (StringUtils.isEmpty(filterString)) {
			return DSL.noCondition();
		}

		Condition condition = FilterStringToConditionConverter.convert(
			filterString, filterStringConverterHelper);

		if (condition == null) {
			return DSL.noCondition();
		}

		return condition;
	}

	public static List<Condition> toConditions(
		List<Long> dataSourceIds, String keywords,
		String[] keywordsFieldNames) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"dataSourceId"
			).in(
				dataSourceIds
			));

		if (keywords != null) {
			List<Condition> orConditions = new ArrayList<>();

			for (String keywordsFieldName : keywordsFieldNames) {
				orConditions.add(
					DSL.field(
						keywordsFieldName, String.class
					).containsIgnoreCase(
						keywords
					));
			}

			conditions.add(DSL.or(orConditions));
		}

		return conditions;
	}

	public static List<Condition> toConditions(Map<String, Object> fields) {
		List<Condition> conditions = new ArrayList<>();

		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			Field<Object> field = DSL.field(entry.getKey());

			conditions.add(field.eq(entry.getValue()));
		}

		return conditions;
	}

}