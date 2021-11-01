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

import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public class InterestRepositoryImpl extends BaseRepository {

	public InterestRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, @Nullable Double score) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(filterHelper, null, null, null, null, null, score)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Interest> findByFilterStringAndScoreGreaterThanEqual(
		@Nullable FilterHelper filterHelper, @Nullable Double score,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(filterHelper, null, null, null, null, null, score)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Interest(record.intoMap())
		);
	}

	public List<Interest> findByOwnerTypeAndRecordedDate(
		@Nullable Long interestId, @Nullable String ownerType,
		@Nullable Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(
				null, interestId, null, null, ownerType, recordedDate, null)
		).orderBy(
			DSL.field("id")
		).limit(
			size
		).fetch(
		).map(
			record -> new Interest(record.intoMap())
		);
	}

	public List<Distribution> getInterestDistributions(
		@Nullable String keyword, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score, Pageable pageable) {

		SelectSelectStep<Record2<Integer, Object>> selectSelectStep =
			_dslContext.select(
				DSL.count(
				).as(
					"count"
				),
				DSL.field("name"));

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(
				null, null, keyword, ownerIds, ownerType, recordedDate, score)
		).groupBy(
			DSL.field("name")
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.getValue("name")))
		);
	}

	public List<String> getTopNamesByOwnerIdAndOwnerType(
		Long ownerId, String ownerType, int size) {

		SelectSelectStep<Record1<String>> selectSelectStep = _dslContext.select(
			DSL.field("name", String.class));

		return selectSelectStep.from(
			"Interest"
		).where(
			DSL.and(
				DSL.field(
					"ownerId"
				).equal(
					ownerId
				),
				DSL.field(
					"ownerType"
				).equal(
					ownerType
				))
		).orderBy(
			DSL.field(
				"score"
			).desc()
		).limit(
			size
		).offset(
			0
		).fetch(
			record -> (String)record.get("name")
		);
	}

	private List<Condition> _getConditions(
		@Nullable FilterHelper filterHelper, @Nullable Long interestId,
		@Nullable String keywords, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate, Double score) {

		List<Condition> conditions = new ArrayList<>();

		if ((filterHelper != null) &&
			!StringUtils.isEmpty(filterHelper.getFilterString())) {

			conditions.add(filterHelper.getCondition());
		}

		if (!StringUtils.isBlank(keywords)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"name"
					).containsIgnoreCase(
						keywords
					)
				).or(
					DSL.field(
						"name"
					).similarTo(
						keywords
					)
				));
		}

		if (interestId != null) {
			conditions.add(
				DSL.field(
					"id"
				).gt(
					interestId
				));
		}

		if ((ownerIds != null) && !ownerIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"ownerId"
				).in(
					ownerIds
				));
		}

		if (ownerType != null) {
			conditions.add(
				DSL.field(
					"ownerType"
				).eq(
					ownerType
				));
		}

		if (recordedDate != null) {
			conditions.add(
				DSL.field(
					"recordedDate"
				).eq(
					recordedDate
				));
		}

		if (score != null) {
			conditions.add(
				DSL.field(
					"score"
				).greaterOrEqual(
					score
				));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}