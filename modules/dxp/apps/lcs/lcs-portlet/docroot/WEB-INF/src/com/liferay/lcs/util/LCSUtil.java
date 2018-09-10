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

package com.liferay.lcs.util;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.jsonwebserviceclient.OAuthJSONWebServiceClientImpl;
import com.liferay.lcs.rest.client.LCSClusterNode;
import com.liferay.lcs.rest.client.LCSClusterNodeClient;
import com.liferay.lcs.rest.client.LCSProject;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.service.ReleaseLocalServiceUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;

import javax.portlet.PortletPreferences;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSUtil {

	public static Set<LCSAlert> getLCSAlerts() {
		return _lcsAlertAdvisor.getLCSAlerts();
	}

	public static String getLCSClusterEntryLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return getLCSLayoutURL(
			PortletPropsValues.OSB_LCS_PORTLET_LAYOUT_LCS_CLUSTER_ENTRY,
			publicRenderParameters);
	}

	public static String getLCSClusterNodeLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSClusterNodeId"),
			String.valueOf(lcsClusterNode.getLcsClusterNodeId()));
		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return getLCSLayoutURL(
			PortletPropsValues.OSB_LCS_PORTLET_LAYOUT_LCS_CLUSTER_NODE,
			publicRenderParameters);
	}

	public static String getLCSPortalURL() {
		StringBundler sb = new StringBundler(5);

		sb.append(PortletPropsValues.OSB_LCS_PORTLET_PROTOCOL);
		sb.append(Http.PROTOCOL_DELIMITER);
		sb.append(PortletPropsValues.OSB_LCS_PORTLET_HOST_NAME);

		if ((PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT == Http.HTTP_PORT) ||
			(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT == Http.HTTPS_PORT)) {

			return sb.toString();
		}

		sb.append(StringPool.COLON);
		sb.append(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT);

		return sb.toString();
	}

	public static int getLCSPortletBuildNumber() {
		Release release = null;

		try {
			release = ReleaseLocalServiceUtil.fetchRelease("lcs-portlet");
		}
		catch (SystemException se) {
			throw new RuntimeException(se);
		}

		return release.getBuildNumber();
	}

	public static String getLCSProjectLayoutURL(LCSProject lcsProject) {
		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return getLCSLayoutURL(
			PortletPropsValues.OSB_LCS_PORTLET_LAYOUT_LCS_PROJECT,
			publicRenderParameters);
	}

	public static String getPortalEdition() {
		try {
			Field field = ReleaseInfo.class.getDeclaredField(
				"_VERSION_DISPLAY_NAME");

			field.setAccessible(true);

			StringTokenizer stringTokenizer = new StringTokenizer(
				(String)field.get(null));

			stringTokenizer.nextToken();

			return stringTokenizer.nextToken();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getRegistrationLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			getPublicRenderParameterName("environmentPage"), "registration");
		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return getLCSLayoutURL(
			PortletPropsValues.OSB_LCS_PORTLET_LAYOUT_LCS_CLUSTER_ENTRY,
			publicRenderParameters);
	}

	public static boolean isLCSClusterNodeRegistered()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeClient.fetchLCSClusterNode(_lcsKeyAdvisor.getKey());

		if ((lcsClusterNode == null) || lcsClusterNode.isArchived()) {
			return false;
		}

		return true;
	}

	public static synchronized boolean isLCSPortletAuthorized()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		PortletPreferences jxPortletPreferences =
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences();

		if (jxPortletPreferences == null) {
			return false;
		}

		String lcsAccessToken = jxPortletPreferences.getValue(
			"lcsAccessToken", null);
		String lcsAccessSecret = jxPortletPreferences.getValue(
			"lcsAccessSecret", null);

		if (Validator.isNull(lcsAccessToken) ||
			Validator.isNull(lcsAccessSecret)) {

			return false;
		}

		if (System.currentTimeMillis() <
				_lcsAccessTokenNextValidityCheckMillis.get()) {

			return true;
		}

		if (!(_jsonWebServiceClient instanceof OAuthJSONWebServiceClientImpl)) {
			return true;
		}

		try {
			((OAuthJSONWebServiceClientImpl)_jsonWebServiceClient).
				testOAuthRequest();
		}
		catch (
			JSONWebServiceTransportException.AuthenticationFailure jsonwsteaf) {

			if (_log.isWarnEnabled()) {
				_log.warn(jsonwsteaf.getMessage());
			}

			return false;
		}

		_lcsAccessTokenNextValidityCheckMillis.set(
			System.currentTimeMillis() + 300000);

		return true;
	}

	public static void processLCSPortletState(LCSPortletState lcsPortletState) {
		Message message = LicenseManagerMessageType.LCS_AVAILABLE.createMessage(
			lcsPortletState);

		MessageBusUtil.sendMessage(message.getDestinationName(), message);

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(3);

			sb.append("Service availability message published for LCS ");
			sb.append("portlet state ");
			sb.append(lcsPortletState.name());

			_log.debug(sb.toString());
		}
	}

	public static void setUpJSONWebServiceClientCredentials(
		String lcsAccessSecret, String lcsAccessToken) {

		OAuthJSONWebServiceClientImpl oAuthJSONWebServiceClientImpl =
			(OAuthJSONWebServiceClientImpl)_jsonWebServiceClient;

		oAuthJSONWebServiceClientImpl.setAccessSecret(lcsAccessSecret);
		oAuthJSONWebServiceClientImpl.setAccessToken(lcsAccessToken);

		_jsonWebServiceClient.resetHttpClient();
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	public void setLCSAlertAdvisor(LCSAlertAdvisor lcsAlertAdvisor) {
		_lcsAlertAdvisor = lcsAlertAdvisor;
	}

	public void setLCSClusterNodeClient(
		LCSClusterNodeClient lcsClusterNodeClient) {

		_lcsClusterNodeClient = lcsClusterNodeClient;
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	protected static String getLCSLayoutURL(
		String friendlyURL, Map<String, String> publicRenderParameters) {

		String layoutFullURL = getLCSPortalURL() + friendlyURL;

		if (publicRenderParameters.isEmpty()) {
			return layoutFullURL;
		}

		StringBundler sb = new StringBundler(
			4 * publicRenderParameters.size() + 2);

		sb.append(layoutFullURL);
		sb.append("?p_p_id=5_WAR_osblcsportlet");

		for (Map.Entry<String, String> entry :
				publicRenderParameters.entrySet()) {

			sb.append(StringPool.AMPERSAND);
			sb.append(entry.getKey());
			sb.append(StringPool.EQUAL);
			sb.append(entry.getValue());
		}

		return sb.toString();
	}

	protected static String getPublicRenderParameterName(String parameterName) {
		StringBundler sb = new StringBundler(4);

		sb.append(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE);
		sb.append("http://www.liferay.com/public-render-parameters".hashCode());
		sb.append(StringPool.UNDERLINE);
		sb.append(parameterName);

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(LCSUtil.class);

	private static JSONWebServiceClient _jsonWebServiceClient;
	private static final AtomicLong _lcsAccessTokenNextValidityCheckMillis =
		new AtomicLong(0);
	private static LCSAlertAdvisor _lcsAlertAdvisor;
	private static LCSClusterNodeClient _lcsClusterNodeClient;
	private static LCSKeyAdvisor _lcsKeyAdvisor;

}