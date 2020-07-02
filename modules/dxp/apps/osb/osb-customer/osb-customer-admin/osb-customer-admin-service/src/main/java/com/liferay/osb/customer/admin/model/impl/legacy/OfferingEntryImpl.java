/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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