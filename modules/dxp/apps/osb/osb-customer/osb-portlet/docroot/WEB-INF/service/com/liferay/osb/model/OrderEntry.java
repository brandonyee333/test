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
 * The extended model interface for the OrderEntry service. Represents a row in the &quot;OSB_OrderEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryModel
 * @see com.liferay.osb.model.impl.OrderEntryImpl
 * @see com.liferay.osb.model.impl.OrderEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.OrderEntryImpl")
@ProviderType
public interface OrderEntry extends OrderEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.OrderEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OrderEntry, Long> ORDER_ENTRY_ID_ACCESSOR = new Accessor<OrderEntry, Long>() {
			@Override
			public Long get(OrderEntry orderEntry) {
				return orderEntry.getOrderEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OrderEntry> getTypeClass() {
				return OrderEntry.class;
			}
		};

	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<OfferingEntry> getOfferingEntries();

	public java.lang.String getSalesforceOpportunityKey();

	public java.lang.String getStatusLabel();

	public void setOfferingEntries(
		java.util.List<OfferingEntry> offeringEntries);
}