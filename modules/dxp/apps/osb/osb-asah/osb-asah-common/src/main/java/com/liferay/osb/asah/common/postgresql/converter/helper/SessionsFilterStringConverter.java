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

import org.jooq.Condition;

/**
 * @author Ivica Cardic
 */
public class SessionsFilterStringConverter
	extends DefaultFilterStringConverterHelper {

	@Override
	public String getFilterType() {
		return "sessions";
	}

	@Override
	public Condition getLogicFunctionCondition(
			String fieldName, String operator, boolean processString,
			String valueString)
		throws Exception {

		Condition condition = getTimeFrameCondition(
			fieldName, operator, processString, valueString);

		if (condition != null) {
			return condition;
		}

		return null;
	}

	@Override
	public String getTableName() {
		return "Session";
	}

}