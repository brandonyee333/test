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

package com.liferay.osb.asah.extractor.processor;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOrganizationDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoSuppressionDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import io.prometheus.client.Counter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DXPEntitiesMessageProcessor {

	public void processQueuedMessages() {
		while (true) {
			List<String> messages = _messageSubscriber.pullMessages(50);

			if (messages.isEmpty()) {
				break;
			}

			for (String message : messages) {
				JSONObject jsonObject = new JSONObject(message);

				ProjectIdThreadLocal.forProject(
					jsonObject.getString("projectId"),
					() -> _processMessage(jsonObject));
			}

			_dxpEntitiesCounter.inc(messages.size());
		}
	}

	private void _addAssociations(
		DXPEntityType dxpEntityType, JSONObject jsonObject) {

		if (dxpEntityType.isUser()) {
			return;
		}

		String id = jsonObject.getString("id");

		try {
			JSONArrayIterator.of(
				"users", _dxpRawElasticsearchInvoker,
				userJSONObject -> {
					JSONObject individualJSONObject =
						_faroInfoElasticsearchInvoker.fetch(
							"individuals",
							_getIndividualQueryBuilder(
								jsonObject.getString("osbAsahDataSourceId"), id,
								dxpEntityType.getIndividualFieldName(),
								userJSONObject.getString("uuid")));

					if (individualJSONObject != null) {
						_faroInfoIndividualDog.addIndividualAssociation(
							dxpEntityType, id, individualJSONObject);
					}

					return null;
				}
			).setQueryBuilder(
				QueryBuilders.termQuery(
					"memberships." + dxpEntityType.getClassName(),
					jsonObject.get(dxpEntityType.getIdFieldName()))
			).iterate();
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(e, e);
			}
		}
	}

	private String _getFieldType(String dataType, String displayType) {
		if (displayType.equals("boolean")) {
			return "Boolean";
		}
		else if (displayType.equals("date")) {
			return "Date";
		}
		else if (dataType.equals("Decimal") || dataType.equals("Integer")) {
			return "Number";
		}
		else if (dataType.equals("Text")) {
			return "Text";
		}

		return null;
	}

	private QueryBuilder _getIndividualQueryBuilder(
		String dataSourceId, String id, String individualFieldName,
		String uuid) {

		return BoolQueryBuilderUtil.filter(
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.individualPKs", uuid)
				),
				ScoreMode.None)
		).mustNot(
			QueryBuilders.termsQuery(individualFieldName, id)
		);
	}

	private String _getOwnerType(String className) {
		if (className.equals(DXPEntityType.CLASS_NAME_USER)) {
			return "individual";
		}
		else if (className.equals(DXPEntityType.CLASS_NAME_ORGANIZATION)) {
			return "organization";
		}

		return null;
	}

	private void _processAssociationObject(
		String action, DXPEntityType dxpEntityType,
		JSONObject objectJSONObject) {

		String dataSourceId = objectJSONObject.getString("osbAsahDataSourceId");

		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.fetch(
			"users",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"userId", objectJSONObject.getLong("userId"))
			).filter(
				QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId)
			));

		if (userJSONObject == null) {
			return;
		}

		JSONObject membershipsJSONObject = userJSONObject.optJSONObject(
			"memberships");

		if (membershipsJSONObject == null) {
			membershipsJSONObject = new JSONObject();

			userJSONObject.put("memberships", membershipsJSONObject);
		}

		JSONArray membershipJSONArray = membershipsJSONObject.optJSONArray(
			dxpEntityType.getClassName());

		if (membershipJSONArray == null) {
			membershipJSONArray = new JSONArray();

			membershipsJSONObject.put(
				dxpEntityType.getClassName(), membershipJSONArray);
		}

		String emailAddress = objectJSONObject.optString("emailAddress");

		if (StringUtils.isBlank(emailAddress)) {
			return;
		}

		long classPK = objectJSONObject.getLong("classPK");

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("demographics.email.value", emailAddress));

		String queryBuilderName = null;

		if (action.equals("addAssociation")) {
			membershipJSONArray.put(classPK);

			_faroInfoIndividualDog.addIndividualAssociation(
				classPK, Long.valueOf(dataSourceId), dxpEntityType,
				individualJSONObject);

			queryBuilderName = "addQueryBuilder";
		}
		else if (action.equals("deleteAssociation")) {
			JSONUtil.removeValue(membershipJSONArray, classPK);

			_faroInfoIndividualDog.deleteIndividualAssociation(
				classPK, Long.valueOf(dataSourceId), dxpEntityType,
				individualJSONObject);

			queryBuilderName = "removeQueryBuilder";
		}

		if (!dxpEntityType.isUser()) {
			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					queryBuilderName,
					QueryBuilders.termsQuery(
						dxpEntityType.getIndividualSegmentFieldName(),
						_faroInfoIndividualDog.getAssociatedIds(
							Long.valueOf(dataSourceId), dxpEntityType,
							Collections.singletonList(classPK))
					).toString()
				).put(
					"dateModified", DateUtil.newDateString()
				));
		}

		_dxpRawElasticsearchInvoker.update("users", userJSONObject);
	}

	private void _processExpandoColumnObject(
		String action, JSONObject objectJSONObject) {

		if (!action.equals("add")) {
			return;
		}

		String dataType = objectJSONObject.getString("dataType");
		String displayType = objectJSONObject.getString("displayType");
		String name = objectJSONObject.getString("name");

		_faroInfoFieldMappingDog.addFieldMapping(
			"custom", name, objectJSONObject.getLong("osbAsahDataSourceId"),
			displayType, StringUtils.removeEnd(name, "-" + dataType),
			_getFieldType(dataType, displayType),
			_getOwnerType(objectJSONObject.getString("className")));
	}

	private void _processMessage(JSONObject messageJSONObject) {
		JSONObject contextJSONObject = messageJSONObject.getJSONObject(
			"context");
		JSONObject objectJSONObject = messageJSONObject.getJSONObject("object");

		DXPEntityType dxpEntityType = DXPEntityType.of(
			contextJSONObject.getString("type"));

		if ((dxpEntityType == null) ||
			(dxpEntityType.isUser() &&
			 _faroInfoSuppressionDog.isSuppressed(
				 objectJSONObject.optString("emailAddress"), null))) {

			return;
		}

		_processObject(
			contextJSONObject.getString("action"), dxpEntityType,
			objectJSONObject);
	}

	private void _processObject(
		String action, DXPEntityType dxpEntityType,
		JSONObject objectJSONObject) {

		if (action.equalsIgnoreCase("addAssociation") ||
			action.equalsIgnoreCase("deleteAssociation")) {

			_processAssociationObject(action, dxpEntityType, objectJSONObject);

			return;
		}

		if (dxpEntityType.isExpandoColumn()) {
			_processExpandoColumnObject(action, objectJSONObject);

			return;
		}

		if (dxpEntityType.isUserField()) {
			_processUserFieldObject(action, objectJSONObject);

			return;
		}

		JSONObject jsonObject = _dxpRawElasticsearchInvoker.fetch(
			dxpEntityType.getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					dxpEntityType.getIdFieldName(),
					objectJSONObject.get(dxpEntityType.getIdFieldName()))
			).filter(
				QueryBuilders.termQuery(
					"osbAsahDataSourceId",
					objectJSONObject.getString("osbAsahDataSourceId"))
			));

		if (action.equalsIgnoreCase("delete") && (jsonObject != null)) {
			try {
				_segmentDog.disableDynamicIndividualSegments(
					dxpEntityType, jsonObject.getString("id"));
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(e, e);
				}
			}

			_dxpRawElasticsearchInvoker.delete(
				dxpEntityType.getCollectionName(), jsonObject);
		}
		else if (!action.equalsIgnoreCase("delete")) {
			if (jsonObject != null) {
				_dxpRawElasticsearchInvoker.update(
					dxpEntityType.getCollectionName(),
					jsonObject.getString("id"), objectJSONObject);
			}
			else {
				jsonObject = _dxpRawElasticsearchInvoker.add(
					dxpEntityType.getCollectionName(), objectJSONObject);

				_addAssociations(dxpEntityType, jsonObject);
			}
		}

		if (!dxpEntityType.isOrganization() && !dxpEntityType.isUser()) {
			return;
		}

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			objectJSONObject.getLong("osbAsahDataSourceId"));

		if ((dataSource == null) ||
			Objects.equals(dataSource.getState(), "IN_PROGRESS_DELETING")) {

			return;
		}

		if (dxpEntityType.isOrganization()) {
			_processOrganizationObject(action, dataSource, objectJSONObject);
		}
		else if (dxpEntityType.isUser()) {
			_processUserObject(dataSource, objectJSONObject);
		}
	}

	private void _processOrganizationObject(
		String action, DataSource dataSource, JSONObject objectJSONObject) {

		String organizationPK = objectJSONObject.optString("organizationId");

		if (organizationPK == null) {
			return;
		}

		JSONObject organizationJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"organizations",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"dataSourceId", String.valueOf(dataSource.getId()))
			).filter(
				QueryBuilders.termsQuery("organizationPK", organizationPK)
			));

		try {
			if (action.equalsIgnoreCase("delete")) {
				_faroInfoOrganizationDog.deleteOrganization(
					organizationJSONObject);
			}
			else if (organizationJSONObject == null) {
				_faroInfoOrganizationDog.addOrganization(
					objectJSONObject, dataSource);
			}
			else {
				_faroInfoOrganizationDog.updateOrganization(
					objectJSONObject, dataSource, organizationJSONObject);
			}
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(e, e);
			}
		}
	}

	private void _processUserFieldObject(
		String action, JSONObject objectJSONObject) {

		if (!action.equals("add")) {
			return;
		}

		String dataType = objectJSONObject.getString("dataType");
		String name = objectJSONObject.getString("name");

		_faroInfoFieldMappingDog.addFieldMapping(
			"demographics", name,
			objectJSONObject.getLong("osbAsahDataSourceId"), null,
			_defaultLiferayFieldMappingMaps.getOrDefault(name, name),
			_getFieldType(dataType, dataType), "individual");
	}

	private void _processUserObject(
		DataSource dataSource, JSONObject objectJSONObject) {

		String emailAddress = objectJSONObject.optString("emailAddress");

		if (emailAddress == null) {
			return;
		}

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery(
				"emailAddressHashed",
				DigestUtils.sha256Hex(StringUtils.lowerCase(emailAddress))));

		try {
			if (individualJSONObject == null) {
				_faroInfoIndividualDog.addIndividual(
					objectJSONObject.optString("uuid", null), objectJSONObject,
					dataSource);
			}
			else {
				_faroInfoIndividualDog.updateIndividual(
					objectJSONObject.optString("uuid", null), objectJSONObject,
					dataSource, individualJSONObject);
			}
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(e, e);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		DXPEntitiesMessageProcessor.class);

	private static final Map<String, String> _defaultLiferayFieldMappingMaps =
		new HashMap<String, String>() {
			{
				put("addresses", "address");
				put("birthday", "birthDate");
				put("emailAddress", "email");
				put("firstName", "givenName");
				put("lastName", "familyName");
				put("middleName", "additionalName");
				put("phones", "telephone");
			}
		};
	private static final Counter _dxpEntitiesCounter = PrometheusUtil.counter(
		"extractor_dxp_entities_count", "The number of extracted DXP entities");

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoOrganizationDog _faroInfoOrganizationDog;

	@Autowired
	private FaroInfoSuppressionDog _faroInfoSuppressionDog;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private SegmentDog _segmentDog;

}