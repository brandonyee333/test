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
 * The extended model interface for the AppEntry service. Represents a row in the &quot;OSB_AppEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryModel
 * @see com.liferay.osb.model.impl.AppEntryImpl
 * @see com.liferay.osb.model.impl.AppEntryModelImpl
 * @generated
 */
public interface AppEntry extends AppEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.AppEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.osb.model.AppVersion getActionableAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppVersion getApprovedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getAssetCategories()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long[] getAssetCategoryIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long[] getAssetListIds()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String[] getAssetTagNames()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long[] getAvailableCountryIds()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String[] getCompatibilities()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getDeveloperEntryName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppVersion getLatestAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getLicenseLifetimeLabel();

	public java.lang.String getLicenseTypeLabel();

	public java.lang.String getStatusLabel();

	public com.liferay.osb.model.AppVersion getUnreleasedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasAvailableCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasCustomOrderWorkflow();

	public boolean hasMultipleNewVersions()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasUnreleasedAppVersion()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isApproved();

	public boolean isCotermRequired();

	public boolean isDeveloperEntryCorpEntry()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isDeveloperEntryLiferayInc()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isDeveloperEntryUser()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isFirstSubmission()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isFree();

	public boolean isLiferayEEPlugin()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isSOEEPlugin();

	public boolean isStatusByDeveloper()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}