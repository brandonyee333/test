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

package com.liferay.osb.corpmembers.util;

import com.liferay.osb.ext.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.lang.reflect.Method;

import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class CorpMembersCommitUtil {

	public static CorpEntry updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String taxDocumentType, File taxDocumentFile,
			ServiceContext serviceContext)
		throws Exception {

		return _instance._updateCorpEntry(
			corpEntryId, name, descriptionMap, logoFile, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			taxDocumentType, taxDocumentFile, serviceContext);
	}

	private CorpEntry _updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
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
					String.class, File.class, ServiceContext.class
				});
		}

		return (CorpEntry)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateCorpEntryWrapperMethod,
			new Object[] {
				corpEntryId, name, descriptionMap, logoFile, street1, street2,
				street3, city, zip, regionId, countryId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website,
				taxDocumentType, taxDocumentFile, serviceContext
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
			String taxDocumentType, File taxDocumentFile,
			ServiceContext serviceContext)
		throws Exception {

		CorpEntry corpEntry = null;
		boolean notificationEnabled = false;

		try {

			// Corp entry

			notificationEnabled = NotificationThreadLocal.isEnabled();

			NotificationThreadLocal.setEnabled(false);

			corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(corpEntryId);

			corpEntry = CorpEntryServiceUtil.updateCorpEntry(
				corpEntryId, name, descriptionMap, logoFile, street1, street2,
				street3, city, zip, regionId, countryId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website,
				corpEntry.getDossieraAccountKey(), serviceContext);

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
			}
		}
		finally {
			NotificationThreadLocal.setEnabled(notificationEnabled);
		}

		return corpEntry;
	}

	private static CorpMembersCommitUtil _instance =
		new CorpMembersCommitUtil();

	private Method _updateCorpEntryWrapperMethod;

}