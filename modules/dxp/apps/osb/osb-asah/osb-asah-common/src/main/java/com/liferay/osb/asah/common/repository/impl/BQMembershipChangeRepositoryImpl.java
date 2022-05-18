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

import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.repository.CustomBQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.SelectWhereStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class BQMembershipChangeRepositoryImpl
	implements CustomBQMembershipChangeRepository {

	public BQMembershipChangeRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countBQMembershipChanges(
		FilterHelper filterHelper, Long segmentId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"BQMembershipChange"
		).where(
			_getConditions(filterHelper, segmentId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<BQMembershipChange> searchBQMembershipChanges(
		FilterHelper filterHelper, Long segmentId, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQMembershipChange"
		).where(
			_getConditions(filterHelper, segmentId)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new BQMembershipChange(record.intoMap())
		);
	}

	@Override
	public List<BQMembershipChange>
		searchLastByCreateDateAndIndividualSegmentId(
			@Nullable Date fromCreateDate, List<Long> individualSegmentIds,
			Date toCreateDate) {

		if (individualSegmentIds.isEmpty()) {
			return Collections.emptyList();
		}

		Field<Object> createDateField = DSL.field("createdate");

		Field<Object> individualSegmentIdField = DSL.field(
			"individualsegmentid");

		Condition condition = individualSegmentIdField.in(individualSegmentIds);

		if (fromCreateDate == null) {
			condition = condition.and(createDateField.le(toCreateDate));
		}
		else {
			condition = condition.and(
				createDateField.between(fromCreateDate, toCreateDate));
		}

		Table<Record> bqMembershipChangeTable = DSL.table("BQMembershipChange");

		SelectWhereStep<Record> selectWhereStep = _dslContext.selectFrom(
			bqMembershipChangeTable);

		return selectWhereStep.where(
			DSL.row(
				createDateField, individualSegmentIdField
			).in(
				DSL.select(
					DSL.max(createDateField), individualSegmentIdField
				).from(
					bqMembershipChangeTable
				).where(
					condition
				).groupBy(
					individualSegmentIdField
				)
			)
		).fetch(
			record -> new BQMembershipChange(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		FilterHelper filterHelper, Long segmentId) {

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					"individualSegmentId"
				).eq(
					segmentId
				));
		}

		if (!StringUtils.isEmpty(filterHelper.getFilterString())) {
			conditions.add(filterHelper.getCondition());
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}