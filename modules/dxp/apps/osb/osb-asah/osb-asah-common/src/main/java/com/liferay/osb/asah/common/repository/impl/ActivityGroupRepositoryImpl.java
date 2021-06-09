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
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.postgresql.converter.helper.ActivitiesFilterStringConverterHelper;

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

	public ActivityGroupRepositoryImpl(
		ActivitiesFilterStringConverterHelper
			activitiesFilterStringConverterHelper,
		DSLContext dslContext) {

		_activitiesFilterStringConverterHelper =
			activitiesFilterStringConverterHelper;
		_dslContext = dslContext;
	}

	public long countActivityGroups(String filterString) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			FilterStringToConditionConverter.convert(
				filterString, _activitiesFilterStringConverterHelper)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<ActivityGroup> searchActivityGroups(
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"ActivityGroup"
		).where(
			FilterStringToConditionConverter.convert(
				filterString, _activitiesFilterStringConverterHelper)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new ActivityGroup(record.intoMap())
		);
	}

	private final ActivitiesFilterStringConverterHelper
		_activitiesFilterStringConverterHelper;
	private final DSLContext _dslContext;

}