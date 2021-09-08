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
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public class AssetRepositoryImpl extends BaseRepository {

	public AssetRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countByAssetTypeAndAssetKeywordNotNull(String assetType) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTableKeywordNotNull(assetType)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countByAssetTypeAndFilterStringAndKeywords(
		String assetType, @Nullable String filterString,
		@Nullable String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTable(assetType, filterString, keywords)
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

	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		String assetType, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			_getAssetTableKeywordNotNull(assetType));

		if (pageable == null) {
			return selectJoinStep.fetch(
			).map(
				this::_toAsset
			);
		}

		return selectJoinStep.orderBy(
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

	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		String assetType, @Nullable String filterString,
		@Nullable String keywords, @Nullable Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			_getAssetTable(assetType, filterString, keywords));

		if (pageable == null) {
			return selectJoinStep.fetch(
			).map(
				this::_toAsset
			);
		}

		return selectJoinStep.orderBy(
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

	public List<Transformation> getAssetTransformations(
		String apply, @Nullable String filterString, Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");
		String groupByField = matcher.group("groupByField");

		Condition condition = ConditionUtil.toCondition(filterString);

		condition = condition.and(
			_getIncludeCondition(containsField, groupByField));

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.field(
				groupByField
			).as(
				"terms"
			),
			DSL.count(
				DSL.field("id")
			).as(
				"totalelements"
			)
		).from(
			"Asset"
		).where(
			condition
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
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

	private Table<Record> _getAssetTableKeywordNotNull(String assetType) {
		SelectSelectStep<Record> selectDistinct = _dslContext.selectDistinct(
			DSL.table(
				"Asset"
			).asterisk());

		return selectDistinct.from(
			"Asset"
		).join(
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
		).where(
			_getConditions(assetType, null, null)
		).and(
			DSL.field(
				"keywords.keyword"
			).isNotNull()
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

	private Condition _getIncludeCondition(
		String containsField, String fieldName) {

		if (containsField == null) {
			return DSL.noCondition();
		}

		return DSL.field(
			fieldName
		).containsIgnoreCase(
			containsField
		);
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