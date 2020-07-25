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

package com.liferay.osb.customer.legacy.web.service.internal.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.legacy.web.service.internal.model.LegacyUserImpl;
import com.liferay.osb.customer.legacy.web.service.util.LegacyConverter;
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
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = LegacyConverter.class)
public class LegacyConverterImpl implements LegacyConverter {

	public User toUser(JSONObject jsonObject) throws PortalException {
		User user = _userLocalService.createUser(0);

		LegacyUserImpl legacyUser = new LegacyUserImpl(user);

		legacyUser.setCompanyId(OSBCustomerConstants.COMPANY_ID);
		legacyUser.setCreateDate(new Date(jsonObject.getLong("createDate")));
		legacyUser.setEmailAddress(jsonObject.getString("emailAddress"));

		JSONObject expandosJSONObject = jsonObject.getJSONObject("expandos");

		if (expandosJSONObject != null) {
			ExpandoBridge expandoBridge = legacyUser.getExpandoBridge();

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
						_expandoColumnLocalService.getColumn(
							legacyUser.getCompanyId(), User.class.getName(),
							expandoTableName, expandoColumnName);

					if (expandoColumn == null) {
						continue;
					}

					String expandoValueData = expandoTableJSONObject.getString(
						expandoColumnName);

					ExpandoValue expandoValue =
						_expandoValueLocalService.createExpandoValue(0);

					expandoValue.setColumn(expandoColumn);
					expandoValue.setData(expandoValueData);

					expandoBridge.setAttribute(
						expandoColumnName, expandoValue.getSerializable());
				}
			}
		}

		legacyUser.setFirstName(jsonObject.getString("firstName"));
		legacyUser.setLanguageId(jsonObject.getString("languageId"));
		legacyUser.setLastName(jsonObject.getString("lastName"));
		legacyUser.setMiddleName(jsonObject.getString("middleName"));

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"organizations");

		if (organizationsJSONArray != null) {
			List<Organization> organizations = new ArrayList<>();

			for (int i = 0; i < organizationsJSONArray.length(); i++) {
				JSONObject organizationJSONObject =
					organizationsJSONArray.getJSONObject(i);

				Organization organization =
					_organizationLocalService.
						fetchOrganizationByUuidAndCompanyId(
							organizationJSONObject.getString("uuid"),
							OSBCustomerConstants.COMPANY_ID);

				if (organization != null) {
					organizations.add(organization);
				}
			}

			legacyUser.setOrganizations(organizations);
		}

		JSONArray phonesJSONArray = jsonObject.getJSONArray("phones");

		if (phonesJSONArray != null) {
			List<Phone> phones = new ArrayList<>();

			long classNameId = _classNameLocalService.getClassNameId(
				Contact.class);

			for (int i = 0; i < phonesJSONArray.length(); i++) {
				JSONObject phoneJSONObject = phonesJSONArray.getJSONObject(i);

				long phoneId = phoneJSONObject.getLong("phoneId");

				Phone phone = _phoneLocalService.createPhone(phoneId);

				phone.setCompanyId(OSBCustomerConstants.COMPANY_ID);
				phone.setClassNameId(classNameId);
				phone.setNumber(phoneJSONObject.getString("number"));
				phone.setExtension(phoneJSONObject.getString("extension"));
				phone.setPrimary(phoneJSONObject.getBoolean("primary"));

				List<ListType> listTypes = _listTypeLocalService.getListTypes(
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

			legacyUser.setPhones(phones);
		}

		JSONArray rolesJSONArray = jsonObject.getJSONArray("roles");

		if (rolesJSONArray != null) {
			List<Role> roles = new ArrayList<>();

			for (int i = 0; i < rolesJSONArray.length(); i++) {
				JSONObject roleJSONObject = rolesJSONArray.getJSONObject(i);

				Role role = _roleLocalService.fetchRoleByUuidAndCompanyId(
					roleJSONObject.getString("uuid"),
					OSBCustomerConstants.COMPANY_ID);

				if (role != null) {
					roles.add(role);
				}
			}

			legacyUser.setRoles(roles);
		}

		legacyUser.setScreenName(jsonObject.getString("screenName"));
		legacyUser.setStatus(jsonObject.getInt("status"));
		legacyUser.setUuid(jsonObject.getString("uuid"));

		return legacyUser;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}