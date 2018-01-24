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

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.model.TicketComment;
import com.liferay.osb.service.persistence.TicketCommentPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketCommentFinderBaseImpl extends BasePersistenceImpl<TicketComment> {
	public TicketCommentFinderBaseImpl() {
		setModelClass(TicketComment.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");
			dbColumnNames.put("settings", "settings_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getTicketCommentPersistence().getBadColumnNames();
	}

	/**
	 * Returns the ticket comment persistence.
	 *
	 * @return the ticket comment persistence
	 */
	public TicketCommentPersistence getTicketCommentPersistence() {
		return ticketCommentPersistence;
	}

	/**
	 * Sets the ticket comment persistence.
	 *
	 * @param ticketCommentPersistence the ticket comment persistence
	 */
	public void setTicketCommentPersistence(
		TicketCommentPersistence ticketCommentPersistence) {
		this.ticketCommentPersistence = ticketCommentPersistence;
	}

	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	private static final Log _log = LogFactoryUtil.getLog(TicketCommentFinderBaseImpl.class);
}