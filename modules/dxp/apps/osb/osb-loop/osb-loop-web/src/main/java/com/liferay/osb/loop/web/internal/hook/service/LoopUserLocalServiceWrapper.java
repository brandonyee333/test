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

package com.liferay.osb.loop.web.internal.hook.service;

import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopPersonLocalService;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Iterator;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wesley Gong
 * @author Timothy Bell
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class LoopUserLocalServiceWrapper extends UserLocalServiceWrapper {

	public LoopUserLocalServiceWrapper() {
		super(null);
	}

	public LoopUserLocalServiceWrapper(UserLocalService userLocalService) {
		super(userLocalService);
	}

	@Override
	public User addUser(
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, Locale locale, String firstName, String middleName,
			String lastName, long prefixId, long suffixId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			ServiceContext serviceContext)
		throws PortalException {

		roleIds = addLoopRoleId(companyId, roleIds);

		User user = super.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		_loopPersonLocalService.addLoopPerson(creatorUserId, user.getUserId());

		return user;
	}

	@Override
	public User addUserWithWorkflow(
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, long facebookId,
			String openId, Locale locale, String firstName, String middleName,
			String lastName, long prefixId, long suffixId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			ServiceContext serviceContext)
		throws PortalException {

		roleIds = addLoopRoleId(companyId, roleIds);

		User user = super.addUserWithWorkflow(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		_loopPersonLocalService.addLoopPerson(creatorUserId, user.getUserId());

		return user;
	}

	@Override
	public User updatePortrait(long userId, byte[] bytes)
		throws PortalException {

		try {
			LoopPerson loopPerson =
				_loopPersonLocalService.fetchLoopPersonByPersonUserId(userId);

			if (loopPerson == null) {
				return getUser(userId);
			}

			JSONObject imagePayloadJSONObject =
				JSONFactoryUtil.createJSONObject(loopPerson.getImagePayload());

			JSONObject profileImagePayload =
				imagePayloadJSONObject.getJSONObject("profileImagePayload");

			if (profileImagePayload != null) {
				return getUser(userId);
			}

			JSONObject profileImageJSONObject =
				LoopImageUtil.uploadImagePayload(
					userId, _portal.getClassNameId(LoopPerson.class), userId,
					"profile.jpg", bytes);

			profileImageJSONObject = LoopImageUtil.getKeywordPayloadJSONObject(
				profileImageJSONObject.toString(),
				_portal.getClassNameId(LoopPerson.class), userId,
				LoopWebConfigurationValues.IMAGE_PROFILE_TYPES,
				LoopWebConfigurationKeys.IMAGE_PROFILE_MAX_SIZE);

			imagePayloadJSONObject.put(
				"profileImagePayload", profileImageJSONObject);

			loopPerson.setImagePayload(imagePayloadJSONObject.toString());

			_loopPersonLocalService.updateLoopPerson(loopPerson);

			JSONObject fileEntryIdsJSONObject =
				profileImageJSONObject.getJSONObject("fileEntryIds");

			Iterator<String> iterator = fileEntryIdsJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				LoopImageUtil.addResourcePermission(
					loopPerson.getCompanyId(),
					fileEntryIdsJSONObject.getLong(key),
					LoopImageUtil.getRoleName(key));
			}
		}
		catch (Exception e) {
			_log.error("Unable to update profile image for " + userId);
		}

		return super.updatePortrait(userId, bytes);
	}

	@Override
	public User updateStatus(
			long userId, int status, ServiceContext serviceContext)
		throws PortalException {

		if ((status != WorkflowConstants.STATUS_APPROVED) &&
			(status != WorkflowConstants.STATUS_INACTIVE)) {

			return super.getUser(userId);
		}

		User user = super.updateStatus(userId, status, serviceContext);

		LoopPerson loopPerson =
			_loopPersonLocalService.fetchLoopPersonByPersonUserId(userId);

		if (loopPerson != null) {
			Indexer indexer = _indexerRegistry.nullSafeGetIndexer(
				LoopPerson.class);

			indexer.reindex(loopPerson);
		}

		return user;
	}

	protected long[] addLoopRoleId(long companyId, long[] roleIds)
		throws PortalException {

		Role role = _roleLocalService.getRole(
			companyId, LoopRoleConstants.LOOP_PERSON);

		if (!ArrayUtil.contains(roleIds, role.getRoleId())) {
			if (roleIds == null) {
				roleIds = new long[] {role.getRoleId()};
			}
			else {
				ArrayUtil.append(roleIds, role.getRoleId());
			}
		}

		return roleIds;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoopUserLocalServiceWrapper.class);

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private LoopPersonLocalService _loopPersonLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

}