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

import com.liferay.osb.asah.common.model.ActivityGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class ActivityGroupRepositoryImpl {

	public ActivityGroupRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countActivityGroups(
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			_getConditions(channelId, endDayDate, startDayDate, ownerId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<ActivityGroup> searchActivityGroups(
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			_getConditions(channelId, endDayDate, startDayDate, ownerId)
		).orderBy(
			_getSortFields(pageable.getSort())
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new ActivityGroup(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId) {

		List<Condition> conditions = new ArrayList<>();

		if (Objects.nonNull(channelId)) {
			Field<Object> field = DSL.field("channelId");

			conditions.add(field.eq(channelId));
		}

		if (Objects.nonNull(endDayDate)) {
			Field<Object> field = DSL.field("dayDate");

			conditions.add(field.lt(endDayDate));
		}

		if (Objects.nonNull(startDayDate)) {
			Field<Object> field = DSL.field("dayDate");

			conditions.add(field.ge(startDayDate));
		}

		if (Objects.nonNull(ownerId)) {
			Field<Object> field = DSL.field("ownerId");

			conditions.add(field.eq(ownerId));
		}

		return conditions;
	}

	private Collection<SortField<?>> _getSortFields(Sort sort) {
		Collection<SortField<?>> sortFields = new ArrayList<>();

		if (sort == null) {
			return sortFields;
		}

		for (Sort.Order order : sort.toList()) {
			Field<?> field = DSL.field(order.getProperty());

			if (order.getDirection() == Sort.Direction.ASC) {
				sortFields.add(field.asc());
			}
			else {
				sortFields.add(field.desc());
			}
		}

		return sortFields;
	}

	private final DSLContext _dslContext;

}