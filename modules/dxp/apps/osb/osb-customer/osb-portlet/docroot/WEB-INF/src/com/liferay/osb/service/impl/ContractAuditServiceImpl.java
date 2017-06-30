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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.service.base.ContractAuditServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ContractAuditServiceImpl extends ContractAuditServiceBaseImpl {

	public void addContractAudit(
			long contractEntryId, String signatoryClassName,
			long signatoryClassPK, String productClassName, long productClassPK)
		throws PortalException, SystemException {

		if (signatoryClassName.equals(CorpEntry.class.getName())) {
			corpEntryLocalService.validateMembership(
				getUserId(), signatoryClassPK, 0);
		}
		else if (signatoryClassName.equals(User.class.getName()) &&
				 (getUserId() != signatoryClassPK)) {

			throw new PrincipalException();
		}

		contractAuditLocalService.addContractAudit(
			getUserId(), contractEntryId, signatoryClassName, signatoryClassPK,
			productClassName, productClassPK);
	}

}