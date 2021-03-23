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

import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class EventAttributeDefinitionRepositoryImpl {

	public EventAttributeDefinitionRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countEventAttributeDefinitions(String name) {
		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			"EventAttributeDefinition"
		).where(
			_getConditions(name)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<EventAttributeDefinition> searchEventAttributeDefinitions(
		String name, int page, int size, Sort sort) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<?> field = DSL.field(sort.getColumn());

		List<EventAttributeDefinition> eventAttributeDefinitions = select.from(
			"EventAttributeDefinition"
		).where(
			_getConditions(name)
		).orderBy(
			field.sort(SortOrder.valueOf(sort.getType()))
		).limit(
			size
		).offset(
			page * size
		).fetch(
		).map(
			record -> new EventAttributeDefinition(record.intoMap())
		);

		Stream<EventAttributeDefinition> stream =
			eventAttributeDefinitions.stream();

		Map<Long, EventAttributeDefinition> eventAttributeDefinitionsById =
			stream.collect(
				Collectors.toMap(
					EventAttributeDefinition::getId, Function.identity()));

		_populateEventDefinitionEventAttributeDefinition(
			eventAttributeDefinitionsById);

		return new ArrayList<>(eventAttributeDefinitionsById.values());
	}

	private List<Condition> _getConditions(String name) {
		List<Condition> conditions = new ArrayList<>();

		if (StringUtils.isNotEmpty(name)) {
			Field<Object> field = DSL.field("name");

			conditions.add(field.containsIgnoreCase(name));
		}

		return conditions;
	}

	private void _populateEventDefinitionEventAttributeDefinition(
		Map<Long, EventAttributeDefinition> eventAttributeDefinitionsById) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("eventAttributeDefinitionId");

		select.from(
			"EventDefinitionEventAttributeDefinition"
		).where(
			field.in(eventAttributeDefinitionsById.keySet())
		).fetch(
		).forEach(
			record -> {
				Map<String, Object> recordMap = record.intoMap();

				EventAttributeDefinition eventAttributeDefinition =
					eventAttributeDefinitionsById.get(
						(Long)recordMap.get("eventattributedefinitionid"));

				eventAttributeDefinition.
					addEventDefinitionEventAttributeDefinition(
						new EventDefinitionEventAttributeDefinition(recordMap));
			}
		);
	}

	private final DSLContext _dslContext;

}