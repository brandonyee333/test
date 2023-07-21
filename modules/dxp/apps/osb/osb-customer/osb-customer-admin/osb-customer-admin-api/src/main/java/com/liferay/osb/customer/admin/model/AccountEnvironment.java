/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AccountEnvironment service. Represents a row in the &quot;OSB_AccountEnvironment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.admin.model.impl.AccountEnvironmentImpl"
)
@ProviderType
public interface AccountEnvironment
	extends AccountEnvironmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountEnvironment, Long>
		ACCOUNT_ENVIRONMENT_ID_ACCESSOR =
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

	public String getEnvASLabel();

	public String getEnvBrowserLabel();

	public String getEnvCommerceLabel();

	public String getEnvCSLabel();

	public String getEnvDBLabel();

	public String getEnvJVMLabel();

	public String getEnvLFRLabel();

	public String getEnvOSLabel();

	public java.util.List<String> getEnvSearchLabels();

	public String getSupportPhaseLabel();

}