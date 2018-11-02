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
public interface AccountEntryFinder {
	public int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params);

	public int countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public java.util.List<com.liferay.osb.model.AccountEntry> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc);

	public java.util.List<com.liferay.osb.model.AccountEntry> findBySupportResponse(
		long supportResponseId);

	public java.util.List<com.liferay.osb.model.AccountEntry> findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc);
}