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

package com.liferay.osb.securitypatch.portlet;

import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.service.SecurityPatchLocalServiceUtil;
import com.liferay.osb.service.SecurityPatchServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Alan Zhang
 */
public class SecurityPatchPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		long securityPatchId = ParamUtil.getLong(
			actionRequest, "securityPatchId");

		try {
			SecurityPatch securityPatch =
				SecurityPatchServiceUtil.getSecurityPatch(securityPatchId);

			String url = _URL_PREFIX + securityPatch.getFileName();

			String key = HttpUtil.URLtoString(url, true);

			url = _URL_PREFIX + StringPool.SLASH + key;

			actionResponse.sendRedirect(url);

			sendAudit(actionRequest, url);
		}
		catch (Exception e) {
			PortalUtil.sendError(e, actionRequest, actionResponse);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("securityPatches")) {
				serveSecurityPatches(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected String getSecurityPatchName(SecurityPatch securityPatch)
		throws PortalException {

		if (Validator.isNotNull(securityPatch.getName())) {
			return securityPatch.getName();
		}

		/*Need to refactor, removing TicketAttachment from osb-portlet
		 * TicketAttachment ticketAttachment =
			TicketAttachmentLocalServiceUtil.getTicketAttachment(
				securityPatch.getTicketAttachmentId());

		String securityPatchName =
			SecurityPatchLocalServiceUtil.getSecurityPatchName(
				securityPatch.getEnvLFR(), ticketAttachment);

		SecurityPatchLocalServiceUtil.updateSecurityPatch(
			securityPatch.getSecurityPatchId(), securityPatchName);

		return securityPatchName;*/

		return null;
	}

	protected void sendAudit(ActionRequest actionRequest, String url) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"layoutURL", PortalUtil.getLayoutFullURL(themeDisplay));

			AuditMessage auditMessage = new AuditMessage(
				ActionKeys.VIEW, OSBConstants.COMPANY_ID, user.getUserId(),
				user.getFullName(), SecurityPatchPortlet.class.getName(), "url",
				url, null, jsonObject);

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void serveSecurityPatches(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String portletId = ParamUtil.getString(resourceRequest, "portletId");
		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");

		List<SecurityPatch> securityPatches =
			SecurityPatchLocalServiceUtil.getSecurityPatches(
				accountEntryId, portletId);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (SecurityPatch securityPatch : securityPatches) {
			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("name", getSecurityPatchName(securityPatch));
				jsonObject.put("value", securityPatch.getSecurityPatchId());

				jsonArray.put(jsonObject);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Invalid security patch " +
							securityPatch.getSecurityPatchId(),
						e);
				}
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("SecurityPatches", jsonArray);
		jsonObject.put("SecurityPatches#key", securityPatches.hashCode());

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final String _URL_PREFIX = "http://downloads.liferay.com";

	private static final Log _log = LogFactoryUtil.getLog(
		SecurityPatchPortlet.class);

}