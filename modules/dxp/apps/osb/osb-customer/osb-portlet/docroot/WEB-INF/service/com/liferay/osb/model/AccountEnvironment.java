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
 * The extended model interface for the AccountEnvironment service. Represents a row in the &quot;OSB_AccountEnvironment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentModel
 * @see com.liferay.osb.model.impl.AccountEnvironmentImpl
 * @see com.liferay.osb.model.impl.AccountEnvironmentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.AccountEnvironmentImpl")
@ProviderType
public interface AccountEnvironment extends AccountEnvironmentModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AccountEnvironmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountEnvironment, Long> ACCOUNT_ENVIRONMENT_ID_ACCESSOR =
		new Accessor<AccountEnvironment, Long>() {
			@Override
			public Long get(AccountEnvironment accountEnvironment) {
				return accountEnvironment.getAccountEnvironmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountEnvironment> getTypeClass() {
				return AccountEnvironment.class;
			}
		};

	public java.lang.String getEnvASLabel();

	public java.lang.String getEnvBrowserLabel();

	public java.lang.String getEnvCSLabel();

	public java.lang.String getEnvDBLabel();

	public java.lang.String getEnvJVMLabel();

	public java.lang.String getEnvLFRLabel();

	public java.lang.String getEnvOSLabel();

	public java.util.List<java.lang.String> getEnvSearchLabels();

	public java.lang.String getSupportPhaseLabel();
}