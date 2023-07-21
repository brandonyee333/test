/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.web.internal.portlet;

import com.liferay.frontend.image.editor.web.internal.constants.ImageEditorPortletKeys;
import com.liferay.frontend.image.editor.web.internal.portlet.tracker.ImageEditorCapabilityTracker;
import com.liferay.portal.portlet.bridge.soy.SoyPortlet;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=image-editor",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/ImageEditor.css",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Image Editor",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + ImageEditorPortletKeys.IMAGE_EDITOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class ImageEditorPortlet extends SoyPortlet {

	@Override
	protected Set<String> getJavaScriptRequiredModules(String path) {
		Set<String> javaScriptRequiredModules = new HashSet<>();

		javaScriptRequiredModules.addAll(
			super.getJavaScriptRequiredModules(path));
		javaScriptRequiredModules.addAll(
			_imageEditorCapabilityTracker.
				getImageEditorCapabilitiesRequirements());

		return javaScriptRequiredModules;
	}

	@Reference
	private ImageEditorCapabilityTracker _imageEditorCapabilityTracker;

}