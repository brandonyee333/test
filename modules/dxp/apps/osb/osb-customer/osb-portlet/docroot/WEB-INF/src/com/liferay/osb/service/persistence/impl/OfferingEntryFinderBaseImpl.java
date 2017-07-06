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

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.persistence.OfferingEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OfferingEntryFinderBaseImpl extends BasePersistenceImpl<OfferingEntry> {
	public OfferingEntryFinderBaseImpl() {
		setModelClass(OfferingEntry.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

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
		return getOfferingEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the offering entry persistence.
	 *
	 * @return the offering entry persistence
	 */
	public OfferingEntryPersistence getOfferingEntryPersistence() {
		return offeringEntryPersistence;
	}

	/**
	 * Sets the offering entry persistence.
	 *
	 * @param offeringEntryPersistence the offering entry persistence
	 */
	public void setOfferingEntryPersistence(
		OfferingEntryPersistence offeringEntryPersistence) {
		this.offeringEntryPersistence = offeringEntryPersistence;
	}

	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	private static final Log _log = LogFactoryUtil.getLog(OfferingEntryFinderBaseImpl.class);
}