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

package com.liferay.osb.asah.common.model.filter;

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 * @author Leslie Wong
 */
public class NotEqualsFilterOperator extends FilterOperator {

	public NotEqualsFilterOperator(
		EventAttributeDefinition.DataType dataType, List<String> values) {

		super(dataType, "ne", values);
	}

	@Override
	public Condition getCondition(DSLHelper dslHelper, Field field) {
		String value = values.get(0);

		if (value != null) {
			if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
				return field.ne(
					dslHelper.getDateValue((Date)getValue(dataType, value)));
			}

			if (dataType.equals(EventAttributeDefinition.DataType.STRING)) {
				Field<String> stringField = DSL.field(
					field.toString(), String.class);

				return stringField.notEqualIgnoreCase(
					(String)getValue(dataType, value));
			}

			return field.ne(getValue(dataType, value));
		}

		return field.isNotNull();
	}

	@Override
	protected List<EventAttributeDefinition.DataType> getSupportedDataTypes() {
		return new ArrayList<EventAttributeDefinition.DataType>() {
			{
				add(EventAttributeDefinition.DataType.BOOLEAN);
				add(EventAttributeDefinition.DataType.DATE);
				add(EventAttributeDefinition.DataType.DURATION);
				add(EventAttributeDefinition.DataType.NUMBER);
				add(EventAttributeDefinition.DataType.STRING);
			}
		};
	}

}