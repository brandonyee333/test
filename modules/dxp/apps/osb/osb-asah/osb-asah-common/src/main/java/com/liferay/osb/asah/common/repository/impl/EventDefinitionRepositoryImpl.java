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
import com.liferay.osb.asah.common.model.EventDefinitionType;
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
		EventDefinitionType eventDefinitionType, String keyword) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			"EventDefinition"
		).where(
			_getConditions(eventDefinitionType, keyword)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<EventDefinition> searchEventDefinitions(
		EventDefinitionType eventDefinitionType, String keyword, int page,
		int size, Sort sort) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<?> field = DSL.field(sort.getColumn());

		return select.from(
			"EventDefinition"
		).where(
			_getConditions(eventDefinitionType, keyword)
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
		EventDefinitionType eventDefinitionType, String keyword) {

		List<Condition> conditions = new ArrayList<>();

		if ((eventDefinitionType != null) &&
			!eventDefinitionType.equals(EventDefinitionType.ALL)) {

			Field<Object> field = DSL.field("type");

			conditions.add(field.in(eventDefinitionType.toString()));
		}

		if (StringUtils.isNotEmpty(keyword)) {
			Field<Object> field = DSL.field("name");

			conditions.add(field.containsIgnoreCase(keyword));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}