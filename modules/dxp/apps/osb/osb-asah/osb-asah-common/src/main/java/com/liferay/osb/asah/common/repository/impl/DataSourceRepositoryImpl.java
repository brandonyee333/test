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
import com.liferay.osb.asah.common.util.BeanUtils;

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

	public Long countDataSources(
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
		).fetchOne(
			0, Long.class
		);
	}

	public List<DataSource> searchDataSources(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL, Pageable pageable) {

		SelectSelectStep<Record> select = _dslContext.select();

		return _getDataSources(
			Stream.of(
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
				).fetchMaps()
			).flatMap(
				List<Map<String, Object>>::stream
			).map(
				map -> {
					DataSource dataSource = new DataSource();

					BeanUtils.copyProperties(map, dataSource);

					return dataSource;
				}
			).collect(
				Collectors.toMap(DataSource::getId, Function.identity())
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

	private List<DataSource> _getDataSources(
		Map<Long, DataSource> dataSources) {

		if (dataSources.isEmpty()) {
			return Collections.emptyList();
		}

		_populateDataSourceOrganizations(dataSources);
		_populateDataSourceSite(dataSources);
		_populateDataSourceUserGroup(dataSources);

		return new ArrayList<>(dataSources.values());
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
		Map<Long, DataSource> dataSources) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		Stream.of(
			select.from(
				"DataSourceOrganization"
			).where(
				field.in(dataSources.keySet())
			).fetchMaps()
		).flatMap(
			List<Map<String, Object>>::stream
		).forEach(
			map -> {
				DataSource dataSource = dataSources.get(
					map.get("datasourceid"));

				Set<DataSourceOrganization> dataSourceOrganizations =
					dataSource.getDataSourceOrganizations();

				DataSourceOrganization dataSourceOrganization =
					new DataSourceOrganization();

				BeanUtils.copyProperties(map, dataSourceOrganization);

				dataSourceOrganizations.add(dataSourceOrganization);
			}
		);
	}

	private void _populateDataSourceSite(Map<Long, DataSource> dataSources) {
		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		Stream.of(
			select.from(
				"DataSourceSite"
			).where(
				field.in(dataSources.keySet())
			).fetchMaps()
		).flatMap(
			List<Map<String, Object>>::stream
		).forEach(
			map -> {
				DataSource dataSource = dataSources.get(
					map.get("datasourceid"));

				Set<DataSourceSite> dataSourceSites =
					dataSource.getDataSourceSites();

				DataSourceSite dataSourceSite = new DataSourceSite();

				BeanUtils.copyProperties(map, dataSourceSite);

				dataSourceSites.add(dataSourceSite);
			}
		);
	}

	private void _populateDataSourceUserGroup(
		Map<Long, DataSource> dataSources) {

		SelectSelectStep<Record> select = _dslContext.select();

		Field<Object> field = DSL.field("dataSourceId");

		Stream.of(
			select.from(
				"DataSourceOrganization"
			).where(
				field.in(dataSources.keySet())
			).fetchMaps()
		).flatMap(
			List<Map<String, Object>>::stream
		).forEach(
			map -> {
				DataSource dataSource = dataSources.get(
					map.get("datasourceid"));

				Set<DataSourceUserGroup> dataSourceUserGroups =
					dataSource.getDataSourceUserGroups();

				DataSourceUserGroup dataSourceUserGroup =
					new DataSourceUserGroup();

				BeanUtils.copyProperties(map, dataSourceUserGroup);

				dataSourceUserGroups.add(dataSourceUserGroup);
			}
		);
	}

	private final DSLContext _dslContext;

}