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
 * The extended model interface for the ProductEntry service. Represents a row in the &quot;OSB_ProductEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryModel
 * @see com.liferay.osb.model.impl.ProductEntryImpl
 * @see com.liferay.osb.model.impl.ProductEntryModelImpl
 * @generated
 */
public interface ProductEntry extends ProductEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.ProductEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.portal.model.ListType> getAllVersionsListTypes()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String[] getDossieraIdMappings()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getEnvironmentLabel();

	public java.lang.String getLESADisplayName();

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getTypeLabel();

	public java.util.List<com.liferay.portal.model.ListType> getVersionsListTypes()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isDeveloperTools();

	public boolean isDeviceDetection();

	public boolean isDigitalEnterprise();

	public boolean isEnterpriseSearchPremium();

	public boolean isEnterpriseSearchStandard();

	public boolean isManagementTools();

	public boolean isMobileExperience();

	public boolean isPortal();

	public boolean isProductivityTools();

	public boolean isSocialOffice();

	public boolean isTicketComponentRequired();

	public boolean isUnlimitedEnterpriseWide();
}