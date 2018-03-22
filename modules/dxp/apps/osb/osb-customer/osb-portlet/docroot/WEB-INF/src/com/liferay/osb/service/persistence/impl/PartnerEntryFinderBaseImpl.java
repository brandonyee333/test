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

import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.persistence.PartnerEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PartnerEntryFinderBaseImpl extends BasePersistenceImpl<PartnerEntry> {
	@Override
	public Set<String> getBadColumnNames() {
		return getPartnerEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the partner entry persistence.
	 *
	 * @return the partner entry persistence
	 */
	public PartnerEntryPersistence getPartnerEntryPersistence() {
		return partnerEntryPersistence;
	}

	/**
	 * Sets the partner entry persistence.
	 *
	 * @param partnerEntryPersistence the partner entry persistence
	 */
	public void setPartnerEntryPersistence(
		PartnerEntryPersistence partnerEntryPersistence) {
		this.partnerEntryPersistence = partnerEntryPersistence;
	}

	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
}