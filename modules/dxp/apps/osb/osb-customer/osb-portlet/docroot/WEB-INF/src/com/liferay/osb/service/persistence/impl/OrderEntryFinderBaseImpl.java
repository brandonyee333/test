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

import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.persistence.OrderEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OrderEntryFinderBaseImpl extends BasePersistenceImpl<OrderEntry> {
	@Override
	public Set<String> getBadColumnNames() {
		return getOrderEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the order entry persistence.
	 *
	 * @return the order entry persistence
	 */
	public OrderEntryPersistence getOrderEntryPersistence() {
		return orderEntryPersistence;
	}

	/**
	 * Sets the order entry persistence.
	 *
	 * @param orderEntryPersistence the order entry persistence
	 */
	public void setOrderEntryPersistence(
		OrderEntryPersistence orderEntryPersistence) {
		this.orderEntryPersistence = orderEntryPersistence;
	}

	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
}