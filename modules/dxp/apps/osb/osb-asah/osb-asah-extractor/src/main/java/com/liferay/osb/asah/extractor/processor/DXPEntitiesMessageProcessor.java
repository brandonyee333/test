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

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageStreamingSubscriber;
import com.liferay.osb.asah.common.model.DXPUser;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DXPEntitiesMessageProcessor implements MessageReceiver {

	public void processQueuedMessages() {
		try {
			_messageStreaming.subscribe(_maxOutstandingMessages, this);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_boundedExecutor.awaitPendingTasks();
	}

	@Override
	public void receiveMessage(
		PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing message with ID " +
						pubsubMessage.getMessageId());
			}

			ByteString byteString = pubsubMessage.getData();

			JSONObject jsonObject = new JSONObject(byteString.toStringUtf8());

			String projectId = jsonObject.getString("projectId");

			_boundedExecutor.runAsync(
				() -> {
					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(projectId);
					_processMessage(jsonObject);

					if (_log.isDebugEnabled()) {
						_log.debug(
							String.format(
								"Message %s processed in %d ms",
								pubsubMessage.getMessageId(),
								System.currentTimeMillis() - start));
					}
				},
				KeyReentrantLock.getReentrantLock(getClass(), projectId));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Sending ack of message with ID " +
						pubsubMessage.getMessageId());
			}

			ackReplyConsumer.ack();
		}
	}

	private void _addAssociations(
		DXPEntity dxpEntity, DXPEntity.Type dxpEntityType) {

		if (dxpEntityType.isUser()) {
			return;
		}

		try {
			JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

			List<DXPUser> dxpUsers =
				_dxpEntityDog.findDXPUsersByMembershipClassNameAndMembershipId(
					dxpEntityType.getClassName(),
					fieldsJSONObject.getLong(dxpEntityType.getIdFieldName()));

			dxpUsers.forEach(
				user -> {
					JSONObject userFieldsJSONObject =
						user.getFieldsJSONObject();

					Individual individual =
						_individualDog.
							fetchIndividualByAssociationIdNotAndDataSourceIdAndIndividualPK(
								dxpEntity.getId(), dxpEntity.getDataSourceId(),
								dxpEntityType.getIndividualFieldName(),
								userFieldsJSONObject.getString("uuid"));

					if (individual != null) {
						_individualDog.addIndividualAssociation(
							dxpEntityType, dxpEntity.getId(), individual);
					}
				});
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
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

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			new HashMap() {
				{
					put("dataSourceId", dataSourceId);
					put(
						"fields." + DXPEntity.Type.USER.getIdFieldName(),
						objectJSONObject.getLong("userId"));
				}
			},
			DXPEntity.Type.USER);

		if (dxpEntity == null) {
			return;
		}

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		JSONObject membershipsJSONObject = fieldsJSONObject.optJSONObject(
			"memberships");

		if (membershipsJSONObject == null) {
			membershipsJSONObject = new JSONObject();

			fieldsJSONObject.put("memberships", membershipsJSONObject);
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

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			emailAddress);

		String queryBuilderName = null;

		if (action.equals("addAssociation")) {
			membershipJSONArray.put(classPK);

			_individualDog.addIndividualAssociation(
				classPK, dataSourceId, type, individual);

			queryBuilderName = "addFilter";
		}
		else if (action.equals("deleteAssociation")) {
			JSONUtil.removeValue(membershipJSONArray, classPK);

			_individualDog.deleteIndividualAssociation(
				classPK, dataSourceId, type, individual);

			queryBuilderName = "removeFilter";
		}

		if (!type.isUser()) {
			Set<Long> associatedIds = _individualDog.getAssociatedIds(
				dataSourceId, type, Collections.singletonList(classPK));

			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					queryBuilderName,
					type.getIndividualSegmentFieldName() + " eq [" +
						StringUtils.join(associatedIds, ",") + "]"
				).put(
					"dateModified", DateUtil.newDateString()
				));
		}

		_dxpEntityDog.updateDXPEntity(dxpEntity);
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

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			new HashMap<String, Object>() {
				{
					put(
						"dataSourceId",
						objectJSONObject.getString("osbAsahDataSourceId"));
					put(
						"fields." + type.getIdFieldName(),
						objectJSONObject.optInt(type.getIdFieldName()));
				}
			},
			type);

		if (action.equalsIgnoreCase("delete") && (dxpEntity != null)) {
			try {
				_segmentDog.disableDynamicSegments(dxpEntity.getId(), type);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}

			_dxpEntityDog.delete(dxpEntity);
		}
		else if (!action.equalsIgnoreCase("delete")) {
			DXPEntity newDXPEntity = new DXPEntity(
				objectJSONObject.getLong("osbAsahDataSourceId"),
				objectJSONObject);

			if (dxpEntity != null) {
				newDXPEntity.setId(dxpEntity.getId());
				newDXPEntity.setType(type);

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
			_log.error(exception, exception);
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

		Individual individual =
			_individualDog.fetchIndividualByEmailAddressHashed(
				DigestUtils.sha256Hex(StringUtils.lowerCase(emailAddress)));

		try {
			if (individual == null) {
				_individualDog.addIndividual(
					objectJSONObject.optString("uuid", null), objectJSONObject,
					dataSource);
			}
			else {
				_individualDog.updateIndividual(
					objectJSONObject.optString("uuid", null), objectJSONObject,
					dataSource, individual);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
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

	@Autowired
	private AsahTaskDog _asahTaskDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private IndividualDog _individualDog;

	@Value(
		"${osb.asah.analytics.events.message.processor.streaming.max.outstanding.messages:1000}"
	)
	private long _maxOutstandingMessages;

	@MessageStreamingSubscriber.Autowired(
		channel = Channel.DXP_ENTITIES_MESSAGE
	)
	private MessageStreamingSubscriber _messageStreaming;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private OrganizationRepository _organizationRepository;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SuppressionDog _suppressionDog;

}