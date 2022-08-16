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

/**
 * @author Leslie Wong
 */
public class LessThanFilterOperator extends FilterOperator {

	public LessThanFilterOperator(
		EventAttributeDefinition.DataType dataType, DSLHelper dslHelper,
		List<String> values) {

		super(dataType, dslHelper, "lt", values);
	}

	@Override
	public Condition getCondition(Field field) {
		String value = values.get(0);

		if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			return field.lt(
				dslHelper.getDateValue((Date)getValue(dataType, value)));
		}

		return field.lt(getValue(dataType, value));
	}

	@Override
	protected List<EventAttributeDefinition.DataType> getSupportedDataTypes() {
		return new ArrayList<EventAttributeDefinition.DataType>() {
			{
				add(EventAttributeDefinition.DataType.DATE);
				add(EventAttributeDefinition.DataType.DURATION);
				add(EventAttributeDefinition.DataType.NUMBER);
			}
		};
	}

}