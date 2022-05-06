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

package com.liferay.osb.asah.stream.curator.bot.nanite.dxpentity;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageStreamingSubscriber;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

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
public class DXPEntitiesNanite implements MessageReceiver, Nanite {

	public void addMessageStreamingSubscriber() {
		try {
			_messageStreamingSubscriber.subscribe(
				_maxOutstandingMessages, this);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_boundedExecutor.awaitPendingTasks();
	}

	@Override
	public String getCollectionName() {
		return "DXPEntity";
	}

	@Override
	public long getInterval() {
		return -1;
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

			_processMessage(pubsubMessage);
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

	@Override
	public void run() {
		addMessageStreamingSubscriber();
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
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

		if (StringUtils.isBlank(objectJSONObject.optString("emailAddress"))) {
			return;
		}

		long classPK = objectJSONObject.getLong("classPK");

		if (action.equals("addAssociation")) {
			membershipJSONArray.put(classPK);
		}
		else if (action.equals("deleteAssociation")) {
			JSONUtil.removeValue(membershipJSONArray, classPK);
		}

		_dxpEntityDog.updateDXPEntity(dxpEntity);
	}

	private void _processMessage(PubsubMessage pubsubMessage) {
		Map<String, String> attributesMap = pubsubMessage.getAttributesMap();

		String projectId = attributesMap.get("projectId");

		_boundedExecutor.runAsync(
			() -> {
				ProjectIdThreadLocal.setProjectId(projectId);

				long start = System.currentTimeMillis();

				ByteString byteString = pubsubMessage.getData();

				JSONArray jsonArray = new JSONArray(byteString.toStringUtf8());

				for (int i = 0; i < jsonArray.length(); i++) {
					_processMessageJSONObject(jsonArray.getJSONObject(i));
				}

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

	private void _processMessageJSONObject(JSONObject messageJSONObject) {
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

		if (type.isUserField()) {
			return;
		}

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			new HashMap<String, Object>() {
				{
					put(
						"dataSourceId",
						objectJSONObject.getString("osbAsahDataSourceId"));

					if (type.isExpandoColumn()) {
						put("fields.name", objectJSONObject.optString("name"));
					}
					else {
						put(
							"fields." + type.getIdFieldName(),
							objectJSONObject.optInt(type.getIdFieldName()));
					}
				}
			},
			type);

		if (action.equalsIgnoreCase("delete") && (dxpEntity != null)) {
			_dxpEntityDog.delete(dxpEntity);
		}
		else if (!action.equalsIgnoreCase("delete")) {
			DXPEntity newDXPEntity = new DXPEntity(
				objectJSONObject.getLong("osbAsahDataSourceId"),
				objectJSONObject);

			if (dxpEntity != null) {
				newDXPEntity.setFieldsJSONObject(
					JSONUtil.merge(
						dxpEntity.getFieldsJSONObject(), objectJSONObject));
				newDXPEntity.setId(dxpEntity.getId());
				newDXPEntity.setType(type);

				_dxpEntityDog.updateDXPEntity(newDXPEntity);
			}
			else {
				_dxpEntityDog.addDXPEntity(newDXPEntity, type);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(DXPEntitiesNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Value(
		"${osb.asah.analytics.events.message.processor.streaming.max.outstanding.messages:1000}"
	)
	private long _maxOutstandingMessages;

	@MessageStreamingSubscriber.Autowired(
		channel = Channel.DXP_ENTITIES_MESSAGE
	)
	private MessageStreamingSubscriber _messageStreamingSubscriber;

	@Autowired
	private SuppressionDog _suppressionDog;

}