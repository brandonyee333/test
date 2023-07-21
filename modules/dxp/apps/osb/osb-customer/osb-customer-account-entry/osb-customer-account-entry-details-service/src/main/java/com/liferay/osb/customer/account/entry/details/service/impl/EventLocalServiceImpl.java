/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.service.impl;

import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.osb.customer.account.entry.details.service.base.EventLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

	public Event addEvent(
			long userId, Date occurDate, long accountEntryId, long classNameId,
			long classPK, int type, long typeClassNameId, long typeClassPK,
			String title, String summary, String additionalInfo)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long eventId = counterLocalService.increment();

		Event event = eventPersistence.create(eventId);

		event.setUserId(user.getUserId());
		event.setUserName(user.getFullName());
		event.setCreateDate(now);
		event.setOccurDate(occurDate);
		event.setAccountEntryId(accountEntryId);
		event.setClassNameId(classNameId);
		event.setClassPK(classPK);
		event.setType(type);
		event.setTypeClassNameId(typeClassNameId);
		event.setTypeClassPK(typeClassPK);
		event.setTitle(title);
		event.setSummary(summary);
		event.setAdditionalInfo(additionalInfo);

		return eventPersistence.update(event);
	}

	public void deleteEvents(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK) {

		eventPersistence.removeByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK);
	}

}