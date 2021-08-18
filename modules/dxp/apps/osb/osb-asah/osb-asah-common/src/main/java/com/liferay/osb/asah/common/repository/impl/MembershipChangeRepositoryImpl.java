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
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.repository.SegmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Rachael Koestartyo
 */
public class MembershipChangeRepositoryImpl {

	public MembershipChangeRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countMembershipChanges(String filterString, Long segmentId) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"MembershipChange"
		).where(
			_getConditions(filterString, segmentId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<MembershipChange> searchMembershipChanges(
		String filterString, Long segmentId, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"MembershipChange"
		).where(
			_getConditions(filterString, segmentId)
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
		String filterString, Long segmentId) {

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					"individualSegmentId"
				).eq(
					segmentId
				));

			Optional<Segment> segmentOptional = _segmentRepository.findById(
				segmentId);

			Segment segment = segmentOptional.orElse(null);

			if ((segment != null) &&
				!BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers())) {

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

	@Autowired
	private SegmentRepository _segmentRepository;

}