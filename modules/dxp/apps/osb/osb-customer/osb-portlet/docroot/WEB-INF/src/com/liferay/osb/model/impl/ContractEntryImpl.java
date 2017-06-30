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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.util.PortletPropsValues;

/**
 * @author Douglas Wong
 */
public class ContractEntryImpl extends ContractEntryBaseImpl {

	public ContractEntryImpl() {
	}

	@Override
	public String getContent(String languageId) {
		if (PortletPropsValues.CONTRACT_ENTRY_LOCALIZED_ENABLED) {
			return super.getContent(languageId);
		}
		else {
			return super.getContent("en_US");
		}
	}

	public String getTypeLabel() {
		return ContractEntryConstants.getTypeLabel(getType());
	}

}