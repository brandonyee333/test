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

import java.util.HashMap;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class InterestFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		return new HashMap<String, String>() {
			{
				put("dateRecorded", "recordedDate");
			}
		};
	}

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (fieldName.equals("score") && operator.equals("eq")) {
			Double score = Double.valueOf(valueString);

			if (score != null) {
				Field<Double> field = DSL.field("score", Double.class);

				return field.ge(score);
			}
		}

		return null;
	}

}