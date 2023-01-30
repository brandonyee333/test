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
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.CustomSegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Select;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Inácio Nery
 */
public class SegmentRepositoryImpl
	extends BaseRepository implements CustomSegmentRepository {

	public SegmentRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(
				dataSourceFieldMappingIds, dataSourceId, filterHelper)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countSegments(
		FilterHelper filterHelper,
		@Nullable List<Map<String, Long>> segmentIdIdentityCounts) {

		SelectSelectStep<Record1<Integer>> selectSelectStep1 =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep;

		if (CollectionUtils.isEmpty(segmentIdIdentityCounts)) {
			selectJoinStep = selectSelectStep1.from(DSL.table("Segment"));
		}
		else {
			Select<Record2<Long, Long>> selectSelectStep = null;

			for (int i = 0; i < segmentIdIdentityCounts.size(); i++) {
				Map<String, Long> segmentIdIdentityCount =
					segmentIdIdentityCounts.get(i);

				if (i == 0) {
					selectSelectStep = DSL.select(
						DSL.inline(segmentIdIdentityCount.get("segmentId")),
						DSL.inline(
							segmentIdIdentityCount.get("identitiesCount")));
				}
				else {
					selectSelectStep = selectSelectStep.unionAll(
						DSL.select(
							DSL.inline(segmentIdIdentityCount.get("segmentId")),
							DSL.inline(
								segmentIdIdentityCount.get(
									"identitiesCount"))));
				}
			}

			selectJoinStep = selectSelectStep1.from(
				DSL.with(
					"membership", "segmentId", "identitiesCount"
				).as(
					selectSelectStep
				).select(
					DSL.field(
						"membership.segmentId"
					).as(
						"segmentId"
					),
					DSL.field(
						"membership.identitiesCount"
					).as(
						"identitiesCount"
					)
				).from(
					"membership"
				).asTable(
					"membership"
				).join(
					"Segment"
				).on(
					DSL.field(
						"Segment.id"
					).eq(
						DSL.field("membership.segmentId")
					)
				));
		}

		return selectJoinStep.where(
			_getConditions(
				filterHelper, _getSegmentIds(segmentIdIdentityCounts))
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countSegments(
		List<Long> channelIds, FilterHelper filterHelper) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(channelIds, filterHelper)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<Transformation> getSegmentTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable,
		@Nullable List<Long> segmentIds) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");
		Field groupByField = DSL.field(matcher.group("groupByField"));

		Condition condition = filterHelper.getCondition();

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
			groupByField.as("terms"),
			DSL.count(
				DSL.field("id")
			).as(
				"totalelements"
			)
		).from(
			"Segment"
		).where(
			condition
		).groupBy(
			groupByField
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField.getName(), record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	@Override
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, @Nullable Boolean includeAnonymousUsers,
		Pageable pageable, Set<Long> segmentIds) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"type"
			).eq(
				Segment.Type.DYNAMIC.toString()
			));

		if (StringUtils.isNotEmpty(filterHelper.getFilterString())) {
			conditions.add(filterHelper.getCondition());
		}

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

		if (includeAnonymousUsers != null) {
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
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			DSL.and(
				filterHelper.getCondition(),
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
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper, Pageable pageable) {

		if (CollectionUtils.isEmpty(dataSourceFieldMappingIds)) {
			return new ArrayList<>();
		}

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(
				dataSourceFieldMappingIds, dataSourceId, filterHelper)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchSegments(
		FilterHelper filterHelper,
		@Nullable List<Map<String, Long>> segmentIdIdentityCounts,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep1 = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep;

		Map<String, String> sortFieldNameConversionMap =
			_getSortFieldNameConversionMap();

		if (CollectionUtils.isEmpty(segmentIdIdentityCounts)) {
			selectJoinStep = selectSelectStep1.from(DSL.table("Segment"));

			sortFieldNameConversionMap.put("individualCount", "id");
		}
		else {
			Select<Record2<Long, Long>> selectSelectStep = null;

			for (int i = 0; i < segmentIdIdentityCounts.size(); i++) {
				Map<String, Long> segmentIdIdentityCount =
					segmentIdIdentityCounts.get(i);

				if (i == 0) {
					selectSelectStep = DSL.select(
						DSL.inline(segmentIdIdentityCount.get("segmentId")),
						DSL.inline(
							segmentIdIdentityCount.get("identitiesCount")));
				}
				else {
					selectSelectStep = selectSelectStep.unionAll(
						DSL.select(
							DSL.inline(segmentIdIdentityCount.get("segmentId")),
							DSL.inline(
								segmentIdIdentityCount.get(
									"identitiesCount"))));
				}
			}

			selectJoinStep = selectSelectStep1.from(
				DSL.with(
					"membership", "segmentId", "identitiesCount"
				).as(
					selectSelectStep
				).select(
					DSL.field(
						"membership.segmentId"
					).as(
						"segmentId"
					),
					DSL.field(
						"membership.identitiesCount"
					).as(
						"identitiesCount"
					)
				).from(
					"membership"
				).asTable(
					"membership"
				).join(
					"Segment"
				).on(
					DSL.field(
						"Segment.id"
					).eq(
						DSL.field("membership.segmentId")
					)
				));
		}

		return selectJoinStep.where(
			_getConditions(
				filterHelper, _getSegmentIds(segmentIdIdentityCounts))
		).orderBy(
			getSortFields(sortFieldNameConversionMap, pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchSegments(
		List<Long> channelIds, FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(channelIds, filterHelper)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchSegments(
		Long dxpEntityId, DXPEntity.Type dxpEntityType, String state,
		Segment.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(dxpEntityId, dxpEntityType, state, type)
		).fetch(
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	public List<Segment> searchSegments(
		String filterString, String state, String status, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getConditions(filterString, state, status)
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Segment(record.intoMap())
		);
	}

	@Override
	protected String getSortFieldName(String fieldName) {
		if (fieldName.equalsIgnoreCase("individualCount")) {
			return "identitiesCount";
		}

		return super.getSortFieldName(fieldName);
	}

	private List<Condition> _getConditions(
		FilterHelper filterHelper, List<Long> segmentIds) {

		List<Condition> conditions = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(segmentIds)) {
			conditions.add(
				DSL.field(
					"id"
				).in(
					segmentIds
				));
		}

		if (StringUtils.isNotEmpty(filterHelper.getFilterString())) {
			conditions.add(filterHelper.getCondition());
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private List<Condition> _getConditions(
		List<Long> channelIds, FilterHelper filterHelper) {

		List<Condition> conditions = new ArrayList<>();

		if (!channelIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"channelId"
				).in(
					channelIds
				));
		}

		conditions.add(filterHelper.getCondition());

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
		String filterString, String state, String status) {

		List<Condition> conditions = new ArrayList<>();

		if (StringUtils.isNotEmpty(filterString)) {
			conditions.add(
				DSL.condition(
					"{0} ~* {1}", DSL.field("filter"), DSL.val(filterString)));
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

	private Condition _getIncludeCondition(String containsField, Field field) {
		if (containsField == null) {
			return DSL.noCondition();
		}

		return field.containsIgnoreCase(containsField);
	}

	private List<Condition> _getPreviewDisabledSegmentsConditions(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.or(
				DSL.exists(
					DSL.selectOne(
					).from(
						"UNNEST(referencedAssetDataSourceIds) AS " +
							"referencedAssetDataSourceId"
					).where(
						DSL.field(
							"referencedAssetDataSourceId"
						).eq(
							dataSourceId
						)
					)),
				DSL.exists(
					DSL.selectOne(
					).from(
						"UNNEST(referencedFieldMappingIds) AS " +
							"referencedFieldMappingId"
					).where(
						DSL.field(
							"referencedFieldMappingId"
						).in(
							dataSourceFieldMappingIds
						)
					))));

		conditions.add(filterHelper.getCondition());

		return conditions;
	}

	private List<Long> _getSegmentIds(
		List<Map<String, Long>> segmentIdIdentityCounts) {

		List<Long> segmentIds = Collections.emptyList();

		if (CollectionUtils.isNotEmpty(segmentIdIdentityCounts)) {
			Stream<Map<String, Long>> stream = segmentIdIdentityCounts.stream();

			segmentIds = stream.map(
				map -> map.get("segmentId")
			).collect(
				Collectors.toList()
			);
		}

		return segmentIds;
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return new HashMap<String, String>() {
			{
				put("author/name", "authorName");
			}
		};
	}

	private final DSLContext _dslContext;

}