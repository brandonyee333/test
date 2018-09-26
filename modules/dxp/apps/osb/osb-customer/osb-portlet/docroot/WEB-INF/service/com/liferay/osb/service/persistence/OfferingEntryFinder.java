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

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface OfferingEntryFinder {
	public int countByU_AEI_PEI_T_S_SED(long userId, long accountEntryId,
		long[] productEntryIds, int[] types, int[] statuses,
		java.util.Date supportEndDateGT, java.util.Date supportEndDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_PEI_T_S_SED(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, java.util.Date supportEndDateGT,
		java.util.Date supportEndDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc);
}