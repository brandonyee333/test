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

import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.DataSourceOrganization;
import com.liferay.osb.asah.common.model.DataSourceSite;
import com.liferay.osb.asah.common.model.DataSourceUserGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class DataSourceRepositoryImpl {

	public DataSourceRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countDataSources(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			"DataSource"
		).where(
			_getConditions(
				channelIds, credentialType, names, providerType, searchNames,
				states, url, workspaceURL)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<DataSource> searchDataSources(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL, Pageable pageable) {

		SelectSelectStep<Record> select = _dslContext.select();

		return _populateDataSources(
			select.from(
				"DataSource"
			).where(
				_getConditions(
					channelIds, credentialType, names, providerType,
					searchNames, states, url, workspaceURL)
			).orderBy(
				_getSortFields(pageable.getSort())
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
			).map(
				record -> new DataSource(record.intoMap())
			));
	}

	private List<Condition> _getConditions(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL) {

		List<Condition> conditions = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(channelIds)) {
			Field<Object> field = DSL.field("channelId");

			conditions.add(field.in(channelIds));
		}

		if (StringUtils.isNotEmpty(credentialType)) {
			Field<Object> field = DSL.field("credentialType");

			conditions.add(field.eq(credentialType));
		}

		if (CollectionUtils.isNotEmpty(names)) {
			Field<Object> field = DSL.field("name");

			conditions.add(field.in(names));
		}

		if (StringUtils.isNotEmpty(providerType)) {
			Field<Object> field = DSL.field("providerType");

			conditions.add(field.eq(providerType));
		}

		if (CollectionUtils.isNotEmpty(searchNames)) {
			for (String searchName : searchNames) {
				Field<Object> field = DSL.field("name");

				conditions.add(field.likeIgnoreCase("%" + searchName + "%"));
			}
		}

		if (CollectionUtils.isNotEmpty(states)) {
			Condition condition = null;

			for (String state : states) {
				Field<Object> field = DSL.field("state");

				if (condition == null) {
					condition = field.eq(state);
				}
				else {
					condition = condition.or(field.eq(state));
				}
			}

			conditions.add(condition);
		}

		if ((url != null) && url) {
			Field<Object> field = DSL.field("url");

			conditions.add(field.eq(""));
		}

		if ((workspaceURL != null) && workspaceURL) {
			Field<Object> field = DSL.field("workspaceURL");

			conditions.add(field.isNull());
		}

		return conditions;
	}

	private Collection<SortField<?>> _getSortFields(Sort sort) {
		Collection<SortField<?>> sortFields = new ArrayList<>();

		if (sort == null) {
			return sortFields;
		}

		for (Sort.Order order : sort.toList()) {
			Field<?> field = DSL.field(order.getProperty());

			if (order.getDirection() == Sort.Direction.ASC) {
				sortFields.add(field.asc());
			}
			else {
				sortFields.add(field.desc());
			}
		}

		return sortFields;
	}

	private void _populateDataSourceOrganizations(
		Map<Long, DataSource> dataSourcesById) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		select.from(
			"DataSourceOrganization"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				Set<DataSourceOrganization> dataSourceOrganizations =
					dataSource.getDataSourceOrganizations();

				dataSourceOrganizations.add(
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

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		select.from(
			"DataSourceSite"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				Set<DataSourceSite> dataSourceSites =
					dataSource.getDataSourceSites();

				dataSourceSites.add(new DataSourceSite(record.intoMap()));
			}
		);
	}

	private void _populateDataSourceUserGroup(
		Map<Long, DataSource> dataSourcesById) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		select.from(
			"DataSourceOrganization"
		).where(
			field.in(dataSourcesById.keySet())
		).fetch(
		).forEach(
			record -> {
				DataSource dataSource = dataSourcesById.get(
					record.get("datasourceid"));

				Set<DataSourceUserGroup> dataSourceUserGroups =
					dataSource.getDataSourceUserGroups();

				dataSourceUserGroups.add(
					new DataSourceUserGroup(record.intoMap()));
			}
		);
	}

	private final DSLContext _dslContext;

}