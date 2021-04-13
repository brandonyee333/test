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
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchFieldRepositoryImpl
	extends BaseElasticsearchRepository<Field, Long>
	implements FieldRepository {

	@Override
	public boolean existsByDataSourceId(Long dataSourceId) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			QueryBuilders.termQuery("dataSourceId", dataSourceId));
	}

	@Override
	public List<Field>
		findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			String context, Long dataSourceId, String name, Long ownerId,
			String ownerType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("context", context)
		).filter(
			QueryBuilders.termQuery("ownerType", ownerType)
		);

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId)));
		}

		if (name != null) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("name", name));
		}

		if (ownerId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId)));
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), boolQueryBuilder));
	}

	@Override
	public List<Field>
		findByContextAndDataSourceIdNotAndNameNotInAndOwnerIdAndOwnerType(
			String context, Long dataSourceId, List<String> names, Long ownerId,
			String ownerType) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				"fields",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termQuery(
							"dataSourceId", String.valueOf(dataSourceId)))
				).filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termsQuery("name", names))
				).filter(
					QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<Field> findByContextAndNameAndOwnerIdAndOwnerType(
		String context, String name, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, null, name, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndOwnerIdAndOwnerType(
		String context, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, null, null, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
		String context, Long ownerId) {

		JSONObject jsonObject = null;

		if (context.equals("organization")) {
			jsonObject = _faroInfoElasticsearchInvoker.get(
				"accounts", String.valueOf(ownerId));
		}
		else {
			jsonObject = _faroInfoElasticsearchInvoker.get(
				"individuals", String.valueOf(ownerId));
		}

		JSONObject contextJSONObject = jsonObject.optJSONObject(context);

		JSONArray fieldsJSONArray = new JSONArray();

		if (contextJSONObject != null) {
			for (String key : contextJSONObject.keySet()) {
				JSONArray jsonArray = contextJSONObject.getJSONArray(key);

				fieldsJSONArray.put(jsonArray.getJSONObject(0));
			}
		}

		return toList(fieldsJSONArray);
	}

	@Override
	protected String getCollectionName() {
		return "fields";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}