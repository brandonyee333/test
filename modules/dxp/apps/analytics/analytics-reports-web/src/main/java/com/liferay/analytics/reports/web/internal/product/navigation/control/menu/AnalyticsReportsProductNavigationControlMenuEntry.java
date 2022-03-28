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

package com.liferay.analytics.reports.web.internal.product.navigation.control.menu;

import com.liferay.analytics.reports.constants.AnalyticsReportsWebKeys;
import com.liferay.analytics.reports.info.item.AnalyticsReportsInfoItem;
import com.liferay.analytics.reports.info.item.AnalyticsReportsInfoItemTracker;
import com.liferay.analytics.reports.info.item.ClassNameClassPKInfoItemIdentifier;
import com.liferay.analytics.reports.info.item.provider.AnalyticsReportsInfoItemObjectProvider;
import com.liferay.analytics.reports.web.internal.constants.AnalyticsReportsPortletKeys;
import com.liferay.analytics.reports.web.internal.info.item.provider.AnalyticsReportsInfoItemObjectProviderTracker;
import com.liferay.analytics.reports.web.internal.util.AnalyticsReportsUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.react.renderer.ComponentDescriptor;
import com.liferay.portal.template.react.renderer.ReactRenderer;
import com.liferay.product.navigation.control.menu.BaseProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;
import com.liferay.taglib.ui.IconTag;
import com.liferay.taglib.util.BodyBottomTag;

import java.io.IOException;
import java.io.Writer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
		"product.navigation.control.menu.entry.order:Integer=400"
	},
	service = {
		AnalyticsReportsProductNavigationControlMenuEntry.class,
		ProductNavigationControlMenuEntry.class
	}
)
public class AnalyticsReportsProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry {

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

	@Override
	public String getURL(HttpServletRequest httpServletRequest) {
		return null;
	}

	@Override
	public boolean includeBody(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		BodyBottomTag bodyBottomTag = new BodyBottomTag();

		bodyBottomTag.setOutputKey("analyticsReportsPanel");

		try {
			bodyBottomTag.doBodyTag(
				httpServletRequest, httpServletResponse,
				pageContext -> {
					try {
						_processBodyBottomTagBody(pageContext);
					}
					catch (Exception exception) {
						throw new ProcessBodyBottomTagBodyException(exception);
					}
				});
		}
		catch (ProcessBodyBottomTagBodyException
					processBodyBottomTagBodyException) {

			throw new IOException(processBodyBottomTagBodyException);
		}
		catch (JspException jspException) {
			throw new IOException(jspException);
		}

		return true;
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		Map<String, String> values = new HashMap<>();

		if (isPanelStateOpen(httpServletRequest)) {
			values.put("cssClass", "active");
		}
		else {
			values.put("cssClass", StringPool.BLANK);
		}

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			_portal.getLocale(httpServletRequest), getClass());

		values.put(
			"title",
			_html.escape(_language.get(resourceBundle, "content-performance")));

		IconTag iconTag = new IconTag();

		iconTag.setCssClass("icon-monospaced");
		iconTag.setImage("analytics");
		iconTag.setMarkupView("lexicon");

		try {
			values.put(
				"iconTag",
				iconTag.doTagAsString(httpServletRequest, httpServletResponse));
		}
		catch (JspException jspException) {
			throw new IOException(jspException);
		}

		values.put("portletNamespace", _portletNamespace);

		Writer writer = httpServletResponse.getWriter();

		writer.write(StringUtil.replace(_ICON_TMPL_CONTENT, "${", "}", values));

