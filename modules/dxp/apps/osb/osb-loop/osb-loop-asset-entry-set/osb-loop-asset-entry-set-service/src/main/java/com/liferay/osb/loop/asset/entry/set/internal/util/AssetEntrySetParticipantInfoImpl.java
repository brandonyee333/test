/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.internal.util;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfo;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
@Component(
	immediate = true, property = "service.ranking:Integer=" + Integer.MIN_VALUE,
	service = AssetEntrySetParticipantInfo.class
)
public class AssetEntrySetParticipantInfoImpl
	implements AssetEntrySetParticipantInfo {

	public JSONArray getAssetTagsJSONArray(long userId, String[] assetTagNames)
		throws PortalException {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		User user = _userLocalService.getUser(userId);

		Group group = _groupLocalService.getCompanyGroup(user.getCompanyId());

		_assetTagLocalService.checkTags(userId, group, assetTagNames);

		for (String assetTagName : assetTagNames) {
			AssetTag assetTag = _assetTagLocalService.getTag(
				group.getGroupId(), assetTagName);

			if (assetTag == null) {
				throw new SystemException(
					"Asset tag does not exist for name " + assetTagName);
			}

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			jsonObject.put(
				"entityClassNameId", _portal.getClassNameId(AssetTag.class));
			jsonObject.put("entityClassPK", assetTag.getTagId());
			jsonObject.put("name", assetTagName);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(
		long userId) {

		return new ObjectValuePair<>(
			_portal.getClassNameId(User.class), userId);
	}

	public String[] getMembershipSearchTerms(long userId) {
		return new String[0];
	}

	public String getParticipantName(long classNameId, long classPK)
		throws PortalException {

		if (classNameId != _portal.getClassNameId(User.class)) {
			return StringPool.BLANK;
		}

		User user = _userLocalService.getUser(classPK);

		return user.getFullName();
	}

	public String getSearchTerm(long classNameId, long classPK) {
		return StringPool.BLANK;
	}

	public boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		if (classNameId != _portal.getClassNameId(User.class)) {
			return false;
		}

		if (sharedToClassNameId == _portal.getClassNameId(User.class)) {
			if (classPK == sharedToClassPK) {
				return true;
			}

			return false;
		}

		try {
			if (classNameId == _portal.getClassNameId(Group.class)) {
				return _groupLocalService.hasUserGroup(
					classPK, sharedToClassPK);
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}