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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOrganizationDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoSuppressionDog;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.extractor.cache.DataSourceCache;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import java.util.Collections;
import java.util.Objects;

import javax.annotation.PostConstruct;

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
			int messagesCount = _queueHttp.getMessagesCount(
				QueueHttp.QUEUE_NAME_DXP_ENTITIES);

			_dxpEntitiesMessageQueueGauge.set(messagesCount);

			if (messagesCount <= 0) {
				break;
			}

			JSONObject responseJSONObject = new JSONObject(
				_queueHttp.getMessages(QueueHttp.QUEUE_NAME_DXP_ENTITIES));

			JSONArray messagesJSONArray = responseJSONObject.getJSONArray(
				"messages");

			_processMessages(messagesJSONArray);

			_dxpEntitiesCounter.inc(messagesJSONArray.length());
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

	@PostConstruct
	private void _init() {
		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
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
				classPK, dataSourceId, dxpEntityType, individualJSONObject);

			queryBuilderName = "addQueryBuilder";
		}
		else if (action.equals("deleteAssociation")) {
			JSONUtil.removeValue(membershipJSONArray, classPK);

			_faroInfoIndividualDog.deleteIndividualAssociation(
				classPK, dataSourceId, dxpEntityType, individualJSONObject);

			queryBuilderName = "removeQueryBuilder";
		}

		if (!dxpEntityType.isUser()) {
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					queryBuilderName,
					QueryBuilders.termsQuery(
						dxpEntityType.getIndividualSegmentFieldName(),
						_faroInfoIndividualDog.getAssociatedIds(
							dataSourceId, dxpEntityType,
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
			"custom", name, objectJSONObject.getString("osbAsahDataSourceId"),
			displayType, StringUtils.removeEnd(name, "-" + dataType),
			_getFieldType(dataType, displayType),
			_getOwnerType(objectJSONObject.getString("className")));
	}

	private void _processMessages(JSONArray messagesJSONArray) {
		for (int i = 0; i < messagesJSONArray.length(); i++) {
			JSONObject messagesJSONObject = new JSONObject(
				String.valueOf(messagesJSONArray.get(i)));

			JSONObject messageJSONObject = new JSONObject(
				String.valueOf(messagesJSONObject.getString("message")));

			JSONObject contextJSONObject = messageJSONObject.getJSONObject(
				"context");
			JSONObject objectJSONObject = messageJSONObject.getJSONObject(
				"object");

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
				_faroInfoIndividualSegmentDog.disableDynamicIndividualSegments(
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

		JSONObject dataSourceJSONObject =
			_dataSourceCache.getDataSourceJSONObject(
				objectJSONObject.getString("osbAsahDataSourceId"));

		if ((dataSourceJSONObject == null) ||
			Objects.equals(
				dataSourceJSONObject.getString("state"),
				"IN_PROGRESS_DELETING")) {

			return;
		}

		if (dxpEntityType.isOrganization()) {
			_processOrganizationObject(
				action, dataSourceJSONObject, objectJSONObject);
		}
		else if (dxpEntityType.isUser()) {
			_processUserObject(dataSourceJSONObject, objectJSONObject);
		}
	}

	private void _processOrganizationObject(
		String action, JSONObject dataSourceJSONObject,
		JSONObject objectJSONObject) {

		String organizationPK = objectJSONObject.optString("organizationId");

		if (organizationPK == null) {
			return;
		}

		JSONObject organizationJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"organizations",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"dataSourceId", dataSourceJSONObject.getString("id"))
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
					objectJSONObject, dataSourceJSONObject);
			}
			else {
				_faroInfoOrganizationDog.updateOrganization(
					objectJSONObject, dataSourceJSONObject,
					organizationJSONObject);
			}
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(e, e);
			}
		}
	}

	private void _processUserObject(
		JSONObject dataSourceJSONObject, JSONObject objectJSONObject) {

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
					dataSourceJSONObject);
			}
			else {
				_faroInfoIndividualDog.updateIndividual(
					objectJSONObject.optString("uuid", null), objectJSONObject,
					dataSourceJSONObject, individualJSONObject);
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

	private static final Counter _dxpEntitiesCounter = PrometheusUtil.counter(
		"extractor_dxp_entities_count", "The number of extracted DXP entities");
	private static final Gauge _dxpEntitiesMessageQueueGauge =
		PrometheusUtil.gauge(
			"extractor_dxp_entities_message_queue_size",
			"The number of DXP entities messages queued to be extracted");

	@Autowired
	private DataSourceCache _dataSourceCache;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoOrganizationDog _faroInfoOrganizationDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private FaroInfoSuppressionDog _faroInfoSuppressionDog;

	@Autowired
	private QueueHttp _queueHttp;

}