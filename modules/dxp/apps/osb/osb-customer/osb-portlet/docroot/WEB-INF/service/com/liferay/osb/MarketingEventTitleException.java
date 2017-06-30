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

package com.liferay.osb;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MarketingEventTitleException extends PortalException {

	public MarketingEventTitleException() {
		super();
	}

	public MarketingEventTitleException(List<String> languageIds) {
		_languageIds = languageIds;
	}

	public MarketingEventTitleException(String msg) {
		super(msg);
	}

	public MarketingEventTitleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MarketingEventTitleException(Throwable cause) {
		super(cause);
	}

	public List<String> getLanguageIds() {
		return _languageIds;
	}

	private List<String> _languageIds;

}