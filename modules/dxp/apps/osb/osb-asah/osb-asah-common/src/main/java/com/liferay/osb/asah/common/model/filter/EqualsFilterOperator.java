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

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public class EqualsFilterOperator extends FilterOperator {

	public EqualsFilterOperator(
		EventAttributeDefinition.DataType dataType, List<String> values) {

		super(dataType, "eq", values);
	}

	@Override
	public Condition getCondition(Field field) {
		String value = values.get(0);

		if (value == null) {
			return field.isNull();
		}

		if (dataType.equals(EventAttributeDefinition.DataType.STRING)) {
			return field.equalIgnoreCase((String)getValue(dataType, value));
		}

		return field.eq(getValue(dataType, value));
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