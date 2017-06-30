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
 * The extended model interface for the TicketAttachment service. Represents a row in the &quot;OSB_TicketAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentModel
 * @see com.liferay.osb.model.impl.TicketAttachmentImpl
 * @see com.liferay.osb.model.impl.TicketAttachmentModelImpl
 * @generated
 */
public interface TicketAttachment extends TicketAttachmentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketAttachmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.Set<java.lang.String> getAvailableFileRepositoryIdsSet();

	public int getContentLength();

	public java.io.File getFile();

	public java.lang.String getFilePath();

	public java.lang.String getKey();

	public com.liferay.osb.model.TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getTypeLabel();

	public java.lang.String getVisibilityLabel();

	public void setAvailableFileRepositoryIdsSet(
		java.util.Set<java.lang.String> availableFileRepositoryIds);

	public void setFile(java.io.File file);
}