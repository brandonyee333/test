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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SupportWorker service. Represents a row in the &quot;OSB_SupportWorker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerModel
 * @see com.liferay.osb.model.impl.SupportWorkerImpl
 * @see com.liferay.osb.model.impl.SupportWorkerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.SupportWorkerImpl")
@ProviderType
public interface SupportWorker extends SupportWorkerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.SupportWorkerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SupportWorker, Long> SUPPORT_WORKER_ID_ACCESSOR =
		new Accessor<SupportWorker, Long>() {
			@Override
			public Long get(SupportWorker supportWorker) {
				return supportWorker.getSupportWorkerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SupportWorker> getTypeClass() {
				return SupportWorker.class;
			}
		};

	public java.util.List<java.lang.Integer> getAccountTiers();

	public java.util.List<java.lang.Integer> getComponents();

	public java.lang.String getNotificationsLabel();

	public java.lang.String getRoleLabel();

	public java.util.List<java.lang.Integer> getSeverities();

	public SupportLabor getSupportLabor()
		throws com.liferay.portal.kernel.exception.PortalException;

	public SupportTeam getSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.Long getTimeUntilClose()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.Long getTimeUntilOpen()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isActive()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isAvailable()
		throws com.liferay.portal.kernel.exception.PortalException;
}