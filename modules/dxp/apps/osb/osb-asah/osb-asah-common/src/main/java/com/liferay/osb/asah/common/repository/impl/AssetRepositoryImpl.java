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
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

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

	public long countByAssetTypeAndFilterStringAndKeyword(
		String assetType, @Nullable String filterString,
		@Nullable String keyword) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTable(assetType, filterString, keyword)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countByFilterString(@Nullable String filterString) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTable(null, filterString, null)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Asset> findByAssetTypeAndFilterStringAndKeyword(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			_getAssetTable(assetType, filterString, keyword)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			this::_toAsset
		);
	}

	public List<Asset> findByFilterString(
		@Nullable String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			_getAssetTable(null, filterString, null)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			this::_toAsset
		);
	}

	private boolean _containsAssetKeywordFilter(String filterString) {
		if (StringUtils.contains(filterString, "keywords/")) {
			return true;
		}

		return false;
	}

	private List<AssetKeyword> _findAssetKeywordsByAssetId(Long assetId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"AssetKeyword"
		).where(
			DSL.field(
				"assetId"
			).eq(
				assetId
			)
		).fetch(
		).map(
			record -> new AssetKeyword(record.intoMap())
		);
	}

	private Table<Record> _getAssetTable(
		String assetType, String filterString, String keyword) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Asset"
			).asterisk());

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from("Asset");

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

		return selectJoinStep.where(
			_getConditions(assetType, filterString, keyword)
		).asTable();
	}

	private List<Condition> _getConditions(
		String assetType, String filterString, String keyword) {

		List<Condition> conditions = new ArrayList<>();

		if (StringUtils.isNotBlank(assetType)) {
			conditions.add(
				DSL.field(
					"assetType"
				).eq(
					assetType
				));
		}

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

	private Asset _toAsset(Record assetRecord) {
		Asset asset = new Asset(assetRecord.intoMap());

		List<AssetKeyword> assetKeywords = _findAssetKeywordsByAssetId(
			asset.getId());

		if (!assetKeywords.isEmpty()) {
			asset.setAssetKeywords(new HashSet<>(assetKeywords));
		}

		return asset;
	}

	private final DSLContext _dslContext;

}