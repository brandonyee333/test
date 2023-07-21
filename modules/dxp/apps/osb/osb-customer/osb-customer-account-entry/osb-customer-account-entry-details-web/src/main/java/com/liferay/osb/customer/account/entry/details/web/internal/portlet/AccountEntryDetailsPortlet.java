/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.portlet;

import com.liferay.osb.customer.account.entry.details.web.internal.configuration.AccountEntryDetailsConfiguration;
import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.ticket.repository.FileRepositoryManager;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.account.entry.details.web.internal.configuration.AccountEntryDetailsConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-account-entry-details-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.footer-portlet-javascript=/dist/main.js",
		"com.liferay.portlet.header-portlet-css=/dist/main.css",
		"com.liferay.portlet.render-weight=50",
		"javax.portlet.display-name=OSB Support Project Details",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AccountEntryDetailsPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			AccountEntryDetailsConfiguration.class.getName(),
			_accountEntryDetailsConfiguration);
		renderRequest.setAttribute(
			FileRepositoryManager.class.getName(), _fileRepositoryManager);
		renderRequest.setAttribute(
			FileRepositoryWebService.class.getName(),
			_fileRepositoryWebService);

		super.render(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_accountEntryDetailsConfiguration = ConfigurableUtil.createConfigurable(
			AccountEntryDetailsConfiguration.class, properties);
	}

	@Override
	protected String getPath(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return (String)portletRequest.getAttribute(
			getMVCPathAttributeName(portletResponse.getNamespace()));
	}

	private volatile AccountEntryDetailsConfiguration
		_accountEntryDetailsConfiguration;

	@Reference
	private FileRepositoryManager _fileRepositoryManager;

	@Reference
	private FileRepositoryWebService _fileRepositoryWebService;

}