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

import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.persistence.PartnerWorkerPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PartnerWorkerFinderBaseImpl extends BasePersistenceImpl<PartnerWorker> {
	/**
	 * Returns the partner worker persistence.
	 *
	 * @return the partner worker persistence
	 */
	public PartnerWorkerPersistence getPartnerWorkerPersistence() {
		return partnerWorkerPersistence;
	}

	/**
	 * Sets the partner worker persistence.
	 *
	 * @param partnerWorkerPersistence the partner worker persistence
	 */
	public void setPartnerWorkerPersistence(
		PartnerWorkerPersistence partnerWorkerPersistence) {
		this.partnerWorkerPersistence = partnerWorkerPersistence;
	}

	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
}