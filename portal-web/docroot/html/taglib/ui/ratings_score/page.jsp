<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_score_page") + StringPool.UNDERLINE;

double score = GetterUtil.getDouble((String)request.getAttribute("liferay-ui:ratings-score:score"));

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumFractionDigits(1);
numberFormat.setMinimumFractionDigits(0);

String scoreString = numberFormat.format(score);
%>

<c:choose>
	<c:when test="<%= themeDisplay.isFacebook() %>">
		<%= scoreString %> Stars
	</c:when>
	<c:otherwise>
		<div class="score taglib-ratings" id="<%= randomNamespace %>averageRating">
			<div class="helper-clearfix" id="<%= randomNamespace %>averageRatingContent">

				<%
				for (int i = 1; i <= 5; i++) {
				%>

					<a class="rating-element <%= (i <= score) ? "rating-element-on" : StringPool.BLANK %>" href="javascript:;"></a>

				<%
				}
				%>

			</div>
		</div>

		<aui:script use="aui-rating">
			var ratingScore = new A.Rating(
				{
					boundingBox: '#<%= randomNamespace %>averageRating',
					defaultSelected: <%= MathUtil.format(score, 1, 1) %>,
					disabled: true,
					srcNode: '#<%= randomNamespace %>averageRatingContent'
				}
			).render();

			ratingScore.get('boundingBox').on(
				'mouseenter',
				function(event) {
					var el = A.Node.getDOMNode(event.currentTarget);

					Liferay.Portal.ToolTip.show(el, '<%= scoreString %> Stars');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>