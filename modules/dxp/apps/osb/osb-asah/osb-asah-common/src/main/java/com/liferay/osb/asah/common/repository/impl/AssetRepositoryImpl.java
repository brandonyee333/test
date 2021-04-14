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

import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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

	public long countAssets(
		String assetType, String keyword,
		List<PropertyFilter> propertyFilters) {

		SelectJoinStep<Record1<Integer>> selectJoinStep = _getSelectJoinStep(
			propertyFilters, _dslContext.selectCount());

		return selectJoinStep.where(
			_getConditions(assetType, keyword, propertyFilters)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Asset> searchAssets(
		String assetType, String keyword, List<PropertyFilter> propertyFilters,
		Pageable pageable) {

		SelectJoinStep<Record> selectJoinStep = _getSelectJoinStep(
			propertyFilters, _dslContext.select());

		return selectJoinStep.where(
			_getConditions(assetType, keyword, propertyFilters)
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

	private boolean _containsAssetKeywordPropertyFilter(
		List<PropertyFilter> propertyFilters) {

		if (propertyFilters == null) {
			return false;
		}

		Stream<PropertyFilter> stream = propertyFilters.stream();

		return stream.anyMatch(
			propertyFilter -> StringUtils.startsWith(
				propertyFilter.getPropertyName(), "keywords"));
	}

	private Condition _getCondition(List<PropertyFilter> propertyFilters) {
		List<Condition> conditions = new ArrayList<>();

		for (PropertyFilter propertyFilter : propertyFilters) {
			conditions.add(_getCondition(propertyFilter));
		}

		return DSL.and(conditions);
	}

	private Condition _getCondition(PropertyFilter propertyFilter) {
		Condition condition = _getCondition(
			propertyFilter.getOperator(), propertyFilter.getPropertyName(),
			propertyFilter.getPropertyValue());

		if (propertyFilter.isNegate()) {
			condition = DSL.not(condition);
		}

		List<PropertyFilter> propertyFilters =
			propertyFilter.getPropertyFilters();

		if (propertyFilters.isEmpty()) {
			return condition;
		}

		return DSL.and(condition, _getCondition(propertyFilters));
	}

	private Condition _getCondition(
		String operator, String propertyName, String propertyValue) {

		if (Objects.equals(operator, "~")) {
			return DSL.field(
				propertyName
			).similarTo(
				propertyValue
			);
		}

		return DSL.field(
			propertyName
		).eq(
			propertyValue
		);
	}

	private List<Condition> _getConditions(
		String assetType, String keyword,
		List<PropertyFilter> propertyFilters) {

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

		if ((propertyFilters != null) && !propertyFilters.isEmpty()) {
			conditions.add(_getCondition(propertyFilters));
		}

		return conditions;
	}

	private <T extends Record> SelectJoinStep<T> _getSelectJoinStep(
		List<PropertyFilter> propertyFilters,
		SelectSelectStep<T> selectSelectStep) {

		SelectJoinStep<T> selectJoinStep = selectSelectStep.from("Asset");

		if (_containsAssetKeywordPropertyFilter(propertyFilters)) {
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