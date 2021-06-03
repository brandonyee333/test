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
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class AccountsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Condition getInferredCondition(String fieldName) {
		if (!fieldName.startsWith("organization") ||
			!fieldName.endsWith("value")) {

			return null;
		}

		String[] fieldNames = fieldName.split("\\.");

		fieldName = fieldNames[1];

		Field<Object> nameField = DSL.field("name");

		return nameField.eq(fieldName);
	}

}