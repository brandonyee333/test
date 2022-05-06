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

import com.liferay.osb.asah.common.entity.BQGroup;
import com.liferay.osb.asah.common.repository.CustomBQGroupRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcos Martins
 */
public class BQGroupRepositoryImpl
	extends BaseRepository implements CustomBQGroupRepository {

	public BQGroupRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, @Nullable String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"BQGroup"
		).where(
			ConditionUtil.toConditions(
				dataSourceIds, keywords, new String[] {"name"})
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<BQGroup> searchByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, @Nullable String keywords,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQGroup"
		).where(
			ConditionUtil.toConditions(
				dataSourceIds, keywords, new String[] {"name"})
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new BQGroup(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}