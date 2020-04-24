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

<%@ include file="/alloy_mvc/jsp/util/testray_build_indexer.jspf" %>

<%@ include file="/alloy_mvc/jsp/util/testray_build_scheduler_message_listener.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayBuild.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayRoutineId", "testrayFactors", "testrayProductVersionId"}, parameterTypes = {String.class, Long.class, String.class, Long.class})
	public void add() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.createTestrayBuild(0);

		_validateAdd(testrayBuild);

		TestrayUtil.setDateProperties(testrayBuild, request);

		String gitHash = ParamUtil.getString(request, "git-hash");

		testrayBuild.setGitHash(gitHash);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.getTestrayRoutine(testrayRoutineId);

		updateModel(testrayBuild, "testrayProjectId", testrayRoutine.getTestrayProjectId());

		_updateCases(testrayBuild);

		Set<Set<Long>> testrayFactorOptionIdSets = _updateTestrayRuns(testrayBuild);

		if (testrayBuild.getTemplateTestrayBuildId() > 0) {
			_addClonedTestrayRuns(testrayBuild.getTemplateTestrayBuildId(), testrayBuild.getTestrayBuildId(), testrayFactorOptionIdSets);
		}

		if (isRespondingTo("json")) {
			respondWith(_getTestrayBuildJSONObject(testrayBuild));

			return;
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayBuild.getTestrayBuildId(), "testrayProjectId", testrayRoutine.getTestrayProjectId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void addTemplate() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.createTestrayBuild(0);

		_validateAddTemplate(testrayBuild);

		TestrayUtil.setDateProperties(testrayBuild, request);

		testrayBuild.setStatus(TestrayBuildConstants.STATUS_DEFAULT);
		testrayBuild.setTemplate(true);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.getTestrayRoutine(testrayRoutineId);

		updateModel(testrayBuild, "testrayProjectId", testrayRoutine.getTestrayProjectId());

		_updateTestrayRuns(testrayBuild);

		_updateCases(testrayBuild);

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayBuild.getTestrayBuildId(), "testrayProjectId", testrayRoutine.getTestrayProjectId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"testrayRunIdA", "testrayRunIdB"}, parameterTypes = {Long.class, Long.class})
	public void autoFill() throws Exception {
		_validateAutoFill();

		long testrayRunIdA = ParamUtil.getLong(request, "testrayRunIdA");

		TestrayRun testrayRunA = TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdA);

		TestrayBuildComposite testrayBuildCompositeA = new TestrayBuildComposite(TestrayBuildLocalServiceUtil.getTestrayBuild(testrayRunA.getTestrayBuildId()), themeDisplay);

		long testrayRunIdB = ParamUtil.getLong(request, "testrayRunIdB");

		TestrayRun testrayRunB = TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdB);

		TestrayBuildComposite testrayBuildCompositeB = new TestrayBuildComposite(TestrayBuildLocalServiceUtil.getTestrayBuild(testrayRunB.getTestrayBuildId()), themeDisplay);

		int testrayCaseResultsCount = TestrayBuildUtil.autoFill(this, testrayBuildCompositeA, testrayBuildCompositeB);

		String message = translate("x-case-results-were-auto-filled", testrayCaseResultsCount);

		if (isRespondingTo("json")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("count", testrayCaseResultsCount);
			jsonObject.put("message", message);

			respondWith(jsonObject);
		}
		else {
			SessionMessages.add(portletRequest, "requestProcessed", message);

			String redirect = ParamUtil.getString(request, "redirect");

			redirectTo(redirect);
		}
	}

	public void create() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.createTestrayBuild(0);

		renderRequest.setAttribute("HttpUtil", new HttpUtil());
		renderRequest.setAttribute("TestrayPortletKeys", getConstantsBean(TestrayPortletKeys.class));

		_setClonedAttributes(testrayBuild, "templateTestrayBuildId");

		boolean template = ParamUtil.getBoolean(request, "template");

		testrayBuild.setTemplate(template);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		testrayBuild.setTestrayRoutineId(testrayRoutineId);

		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.getTestrayRoutine(testrayRoutineId);

		testrayBuild.setTestrayProjectId(testrayRoutine.getTestrayProjectId());

		renderRequest.setAttribute("testrayBuild", testrayBuild);

		Calendar currentDateCalendar = CalendarFactoryUtil.getCalendar();

		renderRequest.setAttribute("currentDateDay", currentDateCalendar.get(Calendar.DATE));
		renderRequest.setAttribute("currentDateDayOfWeek", currentDateCalendar.get(Calendar.DAY_OF_WEEK));
		renderRequest.setAttribute("currentDateMonth", currentDateCalendar.get(Calendar.MONTH));
		renderRequest.setAttribute("currentDateYear", currentDateCalendar.get(Calendar.YEAR));

		Set<Long> selectedTestrayFactorOptionIds = SetUtil.fromCollection(TestrayFactorUtil.getTestrayFactorOptionIds(TestrayRoutine.class.getName(), testrayRoutineId));

		renderRequest.setAttribute("selectedTestrayFactorOptionIds", selectedTestrayFactorOptionIds);

		List<TestrayRoutine> testrayRoutines = TestrayRoutineUtil.getTestrayRoutines(testrayBuild.getTestrayProjectId());

		renderRequest.setAttribute("testrayRoutines", testrayRoutines);

		AlloyServiceInvoker testrayCaseAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCase.class.getName());

		long testrayCasesCount = testrayCaseAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayBuild.getTestrayProjectId()});

		renderRequest.setAttribute("testrayCasesCount", testrayCasesCount);

		Map<TestrayFactorCategory, List<TestrayFactorOption>> testrayFactorCategoryMap = TestrayFactorCategoryUtil.getTestrayFactorCategoryMap(TestrayRoutine.class.getName(), testrayBuild.getTestrayRoutineId());

		renderRequest.setAttribute("testrayFactorCategoryMap", testrayFactorCategoryMap);

		AlloyServiceInvoker testrayProductVersionAlloyServiceInvoker = new AlloyServiceInvoker(TestrayProductVersion.class.getName());

		List<TestrayProductVersion> testrayProductVersions = testrayProductVersionAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayBuild.getTestrayProjectId()});

		renderRequest.setAttribute("testrayProductVersions", testrayProductVersions);

		AlloyServiceInvoker testraySuiteAlloyServiceInvoker = new AlloyServiceInvoker(TestraySuite.class.getName());

		long testraySuitesCount = testraySuiteAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayBuild.getTestrayProjectId()});

		renderRequest.setAttribute("testraySuitesCount", testraySuitesCount);

		String redirect = ParamUtil.getString(request, "redirect");

		renderRequest.setAttribute("redirect", redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateDelete(testrayBuild);

		if (testrayBuild.isTemplate()) {
			List<TestrayBuild> testrayBuilds = alloyServiceInvoker.executeDynamicQuery(new Object[] {"templateTestrayBuildId", testrayBuild.getTestrayBuildId()});

			for (TestrayBuild childTestrayBuild : testrayBuilds) {
				childTestrayBuild.setTemplateTestrayBuildId(0);

				updateModelIgnoreRequest(childTestrayBuild);
			}
		}

		TestrayBuildUtil.deleteTestrayBuild(testrayBuild);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateEdit(testrayBuild);

		renderRequest.setAttribute("HttpUtil", new HttpUtil());
		renderRequest.setAttribute("TestrayPortletKeys", getConstantsBean(TestrayPortletKeys.class));

		boolean template = ParamUtil.getBoolean(request, "template");

		testrayBuild.setTemplate(template);

		renderRequest.setAttribute("testrayBuild", testrayBuild);

		List<TestrayRoutine> testrayRoutines = TestrayRoutineUtil.getTestrayRoutines(testrayBuild.getTestrayProjectId());

		renderRequest.setAttribute("testrayRoutines", testrayRoutines);

		String testrayCasesOrderByCol = ParamUtil.getString(request, "testrayCasesOrderByCol", "priority");

		renderRequest.setAttribute("testrayCasesOrderByCol", testrayCasesOrderByCol);

		String testrayCasesOrderByType = ParamUtil.getString(request, "testrayCasesOrderByType", "asc");

		renderRequest.setAttribute("testrayCasesOrderByType", testrayCasesOrderByType);

		OrderByComparator testrayCasesOBC = OrderByComparatorFactoryUtil.create(TestrayCaseModelImpl.TABLE_NAME, testrayCasesOrderByCol, testrayCasesOrderByType.equals("asc"), "name", true);

		List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestrayBuildTestrayCases(testrayBuild.getTestrayBuildId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, testrayCasesOBC);

		renderRequest.setAttribute("testrayCases", testrayCases);

		List<Long> testrayCaseIds = new ArrayList<Long>();

		for (TestrayCase testrayCase : testrayCases) {
			testrayCaseIds.add(testrayCase.getTestrayCaseId());
		}

		renderRequest.setAttribute("testrayCaseIds", StringUtil.merge(testrayCaseIds));

		Map<TestrayFactorCategory, List<TestrayFactorOption>> testrayFactorCategoryMap = TestrayFactorCategoryUtil.getTestrayFactorCategoryMap(TestrayRoutine.class.getName(), testrayBuild.getTestrayRoutineId());

		renderRequest.setAttribute("testrayFactorCategoryMap", testrayFactorCategoryMap);

		List<Long> testrayFactorCategoryIds = new ArrayList<>();

		for (TestrayFactorCategory testrayFactorCategory : testrayFactorCategoryMap.keySet()) {
			testrayFactorCategoryIds.add(testrayFactorCategory.getTestrayFactorCategoryId());
		}

		renderRequest.setAttribute("testrayFactorCategoryIds", testrayFactorCategoryIds);

		AlloyServiceInvoker testrayProductVersionAlloyServiceInvoker = new AlloyServiceInvoker(TestrayProductVersion.class.getName());

		List<TestrayProductVersion> testrayProductVersions = testrayProductVersionAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayBuild.getTestrayProjectId()});

		renderRequest.setAttribute("testrayProductVersions", testrayProductVersions);

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		SearchContainer<TestrayRun> testrayRunsSearchContainer = new SearchContainer<TestrayRun>(portletRequest, null, null, "curTestrayRuns", 0, 0, portletURL, null, null);

		String testrayRunsOrderByCol = ParamUtil.getString(request, "testrayRunsOrderByCol", "name");

		renderRequest.setAttribute("testrayRunsOrderByCol", testrayRunsOrderByCol);

		String testrayRunsOrderByType = ParamUtil.getString(request, "testrayRunsOrderByType", "asc");

		renderRequest.setAttribute("testrayRunsOrderByType", testrayRunsOrderByType);

		OrderByComparator testrayRunsOBC = OrderByComparatorFactoryUtil.create(TestrayRunModelImpl.TABLE_NAME, testrayRunsOrderByCol, testrayRunsOrderByType.equals("asc"));

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()}, testrayRunsSearchContainer.getStart(), testrayRunsSearchContainer.getEnd(), testrayRunsOBC);

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayTeamId", 0L);

		renderRequest.setAttribute("testrayRunComposites", TestrayCompositeUtil.getComposites(testrayRuns, TestrayRunComposite.class, new Class<?>[] {TestrayRun.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties}));

		long testrayRunsCount = testrayRunAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		renderRequest.setAttribute("testrayRunsCount", testrayRunsCount);

		AlloyServiceInvoker testraySuiteAlloyServiceInvoker = new AlloyServiceInvoker(TestraySuite.class.getName());

		long testraySuitesCount = testraySuiteAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayBuild.getTestrayProjectId()});

		renderRequest.setAttribute("testraySuitesCount", testraySuitesCount);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"archived", "cur", "delta", "end", "name", "start", "testrayRoutineId"}, parameterTypes = {Boolean.class, Integer.class, Integer.class, Integer.class, String.class, Integer.class, Long.class})
	public void index() throws Exception {
		renderRequest.setAttribute("TestrayBuildConstants", getConstantsBean(TestrayBuildConstants.class));
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));
		renderRequest.setAttribute("TestrayTaskConstants", getConstantsBean(TestrayTaskConstants.class));
		renderRequest.setAttribute("TestrayTaskConstantsMethods", new TestrayTaskConstants());

		int cur = ParamUtil.getInteger(request, "cur", 1);

		renderRequest.setAttribute("cur", cur);

		int delta = ParamUtil.getInteger(request, "delta", 75);

		renderRequest.setAttribute("delta", delta);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "createDate_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "desc");

		renderRequest.setAttribute("orderByType", orderByType);

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		int[] testrayTaskStatuses = ParamUtil.getIntegerValues(request, "testrayTaskStatuses");

		List<Integer> testrayTaskStatusesList = ListUtil.toList(testrayTaskStatuses);

		renderRequest.setAttribute("testrayTaskStatuses", testrayTaskStatusesList);
		renderRequest.setAttribute("testrayTaskStatusLabels", TestrayUtil.getStatusLabels(request, TestrayTaskConstants::getStatusLabel, testrayTaskStatusesList));

		if (ArrayUtil.isNotEmpty(testrayTaskStatuses)) {
			attributes.put("testrayTaskStatus", StringUtil.merge(testrayTaskStatuses));
		}

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.getTestrayRoutine(testrayRoutineId);

		attributes.put("testrayProjectId", testrayRoutine.getTestrayProjectId());

		renderRequest.setAttribute("testrayRoutine", testrayRoutine);

		attributes.put("template", false);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("createDate_sortable", true),
			new Sort("testrayProductVersionName_sortable", false),
			new Sort("name_sortable", false)
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayBuildsJSONArray(alloySearchResult.getBaseModels()));

			return;
		}

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long[] testrayCaseTypeIds = ParamUtil.getLongValues(request, "testrayCaseTypeId");

		testrayCaseResultProperties.put("testrayCaseTypeId", StringUtil.merge(testrayCaseTypeIds));

		renderRequest.setAttribute("testrayCaseTypeIds", ListUtil.toList(testrayCaseTypeIds));

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		renderRequest.setAttribute("testrayTeamId", testrayTeamId);

		renderRequest.setAttribute("testrayBuildComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayBuildComposite.class, new Class<?>[] {TestrayBuild.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties}));

		List<TestrayBuild> templateTestrayBuilds = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayRoutineId", testrayRoutineId, "template", true, "status", TestrayBuildConstants.STATUS_DEFAULT});

		renderRequest.setAttribute("templateTestrayBuildComposites", TestrayCompositeUtil.getComposites(templateTestrayBuilds, TestrayBuildComposite.class, new Class<?>[] {TestrayBuild.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties}));

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		AlloyServiceInvoker testrayProductVersionAlloyServiceInvoker = new AlloyServiceInvoker(TestrayProductVersion.class.getName());

		List<TestrayProductVersion> testrayProductVersions = testrayProductVersionAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute("testrayProductVersions", testrayProductVersions);

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayProjectId);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	public void latestBuildComponents() throws Exception {
		PortletURL redirectURL = getPortletURL("case_results", "components", portletRequest.getPortletMode(), PortletRequest.RENDER_PHASE);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayRun testrayRun = _getLatestTestrayRun(testrayRoutineId);

		redirectURL.setParameter("testrayRunId", String.valueOf(testrayRun.getTestrayRunId()));

		renderRequest.setAttribute("redirectURL", redirectURL);

		render("redirect");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"priorities", "testrayRoutineId"}, parameterTypes = {Integer[].class, Long.class})
	public void latestBuildGroupedByComponents() throws Exception {
		if (!isRespondingTo()) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));
		testrayCaseResultProperties.put("testrayTeamId", 0L);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayBuild testrayBuild = TestrayBuildUtil.getLatestTestrayBuild(testrayRoutineId);

		TestrayBuildComposite testrayBuildComposite = new TestrayBuildComposite(testrayBuild, themeDisplay, testrayCaseResultProperties);

		TestrayCaseResultReporter testrayBuildTestrayCaseResultReporter = testrayBuildComposite.getTestrayCaseResultReporter();

		Map<String, Integer> testrayBuildResults = testrayBuildTestrayCaseResultReporter.getTestrayCaseResultStatusCounts();

		jsonObject.put("testrayBuildResults", JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(testrayBuildResults)));

		JSONObject testrayRunJSONObject = JSONFactoryUtil.createJSONObject();

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		for (TestrayRun testrayRun : testrayRuns) {
			JSONObject testrayComponentsJSONObject = JSONFactoryUtil.createJSONObject();

			List<TestrayComponent> testrayComponents = TestrayComponentUtil.getTestrayComponents(0, "testrayRunId", testrayRun.getTestrayRunId());

			testrayCaseResultProperties = new HashMap<String, Serializable>();

			testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));
			testrayCaseResultProperties.put("testrayRunId", testrayRun.getTestrayRunId());

			for (TestrayComponent testrayComponent : testrayComponents) {
				TestrayComponentComposite testrayComponentComposite = new TestrayComponentComposite(testrayComponent, themeDisplay, testrayCaseResultProperties);

				TestrayCaseResultReporter testrayCompositeTestrayCaseResultReporter = testrayComponentComposite.getTestrayCaseResultReporter();

				Map<String, Integer> testrayCompositeResults = testrayCompositeTestrayCaseResultReporter.getTestrayCaseResultStatusCounts();

				testrayComponentsJSONObject.put(testrayComponent.getName(), JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(testrayCompositeResults)));
			}

			testrayCaseResultProperties = new HashMap<String, Serializable>();

			testrayCaseResultProperties.put("testrayTeamId", 0L);

			TestrayRunComposite testrayRunComposite = new TestrayRunComposite(testrayRun, themeDisplay, testrayCaseResultProperties);

			testrayRunJSONObject.put(testrayRunComposite.getTitle(), testrayComponentsJSONObject);
		}

		jsonObject.put("testrayRunResults", testrayRunJSONObject);

		respondWith(jsonObject);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"testrayRoutineId"}, parameterTypes = {Long.class})
	public void latestBuildResults() throws Exception {
		if (!isRespondingTo()) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayBuild testrayBuild = TestrayBuildUtil.getLatestTestrayBuild(testrayRoutineId);

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayBuildId", testrayBuild.getTestrayBuildId());

		TestrayCaseResultReporter testrayBuildTestrayCaseResultReporter = new TestrayCaseResultReporter(themeDisplay, attributes);

		Map<String, Integer> testrayBuildResults = testrayBuildTestrayCaseResultReporter.getTestrayCaseResultStatusCounts();

		jsonObject.put("testrayBuildResults", JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(testrayBuildResults)));

		JSONArray testrayRunResultsJSONArray = JSONFactoryUtil.createJSONArray();

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		for (TestrayRun testrayRun : testrayRuns) {
			TestrayRunComposite testrayRunComposite = new TestrayRunComposite(testrayRun, themeDisplay, new HashMap<>(testrayCaseResultProperties));

			testrayRunResultsJSONArray.put(testrayRunComposite.getDashboardJSONObject());
		}

		jsonObject.put("testrayRunResults", testrayRunResultsJSONArray);

		respondWith(jsonObject);
	}

	public void templates() throws Exception {
		renderRequest.setAttribute("TestrayBuildConstants", getConstantsBean(TestrayBuildConstants.class));
		renderRequest.setAttribute("TestrayBuildConstantsMethods", new TestrayBuildConstants());

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		int templateStatus = ParamUtil.getInteger(request, "templateStatus");

		attributes.put("status", templateStatus);

		attributes.put("template", true);

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.getTestrayRoutine(testrayRoutineId);

		renderRequest.setAttribute("testrayRoutine", testrayRoutine);

		attributes.put("testrayProjectId", testrayRoutine.getTestrayProjectId());

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc"))
		};

		AlloySearchResult alloySearchResult = search(attributes, null, sorts);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		renderRequest.setAttribute("templateTestrayBuildComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayBuildComposite.class, new Class<?>[] {TestrayBuild.class, ThemeDisplay.class}, new Object[] {themeDisplay}));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayRoutineId", "testrayFactors", "testrayProductVersionId"}, parameterTypes = {String.class, Long.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateUpdate(testrayBuild);

		TestrayUtil.setDateProperties(testrayBuild, request);

		String gitHash = ParamUtil.getString(request, "git-hash");

		testrayBuild.setGitHash(gitHash);

		updateModel(testrayBuild);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayBuildJSONObject(testrayBuild));

			return;
		}

		_updateCases(testrayBuild);
		_updateExistingTestrayRuns(testrayBuild);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"archived", "id"}, parameterTypes = {Boolean.class, Long.class})
	public void updateArchived() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateUpdateArchived(testrayBuild);

		boolean archived = ParamUtil.getBoolean(request, "archived");

		TestrayBuildUtil.setArchived(this, testrayBuild, archived, true);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayBuildJSONObject(testrayBuild));

			return;
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayBuild.getTestrayBuildId(), "archived", archived, "testrayProjectId", testrayBuild.getTestrayProjectId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void updateCases() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "id");

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		Set<Long> testrayCaseIds = SetUtil.fromArray(addTestrayCaseIds);

		long[] addTestraySuiteIds = ParamUtil.getLongValues(request, "addTestraySuiteIds");

		for (long testraySuiteId : addTestraySuiteIds) {
			List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestraySuiteTestrayCases(testraySuiteId);

			for (TestrayCase testrayCase : testrayCases) {
				testrayCaseIds.add(testrayCase.getTestrayCaseId());
			}
		}

		for (long testrayCaseId : testrayCaseIds) {
			TestrayValidator.validateTestrayCase(testrayBuildId, testrayCaseId);

			if (!TestrayBuildLocalServiceUtil.hasTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId)) {
				TestrayBuildLocalServiceUtil.addTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId);
			}
		}

		WindowState windowState = actionRequest.getWindowState();

		if (windowState.equals(LiferayWindowState.POP_UP)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			String redirect = ParamUtil.getString(request, "redirect");

			redirectTo(redirect);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "promoted"}, parameterTypes = {Long.class, Boolean.class})
	public void updatePromoted() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateUpdatePromoted(testrayBuild);

		boolean promoted = ParamUtil.getBoolean(request, "promoted");

		testrayBuild.setPromoted(promoted);

		updateModelIgnoreRequest(testrayBuild);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayBuildJSONObject(testrayBuild));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void updateStatus() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateUpdateStatus(testrayBuild);

		int status = ParamUtil.getInteger(request, "status");

		testrayBuild.setStatus(status);

		updateModelIgnoreRequest(testrayBuild);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void updateTemplate() throws Exception {
		TestrayBuild testrayBuild = _fetchTestrayBuild();

		_validateUpdateTemplate(testrayBuild);

		testrayBuild.setTemplate(true);

		updateModel(testrayBuild);

		_updateTestrayRuns(testrayBuild);

		_updateCases(testrayBuild);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {String.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request, request.getParameter("id"));

		_validateView(testrayBuild);

		respondWith(_getTestrayBuildJSONObject(testrayBuild));
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayBuildIndexer.getInstance();
	}

	@Override
	protected MessageListener buildSchedulerMessageListener() {
		return TestrayBuildSchedulerMessageListener.getInstance(this);
	}

	@Override
	protected Trigger getSchedulerTrigger() {
		return TriggerFactoryUtil.createTrigger(getSchedulerJobName(), getMessageListenerGroupName(), PortletPropsValues.TESTRAY_CRON_TRIGGER_BUILDS_CONTROLLER);
	}

	private void _addClonedTestrayRuns(long templateTestrayBuildId, long testrayBuildId, Set<Set<Long>> existingTestrayFactorOptionIdSets) throws Exception {
		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> templateTestrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", templateTestrayBuildId});

		for (TestrayRun templateTestrayRun : templateTestrayRuns) {
			List<Long> testrayFactorOptionIds = TestrayFactorUtil.getTestrayFactorOptionIds(TestrayRun.class.getName(), templateTestrayRun.getTestrayRunId());

			Set<Long> testrayFactorOptionIdSet = new HashSet<Long>(testrayFactorOptionIds);

			boolean uniqueTestrayFactorCombination = existingTestrayFactorOptionIdSets.add(testrayFactorOptionIdSet);

			if (!uniqueTestrayFactorCombination) {
				continue;
			}

			TestrayRun testrayRun = TestrayRunLocalServiceUtil.createTestrayRun(increment());

			testrayRun.setExternalReferencePK(String.valueOf(testrayRun.getTestrayRunId()));
			testrayRun.setName(String.valueOf(testrayRun.getTestrayRunId()));

			testrayRun.setNumber(TestrayRunUtil.getNumber(testrayFactorOptionIdSet, testrayBuildId));

			testrayRun.setTestrayBuildId(testrayBuildId);

			List<TestrayFactor> templateTestrayFactors = TestrayFactorUtil.getTestrayFactors(TestrayRun.class.getName(), templateTestrayRun.getTestrayRunId());

			for (TestrayFactor templateTestrayFactor : templateTestrayFactors) {
				long testrayFactorOptionId = ParamUtil.getLong(request, "testrayFactorOptionId_column" + templateTestrayFactor.getTestrayFactorCategoryId() + "_existing" + templateTestrayRun.getTestrayRunId(), -1);

				if (testrayFactorOptionId < 0) {
					break;
				}

				TestrayFactor testrayFactor = TestrayFactorUtil.createTestrayFactor(TestrayRun.class.getName(), testrayRun.getTestrayRunId(), templateTestrayFactor.getTestrayFactorCategoryId(), null, testrayFactorOptionId, null);

				updateModelIgnoreRequest(testrayFactor);
			}

			List<TestrayFactor> testrayFactors = TestrayFactorUtil.getTestrayFactors(TestrayRun.class.getName(), testrayRun.getTestrayRunId());

			if (!testrayFactors.isEmpty()) {
				String environmentHash = TestrayRunUtil.getEnvironmentHash(testrayRun.getTestrayRunId());

				testrayRun.setEnvironmentHash(environmentHash);

				updateModelIgnoreRequest(testrayRun, "testrayRunId", testrayRun.getTestrayRunId());

				List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestrayBuildTestrayCases(testrayBuildId);

				for (TestrayCase testrayCase : testrayCases) {
					TestrayCaseResultUtil.addTestrayCaseResult(this, testrayRun.getTestrayRunId(), testrayCase.getTestrayCaseId());
				}
			}
		}
	}

	private void _deleteTestrayCases(TestrayBuild testrayBuild) throws Exception {
		List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestrayBuildTestrayCases(testrayBuild.getTestrayBuildId());

		TestrayCaseLocalServiceUtil.deleteTestrayBuildTestrayCases(testrayBuild.getTestrayBuildId(), testrayCases);
	}

	private TestrayBuild _fetchTestrayBuild() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "id");

		return TestrayBuildLocalServiceUtil.fetchTestrayBuild(testrayBuildId);
	}

	private TestrayRun _getLatestTestrayRun(long testrayRoutineId) throws Exception {
		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		TestrayBuild testrayBuild = TestrayBuildUtil.getLatestTestrayBuild(testrayRoutineId);

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		if (testrayRuns.isEmpty()) {
			throw new AlloyException("there-are-no-results-for-the-latest-build", false);
		}
		else if (testrayRuns.size() == 1) {
			return testrayRuns.get(0);
		}
		else {
			TestrayEnvironmentStackComposite defaultTestrayEnvironmentStackComposite = new TestrayEnvironmentStackComposite(TestrayRoutine.class, testrayBuild.getTestrayRoutineId());

			return _getMatchingTestrayRun(testrayRuns, defaultTestrayEnvironmentStackComposite);
		}
	}

	private TestrayRun _getMatchingTestrayRun(List<TestrayRun> testrayRuns, TestrayEnvironmentStackComposite defaultTestrayEnvironmentStackComposite) throws Exception {
		for (TestrayRun testrayRun : testrayRuns) {
			TestrayEnvironmentStackComposite testrayEnvironmentStackComposite = new TestrayEnvironmentStackComposite(TestrayRun.class, testrayRun.getTestrayRunId());

			if (testrayEnvironmentStackComposite.equals(defaultTestrayEnvironmentStackComposite)) {
				return testrayRun;
			}
		}

		throw new AlloyException("there-are-no-runs-matching-the-routine-default-environment-stack", false);
	}

	private JSONObject _getTestrayBuildJSONObject(TestrayBuild testrayBuild) throws Exception {
		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		TestrayBuildComposite testrayBuildComposite = new TestrayBuildComposite(testrayBuild, themeDisplay, testrayCaseResultProperties);

		testrayBuildComposite.setHtmlURL(TestrayUtil.getURL(this, "runs", null, 0L, "testrayBuildId", testrayBuild.getTestrayBuildId()));

		return testrayBuildComposite.getJSONObject();
	}

	private JSONArray _getTestrayBuildsJSONArray(List<BaseModel<?>> baseModels) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (BaseModel<?> baseModel : baseModels) {
			jsonArray.put(_getTestrayBuildJSONObject((TestrayBuild)baseModel));
		}

		return jsonArray;
	}

	private void _setClonedAttributes(TestrayBuild testrayBuild, String paramName) throws Exception {
		long cloneableTestrayBuildId = ParamUtil.getLong(request, paramName);

		TestrayBuild cloneableTestrayBuild = TestrayBuildLocalServiceUtil.fetchTestrayBuild(cloneableTestrayBuildId);

		if (cloneableTestrayBuild != null) {
			testrayBuild.setDescription(cloneableTestrayBuild.getDescription());
			testrayBuild.setTemplateTestrayBuildId(cloneableTestrayBuildId);
			testrayBuild.setTestrayRoutineId(cloneableTestrayBuild.getTestrayRoutineId());

			String testrayCasesOrderByCol = ParamUtil.getString(request, "testrayCasesOrderByCol", "priority");

			renderRequest.setAttribute("testrayCasesOrderByCol", testrayCasesOrderByCol);

			String testrayCasesOrderByType = ParamUtil.getString(request, "testrayCasesOrderByType", "asc");

			renderRequest.setAttribute("testrayCasesOrderByType", testrayCasesOrderByType);

			OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseModelImpl.TABLE_NAME, testrayCasesOrderByCol, testrayCasesOrderByType.equals("asc"), "name", true);

			List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestrayBuildTestrayCases(cloneableTestrayBuildId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

			renderRequest.setAttribute("testrayCases", testrayCases);

			List<Long> testrayCaseIds = new ArrayList<Long>();

			for (TestrayCase testrayCase : testrayCases) {
				testrayCaseIds.add(testrayCase.getTestrayCaseId());
			}

			renderRequest.setAttribute("testrayCaseIds", StringUtil.merge(testrayCaseIds));

			AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

			List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", cloneableTestrayBuildId});

			Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

			renderRequest.setAttribute("testrayRunComposites", TestrayCompositeUtil.getComposites(testrayRuns, TestrayRunComposite.class, new Class<?>[] {TestrayRun.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties}));
		}
	}

	private void _updateCases(TestrayBuild testrayBuild) throws Exception {
		_deleteTestrayCases(testrayBuild);

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		Set<Long> testrayCaseIds = SetUtil.fromArray(addTestrayCaseIds);

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		for (long testrayCaseId : testrayCaseIds) {
			TestrayValidator.validateTestrayCase(testrayBuild.getTestrayBuildId(), testrayCaseId);

			TestrayBuildLocalServiceUtil.addTestrayCaseTestrayBuild(testrayCaseId, testrayBuild.getTestrayBuildId());

			for (TestrayRun testrayRun : testrayRuns) {
				TestrayCaseResultUtil.addTestrayCaseResult(this, testrayRun.getTestrayRunId(), testrayCaseId);
			}
		}
	}

	private void _updateExistingTestrayRuns(TestrayBuild testrayBuild) throws Exception {
		Map<Long, String> defaultTestrayFactorCategoriesMap = TestrayFactorUtil.getDefaultTestrayFactorCategoriesMap(testrayBuild.getTestrayRoutineId());

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		for (TestrayRun testrayRun : testrayRuns) {
			boolean deleteRun = ParamUtil.getBoolean(request, "delete-" + testrayRun.getTestrayRunId());

			if (deleteRun) {
				TestrayRunUtil.deleteTestrayRun(testrayRun);
			}
			else {
				Set<Long> modifiedTestrayFactorCategoryIds = new HashSet<>(defaultTestrayFactorCategoriesMap.keySet());

				List<TestrayFactor> testrayFactors = TestrayFactorUtil.getTestrayFactors(TestrayRun.class.getName(), testrayRun.getTestrayRunId());

				for (TestrayFactor testrayFactor : testrayFactors) {
					long testrayFactorOptionId = ParamUtil.getLong(request, "testrayFactorOptionName_column" + testrayFactor.getTestrayFactorCategoryId() + "_existing" + testrayRun.getTestrayRunId(), -1);

					if (modifiedTestrayFactorCategoryIds.contains(testrayFactor.getTestrayFactorCategoryId()) && (testrayFactorOptionId == testrayFactor.getTestrayFactorOptionId())) {
						modifiedTestrayFactorCategoryIds.remove(testrayFactor.getTestrayFactorCategoryId());
					}
					else {
						TestrayFactorLocalServiceUtil.deleteTestrayFactor(testrayFactor);
					}
				}

				for (long modifiedTestrayFactorCategoryId : modifiedTestrayFactorCategoryIds) {
					long testrayFactorOptionId = ParamUtil.getLong(request, "testrayFactorOptionName_column" + modifiedTestrayFactorCategoryId + "_existing" + testrayRun.getTestrayRunId(), -1);

					if (testrayFactorOptionId >= 0) {
						TestrayFactorUtil.addTestrayFactor(this, TestrayRun.class.getName(), testrayRun.getTestrayRunId(), modifiedTestrayFactorCategoryId, defaultTestrayFactorCategoriesMap.get(modifiedTestrayFactorCategoryId), testrayFactorOptionId, null);
					}
				}

				updateModelIgnoreRequest(TestrayRunLocalServiceUtil.getTestrayRun(testrayRun.getTestrayRunId()), "environmentHash", TestrayRunUtil.getEnvironmentHash(testrayRun.getTestrayRunId()));
			}
		}
	}

	private Set<Set<Long>> _updateTestrayRuns(TestrayBuild testrayBuild) throws Exception {
		Set<Set<Long>> testrayFactorOptionIdSets = new HashSet<Set<Long>>();

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		for (TestrayRun testrayRun : testrayRuns) {
			Set<Long> testrayFactorOptionIds = new HashSet<Long>();

			List<TestrayFactor> testrayFactors = TestrayFactorUtil.getTestrayFactors(TestrayRun.class.getName(), testrayRun.getTestrayRunId());

			for (TestrayFactor testrayFactor : testrayFactors) {
				long testrayFactorOptionId = ParamUtil.getLong(request, "testrayFactorOptionId_column" + testrayFactor.getTestrayFactorCategoryId() + "_existing" + testrayRun.getTestrayRunId(), -1);

				if (testrayFactorOptionId < 0) {
					TestrayRunUtil.deleteTestrayRun(testrayRun);

					break;
				}

				testrayFactorOptionIds.add(testrayFactor.getTestrayFactorOptionId());
			}

			testrayFactorOptionIdSets.add(testrayFactorOptionIds);
		}

		int testrayFactorCategoriesCount = ParamUtil.getInteger(request, "testrayFactorCategoriesCount");

		List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestrayBuildTestrayCases(testrayBuild.getTestrayBuildId());

		int[] testrayFactorIndexes = ParamUtil.getIntegerValues(request, "testrayFactorIndexes");

		for (int testrayFactorIndex : testrayFactorIndexes) {
			TestrayRun testrayRun = TestrayRunUtil.addTestrayRun(this, testrayBuild.getTestrayBuildId(), testrayFactorCategoriesCount, testrayFactorOptionIdSets, "_new", testrayFactorIndex);

			if (testrayRun == null) {
				continue;
			}

			for (TestrayCase testrayCase : testrayCases) {
				TestrayCaseResultUtil.addTestrayCaseResult(this, testrayRun.getTestrayRunId(), testrayCase.getTestrayCaseId());
			}
		}

		return testrayFactorOptionIdSets;
	}

	private void _validateAdd(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateClassIdentifier(request, TestrayProductVersion.class, "testrayProductVersionId", "the-product-version-is-invalid");
		TestrayValidator.validateName(request, "testrayRoutineId", TestrayBuild.class.getName(), "testrayBuildId", "the-build-name-is-invalid", "the-build-name-already-exists");
		TestrayValidator.validateTestrayRoutine(request);
		TestrayValidator.validateTestrayFactors(request, testrayBuild, false);
	}

	private void _validateAddTemplate(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateName(request, "testrayRoutineId", TestrayBuild.class.getName(), "testrayBuildId", "the-build-name-is-invalid", "the-build-name-already-exists");

		boolean noTestrayCases = false;

		try {
			TestrayValidator.validateTestrayCases(request);
		}
		catch (AlloyException ae) {
			noTestrayCases = true;
		}

		boolean noTestrayFactors = false;

		try {
			TestrayValidator.validateTestrayFactors(request, testrayBuild, true);
		}
		catch (IllegalArgumentException iae) {
			noTestrayFactors = true;
		}

		if (noTestrayCases && noTestrayFactors) {
			throw new AlloyException("at-least-one-case-or-environment-stack-is-required", false);
		}
	}

	private void _validateAutoFill() throws Exception {
		TestrayValidator.validateTestrayRuns(request, themeDisplay);
	}

	private void _validateDelete(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");
	}

	private void _validateEdit(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");

		boolean template = ParamUtil.getBoolean(request, "template");

		if (template) {
			_validateTemplate(testrayBuild);
		}
	}

	private void _validateTemplate(TestrayBuild testrayBuild) throws Exception {
		if (!testrayBuild.isNew() && !testrayBuild.isTemplate()) {
			throw new AlloyException("the-build-is-not-a-valid-template", false);
		}
	}

	private void _validateUpdate(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");

		TestrayValidator.validateClassIdentifier(request, TestrayProductVersion.class, "testrayProductVersionId", "the-product-version-is-invalid");
		TestrayValidator.validateName(request, "testrayRoutineId", TestrayBuild.class.getName(), "testrayBuildId", "the-build-name-is-invalid", "the-build-name-already-exists");
	}

	private void _validateUpdateArchived(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");

		boolean archived = ParamUtil.getBoolean(request, "archived");

		if (archived && TestrayBuildUtil.isArchived(testrayBuild)) {
			throw new AlloyException("the-build-is-already-archived");
		}
		else if (!archived && !TestrayBuildUtil.isArchived(testrayBuild)) {
			throw new AlloyException("the-build-is-already-unarchived");
		}

		TestrayTask testrayTask = TestrayTaskUtil.fetchTestrayTask(testrayBuild.getTestrayBuildId());

		if ((testrayTask != null) && (testrayTask.getStatus() == TestrayTaskConstants.STATUS_IN_ANALYSIS)) {
			throw new AlloyException("cannot-archive-a-build-that-is-in-analysis", false);
		}
	}

	private void _validateUpdatePromoted(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");
	}

	private void _validateUpdateStatus(TestrayBuild testrayBuild) throws Exception {
		int status = ParamUtil.getInteger(request, "status");

		if (testrayBuild.isTemplate() && (status == TestrayBuildConstants.STATUS_ARCHIVED)) {
			throw new AlloyException("a-template-cannot-be-archived", false);
		}
		else if (!testrayBuild.isTemplate() && (status == TestrayBuildConstants.STATUS_DEACTIVATED)) {
			throw new AlloyException("a-build-cannot-be-deactivated", false);
		}
	}

	private void _validateUpdateTemplate(TestrayBuild testrayBuild) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayBuild, "the-build-with-id-x-does-not-exist", "id");

		_validateTemplate(testrayBuild);

		TestrayValidator.validateName(request, "testrayRoutineId", TestrayBuild.class.getName(), "testrayBuildId", "the-build-name-is-invalid", "the-build-name-already-exists");

		boolean noTestrayCases = false;

		try {
			TestrayValidator.validateTestrayCases(request);
		}
		catch (AlloyException ae) {
			noTestrayCases = true;
		}

		boolean noTestrayFactors = false;

		try {
			TestrayValidator.validateTestrayFactors(request, testrayBuild, true);
		}
		catch (IllegalArgumentException iae) {
			noTestrayFactors = true;
		}

		if (noTestrayCases && noTestrayFactors) {
			throw new AlloyException("at-least-one-case-or-environment-stack-is-required", false);
		}
	}

	private void _validateView(TestrayBuild testrayBuild) throws Exception {
		if (testrayBuild == null) {
			String testrayBuildIdentifier = request.getParameter("id");

			TestrayValidator.validateTestrayBuildIdentifier(request, testrayBuildIdentifier);
		}
	}

}
%>