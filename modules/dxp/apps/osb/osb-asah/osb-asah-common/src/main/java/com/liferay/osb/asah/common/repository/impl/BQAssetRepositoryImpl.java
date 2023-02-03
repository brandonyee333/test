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

import com.liferay.osb.asah.common.entity.BQAsset;
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.postgresql.converter.helper.AssetFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.CustomBQAssetRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.HashMap;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Ivica Cardic
 */
public class BQAssetRepositoryImpl
	extends BaseRepository implements CustomBQAssetRepository {

	public BQAssetRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public List<BQAsset> searchBQAssets(
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQAsset::new,
			selectSelectStep.from(
				DSL.table(
					"BQAsset"
				).as(
					"Asset"
				)
			).where(
				FilterExpression.convert(
					filterString, new AssetFilterStringConverterHelper())
			).orderBy(
				getSortFields(
					new HashMap<String, String>() {
						{
							put("count", "views");
							put("desc", "assetTitle");
						}
					},
					pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}