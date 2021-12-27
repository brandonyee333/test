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

package com.liferay.fragment.form.internal;

import com.liferay.fragment.contributor.BaseFragmentCollectionContributor;
import com.liferay.fragment.contributor.FragmentCollectionContributor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.template.react.renderer.ComponentDescriptor;
import com.liferay.portal.template.react.renderer.ReactRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Locale;

/**
 * @author Marco Leo
 */
@Component(
	service = FragmentRenderer.class
)
public class TextFragmentRenderer
	implements FragmentRenderer {

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.fragment.form)"
	)
	private ServletContext _servletContext;

	@Override
	public String getLabel(Locale locale) {
		return "Input Text";
	}

	@Override
	public String getCollectionKey() {
		return "FORM";
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		return JSONUtil.put(
			"fieldSets",
			JSONUtil.putAll(
				JSONUtil.put(
					"fields",
					JSONUtil.putAll(
						JSONUtil.put(
							"label", "item"
						).put(
							"name", "itemSelector"
						).put(
							"type", "itemSelector"
						).put(
							"typeOptions",
							JSONUtil.put("enableSelectTemplate", true)
						),
						JSONUtil.put(
							"label", "label"
						).put(
							"name", "label"
						).put(
							"type", "text"
						))))
		).toString();
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) throws IOException {

		FragmentEntryLink fragmentEntryLink =
			fragmentRendererContext.getFragmentEntryLink();

		String label =
			(String)_fragmentEntryConfigurationParser.getFieldValue(
				getConfiguration(fragmentRendererContext),
				fragmentEntryLink.getEditableValues(), "label");

		try {
			_reactRenderer.renderReact(
				new ComponentDescriptor(
					_npmResolver.resolveModuleName("@liferay/fragment-form") +
					"/js/components/Input"),
				HashMapBuilder.<String, Object>put(
					"label", label
				).build(),
				httpServletRequest, httpServletResponse.getWriter());
		}
		catch (Exception exception) {
			throw new IOException(exception);
		}


	}

	@Reference
	private ReactRenderer _reactRenderer;

	@Reference
	private NPMResolver _npmResolver;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

}