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
 * The extended model interface for the PortalRelease service. Represents a row in the &quot;OSB_PortalRelease&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PortalReleaseModel
 * @see com.liferay.osb.model.impl.PortalReleaseImpl
 * @see com.liferay.osb.model.impl.PortalReleaseModelImpl
 * @generated
 */
public interface PortalRelease extends PortalReleaseModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.PortalReleaseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean hasMarketplaceSupport();

	public boolean hasOsgiSupport();

	public boolean hasPaclSupport();

	public boolean isEE();
}