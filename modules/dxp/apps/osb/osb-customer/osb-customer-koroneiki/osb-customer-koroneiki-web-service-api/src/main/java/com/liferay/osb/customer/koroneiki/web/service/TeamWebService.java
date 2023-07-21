/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface TeamWebService {

	public List<Team> getAssignedTeams(String accountKey) throws Exception;

	public List<Team> search(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, String filterString)
		throws Exception;

}