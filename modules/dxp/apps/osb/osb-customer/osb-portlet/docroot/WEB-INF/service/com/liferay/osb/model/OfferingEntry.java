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
 * The extended model interface for the OfferingEntry service. Represents a row in the &quot;OSB_OfferingEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryModel
 * @see com.liferay.osb.model.impl.OfferingEntryImpl
 * @see com.liferay.osb.model.impl.OfferingEntryModelImpl
 * @generated
 */
public interface OfferingEntry extends OfferingEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.OfferingEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@com.liferay.portal.kernel.json.JSON()
	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public java.util.Date getActualStartDate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getAvailableServers()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public java.lang.String getKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys()
		throws com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public int getLicenseKeysCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OfferingEntryGroup getOfferingEntryGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getSizingLabel();

	@com.liferay.portal.kernel.json.JSON()
	public java.util.Date getStartDate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getStatusLabel();

	public com.liferay.osb.model.SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public int getTicketEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getTypeLabel();
}