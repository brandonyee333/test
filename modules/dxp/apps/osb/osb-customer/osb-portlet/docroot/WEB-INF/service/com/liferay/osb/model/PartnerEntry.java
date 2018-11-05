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
 * The extended model interface for the PartnerEntry service. Represents a row in the &quot;OSB_PartnerEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryModel
 * @see com.liferay.osb.model.impl.PartnerEntryImpl
 * @see com.liferay.osb.model.impl.PartnerEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.PartnerEntryImpl")
@ProviderType
public interface PartnerEntry extends PartnerEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.PartnerEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PartnerEntry, Long> PARTNER_ENTRY_ID_ACCESSOR = new Accessor<PartnerEntry, Long>() {
			@Override
			public Long get(PartnerEntry partnerEntry) {
				return partnerEntry.getPartnerEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PartnerEntry> getTypeClass() {
				return PartnerEntry.class;
			}
		};

	public java.util.List<AccountEntry> getAccountEntries();

	public java.util.List<PartnerEntry> getChildPartnerEntries(
		boolean recursive);

	public PartnerEntry getParentPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<AccountEntry> getPartnerManagedAccountEntries();

	public java.util.List<PartnerWorker> getPartnerWorkers();

	public java.lang.String getStatusLabel();

	public SupportRegion getSupportRegion();

	public long[] getSupportRegionIds();
}