		return true;
	}

	public boolean isPanelStateOpen(HttpServletRequest httpServletRequest) {
		String analyticsReportsPanelState = SessionClicks.get(
			httpServletRequest, _SESSION_CLICKS_KEY, "closed");

		if (Objects.equals(analyticsReportsPanelState, "open")) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		InfoItemReference infoItemReference = _getInfoItemReference(
			httpServletRequest);

		AnalyticsReportsInfoItemObjectProvider<Object>
			analyticsReportsInfoItemObjectProvider =
				(AnalyticsReportsInfoItemObjectProvider<Object>)
					_analyticsReportsInfoItemObjectProviderTracker.
						getAnalyticsReportsInfoItemObjectProvider(
							infoItemReference.getClassName());

		if (analyticsReportsInfoItemObjectProvider == null) {
			return false;
		}

		Object analyticsReportsInfoItemObject =
			analyticsReportsInfoItemObjectProvider.
				getAnalyticsReportsInfoItemObject(infoItemReference);

		if (analyticsReportsInfoItemObject == null) {
			return false;
		}

		AnalyticsReportsInfoItem<Object> analyticsReportsInfoItem =
			(AnalyticsReportsInfoItem<Object>)
				_analyticsReportsInfoItemTracker.getAnalyticsReportsInfoItem(
					infoItemReference.getClassName());

		if ((analyticsReportsInfoItem == null) ||
			!analyticsReportsInfoItem.isShow(analyticsReportsInfoItemObject)) {

			return false;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!AnalyticsReportsUtil.isShowAnalyticsReportsPanel(
				themeDisplay.getCompanyId(), httpServletRequest)) {

			return false;
		}

		return super.isShow(httpServletRequest);
	}

	public void setPanelState(
		HttpServletRequest httpServletRequest, String panelState) {

		SessionClicks.put(httpServletRequest, _SESSION_CLICKS_KEY, panelState);
	}

	public static class ProcessBodyBottomTagBodyException
		extends RuntimeException {

		public ProcessBodyBottomTagBodyException(Throwable throwable) {
			super(throwable);
		}

	}

	@Activate
	protected void activate() {
		_portletNamespace = _portal.getPortletNamespace(
			AnalyticsReportsPortletKeys.ANALYTICS_REPORTS);
	}

	private String _getAnalyticsReportsURL(
		HttpServletRequest httpServletRequest) {

		InfoItemReference infoItemReference = _getInfoItemReference(
			httpServletRequest);

		if (infoItemReference.getInfoItemIdentifier() instanceof
				ClassNameClassPKInfoItemIdentifier) {

			ClassNameClassPKInfoItemIdentifier
				classNameClassPKInfoItemIdentifier =
					(ClassNameClassPKInfoItemIdentifier)
						infoItemReference.getInfoItemIdentifier();

			return PortletURLBuilder.create(
				_portletURLFactory.create(
					httpServletRequest,
					AnalyticsReportsPortletKeys.ANALYTICS_REPORTS,
					PortletRequest.RESOURCE_PHASE)
			).setParameter(
				"className", infoItemReference.getClassName()
			).setParameter(
				"classPK", classNameClassPKInfoItemIdentifier.getClassPK()
			).setParameter(
				"classTypeName",
				classNameClassPKInfoItemIdentifier.getClassName()
			).setParameter(
				"p_p_resource_id", "/analytics_reports/get_data"
			).buildString();
		}
		else if (infoItemReference.getInfoItemIdentifier() instanceof
					ClassPKInfoItemIdentifier) {

			return PortletURLBuilder.create(
				_portletURLFactory.create(
					httpServletRequest,
					AnalyticsReportsPortletKeys.ANALYTICS_REPORTS,
					PortletRequest.RESOURCE_PHASE)
			).setParameter(
				"className", infoItemReference.getClassName()
			).setParameter(
				"classPK", infoItemReference.getClassPK()
			).setParameter(
				"p_p_resource_id", "/analytics_reports/get_data"
			).buildString();
		}

		return StringPool.BLANK;
	}

	private InfoItemReference _getInfoItemReference(
		HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return Optional.ofNullable(
			(InfoItemReference)httpServletRequest.getAttribute(
				AnalyticsReportsWebKeys.INFO_ITEM_REFERENCE)
		).orElseGet(
			() -> new InfoItemReference(
				Layout.class.getName(), themeDisplay.getPlid())
		);
	}

	private void _processBodyBottomTagBody(PageContext pageContext)
		throws IOException, JspException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)pageContext.getRequest();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			_portal.getLocale(httpServletRequest), getClass());

		pageContext.setAttribute("resourceBundle", resourceBundle);

		JspWriter jspWriter = pageContext.getOut();

		try {
			if (AnalyticsReportsUtil.isAnalyticsSynced(
					_portal.getCompanyId(httpServletRequest),
					_portal.getScopeGroupId(httpServletRequest))) {

				StringBundler sb = new StringBundler(6);

				sb.append("<div id=\"");
				sb.append(_portletNamespace);
				sb.append("-analytics-reports-root\">");
				sb.append("<span aria-hidden=\"true\"");
				sb.append("class=\"loading-animation ");
				sb.append("loading-animation-sm\"></span>");

				jspWriter.write(sb.toString());

				String module = _npmResolver.resolveModuleName(
					"analytics-reports-web/js/AnalyticsReportsApp");

				_reactRenderer.renderReact(
					new ComponentDescriptor(module),
					Collections.singletonMap(
						"context",
						Collections.singletonMap(
							"analyticsReportsDataURL",
							String.valueOf(
								_getAnalyticsReportsURL(httpServletRequest)))),
					httpServletRequest, jspWriter);
			}
			else {
				StringBundler sb = new StringBundler(13);

				sb.append("<div class=\"p-3 pt-4 text-center\">");

				IconTag iconTag = new IconTag();

				iconTag.setSrc(
					_portal.getPathContext(httpServletRequest) +
						"/assets/ac-icon.svg");
				iconTag.setAlt("connect-to-liferay-analytics-cloud");

				sb.append(iconTag.doTagAsString(pageContext));

				if (AnalyticsReportsUtil.isAnalyticsConnected(
						_portal.getCompanyId(httpServletRequest))) {

					sb.append("<h4 class=\"font-weight-semi-bold h5 mt-3\">");
					sb.append(
						_language.get(
							_portal.getLocale(httpServletRequest),
							"sync-to-analytics-cloud"));
					sb.append("</h4>");

					sb.append("<p class=\"text-secondary\">");
					sb.append(
						_language.get(
							_portal.getLocale(httpServletRequest),
							StringBundler.concat(
								"sync-your-liferay-dxp-instance-with-",
								"analytics-cloud-to-view-content-performance-",
								"metrics-and-build-a-successful-content-",
								"strategy")));
					sb.append("</p>");

					IconTag openIconTag = new IconTag();

					openIconTag.setLabel(true);
					openIconTag.setLinkCssClass("btn btn-primary btn-sm mb-3");
					openIconTag.setMarkupView("lexicon");
					openIconTag.setTarget("_blank");
					openIconTag.setUrl(
						PrefsPropsUtil.getString(
							_portal.getCompanyId(httpServletRequest),
							"liferayAnalyticsURL"));

					sb.append(openIconTag.doTagAsString(pageContext));
				}
				else {
					sb.append("<h4 class=\"font-weight-semi-bold h5 mt-3\">");
					sb.append(
						_language.get(
							_portal.getLocale(httpServletRequest),
							"connect-to-liferay-analytics-cloud"));
					sb.append("</h4>");

					sb.append("<p class=\"text-secondary\">");
					sb.append(
						_language.get(
							_portal.getLocale(httpServletRequest),
							StringBundler.concat(
								"liferay-dxp-instance-has-to-be-connected-",
								"with-analytics-cloud-to-view-content-",
								"performance-metrics-and-build-a-successful-",
								"content-strategy")));
					sb.append("</p>");

					IconTag openIconTag = new IconTag();

					openIconTag.setLabel(true);
					openIconTag.setLinkCssClass("btn btn-secondary btn-sm");
					openIconTag.setMarkupView("lexicon");
					openIconTag.setTarget("_blank");
					openIconTag.setUrl(
						AnalyticsReportsUtil.ANALYTICS_CLOUD_TRIAL_URL);
					openIconTag.setMessage("start-free-trial");

					sb.append(openIconTag.doTagAsString(pageContext));
				}

				IconTag openIconTag = new IconTag();

				openIconTag.setLabel(true);
				openIconTag.setLinkCssClass(
					"d-block font-weight-bold mb-2 mt-5");
				openIconTag.setMarkupView("lexicon");
				openIconTag.setTarget("_blank");
				openIconTag.setUrl(
					PortletURLBuilder.createActionURL(
						_portal.getLiferayPortletResponse(
							(PortletResponse)httpServletRequest.getAttribute(
								JavaConstants.JAVAX_PORTLET_RESPONSE))
					).setActionName(
						"/analytics_reports/hide_panel"
					).setRedirect(
						() -> {
							String redirect = ParamUtil.getString(
								httpServletRequest, "redirect");

							if (Validator.isNotNull(redirect)) {
								return redirect;
							}

							return themeDisplay.getLayoutFriendlyURL(
								themeDisplay.getLayout());
						}
					).buildString());

				openIconTag.setMessage("do-not-show-me-this-again");

				sb.append(openIconTag.doTagAsString(pageContext));

				sb.append("<p class=\"text-secondary\">");
				sb.append(
					_language.get(
						themeDisplay.getLocale(),
						"do-not-show-me-this-again-help"));

				sb.append("</p>");

				jspWriter.write(sb.toString());

				jspWriter.write("</div>");
			}
		}
		catch (PortalException portalException) {
			throw new IOException(portalException);
		}
	}

	private static final String _ICON_TMPL_CONTENT = StringUtil.read(
		AnalyticsReportsProductNavigationControlMenuEntry.class, "icon.tmpl");

	private static final String _SESSION_CLICKS_KEY =
		"com.liferay.analytics.reports.web_panelState";

	@Reference
	private AnalyticsReportsInfoItemObjectProviderTracker
		_analyticsReportsInfoItemObjectProviderTracker;

	@Reference
	private AnalyticsReportsInfoItemTracker _analyticsReportsInfoItemTracker;

	@Reference
	private Html _html;

	@Reference
	private Language _language;

	@Reference
	private NPMResolver _npmResolver;

	@Reference
	private Portal _portal;

	private String _portletNamespace;

	@Reference
	private PortletURLFactory _portletURLFactory;

	@Reference
	private ReactRenderer _reactRenderer;

}