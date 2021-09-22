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

import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;

import org.springframework.data.domain.Pageable;

/**
 * @author Inácio Nery
 */
public class ActivityGroupRepositoryImpl extends BaseRepository {

	public ActivityGroupRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countActivityGroups(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			filterHelper.getCondition()
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<ActivityGroup> searchActivityGroups(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			filterHelper.getCondition()
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new ActivityGroup(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}