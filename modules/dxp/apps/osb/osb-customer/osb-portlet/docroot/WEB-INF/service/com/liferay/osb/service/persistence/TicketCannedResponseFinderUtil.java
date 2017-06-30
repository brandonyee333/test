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

package com.liferay.osb.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketCannedResponseFinderUtil {
	public static int countByKeywords(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(keywords);
	}

	public static int countByN_C(java.lang.String name,
		java.lang.String content, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByN_C(name, content, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> findByN_C(
		java.lang.String name, java.lang.String content, boolean andOperator,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByN_C(name, content, andOperator, start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> findByKeywords(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByKeywords(keywords, start, end);
	}

	public static TicketCannedResponseFinder getFinder() {
		if (_finder == null) {
			_finder = (TicketCannedResponseFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketCannedResponseFinder.class.getName());

			ReferenceRegistry.registerReference(TicketCannedResponseFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TicketCannedResponseFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TicketCannedResponseFinderUtil.class,
			"_finder");
	}

	private static TicketCannedResponseFinder _finder;
}