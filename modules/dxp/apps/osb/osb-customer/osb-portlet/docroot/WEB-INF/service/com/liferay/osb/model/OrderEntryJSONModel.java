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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OrderEntryJSONModel extends OrderEntryClp {

	@Override
	public List<OfferingEntry> getOfferingEntries() {
		return _offeringEntries;
	}

	@Override
	public void setOfferingEntries(List<OfferingEntry> offeringEntries) {
		_offeringEntries = new ArrayList<>(offeringEntries);
	}

	private List<OfferingEntry> _offeringEntries;

}