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

import com.liferay.osb.asah.common.entity.BQEventProperty;

import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Alejo Ceballos
 */
public class BQEventPropertyRepositoryImpl extends BaseRepository {

	public BQEventPropertyRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			_getEventPropertySelectStep(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public Optional<BQEventProperty> findByEventAttributeDefinitionIdAndEventId(
		Long eventAttributeDefinitionId, Long eventId) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return Optional.ofNullable(
			selectSelectStep.from(
				"BQEventProperty"
			).join(
				"EventAttributeDefinition"
			).on(
				DSL.field(
					"BQEventProperty.name"
				).eq(
					DSL.field("EventAttributeDefinition.name")
				)
			).where(
				DSL.and(
					DSL.field(
						"EventAttributeDefinition.id"
					).eq(
						eventAttributeDefinitionId
					),
					DSL.field(
						"BQEventProperty.id"
					).eq(
						eventId
					))
			).fetchAny(
				record -> new BQEventProperty(record.intoMap())
			));
	}

	public List<String> searchValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		SelectConditionStep<Record1<String>> eventPropertySelectStep =
			_getEventPropertySelectStep(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords);

		return eventPropertySelectStep.orderBy(
			DSL.lower(
				DSL.field("value", String.class)
			).asc()
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			Record1::value1
		);
	}

	private Condition _getCondition(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		return DSL.field(
			"BQEvent.channelId"
		).eq(
			channelId
		).and(
			DSL.field(
				"EventDefinition.id"
			).eq(
				eventDefinitionId
			)
		).and(
			DSL.field(
				"EventAttributeDefinition.id"
			).eq(
				eventAttributeDefinitionId
			)
		).and(
			DSL.field(
				"BQEventProperty.value"
			).likeIgnoreCase(
				"%" + keywords + "%"
			)
		);
	}

	private SelectConditionStep<Record1<String>> _getEventPropertySelectStep(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(
				DSL.lower(DSL.field("value", String.class)));

		return selectSelectStep.from(
			"BQEventProperty"
		).join(
			"BQEvent"
		).on(
			DSL.field(
				"BQEvent.id"
			).eq(
				DSL.field("BQEventProperty.id")
			)
		).join(
			"EventDefinition"
		).on(
			DSL.field(
				"BQEvent.eventId"
			).eq(
				DSL.field("EventDefinition.name")
			)
		).join(
			"EventAttributeDefinition"
		).on(
			DSL.field(
				"BQEventProperty.name"
			).eq(
				DSL.field("EventAttributeDefinition.name")
			)
		).where(
			_getCondition(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords)
		);
	}

	private final DSLContext _dslContext;

}