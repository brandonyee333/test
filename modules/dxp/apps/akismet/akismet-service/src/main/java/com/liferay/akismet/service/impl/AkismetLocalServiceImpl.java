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

import com.liferay.akismet.model.Akismet;
import com.liferay.akismet.service.base.AkismetLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

/**
 * @author Jamie Sammons
 */
public class AkismetLocalServiceImpl extends AkismetLocalServiceBaseImpl {

	public void deleteAkismetData(Date modifiedDate) {
		akismetPersistence.removeByLtModifiedDate(modifiedDate);
	}

	public void deleteAkismetData(String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		akismetPersistence.removeByC_C(classNameId, classPK);
	}

	public Akismet fetchAkismetData(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return akismetPersistence.fetchByC_C(classNameId, classPK);
	}

	public Akismet updateAkismetData(
		String className, long classPK, String type, String permalink,
		String referrer, String userAgent, String userIP, String userURL) {

		long classNameId = classNameLocalService.getClassNameId(className);

		Akismet akismetData = akismetPersistence.fetchByC_C(
			classNameId, classPK);

		if (akismetData == null) {
			long akismetDataId = counterLocalService.increment();

			akismetData = akismetPersistence.create(akismetDataId);
		}

		akismetData.setModifiedDate(new Date());
		akismetData.setClassNameId(classNameId);
		akismetData.setClassPK(classPK);
		akismetData.setType(type);
		akismetData.setPermalink(permalink);
		akismetData.setReferrer(referrer);
		akismetData.setUserAgent(userAgent);
		akismetData.setUserIP(userIP);
		akismetData.setUserURL(userURL);

		akismetPersistence.update(akismetData);

		return akismetData;
	}

}