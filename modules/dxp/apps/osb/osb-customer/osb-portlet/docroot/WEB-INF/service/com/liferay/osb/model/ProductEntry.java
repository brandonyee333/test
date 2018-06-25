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
 * The extended model interface for the ProductEntry service. Represents a row in the &quot;OSB_ProductEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryModel
 * @see com.liferay.osb.model.impl.ProductEntryImpl
 * @see com.liferay.osb.model.impl.ProductEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.ProductEntryImpl")
@ProviderType
public interface ProductEntry extends ProductEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.ProductEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProductEntry, Long> PRODUCT_ENTRY_ID_ACCESSOR = new Accessor<ProductEntry, Long>() {
			@Override
			public Long get(ProductEntry productEntry) {
				return productEntry.getProductEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ProductEntry> getTypeClass() {
				return ProductEntry.class;
			}
		};

	public java.util.List<com.liferay.portal.kernel.model.ListType> getAllVersionsListTypes();

	public java.lang.String[] getDossieraIdMappings();

	public java.lang.String getEnvironmentLabel();

	@com.liferay.portal.kernel.json.JSON()
	public java.util.List<LicenseEntry> getLicenseEntries();

	public java.lang.String getTypeLabel();

	public java.util.List<com.liferay.portal.kernel.model.ListType> getVersionsListTypes();

	public boolean isDeveloperTools();

	public boolean isDeviceDetection();

	public boolean isDigitalEnterprise();

	public boolean isEnterpriseSearchPremium();

	public boolean isEnterpriseSearchStandard();

	public boolean isExtendedPremiumSupport();

	public boolean isManagementTools();

	public boolean isMobileExperience();

	public boolean isPortal();

	public boolean isProductivityTools();

	public boolean isSocialOffice();

	public boolean isUnlimitedEnterpriseWide();
}