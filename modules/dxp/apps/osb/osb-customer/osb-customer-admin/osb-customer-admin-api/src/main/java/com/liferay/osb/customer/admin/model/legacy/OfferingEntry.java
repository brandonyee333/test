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