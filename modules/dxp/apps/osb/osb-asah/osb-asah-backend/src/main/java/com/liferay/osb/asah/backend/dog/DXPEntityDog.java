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
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.backend.model.User;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
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
		Map<String, String> sort, int start) {

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

			JSONObject channelJSONObject = _faroInfoElasticsearchInvoker.fetch(
				"channels", channelId);

			if (channelJSONObject == null) {
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
					_getBoolQueryBuilder(
						boolQueryBuilder, channelJSONObject, "groupId"),
					size, start));

			return _createResultBag(this::_mapDXPEntity, searchHits);
		}
		else if (collectionName.equals("organizations")) {
			SearchHits searchHits = _dataDog.querySearchHits(
				collectionName, _faroInfoElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					_getFieldSortBuilders(collectionName, sort),
					QueryUtil.buildSearchQueryBuilder("name", keywords), size,
					start));

			return _createResultBag(this::_mapOrganization, searchHits);
		}
		else if (collectionName.equals("users")) {
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

			JSONObject channelJSONObject = _faroInfoElasticsearchInvoker.fetch(
				"channels", channelId);

			if (channelJSONObject == null) {
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
					_getBoolQueryBuilder(
						boolQueryBuilder, channelJSONObject,
						"memberships.com.liferay.portal.kernel.model.Group"),
					size, start));

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

		return new ResultBag<>(dxpEntities, searchHits.getTotalHits());
	}

	private BoolQueryBuilder _getBoolQueryBuilder(
		BoolQueryBuilder boolQueryBuilder, JSONObject channelJSONObject,
		String key) {

		JSONArray dataSourcesJSONArray = channelJSONObject.optJSONArray(
			"dataSources");

		if ((dataSourcesJSONArray == null) ||
			(dataSourcesJSONArray.length() == 0)) {

			return boolQueryBuilder;
		}

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject jsonObject = dataSourcesJSONArray.getJSONObject(i);

			boolQueryBuilder.should(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termsQuery(
						key,
						JSONUtil.toStringArray(
							jsonObject.getJSONArray("groupIds")))
				).filter(
					QueryBuilders.termQuery(
						"osbAsahDataSourceId", jsonObject.getString("id"))
				));
		}

		return boolQueryBuilder;
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(
		String collectionName, Map<String, String> sort) {

		if (!collectionName.equals("users") ||
			!StringUtils.equals(sort.get("column"), "name")) {

			return Collections.singletonList(SortBuilderUtil.fieldSort(sort));
		}

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		SortOrder sortOrder = SortOrder.valueOf(sort.get("type"));

		fieldSortBuilders.add(
			SortBuilderUtil.fieldSort("firstName", sortOrder));
		fieldSortBuilders.add(SortBuilderUtil.fieldSort("lastName", sortOrder));

		return fieldSortBuilders;
	}

	@PostConstruct
	private void _init() {
		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private DXPEntity _mapDXPEntity(
		Map<String, String> dataSourceNames, JSONObject dxpEntityJSONObject) {

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				dxpEntityJSONObject.getString("osbAsahDataSourceId"),
				dataSourceId -> {
					JSONObject dataSourceJSONObject =
						_faroInfoElasticsearchInvoker.fetch(
							"data-sources",
							QueryBuilders.termQuery("id", dataSourceId));

					return dataSourceJSONObject.getString("name");
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
					JSONObject dataSourceJSONObject =
						_faroInfoElasticsearchInvoker.fetch(
							"data-sources",
							QueryBuilders.termQuery("id", dataSourceId));

					return dataSourceJSONObject.getString("name");
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
					JSONObject dataSourceJSONObject =
						_faroInfoElasticsearchInvoker.fetch(
							"data-sources",
							QueryBuilders.termQuery("id", dataSourceId));

					return dataSourceJSONObject.getString("name");
				}));
		organization.setId(organizationJSONObject.getString("id"));
		organization.setName(organizationJSONObject.getString("name"));
		organization.setParentName(
			organizationJSONObject.getString("parentName"));
		organization.setType(organizationJSONObject.getString("type"));

		return organization;
	}

	@Autowired
	private DataDog _dataDog;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}