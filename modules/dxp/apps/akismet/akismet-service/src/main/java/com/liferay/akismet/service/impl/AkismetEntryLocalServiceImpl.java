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

package com.liferay.akismet.service.impl;

import com.liferay.akismet.model.AkismetEntry;
import com.liferay.akismet.service.base.AkismetEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

/**
 * @author Jamie Sammons
 */
public class AkismetEntryLocalServiceImpl
	extends AkismetEntryLocalServiceBaseImpl {

	public void deleteAkismetEntry(Date modifiedDate) {
		akismetEntryPersistence.removeByLtModifiedDate(modifiedDate);
	}

	public void deleteAkismetEntry(String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		akismetEntryPersistence.removeByC_C(classNameId, classPK);
	}

	public AkismetEntry fetchAkismetEntry(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return akismetEntryPersistence.fetchByC_C(classNameId, classPK);
	}

	public AkismetEntry updateAkismetEntry(
		String className, long classPK, String type, String permalink,
		String referrer, String userAgent, String userIP, String userURL) {

		long classNameId = classNameLocalService.getClassNameId(className);

		AkismetEntry akismetEntry = akismetEntryPersistence.fetchByC_C(
			classNameId, classPK);

		if (akismetEntry == null) {
			long akismetEntryId = counterLocalService.increment();

			akismetEntry = akismetEntryPersistence.create(akismetEntryId);
		}

		akismetEntry.setModifiedDate(new Date());
		akismetEntry.setClassNameId(classNameId);
		akismetEntry.setClassPK(classPK);
		akismetEntry.setType(type);
		akismetEntry.setPermalink(permalink);
		akismetEntry.setReferrer(referrer);
		akismetEntry.setUserAgent(userAgent);
		akismetEntry.setUserIP(userIP);
		akismetEntry.setUserURL(userURL);

		akismetEntryPersistence.update(akismetEntry);

		return akismetEntry;
	}

}