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

import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.FieldMappingFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.DataSourceFieldMappingRepository;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;
import org.jooq.tools.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class FieldMappingRepositoryImpl extends BaseRepository {

	public FieldMappingRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countFieldMappings(@Nullable String filterString) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"FieldMapping"
		).where(
			ConditionUtil.toCondition(filterString)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countIndividualFieldMappings(@Nullable String name) {
		Condition condition = DSL.field(
			"ownerType"
		).eq(
			"individual"
		);

		if (!StringUtils.isBlank(name)) {
			condition = DSL.and(
				condition,
				DSL.field(
					"fieldName"
				).likeIgnoreCase(
					"%" + name + "%"
				));
		}

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.select(
			DSL.countDistinct(DSL.field("id"))
		).from(
			"FieldMapping"
		).innerJoin(
			DSL.table(
				"DataSourceFieldMapping"
			).as(
				"datasourcefieldmapping"
			)
		).on(
			DSL.field(
				"id"
			).eq(
				DSL.field("datasourcefieldmapping.fieldmappingid")
			)
		).where(
			condition
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Transformation> getFieldMappingTransformations(
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
			"Field"
		).where(
			condition
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
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

	public List<FieldMapping> searchFieldMappings(
		@Nullable String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			"FieldMapping");

		if (!StringUtils.isEmpty(filterString) &&
			filterString.contains("dataSourceFieldNames")) {

			selectJoinStep = selectJoinStep.join(
				DSL.select(
					DSL.field("dataSourceId"), DSL.field("fieldMappingId"),
					DSL.field(
						"fieldName"
					).as(
						"datasourcefieldmapping.fieldname"
					)
				).from(
					"DataSourceFieldMapping"
				).asTable(
					"datasourcefieldmapping"
				)
			).on(
				DSL.field(
					"id"
				).eq(
					DSL.field("datasourcefieldmapping.fieldmappingid")
				)
			);
		}

		return selectJoinStep.where(
			ConditionUtil.toCondition(
				filterString, _fieldMappingFilterStringConverterHelper)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> {
				FieldMapping fieldMapping = new FieldMapping(record.intoMap());

				fieldMapping.setDataSourceFieldMappings(
					_getDataSourceFieldMappings(fieldMapping.getId()));
				fieldMapping.setDataSourceFieldNames(
					_getDataSourceFieldNames(fieldMapping.getId()));

				return fieldMapping;
			}
		);
	}

	public List<FieldMapping> searchIndividualFieldMappings(
		@Nullable String name, Pageable pageable) {

		Condition condition = DSL.field(
			"ownerType"
		).eq(
			"individual"
		);

		if (!StringUtils.isBlank(name)) {
			condition = DSL.and(
				condition,
				DSL.field(
					"fieldName"
				).likeIgnoreCase(
					"%" + name + "%"
				));
		}

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"FieldMapping"
		).innerJoin(
			DSL.select(
				DSL.field("dataSourceId"), DSL.field("fieldMappingId"),
				DSL.field(
					"fieldName"
				).as(
					"datasourcefieldmapping.fieldname"
				)
			).from(
				"DataSourceFieldMapping"
			).asTable(
				"datasourcefieldmapping"
			)
		).on(
			DSL.field(
				"id"
			).eq(
				DSL.field("datasourcefieldmapping.fieldmappingid")
			)
		).where(
			condition
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> {
				FieldMapping fieldMapping = new FieldMapping(record.intoMap());

				fieldMapping.setDataSourceFieldMappings(
					_getDataSourceFieldMappings(fieldMapping.getId()));
				fieldMapping.setDataSourceFieldNames(
					_getDataSourceFieldNames(fieldMapping.getId()));

				return fieldMapping;
			}
		);
	}

	private Set<DataSourceFieldMapping> _getDataSourceFieldMappings(
		Long fieldMappingId) {

		List<DataSourceFieldMapping> dataSourceFieldMappings =
			_dataSourceFieldMappingRepository.findByFieldMappingId(
				fieldMappingId);

		return new HashSet<>(dataSourceFieldMappings);
	}

	private Map<String, String> _getDataSourceFieldNames(Long fieldMappingId) {
		List<DataSourceFieldMapping> dataSourceFieldMappings =
			_dataSourceFieldMappingRepository.findByFieldMappingId(
				fieldMappingId);

		Stream<DataSourceFieldMapping> stream =
			dataSourceFieldMappings.stream();

		return stream.collect(
			Collectors.toMap(
				dataSourceFieldMapping -> String.valueOf(
					dataSourceFieldMapping.getDataSourceId()),
				DataSourceFieldMapping::getFieldName));
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

	@Autowired
	private DataSourceFieldMappingRepository _dataSourceFieldMappingRepository;

	private final DSLContext _dslContext;

	@Autowired
	private FieldMappingFilterStringConverterHelper
		_fieldMappingFilterStringConverterHelper;

}