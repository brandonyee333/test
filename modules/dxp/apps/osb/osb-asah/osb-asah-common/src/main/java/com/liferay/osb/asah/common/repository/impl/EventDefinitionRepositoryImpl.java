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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class EventDefinitionRepositoryImpl extends BaseRepository {

	public EventDefinitionRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countEventDefinitions(
		Boolean blocked, String keyword, EventDefinition.Type type) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"EventDefinition"
		).where(
			_getConditions(blocked, keyword, type)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<EventDefinition> searchEventDefinitions(
		Boolean blocked, String keyword, Pageable pageable,
		EventDefinition.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"EventDefinition"
		).where(
			_getConditions(blocked, keyword, type)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new EventDefinition(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		Boolean blocked, String keyword, EventDefinition.Type type) {

		List<Condition> conditions = new ArrayList<>();

		if (blocked != null) {
			Field<Object> field = DSL.field("blocked");

			conditions.add(field.eq(blocked));
		}

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