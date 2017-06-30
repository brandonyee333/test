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
 * The extended model interface for the DeveloperEntry service. Represents a row in the &quot;OSB_DeveloperEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DeveloperEntryModel
 * @see com.liferay.osb.model.impl.DeveloperEntryImpl
 * @see com.liferay.osb.model.impl.DeveloperEntryModelImpl
 * @generated
 */
public interface DeveloperEntry extends DeveloperEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.DeveloperEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getCity();

	public com.liferay.portal.model.Country getCountry();

	public java.lang.String getCountryCode();

	public long getCountryId();

	public java.util.List<com.liferay.osb.model.AssetAttachment> getDocumentAssetAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public double getFatcaWithholdingPercentageValue()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getProfileURL(
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Region getRegion();

	public long getRegionId();

	public java.lang.String getStatusLabel();

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getStreet1();

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getStreet2();

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getStreet3();

	@com.liferay.portal.kernel.bean.AutoEscape()
	public java.lang.String getZip();

	public boolean isApproved();

	public boolean isCompany();

	public boolean isDomainStatusApproved();

	public boolean isLiferayInc();

	public boolean isSubscriptionApproved();

	public boolean isSubscriptionExpired();

	public boolean isUser();
}