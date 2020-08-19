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

package com.liferay.osb.asah.dxp.extractor.bot.nanite;

import com.liferay.osb.asah.common.bot.exception.InterruptBotException;
import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorUserDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoSuppressionDog;
import com.liferay.osb.asah.common.json.JSONArrayPaginator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.dxp.extractor.bot.DXPExtractorConfigurableBot;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorRuntimeConfigurationImpl;
import com.liferay.osb.asah.dxp.extractor.dog.AuditEventDog;
import com.liferay.osb.asah.dxp.extractor.dog.GroupDog;
import com.liferay.osb.asah.dxp.extractor.dog.OrganizationDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserGroupDog;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Brian Wing Shun Chan
 */
public class DXPExtractorNanite implements Nanite {

	public DXPExtractorNanite(
		DXPExtractorConfiguration dxpExtractorConfiguration) {

		_dxpExtractorConfiguration = dxpExtractorConfiguration;

		_osbAsahDataSourceIdTermQueryBuilder = QueryBuilders.termQuery(
			"osbAsahDataSourceId",
			_dxpExtractorConfiguration.getDataSourceId());
	}

	@PostConstruct
	public void init() {
		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public void run() throws Exception {
		_throwNewInterruptBotException();

		if (Objects.equals(
				_dxpExtractorConfiguration.getDXPAuthenticationType(),
				"Token Authentication")) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping nanite because data source " +
						_dxpExtractorConfiguration.getDataSourceId() +
							" is using token authentication");
			}

