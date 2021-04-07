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

import com.liferay.osb.asah.common.entity.Event;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class EventRepositoryImpl extends BaseRepository {

	public EventRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public Event findLastSeenEvent(Long eventDefinitionId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<?> field = DSL.field("eventDate");

		return selectSelectStep.from(
			"Event"
		).where(
			_getConditions(eventDefinitionId)
		).orderBy(
			field.desc()
		).limit(
			1
		).fetchOne(
			record -> new Event(record.intoMap())
		);
	}

	private List<Condition> _getConditions(Long eventDefinitionId) {
		List<Condition> conditions = new ArrayList<>();

		if (eventDefinitionId != null) {
			Field<Object> field = DSL.field("eventDefinitionId");

			conditions.add(field.eq(eventDefinitionId));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}