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
<%@ include file="/alloy_mvc/jsp/util/testray_task_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayTask.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayBuildId", "testrayCaseTypeIds", "userIds"}, parameterTypes = {String.class, Long.class, String.class, String.class})
	public void add() throws Exception {
		TestrayTask testrayTask = _fetchOrCreateTestrayTask();

		_validateAdd();

		updateModel(testrayTask);

		long[] userIds = ParamUtil.getLongValues(request, "userIds");

		_addAssignments(testrayTask, SetUtil.fromArray(userIds));

		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		long[] testrayCaseTypeIds = ParamUtil.getLongValues(request, "testrayCaseTypeIds");

		TestrayCaseTypeLocalServiceUtil.addTestrayTaskTestrayCaseTypes(testrayTask.getTestrayTaskId(), testrayCaseTypeIds);

		TestrayTaskUtil.addTestraySubtasks(this, testrayBuildId, testrayTask.getTestrayTaskId(), testrayCaseTypeIds);

		_indexTestrayBuild(testrayTask.getTestrayBuildId());

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTaskJSONObject(testrayTask));

			return;
		}

		String viewTestrayTasksURL = TestrayUtil.getURL(this, "subtasks", "index", 0L, "testrayTaskId", testrayTask.getTestrayTaskId());

		redirectTo(viewTestrayTasksURL);
	}

	public void create() throws Exception {
		TestrayTask testrayTask = _fetchOrCreateTestrayTask();

		_validateCreate();

		renderRequest.setAttribute("TestrayPortletKeys", getConstantsBean(TestrayPortletKeys.class));
		renderRequest.setAttribute("TestrayUtil", new TestrayUtil());

		renderRequest.setAttribute("testrayTask", testrayTask);

		renderRequest.setAttribute("testrayCaseTypes", TestrayCaseTypeUtil.getAutomatedTestrayCaseTypes());
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateDelete(testrayTask);

		TestrayTaskUtil.deleteTestrayTask(testrayTask);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateEdit(testrayTask);

		renderRequest.setAttribute("TestrayPortletKeys", getConstantsBean(TestrayPortletKeys.class));

		TestrayTaskComposite testrayTaskComposite = new TestrayTaskComposite(testrayTask, themeDisplay, user);

		renderRequest.setAttribute("testrayTaskComposite", testrayTaskComposite);

		String assignedUserIdsString = StringUtil.merge(testrayTaskComposite.getAssignedUserIds());

		renderRequest.setAttribute("assignedUserIdsString", assignedUserIdsString);

		renderRequest.setAttribute("testrayCaseTypes", TestrayCaseTypeUtil.getAutomatedTestrayCaseTypes());

		List<Long> relevantTestrayCaseTypesIds = TestrayCaseTypeUtil.getTestrayCaseTypesIds(TestrayCaseTypeLocalServiceUtil.getTestrayTaskTestrayCaseTypes(testrayTask.getTestrayTaskId()));

		renderRequest.setAttribute("relevantTestrayCaseTypesIds", relevantTestrayCaseTypesIds);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "assignedUserId", "testrayBuildName", "testrayRoutineName", "name", "statuses", "testrayProjectId", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Long.class, String.class, String.class, String.class, String.class, Long.class, Integer.class})
	public void index() throws Exception {
		if (isRespondingTo("js")) {
			Set<User> userGroupUsers = new HashSet<User>();

			long[] userGroupIds = ParamUtil.getLongValues(request, "userGroupIds");

			for (long userGroupId : userGroupIds) {
				List<User> users = UserLocalServiceUtil.getUserGroupUsers(userGroupId);

				for (User user : users) {
					userGroupUsers.add(user);
				}
			}

			JSONArray userGroupUsersJSONArray = _getUsersJSONArray(userGroupUsers);

			renderRequest.setAttribute("userGroupUsersJSONArray", userGroupUsersJSONArray);

			render("tasks/get_user_group_users_js");

			return;
		}

		renderRequest.setAttribute("TestrayTaskConstants", getConstantsBean(TestrayTaskConstants.class));
		renderRequest.setAttribute("TestrayTaskConstantsMethods", new TestrayTaskConstants());

		AlloySearchResult alloySearchResult = _search();

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTasksJSONArray(alloySearchResult.getBaseModels()));

			return;
		}

		renderRequest.setAttribute("testrayTaskComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayTaskComposite.class, new Class<?>[] {TestrayTask.class, ThemeDisplay.class, User.class}, new Object[] {themeDisplay, user}));

		List<User> users = UserLocalServiceUtil.getUsers(themeDisplay.getCompanyId(), false, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));

		renderRequest.setAttribute("users", users);
	}

	public void listUsers() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		TestrayTaskComposite testrayTaskComposite = new TestrayTaskComposite(testrayTask, themeDisplay, user);

		renderRequest.setAttribute("users", testrayTaskComposite.getAssignedUsers());

		renderRequest.setAttribute("viewOnly", true);

		render("users/select");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void metrics() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		TestrayTaskComposite testrayTaskComposite = new TestrayTaskComposite(testrayTask, themeDisplay, user);

		respondWith(testrayTaskComposite.getTaskCompletionMetricsJSONObject());
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void progress() throws Exception {
		long testrayTaskId = ParamUtil.getLong(request, "id");

		Map<String, Integer> progressCountsMap = TestrayTaskUtil.getTestrayTaskProgressCountsMap(testrayTaskId, themeDisplay);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("completeSubtasks", progressCountsMap.get("completeSubtasks"));
		jsonObject.put("completeTasks", progressCountsMap.get("completeTasks"));
		jsonObject.put("totalSubtasks", progressCountsMap.get("totalSubtasks"));

		respondWith(jsonObject);
	}

	public void selectBuild() throws Exception {
		render("tasks/select_build");
	}

	public void selectUserGroups() throws Exception {
		TestrayAssignmentChecker rowChecker = new TestrayAssignmentChecker(renderResponse);

		rowChecker.setFormName("fm2");

		renderRequest.setAttribute("rowChecker", rowChecker);

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(company.getCompanyId());

		renderRequest.setAttribute("userGroups", userGroups);
		renderRequest.setAttribute("userGroupsCount", userGroups.size());

		render("tasks/select_user_groups");
	}

	public void selectUsers() throws Exception {
		renderRequest.setAttribute("TestrayUtil", new TestrayUtil());

		TestrayAssignmentChecker rowChecker = new TestrayAssignmentChecker(renderResponse);

		rowChecker.setFormName("fm2");

		renderRequest.setAttribute("rowChecker", rowChecker);

		String keywords = ParamUtil.getString(request, "keywords");

		SearchContainer<User> usersSearchContainer = new SearchContainer<User>(portletRequest, portletURL, null, null);

		Sort[] sorts = {
			new Sort("firstName", false),
			new Sort("lastName", false)
		};

		Hits hits = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, null, usersSearchContainer.getStart(), usersSearchContainer.getEnd(), sorts);

		List<User> users = UsersAdminUtil.getUsers(hits);

		renderRequest.setAttribute("users", users);
		renderRequest.setAttribute("usersJSONArray", _getUsersJSONArray(users));

		renderRequest.setAttribute("usersCount", hits.getLength());

		render("tasks/select_users");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name", "userIds"}, parameterTypes = {String.class, String.class, String.class})
	public void update() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateUpdate(testrayTask);

		updateModel(testrayTask);

		long[] userIds = ParamUtil.getLongValues(request, "userIds", null);

		if (userIds != null) {
			_updateAssignments(testrayTask, SetUtil.fromArray(userIds));
		}

		_indexTestrayBuild(testrayTask.getTestrayBuildId());

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTaskJSONObject(testrayTask));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "statusLabel"}, parameterTypes = {String.class, String.class})
	public void updateStatus() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateUpdateStatus(testrayTask);

		String statusLabel = ParamUtil.getString(request, "statusLabel");

		if (Validator.isNotNull(statusLabel)) {
			testrayTask.setStatus(TestrayTaskConstants.getLabelStatus(statusLabel));
			testrayTask.setStatusUpdateDate(new Date());
		}

		updateModelIgnoreRequest(testrayTask);

		_indexTestrayBuild(testrayTask.getTestrayBuildId());

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTaskJSONObject(testrayTask));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "userIds"}, parameterTypes = {Long.class, String.class})
	public void updateUsers() throws Exception {
		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateUpdateUsers(testrayTask);

		long[] userIds = ParamUtil.getLongValues(request, "userIds");

		_addAssignments(testrayTask, SetUtil.fromArray(userIds));

		indexModel(testrayTask);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTaskJSONObject(testrayTask));

			return;
		}

		setOpenerSuccessMessage();

		render("close");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {String.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(request);

		_validateView(testrayTask);

		respondWith(_getTestrayTaskJSONObject(testrayTask));
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayTaskIndexer.getInstance();
	}

	private void _addAssignments(TestrayTask testrayTask, Set<Long> userIds) throws Exception {
		for (long userId : userIds) {
			TestrayAssignment testrayAssignment = TestrayAssignmentLocalServiceUtil.createTestrayAssignment(0);

			testrayAssignment.setAssignedUserId(userId);
			testrayAssignment.setClassName(TestrayTask.class.getName());
			testrayAssignment.setClassPK(testrayTask.getTestrayTaskId());

			updateModelIgnoreRequest(testrayAssignment);
		}
	}

	private void _deleteAssignments(TestrayTask testrayTask) throws Exception {
		AlloyServiceInvoker testrayAssignmentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayAssignment.class.getName());

		List<TestrayAssignment> testrayAssignments = testrayAssignmentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"classNameId", PortalUtil.getClassNameId(TestrayTask.class), "classPK", testrayTask.getTestrayTaskId()});

		for (TestrayAssignment testrayAssignment : testrayAssignments) {
			TestrayAssignmentLocalServiceUtil.deleteTestrayAssignment(testrayAssignment);
		}
	}

	private TestrayTask _fetchOrCreateTestrayTask() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request);

		return TestrayTaskUtil.fetchOrCreateTestrayTask(testrayBuild);
	}

	private JSONObject _getTestrayTaskJSONObject(TestrayTask testrayTask) throws Exception {
		TestrayTaskComposite testrayTaskComposite = new TestrayTaskComposite(testrayTask, themeDisplay, user);

		return testrayTaskComposite.getJSONObject();
	}

	private JSONArray _getTestrayTasksJSONArray(List<BaseModel<?>> baseModels) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (BaseModel<?> baseModel : baseModels) {
			jsonArray.put(_getTestrayTaskJSONObject((TestrayTask)baseModel));
		}

		return jsonArray;
	}

	private JSONObject _getUserJSONObject(User user) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("emailAddress", user.getEmailAddress());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("lastName", user.getLastName());
		jsonObject.put("screenName", user.getScreenName());
		jsonObject.put("userGroupString", TestrayUtil.getUserGroupsString(user));
		jsonObject.put("userId", user.getUserId());

		return jsonObject;
	}

	private JSONArray _getUsersJSONArray(Collection<User> users) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (User user : users) {
			jsonArray.put(_getUserJSONObject(user));
		}

		return jsonArray;
	}

	private void _indexTestrayBuild(long testrayBuildId) throws Exception {
		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.getTestrayBuild(testrayBuildId);

		indexModel(testrayBuild);
	}

	private AlloySearchResult _search() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		long assignedUserId = ParamUtil.getLong(request, "assignedUserId", -1);

		User assignedUser = UserLocalServiceUtil.fetchUser(assignedUserId);

		renderRequest.setAttribute("assignedUser", assignedUser);

		int[] statuses = ParamUtil.getIntegerValues(request, "statuses", new int[] {TestrayTaskConstants.STATUS_IN_ANALYSIS});

		portletURL.setParameter("statuses", ArrayUtil.toStringArray(statuses));

		List<Integer> testrayTaskStatuses = ListUtil.toList(statuses);

		attributes.put("status", StringUtil.merge(testrayTaskStatuses));

		renderRequest.setAttribute("testrayTaskStatuses", testrayTaskStatuses);

		renderRequest.setAttribute("statusLabels", TestrayUtil.getStatusLabels(request, TestrayTaskConstants::getStatusLabel, testrayTaskStatuses));

		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "createDate_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "desc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("createDate_sortable", true)
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		return search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);
	}

	private void _updateAssignments(TestrayTask testrayTask, Set<Long> userIds) throws Exception {
		TestrayTaskUtil.deleteTestrayAssignments(testrayTask);

		_addAssignments(testrayTask, userIds);
	}

	private void _validateAdd() throws Exception {
		_validateCaseTypes();
		_validateName();
		_validateUserIds();

		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);
	}

	private void _validateCaseTypes() throws Exception {
		long[] testrayCaseTypeIds = ParamUtil.getLongValues(request, "testrayCaseTypeIds");

		if (ArrayUtil.isEmpty(testrayCaseTypeIds)) {
			throw new AlloyException("please-mark-at-least-one-case-type-for-processing", false);
		}
	}

	private void _validateCreate() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		List<TestrayTask> testrayTasks = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuildId});

		if (!testrayTasks.isEmpty()) {
			throw new AlloyException("a-task-for-this-build-already-exists", false);
		}

		String testrayBuildIdentifier = ParamUtil.getString(request, "testrayBuildId");

		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request, testrayBuildIdentifier);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);
	}

	private void _validateDelete(TestrayTask testrayTask) throws Exception {
		_validateTestrayTask(testrayTask);

		int status = testrayTask.getStatus();

		if ((status != TestrayTaskConstants.STATUS_COMPLETE) && (status != TestrayTaskConstants.STATUS_ABANDONED)) {
			throw new AlloyException("the-task-must-be-complete-or-abandoned-to-be-deleted", false);
		}
	}

	private void _validateEdit(TestrayTask testrayTask) throws Exception {
		_validateTestrayTask(testrayTask);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name", null);

		if (Validator.isNull(name)) {
			throw new AlloyException("the-task-name-is-invalid", false);
		}

		List<TestrayTask> testrayTasks = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", name});

		if (!testrayTasks.isEmpty()) {
			throw new AlloyException("the-task-name-already-exists", false);
		}
	}

	private void _validateTestrayTask(TestrayTask testrayTask) throws Exception {
		if ((testrayTask == null) || testrayTask.isNew()) {
			String id = ParamUtil.getString(request, "id");

			if (Validator.isNumber(id)) {
				throw new AlloyException(translate("the-task-with-id-x-does-not-exist", GetterUtil.getLong(id)), false);
			}
			else if (id.indexOf(StringPool.UNDERLINE) == 0) {
				throw new AlloyException(translate("the-task-with-name-x-does-not-exist", id.substring(1)), false);
			}
		}
	}

	private void _validateUpdate(TestrayTask testrayTask) throws Exception {
		_validateTestrayTask(testrayTask);
	}

	private void _validateUpdateStatus(TestrayTask testrayTask) throws Exception {
		_validateTestrayTask(testrayTask);

		TestrayValidator.validateTaskStatusLabel(request);
	}

	private void _validateUpdateUsers(TestrayTask testrayTask) throws Exception {
		_validateUserIds();

		List<User> duplicateUsers = new ArrayList<User>();

		AlloyServiceInvoker testrayAssignmentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayAssignment.class.getName());

		DynamicQuery testrayAssignmentDynamicQuery = testrayAssignmentAlloyServiceInvoker.buildDynamicQuery(new Object[] {"classNameId", PortalUtil.getClassNameId(TestrayTask.class), "classPK", testrayTask.getTestrayTaskId()});

		Property assignedUserIdProperty = PropertyFactoryUtil.forName("assignedUserId");

		long[] userIds = ParamUtil.getLongValues(request, "userIds");

		testrayAssignmentDynamicQuery.add(assignedUserIdProperty.in(userIds));

		List<TestrayAssignment> testrayAssignments = testrayAssignmentAlloyServiceInvoker.executeDynamicQuery(testrayAssignmentDynamicQuery);

		for (TestrayAssignment testrayAssignment : testrayAssignments) {
			duplicateUsers.add(UserLocalServiceUtil.getUser(testrayAssignment.getAssignedUserId()));
		}

		if (duplicateUsers.size() == 1) {
			User user = duplicateUsers.get(0);

			throw new AlloyException(translate("the-user-x-is-already-assigned", user.getFullName()), false);
		}
		else if (!duplicateUsers.isEmpty()) {
			throw new AlloyException(translate("the-users-x-are-already-assigned", ListUtil.toString(duplicateUsers, "fullName", StringPool.COMMA_AND_SPACE)), false);
		}
	}

	private void _validateUserIds() throws Exception {
		long[] userIds = ParamUtil.getLongValues(request, "userIds");

		if (userIds.length == 0) {
			throw new AlloyException("please-mark-at-least-one-user-or-user-group-for-assignment", false);
		}
	}

	private void _validateView(TestrayTask testrayTask) throws Exception {
		_validateTestrayTask(testrayTask);
	}

}
%>