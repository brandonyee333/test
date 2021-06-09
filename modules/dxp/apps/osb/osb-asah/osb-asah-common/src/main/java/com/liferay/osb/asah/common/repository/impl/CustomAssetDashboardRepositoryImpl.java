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

import com.liferay.osb.asah.common.entity.CustomAssetDashboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author André Miranda
 */
public class CustomAssetDashboardRepositoryImpl extends BaseRepository {

	public CustomAssetDashboardRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countCustomAssetDashboards(Long channelId, String keywords) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"CustomAssetDashboard"
		).where(
			_getConditions(channelId, keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<CustomAssetDashboard> searchCustomAssetDashboards(
		Long channelId, String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"CustomAssetDashboard"
		).where(
			_getConditions(channelId, keywords)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new CustomAssetDashboard(record.intoMap())
		);
	}

	private List<Condition> _getConditions(Long channelId, String keywords) {
		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			Field<Object> field = DSL.field("channelId");

			conditions.add(field.eq(channelId));
		}

		if (StringUtils.isNotEmpty(keywords)) {
			Field<Object> field = DSL.field("assetTitle");

			conditions.add(
				field.likeIgnoreCase(String.format("%%%s%%", keywords)));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}