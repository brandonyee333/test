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

package com.liferay.osb.model.impl;

// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@JSON(strict = true)
public class OrderEntryImpl extends OrderEntryBaseImpl {

	public OrderEntryImpl() {
	}

	@JSON
	public List<OfferingEntry> getOfferingEntries() {
		if (_offeringEntries == null) {
			_offeringEntries =
				OfferingEntryLocalServiceUtil.getOrderEntryOfferingEntries(
					getOrderEntryId());
		}

		return _offeringEntries;
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(getStatus());
	}

	public void setOfferingEntries(List<OfferingEntry> offeringEntries) {
		_offeringEntries = offeringEntries;
	}

	private List<OfferingEntry> _offeringEntries;

}