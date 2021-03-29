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

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.util.ListUtil;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class SegmentRepositoryImpl extends BaseRepository {

	public SegmentRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countPreviewDisabledSegments(
		Long dataSourceId, String filterString) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(dataSourceId, filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Segment> searchPreviewDisabledSegments(
		Long dataSourceId, String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Segment"
		).where(
			_getPreviewDisabledSegmentsConditions(dataSourceId, filterString)
		).orderBy(
			getSortFields(pageable.getSort(), null)
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
		DXPEntityType dxpEntityType, Long segmentId, String state,
		Segment.Type type) {

		SelectSelectStep<Record> select = _dslContext.select();

		return select.from(
			"Segment"
		).where(
			_getConditions(dxpEntityType, segmentId, state, type)
		).fetch(
		).map(
			record -> new Segment(record.intoMap())
		);
	}

	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable) {

		SelectSelectStep<Record> select = _dslContext.select();

		return select.from(
			"Segment"
		).where(
			_getConditions(filter, state, status)
		).orderBy(
			getSortFields(pageable.getSort(), null)
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
		DXPEntityType dxpEntityType, Long segmentId, String state,
		Segment.Type type) {

		List<Condition> conditions = new ArrayList<>();

		if (dxpEntityType != null) {
			Field<Object> field = DSL.field(
				dxpEntityType.getIndividualSegmentFieldName());

			conditions.add(field.in(segmentId));
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

	private List<Condition> _getPreviewDisabledSegmentsConditions(
		Long dataSourceId, String filterString) {

		List<Condition> conditions = new ArrayList<>();

		List<Long> dataSourceFieldMappingIds = ListUtil.map(
			_faroInfoFieldMappingDog.getDataSourceFieldMappingIds(
				dataSourceId, true),
			Long::valueOf);

		conditions.add(
			DSL.or(
				DSL.val(
					dataSourceId
				).eq(
					(Field<Long>)DSL.any(
						DSL.field("referencedAssetDataSourceIds"))
				),
				DSL.exists(
					DSL.selectOne(
					).from(
						DSL.unnest(
							DSL.field("referencedFieldMappingIds")
						).as(
							"referencedFieldMappingId"
						)
					).where(
						DSL.field(
							"referencedFieldMappingId"
						).in(
							dataSourceFieldMappingIds
						)
					))));

		conditions.add(FilterStringToConditionConverter.convert(filterString));

		return conditions;
	}

	private final DSLContext _dslContext;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

}