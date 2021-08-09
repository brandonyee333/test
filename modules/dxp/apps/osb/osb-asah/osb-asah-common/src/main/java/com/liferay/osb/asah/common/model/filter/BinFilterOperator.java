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
import org.jooq.impl.DSL;

/**
 * @author Matthew Kong
 */
public class BinFilterOperator extends FilterOperator {

	public BinFilterOperator(
		EventAttributeDefinition.DataType dataType, List<String> values) {

		super(dataType, 2, "bin", values);
	}

	@Override
	public Condition getCondition(Field field) {
		Number binSize = (Number)getValue(dataType, values.get(0));

		return DSL.floor(
			field.div(binSize)
		).multiply(
			binSize
		).eq(
			getValue(dataType, values.get(1))
		);
	}

	@Override
	protected List<EventAttributeDefinition.DataType> getSupportedDataTypes() {
		return new ArrayList<EventAttributeDefinition.DataType>() {
			{
				add(EventAttributeDefinition.DataType.DURATION);
				add(EventAttributeDefinition.DataType.NUMBER);
			}
		};
	}

}