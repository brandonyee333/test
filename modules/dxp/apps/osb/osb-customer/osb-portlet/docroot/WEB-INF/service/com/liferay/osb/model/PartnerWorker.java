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
 * The extended model interface for the PartnerWorker service. Represents a row in the &quot;OSB_PartnerWorker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerModel
 * @see com.liferay.osb.model.impl.PartnerWorkerImpl
 * @see com.liferay.osb.model.impl.PartnerWorkerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.PartnerWorkerImpl")
@ProviderType
public interface PartnerWorker extends PartnerWorkerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.PartnerWorkerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PartnerWorker, Long> PARTNER_WORKER_ID_ACCESSOR =
		new Accessor<PartnerWorker, Long>() {
			@Override
			public Long get(PartnerWorker partnerWorker) {
				return partnerWorker.getPartnerWorkerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PartnerWorker> getTypeClass() {
				return PartnerWorker.class;
			}
		};

	public PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getRoleLabel();
}