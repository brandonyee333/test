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

package com.liferay.portal.workflow.kaleo.forms.web.internal.display.context;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMDisplayRegistry;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.forms.constants.KaleoFormsPortletKeys;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.util.comparator.KaleoProcessCreateDateComparator;
import com.liferay.portal.workflow.kaleo.forms.util.comparator.KaleoProcessModifiedDateComparator;
import com.liferay.portal.workflow.kaleo.forms.web.configuration.KaleoFormsWebConfiguration;
import com.liferay.portal.workflow.kaleo.forms.web.internal.display.context.util.KaleoFormsAdminRequestHelper;
import com.liferay.portal.workflow.kaleo.forms.web.internal.search.KaleoProcessSearch;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Leonardo Barros
 */
public class KaleoFormsAdminDisplayContext {

	public KaleoFormsAdminDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		DDMDisplayRegistry ddmDisplayRegistry,
		KaleoFormsWebConfiguration kaleoFormsWebConfiguration,
		StorageEngine storageEngine) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_ddmDisplayRegistry = ddmDisplayRegistry;
		_kaleoFormsWebConfiguration = kaleoFormsWebConfiguration;
		_storageEngine = storageEngine;

		_kaleoFormsAdminRequestHelper = new KaleoFormsAdminRequestHelper(
			renderRequest);
	}

	public boolean changeableDefaultLanguage() {
		return _kaleoFormsWebConfiguration.changeableDefaultLanguage();
	}

	public DDMDisplay getDDMDisplay() {
		return _ddmDisplayRegistry.getDDMDisplay(
			_kaleoFormsAdminRequestHelper.getPortletId());
	}

	public DDMFormValues getDDMFormValues(long ddmStorageId)
		throws StorageException {

		return _storageEngine.getDDMFormValues(ddmStorageId);
	}

	public String getDisplayStyle() {
		if (_kaleoFormsAdminDisplayStyle != null) {
			return _kaleoFormsAdminDisplayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_renderRequest);

		_kaleoFormsAdminDisplayStyle = ParamUtil.getString(
			_renderRequest, "displayStyle");

		if (Validator.isNull(_kaleoFormsAdminDisplayStyle)) {
			_kaleoFormsAdminDisplayStyle = portalPreferences.getValue(
				KaleoFormsPortletKeys.KALEO_FORMS_ADMIN, "display-style",
				_kaleoFormsWebConfiguration.defaultDisplayView());
		}
		else if (ArrayUtil.contains(
					 getDisplayViews(), _kaleoFormsAdminDisplayStyle)) {

			portalPreferences.setValue(
				KaleoFormsPortletKeys.KALEO_FORMS_ADMIN, "display-style",
				_kaleoFormsAdminDisplayStyle);
		}

		if (!ArrayUtil.contains(
				getDisplayViews(), _kaleoFormsAdminDisplayStyle)) {

			_kaleoFormsAdminDisplayStyle = getDisplayViews()[0];
		}

		return _kaleoFormsAdminDisplayStyle;
	}

	public String[] getDisplayViews() {
		return _DISPLAY_VIEWS;
	}

	public OrderByComparator<KaleoProcess> getKaleoProcessOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<KaleoProcess> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new KaleoProcessCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new KaleoProcessModifiedDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public KaleoProcessSearch getKaleoProcessSearch(PortletURL portletURL) {
		KaleoProcessSearch kaleoProcessSearch = new KaleoProcessSearch(
			_renderRequest, portletURL);

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<KaleoProcess> orderByComparator =
			getKaleoProcessOrderByComparator(orderByCol, orderByType);

		kaleoProcessSearch.setOrderByCol(orderByCol);
		kaleoProcessSearch.setOrderByComparator(orderByComparator);
		kaleoProcessSearch.setOrderByType(orderByType);

		return kaleoProcessSearch;
	}

	public String getOrderByCol() {
		String orderByCol = ParamUtil.getString(
			_renderRequest, "orderByCol", "modified-date");

		return orderByCol;
	}

	public String getOrderByType() {
		String orderByType = ParamUtil.getString(
			_renderRequest, "orderByType", "asc");

		return orderByType;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/admin/view.jsp");

		return portletURL;
	}

	private static final String[] _DISPLAY_VIEWS = {"list"};

	private final DDMDisplayRegistry _ddmDisplayRegistry;
	private String _kaleoFormsAdminDisplayStyle;
	private final KaleoFormsAdminRequestHelper _kaleoFormsAdminRequestHelper;
	private final KaleoFormsWebConfiguration _kaleoFormsWebConfiguration;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final StorageEngine _storageEngine;

}