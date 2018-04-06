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

<%@ include file="/support/2/init.jsp" %>

<%
List<SupportTeam> supportTeams = SupportTeamLocalServiceUtil.getUserRoleSupportTeams(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER);
%>

<c:if test="<%= !supportTeams.isEmpty() %>">
	<div class="clearfix manager-dashboard w100">
		<table class="lfr-table">
			<tr>

				<%
				int teamIterator = 0;

				int rows = supportTeams.size() / 3;

				if ((supportTeams.size() % 3) != 0) {
					rows++;
				}

				for (int i = 0; i < 3; i++) {
				%>

					<td class="manager-dashboard-col w33 <%= (teamIterator == supportTeams.size()) ? "empty" : "" %>">

						<%
						if (teamIterator < supportTeams.size()) {
							for (int j = 0; j < rows; j++) {
								SupportTeam supportTeam = supportTeams.get(teamIterator);

								String cssClass = StringPool.BLANK;

								if (((j == (rows - 1)) || (teamIterator == (supportTeams.size() - 1)) || (i == 1)) && (j == (rows - 2)) && (((supportTeams.size() - teamIterator) - 1) < rows)) {
									cssClass = "last";
								}
						%>

								<div class="content-column content-column-content left-column manager-dashboard-team w100 <%= cssClass %>">
									<div class="team-title">
										<%= HtmlUtil.escape(supportTeam.getName()) %>
									</div>

									<table class="lfr-table">

										<%
										Map<Integer, List<SupportWorker>> supportWorkerMap = SupportUtil.getSupportWorkerMap(supportTeam.getSupportTeamId());

										for (int role : SupportWorkerConstants.ROLES) {
											List<SupportWorker> managedSupportWorkers = supportWorkerMap.get(role);

											if (managedSupportWorkers == null) {
												continue;
											}

											for (SupportWorker managedSupportWorker : managedSupportWorkers) {
												User managedSupportWorkerUser = UserLocalServiceUtil.getUser(managedSupportWorker.getUserId());
										%>

												<tr>
													<td class="switch">
														<c:if test="<%= managedSupportWorker.getRole() != SupportWorkerConstants.ROLE_WATCHER %>">
															<portlet:actionURL name="clockInOut" var="clockInOutURL">
																<portlet:param name="mvcPath" value="/support/2/preferences.jsp" />
																<portlet:param name="tabs1" value="manager-dashboard" />
																<portlet:param name="redirect" value="<%= currentURL %>" />
																<portlet:param name="supportWorkerId" value="<%= StringUtil.valueOf(managedSupportWorker.getSupportWorkerId()) %>" />
															</portlet:actionURL>

															<a class="toggle-on-off-switch <%= managedSupportWorker.isClockedIn() ? "on" : "off" %>" href="<%= clockInOutURL %>">
																<span class="toggle-on-off-switch-inner">
																	<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="in" /></span>

																	<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="on-pto" /></span>
																</span>
															</a>
														</c:if>
													</td>
													<td class="role-icon">
														<c:choose>
															<c:when test="<%= managedSupportWorker.getRole() == SupportWorkerConstants.ROLE_MANAGER %>">
																<span class="manager">M</span>
															</c:when>
															<c:when test="<%= managedSupportWorker.getRole() == SupportWorkerConstants.ROLE_WATCHER %>">
																<span class="watcher">W</span>
															</c:when>
															<c:otherwise>
																<span class="developer">D</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td class="user-name">
														<%= HtmlUtil.escape(managedSupportWorkerUser.getFullName()) %>
													</td>
												</tr>

										<%
											}
										}
										%>

									</table>
								</div>

						<%
								teamIterator++;

								if (cssClass.equals("last")) {
									break;
								}
							}
						}
						%>

					</td>

				<%
				}
				%>

			</tr>
		</table>
	</div>
</c:if>