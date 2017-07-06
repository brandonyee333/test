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
 * The extended model interface for the TicketEntry service. Represents a row in the &quot;OSB_TicketEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryModel
 * @see com.liferay.osb.model.impl.TicketEntryImpl
 * @see com.liferay.osb.model.impl.TicketEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.TicketEntryImpl")
@ProviderType
public interface TicketEntry extends TicketEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketEntry, Long> TICKET_ENTRY_ID_ACCESSOR = new Accessor<TicketEntry, Long>() {
			@Override
			public Long get(TicketEntry ticketEntry) {
				return ticketEntry.getTicketEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketEntry> getTypeClass() {
				return TicketEntry.class;
			}
		};

	public AccountEntry getAccountEntry()
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

	public java.lang.String getEnvASLabel();

	public java.lang.String getEnvBrowserLabel();

	public java.lang.String getEnvCSLabel();

	public java.lang.String getEnvDBLabel();

	public java.lang.String getEnvJVMLabel();

	public java.lang.String getEnvLFRLabel();

	public java.lang.String getEnvOSLabel();

	public java.util.List<java.lang.String> getEnvSearchLabels();

	public java.lang.String getEscalationLevelLabel();

	public java.lang.String getLanguageLabel();

	public OfferingEntry getOfferingEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public OrderEntry getOrderEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getResolutionLabel();

	public java.lang.String getSeverityLabel();

	public java.lang.String getStatusLabel();

	public java.lang.String getSubcomponentLabel();

	public java.lang.String getSupportPhaseLabel();

	public SupportRegion getSupportRegion()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<TicketAttachment> getTicketAttachments()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<TicketAttachment> getTicketAttachments(int[] types,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getTicketAttachmentsCount(int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.Map<java.lang.Long, java.lang.String> getTicketInformationFieldsMap()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<TicketInformation> getTicketInformationList()
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