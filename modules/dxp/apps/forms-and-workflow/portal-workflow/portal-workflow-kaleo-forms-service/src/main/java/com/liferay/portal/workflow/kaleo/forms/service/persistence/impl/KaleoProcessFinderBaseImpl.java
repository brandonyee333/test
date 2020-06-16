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

package com.liferay.portal.workflow.kaleo.forms.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessPersistence;

import java.util.Set;

/**
 * @author Marcellus Tavares
 * @generated
 */
public class KaleoProcessFinderBaseImpl extends BasePersistenceImpl<KaleoProcess> {
	@Override
	public Set<String> getBadColumnNames() {
		return getKaleoProcessPersistence().getBadColumnNames();
	}

	/**
	 * Returns the kaleo process persistence.
	 *
	 * @return the kaleo process persistence
	 */
	public KaleoProcessPersistence getKaleoProcessPersistence() {
		return kaleoProcessPersistence;
	}

	/**
	 * Sets the kaleo process persistence.
	 *
	 * @param kaleoProcessPersistence the kaleo process persistence
	 */
	public void setKaleoProcessPersistence(
		KaleoProcessPersistence kaleoProcessPersistence) {
		this.kaleoProcessPersistence = kaleoProcessPersistence;
	}

	@BeanReference(type = KaleoProcessPersistence.class)
	protected KaleoProcessPersistence kaleoProcessPersistence;
}