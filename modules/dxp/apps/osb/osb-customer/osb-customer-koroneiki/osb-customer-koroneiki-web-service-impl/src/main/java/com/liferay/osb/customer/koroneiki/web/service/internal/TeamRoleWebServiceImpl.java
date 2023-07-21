/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service.internal;

import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.TeamRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamRoleSerDes;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = TeamRoleWebService.class
)
public class TeamRoleWebServiceImpl implements TeamRoleWebService {

	public TeamRole fetchTeamRole(String type, String name) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_teamRoleResource.
				getTeamRoleByTypeTeamRoleTypeByNameTeamRoleNameHttpResponse(
					_http.encodePath(type), _http.encodePath(name));

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return TeamRoleSerDes.toDTO(httpResponse.getContent());
	}

	public List<TeamRole> getTeamRoles(String accountKey, String teamKey)
		throws Exception {

		Page<TeamRole> teamRolesPage =
			_teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, null);

		if ((teamRolesPage != null) && (teamRolesPage.getItems() != null)) {
			return new ArrayList<>(teamRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		TeamRoleResource.Builder builder = TeamRoleResource.builder();

		_teamRoleResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	@Reference
	private Http _http;

	private TeamRoleResource _teamRoleResource;

}