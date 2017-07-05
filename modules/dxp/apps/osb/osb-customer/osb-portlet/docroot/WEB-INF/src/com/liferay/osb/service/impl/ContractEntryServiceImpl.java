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

import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.service.base.ContractEntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ContractEntryServiceImpl extends ContractEntryServiceBaseImpl {

	public ContractEntry addContractEntry(
			long classNameId, long classPK, int type,
			Map<Locale, String> contentMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return contractEntryLocalService.addContractEntry(
			getUserId(), classNameId, classPK, type, contentMap,
			serviceContext);
	}

}