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

import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.persistence.SupportWorkerPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SupportWorkerFinderBaseImpl extends BasePersistenceImpl<SupportWorker> {
	/**
	 * Returns the support worker persistence.
	 *
	 * @return the support worker persistence
	 */
	public SupportWorkerPersistence getSupportWorkerPersistence() {
		return supportWorkerPersistence;
	}

	/**
	 * Sets the support worker persistence.
	 *
	 * @param supportWorkerPersistence the support worker persistence
	 */
	public void setSupportWorkerPersistence(
		SupportWorkerPersistence supportWorkerPersistence) {
		this.supportWorkerPersistence = supportWorkerPersistence;
	}

	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
}