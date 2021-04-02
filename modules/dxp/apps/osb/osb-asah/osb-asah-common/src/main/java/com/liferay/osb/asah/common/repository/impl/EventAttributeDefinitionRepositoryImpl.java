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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class EventAttributeDefinitionRepositoryImpl extends BaseRepository {

	public EventAttributeDefinitionRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countEventAttributeDefinitions(String name) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
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
		String name, Pageable pageable) {

		Map<Long, EventAttributeDefinition> eventAttributeDefinitionsById =
			new LinkedHashMap<>();

		SelectSelectStep<?> selectSelectStep = _dslContext.select();

		Table<?> table = _getTopEventAttributeDefinitionsTable(name, pageable);

		Field<Object> field = DSL.field("eventAttributeDefinitionId");

		selectSelectStep.from(
			"EventDefinitionEventAttributeDefinition"
		).join(
			table
		).on(
			field.eq(DSL.field(table + ".id"))
		).orderBy(
			getSortFields(pageable.getSort(), table)
		).fetch(
		).forEach(
			record -> {
				Map<String, Object> recordMap = record.intoMap();

				Long id = (Long)recordMap.get("id");

				EventAttributeDefinition eventAttributeDefinition =
					eventAttributeDefinitionsById.computeIfAbsent(
						id,
						eventAttributeDefinitionId ->
							new EventAttributeDefinition(recordMap));

				eventAttributeDefinition.
					addEventDefinitionEventAttributeDefinition(
						new EventDefinitionEventAttributeDefinition(recordMap));
			}
		);

		return new ArrayList<>(eventAttributeDefinitionsById.values());
	}

	private List<Condition> _getConditions(String name) {
		List<Condition> conditions = new ArrayList<>();

		if (StringUtils.isNotEmpty(name)) {
			Field<Object> descriptionField = DSL.field("description");
			Field<Object> displayNameField = DSL.field("displayName");
			Field<Object> nameField = DSL.field("name");

			conditions.add(
				DSL.or(
					descriptionField.containsIgnoreCase(name),
					displayNameField.containsIgnoreCase(name),
					nameField.containsIgnoreCase(name)));
		}

		return conditions;
	}

	private Table<?> _getTopEventAttributeDefinitionsTable(
		String name, Pageable pageable) {

		SelectSelectStep<?> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"EventAttributeDefinition"
		).where(
			_getConditions(name)
		).groupBy(
			DSL.field("id")
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).asTable(
			"topEventAttributeDefinitions"
		);
	}

	private final DSLContext _dslContext;

}