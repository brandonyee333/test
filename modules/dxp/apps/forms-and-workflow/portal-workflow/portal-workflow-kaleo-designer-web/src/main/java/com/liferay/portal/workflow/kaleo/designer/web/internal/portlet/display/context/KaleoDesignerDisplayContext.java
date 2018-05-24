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

package com.liferay.portal.workflow.kaleo.designer.web.internal.portlet.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.util.comparator.KaleoDraftDefinitionCreateDateComparator;
import com.liferay.portal.workflow.kaleo.designer.util.comparator.KaleoDraftDefinitionTitleComparator;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Rafael Praxedes
 */
public class KaleoDesignerDisplayContext {

	public KaleoDesignerDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public PortletURL getBasePortletURL() throws PortalException {
		PortletURL portletURL = _renderResponse.createRenderURL();

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		return portletURL;
	}

	public String getKaleoDraftDefinitionDisplayStyle() {
		return _DISPLAY_VIEWS[0];
	}

	public String[] getKaleoDraftDefinitionDisplayViews() {
		return _DISPLAY_VIEWS;
	}

	public OrderByComparator<KaleoDraftDefinition>
		getKaleoDraftDefinitionOrderByComparator() {

		boolean orderByAsc = false;
		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<KaleoDraftDefinition> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new KaleoDraftDefinitionCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("title")) {
			orderByComparator = new KaleoDraftDefinitionTitleComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(_renderRequest, "orderByCol", "create-date");
	}

	public String getOrderByType() {
		return ParamUtil.getString(_renderRequest, "orderByType", "desc");
	}

	public String[] getOrderColumns() {
		return _ORDER_COLUMNS;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		Class<?> clazz = getClass();

		return ResourceBundleUtil.getBundle(
			"content.Language", locale, clazz.getClassLoader());
	}

	private static final String[] _DISPLAY_VIEWS = {"list"};

	private static final String[] _ORDER_COLUMNS = {"create-date", "title"};

	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}