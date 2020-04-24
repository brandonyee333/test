<%--
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
--%>

<%@ include file="/alloy_mvc/jsp/testray/controllers/init.jspf" %>

<%@ include file="/alloy_mvc/jsp/util/testray_case_result_checker.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setPermissioned(true);
	}

	public void cancelBulkOperation() throws Exception {
		HttpSession session = request.getSession();

		session.removeAttribute("testrayCaseResultBulkOperation");

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void editCaseResults() throws Exception {
		renderRequest.setAttribute("PortletKeys", getConstantsBean(PortletKeys.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));

		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = _fetchTestrayCaseResultBulkOperation();

		String orderByCol = ParamUtil.getString(request, "orderByCol", "statusLabel");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		TestrayCompositeComparator testrayCompositeComparator = new TestrayCompositeComparator(orderByCol, orderByType.equals("asc"), "statusLabel", true, "priority", false, "testrayComponentName", true, "testrayCaseName", true, "comment", true);

		List<TestrayCaseResultComposite> testrayCaseResultComposites = ListUtil.sort(testrayCaseResultBulkOperation.getSelectedTestrayCaseResultComposites(), testrayCompositeComparator);

		renderRequest.setAttribute("testrayCaseResultComposites", testrayCaseResultComposites);

		String redirect = ParamUtil.getString(request, "redirect");

		renderRequest.setAttribute("redirect", redirect);

		long testrayRunId = ParamUtil.getLong(request, "testrayRunId");

		renderRequest.setAttribute("testrayRunId", testrayRunId);

		render("case_results/edit_case_results");
	}

	public void selectCaseResults() throws Exception {
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));

		TestrayCaseResultChecker rowChecker = new TestrayCaseResultChecker(renderResponse, request.getSession());

		rowChecker.setFormName("selectCaseResultsFm");

		renderRequest.setAttribute("rowChecker", rowChecker);

		TestrayUtil.setPortletURL(request, portletURL, "search");

		long testrayRunId = ParamUtil.getLong(request, "testrayRunId");

		boolean search = ParamUtil.getBoolean(request, "search");

		if (search) {
			String errors = ParamUtil.getString(request, "errors");
			boolean errorsBlankOnly = ParamUtil.getBoolean(request, "errorsBlankOnly");
			String issues = ParamUtil.getString(request, "issues");
			boolean issuesBlankOnly = ParamUtil.getBoolean(request, "issuesBlankOnly");
			String comments = ParamUtil.getString(request, "comments");
			boolean commentsBlankOnly = ParamUtil.getBoolean(request, "commentsBlankOnly");
			String name = ParamUtil.getString(request, "name");
			long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId");
			long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

			int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

			List<Integer> testrayCasePriorities = ListUtil.toList(priorities);

			renderRequest.setAttribute("testrayCasePriorities", testrayCasePriorities);

			int[] statuses = ParamUtil.getIntegerValues(request, "statuses");

			List<Integer> statusesList = ListUtil.toList(statuses);

			portletURL.setParameter("statuses", StringUtil.merge(statusesList));

			renderRequest.setAttribute("selectedStatuses", statusesList);

			List<TestrayCaseResult> testrayCaseResults = TestrayUtil.getFilteredTestrayCaseResults(testrayRunId, -1, errors, errorsBlankOnly, issues, issuesBlankOnly, comments, commentsBlankOnly, statusesList, testrayTeamId, name, testrayComponentId, testrayCasePriorities);

			List<TestrayCaseResultComposite> testrayCaseResultComposites = TestrayCompositeUtil.getComposites(testrayCaseResults, TestrayCaseResultComposite.class, new Class<?>[] {TestrayCaseResult.class, ThemeDisplay.class}, new Object[] {themeDisplay});

			String orderByCol = ParamUtil.getString(request, "orderByCol", "statusLabel");

			renderRequest.setAttribute("orderByCol", orderByCol);

			String orderByType = ParamUtil.getString(request, "orderByType", "asc");

			renderRequest.setAttribute("orderByType", orderByType);

			TestrayCompositeComparator testrayCompositeComparator = new TestrayCompositeComparator(orderByCol, orderByType.equals("asc"), "statusLabel", true, "priority", false, "testrayComponentName", true, "testrayCaseName", true, "comment", true);

			testrayCaseResultComposites = ListUtil.sort(testrayCaseResultComposites, testrayCompositeComparator);

			renderRequest.setAttribute("testrayCaseResultComposites", testrayCaseResultComposites);

			renderRequest.setAttribute("testrayCaseResultsCount", testrayCaseResults.size());
		}

		List<TestrayComponent> testrayComponents = Collections.emptyList();

		TestrayRun testrayRun = TestrayRunLocalServiceUtil.getTestrayRun(testrayRunId);

		testrayComponents = TestrayComponentUtil.getTestrayComponents(0, "testrayRunId", testrayRun.getTestrayRunId());

		renderRequest.setAttribute("testrayComponents", testrayComponents);

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayProjectId);

		renderRequest.setAttribute("testrayTeams", testrayTeams);

		render("case_results/select");
	}

	public void setAssigned() throws Exception {
		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = _fetchTestrayCaseResultBulkOperation();

		long assignedUserId = ParamUtil.getLong(request, "assignedUserId");

		testrayCaseResultBulkOperation.setAssignedUserId(assignedUserId);
	}

	public void setCaseResults() throws Exception {
		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = _fetchTestrayCaseResultBulkOperation();

		long[] addTestrayCaseResultIds = ParamUtil.getLongValues(request, "addTestrayCaseResultIds");

		testrayCaseResultBulkOperation.addTestrayCaseResultIds(addTestrayCaseResultIds);

		TestrayCaseResultUtil.bulkUpdateTestrayCaseResults(ListUtil.toList(addTestrayCaseResultIds), "assignedUserId", themeDisplay.getUserId());

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void unsetCaseResult() throws Exception {
		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = _fetchTestrayCaseResultBulkOperation();

		long testrayCaseResultId = ParamUtil.getLong(request, "testrayCaseResultId");

		testrayCaseResultBulkOperation.removeTestrayCaseResultId(testrayCaseResultId);

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@RequireIssueEngineAuthorization
	public void updateCaseResults() throws Exception {
		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = _fetchTestrayCaseResultBulkOperation();

		String comment = ParamUtil.getString(request, "comment");
		int commentType = ParamUtil.getInteger(request, "commentType");
		String issues = ParamUtil.getString(request, "issues");
		int issuesType = ParamUtil.getInteger(request, "issuesType");

		List<TestrayIssue> selectedTestrayIssues = TestrayIssueUtil.fetchOrAddTestrayIssues(this, StringUtil.split(issues));

		Set<TestrayIssue> oldTestrayIssues = new HashSet<TestrayIssue>();

		Set<Long> testrayCaseResultIds = testrayCaseResultBulkOperation.getSelectedTestrayCaseResultIds();

		for (long testrayCaseResultId : testrayCaseResultIds) {
			TestrayCaseResult testrayCaseResult = TestrayCaseResultLocalServiceUtil.getTestrayCaseResult(testrayCaseResultId);

			if (commentType == TestrayCaseResultConstants.BULK_EDIT_TYPE_APPEND) {
				String body = comment;

				MBMessage mbMessage = TestrayUtil.fetchMBMessage(testrayCaseResult.getCommentMBMessageId());

				if (mbMessage != null) {
					body = mbMessage.getBody() + StringPool.SPACE + body;
				}

				long commentMBMessageId = TestrayUtil.updateMBMessage(themeDisplay, testrayCaseResult.getCommentMBMessageId(), TestrayCase.class.getName(), testrayCaseResult.getTestrayCaseId(), body, portletRequest);

				testrayCaseResult.setCommentMBMessageId(commentMBMessageId);
			}
			else if (commentType == TestrayCaseResultConstants.BULK_EDIT_TYPE_REPLACE) {
				long commentMBMessageId = TestrayUtil.updateMBMessage(themeDisplay, testrayCaseResult.getCommentMBMessageId(), TestrayCase.class.getName(), testrayCaseResult.getTestrayCaseId(), comment, portletRequest);

				testrayCaseResult.setCommentMBMessageId(commentMBMessageId);
			}

			if (testrayCaseResultBulkOperation.getAssignedUserId() > 0) {
				testrayCaseResult.setAssignedUserId(testrayCaseResultBulkOperation.getAssignedUserId());
			}

			int status = ParamUtil.getInteger(request, "status", -1);

			if ((status != testrayCaseResult.getStatus()) && (status >= 0)) {
			}

			updateModel(testrayCaseResult);

			if (issuesType == TestrayCaseResultConstants.BULK_EDIT_TYPE_APPEND) {
				TestrayIssueLocalServiceUtil.addTestrayCaseResultTestrayIssues(testrayCaseResultId, selectedTestrayIssues);
			}
			else if (issuesType == TestrayCaseResultConstants.BULK_EDIT_TYPE_REPLACE) {
				oldTestrayIssues.addAll(TestrayIssueLocalServiceUtil.getTestrayCaseResultTestrayIssues(testrayCaseResultId));

				TestrayUtil.updateTestrayIssuesRelationships(TestrayCaseResult.class.getName(), testrayCaseResultId, selectedTestrayIssues);
			}
			else if (issuesType == TestrayCaseResultConstants.BULK_EDIT_TYPE_REMOVE) {
				TestrayIssueLocalServiceUtil.deleteTestrayCaseResultTestrayIssues(testrayCaseResultId, selectedTestrayIssues);
			}
		}

		TestrayIssueEngineUtil.updateIssues(TestrayUtil.getUnion(selectedTestrayIssues, oldTestrayIssues), user);

		HttpSession session = request.getSession();

		session.removeAttribute("testrayCaseResultBulkOperation");

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	private TestrayCaseResultBulkOperation _fetchTestrayCaseResultBulkOperation() throws Exception {
		HttpSession session = request.getSession();

		Object testrayCaseResultBulkOperationObject = session.getAttribute("testrayCaseResultBulkOperation");

		long assignedUserId = BeanPropertiesUtil.getLong(testrayCaseResultBulkOperationObject, "assignedUserId");
		Set<Long> selectedTestrayCaseResultIds = (Set<Long>)BeanPropertiesUtil.getObject(testrayCaseResultBulkOperationObject, "selectedTestrayCaseResultIds");

		TestrayCaseResultBulkOperation testrayCaseResultBulkOperation = new TestrayCaseResultBulkOperation(assignedUserId, selectedTestrayCaseResultIds, themeDisplay);

		session.setAttribute("testrayCaseResultBulkOperation", testrayCaseResultBulkOperation);

		return testrayCaseResultBulkOperation;
	}

}
%>