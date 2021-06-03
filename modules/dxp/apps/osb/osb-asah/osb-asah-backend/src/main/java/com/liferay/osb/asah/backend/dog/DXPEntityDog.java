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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.DXPEntity;
import com.liferay.osb.asah.backend.model.Organization;
import com.liferay.osb.asah.backend.model.User;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DXPEntityDog {

	public ResultBag<? extends DXPEntity> getDXPEntityResultBag(
		String channelId, String collectionName, String keywords, int size,
		Sort sort, int start) {

		if (collectionName.equals("groups") || collectionName.equals("teams")) {
			SearchHits searchHits = _dataDog.querySearchHits(
				collectionName, _dxpRawElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					QueryUtil.buildSearchQueryBuilder("name", keywords), size,
					start));

			if (StringUtils.isEmpty(channelId)) {
				return _createResultBag(this::_mapDXPEntity, searchHits);
			}

			Channel channel = _channelDog.fetchChannel(Long.valueOf(channelId));

			if (channel == null) {
				return _createResultBag(this::_mapDXPEntity, searchHits);
			}

			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			if (StringUtils.isNotBlank(keywords)) {
				boolQueryBuilder = BoolQueryBuilderUtil.filter(
					QueryUtil.buildSearchQueryBuilder("name", keywords));
			}

			searchHits = _dataDog.querySearchHits(
				collectionName, _dxpRawElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					_getBoolQueryBuilder(boolQueryBuilder, channel), size,
					start));

			return _createResultBag(this::_mapDXPEntity, searchHits);
		}

		if (collectionName.equals("organizations")) {
			SearchHits searchHits = _dataDog.querySearchHits(
				collectionName, _faroInfoElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					QueryUtil.buildSearchQueryBuilder("name", keywords), size,
					start));

			return _createResultBag(this::_mapOrganization, searchHits);
		}

		if (collectionName.equals("users")) {
			SearchHits searchHits = _dataDog.querySearchHits(
				collectionName, _dxpRawElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					BoolQueryBuilderUtil.should(
						QueryUtil.buildSearchQueryBuilder("firstName", keywords)
					).should(
						QueryUtil.buildSearchQueryBuilder("lastName", keywords)
					),
					size, start));

			if (StringUtils.isEmpty(channelId)) {
				return _createResultBag(this::_mapDXPUser, searchHits);
			}

			Channel channel = _channelDog.fetchChannel(Long.valueOf(channelId));

			if (channel == null) {
				return _createResultBag(this::_mapDXPUser, searchHits);
			}

			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			if (StringUtils.isNotBlank(keywords)) {
				boolQueryBuilder = BoolQueryBuilderUtil.should(
					QueryUtil.buildSearchQueryBuilder("firstName", keywords)
				).should(
					QueryUtil.buildSearchQueryBuilder("lastName", keywords)
				);
			}

			searchHits = _dataDog.querySearchHits(
				collectionName, _dxpRawElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					_getBoolQueryBuilder(boolQueryBuilder, channel), size,
					start));

			return _createResultBag(this::_mapDXPUser, searchHits);
		}

		SearchHits searchHits = _dataDog.querySearchHits(
			collectionName, _dxpRawElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				_getFieldSortBuilders(collectionName, sort),
				QueryUtil.buildSearchQueryBuilder("name", keywords), size,
				start));

		return _createResultBag(this::_mapDXPEntity, searchHits);
	}

	private ResultBag<? extends DXPEntity> _createResultBag(
		BiFunction<Map<String, String>, JSONObject, ? extends DXPEntity>
			searchHitModelMapperFunction,
		SearchHits searchHits) {

		Map<String, String> dataSourceNames = new HashMap<>();
		List<DXPEntity> dxpEntities = new ArrayList<>();

		for (SearchHit searchHit : searchHits) {
			searchHitModelMapperFunction.apply(
				dataSourceNames, new JSONObject(searchHit.getSourceAsMap()));

			dxpEntities.add(
				searchHitModelMapperFunction.apply(
					dataSourceNames,
					new JSONObject(searchHit.getSourceAsMap())));
		}

		return new ResultBag<>(
			dxpEntities, HitsUtil.getTotalHitsCount(searchHits));
	}

	private BoolQueryBuilder _getBoolQueryBuilder(
		BoolQueryBuilder boolQueryBuilder, Channel channel) {

		Set<ChannelDataSource> channelDataSources =
			channel.getChannelDataSources();

		if (channelDataSources.isEmpty()) {
			return boolQueryBuilder;
		}

		BoolQueryBuilder dataSourceBoolQueryBuilder = QueryBuilders.boolQuery();

		for (ChannelDataSource channelDataSource : channelDataSources) {
			dataSourceBoolQueryBuilder.should(
				QueryBuilders.termQuery(
					"osbAsahDataSourceId",
					String.valueOf(channelDataSource.getDataSourceId())));
		}

		return BoolQueryBuilderUtil.filter(
			boolQueryBuilder
		).filter(
			dataSourceBoolQueryBuilder
		);
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(
		String collectionName, Sort sort) {

		if (!collectionName.equals("users") ||
			!StringUtils.equals(sort.getColumn(), "name")) {

			return Collections.singletonList(SortBuilderUtil.fieldSort(sort));
		}

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		SortOrder sortOrder = SortOrder.valueOf(sort.getType());

		fieldSortBuilders.add(
			SortBuilderUtil.fieldSort("firstName", sortOrder));
		fieldSortBuilders.add(SortBuilderUtil.fieldSort("lastName", sortOrder));

		return fieldSortBuilders;
	}

	private DXPEntity _mapDXPEntity(
		Map<String, String> dataSourceNames, JSONObject dxpEntityJSONObject) {

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				dxpEntityJSONObject.getString("osbAsahDataSourceId"),
				dataSourceId -> {
					DataSource dataSource = _dataSourceDog.fetchDataSource(
						Long.valueOf(dataSourceId));

					if (dataSource != null) {
						return dataSource.getName();
					}

					return null;
				}));
		dxpEntity.setId(dxpEntityJSONObject.getString("id"));
		dxpEntity.setName(dxpEntityJSONObject.getString("name"));

		return dxpEntity;
	}

	private User _mapDXPUser(
		Map<String, String> dataSourceNames, JSONObject userJSONObject) {

		User user = new User();

		user.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				userJSONObject.getString("osbAsahDataSourceId"),
				dataSourceId -> {
					DataSource dataSource = _dataSourceDog.fetchDataSource(
						Long.valueOf(dataSourceId));

					if (dataSource != null) {
						return dataSource.getName();
					}

					return null;
				}));
		user.setFirstName(userJSONObject.getString("firstName"));
		user.setId(userJSONObject.getString("id"));
		user.setLastName(userJSONObject.getString("lastName"));
		user.setScreenName(userJSONObject.getString("screenName"));

		return user;
	}

	private Organization _mapOrganization(
		Map<String, String> dataSourceNames,
		JSONObject organizationJSONObject) {

		Organization organization = new Organization();

		organization.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				organizationJSONObject.getString("dataSourceId"),
				dataSourceId -> {
					DataSource dataSource = _dataSourceDog.fetchDataSource(
						Long.valueOf(dataSourceId));

					if (dataSource != null) {
						return dataSource.getName();
					}

					return null;
				}));
		organization.setId(organizationJSONObject.getString("id"));
		organization.setName(organizationJSONObject.getString("name"));
		organization.setParentName(
			organizationJSONObject.getString("parentName"));
		organization.setType(organizationJSONObject.getString("type"));

		return organization;
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}