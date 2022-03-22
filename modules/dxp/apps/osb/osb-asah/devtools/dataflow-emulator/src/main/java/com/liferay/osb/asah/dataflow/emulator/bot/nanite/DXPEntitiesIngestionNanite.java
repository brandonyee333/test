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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.dataflow.emulator.entity.BQExpandoColumn;
import com.liferay.osb.asah.dataflow.emulator.entity.BQExpandoValue;
import com.liferay.osb.asah.dataflow.emulator.entity.BQGroup;
import com.liferay.osb.asah.dataflow.emulator.entity.BQOrganization;
import com.liferay.osb.asah.dataflow.emulator.entity.BQRole;
import com.liferay.osb.asah.dataflow.emulator.entity.BQTeam;
import com.liferay.osb.asah.dataflow.emulator.entity.BQUser;
import com.liferay.osb.asah.dataflow.emulator.entity.BQUserGroup;
import com.liferay.osb.asah.dataflow.emulator.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQGroupRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQOrganizationRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQRoleRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQTeamRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQUserGroupRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQUserRepository;
import com.liferay.osb.asah.dataflow.emulator.util.DatabaseUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DXPEntitiesIngestionNanite {

	public void run() throws Exception {
		while (true) {
			List<Message<String>> messages = _messageSubscriber.pullMessages(
				100, String::valueOf);

			if (messages.isEmpty()) {
				break;
			}

			DatabaseUtil.createTables(
				_dataSource, ProjectIdThreadLocal.getProjectId());

			Stream<Message<String>> stream = messages.stream();

			stream.forEach(this::_processMessage);

			_acknowledgeMessages(messages);
		}
	}

	private void _acknowledgeMessages(List<Message<String>> messages) {
		Stream<Message<String>> stream = messages.stream();

		_messageSubscriber.sendAckIds(
			stream.map(
				Message::getAckId
			).collect(
				Collectors.toList()
			));
	}

	private String _generateBQExpandoValueId(
		Long columnId, Long classPK, Long dataSourceId, String projectId) {

		return DigestUtils.sha256Hex(
			String.join(
				"#", projectId, String.valueOf(dataSourceId),
				String.valueOf(columnId), String.valueOf(classPK)));
	}

	private String _generateDXPEntityId(
		Long dataSourceId, Long entityPK, String projectId) {

		return DigestUtils.sha256Hex(
			String.join(
				"#", projectId, String.valueOf(dataSourceId),
				String.valueOf(entityPK)));
	}

	private Long[] _getExpandoColumnIds(JSONArray expandoFieldsJSONArray) {
		List<Object> objects = expandoFieldsJSONArray.toList();

		Stream<Object> stream = objects.stream();

		return stream.map(
			object -> {
				Map<String, Object> map = (Map<String, Object>)object;

				return Long.valueOf(String.valueOf(map.get("columnId")));
			}
		).toArray(
			Long[]::new
		);
	}

	private String[] _getExpandoValueIds(
		Iterable<BQExpandoValue> bqExpandoValues) {

		List<BQExpandoValue> bqExpandoValuesList = IterableUtils.toList(
			bqExpandoValues);

		Stream<BQExpandoValue> stream = bqExpandoValuesList.stream();

		return stream.map(
			BQExpandoValue::getId
		).toArray(
			String[]::new
		);
	}

	private Set<BQExpandoValue> _getExpandoValues(
		Long classPK, BQExpandoValue.ClassType classType, Long dataSourceId,
		JSONArray expandoFieldsJSONArray, String projectId) {

		Set<BQExpandoValue> bqExpandoValues = new HashSet<>();

		expandoFieldsJSONArray.forEach(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				BQExpandoValue bqExpandoValue = new BQExpandoValue();

				bqExpandoValue.setClassPK(classPK);
				bqExpandoValue.setClassType(classType);
				bqExpandoValue.setColumnId(jsonObject.getLong("columnId"));

				bqExpandoValue.setId(
					_generateBQExpandoValueId(
						jsonObject.getLong("columnId"), classPK, dataSourceId,
						projectId));

				bqExpandoValue.setIsNew(
					_isNew(_bqExpandoValueRepository, bqExpandoValue.getId()));

				bqExpandoValue.setValue(jsonObject.getString("value"));

				bqExpandoValues.add(bqExpandoValue);
			});

		return bqExpandoValues;
	}

	private boolean _isNew(CrudRepository crudRepository, String id) {
		Optional<Object> optional = crudRepository.findById(id);

		return !optional.isPresent();
	}

	private Map<String, Object> _parseFields(JSONArray fieldsJSONArray) {
		Map<String, Object> fields = new HashMap<>();

		fieldsJSONArray.forEach(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				fields.put(
					jsonObject.getString("name"), jsonObject.get("value"));
			});

		return fields;
	}

	private void _processMessage(Message<String> message) {
		JSONObject jsonObject = new JSONObject(message.getObject());

		Long dataSourceId = jsonObject.getLong("dataSourceId");
		String projectId = jsonObject.getString("projectId");

		JSONObject dxpEntityJSONObject = jsonObject.getJSONObject("dxpEntity");

		String type = dxpEntityJSONObject.getString("type");

		Map<String, Object> fields = _parseFields(
			dxpEntityJSONObject.getJSONArray("fields"));

		if (StringUtils.equals(type, "expando-column")) {
			BQExpandoColumn bqExpandoColumn = _objectMapper.convertValue(
				fields, BQExpandoColumn.class);

			bqExpandoColumn.setId(
				_generateDXPEntityId(
					dataSourceId, bqExpandoColumn.getColumnId(), projectId));

			bqExpandoColumn.setIsNew(
				_isNew(_bqExpandoColumnRepository, bqExpandoColumn.getId()));

			_bqExpandoColumnRepository.save(bqExpandoColumn);
		}
		else if (StringUtils.equals(type, "group")) {
			BQGroup bqGroup = _objectMapper.convertValue(fields, BQGroup.class);

			bqGroup.setId(
				_generateDXPEntityId(
					dataSourceId, bqGroup.getGroupId(), projectId));

			bqGroup.setIsNew(_isNew(_bqGroupRepository, bqGroup.getId()));

			_bqGroupRepository.save(bqGroup);
		}
		else if (StringUtils.equals(type, "organization")) {
			BQOrganization bqOrganization = _objectMapper.convertValue(
				fields, BQOrganization.class);

			JSONArray expandFieldsJSONArray = dxpEntityJSONObject.getJSONArray(
				"expandoFields");

			bqOrganization.setExpandoColumnIds(
				_getExpandoColumnIds(expandFieldsJSONArray));

			Iterable<BQExpandoValue> bqExpandoValues =
				_bqExpandoValueRepository.saveAll(
					_getExpandoValues(
						bqOrganization.getOrganizationId(),
						BQExpandoValue.ClassType.ORGANIZATION, dataSourceId,
						expandFieldsJSONArray, projectId));

			bqOrganization.setExpandoValueIds(
				_getExpandoValueIds(bqExpandoValues));

			bqOrganization.setId(
				_generateDXPEntityId(
					dataSourceId, bqOrganization.getOrganizationId(),
					projectId));

			bqOrganization.setIsNew(
				_isNew(_bqOrganizationRepository, bqOrganization.getId()));

			_bqOrganizationRepository.save(bqOrganization);
		}
		else if (StringUtils.equals(type, "role")) {
			BQRole bqRole = _objectMapper.convertValue(fields, BQRole.class);

			bqRole.setId(
				_generateDXPEntityId(
					dataSourceId, bqRole.getRoleId(), projectId));

			bqRole.setIsNew(_isNew(_bqRoleRepository, bqRole.getId()));

			_bqRoleRepository.save(bqRole);
		}
		else if (StringUtils.equals(type, "team")) {
			BQTeam bqTeam = _objectMapper.convertValue(fields, BQTeam.class);

			bqTeam.setId(
				_generateDXPEntityId(
					dataSourceId, bqTeam.getTeamId(), projectId));

			bqTeam.setIsNew(_isNew(_bqTeamRepository, bqTeam.getId()));

			_bqTeamRepository.save(bqTeam);
		}
		else if (StringUtils.equals(type, "user")) {
			BQUser bqUser = _objectMapper.convertValue(fields, BQUser.class);

			JSONArray expandFieldsJSONArray = dxpEntityJSONObject.getJSONArray(
				"expandoFields");

			bqUser.setExpandoColumnIds(
				_getExpandoColumnIds(expandFieldsJSONArray));

			Iterable<BQExpandoValue> bqExpandoValues =
				_bqExpandoValueRepository.saveAll(
					_getExpandoValues(
						bqUser.getUserId(), BQExpandoValue.ClassType.INDIVIDUAL,
						dataSourceId, expandFieldsJSONArray, projectId));

			bqUser.setExpandoValueIds(_getExpandoValueIds(bqExpandoValues));

			bqUser.setId(
				_generateDXPEntityId(
					dataSourceId, bqUser.getUserId(), projectId));

			bqUser.setIsNew(_isNew(_bqUserRepository, bqUser.getId()));

			_bqUserRepository.save(bqUser);
		}
		else if (StringUtils.equals(type, "user-group")) {
			BQUserGroup bqUserGroup = _objectMapper.convertValue(
				fields, BQUserGroup.class);

			bqUserGroup.setId(
				_generateDXPEntityId(
					dataSourceId, bqUserGroup.getUserGroupId(), projectId));

			bqUserGroup.setIsNew(
				_isNew(_bqUserGroupRepository, bqUserGroup.getId()));

			_bqUserGroupRepository.save(bqUserGroup);
		}
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private BQGroupRepository _bqGroupRepository;

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private BQRoleRepository _bqRoleRepository;

	@Autowired
	private BQTeamRepository _bqTeamRepository;

	@Autowired
	private BQUserGroupRepository _bqUserGroupRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _dataSource;

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

}