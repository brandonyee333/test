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

import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.service.persistence.HolidayEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HolidayEntryFinderBaseImpl extends BasePersistenceImpl<HolidayEntry> {
	/**
	 * Returns the holiday entry persistence.
	 *
	 * @return the holiday entry persistence
	 */
	public HolidayEntryPersistence getHolidayEntryPersistence() {
		return holidayEntryPersistence;
	}

	/**
	 * Sets the holiday entry persistence.
	 *
	 * @param holidayEntryPersistence the holiday entry persistence
	 */
	public void setHolidayEntryPersistence(
		HolidayEntryPersistence holidayEntryPersistence) {
		this.holidayEntryPersistence = holidayEntryPersistence;
	}

	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
}