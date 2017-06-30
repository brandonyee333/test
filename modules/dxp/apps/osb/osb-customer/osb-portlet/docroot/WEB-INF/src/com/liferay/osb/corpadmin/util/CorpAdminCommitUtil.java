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

package com.liferay.osb.corpadmin.util;

import com.liferay.osb.ext.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpGroupLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.File;

import java.lang.reflect.Method;

import java.util.Locale;
import java.util.Map;

/**
 * @author Peter Shin
 * @author Joan Kim
 * @author Ryan Park
 * @author Haote Chou
 */
public class CorpAdminCommitUtil {

	public static CorpEntry updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, long[] corpGroupIds,
			String taxDocumentType, File taxDocumentFile,
			ServiceContext serviceContext)
		throws Exception {

		return _instance._updateCorpEntry(
			corpEntryId, name, descriptionMap, logoFile, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			dossieraAccountKey, corpGroupIds, taxDocumentType, taxDocumentFile,
			serviceContext);
	}

	public static CorpGroup updateCorpGroup(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			long[] corpEntryIds, ServiceContext serviceContext)
		throws Exception {

		return _instance._updateCorpGroup(
			corpGroupId, name, descriptionMap, logoFile, emailAddress, website,
			corpEntryIds, serviceContext);
	}

	public static CorpEntry updateStatus(
			long userId, long corpEntryId, int status, String statusMessage)
		throws Exception {

		return _instance._updateStatus(
			userId, corpEntryId, status, statusMessage);
	}

	private CorpEntry _updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, long[] corpGroupIds,
			String taxDocumentType, File taxDocumentFile,
			ServiceContext serviceContext)
		throws Exception {

		if (_updateCorpEntryWrapperMethod == null) {
			_updateCorpEntryWrapperMethod = getClass().getDeclaredMethod(
				"_updateCorpEntryWrapper",
				new Class<?>[] {
					long.class, String.class, Map.class, File.class,
					String.class, String.class, String.class, String.class,
					String.class, long.class, long.class, String.class,
					String.class, String.class, String.class, String.class,
					String.class, long[].class, String.class, File.class,
					ServiceContext.class
				});
		}

		return (CorpEntry)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateCorpEntryWrapperMethod,
			new Object[] {
				corpEntryId, name, descriptionMap, logoFile, street1, street2,
				street3, city, zip, regionId, countryId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website,
				dossieraAccountKey, corpGroupIds, taxDocumentType,
				taxDocumentFile, serviceContext
			},
			new String[] {"transactionAdvice"});
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	private CorpEntry _updateCorpEntryWrapper(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, long[] corpGroupIds,
			String taxDocumentType, File taxDocumentFile,
			ServiceContext serviceContext)
		throws Exception {

		CorpEntry corpEntry = null;
		boolean notificationEnabled = false;

		try {

			// Corp entry

			notificationEnabled = NotificationThreadLocal.isEnabled();

			NotificationThreadLocal.setEnabled(false);

			if (corpEntryId <= 0) {
				serviceContext.setAttribute(
					"status", WorkflowConstants.STATUS_PENDING);

				corpEntry = CorpEntryLocalServiceUtil.addCorpEntry(
					serviceContext.getUserId(), name, descriptionMap, logoFile,
					street1, street2, street3, city, zip, regionId, countryId,
					contactEmailAddress, profileEmailAddress, phoneNumber,
					faxNumber, website, dossieraAccountKey, serviceContext);

				UserLocalServiceUtil.addOrganizationUsers(
					corpEntry.getOrganizationId(),
					new long[] {serviceContext.getUserId()});
			}
			else {
				corpEntry = CorpEntryLocalServiceUtil.updateCorpEntry(
					corpEntryId, name, descriptionMap, logoFile, street1,
					street2, street3, city, zip, regionId, countryId,
					contactEmailAddress, profileEmailAddress, phoneNumber,
					faxNumber, website, dossieraAccountKey, serviceContext);
			}

			// Corp group

			CorpGroupLocalServiceUtil.setCorpEntryCorpGroups(
				corpEntry.getCorpEntryId(), corpGroupIds);

			// Developer entry

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
					corpEntry.getDossieraAccountKey());

			if (developerEntry != null) {
				DeveloperEntryLocalServiceUtil.updateCompanyDeveloperEntry(
					serviceContext.getUserId(),
					developerEntry.getDeveloperEntryId(), contactEmailAddress,
					name, phoneNumber, faxNumber, street1, street2, street3,
					city, zip, regionId, countryId, null, descriptionMap,
					profileEmailAddress, website, taxDocumentType,
					taxDocumentFile, serviceContext);

				DeveloperEntryLocalServiceUtil.updateDeveloperEntry(
					developerEntry.getDeveloperEntryId(), dossieraAccountKey);
			}

			// Site

			CorpEntryLocalServiceUtil.updateSite(
				corpEntry.getCorpEntryId(), WorkflowConstants.STATUS_APPROVED,
				true);

			// Status

			CorpEntryLocalServiceUtil.updateStatus(
				serviceContext.getUserId(), corpEntry.getCorpEntryId(),
				WorkflowConstants.STATUS_APPROVED, StringPool.BLANK);
		}
		finally {
			NotificationThreadLocal.setEnabled(notificationEnabled);
		}

		return corpEntry;
	}

	private CorpGroup _updateCorpGroup(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			long[] corpEntryIds, ServiceContext serviceContext)
		throws Exception {

		if (_updateCorpGroupWrapperMethod == null) {
			_updateCorpGroupWrapperMethod = getClass().getDeclaredMethod(
				"_updateCorpGroupWrapper",
				new Class<?>[] {
					long.class, String.class, Map.class, File.class,
					String.class, String.class, long[].class,
					ServiceContext.class
				});
		}

		return (CorpGroup)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateCorpGroupWrapperMethod,
			new Object[] {
				corpGroupId, name, descriptionMap, logoFile, emailAddress,
				website, corpEntryIds, serviceContext
			},
			new String[] {"transactionAdvice"});
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	private CorpGroup _updateCorpGroupWrapper(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			long[] corpEntryIds, ServiceContext serviceContext)
		throws Exception {

		CorpGroup corpGroup = null;
		boolean notificationEnabled = false;

		try {

			// Corp group

			notificationEnabled = NotificationThreadLocal.isEnabled();

			NotificationThreadLocal.setEnabled(false);

			if (corpGroupId <= 0) {
				corpGroup = CorpGroupLocalServiceUtil.addCorpGroup(
					serviceContext.getUserId(), name, descriptionMap, logoFile,
					emailAddress, website, serviceContext);
			}
			else {
				corpGroup = CorpGroupLocalServiceUtil.updateCorpGroup(
					corpGroupId, name, descriptionMap, logoFile, emailAddress,
					website, serviceContext);
			}

			// Corp entry

			CorpEntryLocalServiceUtil.setCorpGroupCorpEntries(
				corpGroup.getCorpGroupId(), corpEntryIds);

			// Site

			CorpGroupLocalServiceUtil.updateSite(
				corpGroup.getCorpGroupId(), true);
		}
		finally {
			NotificationThreadLocal.setEnabled(notificationEnabled);
		}

		return corpGroup;
	}

	private CorpEntry _updateStatus(
			long userId, long corpEntryId, int status, String statusMessage)
		throws Exception {

		if (_updateStatusWrapperMethod == null) {
			_updateStatusWrapperMethod = getClass().getDeclaredMethod(
				"_updateStatusWrapper",
				new Class<?>[] {
					long.class, long.class, int.class, String.class
				});
		}

		return (CorpEntry)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateStatusWrapperMethod,
			new Object[] {userId, corpEntryId, status, statusMessage},
			new String[] {"transactionAdvice"});
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	private CorpEntry _updateStatusWrapper(
			long userId, long corpEntryId, int status, String statusMessage)
		throws Exception {

		CorpEntry corpEntry = null;
		boolean notificationEnabled = false;

		try {

			// Corp entry

			notificationEnabled = NotificationThreadLocal.isEnabled();

			NotificationThreadLocal.setEnabled(false);

			corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(corpEntryId);

			corpEntry = CorpEntryLocalServiceUtil.updateStatus(
				userId, corpEntryId, status, statusMessage);

			// Developer entry

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
					corpEntry.getDossieraAccountKey());

			if ((developerEntry != null) && developerEntry.isApproved()) {
				if (status != WorkflowConstants.STATUS_APPROVED) {
					DeveloperEntryLocalServiceUtil.updateStatus(
						userId, developerEntry.getDeveloperEntryId(),
						WorkflowConstants.STATUS_EXPIRED, statusMessage);
				}
			}
		}
		finally {
			NotificationThreadLocal.setEnabled(notificationEnabled);
		}

		return corpEntry;
	}

	private static CorpAdminCommitUtil _instance = new CorpAdminCommitUtil();

	private Method _addCorpEntryWrapperMethod;
	private Method _updateCorpEntryWrapperMethod;
	private Method _updateCorpGroupWrapperMethod;
	private Method _updateStatusWrapperMethod;

}