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
<%@ include file="/alloy_mvc/jsp/util/testray_subtask_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestraySubtask.class);
		setPermissioned(true);
	}

	public void editStatus() throws Exception {
		TestraySubtask testraySubtask = TestraySubtaskUtil.fetchTestraySubtask(request);

		_validateEditStatus(testraySubtask);

		renderRequest.setAttribute("testraySubtaskComposite", new TestraySubtaskComposite(testraySubtask, themeDisplay));

		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));

		render("subtasks/edit_status");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "assignedUserId", "errors", "name", "statusLabels", "testrayComponentName", "testrayTeamId", "start", "testrayTaskId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Long.class, String.class, String.class, String.class, String.class, Long.class, Integer.class, String.class})
	public void index() throws Exception {
		AlloySearchResult alloySearchResult = _search();

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtasksJSONArray(alloySearchResult.getBaseModels()));

			return;
		}

		renderRequest.setAttribute("TestraySubtaskConstants", getConstantsBean(TestraySubtaskConstants.class));
		renderRequest.setAttribute("TestraySubtaskConstantsMethods", new TestraySubtaskConstants());
		renderRequest.setAttribute("TestrayTaskConstants", getConstantsBean(TestrayTaskConstants.class));

		TestrayRowChecker rowChecker = new TestrayRowChecker(renderResponse);

		rowChecker.setFormName("testraySubtasksForm");

		renderRequest.setAttribute("rowChecker", rowChecker);

		List<TestraySubtaskComposite> testraySubtaskComposites = TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestraySubtaskComposite.class, new Class<?>[] {TestraySubtask.class, ThemeDisplay.class}, new Object[] {themeDisplay});

		renderRequest.setAttribute("testraySubtaskComposites", testraySubtaskComposites);

		renderRequest.setAttribute("testraySubtasksCount", alloySearchResult.getSize());

		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request, "testrayTaskId");

		long testraySubtasksTotalCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayTaskId", testrayTask.getTestrayTaskId(), "mergedToTestraySubtaskId", 0L});

		renderRequest.setAttribute("testraySubtasksTotalCount", testraySubtasksTotalCount);

		TestrayTaskComposite testrayTaskComposite = new TestrayTaskComposite(testrayTask, themeDisplay, user);

		renderRequest.setAttribute("testrayTaskComposite", testrayTaskComposite);

		List<User> users = UserLocalServiceUtil.getUsers(themeDisplay.getCompanyId(), false, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));

		renderRequest.setAttribute("users", users);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"names", "testraySubtaskIds", "testrayTaskId"}, parameterTypes = {String.class, String.class, Long.class})
	public void merge() throws Exception {
		List<TestraySubtask> testraySubtasks = null;

		long testrayTaskId = ParamUtil.getLong(request, "testrayTaskId", -1);
		String[] names = ParamUtil.getStringValues(request, "names");

		if ((testrayTaskId < 0) || ArrayUtil.isEmpty(names)) {
			long[] testraySubtaskIds = ParamUtil.getLongValues(request, "testraySubtaskIds");

			_validateMerge(testraySubtaskIds);

			testraySubtasks = TestraySubtaskUtil.fetchTestraySubtasks(testraySubtaskIds);
		}
		else {
			_validateMerge(testrayTaskId, names);

			testraySubtasks = TestraySubtaskUtil.fetchTestraySubtasks(testrayTaskId, names);
		}

		TestraySubtask testraySubtask = TestraySubtaskUtil.merge(this, testraySubtasks);

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtaskJSONObject(testraySubtask));

			return;
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testraySubtask.getTestraySubtaskId(), "score", testraySubtask.getScore());

		List<TestraySubtask> mergingTestraySubtasks = ListUtil.remove(testraySubtasks, Arrays.asList(testraySubtask));

		String mergingTestraySubtasksString = ListUtil.toString(mergingTestraySubtasks, "name", StringPool.COMMA_AND_SPACE);

		SessionMessages.add(portletRequest, "requestProcessed", translate("x-merged-with-x-successfully", mergingTestraySubtasksString, testraySubtask.getName()));

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void reindex() throws Exception {
		long testrayTaskId = ParamUtil.getLong(request, "testrayTaskId");

		List<BaseModel<?>> testraySubtaskBaseModels = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayTaskId", testrayTaskId});

		if (!testraySubtaskBaseModels.isEmpty()) {
			indexer.reindex(testraySubtaskBaseModels);
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayCaseResultIds", "testrayTaskId"}, parameterTypes = {String.class, String.class, Long.class})
	public void split() throws Exception {
		long testrayTaskId = ParamUtil.getLong(request, "testrayTaskId");
		String name = ParamUtil.getString(request, "name");
		long[] testrayCaseResultIds = ParamUtil.getLongValues(request, "testrayCaseResultIds");

		_validateSplit(testrayTaskId, name, testrayCaseResultIds);

		TestraySubtask testraySubtask = TestraySubtaskUtil.split(this, testrayTaskId, name, testrayCaseResultIds);

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtaskJSONObject(testraySubtask));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"comment", "id", "issues", "statusLabel"}, parameterTypes = {String.class, String.class, String.class, String.class})
	@RequireIssueEngineAuthorization
	public void updateStatus() throws Exception {
		TestraySubtask testraySubtask = TestraySubtaskUtil.fetchTestraySubtask(request);

		_validateUpdateStatus(testraySubtask);

		int status = ParamUtil.getInteger(request, "status", -1);

		String[] issues = ParamUtil.getStringValues(request, "issues");

		String issueComment = null;

		String body = ParamUtil.getString(request, "comment");

		if (Validator.isNotNull(body)) {
			StringBundler sb = new StringBundler(22);

			sb.append("*Testray Link*");
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.OPEN_BRACKET);
			sb.append(testraySubtask.getName());
			sb.append(StringPool.PIPE);
			sb.append(TestrayUtil.getURL(this, "subtasks", "view", testraySubtask.getTestraySubtaskId()));
			sb.append(StringPool.CLOSE_BRACKET);
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
			sb.append("*Status*");
			sb.append(StringPool.NEW_LINE);
			sb.append(LanguageUtil.get(request, TestraySubtaskConstants.getLabelStatus(status)));
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
			sb.append("*Issues*");
			sb.append(StringPool.NEW_LINE);
			sb.append(StringUtil.merge(issues, StringPool.COMMA_AND_SPACE));
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
			sb.append("*Testray Comment*");
			sb.append(StringPool.NEW_LINE);
			sb.append(body);

			issueComment = sb.toString();
		}

		TestraySubtaskUtil.updateTestraySubtasksTestrayIssues(this, issues, testraySubtask.getTestraySubtaskId(), user, issueComment);

		long commentMBMessageId = TestrayUtil.updateMBMessage(themeDisplay, testraySubtask.getCommentMBMessageId(), TestraySubtask.class.getName(), testraySubtask.getTestraySubtaskId(), body, portletRequest);

		updateModel(testraySubtask, "commentMBMessageId", commentMBMessageId, "status", TestraySubtaskConstants.STATUS_COMPLETE, "statusUpdateDate", new Date());

		List<TestrayCaseResult> testrayCaseResults = TestrayCaseResultLocalServiceUtil.getTestraySubtaskTestrayCaseResults(testraySubtask.getTestraySubtaskId());

		for (TestrayCaseResult testrayCaseResult : testrayCaseResults) {
			TestrayCaseResultUtil.updateTestrayCaseResultsTestrayIssues(this, issues, testrayCaseResult.getTestrayCaseResultId(), user);

			commentMBMessageId = TestrayUtil.updateMBMessage(themeDisplay, testrayCaseResult.getCommentMBMessageId(), TestrayCase.class.getName(), testrayCaseResult.getTestrayCaseId(), body, portletRequest);

			if (status < 0) {
				String statusLabel = ParamUtil.getString(request, "statusLabel");

				status = TestrayCaseResultConstants.getLabelStatus(statusLabel);
			}

			updateModelIgnoreRequest(testrayCaseResult, "commentMBMessageId", commentMBMessageId, "status", status);
		}

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtaskJSONObject(testraySubtask));

			return;
		}

		setOpenerSuccessMessage();

		render("close");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "assignedUserId"}, parameterTypes = {Long.class, Long.class})
	public void updateUser() throws Exception {
		_validateUpdateUser();

		TestraySubtask testraySubtask = TestraySubtaskUtil.fetchTestraySubtask(request);

		long assignedUserId = ParamUtil.getLong(request, "assignedUserId");

		_updateUser(testraySubtask, assignedUserId);

		if ((testraySubtask.getStatus() == TestraySubtaskConstants.STATUS_IN_ANALYSIS) && (assignedUserId == 0)) {
			testraySubtask.setStatus(TestraySubtaskConstants.STATUS_OPEN);
			testraySubtask.setStatusUpdateDate(new Date());

			updateModelIgnoreRequest(testraySubtask);
		}
		else if (testraySubtask.getStatus() != TestraySubtaskConstants.STATUS_IN_ANALYSIS) {
			testraySubtask.setStatus(TestraySubtaskConstants.STATUS_IN_ANALYSIS);
			testraySubtask.setStatusUpdateDate(new Date());

			updateModelIgnoreRequest(testraySubtask);
		}

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtaskJSONObject(testraySubtask));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayTaskId"}, parameterTypes = {String.class, String.class})
	public void view() throws Exception {
		TestraySubtask testraySubtask = TestraySubtaskUtil.fetchTestraySubtask(request);

		_validateView(testraySubtask);

		if (isRespondingTo("json")) {
			respondWith(_getTestraySubtaskJSONObject(testraySubtask));

			return;
		}

		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestraySubtaskConstants", getConstantsBean(TestraySubtaskConstants.class));

		TestrayRowChecker rowChecker = new TestrayRowChecker(renderResponse);

		rowChecker.setFormName("testraySubtasksForm");

		renderRequest.setAttribute("rowChecker", rowChecker);

		TestraySubtaskComposite testraySubtaskComposite = new TestraySubtaskComposite(testraySubtask, themeDisplay);

		renderRequest.setAttribute("testraySubtaskComposite", testraySubtaskComposite);

		AlloySearchResult alloySearchResult = _searchTestrayCaseResults();

		List<TestrayCaseResultComposite> testrayCaseResultComposites = TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayCaseResultComposite.class, new Class<?>[] {TestrayCaseResult.class, ThemeDisplay.class}, new Object[] {themeDisplay});

		renderRequest.setAttribute("testrayCaseResultComposites", testrayCaseResultComposites);

		renderRequest.setAttribute("testrayCaseResultsCount", alloySearchResult.getSize());

		int testrayCaseResultsTotalCount = TestrayCaseResultLocalServiceUtil.getTestraySubtaskTestrayCaseResultsCount(testraySubtask.getTestraySubtaskId());

		renderRequest.setAttribute("testrayCaseResultsTotalCount", testrayCaseResultsTotalCount);
	}

	@Override
	protected Indexer buildIndexer() {
		return TestraySubtaskIndexer.getInstance();
	}

	private TestrayAssignment _fetchTestrayAssignment(TestraySubtask testraySubtask) throws Exception {
		AlloyServiceInvoker testrayAssignmentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayAssignment.class.getName());

		List<TestrayAssignment> testrayAssignments = testrayAssignmentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"classNameId", PortalUtil.getClassNameId(TestraySubtask.class), "classPK", testraySubtask.getTestraySubtaskId()});

		if (!testrayAssignments.isEmpty()) {
			return testrayAssignments.get(0);
		}

		TestrayAssignment testrayAssignment = TestrayAssignmentLocalServiceUtil.createTestrayAssignment(0);

		testrayAssignment.setClassName(TestraySubtask.class.getName());
		testrayAssignment.setClassPK(testraySubtask.getTestraySubtaskId());

		return testrayAssignment;
	}

	private TestraySubtask _getTestraySubtask(long testrayTaskId, String name) throws Exception {
		List<TestraySubtask> testraySubtasks = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayTaskId", testrayTaskId, "name", name});

		if (testraySubtasks.isEmpty()) {
			throw new AlloyException(translate("there-is-no-subtask-with-name-x", name), false);
		}

		return testraySubtasks.get(0);
	}

	private JSONObject _getTestraySubtaskJSONObject(TestraySubtask testraySubtask) throws Exception {
		TestraySubtaskComposite testraySubtaskComposite = new TestraySubtaskComposite(testraySubtask, themeDisplay);

		return testraySubtaskComposite.getJSONObject();
	}

	private JSONArray _getTestraySubtasksJSONArray(List<BaseModel<?>> baseModels) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (BaseModel<?> baseModel : baseModels) {
			jsonArray.put(_getTestraySubtaskJSONObject((TestraySubtask)baseModel));
		}

		return jsonArray;
	}

	private AlloySearchResult _search() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		long assignedUserId = ParamUtil.getLong(request, "assignedUserId", -1);

		User assignedUser = UserLocalServiceUtil.fetchUser(assignedUserId);

		renderRequest.setAttribute("assignedUser", assignedUser);

		attributes.put("mergedToTestraySubtaskId", 0);

		String[] statuses = ParamUtil.getStringValues(request, "statuses", null);

		if (statuses == null) {
			String[] statusLabels = ParamUtil.getStringValues(request, "statusLabels");

			statuses = new String[statusLabels.length];

			for (int i = 0; i < statusLabels.length; i++) {
				statuses[i] = String.valueOf(TestraySubtaskConstants.getLabelStatus(statusLabels[i]));
			}
		}

		if (statuses.length > 0) {
			portletURL.setParameter("statuses", statuses);

			attributes.put("status", StringUtil.merge(statuses));

			List<Integer> testraySubtaskStatuses = ListUtil.toList(GetterUtil.getIntegerValues(statuses));

			renderRequest.setAttribute("testraySubtaskStatuses", testraySubtaskStatuses);

			renderRequest.setAttribute("statusLabels", TestrayUtil.getStatusLabels(request, TestraySubtaskConstants::getStatusLabel, testraySubtaskStatuses));
		}

		TestrayTask testrayTask = TestrayTaskUtil.getTestrayTask(request, "testrayTaskId");

		attributes.put("testrayTaskId", testrayTask.getTestrayTaskId());

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.fetchTestrayTeam(testrayTeamId);

		renderRequest.setAttribute("testrayTeam", testrayTeam);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "score_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "desc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("score_sortable", true),
			new Sort("name_sortable", true)
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		return search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);
	}

	private AlloySearchResult _searchTestrayCaseResults() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		long testrayCaseTypeId = ParamUtil.getLong(request, "testrayCaseTypeId");

		TestrayCaseType testrayCaseType = TestrayCaseTypeLocalServiceUtil.fetchTestrayCaseType(testrayCaseTypeId);

		renderRequest.setAttribute("testrayCaseType", testrayCaseType);

		String comments = ParamUtil.getString(request, "comments");

		if (Validator.isNotNull(comments)) {
			attributes.put("commentMBMessageBody", comments);
		}

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		if (ArrayUtil.isNotEmpty(priorities)) {
			attributes.put("testrayCasePriority", StringUtil.merge(priorities));
		}

		long[] testrayRunIds = ParamUtil.getLongValues(request, "testrayRunId");

		if (ArrayUtil.isNotEmpty(testrayRunIds)) {
			attributes.put("testrayRunId", StringUtil.merge(testrayRunIds));

			renderRequest.setAttribute("testrayRunIds", ListUtil.toList(testrayRunIds));
		}

		long testraySubtaskId = ParamUtil.getLong(request, "id");

		attributes.put("testraySubtaskId", testraySubtaskId);

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.fetchTestrayTeam(testrayTeamId);

		renderRequest.setAttribute("testrayTeam", testrayTeam);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "testrayCasePriority_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "desc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("testrayCasePriority_sortable", true),
			new Sort("testrayTeamId", true),
			new Sort("testrayComponentName_sortable", false),
			new Sort("testrayCaseName_sortable", false),
			new Sort("testrayRunNumber", false),
			new Sort("status_sortable", false)
		};

		return search(IndexerRegistryUtil.nullSafeGetIndexer(TestrayCaseResult.class), new AlloyServiceInvoker(TestrayCaseResult.class.getName()), request, portletRequest, null, attributes, null, sorts);
	}

	private void _updateTestrayCaseResultAssignedUserId(long testraySubtaskId, long assignedUserId) throws Exception {
		List<TestrayCaseResult> testrayCaseResults = TestrayCaseResultLocalServiceUtil.getTestraySubtaskTestrayCaseResults(testraySubtaskId);

		for (TestrayCaseResult testrayCaseResult : testrayCaseResults) {
			testrayCaseResult.setAssignedUserId(assignedUserId);

			updateModelIgnoreRequest(testrayCaseResult);
		}
	}

	private void _updateUser(TestraySubtask testraySubtask, long assignedUserId) throws Exception {
		TestrayAssignment testrayAssignment = _fetchTestrayAssignment(testraySubtask);

		if (assignedUserId > 0) {
			testrayAssignment.setAssignedUserId(assignedUserId);

			updateModelIgnoreRequest(testrayAssignment);
		}
		else {
			TestrayAssignmentLocalServiceUtil.deleteTestrayAssignment(testrayAssignment);
		}

		indexModel(testraySubtask);

		_updateTestrayCaseResultAssignedUserId(testraySubtask.getTestraySubtaskId(), testrayAssignment.getAssignedUserId());
	}

	private void _validateAssignment(TestraySubtask testraySubtask, boolean allowUnassigned) throws Exception {
		TestrayAssignment testrayAssignment = _fetchTestrayAssignment(testraySubtask);

		if (allowUnassigned && (testrayAssignment.getAssignedUserId() == 0)) {
			return;
		}

		if (user.getUserId() != testrayAssignment.getAssignedUserId()) {
			throw new AlloyException("you-are-not-the-assigned-user", false);
		}
	}

	private void _validateEditStatus(TestraySubtask testraySubtask) throws Exception {
		if (testraySubtask.getStatus() != TestraySubtaskConstants.STATUS_IN_ANALYSIS) {
			throw new AlloyException("the-subtask-status-must-be-in-analysis", false);
		}

		_validateAssignment(testraySubtask, false);
	}

	private void _validateMerge(long testrayTaskId, String[] names) throws Exception {
		if (names.length < 2) {
			throw new AlloyException(translate("please-select-at-least-two-subtasks-to-merge"));
		}

		for (String name : names) {
			TestraySubtask testraySubtask = _getTestraySubtask(testrayTaskId, name);

			_validateMergeableTestraySubtask(testraySubtask);
		}
	}

	private void _validateMerge(long[] testraySubtaskIds) throws Exception {
		if (testraySubtaskIds.length < 2) {
			throw new AlloyException(translate("please-select-at-least-two-subtasks-to-merge"));
		}

		for (long testraySubtaskId : testraySubtaskIds) {
			TestraySubtask testraySubtask = TestraySubtaskLocalServiceUtil.getTestraySubtask(testraySubtaskId);

			_validateMergeableTestraySubtask(testraySubtask);
		}
	}

	private void _validateMergeableTestraySubtask(TestraySubtask testraySubtask) throws Exception {
		_validateAssignment(testraySubtask, true);
	}

	private void _validateSplit(long testrayTaskId, String name, long[] testrayCaseResultIds) throws Exception {
		AlloyServiceInvoker testrayAssignmentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayAssignment.class.getName());

		TestraySubtask testraySubtask = _getTestraySubtask(testrayTaskId, name);

		if (testraySubtask.getStatus() != TestraySubtaskConstants.STATUS_IN_ANALYSIS) {
			throw new AlloyException(translate("subtask-x-must-be-in-analysis-to-be-used-in-a-split", name));
		}

		DynamicQuery testrayAssignmentsDynamicQuery = testrayAssignmentAlloyServiceInvoker.buildDynamicQuery(new Object[] {"classNameId", PortalUtil.getClassNameId(TestraySubtask.class), "classPK", testraySubtask.getTestraySubtaskId()});

		Projection assignedUserIdProjection = ProjectionFactoryUtil.property("assignedUserId");

		testrayAssignmentsDynamicQuery.setProjection(assignedUserIdProjection);

		List<Long> assignedUserIds = testrayAssignmentAlloyServiceInvoker.executeDynamicQuery(testrayAssignmentsDynamicQuery, 0, 1);

		if (assignedUserIds.isEmpty() || ((long)assignedUserIds.get(0) != user.getUserId())) {
			throw new AlloyException(translate("the-subtask-x-must-be-assigned-to-you-to-split", name), false);
		}

		for (long testrayCaseResultId : testrayCaseResultIds) {
			if (!TestraySubtaskLocalServiceUtil.hasTestrayCaseResultTestraySubtask(testrayCaseResultId, testraySubtask.getTestraySubtaskId())) {
				throw new AlloyException(translate("the-case-result-x-is-not-a-part-of-subtask-x", testrayCaseResultId, name), false);
			}
		}

		int testraySubtaskTestrayCaseResultsCount = TestrayCaseResultLocalServiceUtil.getTestraySubtaskTestrayCaseResultsCount(testraySubtask.getTestraySubtaskId());

		if (testrayCaseResultIds.length == testraySubtaskTestrayCaseResultsCount) {
			throw new AlloyException("you-cannot-split-all-case-results-from-a-subtask", false);
		}
		else if (testrayCaseResultIds.length == 0) {
			throw new AlloyException("please-include-at-least-one-case-result-to-split-out", false);
		}
	}

	private void _validateTestraySubtask(long testrayTaskId, String testraySubtaskName) throws Exception {
		_getTestraySubtask(testrayTaskId, testraySubtaskName);
	}

	private void _validateUpdateStatus(TestraySubtask testraySubtask) throws Exception {
		_validateAssignment(testraySubtask, false);
		_validateTestraySubtask(testraySubtask.getTestrayTaskId(), testraySubtask.getName());

		String issues = ParamUtil.getString(request, "issues");
		int status = ParamUtil.getInteger(request, "status");

		if (Validator.isNull(issues) && ((status == TestrayCaseResultConstants.STATUS_FAILED) || (status == TestrayCaseResultConstants.STATUS_TEST_FIX))) {
			throw new AlloyException("please-enter-at-least-one-issue", false);
		}
	}

	private void _validateUpdateUser() throws Exception {
		long assignedUserId = ParamUtil.getLong(request, "assignedUserId", -1);

		if (assignedUserId < 0) {
			throw new AlloyException("a-user-id-is-required", false);
		}
		else if (assignedUserId > 0) {
			User user = UserLocalServiceUtil.fetchUser(assignedUserId);

			if (user == null) {
				throw new AlloyException(translate("the-user-with-id-x-does-not-exist", assignedUserId), false);
			}
		}
	}

	private void _validateView(TestraySubtask testraySubtask) throws Exception {
		if (testraySubtask == null) {
			long testraySubtaskId = ParamUtil.getLong(request, "id");

			throw new AlloyException(translate("the-subtask-with-id-x-does-not-exist", testraySubtaskId), false);
		}
	}

}
%>