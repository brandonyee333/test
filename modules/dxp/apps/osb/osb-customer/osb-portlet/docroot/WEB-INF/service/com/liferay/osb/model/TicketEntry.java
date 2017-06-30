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
 * The extended model interface for the TicketEntry service. Represents a row in the &quot;OSB_TicketEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryModel
 * @see com.liferay.osb.model.impl.TicketEntryImpl
 * @see com.liferay.osb.model.impl.TicketEntryModelImpl
 * @generated
 */
public interface TicketEntry extends TicketEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getAccountTier()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getComponentIcon();

	public java.lang.String getComponentLabel();

	public java.lang.String getDisplayId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getEnvAS();

	public java.lang.String getEnvASLabel();

	public int getEnvBrowser();

	public java.lang.String getEnvBrowserLabel();

	public int getEnvCS();

	public java.lang.String getEnvCSLabel();

	public int getEnvDB();

	public java.lang.String getEnvDBLabel();

	public int getEnvJVM();

	public java.lang.String getEnvJVMLabel();

	public int getEnvLFR();

	public java.lang.String getEnvLFRLabel();

	public java.lang.String getEnvName();

	public int getEnvOS();

	public java.lang.String getEnvOSLabel();

	public java.lang.String getEnvSearch();

	public java.util.List<java.lang.String> getEnvSearchLabels();

	public java.lang.String getEscalationLevelLabel();

	public java.lang.String getLanguageLabel();

	public com.liferay.osb.model.OfferingEntry getOfferingEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getResolutionLabel();

	public java.lang.String getSeverityLabel();

	public java.lang.String getStatusLabel();

	public java.lang.String getSubcomponentLabel();

	public java.lang.String getSupportPhaseLabel();

	public com.liferay.osb.model.SupportRegion getSupportRegion()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getTicketAttachmentsCount(int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.Long, java.lang.String> getTicketInformationFieldsMap()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformationList()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getWeightLabel();

	public double getWork()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasParticipant(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isPendingCustomer()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isPendingLiferay()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isPendingPartner()
		throws com.liferay.portal.kernel.exception.SystemException;
}