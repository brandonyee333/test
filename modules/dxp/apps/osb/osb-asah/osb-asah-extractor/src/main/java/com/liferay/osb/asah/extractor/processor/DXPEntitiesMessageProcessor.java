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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.User;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
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
		DXPEntity dxpEntity, DXPEntity.Type dxpEntityType) {

		if (dxpEntityType.isUser()) {
			return;
		}

		try {
			JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

			List<User> users =
				_dxpEntityDog.findUsersByMembershipClassNameAndMembershipId(
					dxpEntityType.getClassName(),
					fieldsJSONObject.getString(dxpEntityType.getIdFieldName()));

			users.forEach(
				user -> {
					JSONObject userFieldsJSONObject =
						user.getFieldsJSONObject();

					JSONObject individualJSONObject =
						_faroInfoElasticsearchInvoker.fetch(
							"individuals",
							_getIndividualQueryBuilder(
								String.valueOf(dxpEntity.getDataSourceId()),
								String.valueOf(dxpEntity.getId()),
								dxpEntityType.getIndividualFieldName(),
								userFieldsJSONObject.getString("uuid")));

					if (individualJSONObject != null) {
						_faroInfoIndividualDog.addIndividualAssociation(
							dxpEntityType, String.valueOf(dxpEntity.getId()),
							individualJSONObject);
					}
				});
		}
		catch (Exception exception) {
			if (_log.isInfoEnabled()) {
				_log.info(exception, exception);
			}
		}
	}

	private String _getFieldType(String dataType, String displayType) {
		if (displayType.equals("boolean")) {
			return "Boolean";
		}

		if (displayType.equals("date")) {
			return "Date";
		}

		if (dataType.equals("Decimal") || dataType.equals("Integer")) {
			return "Number";
		}

		if (dataType.equals("Text")) {
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
		if (className.equals(DXPEntity.Type.CLASS_NAME_USER)) {
			return "individual";
		}

		if (className.equals(DXPEntity.Type.CLASS_NAME_ORGANIZATION)) {
			return "organization";
		}

		return null;
	}

	private void _processAssociationObject(
		String action, JSONObject objectJSONObject, DXPEntity.Type type) {

		Long dataSourceId = Long.valueOf(
			objectJSONObject.getString("osbAsahDataSourceId"));

		User user = _dxpEntityDog.fetchUserByFields(
			new HashMap() {
				{
					put("dataSourceId", dataSourceId);
					put(
						"fields." + DXPEntity.Type.USER.getIdFieldName(),
						objectJSONObject.getLong("userId"));
				}
			});

		if (user == null) {
			return;
		}

		JSONObject userFieldsJSONObject = user.getFieldsJSONObject();

		JSONObject membershipsJSONObject = userFieldsJSONObject.optJSONObject(
			"memberships");

		if (membershipsJSONObject == null) {
			membershipsJSONObject = new JSONObject();

			userFieldsJSONObject.put("memberships", membershipsJSONObject);
		}

		JSONArray membershipJSONArray = membershipsJSONObject.optJSONArray(
			type.getClassName());

		if (membershipJSONArray == null) {
			membershipJSONArray = new JSONArray();

			membershipsJSONObject.put(type.getClassName(), membershipJSONArray);
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
				classPK, dataSourceId, type, individualJSONObject);

			queryBuilderName = "addQueryBuilder";
		}
		else if (action.equals("deleteAssociation")) {
			JSONUtil.removeValue(membershipJSONArray, classPK);

			_faroInfoIndividualDog.deleteIndividualAssociation(
				classPK, dataSourceId, type, individualJSONObject);

			queryBuilderName = "removeQueryBuilder";
		}

		if (!type.isUser()) {
			List<String> associatedIds =
				_faroInfoIndividualDog.getAssociatedIds(
					dataSourceId, type, Collections.singletonList(classPK));

			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					queryBuilderName,
					type.getIndividualSegmentFieldName() + " eq " +
						associatedIds
				).put(
					"dateModified", DateUtil.newDateString()
				));
		}

		_dxpEntityDog.updateDXPEntity(user);
	}

	private void _processExpandoColumnObject(
		String action, JSONObject objectJSONObject) {

		if (!action.equals("add")) {
			return;
		}

		String dataType = objectJSONObject.getString("dataType");
		String displayType = objectJSONObject.getString("displayType");
		String name = objectJSONObject.getString("name");

		_fieldMappingDog.addFieldMapping(
			"custom", name, objectJSONObject.getLong("osbAsahDataSourceId"),
			displayType, StringUtils.removeEnd(name, "-" + dataType),
			_getFieldType(dataType, displayType),
			_getOwnerType(objectJSONObject.getString("className")));
	}

	private void _processMessage(JSONObject messageJSONObject) {
		JSONObject contextJSONObject = messageJSONObject.getJSONObject(
			"context");
		JSONObject objectJSONObject = messageJSONObject.getJSONObject("object");

		DXPEntity.Type dxpEntityType = DXPEntity.Type.of(
			contextJSONObject.getString("type"));

		if ((dxpEntityType == null) ||
			(dxpEntityType.isUser() &&
			 _suppressionDog.isSuppressed(
				 objectJSONObject.optString("emailAddress"), null))) {

			return;
		}

		_processObject(
			contextJSONObject.getString("action"), objectJSONObject,
			dxpEntityType);
	}

	private void _processObject(
		String action, JSONObject objectJSONObject, DXPEntity.Type type) {

		DXPEntity dxpEntity = _dxpEntityDog.fetchUserByFields(
			new HashMap<String, Object>() {
				{
					put(
						"dataSourceId",
						objectJSONObject.getString("osbAsahDataSourceId"));
					put("userId", objectJSONObject.getLong("userId"));
				}
			});

		if (action.equalsIgnoreCase("addAssociation") ||
			action.equalsIgnoreCase("deleteAssociation")) {

			_processAssociationObject(action, objectJSONObject, type);

			return;
		}

		if (type.isExpandoColumn()) {
			_processExpandoColumnObject(action, objectJSONObject);

			return;
		}

		if (type.isUserField()) {
			_processUserFieldObject(action, objectJSONObject);

			return;
		}

		if (action.equalsIgnoreCase("delete") && (dxpEntity != null)) {
			try {
				_segmentDog.disableDynamicSegments(type, dxpEntity.getId());
			}
			catch (Exception exception) {
				if (_log.isInfoEnabled()) {
					_log.info(exception, exception);
				}
			}

			_dxpEntityDog.delete(dxpEntity);
		}
		else if (!action.equalsIgnoreCase("delete")) {
			DXPEntity newDXPEntity = new DXPEntity(
				objectJSONObject.getLong("osbAsahDataSourceId"),
				objectJSONObject);

			if (dxpEntity != null) {
				newDXPEntity.setId(dxpEntity.getId());

				_dxpEntityDog.updateDXPEntity(newDXPEntity);
			}
			else {
				_addAssociations(
					_dxpEntityDog.addDXPEntity(newDXPEntity, type), type);
			}
		}

		if (!type.isOrganization() && !type.isUser()) {
			return;
		}

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			objectJSONObject.getLong("osbAsahDataSourceId"));

		if ((dataSource == null) ||
			Objects.equals(dataSource.getState(), "IN_PROGRESS_DELETING")) {

			return;
		}

		if (type.isOrganization()) {
			_processOrganizationObject(action, dataSource, objectJSONObject);
		}
		else if (type.isUser()) {
			_processUserObject(dataSource, objectJSONObject);
		}
	}

	private void _processOrganizationObject(
		String action, DataSource dataSource, JSONObject objectJSONObject) {

		String organizationPK = objectJSONObject.optString(
			"organizationId", null);

		if (organizationPK == null) {
			return;
		}

		Organization organization =
			_organizationRepository.findByDataSourceIdAndOrganizationPK(
				dataSource.getId(), Long.valueOf(organizationPK));

		try {
			if (action.equalsIgnoreCase("delete")) {
				_organizationDog.deleteOrganization(organization);
			}
			else if (organization == null) {
				_organizationDog.addOrganization(objectJSONObject, dataSource);
			}
			else {
				_organizationDog.updateOrganization(
					objectJSONObject, dataSource, organization);
			}
		}
		catch (Exception exception) {
			if (_log.isInfoEnabled()) {
				_log.info(exception, exception);
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

		_fieldMappingDog.addFieldMapping(
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
		catch (Exception exception) {
			if (_log.isInfoEnabled()) {
				_log.info(exception, exception);
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

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private OrganizationRepository _organizationRepository;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SuppressionDog _suppressionDog;

}