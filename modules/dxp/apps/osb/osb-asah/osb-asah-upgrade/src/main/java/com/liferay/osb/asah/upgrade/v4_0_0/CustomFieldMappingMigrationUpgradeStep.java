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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.json.JSONArrayIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.util.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class CustomFieldMappingMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		JSONObject osbAsahMarkersJSONObject = _elasticsearchInvoker.fetch(
			"OSBAsahMarkers", "CustomFieldMappingUpgradeStep");

		String lastInsertedId = "0";

		if (osbAsahMarkersJSONObject != null) {
			JSONObject contextJSONObject =
				osbAsahMarkersJSONObject.optJSONObject("context");

			if (contextJSONObject != null) {
				lastInsertedId = contextJSONObject.optString("id", "0");
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Running CustomFieldMappingUpgradeStep starting from %s",
					lastInsertedId));
		}

		JSONArrayIterator.of(
			"field-mappings", _elasticsearchInvoker, null
		).setProcessJSONArrayUnsafeFunction(
			jsonArray -> {
				List<DXPEntity> dxpEntities = new ArrayList<>();

				jsonArray.forEach(
					object -> {
						JSONObject jsonObject = (JSONObject)object;

						JSONObject dataSourceFieldNamesJSONObject =
							jsonObject.optJSONObject("dataSourceFieldNames");

						if (dataSourceFieldNamesJSONObject == null) {
							return;
						}

						Map<String, Object> dataSources = JSONUtil.toMap(
							dataSourceFieldNamesJSONObject);

						for (Map.Entry<String, Object> dataSource :
								dataSources.entrySet()) {

							String name = String.valueOf(dataSource.getValue());

							DXPEntity dxpEntity = new DXPEntity();

							dxpEntity.setDataSourceId(
								Long.parseLong(dataSource.getKey()));

							String className =
								"com.liferay.portal.kernel.model.User";

							if (StringUtils.equalsIgnoreCase(
									"organization",
									jsonObject.getString("ownerType"))) {

								className =
									"com.liferay.portal.kernel.model." +
										"Organization";
							}

							String dataType = "Text";

							if (name.endsWith("-Decimal")) {
								dataType = "Decimal";
							}
							else if (name.endsWith("-Integer")) {
								dataType = "Integer";
							}

							dxpEntity.setFieldsJSONObject(
								JSONUtil.put(
									"className", className
								).put(
									"columnId", name
								).put(
									"dataType", dataType
								).put(
									"displayType",
									jsonObject.getString("displayType")
								).put(
									"name", name
								));

							dxpEntity.setIsNew(Boolean.TRUE);
							dxpEntity.setModifiedDate(DateUtil.newDate());
							dxpEntity.setType(DXPEntity.Type.EXPANDO_COLUMN);

							dxpEntities.add(dxpEntity);
						}
					});

				int batchSize = dxpEntities.size();

				DXPEntity firstDXPEntity = dxpEntities.get(0);
				DXPEntity lastDXPEntity = dxpEntities.get(batchSize - 1);

				try {
					_dxpEntityRepository.saveAll(dxpEntities);
				}
				catch (Exception exception) {
					_log.error(
						String.format(
							"Unable to save DXPEntity, range[%s, %s]",
							firstDXPEntity.getId(), lastDXPEntity.getId()),
						exception);
				}

				_updateAsahMarker(lastDXPEntity.getId());

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					lastInsertedId
				)
			).filter(
				QueryBuilders.termQuery("context", "custom")
			)
		).iterate();
	}

	private void _updateAsahMarker(Long lastInsertedId) {
		JSONObject osbAsahMarkersJSONObject = _elasticsearchInvoker.fetch(
			"OSBAsahMarkers", "CustomFieldMappingUpgradeStep");

		if (osbAsahMarkersJSONObject == null) {
			osbAsahMarkersJSONObject = _elasticsearchInvoker.add(
				"OSBAsahMarkers",
				JSONUtil.put("id", "CustomFieldMappingUpgradeStep"));
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Updating Asah Marker for CustomFieldMappingUpgradeStep, " +
						"lastInsertedId: %s",
					lastInsertedId));
		}

		JSONObject contextJSONObject = osbAsahMarkersJSONObject.optJSONObject(
			"context", new JSONObject());

		contextJSONObject.put("id", lastInsertedId);

		osbAsahMarkersJSONObject.put("context", contextJSONObject);

		_elasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkersJSONObject);
	}

	private static final Log _log = LogFactory.getLog(
		CustomFieldMappingMigrationUpgradeStep.class);

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}