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
 * The extended model interface for the AccountCustomer service. Represents a row in the &quot;OSB_AccountCustomer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerModel
 * @see com.liferay.osb.model.impl.AccountCustomerImpl
 * @see com.liferay.osb.model.impl.AccountCustomerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.AccountCustomerImpl")
@ProviderType
public interface AccountCustomer extends AccountCustomerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AccountCustomerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountCustomer, Long> ACCOUNT_CUSTOMER_ID_ACCESSOR =
		new Accessor<AccountCustomer, Long>() {
			@Override
			public Long get(AccountCustomer accountCustomer) {
				return accountCustomer.getAccountCustomerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountCustomer> getTypeClass() {
				return AccountCustomer.class;
			}
		};

	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getNotificationsLabel();

	public java.lang.String getRoleLabel();
}