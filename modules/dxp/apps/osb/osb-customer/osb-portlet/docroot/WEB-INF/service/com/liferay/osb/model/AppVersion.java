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
 * The extended model interface for the AppVersion service. Represents a row in the &quot;OSB_AppVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AppVersionModel
 * @see com.liferay.osb.model.impl.AppVersionImpl
 * @see com.liferay.osb.model.impl.AppVersionModelImpl
 * @generated
 */
public interface AppVersion extends AppVersionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AppVersionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.osb.model.AppEntry getAppEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ContractEntry getContractEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getDefaultLanguageId();

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getDeveloperEntryName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getLicenseType()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getProductTypeLabel();

	public java.lang.String getStatusLabel();

	public boolean hasCustomOrderWorkflow();

	public boolean hasTrialLicense()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isAddResourcesImporter()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isApproved();

	public boolean isDeveloperEntryCorpEntry()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isDeveloperEntryLiferayInc()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isDeveloperEntryUser()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isFree()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isLatestApproved()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isNewRelease();

	public boolean isPending();

	public boolean isSOEEPlugin();
}