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
 * The extended model interface for the SupportWorkerAccountTier service. Represents a row in the &quot;OSB_SupportWorkerAccountTier&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTierModel
 * @see com.liferay.osb.model.impl.SupportWorkerAccountTierImpl
 * @see com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.SupportWorkerAccountTierImpl")
@ProviderType
public interface SupportWorkerAccountTier extends SupportWorkerAccountTierModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.SupportWorkerAccountTierImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SupportWorkerAccountTier, Long> SUPPORT_WORKER_ACCOUNT_TIER_ID_ACCESSOR =
		new Accessor<SupportWorkerAccountTier, Long>() {
			@Override
			public Long get(SupportWorkerAccountTier supportWorkerAccountTier) {
				return supportWorkerAccountTier.getSupportWorkerAccountTierId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SupportWorkerAccountTier> getTypeClass() {
				return SupportWorkerAccountTier.class;
			}
		};
}