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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.remote.koroneiki.KoroneikiRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class RemoteCorpProjectLocalServiceImpl
	extends RemoteCorpProjectLocalServiceBaseImpl {

	@Override
	public CorpProject addCorpProject(
			String dossieraAccountKey, String dossieraProjectKey,
			String salesforceProjectKey, String name)
		throws PortalException {

		String parentAccountKey = StringPool.BLANK;

		if (Validator.isNotNull(dossieraAccountKey)) {
			JSONObject jsonObject = KoroneikiRESTWebServiceUtil.getAccounts(
				"dossiera", "account", dossieraAccountKey);

			JSONArray jsonArray = jsonObject.getJSONArray("items");

			if ((jsonArray != null) && (jsonArray.length() > 0)) {
				JSONObject accountJSONObject = jsonArray.getJSONObject(0);

				parentAccountKey = accountJSONObject.getString("key");
			}
		}

		JSONObject jsonObject = KoroneikiRESTWebServiceUtil.postAccounts(
			parentAccountKey, name);

		String accountKey = jsonObject.getString("key");

		if (Validator.isNotNull(dossieraProjectKey)) {
			KoroneikiRESTWebServiceUtil.postAccountExternalLinks(
				accountKey, "dossiera", "project", dossieraProjectKey);
		}

		if (Validator.isNotNull(salesforceProjectKey)) {
			KoroneikiRESTWebServiceUtil.postAccountExternalLinks(
				accountKey, "salesforce", "project", salesforceProjectKey);
		}

		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setUuid(accountKey);

		return corpProject;
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		addCorpProjectUsers(corpProject.getUuid(), userIds);
	}

	@Override
	public void addCorpProjectUsers(String corpProjectUuid, long[] userIds)
		throws PortalException {

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			JSONObject jsonObject = KoroneikiRESTWebServiceUtil.getContacts(
				user.getUuid());

			if (jsonObject == null) {
				KoroneikiRESTWebServiceUtil.postContacts(
					user.getUuid(), user.getEmailAddress(), user.getFirstName(),
					user.getMiddleName(), user.getLastName(),
					user.getLanguageId());
			}

			KoroneikiRESTWebServiceUtil.putAccountContacts(
				corpProjectUuid, new String[] {user.getUuid()});
		}
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, long roleId)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		addUserCorpProjectRoles(corpProject.getUuid(), userIds, roleId);
	}

	@Override
	public void addUserCorpProjectRoles(
			String corpProjectUuid, long[] userIds, long roleId)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			KoroneikiRESTWebServiceUtil.putAccountContactRoles(
				corpProjectUuid, user.getUuid(), new String[] {role.getUuid()});
		}
	}

	@Override
	public void deleteCorpProject(String corpProjectUuid)
		throws PortalException {

		KoroneikiRESTWebServiceUtil.deleteAccounts(corpProjectUuid);
	}

	@Override
	public void updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		KoroneikiRESTWebServiceUtil.putAccounts(corpProject.getUuid(), name);
	}

}