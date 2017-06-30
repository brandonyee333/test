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

package com.liferay.osb.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SupportWorker service. Represents a row in the &quot;OSB_SupportWorker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerModel
 * @see com.liferay.osb.model.impl.SupportWorkerImpl
 * @see com.liferay.osb.model.impl.SupportWorkerModelImpl
 * @generated
 */
public interface SupportWorker extends SupportWorkerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.SupportWorkerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<java.lang.Integer> getAccountTiers()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Integer> getComponents()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getNotificationsLabel();

	public java.lang.String getRoleLabel();

	public java.util.List<java.lang.Integer> getSeverities()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportLabor getSupportLabor()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportTeam getSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.Long getTimeUntilClose()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.Long getTimeUntilOpen()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isActive()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isAvailable()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}