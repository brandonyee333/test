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

import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class AssetRepositoryImpl extends BaseRepository {

	public AssetRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countAssets(String filterString) {
		SelectJoinStep<Record1<Integer>> selectJoinStep = _getSelectJoinStep(
			filterString, _dslContext.selectCount());

		return selectJoinStep.where(
			FilterStringToConditionConverter.convert(filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countAssets(
		String assetType, String keyword, String filterString) {

		SelectJoinStep<Record1<Integer>> selectJoinStep = _getSelectJoinStep(
			filterString, _dslContext.selectCount());

		return selectJoinStep.where(
			_getConditions(assetType, keyword, filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Asset> searchAssets(String filterString, Pageable pageable) {
		SelectJoinStep<Record> selectJoinStep = _getSelectJoinStep(
			filterString, _dslContext.select());

		return selectJoinStep.where(
			FilterStringToConditionConverter.convert(filterString)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Asset(record.intoMap())
		);
	}

	public List<Asset> searchAssets(
		String assetType, String keyword, String filterString,
		Pageable pageable) {

		SelectJoinStep<Record> selectJoinStep = _getSelectJoinStep(
			filterString, _dslContext.select());

		return selectJoinStep.where(
			_getConditions(assetType, keyword, filterString)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Asset(record.intoMap())
		);
	}

	private boolean _containsAssetKeywordFilter(String filterString) {
		if (StringUtils.contains(filterString, "keywords/")) {
			return true;
		}
		return false;

	}

	private List<Condition> _getConditions(
		String assetType, String keyword, String filterString) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"assetType"
			).eq(
				assetType
			));

		if (StringUtils.isNotBlank(keyword)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"canonicalURL"
					).containsIgnoreCase(
						keyword
					),
					DSL.field(
						"description"
					).containsIgnoreCase(
						keyword
					),
					DSL.field(
						"title"
					).containsIgnoreCase(
						keyword
					),
					DSL.field(
						"url"
					).containsIgnoreCase(
						keyword
					)));
		}

		if (StringUtils.isNotBlank(filterString)) {
			conditions.add(
				FilterStringToConditionConverter.convert(filterString));
		}

		return conditions;
	}

	private <T extends Record> SelectJoinStep<T> _getSelectJoinStep(
		String filterString, SelectSelectStep<T> selectSelectStep) {

		SelectJoinStep<T> selectJoinStep = selectSelectStep.from("Asset");

		if (_containsAssetKeywordFilter(filterString)) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"assetkeyword"
				).as(
					"keywords"
				)
			).on(
				DSL.field(
					"id"
				).eq(
					DSL.field("keywords.assetid")
				)
			);
		}

		return selectJoinStep;
	}

	private final DSLContext _dslContext;

}
