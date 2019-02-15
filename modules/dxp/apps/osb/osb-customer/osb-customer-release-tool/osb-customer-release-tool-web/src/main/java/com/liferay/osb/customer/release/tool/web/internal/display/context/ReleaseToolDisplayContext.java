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

package com.liferay.osb.customer.release.tool.web.internal.display.context;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.release.tool.web.internal.constants.DDMStructureConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.MimeResponse;
import javax.portlet.RenderRequest;

/**
 * @author Amos Fong
 */
public class ReleaseToolDisplayContext {

	public ReleaseToolDisplayContext(
			RenderRequest renderRequest, MimeResponse mimeResponse)
		throws Exception {

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_initHighlightsFilters();
	}

	public JSONArray getHightlightsFiltersJSONArray() {
		return _highlightsFiltersJSONArray;
	}

	private void _initHighlightsFilters() throws PortalException {
		_highlightsFiltersJSONArray = JSONFactoryUtil.createJSONArray();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			JournalArticle.class);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			_themeDisplay.getCompanyGroupId(), classNameId,
			DDMStructureConstants.KEY_FIX_PACK);

		DDMFormField ddmFormField = ddmStructure.getDDMFormField(
			DDMStructureConstants.FIELD_HIGHLIGHTS);

		List<DDMFormField> childDDMFormFields =
			ddmFormField.getNestedDDMFormFields();

		for (DDMFormField childDDMFormField : childDDMFormFields) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			LocalizedValue localizedValue = childDDMFormField.getLabel();

			jsonObject.put(
				"label", localizedValue.getString(_themeDisplay.getLocale()));

			jsonObject.put("value", childDDMFormField.getName());

			_highlightsFiltersJSONArray.put(jsonObject);
		}
	}

	private JSONArray _highlightsFiltersJSONArray;
	private final ThemeDisplay _themeDisplay;

}