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

/**
 * @author Brian Wing Shun Chan
 */
public interface TrainingCustomerFinder {
	public int countByKeywords(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByFN_LN_EA(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingCustomer> findByKeywords(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingCustomer> findByFN_LN_EA(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}