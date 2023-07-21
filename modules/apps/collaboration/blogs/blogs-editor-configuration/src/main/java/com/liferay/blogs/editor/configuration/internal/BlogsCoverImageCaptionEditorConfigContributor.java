/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.editor.configuration.internal;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	property = {
		"editor.config.key=coverImageCaptionEditor",
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN
	},
	service = EditorConfigContributor.class
)
public class BlogsCoverImageCaptionEditorConfigContributor
	extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		jsonObject.put("allowedContent", "a");
		jsonObject.put("disallowedContent", "br");

		String removePlugins = jsonObject.getString("removePlugins");

		if (Validator.isNotNull(removePlugins)) {
			removePlugins = removePlugins + ",magicline";
		}
		else {
			removePlugins = "magicline";
		}

		jsonObject.put("removePlugins", removePlugins);
	}

	protected JSONObject getToolbarsJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("styles", getToolbarsStylesJSONObject());

		return jsonObject;
	}

	protected JSONObject getToolbarsStylesJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("selections", getToolbarStylesSelectionsJSONArray());
		jsonObject.put("tabIndex", 1);

		return jsonObject;
	}

	protected JSONArray getToolbarStylesSelectionsJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(getToolbarStylesSelectionsLinkJSONObject());
		jsonArray.put(getToolbarStylesSelectionsTextJSONObject());

		return jsonArray;
	}

	protected JSONObject getToolbarStylesSelectionsLinkJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("buttons", toJSONArray("['linkEdit']"));
		jsonObject.put("name", "link");
		jsonObject.put("test", "AlloyEditor.SelectionTest.link");

		return jsonObject;
	}

	protected JSONObject getToolbarStylesSelectionsTextJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("buttons", toJSONArray("['link']"));
		jsonObject.put("name", "text");
		jsonObject.put("test", "AlloyEditor.SelectionTest.text");

		return jsonObject;
	}

}