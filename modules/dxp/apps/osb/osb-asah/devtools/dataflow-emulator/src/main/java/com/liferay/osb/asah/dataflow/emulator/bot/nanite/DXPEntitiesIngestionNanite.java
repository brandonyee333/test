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

import com.liferay.osb.asah.common.entity.BQAccountEntry;
import com.liferay.osb.asah.common.entity.BQAccountGroup;
import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.BQGroup;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.BQRole;
import com.liferay.osb.asah.common.entity.BQTeam;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.BQUserGroup;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.repository.BQAccountEntryRepository;
import com.liferay.osb.asah.common.repository.BQAccountGroupRepository;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.BQGroupRepository;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.BQRoleRepository;
import com.liferay.osb.asah.common.repository.BQTeamRepository;
import com.liferay.osb.asah.common.repository.BQUserGroupRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.dataflow.emulator.model.AnalyticsDeleteMessage;

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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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
		Long classPK, Long dataSourceId, String projectId) {

		return DigestUtils.sha256Hex(
			String.join(
				"#", projectId, String.valueOf(dataSourceId),
				String.valueOf(classPK)));
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
		Map<String, String> attributes = message.getAttributes();

		Long dataSourceId = MapUtil.getLong(attributes, "dataSourceId");
		String projectId = MapUtil.getString(attributes, "projectId");

		JSONObject jsonObject = new JSONObject(message.getObject());

		String type = jsonObject.getString("type");

		Map<String, Object> fields = _parseFields(
			jsonObject.getJSONArray("fields"));

		if (StringUtils.equals(
				type, "com.liferay.account.model.AccountEntry")) {

			BQAccountEntry accountEntry = _objectMapper.convertValue(
				fields, BQAccountEntry.class);

			accountEntry.setId(
				_generateDXPEntityId(
					accountEntry.getAccountEntryId(), dataSourceId, projectId));

			accountEntry.setIsNew(
				_isNew(_bqAccountEntryRepository, accountEntry.getId()));

			_bqAccountEntryRepository.save(accountEntry);
		}
		else if (StringUtils.equals(
					type, "com.liferay.account.model.AccountGroup")) {

			BQAccountGroup accountGroup = _objectMapper.convertValue(
				fields, BQAccountGroup.class);

			accountGroup.setId(
				_generateDXPEntityId(
					accountGroup.getAccountGroupId(), dataSourceId, projectId));

			accountGroup.setIsNew(
				_isNew(_bqAccountGroupRepository, accountGroup.getId()));

			_bqAccountGroupRepository.save(accountGroup);
		}
		else if (StringUtils.equals(
					type,
					"com.liferay.analytics.message.storage.model." +
						"AnalyticsDeleteMessage")) {

			try {
				AnalyticsDeleteMessage analyticsDeleteMessage =
					_objectMapper.convertValue(
						fields, AnalyticsDeleteMessage.class);

				CrudRepository crudRepository =
					(CrudRepository)_applicationContext.getBean(
						Class.forName(
							String.format(
								"com.liferay.osb.asah.common.repository." +
									"BQ%sRepository",
								StringUtils.substringAfterLast(
									analyticsDeleteMessage.getClassName(),
									"."))));

				crudRepository.deleteById(
					_generateDXPEntityId(
						analyticsDeleteMessage.getClassPK(), dataSourceId,
						projectId));
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
		else if (StringUtils.equals(
					type, "com.liferay.expando.kernel.model.ExpandoColumn")) {

			BQExpandoColumn bqExpandoColumn = _objectMapper.convertValue(
				fields, BQExpandoColumn.class);

			bqExpandoColumn.setDataSourceId(dataSourceId);
			bqExpandoColumn.setId(
				_generateDXPEntityId(
					bqExpandoColumn.getColumnId(), dataSourceId, projectId));
			bqExpandoColumn.setIsNew(
				_isNew(_bqExpandoColumnRepository, bqExpandoColumn.getId()));

			_bqExpandoColumnRepository.save(bqExpandoColumn);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.Group")) {

			BQGroup bqGroup = _objectMapper.convertValue(fields, BQGroup.class);

			bqGroup.setDataSourceId(dataSourceId);
			bqGroup.setId(
				_generateDXPEntityId(
					bqGroup.getGroupId(), dataSourceId, projectId));
			bqGroup.setIsNew(_isNew(_bqGroupRepository, bqGroup.getId()));

			_bqGroupRepository.save(bqGroup);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.Organization")) {

			BQOrganization bqOrganization = _objectMapper.convertValue(
				fields, BQOrganization.class);

			bqOrganization.setDataSourceId(dataSourceId);

			JSONArray expandFieldsJSONArray = jsonObject.optJSONArray(
				"expandoFields");

			if (expandFieldsJSONArray != null) {
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
			}

			bqOrganization.setId(
				_generateDXPEntityId(
					bqOrganization.getOrganizationId(), dataSourceId,
					projectId));
			bqOrganization.setIsNew(
				_isNew(_bqOrganizationRepository, bqOrganization.getId()));

			_bqOrganizationRepository.save(bqOrganization);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.Role")) {

			BQRole bqRole = _objectMapper.convertValue(fields, BQRole.class);

			bqRole.setDataSourceId(dataSourceId);
			bqRole.setId(
				_generateDXPEntityId(
					bqRole.getRoleId(), dataSourceId, projectId));
			bqRole.setIsNew(_isNew(_bqRoleRepository, bqRole.getId()));

			_bqRoleRepository.save(bqRole);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.Team")) {

			BQTeam bqTeam = _objectMapper.convertValue(fields, BQTeam.class);

			bqTeam.setDataSourceId(dataSourceId);
			bqTeam.setId(
				_generateDXPEntityId(
					bqTeam.getTeamId(), dataSourceId, projectId));
			bqTeam.setIsNew(_isNew(_bqTeamRepository, bqTeam.getId()));

			_bqTeamRepository.save(bqTeam);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.User")) {

			BQUser bqUser = _objectMapper.convertValue(fields, BQUser.class);

			bqUser.setDataSourceId(dataSourceId);

			JSONArray expandFieldsJSONArray = jsonObject.optJSONArray(
				"expandoFields");

			if (expandFieldsJSONArray != null) {
				bqUser.setExpandoColumnIds(
					_getExpandoColumnIds(expandFieldsJSONArray));

				Iterable<BQExpandoValue> bqExpandoValues =
					_bqExpandoValueRepository.saveAll(
						_getExpandoValues(
							bqUser.getDXPUserId(),
							BQExpandoValue.ClassType.INDIVIDUAL, dataSourceId,
							expandFieldsJSONArray, projectId));

				bqUser.setExpandoValueIds(_getExpandoValueIds(bqExpandoValues));
			}

			bqUser.setId(
				_generateDXPEntityId(
					bqUser.getDXPUserId(), dataSourceId, projectId));
			bqUser.setIsNew(_isNew(_bqUserRepository, bqUser.getId()));

			_bqUserRepository.save(bqUser);
		}
		else if (StringUtils.equals(
					type, "com.liferay.portal.kernel.model.UserGroup")) {

			BQUserGroup bqUserGroup = _objectMapper.convertValue(
				fields, BQUserGroup.class);

			bqUserGroup.setDataSourceId(dataSourceId);
			bqUserGroup.setId(
				_generateDXPEntityId(
					bqUserGroup.getUserGroupId(), dataSourceId, projectId));
			bqUserGroup.setIsNew(
				_isNew(_bqUserGroupRepository, bqUserGroup.getId()));

			_bqUserGroupRepository.save(bqUserGroup);
		}
	}

	private static final Log _log = LogFactory.getLog(
		DXPEntitiesIngestionNanite.class);

	@Autowired
	private ApplicationContext _applicationContext;

	@Autowired
	private BQAccountEntryRepository _bqAccountEntryRepository;

	@Autowired
	private BQAccountGroupRepository _bqAccountGroupRepository;

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

	@MessageSubscriber.Autowired(channel = Channel.DXP_ENTITIES_DEFAULT)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

}