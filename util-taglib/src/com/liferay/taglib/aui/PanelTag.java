/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.aui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.servlet.taglib.aui.ToolTag;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BasePanelTag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class PanelTag extends BasePanelTag implements BodyTag {

	public void addToolTag(ToolTag toolTag) {
		if (_toolTags == null) {
			_toolTags = new ArrayList<>();
		}

		_toolTags.add(toolTag);
	}

	public List<ToolTag> getToolTags() {
		return _toolTags;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		if (_toolTags != null) {
			for (ToolTag toolTag : _toolTags) {
				toolTag.cleanUp();
			}

			_toolTags = null;
		}
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected int processStartTag() throws Exception {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		String id = getId();

		if (Validator.isNull(id)) {
			id = PortalUtil.getUniqueElementId(
				request, StringPool.BLANK, AUIUtil.normalizeId("panel"));
		}

		setNamespacedAttribute(request, "id", id);
		setNamespacedAttribute(request, "toolTags", _toolTags);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private List<ToolTag> _toolTags;

}