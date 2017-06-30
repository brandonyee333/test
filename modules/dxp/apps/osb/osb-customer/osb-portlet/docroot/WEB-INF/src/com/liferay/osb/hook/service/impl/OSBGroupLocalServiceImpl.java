/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.NoSuchCorpEntryException;
import com.liferay.osb.NoSuchCorpGroupException;
import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpGroupLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceWrapper;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import java.util.Locale;

/**
 * @author Ryan Park
 */
public class OSBGroupLocalServiceImpl extends GroupLocalServiceWrapper {

	public OSBGroupLocalServiceImpl(GroupLocalService groupLocalService) {
		super(groupLocalService);
	}

	@Override
	public Group fetchFriendlyURLGroup(long companyId, String friendlyURL)
		throws SystemException {

		Group group = super.fetchFriendlyURLGroup(companyId, friendlyURL);

		if (group != null) {
			return group;
		}

		if (Validator.isNotNull(friendlyURL)) {
			friendlyURL = friendlyURL.substring(1);

			if (Validator.isNumber(friendlyURL)) {
				group = super.fetchFriendlyURLGroup(
					companyId, "/user." + friendlyURL);
			}
		}

		return group;
	}

	@Override
	public String getGroupDescriptiveName(Group group, Locale locale)
		throws PortalException, SystemException {

		if (!group.isOrganization()) {
			return super.getGroupDescriptiveName(group, locale);
		}

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				group.getOrganizationId());

		if (organization.getParentOrganizationId() ==
				OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID) {

			try {
				CorpEntry corpEntry =
					CorpEntryLocalServiceUtil.getOrganizationCorpEntry(
						organization.getOrganizationId());

				String name = corpEntry.getName();

				if (name.equals(OSBConstants.ORGANIZATION_LIFERAY_INC_NAME)) {
					name += " (Account)";
				}

				return name;
			}
			catch (NoSuchCorpEntryException nscee) {
			}
		}
		else if (organization.getParentOrganizationId() ==
					OSBConstants.ORGANIZATION_CORPORATION_GROUP_PARENT_ID) {

			try {
				CorpGroup corpGroup =
					CorpGroupLocalServiceUtil.getOrganizationCorpGroup(
						organization.getOrganizationId());

				return corpGroup.getName();
			}
			catch (NoSuchCorpGroupException nscge) {
			}
		}
		else if (organization.getParentOrganizationId() ==
					OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID) {

			try {
				CorpProject corpProject =
					CorpProjectLocalServiceUtil.getOrganizationCorpProject(
						organization.getOrganizationId());

				return corpProject.getName();
			}
			catch (NoSuchCorpProjectException nscpe) {
			}
		}

		return super.getGroupDescriptiveName(group, locale);
	}

	@Override
	public String getGroupDescriptiveName(long groupId, Locale locale)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		return getGroupDescriptiveName(group, locale);
	}

}