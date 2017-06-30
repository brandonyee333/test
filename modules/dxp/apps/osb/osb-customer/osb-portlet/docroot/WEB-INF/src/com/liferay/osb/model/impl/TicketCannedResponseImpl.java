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

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TicketCannedResponseImpl extends TicketCannedResponseBaseImpl {

	public TicketCannedResponseImpl() {
	}

	public String[] getAvailableLocales() {
		return LocalizationUtil.getAvailableLocales(getName());
	}

	public String getDefaultLocale() {
		String name = getName();

		if (name == null) {
			return StringPool.BLANK;
		}

		return LocalizationUtil.getDefaultLocale(name);
	}

}