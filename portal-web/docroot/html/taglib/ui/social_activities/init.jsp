<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.social.kernel.model.SocialActivity" %><%@
page import="com.liferay.social.kernel.model.SocialActivityFeedEntry" %><%@
page import="com.liferay.social.kernel.service.SocialActivityLocalServiceUtil" %><%@
page import="com.liferay.social.kernel.util.SocialActivityDescriptor" %>

<%
List<SocialActivityDescriptor> activityDescriptors = (List<SocialActivityDescriptor>)request.getAttribute("liferay-ui:social-activities:activityDescriptors");
String className = (String)request.getAttribute("liferay-ui:social-activities:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:social-activities:classPK"));
int feedDelta = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:social-activities:feedDelta"));
String feedDisplayStyle = (String)request.getAttribute("liferay-ui:social-activities:feedDisplayStyle");
boolean feedEnabled = !PortalUtil.isRSSFeedsEnabled() ? false : GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:social-activities:feedEnabled"));
ResourceURL feedResourceURL = (ResourceURL)request.getAttribute("liferay-ui:social-activities:feedResourceURL");
String feedTitle = (String)request.getAttribute("liferay-ui:social-activities:feedTitle");
String feedType = (String)request.getAttribute("liferay-ui:social-activities:feedType");
String feedURL = (String)request.getAttribute("liferay-ui:social-activities:feedURL");
String feedURLMessage = (String)request.getAttribute("liferay-ui:social-activities:feedURLMessage");

if (activityDescriptors == null) {
	List<SocialActivity> activities = SocialActivityLocalServiceUtil.getActivities(0, className, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	activityDescriptors = new ArrayList<SocialActivityDescriptor>(activities.size());

	for (SocialActivity activity : activities) {
		activityDescriptors.add(new SocialActivityDescriptor(activity));
	}
}

String selector = StringPool.BLANK;

Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM d", locale, timeZone);
Format yearDateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM d, yyyy", locale, timeZone);

Format timeFormatDate = FastDateFormatFactoryUtil.getTime(locale, timeZone);
%>

<%@ include file="/html/taglib/ui/social_activities/init-ext.jsp" %>