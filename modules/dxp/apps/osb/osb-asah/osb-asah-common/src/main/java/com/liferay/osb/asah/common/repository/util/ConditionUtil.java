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

import org.jooq.Condition;
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

}