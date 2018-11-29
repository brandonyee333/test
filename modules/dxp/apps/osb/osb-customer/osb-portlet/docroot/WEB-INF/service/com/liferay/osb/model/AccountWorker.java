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
 * The extended model interface for the AccountWorker service. Represents a row in the &quot;OSB_AccountWorker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerModel
 * @see com.liferay.osb.model.impl.AccountWorkerImpl
 * @see com.liferay.osb.model.impl.AccountWorkerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.AccountWorkerImpl")
@ProviderType
public interface AccountWorker extends AccountWorkerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AccountWorkerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountWorker, Long> ACCOUNT_WORKER_ID_ACCESSOR =
		new Accessor<AccountWorker, Long>() {
			@Override
			public Long get(AccountWorker accountWorker) {
				return accountWorker.getAccountWorkerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountWorker> getTypeClass() {
				return AccountWorker.class;
			}
		};

	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getKey();

	public java.lang.String getRoleLabel();
}