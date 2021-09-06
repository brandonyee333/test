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

import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;

import java.util.ArrayList;
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

/**
 * @author Rachael Koestartyo
 */
public class MembershipChangeRepositoryImpl {

	public MembershipChangeRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countMembershipChanges(
		String filterString, Boolean includeAnonymousUsers, Long segmentId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"MembershipChange"
		).where(
			_getConditions(filterString, includeAnonymousUsers, segmentId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<MembershipChange>
		searchLastByDateChangedPeriodAndIndividualSegmentId(
			Date dateChangedEnd, Date dateChangedStart,
			boolean includeAnonymousUsers, List<Long> individualSegmentIds) {

		Field<Object> individualSegmentIdField = DSL.field(
			"individualsegmentid");
		Field<Object> modifiedDateField = DSL.field("modifieddate");

		Condition condition = individualSegmentIdField.in(individualSegmentIds);

		condition = condition.and(
			modifiedDateField.between(dateChangedStart, dateChangedEnd));

		if (!includeAnonymousUsers) {
			condition = condition.and(
				DSL.field(
					"individualemail"
				).isNotNull());
		}

		Table<Record> membershipChangeTable = DSL.table("membershipchange");

		SelectWhereStep<Record> selectWhereStep = _dslContext.selectFrom(
			membershipChangeTable);

		Field<Object> individualsCountField = DSL.field("individualscount");

		return selectWhereStep.where(
			DSL.row(
				individualSegmentIdField, individualsCountField,
				modifiedDateField
			).in(
				DSL.select(
					individualSegmentIdField, DSL.max(individualsCountField),
					DSL.max(modifiedDateField)
				).from(
					membershipChangeTable
				).where(
					condition
				).groupBy(
					individualSegmentIdField
				)
			)
		).fetch(
		).map(
			record -> new MembershipChange(record.intoMap())
		);
	}

	public List<MembershipChange> searchMembershipChanges(
		String filterString, Boolean includeAnonymousUsers, Long segmentId,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"MembershipChange"
		).where(
			_getConditions(filterString, includeAnonymousUsers, segmentId)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new MembershipChange(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		String filterString, Boolean includeAnonymousUsers, Long segmentId) {

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					"individualSegmentId"
				).eq(
					segmentId
				));

			if (!includeAnonymousUsers) {
				conditions.add(
					DSL.field(
						"individualEmail"
					).isNotNull());
			}
		}

		if (!StringUtils.isEmpty(filterString)) {
			conditions.add(
				FilterStringToConditionConverter.convert(filterString));
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}