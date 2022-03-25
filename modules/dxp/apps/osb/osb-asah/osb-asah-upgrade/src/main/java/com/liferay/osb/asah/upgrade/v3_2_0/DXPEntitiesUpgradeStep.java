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

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryError;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllResponse;
import com.google.cloud.bigquery.TableId;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class DXPEntitiesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		long startTime = System.currentTimeMillis();

		List<Map<String, Object>> dxpEntities = _getDXPEntities();

		int successfulWrites = dxpEntities.size();

		try {
			if (dxpEntities.isEmpty()) {
				return;
			}

			TableId tableId = TableId.of(
				ProjectIdThreadLocal.getProjectId(), _DXP_ENTITY_TABLE_NAME);

			InsertAllRequest.Builder builder = InsertAllRequest.newBuilder(
				tableId);

			Stream<Map<String, Object>> stream = dxpEntities.stream();

			stream.map(
				dxpEntityMap -> InsertAllRequest.RowToInsert.of(
					MapUtil.get(dxpEntityMap, "id"), dxpEntityMap)
			).forEach(
				builder::addRow
			);

			InsertAllResponse insertAllResponse = _bigQuery.insertAll(
				builder.build());

			if (insertAllResponse.hasErrors()) {
				Map<Long, List<BigQueryError>> insertErrors =
					insertAllResponse.getInsertErrors();

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Retrying %d dxp entities", insertErrors.size()));
				}

				_sleep(1000);

				int failedWrites = _retry(
					insertErrors.keySet(), dxpEntities, tableId);

				successfulWrites = successfulWrites - failedWrites;
			}
		}
		catch (BigQueryException bigQueryException) {
			_log.error("Unable to save dxp entities batch", bigQueryException);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"It took " + (System.currentTimeMillis() - startTime) +
						"ms to insert " + successfulWrites + " dxp entities");
			}

			dxpEntities.clear();
		}
	}

	private String _generateId(DXPEntity dxpEntity) {
		return DigestUtils.sha256Hex(
			String.join(
				"#", ProjectIdThreadLocal.getProjectId(),
				String.valueOf(dxpEntity.getDataSourceId()),
				String.valueOf(dxpEntity.getType()),
				dxpEntity.getIdFieldValue()));
	}

	private List<Map<String, Object>> _getDXPEntities() {
		List<DXPEntity> dxpEntities = new ArrayList<>();

		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.GROUP));
		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.ORGANIZATION));
		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.ROLE));
		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.TEAM));
		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.USER));
		dxpEntities.addAll(_getDXPEntities(DXPEntity.Type.USER_GROUP));

		Stream<DXPEntity> stream = dxpEntities.stream();

		return stream.map(
			this::_toMap
		).collect(
			Collectors.toList()
		);
	}

	private List<DXPEntity> _getDXPEntities(DXPEntity.Type type) {
		List<DXPEntity> dxpEntities = new ArrayList<>();

		Page<? extends DXPEntity> dxpEntitiesPage = null;

		int page = 0;

		while (true) {
			dxpEntitiesPage = _dxpEntityDog.getDXPEntityPage(
				null, null, _DXP_ENTITY_PAGE_SIZE, Sort.asc("id"),
				_DXP_ENTITY_PAGE_SIZE * page++, type);

			if (dxpEntitiesPage.isEmpty()) {
				break;
			}

			dxpEntities.addAll(dxpEntitiesPage.getContent());
		}

		return dxpEntities;
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
	}

	private int _retry(
		Set<Long> errorIndexes, List<Map<String, Object>> dxpEntities,
		TableId tableId) {

		int failedWrites = 0;

		InsertAllRequest.Builder builder = InsertAllRequest.newBuilder(tableId);

		for (Long errorIndex : errorIndexes) {
			Map<String, Object> dxpEntitiy = dxpEntities.get(
				errorIndex.intValue());

			builder.addRow(InsertAllRequest.RowToInsert.of(dxpEntitiy));
		}

		InsertAllResponse insertAllResponse = _bigQuery.insertAll(
			builder.build());

		if (insertAllResponse.hasErrors()) {
			Map<Long, List<BigQueryError>> insertErrors =
				insertAllResponse.getInsertErrors();

			for (Map.Entry<Long, List<BigQueryError>> entry :
					insertErrors.entrySet()) {

				Long key = entry.getKey();

				_log.error(
					String.format(
						"Unable to insert %s. Reason: %s",
						dxpEntities.get(key.intValue()), entry.getValue()));
			}

			failedWrites = insertErrors.size();
		}

		return failedWrites;
	}

	private void _sleep(long millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private Map<String, Object> _toMap(DXPEntity dxpEntity) {
		Map<String, Object> dxpEntityMap = new HashMap<>();

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		if (dxpEntity.getType() == DXPEntity.Type.ORGANIZATION) {
			if (fieldsJSONObject.has("nameTreePath") &&
				fieldsJSONObject.has("treePath")) {

				fieldsJSONObject.put(
					"treePath", fieldsJSONObject.get("nameTreePath"));
			}
		}
		else if (dxpEntity.getType() == DXPEntity.Type.USER) {
			if (!fieldsJSONObject.has("createDate")) {
				fieldsJSONObject.put("createDate", DateUtil.newDateString());
			}

			if (fieldsJSONObject.has("memberships")) {
				JSONObject membershipJSONObject =
					fieldsJSONObject.getJSONObject("memberships");

				for (String key : membershipJSONObject.keySet()) {
					DXPEntity.Type type = DXPEntity.Type.of(key);

					if (type == null) {
						continue;
					}

					fieldsJSONObject.put(
						type.getIndividualFieldName(),
						membershipJSONObject.get(key));
				}
			}
		}

		if (fieldsJSONObject.has("expando")) {
			dxpEntityMap.put(
				"expandoFields",
				String.valueOf(fieldsJSONObject.get("expando")));
		}

		dxpEntityMap.put("classPK", dxpEntity.getIdFieldValue());
		dxpEntityMap.put("dataSourceId", dxpEntity.getDataSourceId());
		dxpEntityMap.put("fields", fieldsJSONObject.toString());
		dxpEntityMap.put("id", _generateId(dxpEntity));

		if (!fieldsJSONObject.has("modifiedDate")) {
			dxpEntityMap.put("modifiedDate", DateUtil.newDateString());
		}
		else {
			dxpEntityMap.put(
				"modifiedDate", fieldsJSONObject.get("modifiedDate"));
		}

		dxpEntityMap.put("projectId", ProjectIdThreadLocal.getProjectId());
		dxpEntityMap.put("type", String.valueOf(dxpEntity.getType()));

		return dxpEntityMap;
	}

	private static final int _DXP_ENTITY_PAGE_SIZE = 500;

	private static final String _DXP_ENTITY_TABLE_NAME = "dxp-entity-import";

	private static final Log _log = LogFactory.getLog(
		DXPEntitiesUpgradeStep.class);

	private BigQuery _bigQuery;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

}