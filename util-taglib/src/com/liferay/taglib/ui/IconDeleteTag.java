/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.FileAvailabilityUtil;
import com.liferay.taglib.util.TagResourceBundleUtil;

import java.util.ResourceBundle;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconDeleteTag extends IconTag {

	public void setConfirmation(String confirmation) {
		_confirmation = confirmation;
	}

	public void setShowIcon(boolean showIcon) {
		_showIcon = showIcon;
	}

	public void setTrash(boolean trash) {
		_trash = trash;
	}

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		String cssClass = GetterUtil.getString(getCssClass());

		setCssClass(cssClass.concat(" item-remove"));

		String icon = StringPool.BLANK;

		if (_showIcon) {
			icon = getIcon();

			if (Validator.isNull(icon)) {
				if (_trash) {
					icon = "trash";
				}
				else {
					icon = "times";
				}
			}
		}

		setIcon(icon);

		setMarkupView("lexicon");

		if (Validator.isNull(getMessage())) {
			if (_trash) {
				setMessage("move-to-the-recycle-bin");
			}
			else {
				setMessage("delete");
			}
		}

		String url = getUrl();

		if (url.startsWith("javascript:if (confirm('")) {
			return super.getPage();
		}

		if (url.startsWith("javascript:")) {
			url = url.substring(11);
		}

		if (url.startsWith(Http.HTTP_WITH_SLASH) ||
			url.startsWith(Http.HTTPS_WITH_SLASH)) {

			url = "submitForm(document.hrefFm, '".concat(
				HtmlUtil.escapeJS(url)
			).concat(
				"');"
			);
		}

		if (url.startsWith("wsrp_rewrite?")) {
			url = StringUtil.replace(
				url, "/wsrp_rewrite",
				"&wsrp-extensions=encodeURL/wsrp_rewrite");
			url = "submitForm(document.hrefFm, '".concat(
				url
			).concat(
				"');"
			);
		}

		if (!_trash) {
			StringBundler sb = new StringBundler(5);

			sb.append("javascript:if (confirm('");

			ResourceBundle resourceBundle =
				TagResourceBundleUtil.getResourceBundle(pageContext);

			if (Validator.isNotNull(_confirmation)) {
				sb.append(
					UnicodeLanguageUtil.get(resourceBundle, _confirmation));
			}
			else {
				String confirmation = "are-you-sure-you-want-to-delete-this";

				sb.append(
					UnicodeLanguageUtil.get(resourceBundle, confirmation));
			}

			sb.append("')) { ");
			sb.append(url);
			sb.append(" } else { self.focus(); }");

			url = sb.toString();
		}
		else {
			url = "javascript:".concat(url);
		}

		setUrl(url);

		return super.getPage();
	}

	private static final String _PAGE = "/html/taglib/ui/icon_delete/page.jsp";

	private String _confirmation;
	private boolean _showIcon;
	private boolean _trash;

}