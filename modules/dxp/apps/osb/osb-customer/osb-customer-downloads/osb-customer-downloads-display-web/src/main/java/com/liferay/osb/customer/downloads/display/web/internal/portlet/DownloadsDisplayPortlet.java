/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.downloads.display.web.internal.portlet;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.downloads.display.web.configuration.DownloadsDisplayConfiguration;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.downloads.display.web.configuration.DownloadsDisplayConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-downloads-display-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"javax.portlet.display-name=OSB Downloads Display",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class DownloadsDisplayPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(DDMIndexer.class.getName(), _ddmIndexer);
		renderRequest.setAttribute(
			DownloadsDisplayConfiguration.class.getName(),
			_downloadsDisplayConfiguration);
		renderRequest.setAttribute(
			JournalConverter.class.getName(), _journalConverter);

		super.render(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_downloadsDisplayConfiguration = ConfigurableUtil.createConfigurable(
			DownloadsDisplayConfiguration.class, properties);
	}

	@Reference
	private DDMIndexer _ddmIndexer;

	private volatile DownloadsDisplayConfiguration
		_downloadsDisplayConfiguration;

	@Reference
	private JournalConverter _journalConverter;

}