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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DXPEntityMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeCollection(DXPEntity.Type.GROUP);
		_upgradeCollection(DXPEntity.Type.ORGANIZATION);
		_upgradeCollection(DXPEntity.Type.ROLE);
		_upgradeCollection(DXPEntity.Type.TEAM);
		_upgradeCollection(DXPEntity.Type.USER);
		_upgradeCollection(DXPEntity.Type.USER_GROUP);
		_upgradeExpandoColumns();
	}

	private List<Long> _getDXPEntityIds(boolean retry, DXPEntity.Type type) {
		try {
			return _namedParameterJdbcTemplate.queryForList(
				"SELECT id FROM dxpentity WHERE type = :type",
				Collections.singletonMap("type", type.toString()), Long.class);
		}
		catch (Exception exception) {
			_log.error("Select DXP entity IDs query failed", exception);

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getDXPEntityIds(false, type);
			}

			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _upgradeCollection(DXPEntity.Type type) throws Exception {
		JSONArrayIterator.of(
			type.getCollectionName(), _dxpRawElasticsearchInvoker,
			jsonObject -> {
				DXPEntity dxpEntity = _objectMapper.convertValue(
					jsonObject, DXPEntity.class);

				dxpEntity.setIsNew(Boolean.TRUE);
				dxpEntity.setType(type);

				_dxpEntityRepository.save(dxpEntity);

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", _getDXPEntityIds(true, type)))
		).iterate();
	}

	private void _upgradeExpandoColumns() throws Exception {
		JSONArrayIterator.of(
			"field-mappings", _faroInfoElasticsearchInvoker,
			jsonObject -> {
				JSONObject dataSourceFieldNamesJSONObject =
					jsonObject.getJSONObject("dataSourceFieldNames");

				for (String key : dataSourceFieldNamesJSONObject.keySet()) {
					DXPEntity dxpEntity = new DXPEntity();

					dxpEntity.setDataSourceId(Long.valueOf(key));

					JSONObject fieldsJSONObject = JSONUtil.put(
						"dataType", jsonObject.get("fieldType")
					).put(
						"name", dataSourceFieldNamesJSONObject.get(key)
					);

					if (StringUtils.equals(
							jsonObject.getString("ownerType"), "individual")) {

						fieldsJSONObject.put(
							"className", DXPEntity.Type.CLASS_NAME_USER);
					}
					else if (StringUtils.equals(
								jsonObject.getString("ownerType"),
								"organization")) {

						fieldsJSONObject.put(
							"className",
							DXPEntity.Type.CLASS_NAME_ORGANIZATION);
					}

					dxpEntity.setFieldsJSONObject(fieldsJSONObject);
					dxpEntity.setIsNew(Boolean.TRUE);
					dxpEntity.setId(Long.valueOf(jsonObject.getString("id")));
					dxpEntity.setModifiedDate(
						DateUtil.toUTCDate(
							jsonObject.getString("dateModified")));
					dxpEntity.setType(DXPEntity.Type.EXPANDO_COLUMN);

					_dxpEntityRepository.save(dxpEntity);
				}

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", "custom")
			).mustNot(
				QueryBuilders.termsQuery(
					"id", _getDXPEntityIds(true, DXPEntity.Type.EXPANDO_COLUMN))
			)
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(
		DXPEntityMigrationUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

}