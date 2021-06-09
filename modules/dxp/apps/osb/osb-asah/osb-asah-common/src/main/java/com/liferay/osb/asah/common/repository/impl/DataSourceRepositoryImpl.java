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

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceOrganization;
import com.liferay.osb.asah.common.entity.DataSourceSite;
import com.liferay.osb.asah.common.entity.DataSourceUserGroup;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.postgresql.converter.helper.DataSourceFilterStringConverterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Inácio Nery
 */
public class DataSourceRepositoryImpl extends BaseRepository {

	public DataSourceRepositoryImpl(
		DataSourceFilterStringConverterHelper
			dataSourceFilterStringConverterHelper,
		DSLContext dslContext) {

		_dataSourceFilterStringConverterHelper =
			dataSourceFilterStringConverterHelper;
		_dslContext = dslContext;
	}

	public long countDataSources(String filterString) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"DataSource"
		).where(
			FilterStringToConditionConverter.convert(
				filterString, _dataSourceFilterStringConverterHelper)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<DataSource> searchDataSources(
		String filterString, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _populateDataSources(
			selectSelectStep.from(
				"DataSource"
			).where(
				FilterStringToConditionConverter.convert(
					filterString, _dataSourceFilterStringConverterHelper)
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
			).map(
				record -> new DataSource(record.intoMap())
			));
	}

	private void _populateDataSourceOrganizations(
		Map<Long, DataSource> dataSourcesById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		selectSelectStep.from(
			"DataSourceOrganization"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				dataSource.addDataSourceOrganization(
					new DataSourceOrganization(record.intoMap()));
			}
		);
	}

	private List<DataSource> _populateDataSources(
		List<DataSource> dataSources) {

		if (dataSources.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<DataSource> stream = dataSources.stream();

		Map<Long, DataSource> dataSourcesById = stream.collect(
			Collectors.toMap(DataSource::getId, Function.identity()));

		_populateDataSourceOrganizations(dataSourcesById);
		_populateDataSourceSite(dataSourcesById);
		_populateDataSourceUserGroup(dataSourcesById);

		return new ArrayList<>(dataSourcesById.values());
	}

	private void _populateDataSourceSite(
		Map<Long, DataSource> dataSourcesById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		selectSelectStep.from(
			"DataSourceSite"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				dataSource.addDataSourceSite(
					new DataSourceSite(record.intoMap()));
			}
		);
	}

	private void _populateDataSourceUserGroup(
		Map<Long, DataSource> dataSourcesById) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		selectSelectStep.from(
			"DataSourceOrganization"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				dataSource.addDataSourceUserGroup(
					new DataSourceUserGroup(record.intoMap()));
			}
		);
	}

	private final DataSourceFilterStringConverterHelper
		_dataSourceFilterStringConverterHelper;
	private final DSLContext _dslContext;

}