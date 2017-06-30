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

package com.liferay.osb.marketplaceadmin.util;

import com.liferay.osb.ext.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.File;

import java.lang.reflect.Method;

/**
 * @author Haote Chou
 */
public class MarketplaceAdminRegistrationUtil {

	public static DeveloperEntry updateDeveloperEntryStatus(
			long developerEntryId, String dossieraAccountKey, int status,
			String statusMessage)
		throws Exception {

		return _instance._updateDeveloperEntryStatus(
			developerEntryId, dossieraAccountKey, status, statusMessage);
	}

	private DeveloperEntry _updateDeveloperEntryStatus(
			long developerEntryId, String dossieraAccountKey, int status,
			String statusMessage)
		throws Exception {

		if (_updateDeveloperEntryStatusWrapperMethod == null) {
			_updateDeveloperEntryStatusWrapperMethod =
				getClass().getDeclaredMethod(
					"_updateDeveloperEntryStatusWrapper",
					new Class<?>[] {
						long.class, String.class, int.class, String.class
					});
		}

		return (DeveloperEntry)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateDeveloperEntryStatusWrapperMethod,
			new Object[] {
				developerEntryId, dossieraAccountKey, status, statusMessage
			},
			new String[] {"transactionAdvice"});
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	private DeveloperEntry _updateDeveloperEntryStatusWrapper(
			long developerEntryId, String dossieraAccountKey, int status,
			String statusMessage)
		throws Exception {

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			developerEntry.isCompany()) {

			DeveloperEntryServiceUtil.updateDeveloperEntry(
				developerEntryId, dossieraAccountKey);

			CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(
				dossieraAccountKey);

			if (corpEntry == null) {
				File profileLogoFile = null;

				try {
					AssetAttachment assetAttachment =
						AssetAttachmentLocalServiceUtil.getAssetAttachment(
							developerEntry.getProfileLogoId());

					profileLogoFile = FileUtil.createTempFile(
						FileUtil.getExtension(assetAttachment.getFileName()));

					FileUtil.write(
						profileLogoFile, assetAttachment.getFileAsStream());

					corpEntry = CorpEntryServiceUtil.addCorpEntry(
						developerEntry.getLegalEntityName(),
						developerEntry.getProfileDescriptionMap(),
						profileLogoFile, developerEntry.getStreet1(),
						developerEntry.getStreet2(),
						developerEntry.getStreet3(), developerEntry.getCity(),
						developerEntry.getZip(), developerEntry.getRegionId(),
						developerEntry.getCountryId(),
						developerEntry.getEmailAddress(),
						developerEntry.getProfileEmailAddress(),
						developerEntry.getPhoneNumber(),
						developerEntry.getFaxNumber(),
						developerEntry.getProfileWebsite(), dossieraAccountKey,
						new ServiceContext());

					UserLocalServiceUtil.addOrganizationUsers(
						corpEntry.getOrganizationId(),
						new long[] {developerEntry.getUserId()});

					UserGroupRoleLocalServiceUtil.addUserGroupRoles(
						developerEntry.getUserId(), corpEntry.getGroupId(),
						PortletPropsValues.CORP_ENTRY_ROLE_IDS_DEFAULT);
				}
				finally {
					FileUtil.delete(profileLogoFile);
				}
			}
		}

		return DeveloperEntryServiceUtil.updateStatus(
			developerEntryId, status, statusMessage);
	}

	private static MarketplaceAdminRegistrationUtil _instance =
		new MarketplaceAdminRegistrationUtil();

	private Method _updateDeveloperEntryStatusWrapperMethod;

}