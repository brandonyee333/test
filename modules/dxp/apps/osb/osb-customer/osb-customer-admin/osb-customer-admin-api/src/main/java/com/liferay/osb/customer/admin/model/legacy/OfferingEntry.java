/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.legacy;

import java.util.Date;

/**
 * @author Amos Fong
 */
public interface OfferingEntry {

	public long getProductEntryId();

	public int getQuantity();

	public Date getStartDate();

	public Date getSupportEndDate();

	public void setProductEntryId(long productEntryId);

	public void setQuantity(int quantity);

	public void setStartDate(Date startDate);

	public void setSupportEndDate(Date supportEndDate);

}