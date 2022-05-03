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
import com.liferay.osb.asah.common.repository.CustomFieldMappingRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;
import org.jooq.tools.StringUtils;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class FieldMappingRepositoryImpl
	extends BaseRepository implements CustomFieldMappingRepository {

	public FieldMappingRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countFieldMappings(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"FieldMapping"
		).where(
			filterHelper.getCondition()
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
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

	@Override
	public List<FieldMapping>
		findByContextAndDataSourceIdAndFieldNameAndOwnerType(
			String context, Long dataSourceId, String fieldName,
			String ownerType) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"FieldMapping"
			).asterisk());

		return _populateFieldMappings(
			selectSelectStep.from(
				"FieldMapping"
			).join(
				"DataSourceFieldMapping"
			).on(
				DSL.field(
					"fieldmapping.id"
				).eq(
					DSL.field("datasourcefieldmapping.fieldmappingid")
				)
			).where(
				DSL.and(
					DSL.field(
						"datasourcefieldmapping.datasourceid"
					).eq(
						dataSourceId
					),
					DSL.field(
						"fieldmapping.context"
					).eq(
						context
					),
					DSL.field(
						"fieldmapping.fieldName"
					).eq(
						fieldName
					),
					DSL.field(
						"fieldmapping.ownertype"
					).eq(
						ownerType
					))
			).fetch(
				record -> new FieldMapping(record.intoMap())
			));
	}

	@Override
	public List<FieldMapping> findByContextAndDataSourceIdAndOwnerType(
		String context, @Nullable Long dataSourceId, String ownerType) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"FieldMapping"
			).asterisk());

		Condition condition = DSL.and(
			DSL.field(
				"fieldmapping.context"
			).eq(
				context
			),
			DSL.field(
				"fieldmapping.ownertype"
			).eq(
				ownerType
			));

		if (dataSourceId != null) {
			condition = condition.and(
				DSL.field(
					"datasourcefieldmapping.datasourceid"
				).eq(
					dataSourceId
				));
		}

		return _populateFieldMappings(
			selectSelectStep.from(
				"FieldMapping"
			).join(
				"DataSourceFieldMapping"
			).on(
				DSL.field(
					"fieldmapping.id"
				).eq(
					DSL.field("datasourcefieldmapping.fieldmappingid")
				)
			).where(
				condition
			).orderBy(
				DSL.field(
					"fieldmapping.displayName"
				).asc(),
				DSL.field(
					"fieldmapping.modifiedDate"
				).desc()
			).fetch(
				record -> new FieldMapping(record.intoMap())
			));
	}

	@Override
	public List<FieldMapping>
		findByDataSourceFieldNameAndDataSourceIdAndOwnerType(
			String dataSourceFieldName, Long dataSourceId, String ownerType) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"FieldMapping"
			).asterisk());

		return _populateFieldMappings(
			selectSelectStep.from(
				"FieldMapping"
			).join(
				"DataSourceFieldMapping"
			).on(
				DSL.field(
					"fieldmapping.id"
				).eq(
					DSL.field("datasourcefieldmapping.fieldmappingid")
				)
			).where(
				DSL.and(
					DSL.field(
						"datasourcefieldmapping.datasourceid"
					).eq(
						dataSourceId
					),
					DSL.field(
						"datasourcefieldmapping.fieldname"
					).eq(
						dataSourceFieldName
					),
					DSL.field(
						"fieldmapping.ownertype"
					).eq(
						ownerType
					))
			).fetch(
				record -> new FieldMapping(record.intoMap())
			));
	}

	@Override
	public List<FieldMapping> findByDataSourceId(Long dataSourceId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"FieldMapping"
			).asterisk());

		return _populateFieldMappings(
			selectSelectStep.from(
				"FieldMapping"
			).join(
				"DataSourceFieldMapping"
			).on(
				DSL.field(
					"fieldmapping.id"
				).eq(
					DSL.field("datasourcefieldmapping.fieldmappingid")
				)
			).where(
				DSL.field(
					"datasourcefieldmapping.datasourceid"
				).eq(
					dataSourceId
				)
			).fetch(
				record -> new FieldMapping(record.intoMap())
			));
	}

	@Override
	public List<Transformation> getFieldMappingTransformations(
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
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	@Override
	public List<FieldMapping> searchFieldMappings(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			DSL.table(
				"FieldMapping"
			).asterisk());

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			"FieldMapping");

		String filterString = filterHelper.getFilterString();

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

		return _populateFieldMappings(
			selectJoinStep.where(
				filterHelper.getCondition()
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
				record -> new FieldMapping(record.intoMap())
			));
	}

	@Override
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

		return _populateFieldMappings(
			selectSelectStep.from(
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
				record -> new FieldMapping(record.intoMap())
			));
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

	private void _populateDataSourceFieldMappings(
		Map<Long, FieldMapping> individualsById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		selectSelectStep.from(
			"DataSourceFieldMapping"
		).where(
			field.in(individualsById.keySet())
		).fetch(
		).forEach(
			record -> {
				FieldMapping fieldMapping = individualsById.get(
					record.get("datasourceid"));

				DataSourceFieldMapping dataSourceFieldMapping =
					new DataSourceFieldMapping(record.intoMap());

				fieldMapping.addDataSourceFieldMapping(dataSourceFieldMapping);
				fieldMapping.addDataSourceFieldName(
					String.valueOf(dataSourceFieldMapping.getDataSourceId()),
					dataSourceFieldMapping.getFieldName());
			}
		);
	}

	private List<FieldMapping> _populateFieldMappings(
		List<FieldMapping> fieldMappings) {

		if (fieldMappings.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<FieldMapping> stream = fieldMappings.stream();

		Map<Long, FieldMapping> fieldMappingsById = stream.collect(
			Collectors.toMap(
				FieldMapping::getId, Function.identity(),
				(id, fieldMapping) -> id, LinkedHashMap::new));

		_populateDataSourceFieldMappings(fieldMappingsById);

		return new ArrayList<>(fieldMappingsById.values());
	}

	private final DSLContext _dslContext;

}