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

import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.repository.CustomBQUserRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;

import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 */
public class BQUserRepositoryImpl
	extends BaseRepository implements CustomBQUserRepository {

	public BQUserRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"BQUser"
		).where(
			ConditionUtil.toConditions(
				dataSourceIds, keywords, new String[] {"firstName", "lastName"})
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<BQUser> findByFields(
		Map<String, Object> fields, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQUser"
		).where(
			ConditionUtil.toConditions(fields)
		).fetch(
			record -> new BQUser(record.intoMap())
		);
	}

	public List<BQUser> searchByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQUser"
		).where(
			ConditionUtil.toConditions(
				dataSourceIds, keywords, new String[] {"firstName", "lastName"})
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new BQUser(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}