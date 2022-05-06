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

import com.liferay.osb.asah.common.entity.BQRole;
import com.liferay.osb.asah.common.repository.CustomBQRoleRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;

import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 */
public class BQRoleRepositoryImpl
	extends BaseRepository implements CustomBQRoleRepository {

	public BQRoleRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"BQRole"
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
	public List<BQRole> searchByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQRole"
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
			record -> new BQRole(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}