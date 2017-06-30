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
 * The extended model interface for the MarketingEvent service. Represents a row in the &quot;OSB_MarketingEvent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventModel
 * @see com.liferay.osb.model.impl.MarketingEventImpl
 * @see com.liferay.osb.model.impl.MarketingEventModelImpl
 * @generated
 */
public interface MarketingEvent extends MarketingEventModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.MarketingEventImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.model.Address getAddress();

	public com.liferay.portal.kernel.repository.model.FileEntry getImageFileEntry();

	public com.liferay.portal.kernel.repository.model.FileEntry getSlidesFileEntry();

	public java.lang.String getVideoURL(java.lang.String extension);

	public boolean hasVideo();

	public boolean isTypeCommunity();

	public boolean isTypeConference();

	public boolean isTypeIndustry();

	public boolean isTypePartner();

	public boolean isTypeRoadshow();

	public boolean isTypeWebinar();
}