			return;
		}

		if (!Objects.equals(
				_dxpExtractorConfiguration.getDataSourceState(),
				"CREDENTIALS_VALID") ||
			!Objects.equals(
				_dxpExtractorConfiguration.getDataSourceStatus(), "ACTIVE")) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping nanite because data source " +
						_dxpExtractorConfiguration.getDataSourceId() +
							" is not active");
			}

			return;
		}

		long lastSyncTime = System.currentTimeMillis();

		JSONObject lastSuccessfulAuditEventJSONObject =
			_getLastSuccessfulAuditEventJSONObject();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Last successful audit event " +
					lastSuccessfulAuditEventJSONObject + " for " +
						_dxpExtractorConfiguration.getDXPURL());
		}

		if (lastSuccessfulAuditEventJSONObject == null) {
			_runLogger.log(
				_dxpExtractorConfiguration.getDataSourceId(), this, "STARTED",
				_dxpRawElasticsearchInvoker, "initialRun", true,
				"totalOperations", _getUsersCount());

			try {
				_delete();

				_populate();

				_runLogger.log(
					_dxpExtractorConfiguration.getDataSourceId(), this,
					"COMPLETED", _dxpRawElasticsearchInvoker);
			}
			catch (Exception e) {
				_runLogger.log(
					_dxpExtractorConfiguration.getDataSourceId(), this,
					"FAILED", _dxpRawElasticsearchInvoker);

				throw e;
			}
		}
		else {
			_sync(lastSuccessfulAuditEventJSONObject);

			_syncGroups();
		}

		_updateOSBAsahMarker(lastSyncTime);
	}

	private void _addFaroAuditEvent(JSONObject auditEventJSONObject) {
		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equalsIgnoreCase("ASSIGN")) {
			eventType = "ADD";
		}
		else if (eventType.equalsIgnoreCase("UNASSIGN")) {
			eventType = "DELETE";
		}

		_dxpRawElasticsearchInvoker.add(
			"faro-audit-events",
			JSONUtil.put(
				"additionalInfo",
				auditEventJSONObject.getString("additionalInfo")
			).put(
				"dateCreated",
				DateUtil.toString(
					new Date(auditEventJSONObject.getLong("createDate")))
			).put(
				"eventType", eventType
			).put(
				"osbAsahDataSourceId",
				_dxpExtractorConfiguration.getDataSourceId()
			).put(
				"userId", auditEventJSONObject.getLong("classPK")
			));
	}

	private void _addOrganization(JSONObject auditEventJSONObject) {
		long organizationId = auditEventJSONObject.getLong("classPK");

		JSONObject organizationJSONObject =
			_organizationDog.getOrganizationJSONObject(
				_dxpExtractorConfiguration, organizationId);

		_setOSBAsahDataSourceId(organizationJSONObject);

		_dxpRawElasticsearchInvoker.add(
			"organizations", organizationJSONObject);
	}

	private void _addUser(JSONObject auditEventJSONObject) {
		long userId = auditEventJSONObject.getLong("classPK");

		JSONObject userJSONObject = _userDog.getUserJSONObject(
			_dxpExtractorConfiguration, userId);

		if (_faroInfoSuppressedUserDog.isSuppressed(
				userJSONObject.optString("emailAddress"), null)) {

			return;
		}

		_setOSBAsahDataSourceId(userJSONObject);

		_dxpRawElasticsearchInvoker.add("users", userJSONObject);

		_addFaroAuditEvent(auditEventJSONObject);
	}

	private void _addUserGroup(JSONObject auditEventJSONObject) {
		long userGroupId = auditEventJSONObject.getLong("classPK");

		JSONObject userGroupJSONObject = _userGroupDog.getUserGroupJSONObject(
			_dxpExtractorConfiguration, userGroupId);

		_setOSBAsahDataSourceId(userGroupJSONObject);

		_dxpRawElasticsearchInvoker.add("user-groups", userGroupJSONObject);
	}

	private JSONArray _buildAddFaroAuditEventsJSONArray(
		JSONArray usersJSONArray) {

		JSONArray faroAuditEventsJSONArray = new JSONArray();

		for (int i = 0; i < usersJSONArray.length(); i++) {
			JSONObject userJSONObject = usersJSONArray.getJSONObject(i);

			faroAuditEventsJSONArray.put(
				JSONUtil.put(
					"additionalInfo", String.valueOf(new JSONObject())
				).put(
					"dateCreated",
					DateUtil.toString(
						new Date(userJSONObject.getLong("createDate")))
				).put(
					"eventType", "ADD"
				).put(
					"osbAsahDataSourceId",
					_dxpExtractorConfiguration.getDataSourceId()
				).put(
					"userId", userJSONObject.getLong("userId")
				));
		}

		return faroAuditEventsJSONArray;
	}

	private void _delete() {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleting audit-events, organizations, user-groups, and users");
		}

		_dxpRawElasticsearchInvoker.delete(
			"audit-events", _osbAsahDataSourceIdTermQueryBuilder);
		_dxpRawElasticsearchInvoker.delete(
			"groups", _osbAsahDataSourceIdTermQueryBuilder);
		_dxpRawElasticsearchInvoker.delete(
			"organizations", _osbAsahDataSourceIdTermQueryBuilder);
		_dxpRawElasticsearchInvoker.delete(
			"user-groups", _osbAsahDataSourceIdTermQueryBuilder);
		_dxpRawElasticsearchInvoker.delete(
			"users", _osbAsahDataSourceIdTermQueryBuilder);
	}

	private void _deleteUser(JSONObject auditEventJSONObject) {
		long userId = auditEventJSONObject.getLong("classPK");

		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.fetch(
			"users",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery("userId", userId)
			));

		if (userJSONObject == null) {
			return;
		}

		_dxpRawElasticsearchInvoker.delete("users", userJSONObject);

		auditEventJSONObject.put(
			"additionalInfo",
			String.valueOf(
				JSONUtil.put(
					"email", userJSONObject.getString("emailAddress"))));

		_addFaroAuditEvent(auditEventJSONObject);
	}

	private JSONObject _fetchJSONObject(
		JSONObject auditEventJSONObject, String classPKFieldName,
		String collectionName) {

		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.fetch(
			collectionName,
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery(
					classPKFieldName, auditEventJSONObject.getString("classPK"))
			));

		if (userJSONObject == null) {
			return null;
		}

		_setOSBAsahDataSourceId(userJSONObject);

		return userJSONObject;
	}

	private JSONObject _getLastSuccessfulAuditEventJSONObject() {
		JSONObject osbAsahMarkerJSONObject = _dxpRawElasticsearchInvoker.fetch(
			"OSBAsahMarkers", _osbAsahDataSourceIdTermQueryBuilder);

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = new JSONObject();

			_setOSBAsahDataSourceId(osbAsahMarkerJSONObject);

			_dxpRawElasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject.optJSONObject(
			"lastSuccessfulAuditEvent");
	}

	private int _getUsersCount() {
		if (_dxpExtractorConfiguration.isSyncAllUsers()) {
			return _userDog.getCompanyUsersCount(
				_dxpExtractorConfiguration,
				_userDog.getCurrentUserCompanyId(_dxpExtractorConfiguration));
		}

		Set<Long> syncOrganizationIds =
			_dxpExtractorConfiguration.getSyncOrganizationIds();
		Set<Long> syncUserGroupIds =
			_dxpExtractorConfiguration.getSyncUserGroupIds();

		return _userDog.getOrganizationsAndUserGroupsUsersCount(
			_dxpExtractorConfiguration,
			ArrayUtils.toPrimitive(syncOrganizationIds.toArray(new Long[0])),
			ArrayUtils.toPrimitive(syncUserGroupIds.toArray(new Long[0])));
	}

	private boolean _isInScope(long userId) {
		if (_dxpExtractorConfiguration.isSyncAllUsers()) {
			return true;
		}

		try {
			Set<Long> syncOrganizationIds =
				_dxpExtractorConfiguration.getSyncOrganizationIds();

			JSONArray organizationsJSONArray =
				_organizationDog.getUserOrganizationsJSONArray(
					_dxpExtractorConfiguration, userId);

			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				JSONObject organizationJSONObject =
					organizationsJSONArray.getJSONObject(i);

				if (syncOrganizationIds.contains(
						organizationJSONObject.getLong("organizationId"))) {

					return true;
				}
			}

			Set<Long> syncUserGroupIds =
				_dxpExtractorConfiguration.getSyncUserGroupIds();

			JSONArray userGroupsJSONArray =
				_userGroupDog.getUserUserGroupsJSONArray(
					_dxpExtractorConfiguration, userId);

			for (int i = 0; i < userGroupsJSONArray.length(); i++) {
				JSONObject userGroupJSONObject =
					userGroupsJSONArray.getJSONObject(i);

				if (syncUserGroupIds.contains(
						userGroupJSONObject.getLong("userGroupId"))) {

					return true;
				}
			}

			return false;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to determine if user was still in scope, falling " +
						"back to secondary method",
					e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine if user was still in scope, falling " +
						"back to secondary method: " + e);
			}
		}

		JSONArray organizationsUsersJSONArray =
			_organizationDog.getOrganizationsUsersJSONArray(
				_dxpExtractorConfiguration, userId - 1, 1);

		for (int i = 0; i < organizationsUsersJSONArray.length(); i++) {
			JSONArray organizationUsersJSONArray =
				organizationsUsersJSONArray.getJSONArray(i);

			for (int j = 0; j < organizationUsersJSONArray.length(); j++) {
				JSONObject userJSONObject =
					organizationUsersJSONArray.getJSONObject(j);

				if (userJSONObject.getLong("userId") == userId) {
					return true;
				}
			}
		}

		JSONArray userGroupsUsersJSONArray =
			_userGroupDog.getUserGroupsUsersJSONArray(
				_dxpExtractorConfiguration, userId - 1, 1);

		for (int i = 0; i < userGroupsUsersJSONArray.length(); i++) {
			JSONArray userGroupUsersJSONArray =
				userGroupsUsersJSONArray.getJSONArray(i);

			for (int j = 0; j < userGroupUsersJSONArray.length(); j++) {
				JSONObject userJSONObject =
					userGroupUsersJSONArray.getJSONObject(j);

				if (userJSONObject.getLong("userId") == userId) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean _isShouldSyncAuditEvent(JSONObject auditEventJSONObject) {
		String className = auditEventJSONObject.getString("className");

		if (className.equals(DXPEntityType.CLASS_NAME_ORGANIZATION) ||
			className.equals(DXPEntityType.CLASS_NAME_USER_GROUP)) {

			return true;
		}

		if (!className.equals(DXPEntityType.CLASS_NAME_USER)) {
			return false;
		}

		if (_dxpExtractorConfiguration.isSyncAllUsers()) {
			String eventType = auditEventJSONObject.getString("eventType");

			if (eventType.equalsIgnoreCase("ADD") ||
				eventType.equalsIgnoreCase("DELETE") ||
				eventType.equalsIgnoreCase("UPDATE")) {

				return true;
			}

			return false;
		}

		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equalsIgnoreCase("ASSIGN") ||
			eventType.equalsIgnoreCase("UNASSIGN")) {

			JSONObject additionalInfoJSONObject = new JSONObject(
				auditEventJSONObject.getString("additionalInfo"));

			long organizationId = additionalInfoJSONObject.optLong(
				"organizationId");

			if (organizationId > 0) {
				Set<Long> organizationIds =
					_dxpExtractorConfiguration.getSyncOrganizationIds();

				return organizationIds.contains(organizationId);
			}

			long userGroupId = additionalInfoJSONObject.optLong("userGroupId");

			if (userGroupId > 0) {
				Set<Long> userGroupIds =
					_dxpExtractorConfiguration.getSyncUserGroupIds();

				return userGroupIds.contains(userGroupId);
			}
		}
		else if (eventType.equalsIgnoreCase("DELETE")) {
			return true;
		}
		else if (eventType.equalsIgnoreCase("UPDATE")) {
			return _dxpRawElasticsearchInvoker.exists(
				"users",
				BoolQueryBuilderUtil.filter(
					_osbAsahDataSourceIdTermQueryBuilder
				).filter(
					QueryBuilders.termQuery(
						"userId", auditEventJSONObject.getString("classPK"))
				));
		}

		return false;
	}

	private void _populate() throws Exception {

		// Do not rely on time for syncing because the DXP server clock may not
		// match our clock. Use the latest audit event as the last successful
		// audit event since we are populating data from scratch.

		if (_log.isInfoEnabled()) {
			_log.info("Populate");
		}

		long companyId = _userDog.getCurrentUserCompanyId(
			_dxpExtractorConfiguration);

		JSONObject latestAuditEventJSONObject =
			_auditEventDog.getLatestAuditEventJSONObject(
				_dxpExtractorConfiguration, companyId);

		if (_log.isInfoEnabled()) {
			_log.info("Latest audit event " + latestAuditEventJSONObject);
		}

		_populateCompany(companyId);

		if (latestAuditEventJSONObject != null) {
			_updateOSBAsahMarker(latestAuditEventJSONObject);

			_sync(latestAuditEventJSONObject);
		}
	}

	private void _populateCompany(long companyId) {
		if (_log.isInfoEnabled()) {
			_log.info("Populate company " + companyId);
		}

		_populateOrganizations(companyId, 0);
		_populateUserGroups(companyId, 0);

		_syncGroups();

		if (_dxpExtractorConfiguration.isSyncAllUsers()) {
			_populateUsers(companyId);
		}
		else {
			for (long organizationId :
					_dxpExtractorConfiguration.getSyncOrganizationIds()) {

				_populateOrganizationUsers(organizationId);
			}

			for (long userGroupId :
					_dxpExtractorConfiguration.getSyncUserGroupIds()) {

				_populateUserGroupUsers(userGroupId);
			}
		}
	}

	private void _populateOrganizations(
		long companyId, long parentOrganizationId) {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Populate organizations for company " + companyId +
					" and parent organization " + parentOrganizationId);
		}

		long gtOrganizationId = 0;

		while (true) {
			_throwNewInterruptBotException();

			JSONArray organizationsJSONArray =
				_organizationDog.getGtOrganizationsJSONArray(
					_dxpExtractorConfiguration, gtOrganizationId, companyId,
					parentOrganizationId, 50);

			if (organizationsJSONArray.length() == 0) {
				break;
			}

			_setOSBAsahDataSourceId(organizationsJSONArray);

			_dxpRawElasticsearchInvoker.add(
				"organizations", organizationsJSONArray);

			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				JSONObject organizationJSONObject =
					organizationsJSONArray.getJSONObject(i);

				long organizationId = organizationJSONObject.getLong(
					"organizationId");

				_populateOrganizations(companyId, organizationId);
			}

			JSONObject organizationJSONObject =
				organizationsJSONArray.getJSONObject(
					organizationsJSONArray.length() - 1);

			gtOrganizationId = organizationJSONObject.getLong("organizationId");
		}
	}

	private void _populateOrganizationUsers(long organizationId) {
		if (_log.isInfoEnabled()) {
			_log.info("Populate users for organization " + organizationId);
		}

		long gtUserId = 0;

		while (true) {
			_throwNewInterruptBotException();

			JSONArray usersJSONArray = _userDog.getGtOrganizationUsersJSONArray(
				_dxpExtractorConfiguration, gtUserId, organizationId, 50);

			if (usersJSONArray.length() == 0) {
				break;
			}

			JSONObject userJSONObject = usersJSONArray.getJSONObject(
				usersJSONArray.length() - 1);

			gtUserId = userJSONObject.getLong("userId");

			_removeDuplicateUsers(usersJSONArray);
			_removeSuppressedUsers(usersJSONArray);

			if (usersJSONArray.length() == 0) {
				continue;
			}

			_setOSBAsahDataSourceId(usersJSONArray);

			_dxpRawElasticsearchInvoker.add("users", usersJSONArray);

			_dxpRawElasticsearchInvoker.add(
				"faro-audit-events",
				_buildAddFaroAuditEventsJSONArray(usersJSONArray));
		}
	}

	private void _populateUserGroups(long companyId, long parentUserGroupId) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Populate user groups for company " + companyId +
					" and parent user group " + parentUserGroupId);
		}

		long gtUserGroupId = 0;

		while (true) {
			_throwNewInterruptBotException();

			JSONArray userGroupsJSONArray =
				_userGroupDog.getGtUserGroupsJSONArray(
					_dxpExtractorConfiguration, gtUserGroupId, companyId,
					parentUserGroupId, 50);

			if (userGroupsJSONArray.length() == 0) {
				break;
			}

			_setOSBAsahDataSourceId(userGroupsJSONArray);

			_dxpRawElasticsearchInvoker.add("user-groups", userGroupsJSONArray);

			for (int i = 0; i < userGroupsJSONArray.length(); i++) {
				JSONObject userGroupJSONObject =
					userGroupsJSONArray.getJSONObject(i);

				long userGroupId = userGroupJSONObject.getLong("userGroupId");

				_populateUserGroups(companyId, userGroupId);
			}

			JSONObject userGroupJSONObject = userGroupsJSONArray.getJSONObject(
				userGroupsJSONArray.length() - 1);

			gtUserGroupId = userGroupJSONObject.getLong("userGroupId");
		}
	}

	private void _populateUserGroupUsers(long userGroupId) {
		if (_log.isInfoEnabled()) {
			_log.info("Populate users for user group " + userGroupId);
		}

		long gtUserId = 0;

		while (true) {
			_throwNewInterruptBotException();

			JSONArray usersJSONArray = _userDog.getGtUserGroupUsersJSONArray(
				_dxpExtractorConfiguration, gtUserId, userGroupId, 50);

			if (usersJSONArray.length() == 0) {
				break;
			}

			JSONObject userJSONObject = usersJSONArray.getJSONObject(
				usersJSONArray.length() - 1);

			gtUserId = userJSONObject.getLong("userId");

			_removeDuplicateUsers(usersJSONArray);
			_removeSuppressedUsers(usersJSONArray);

			if (usersJSONArray.length() == 0) {
				continue;
			}

			_setOSBAsahDataSourceId(usersJSONArray);

			_dxpRawElasticsearchInvoker.add("users", usersJSONArray);

			_dxpRawElasticsearchInvoker.add(
				"faro-audit-events",
				_buildAddFaroAuditEventsJSONArray(usersJSONArray));
		}
	}

	private void _populateUsers(long companyId) {
		if (_log.isInfoEnabled()) {
			_log.info("Populate users for company " + companyId);
		}

		long gtUserId = 0;

		while (true) {
			_throwNewInterruptBotException();

			JSONArray usersJSONArray = _userDog.getGtCompanyUsersJSONArray(
				_dxpExtractorConfiguration, gtUserId, companyId, 50);

			_removeSuppressedUsers(usersJSONArray);

			if (usersJSONArray.length() == 0) {
				break;
			}

			_setOSBAsahDataSourceId(usersJSONArray);

			_dxpRawElasticsearchInvoker.add("users", usersJSONArray);

			_dxpRawElasticsearchInvoker.add(
				"faro-audit-events",
				_buildAddFaroAuditEventsJSONArray(usersJSONArray));

			JSONObject userJSONObject = usersJSONArray.getJSONObject(
				usersJSONArray.length() - 1);

			gtUserId = userJSONObject.getLong("userId");
		}
	}

	private void _removeDuplicateUsers(JSONArray usersJSONArray) {
		Iterator<Object> iterator = usersJSONArray.iterator();

		while (iterator.hasNext()) {
			JSONObject userJSONObject = (JSONObject)iterator.next();

			long userId = userJSONObject.getLong("userId");

			// Skip JavaParser, will fix

			if (_dxpRawElasticsearchInvoker.exists(
					"users",
					BoolQueryBuilderUtil.filter(
						_osbAsahDataSourceIdTermQueryBuilder
					).filter(
						QueryBuilders.termQuery("userId", userId)
					)
				)) {

				iterator.remove();
			}
		}
	}

	private void _removeSuppressedUsers(JSONArray usersJSONArray) {
		Iterator<Object> iterator = usersJSONArray.iterator();

		while (iterator.hasNext()) {
			JSONObject userJSONObject = (JSONObject)iterator.next();

			if (_faroInfoSuppressedUserDog.isSuppressed(
					userJSONObject.optString("emailAddress"), null)) {

				iterator.remove();
			}
		}
	}

	private void _setAuditEventAttributes(
		JSONObject auditEventJSONObject, JSONObject jsonObject) {

		JSONObject additionalInfoJSONObject = new JSONObject(
			auditEventJSONObject.getString("additionalInfo"));

		JSONArray attributesJSONArray = additionalInfoJSONObject.getJSONArray(
			"attributes");

		for (int i = 0; i < attributesJSONArray.length(); i++) {
			JSONObject attributeJSONObject = attributesJSONArray.getJSONObject(
				i);

			String name = attributeJSONObject.getString("name");

			if (jsonObject.has(name)) {
				jsonObject.put(name, attributeJSONObject.getString("newValue"));
			}
		}

		if (jsonObject.has("modifiedDate")) {
			jsonObject.put(
				"modifiedDate", auditEventJSONObject.get("createDate"));
		}
	}

	private void _setOSBAsahDataSourceId(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			jsonObject.put(
				"osbAsahDataSourceId",
				_dxpExtractorConfiguration.getDataSourceId());
		}
	}

	private void _setOSBAsahDataSourceId(JSONObject jsonObject) {
		jsonObject.put(
			"osbAsahDataSourceId",
			_dxpExtractorConfiguration.getDataSourceId());
	}

	private void _sync(JSONObject lastSuccessfulAuditEventJSONObject)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sync since audit event " + lastSuccessfulAuditEventJSONObject);
		}

		long companyId = _userDog.getCurrentUserCompanyId(
			_dxpExtractorConfiguration);

		long lastSuccessfulAuditEventId =
			lastSuccessfulAuditEventJSONObject.getLong("auditEventId");

		new JSONArrayPaginator() {

			@Override
			protected JSONArray paginate(int start, int end) {
				_throwNewInterruptBotException();

				JSONArray newAuditEventsJSONArray = new JSONArray();

				// Go back 5 seconds because audit events may have duplicate
				// create dates

				long createDate = lastSuccessfulAuditEventJSONObject.getLong(
					"createDate");

				createDate = createDate - 5000;

				JSONArray auditEventsJSONArray =
					_auditEventDog.getAuditEventsJSONArray(
						_dxpExtractorConfiguration, companyId,
						new Date(createDate), start, end);

				_setOSBAsahDataSourceId(auditEventsJSONArray);

				for (int i = 0; i < auditEventsJSONArray.length(); i++) {
					JSONObject auditEventJSONObject =
						auditEventsJSONArray.getJSONObject(i);

					// If the passed in audit event is newer than the iterated
					// audit event, remove the iterated audit event. We only
					// want newer audit events.

					long auditEventId = auditEventJSONObject.getLong(
						"auditEventId");

					// Skip JavaParser, will fix

					if ((auditEventId <= lastSuccessfulAuditEventId) ||
						!_isShouldSyncAuditEvent(auditEventJSONObject) ||
						_dxpRawElasticsearchInvoker.exists(
							"audit-events",
							BoolQueryBuilderUtil.filter(
								_osbAsahDataSourceIdTermQueryBuilder
							).filter(
								QueryBuilders.termQuery(
									"auditEventId",
									auditEventJSONObject.getLong(
										"auditEventId"))
									))) {

						if (_log.isDebugEnabled()) {
							_log.debug("Ignore audit event " + auditEventId);
						}
					}
					else {
						if (_log.isDebugEnabled()) {
							_log.debug(
								"Add audit event " + auditEventJSONObject);
						}

						newAuditEventsJSONArray.put(auditEventJSONObject);
					}
				}

				_dxpRawElasticsearchInvoker.add(
					"audit-events", newAuditEventsJSONArray);

				if (newAuditEventsJSONArray.length() > 0) {
					_updateOSBAsahMarker(
						newAuditEventsJSONArray.getJSONObject(0));
				}

				processedCount += newAuditEventsJSONArray.length();

				if (_log.isInfoEnabled()) {
					_log.info("Added " + processedCount + " audit-events");
				}

				return auditEventsJSONArray;
			}

		};

		_runLogger.log(
			_dxpExtractorConfiguration.getDataSourceId(), this, "STARTED",
			_dxpRawElasticsearchInvoker, "initialRun", false, "totalOperations",
			_dxpRawElasticsearchInvoker.count(
				"audit-events", _osbAsahDataSourceIdTermQueryBuilder));

		// AuditEventDog returned audit events in descending order. We must now
		// read and process audit events in ascending order.

		try {
			int processedCount = 0;

			while (true) {
				_throwNewInterruptBotException();

				JSONArray auditEventsJSONArray = new JSONArray(
					_dxpRawElasticsearchInvoker.get(
						"audit-events",
						searchSourceBuilder -> {
							searchSourceBuilder.query(
								BoolQueryBuilderUtil.filter(
									_osbAsahDataSourceIdTermQueryBuilder));
							searchSourceBuilder.size(500);
							searchSourceBuilder.sort(
								SortBuilderUtil.fieldSort("auditEventId"));
						}));

				if (auditEventsJSONArray.length() == 0) {
					break;
				}

				for (int i = 0; i < auditEventsJSONArray.length(); i++) {
					JSONObject auditEventsJSONObject =
						auditEventsJSONArray.getJSONObject(i);

					_syncAuditEvent(auditEventsJSONObject);
				}

				processedCount += auditEventsJSONArray.length();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Processed " + processedCount + " audit-events");
				}
			}

			_runLogger.log(
				_dxpExtractorConfiguration.getDataSourceId(), this, "COMPLETED",
				_dxpRawElasticsearchInvoker);

			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"DXPIndividualsNanite",
				JSONUtil.put(
					"dataSourceId", _dxpExtractorConfiguration.getDataSourceId()
				).put(
					"type", "audit-events"
				));
		}
		catch (Exception e) {
			_runLogger.log(
				_dxpExtractorConfiguration.getDataSourceId(), this, "FAILED",
				_dxpRawElasticsearchInvoker);

			throw e;
		}
	}

	private void _syncAuditEvent(JSONObject auditEventJSONObject)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Sync audit event " + auditEventJSONObject);
		}

		String className = auditEventJSONObject.getString("className");

		try {
			if (Objects.equals(
					className, DXPEntityType.CLASS_NAME_ORGANIZATION)) {

				_syncOrganization(auditEventJSONObject);
			}
			else if (Objects.equals(className, DXPEntityType.CLASS_NAME_USER)) {
				_syncUser(auditEventJSONObject);
			}
			else if (Objects.equals(
						className, DXPEntityType.CLASS_NAME_USER_GROUP)) {

				_syncUserGroup(auditEventJSONObject);
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info("Ignoring audit event for class " + className);
				}
			}
		}
		catch (HttpClientErrorException hcee) {
			if (hcee.getStatusCode() == HttpStatus.FORBIDDEN) {
				_userDog.getCurrentUserJSONObject(_dxpExtractorConfiguration);
			}
			else if (hcee.getStatusCode() != HttpStatus.NOT_FOUND) {
				throw hcee;
			}
		}

		_dxpRawElasticsearchInvoker.delete(
			"audit-events",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery(
					"auditEventId",
					auditEventJSONObject.getLong("auditEventId"))
			));
	}

	private void _syncGroups() {
		Set<Long> groupIds = _dxpExtractorConfiguration.getSyncGroupIds();

		if (groupIds == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Sync groups");
		}

		_throwNewInterruptBotException();

		JSONArray groupsJSONArray = null;

		try {
			groupsJSONArray = _groupDog.getGroupsJSONArray(
				_dxpExtractorConfiguration,
				ArrayUtils.toPrimitive(groupIds.toArray(new Long[0])));
		}
		catch (Exception e) {
			groupsJSONArray = new JSONArray();

			for (long groupId : groupIds) {
				try {
					JSONObject groupJSONObject = _groupDog.getGroupJSONObject(
						_dxpExtractorConfiguration, groupId);

					groupsJSONArray.put(groupJSONObject);
				}
				catch (HttpClientErrorException hcee) {
					if (hcee.getStatusCode() == HttpStatus.NOT_FOUND) {
						_updateDataSourceAnalyticsConfiguration(groupId);
					}
				}
			}
		}

		Map<String, JSONObject> groupJSONObjects = JSONUtil.toJSONObjectMap(
			groupsJSONArray, "groupId");

		_setOSBAsahDataSourceId(groupsJSONArray);

		JSONArray existingGroupsJSONArray = _dxpRawElasticsearchInvoker.get(
			"groups", _osbAsahDataSourceIdTermQueryBuilder);

		for (int i = 0; i < existingGroupsJSONArray.length(); i++) {
			JSONObject existingGroupJSONObject =
				existingGroupsJSONArray.getJSONObject(i);

			String groupId = existingGroupJSONObject.getString("groupId");

			JSONObject groupJSONObject = groupJSONObjects.get(groupId);

			if (groupJSONObject == null) {
				_dxpRawElasticsearchInvoker.delete(
					"groups", existingGroupJSONObject.getString("id"));
			}
			else if (!Objects.equals(
						existingGroupJSONObject.optString("descriptiveName"),
						groupJSONObject.optString("descriptiveName")) ||
					 !Objects.equals(
						 existingGroupJSONObject.optString("friendlyURL"),
						 groupJSONObject.optString("friendlyURL")) ||
					 !Objects.equals(
						 existingGroupJSONObject.optString("nameCurrentValue"),
						 groupJSONObject.optString("nameCurrentValue"))) {

				groupJSONObject.put(
					"id", existingGroupJSONObject.getString("id"));

				_dxpRawElasticsearchInvoker.update("groups", groupJSONObject);
			}

			groupJSONObjects.remove(groupId);
		}

		if (groupJSONObjects.isEmpty()) {
			return;
		}

		_dxpRawElasticsearchInvoker.add(
			"groups", new JSONArray(groupJSONObjects.values()));
	}

	private void _syncOrganization(JSONObject auditEventJSONObject)
		throws Exception {

		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equalsIgnoreCase("ADD")) {
			_addOrganization(auditEventJSONObject);
		}
		else if (eventType.equalsIgnoreCase("DELETE")) {
			long organizationId = auditEventJSONObject.getLong("classPK");

			_dxpRawElasticsearchInvoker.delete(
				"organizations",
				BoolQueryBuilderUtil.filter(
					_osbAsahDataSourceIdTermQueryBuilder
				).filter(
					QueryBuilders.termQuery("organizationId", organizationId)
				));

			_updateDataSourceContactsConfiguration(
				organizationId, "organizations");
		}
		else if (eventType.equalsIgnoreCase("UPDATE")) {
			JSONObject organizationJSONObject = _fetchJSONObject(
				auditEventJSONObject, "organizationId", "organizations");

			if (organizationJSONObject == null) {
				_addOrganization(auditEventJSONObject);

				return;
			}

			_setAuditEventAttributes(
				auditEventJSONObject, organizationJSONObject);

			_dxpRawElasticsearchInvoker.update(
				"organizations", organizationJSONObject);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Ignoring audit event type " + eventType);
			}
		}
	}

	private void _syncUser(JSONObject auditEventJSONObject) {
		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equalsIgnoreCase("ADD")) {
			_addUser(auditEventJSONObject);
		}
		else if (eventType.equalsIgnoreCase("ASSIGN")) {

			// Skip JavaParser, will fix

			if (!_dxpRawElasticsearchInvoker.exists(
					"users",
					BoolQueryBuilderUtil.filter(
						_osbAsahDataSourceIdTermQueryBuilder
					).filter(
						QueryBuilders.termQuery(
							"userId", auditEventJSONObject.getString("classPK"))
						))) {

				_addUser(auditEventJSONObject);
			}
		}
		else if (eventType.equalsIgnoreCase("DELETE")) {
			_deleteUser(auditEventJSONObject);
		}
		else if (eventType.equalsIgnoreCase("UNASSIGN")) {
			if (!_isInScope(auditEventJSONObject.getLong("classPK"))) {
				_deleteUser(auditEventJSONObject);
			}
		}
		else if (eventType.equalsIgnoreCase("UPDATE")) {
			JSONObject userJSONObject = _fetchJSONObject(
				auditEventJSONObject, "userId", "users");

			if (userJSONObject == null) {
				_addUser(auditEventJSONObject);

				return;
			}

			if (_faroInfoSuppressedUserDog.isSuppressed(
					userJSONObject.optString("emailAddress"), null)) {

				return;
			}

			_setAuditEventAttributes(
				auditEventJSONObject, userJSONObject.getJSONObject("contact"));

			_setAuditEventAttributes(auditEventJSONObject, userJSONObject);

			_dxpExtractorUserDog.processGenderField(userJSONObject);

			_dxpRawElasticsearchInvoker.update("users", userJSONObject);

			_addFaroAuditEvent(auditEventJSONObject);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Ignoring audit event type " + eventType);
			}
		}
	}

	private void _syncUserGroup(JSONObject auditEventJSONObject)
		throws Exception {

		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equalsIgnoreCase("ADD")) {
			_addUserGroup(auditEventJSONObject);
		}
		else if (eventType.equalsIgnoreCase("DELETE")) {
			long userGroupId = auditEventJSONObject.getLong("classPK");

			_dxpRawElasticsearchInvoker.delete(
				"user-groups",
				BoolQueryBuilderUtil.filter(
					_osbAsahDataSourceIdTermQueryBuilder
				).filter(
					QueryBuilders.termQuery("userGroupId", userGroupId)
				));

			_updateDataSourceContactsConfiguration(userGroupId, "userGroups");
		}
		else if (eventType.equalsIgnoreCase("UPDATE")) {
			JSONObject userGroupJSONObject = _fetchJSONObject(
				auditEventJSONObject, "userGroupId", "user-groups");

			if (userGroupJSONObject == null) {
				_addUserGroup(auditEventJSONObject);

				return;
			}

			_setAuditEventAttributes(auditEventJSONObject, userGroupJSONObject);

			_dxpRawElasticsearchInvoker.update(
				"user-groups", userGroupJSONObject);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Ignoring audit event type " + eventType);
			}
		}
	}

	private void _throwNewInterruptBotException() {
		if (_dxpExtractorConfigurableBot.isStop()) {
			throw new InterruptBotException();
		}
	}

	private void _updateDataSourceAnalyticsConfiguration(long classPK) {
		JSONObject dataSourceJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"data-sources",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"id", _dxpExtractorConfiguration.getDataSourceId())
			).filter(
				QueryBuilders.termQuery(
					"provider.analyticsConfiguration.sites.id", classPK)
			));

		if (dataSourceJSONObject == null) {
			return;
		}

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject analyticsConfigurationJSONObject =
			providerJSONObject.getJSONObject("analyticsConfiguration");

		JSONArray jsonArray = analyticsConfigurationJSONObject.getJSONArray(
			"sites");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (jsonObject.getLong("id") == classPK) {
				jsonArray.remove(i);

				break;
			}
		}

		_faroInfoElasticsearchInvoker.update(
			"data-sources", dataSourceJSONObject.getString("id"),
			dataSourceJSONObject);

		DXPExtractorRuntimeConfigurationImpl
			dxpExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)
					_dxpExtractorConfigurationManagerImpl.getConfiguration(
						_dxpExtractorConfiguration.getDataSourceId());

		if (dxpExtractorRuntimeConfigurationImpl != null) {
			dxpExtractorRuntimeConfigurationImpl.
				setAnalyticsConfigurationJSONObject(
					analyticsConfigurationJSONObject);
		}
	}

	private void _updateDataSourceContactsConfiguration(
			long classPK, String type)
		throws Exception {

		JSONObject dataSourceJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"data-sources",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"id", _dxpExtractorConfiguration.getDataSourceId())
			).filter(
				QueryBuilders.termQuery(
					"provider.contactsConfiguration." + type + ".id", classPK)
			));

		if (dataSourceJSONObject == null) {
			return;
		}

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.getJSONObject("contactsConfiguration");

		JSONArray jsonArray = contactsConfigurationJSONObject.getJSONArray(
			type);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (jsonObject.getLong("id") == classPK) {
				jsonArray.remove(i);

				break;
			}
		}

		_faroInfoElasticsearchInvoker.update(
			"data-sources", dataSourceJSONObject.getString("id"),
			dataSourceJSONObject);

		DXPExtractorRuntimeConfigurationImpl
			dxpExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)
					_dxpExtractorConfigurationManagerImpl.getConfiguration(
						_dxpExtractorConfiguration.getDataSourceId());

		if (dxpExtractorRuntimeConfigurationImpl != null) {
			dxpExtractorRuntimeConfigurationImpl.
				setContactsConfigurationJSONObject(
					contactsConfigurationJSONObject);
		}
	}

	private void _updateOSBAsahMarker(JSONObject auditEventJSONObject) {
		JSONObject osbAsahMarkerJSONObject = _dxpRawElasticsearchInvoker.fetch(
			"OSBAsahMarkers", _osbAsahDataSourceIdTermQueryBuilder);

		osbAsahMarkerJSONObject.put(
			"lastSuccessfulAuditEvent", auditEventJSONObject);

		_dxpRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		_dxpRawElasticsearchInvoker.delete(
			"audit-events",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery(
					"auditEventId",
					auditEventJSONObject.getLong("auditEventId"))
			));
	}

	private void _updateOSBAsahMarker(long lastSyncTime) {
		JSONObject osbAsahMarkerJSONObject = _dxpRawElasticsearchInvoker.fetch(
			"OSBAsahMarkers", _osbAsahDataSourceIdTermQueryBuilder);

		osbAsahMarkerJSONObject.put("lastSyncTime", lastSyncTime);

		_dxpRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);
	}

	private static final Log _log = LogFactory.getLog(DXPExtractorNanite.class);

	@Autowired
	private AuditEventDog _auditEventDog;

	@Autowired
	private DXPExtractorConfigurableBot _dxpExtractorConfigurableBot;

	private final DXPExtractorConfiguration _dxpExtractorConfiguration;

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private DXPExtractorUserDog _dxpExtractorUserDog;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private FaroInfoSuppressionDog _faroInfoSuppressedUserDog;

	@Autowired
	private GroupDog _groupDog;

	@Autowired
	private OrganizationDog _organizationDog;

	private final TermQueryBuilder _osbAsahDataSourceIdTermQueryBuilder;

	@Autowired
	private RunLogger _runLogger;

	@Autowired
	private UserDog _userDog;

	@Autowired
	private UserGroupDog _userGroupDog;

}