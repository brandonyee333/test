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
 * The extended model interface for the AccountEntry service. Represents a row in the &quot;OSB_AccountEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryModel
 * @see com.liferay.osb.model.impl.AccountEntryImpl
 * @see com.liferay.osb.model.impl.AccountEntryModelImpl
 * @generated
 */
public interface AccountEntry extends AccountEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AccountEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.Object clone();

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getEWSADossieraProjectKey()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getIndustryLabel();

	public java.lang.String[] getLanguageIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getStatusLabel();

	public long[] getSupportRegionIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getTypeLabel();

	public void setAddress(com.liferay.portal.model.Address address);

	public void setLanguageIds(java.lang.String[] languageIds);

	public void setSupportRegionIds(long[] supportRegionIds);
}