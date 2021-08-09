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
import com.liferay.osb.asah.common.model.DateGrouping;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 * @author Matthew Kong
 */
public class DateGroupingFilterOperator extends FilterOperator {

	public DateGroupingFilterOperator(
		EventAttributeDefinition.DataType dataType, List<String> values) {

		super(dataType, 2, "dateGrouping", values);
	}

	@Override
	public Condition getCondition(Field field) {
		DateGrouping dateGrouping = DateGrouping.valueOf(values.get(0));

		if (dateGrouping.equals(DateGrouping.DAY)) {
			field = DSL.concat(
				DSL.extract(field, DatePart.YEAR), DSL.val("-"),
				DSL.extract(field, DatePart.MONTH), DSL.val("-"),
				DSL.extract(field, DatePart.DAY));
		}
		else if (dateGrouping.equals(DateGrouping.MONTH)) {
			field = DSL.concat(
				DSL.extract(field, DatePart.YEAR), DSL.val("-"),
				DSL.extract(field, DatePart.MONTH));
		}
		else if (dateGrouping.equals(DateGrouping.YEAR)) {
			field = DSL.extract(field, DatePart.YEAR);
		}

		return field.eq(
			getValue(EventAttributeDefinition.DataType.STRING, values.get(1)));
	}

	@Override
	protected List<EventAttributeDefinition.DataType> getSupportedDataTypes() {
		return new ArrayList<EventAttributeDefinition.DataType>() {
			{
				add(EventAttributeDefinition.DataType.DATE);
			}
		};
	}

}