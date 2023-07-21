/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl.legacy;

import com.liferay.osb.customer.admin.model.legacy.OfferingEntry;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class OfferingEntryImpl implements OfferingEntry {

	@Override
	public long getProductEntryId() {
		return _productEntryId;
	}

	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;
	}

	private long _productEntryId;
	private int _quantity;
	private Date _startDate;
	private Date _supportEndDate;

}