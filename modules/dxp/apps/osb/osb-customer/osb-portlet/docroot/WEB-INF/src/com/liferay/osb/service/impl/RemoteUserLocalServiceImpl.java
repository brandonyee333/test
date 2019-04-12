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

package com.liferay.osb.service.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.osb.hook.model.impl.RemoteUserImpl;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteUserLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RemoteUserLocalServiceImpl extends RemoteUserLocalServiceBaseImpl {

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putOrganizationsUser(
				organization.getUuid(), user.getUuid());
		}
	}

	@Override
	public void addRoleUsers(long roleId, long[] userIds)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putRolesUser(role.getUuid(), user.getUuid());
		}
	}

	@Override
	public void deleteRoleUser(long roleId, long userId)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);
		User user = userLocalService.getUser(userId);

		WebRESTWebServiceUtil.deleteRolesUser(role.getUuid(), user.getUuid());
	}

	@Override
	public User fetchUserByEmailAddress(String emailAddress)
		throws PortalException {

		JSONObject jsonObject = WebRESTWebServiceUtil.getUsersEmailAddress(
			emailAddress);

		if (jsonObject == null) {
			return null;
		}

		return translate(jsonObject);
	}

	@Override
	public User getUserByUuid(String uuid) throws PortalException {
		JSONObject jsonObject = WebRESTWebServiceUtil.getUsers(uuid);

		return translate(jsonObject);
	}

	@Override
	public void synchronize(long userId) throws PortalException {

		// User

		User user = userLocalService.getUser(userId);

		User remoteUser = getUserByUuid(user.getUuid());

		Contact contact = user.getContact();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(contact.getBirthday());

		userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(),
			user.getFacebookId(), user.getOpenId(), true, null,
			remoteUser.getLanguageId(), remoteUser.getTimeZoneId(),
			user.getGreeting(), user.getComments(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(),
			contact.getPrefixId(), contact.getSuffixId(), contact.isMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(),
			remoteUser.getJobTitle(), null, null, null, null, null, null);

		// Expandos

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		// Organizations

		long[] newOrganizationIds = remoteUser.getOrganizationIds();

		long[] oldOrganizationIds = user.getOrganizationIds();

		for (long oldOrganizationId : oldOrganizationIds) {
			if (ArrayUtil.contains(newOrganizationIds, oldOrganizationId)) {
				continue;
			}

			Organization organization =
				organizationLocalService.getOrganization(oldOrganizationId);

			ExpandoBridge organizationExpandoBridge =
				organization.getExpandoBridge();

			boolean remote = (Boolean)organizationExpandoBridge.getAttribute(
				"remote", false);

			if (!remote) {
				continue;
			}

			userLocalService.unsetOrganizationUsers(
				oldOrganizationId, new long[] {user.getUserId()});
		}

		for (long newOrganizationId : newOrganizationIds) {
			if (!ArrayUtil.contains(oldOrganizationIds, newOrganizationId)) {
				userLocalService.addOrganizationUsers(
					newOrganizationId, new long[] {user.getUserId()});
			}
		}

		// Roles

		long[] newRoleIds = remoteUser.getRoleIds();

		long[] oldRoleIds = user.getRoleIds();

		for (long oldRoleId : oldRoleIds) {
			if (ArrayUtil.contains(newRoleIds, oldRoleId)) {
				continue;
			}

			Role role = roleLocalService.getRole(oldRoleId);

			ExpandoBridge roleExpandoBridge = role.getExpandoBridge();

			boolean remote = (Boolean)roleExpandoBridge.getAttribute(
				"remote", false);

			if (!remote) {
				continue;
			}

			userLocalService.unsetRoleUsers(
				oldRoleId, new long[] {user.getUserId()});
		}

		for (long newRoleId : newRoleIds) {
			if (!ArrayUtil.contains(oldRoleIds, newRoleId)) {
				userLocalService.addRoleUsers(
					newRoleId, new long[] {user.getUserId()});
			}
		}
	}

	@Override
	public User translate(JSONObject jsonObject) throws PortalException {
		User user = userLocalService.createUser(0);

		RemoteUserImpl remoteUser = new RemoteUserImpl(user);

		remoteUser.setCompanyId(OSBConstants.COMPANY_ID);
		remoteUser.setCreateDate(new Date(jsonObject.getLong("createDate")));
		remoteUser.setEmailAddress(jsonObject.getString("emailAddress"));

		JSONObject expandosJSONObject = jsonObject.getJSONObject("expandos");

		if (expandosJSONObject != null) {
			ExpandoBridge expandoBridge = remoteUser.getExpandoBridge();

			Iterator<String> iterator = expandosJSONObject.keys();

			while (iterator.hasNext()) {
				String expandoTableName = iterator.next();

				JSONObject expandoTableJSONObject =
					expandosJSONObject.getJSONObject(expandoTableName);

				Iterator<String> expandoTableIterator =
					expandoTableJSONObject.keys();

				while (expandoTableIterator.hasNext()) {
					String expandoColumnName = expandoTableIterator.next();

					ExpandoColumn expandoColumn =
						expandoColumnLocalService.getColumn(
							remoteUser.getCompanyId(), User.class.getName(),
							expandoTableName, expandoColumnName);

					if (expandoColumn == null) {
						continue;
					}

					String expandoValueData = expandoTableJSONObject.getString(
						expandoColumnName);

					ExpandoValue expandoValue =
						expandoValueLocalService.createExpandoValue(0);

					expandoValue.setColumn(expandoColumn);
					expandoValue.setData(expandoValueData);

					expandoBridge.setAttribute(
						expandoColumnName, expandoValue.getSerializable());
				}
			}
		}

		remoteUser.setFirstName(jsonObject.getString("firstName"));
		remoteUser.setLanguageId(jsonObject.getString("languageId"));
		remoteUser.setLastName(jsonObject.getString("lastName"));
		remoteUser.setMiddleName(jsonObject.getString("middleName"));

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"organizations");

		if (organizationsJSONArray != null) {
			List<Organization> organizations = new ArrayList<>();

			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				JSONObject organizationJSONObject =
					organizationsJSONArray.getJSONObject(i);

				Organization organization =
					organizationLocalService.
						fetchOrganizationByUuidAndCompanyId(
							organizationJSONObject.getString("uuid"),
							OSBConstants.COMPANY_ID);

				if (organization != null) {
					organizations.add(organization);
				}
			}

			remoteUser.setOrganizations(organizations);
		}

		JSONArray phonesJSONArray = jsonObject.getJSONArray("phones");

		if (phonesJSONArray != null) {
			List<Phone> phones = new ArrayList<>();

			long classNameId = classNameLocalService.getClassNameId(
				Contact.class);

			for (int i = 0; i < phonesJSONArray.length(); i++) {
				JSONObject phoneJSONObject = phonesJSONArray.getJSONObject(i);

				long phoneId = phoneJSONObject.getLong("phoneId");

				Phone phone = phoneLocalService.createPhone(phoneId);

				phone.setCompanyId(OSBConstants.COMPANY_ID);
				phone.setClassNameId(classNameId);
				phone.setExtension(phoneJSONObject.getString("extension"));
				phone.setNumber(phoneJSONObject.getString("number"));
				phone.setPrimary(phoneJSONObject.getBoolean("primary"));

				List<ListType> listTypes = listTypeLocalService.getListTypes(
					"com.liferay.portal.kernel.model.Contact.phone");

				for (ListType listType : listTypes) {
					String name = LanguageUtil.get(
						LocaleUtil.US, listType.getName());

					if (name.equals("Mobile Phone")) {
						name = "Mobile";
					}

					if (name.equals(phoneJSONObject.getString("type"))) {
						phone.setTypeId(listType.getListTypeId());

						break;
					}
				}

				phones.add(phone);
			}

			remoteUser.setPhones(phones);
		}

		JSONArray rolesJSONArray = jsonObject.getJSONArray("roles");

		if (rolesJSONArray != null) {
			List<Role> roles = new ArrayList<>();

			for (int i = 0; i < rolesJSONArray.length(); i++) {
				JSONObject roleJSONObject = rolesJSONArray.getJSONObject(i);

				Role role = roleLocalService.fetchRoleByUuidAndCompanyId(
					roleJSONObject.getString("uuid"), OSBConstants.COMPANY_ID);

				if (role != null) {
					roles.add(role);
				}
			}

			remoteUser.setRoles(roles);
		}

		remoteUser.setScreenName(jsonObject.getString("screenName"));
		remoteUser.setStatus(jsonObject.getInt("status"));
		remoteUser.setUuid(jsonObject.getString("uuid"));

		return remoteUser;
	}

	@Override
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.deleteOrganizationsUser(
				organization.getUuid(), user.getUuid());
		}
	}

	@Override
	public void updateUserExpandoValue(
			long userId, String expandoColumnName, Object data)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		ExpandoColumn expandoColumn = expandoColumnLocalService.getColumn(
			user.getCompanyId(), User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, expandoColumnName);

		String expandoValueData = getExpandoValueData(
			expandoColumn.getType(), data);

		WebRESTWebServiceUtil.putUsersExpandoValue(
			user.getUuid(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
			expandoColumnName, expandoValueData);
	}

	protected String getExpandoValueData(int type, Object data)
		throws PortalException {

		ExpandoValue expandoValue = expandoValueLocalService.createExpandoValue(
			0);

		if (type == ExpandoColumnConstants.BOOLEAN) {
			expandoValue.setBoolean((Boolean)data);
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			expandoValue.setBooleanArray((boolean[])data);
		}
		else if (type == ExpandoColumnConstants.DATE) {
			expandoValue.setDate((Date)data);
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			expandoValue.setDateArray((Date[])data);
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			expandoValue.setDouble((Double)data);
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			expandoValue.setDoubleArray((double[])data);
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			expandoValue.setFloat((Float)data);
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			expandoValue.setFloatArray((float[])data);
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			expandoValue.setInteger((Integer)data);
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			expandoValue.setIntegerArray((int[])data);
		}
		else if (type == ExpandoColumnConstants.LONG) {
			expandoValue.setLong((Long)data);
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			expandoValue.setLongArray((long[])data);
		}
		else if (type == ExpandoColumnConstants.NUMBER) {
			expandoValue.setNumber((Number)data);
		}
		else if (type == ExpandoColumnConstants.NUMBER_ARRAY) {
			expandoValue.setNumberArray((Number[])data);
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			expandoValue.setShort((Short)data);
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			expandoValue.setShortArray((short[])data);
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			expandoValue.setStringArray((String[])data);
		}
		else if (type == ExpandoColumnConstants.STRING_LOCALIZED) {
			expandoValue.setStringMap(
				(Map<Locale, String>)data, LocaleUtil.getDefault());
		}
		else {
			expandoValue.setString((String)data);
		}

		return expandoValue.getData();
	}

}