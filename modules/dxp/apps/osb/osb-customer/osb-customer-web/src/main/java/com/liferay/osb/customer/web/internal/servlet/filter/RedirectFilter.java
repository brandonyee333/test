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

package com.liferay.osb.customer.web.internal.servlet.filter;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	property = {
		"dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Redirect Filter", "url-pattern=/group/customer/*"
	},
	service = Filter.class
)
public class RedirectFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		try {
			String redirect = getRedirect(request, response);

			if (Validator.isNotNull(redirect)) {
				response.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	protected long getPlid(
		long companyId, String pathInfo, String servletPath) {

		if (Validator.isNull(pathInfo) ||
			!pathInfo.startsWith(StringPool.SLASH)) {

			return 0;
		}

		String friendlyURL = null;

		int pos = pathInfo.indexOf(CharPool.SLASH, 1);

		if (pos != -1) {
			friendlyURL = pathInfo.substring(0, pos);
		}
		else if (pathInfo.length() > 1) {
			friendlyURL = pathInfo;
		}

		if (Validator.isNull(friendlyURL)) {
			return 0;
		}

		long groupId = 0;
		boolean privateLayout = false;

		try {
			Group group = _groupLocalService.getFriendlyURLGroup(
				companyId, friendlyURL);

			groupId = group.getGroupId();

			String privateGroupServletMapping = PropsUtil.get(
				PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING);
			String privateUserServletMapping = PropsUtil.get(
				PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING);
			String publicServletMapping = PropsUtil.get(
				PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);

			if (servletPath.startsWith(privateGroupServletMapping) ||
				servletPath.startsWith(privateUserServletMapping)) {

				privateLayout = true;
			}
			else if (servletPath.startsWith(publicServletMapping)) {
				privateLayout = false;
			}
		}
		catch (NoSuchLayoutException nsle) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsle, nsle);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.error(e, e);
			}

			return 0;
		}

		friendlyURL = null;

		if ((pos != -1) && ((pos + 1) != pathInfo.length())) {
			friendlyURL = pathInfo.substring(pos);
		}

		if (Validator.isNull(friendlyURL)) {
			try {
				return _layoutLocalService.getDefaultPlid(
					groupId, privateLayout);
			}
			catch (Exception e) {
				_log.warn(e, e);

				return 0;
			}
		}
		else if (friendlyURL.endsWith(StringPool.FORWARD_SLASH)) {
			friendlyURL = friendlyURL.substring(0, friendlyURL.length() - 1);
		}

		try {
			Layout layout = _layoutLocalService.getFriendlyURLLayout(
				groupId, privateLayout, friendlyURL);

			return layout.getPlid();
		}
		catch (NoSuchLayoutException nsle) {
			_log.warn(nsle, nsle);

			return 0;
		}
		catch (Exception e) {
			_log.error(e, e);

			return 0;
		}
	}

	protected String getRedirect(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		User user = _portal.getUser(request);

		if (user == null) {
			return null;
		}

		String pathInfo = request.getPathInfo();

		long plid = getPlid(
			user.getCompanyId(), pathInfo, request.getServletPath());

		if (plid <= 0) {
			return null;
		}

		Layout layout = _layoutLocalService.getLayout(plid);

		if (layout.isTypeLinkToLayout()) {
			long layoutId = GetterUtil.getLong(
				layout.getTypeSettingsProperty("linkToLayoutId"));

			if (layoutId > 0) {
				layout = _layoutLocalService.getLayout(
					layout.getGroupId(), layout.isPrivateLayout(), layoutId);
			}
		}

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		if (LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.VIEW)) {

			return null;
		}

		ExpandoTable expandoTable = _expandoTableLocalService.getTable(
			user.getCompanyId(), Layout.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME);

		ExpandoColumn expandoColumn = _expandoColumnLocalService.getColumn(
			expandoTable.getTableId(), "osbRedirectURL");

		ExpandoValue expandoValue = _expandoValueLocalService.getValue(
			expandoTable.getTableId(), expandoColumn.getColumnId(),
			layout.getPlid());

		if ((expandoValue != null) &&
			!pathInfo.contains(expandoValue.getData())) {

			return expandoValue.getData();
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(RedirectFilter.class);

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}