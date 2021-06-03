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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchOrganizationRepositoryImpl
	extends BaseElasticsearchRepository<Organization, Long>
	implements OrganizationRepository {

	@Override
	public Organization findByDataSourceIdAndOrganizationPK(
		Long dataSourceId, Long organizationPK) {

		return toEntity(
			_faroInfoElasticsearchInvoker.fetch(
				"organizations",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery("organizationPK", organizationPK)
				)));
	}

	@Override
	public List<Transformation> getOrganizationTransformations(
		String apply, @Nullable String filterString, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if (queryBuilder != null) {
			transformationGetResponse.setQueryBuilder(queryBuilder);
		}

		transformationGetResponse.setSize(pageable.getPageSize());
		transformationGetResponse.setSorts(
			new HashMap<String, String>() {
				{
					put("terms", "_key");
					put("totalElements", "_count");
				}
			},
			_getSorts(pageable.getSort()));
		transformationGetResponse.setTransformationJSONArrayFunction(
			new TermsAggregationTransformationJSONArrayFunction(apply, null));
		transformationGetResponse.setTransformationName(
			"organization-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception e) {
			_log.error(e, e);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray organizationTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("organization-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			organizationTransformationsJSONArray);

		return stream.map(
			object -> {
				JSONObject curJSONObject = (JSONObject)object;

				return new Transformation(
					JSONUtil.toMap(curJSONObject.getJSONObject("terms")),
					curJSONObject.getInt("totalElements"));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public <S extends Organization> S save(S organization) {
		JSONObject jsonObject = toJSONObject(organization);

		Set<Field> customFields = organization.getCustomFields();

		if (CollectionUtils.isNotEmpty(customFields)) {
			JSONObject customJSONObject = new JSONObject();

			for (Field customField : customFields) {
				JSONObject customFieldJSONObject = objectMapper.convertValue(
					customField, JSONObject.class);

				customFieldJSONObject.remove("id");

				customFieldJSONObject.put(
					"ownerId", jsonObject.getString("id"));

				customJSONObject.put(
					customField.getName(), JSONUtil.put(customFieldJSONObject));
			}

			jsonObject.put("custom", customJSONObject);
		}

		if ((organization.getId() != null) &&
			_faroInfoElasticsearchInvoker.exists(
				getCollectionName(), String.valueOf(organization.getId()))) {

			jsonObject = _faroInfoElasticsearchInvoker.update(
				getCollectionName(), jsonObject);
		}
		else {
			jsonObject = _faroInfoElasticsearchInvoker.add(
				getCollectionName(), jsonObject);
		}

		return (S)toEntity(jsonObject);
	}

	@Override
	protected String getCollectionName() {
		return "organizations";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private String[] _getSorts(Sort sort) {
		if (sort == null) {
			return null;
		}

		List<String> sorts = new LinkedList<>();

		for (Sort.Order order : sort) {
			sorts.add(order.getProperty());

			if (order.isAscending()) {
				sorts.add("asc");
			}
			else {
				sorts.add("desc");
			}
		}

		return sorts.toArray(new String[0]);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchOrganizationRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}