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

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Inácio Nery
 */
public class SegmentRepositoryImpl extends BaseRepository {

	public SegmentRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		String filterString) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(
				dataSourceFieldMappingIds, dataSourceId, filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countSegments(List<Long> channelIds, String filterString) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(channelIds, filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countSegments(
		@Nullable String filterString, @Nullable List<Long> segmentIds) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(filterString, segmentIds)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Transformation> getSegmentTransformations(
		String apply, @Nullable String filterString, Pageable pageable,
		@Nullable List<Long> segmentIds) {

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

		if ((segmentIds != null) && !segmentIds.isEmpty()) {
			condition = condition.and(
				DSL.field(
					"id"
				).in(
					segmentIds
				));
		}

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
			"Segment"
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

	public List<Segment> searchDynamicSegments(
		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs,
		String filterString, boolean includeAnonymousUsers, Pageable pageable,
		Set<Long> segmentIds) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"type"
			).eq(
				Segment.Type.DYNAMIC.toString()
			));

		if (StringUtils.isNotEmpty(filterString)) {
			conditions.add(
				FilterStringToConditionConverter.convert(filterString));
		}

		if (CollectionUtils.isEmpty(dataSourceAccountPKs)) {
			conditions.add(
				DSL.not(
					DSL.and(
						DSL.field(
							"filter"
						).startsWith(
							"((dataSourceAccountPKs/accountPKs eq '"
						),
						DSL.field(
							"name"
						).startsWith(
							"Account: "
						),
						DSL.field(
							"status"
						).eq(
							"INACTIVE"
						))));
		}
		else {
			Set<String> filterStrings = new HashSet<>();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				Set<String> accountPKs = dataSourceAccountPK.getAccountPKs();

				for (String accountPK : accountPKs) {
					filterStrings.add(
						"((dataSourceAccountPKs/accountPKs eq '" + accountPK +
							"'))");
				}
			}

			conditions.add(
				DSL.or(
					DSL.field(
						"status"
					).eq(
						"ACTIVE"
					),
					DSL.and(
						DSL.field(
							"status"
						).eq(
							"INACTIVE"
						),
						DSL.field(
							"filter"
						).in(
							filterStrings
						))));
		}

		if (!includeAnonymousUsers) {
			conditions.add(
				DSL.or(
					DSL.field(
						"includeAnonymousUsers"
					).isNull(),
					DSL.field(
						"includeAnonymousUsers"
					).eq(
						false
					)));
		}
		else {
			conditions.add(
				DSL.field(
					"includeAnonymousUsers"
				).eq(
					true
				));
		}

		if (CollectionUtils.isNotEmpty(segmentIds)) {
			conditions.add(
				DSL.field(
					"id"
				).in(
					segmentIds
				));
		}

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			conditions
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchDynamicSegments(
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			DSL.and(
				FilterStringToConditionConverter.convert(filterString),
				DSL.field(
					"type"
				).eq(
					Segment.Type.DYNAMIC.toString()
				))
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(
				dataSourceFieldMappingIds, dataSourceId, filterString)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchSegments(
		List<Long> channelIds, String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(channelIds, filterString)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchSegments(
		Long dxpEntityId, DXPEntity.Type dxpEntityType, String state,
		Segment.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(dxpEntityId, dxpEntityType, state, type)
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchSegments(
		@Nullable String filterString, @Nullable List<Long> segmentIds,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(filterString, segmentIds)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(filter, state, status)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		List<Long> channelIds, String filterString) {

		List<Condition> conditions = new ArrayList<>();

		if (!channelIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"channelId"
				).in(
					channelIds
				));
		}

		conditions.add(FilterStringToConditionConverter.convert(filterString));

		return conditions;
	}

	private List<Condition> _getConditions(
		Long dxpEntityId, DXPEntity.Type dxpEntityType, String state,
		Segment.Type type) {

		List<Condition> conditions = new ArrayList<>();

		if (dxpEntityType != null) {
			Field<Object> field = DSL.field(
				dxpEntityType.getIndividualSegmentFieldName());

			conditions.add(field.in(dxpEntityId));
		}

		if (StringUtils.isNotEmpty(state)) {
			Field<Object> field = DSL.field("state");

			conditions.add(field.notEqual(state));
		}

		if (type != null) {
			Field<Object> field = DSL.field("type");

			conditions.add(field.eq(type.toString()));
		}

		return conditions;
	}

	private List<Condition> _getConditions(
		String filterString, List<Long> segmentIds) {

		List<Condition> conditions = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(segmentIds)) {
			conditions.add(
				DSL.field(
					"id"
				).in(
					segmentIds
				));
		}

		if (StringUtils.isNotEmpty(filterString)) {
			conditions.add(
				FilterStringToConditionConverter.convert(filterString));
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private List<Condition> _getConditions(
		String filter, String state, String status) {

		List<Condition> conditions = new ArrayList<>();

		if (StringUtils.isNotEmpty(filter)) {
			conditions.add(
				DSL.condition(
					"{0} ~* {1}", DSL.field("filter"), DSL.val(filter)));
		}

		if (StringUtils.isNotEmpty(state)) {
			Field<Object> field = DSL.field("state");

			conditions.add(field.eq(state));
		}

		if (StringUtils.isNotEmpty(status)) {
			Field<Object> field = DSL.field("status");

			conditions.add(field.eq(status));
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

	private List<Condition> _getPreviewDisabledSegmentsConditions(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		String filterString) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.or(
				DSL.field(
					"referencedAssetDataSourceIds"
				).in(
					Collections.singletonList(dataSourceId)
				),
				DSL.field(
					"referencedFieldMappingIds"
				).in(
					dataSourceFieldMappingIds
				)));

		conditions.add(FilterStringToConditionConverter.convert(filterString));

		return conditions;
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("author/name", "authorName");
	}

	private final DSLContext _dslContext;

}