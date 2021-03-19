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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.Sort;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class EventDefinitionRepositoryImpl {

	public EventDefinitionRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countEventDefinitions(
		String keyword, EventDefinition.Type type) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"EventDefinition"
		).where(
			_getConditions(keyword, type)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<EventDefinition> searchEventDefinitions(
		String keyword, int page, int size, Sort sort,
		EventDefinition.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<?> field = DSL.field(sort.getColumn());

		return selectSelectStep.from(
			"EventDefinition"
		).where(
			_getConditions(keyword, type)
		).orderBy(
			field.sort(SortOrder.valueOf(sort.getType()))
		).limit(
			size
		).offset(
			page * size
		).fetch(
		).map(
			record -> new EventDefinition(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		String keyword, EventDefinition.Type type) {

		List<Condition> conditions = new ArrayList<>();

		if ((type != null) && !type.equals(EventDefinition.Type.ALL)) {
			Field<Object> field = DSL.field("type");

			conditions.add(field.in(type.toString()));
		}

		if (StringUtils.isNotEmpty(keyword)) {
			Field<Object> field = DSL.field("name");

			conditions.add(field.containsIgnoreCase(keyword));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}