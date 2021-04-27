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
import com.liferay.osb.asah.common.model.ResultBag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author André Miranda
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class CustomAssetDashboardRepositoryImpl extends BaseRepository {

	public CustomAssetDashboardRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public ResultBag<CustomAssetDashboard> searchCustomAssetDashboard(
		Long channelId, String keyword, Pageable pageable) {

		ResultBag<CustomAssetDashboard> resultBag = new ResultBag<>();

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		selectSelectStep.from(
			"CustomAssetDashboard"
		).where(
			_getConditions(channelId, keyword)
		);

		resultBag.setTotal(_dslContext.fetchCount(selectSelectStep));

		List<CustomAssetDashboard> customAssetDashboards =
			selectSelectStep.orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
			).map(
				record -> new CustomAssetDashboard(record.intoMap())
			);

		resultBag.setResults(customAssetDashboards);

		return resultBag;
	}

	private List<Condition> _getConditions(Long channelId, String keyword) {
		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			Field<Object> field = DSL.field("channelId");

			conditions.add(field.eq(channelId));
		}

		if (StringUtils.isNotEmpty(keyword)) {
			Field<Object> field = DSL.field("assetTitle");

			conditions.add(
				field.likeIgnoreCase(String.format("%%%s%%", keyword)));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}