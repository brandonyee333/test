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
import com.liferay.osb.asah.common.repository.CustomAssetRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public class AssetRepositoryImpl
	extends BaseRepository implements CustomAssetRepository {

	public AssetRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countAssetKeywords(String keyword) {
		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			_getAssetKeywordSelectStep(keyword)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			_getAssetTable(assetType, canonicalUrls)
		).where(
			DSL.field(
				"Asset.rownumber"
			).eq(
				1
			)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countByAssetTypeAndFilterStringAndKeywords(
		String assetType, FilterHelper filterHelper,
		@Nullable String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTable(assetType, filterHelper, keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countByFilterString(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			_getAssetTable(null, filterHelper, null)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		Long assetId, String assetType, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			_getAssetTableKeywordNotNull(assetId, assetType));

		return selectJoinStep.orderBy(
			DSL.field(
				"id"
			).asc()
		).limit(
			size
		).fetch(
			this::_toAsset
		);
	}

	@Override
	public List<Asset> findByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls, Pageable pageable) {

		return _dslContext.select(
			DSL.table(
				"Asset"
			).asterisk()
		).from(
			_getAssetTable(assetType, canonicalUrls)
		).where(
			DSL.field(
				"Asset.rownumber"
			).eq(
				1
			)
		).orderBy(
			getSortFields(pageable.getSort(), DSL.table("Asset"))
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			this::_toAsset
		);
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterString(
		String assetType, FilterHelper filterHelper) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			_getAssetTable(assetType, filterHelper, null)
		).orderBy(
			DSL.field("title")
		).fetch(
			this::_toAsset
		);
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		String assetType, FilterHelper filterHelper, String keywords,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			_getAssetTable(assetType, filterHelper, keywords));

		return selectJoinStep.orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			this::_toAsset
		);
	}

	@Override
	public List<Asset> findByChannelIds(
		List<Long> channelIds, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Asset"
			).asterisk());

		return selectSelectStep.from(
			"Asset"
		).where(
			DSL.condition(
				String.format(
					"channelIds && '{%s}'", StringUtils.join(channelIds, ",")))
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

	@Override
	public List<Asset> findByFilterString(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			_getAssetTable(null, filterHelper, null)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			this::_toAsset
		);
	}

	@Override
	public List<String> findDataSourceAssetPKByKeyword(String keyword) {
		return _dslContext.select(
			DSL.field("dataSourceAssetPK", String.class)
		).from(
			"Asset"
		).join(
			"AssetKeyword"
		).on(
			DSL.field(
				"AssetKeyword.assetId"
			).eq(
				DSL.field("Asset.id")
			)
		).where(
			DSL.field(
				"AssetKeyword.keyword"
			).eq(
				keyword
			)
		).fetchInto(
			String.class
		);
	}

	@Override
	public List<String> findKeywordByAssetType(String assetType) {
		Field<String> keywordField = DSL.field(
			"AssetKeyword.keyword", String.class);

		return _dslContext.selectDistinct(
			keywordField
		).from(
			"Asset"
		).join(
			"AssetKeyword"
		).on(
			DSL.field(
				"AssetKeyword.assetId"
			).eq(
				DSL.field("Asset.id")
			)
		).where(
			DSL.field(
				"assetType"
			).eq(
				assetType
			)
		).orderBy(
			keywordField
		).fetchInto(
			String.class
		);
	}

	@Override
	public List<String> getAssetKeywords(String keyword, Pageable pageable) {
		return _getAssetKeywordSelectStep(
			keyword
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			Record1::value1
		);
	}

	@Override
	public List<Transformation> getAssetTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");
		String groupByField = matcher.group("groupByField");

		Condition condition = filterHelper.getCondition();

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
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	@Override
	public Map<String, Set<String>> getByAssetTypeAndChannelIdAndDatasourceId(
		String assetType, @Nullable Long channelId,
		@Nullable Long dataSourceId) {

		Map<String, Set<String>> assets = new HashMap<>();

		Condition condition = DSL.and(
			DSL.field(
				"Asset.assetType"
			).eq(
				assetType
			),
			DSL.field(
				"AssetKeyword.keyword"
			).isNotNull());

		if (channelId != null) {
			condition = condition.and(
				DSL.condition(
					String.format("%s = any (Asset.channelIds)", channelId)));
		}

		if (dataSourceId != null) {
			condition = condition.and(
				DSL.field(
					"Asset.dataSourceId"
				).eq(
					dataSourceId
				));
		}

		_dslContext.select(
			DSL.field("AssetKeyword.assetId", Long.class),
			DSL.field("AssetKeyword.keyword", String.class)
		).from(
			DSL.table("Asset")
		).innerJoin(
			DSL.table("AssetKeyword")
		).on(
			DSL.field(
				"Asset.id"
			).eq(
				DSL.field("AssetKeyword.assetId")
			)
		).where(
			condition
		).fetch(
		).forEach(
			record -> {
				Set<String> assetIds = assets.getOrDefault(
					record.value2(), new HashSet<>());

				assetIds.add(String.valueOf(record.value1()));

				assets.put(record.value2(), assetIds);
			}
		);

		return assets;
	}

	private boolean _containsAssetKeywordFilter(FilterHelper filterHelper) {
		if (StringUtils.contains(filterHelper.getFilterString(), "keywords/")) {
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
		).orderBy(
			DSL.field("keyword")
		).fetch(
			record -> new AssetKeyword(record.intoMap())
		);
	}

	private SelectConditionStep<Record1<String>> _getAssetKeywordSelectStep(
		String keyword) {

		Condition condition = null;

		Field<String> field = DSL.field("AssetKeyword.keyword", String.class);

		if (StringUtils.isEmpty(keyword)) {
			condition = field.isNotNull();
		}
		else {
			condition = field.likeIgnoreCase("%" + keyword + "%");
		}

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(field);

		return selectSelectStep.from(
			DSL.table("Asset")
		).innerJoin(
			DSL.table("AssetKeyword")
		).on(
			DSL.field(
				"Asset.id"
			).eq(
				DSL.field("AssetKeyword.assetId")
			)
		).where(
			condition
		);
	}

	private Table<Record> _getAssetTable(
		String assetType, Collection<String> canonicalUrls) {

		return _dslContext.select(
			DSL.asterisk(),
			DSL.rowNumber(
			).over(
				DSL.partitionBy(
					DSL.field("Asset.canonicalURL")
				).orderBy(
					DSL.field(
						"Asset.id"
					).desc()
				)
			).as(
				"rownumber"
			)
		).from(
			"Asset"
		).where(
			DSL.and(
				DSL.field(
					"Asset.assetType"
				).eq(
					assetType
				),
				DSL.field(
					"Asset.canonicalURL"
				).in(
					canonicalUrls
				))
		).asTable(
			"Asset"
		);
	}

	private Table<Record> _getAssetTable(
		String assetType, FilterHelper filterHelper, String keyword) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Asset"
			).asterisk());

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from("Asset");

		if (_containsAssetKeywordFilter(filterHelper)) {
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
			_getConditions(null, assetType, filterHelper, keyword)
		).asTable();
	}

	private Table<Record> _getAssetTableKeywordNotNull(
		Long assetId, String assetType) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"Asset"
			).asterisk());

		return selectSelectStep.from(
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
			_getConditions(assetId, assetType, FilterHelper.EMPTY, null)
		).and(
			DSL.field(
				"keywords.keyword"
			).isNotNull()
		).asTable();
	}

	private List<Condition> _getConditions(
		Long assetId, String assetType, FilterHelper filterHelper,
		String keyword) {

		List<Condition> conditions = new ArrayList<>();

		if (assetId != null) {
			conditions.add(
				DSL.field(
					"id"
				).gt(
					assetId
				));
		}

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

		if (StringUtils.isNotBlank(filterHelper.getFilterString())) {
			conditions.add(filterHelper.getCondition());
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