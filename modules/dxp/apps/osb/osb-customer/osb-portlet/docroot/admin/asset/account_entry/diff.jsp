<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, String> newAccountEntryAttributes = (TreeMap<String, String>)request.getAttribute("diff.jsp-newAccountEntryAttributes");
Map<String, String> oldAccountEntryAttributes = (TreeMap<String, String>)request.getAttribute("diff.jsp-oldAccountEntryAttributes");
%>

<c:if test="<%= (newAccountEntryAttributes != null) && !newAccountEntryAttributes.isEmpty() && (oldAccountEntryAttributes != null) && !oldAccountEntryAttributes.isEmpty() %>">
	<h3>
		<liferay-ui:message key="project-differences" />
	</h3>

	<table class="table">
		<tr>
			<th>
				<liferay-ui:message key="field" />
			</th>
			<th>
				<liferay-ui:message key="old-value" />
			</th>
			<th>
				<liferay-ui:message key="new-value" />
			</th>
		</tr>

		<%
		for (String field : oldAccountEntryAttributes.keySet()) {
		%>

			<tr>
				<td>
					<%= _getFieldLabel(request, field) %>
				</td>
				<td>
					<%= _getValueLabel(request, themeDisplay, field, oldAccountEntryAttributes.get(field)) %>
				</td>
				<td>
					<%= _getValueLabel(request, themeDisplay, field, newAccountEntryAttributes.get(field)) %>
				</td>
			</tr>

		<%
		}
		%>

	</table>
</c:if>

<%!
private String _getFieldLabel(HttpServletRequest request, String field) {
	String label = field;

	if (field.equals("languageIds")) {
		label = "languages";
	}
	else if (field.equals("partnerEntryId")) {
		label = "partner";
	}
	else if (field.equals("partnerManagedSupport")) {
		label = "partner-first-line-support";
	}
	else if (field.equals("supportRegionIds")) {
		label = "support-regions";
	}

	return LanguageUtil.get(request, label);
}

private String _getValueLabel(HttpServletRequest request, ThemeDisplay themeDisplay, String field, String value) throws Exception {
	if (field.equals("address")) {
		value = HtmlUtil.escape(value);

		return value.replace(StringPool.NEW_LINE, "<br />");
	}
	else if (field.equals("industry")) {
		int industry = GetterUtil.getInteger(value);

		return LanguageUtil.get(themeDisplay.getLocale(), AccountEntryConstants.getIndustryLabel(industry));
	}
	else if (field.equals("languageIds")) {
		String[] languageIds = StringUtil.split(value);

		for (int i = 0; i < languageIds.length; i++) {
			languageIds[i] = LanguageUtil.get(themeDisplay.getLocale(), AccountEntryConstants.getLanguageLabel(languageIds[i]));
		}

		return StringUtil.merge(languageIds, StringPool.COMMA_AND_SPACE);
	}
	else if (field.equals("notes")) {
		return "<pre>" + HtmlUtil.escape(value) + "</pre>";
	}
	else if (field.equals("partnerEntryId")) {
		long partnerEntryId = GetterUtil.getLong(value);

		if (partnerEntryId <= 0) {
			return StringPool.BLANK;
		}

		PartnerEntry partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);

		PortletURL portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_ADMIN, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/admin/edit_partner_entry.jsp");
		portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));

		StringBundler sb = new StringBundler(5);

		sb.append("<a href=\"");
		sb.append(portletURL.toString());
		sb.append("\" target=\"_blank\">");
		sb.append(HtmlUtil.escape(partnerEntry.getCode()));
		sb.append("</a>");

		return sb.toString();
	}
	else if (field.equals("supportRegionIds")) {
		long[] supportRegionIds = StringUtil.split(value, 0l);

		String[] supportRegionNames = new String[supportRegionIds.length];

		for (int i = 0; i < supportRegionIds.length; i++) {
			SupportRegion supportRegion = SupportRegionLocalServiceUtil.getSupportRegion(supportRegionIds[i]);

			supportRegionNames[i] = supportRegion.getName();
		}

		return StringUtil.merge(supportRegionNames, StringPool.COMMA_AND_SPACE);
	}
	else {
		return HtmlUtil.escape(LanguageUtil.get(themeDisplay.getLocale(), value));
	}
}
%>