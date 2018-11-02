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

}