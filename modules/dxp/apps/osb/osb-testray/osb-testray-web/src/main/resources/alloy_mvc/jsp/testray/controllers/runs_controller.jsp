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

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayRun.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"testrayBuildId", "testrayFactors"}, parameterTypes = {Long.class, String.class})
	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAdd();

		TestrayRun testrayRun = TestrayRunLocalServiceUtil.createTestrayRun(increment());

		String testrayFactors = ParamUtil.getString(request, "testrayFactors");

		TestrayFactorUtil.addTestrayFactors(this, TestrayRun.class.getName(), testrayRun.getTestrayRunId(), testrayFactors);

		String environmentHash = TestrayRunUtil.getEnvironmentHash(testrayRun.getTestrayRunId());

		testrayRun.setEnvironmentHash(environmentHash);

		testrayRun.setExternalReferencePK(String.valueOf(testrayRun.getTestrayRunId()));
		testrayRun.setName(String.valueOf(testrayRun.getTestrayRunId()));

		List<Long> testrayFactorOptionIds = TestrayFactorUtil.getTestrayFactorOptionIds(TestrayRun.class.getName(), testrayRun.getTestrayRunId());

		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		long runNumber = TestrayRunUtil.getNumber(new HashSet<Long>(testrayFactorOptionIds), testrayBuildId);

		testrayRun.setNumber(runNumber);

		testrayRun.setTestrayBuildId(testrayBuildId);

		updateModel(testrayRun, "testrayRunId", testrayRun.getTestrayRunId());

		respondWith(_getTestrayRunJSONObject(testrayRun));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"testrayRunIdA", "testrayRunIdB"}, parameterTypes = {Long.class, Long.class})
	public void autoFill() throws Exception {
		_validateAutoFill();

		long testrayRunIdA = ParamUtil.getLong(request, "testrayRunIdA");

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		TestrayRunComposite testrayRunCompositeA = new TestrayRunComposite(TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdA), themeDisplay, testrayCaseResultProperties);

		long testrayRunIdB = ParamUtil.getLong(request, "testrayRunIdB");

		TestrayRunComposite testrayRunCompositeB = new TestrayRunComposite(TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdB), themeDisplay, testrayCaseResultProperties);

		int testrayCaseResultsCount = TestrayRunUtil.autoFill(this, testrayRunCompositeA, testrayRunCompositeB);

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

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"testrayRunIdA", "testrayRunIdB", "view"}, parameterTypes = {Long.class, Long.class, String.class})
	public void compare() throws Exception {
		_validateCompare();

		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));

		String redirect = ParamUtil.getString(request, "redirect");

		renderRequest.setAttribute("redirect", redirect);

		TestrayRunComposite testrayRunCompositeA = null;

		long testrayRunIdA = ParamUtil.getLong(request, "testrayRunIdA");

		portletURL.setParameter("testrayRunIdA", String.valueOf(testrayRunIdA));

		Map<String, Serializable> testrayCaseResultProperties;

		if (testrayRunIdA > 0) {
			testrayCaseResultProperties = new HashMap<>();

			testrayRunCompositeA = new TestrayRunComposite(TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdA), themeDisplay, testrayCaseResultProperties);

			renderRequest.setAttribute("testrayRunCompositeA", testrayRunCompositeA);
		}

		TestrayRunComposite testrayRunCompositeB = null;

		long testrayRunIdB = ParamUtil.getLong(request, "testrayRunIdB");

		portletURL.setParameter("testrayRunIdB", String.valueOf(testrayRunIdB));

		if (testrayRunIdB > 0) {
			testrayCaseResultProperties = new HashMap<>();

			testrayRunCompositeB = new TestrayRunComposite(TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdB), themeDisplay, testrayCaseResultProperties);

			renderRequest.setAttribute("testrayRunCompositeB", testrayRunCompositeB);
		}

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		List<Integer> testrayCasePriorities = ListUtil.toList(priorities);

		renderRequest.setAttribute("testrayCasePriorities", testrayCasePriorities);

		renderRequest.setAttribute("testrayTeams", TestrayTeamUtil.getTestrayTeams(testrayProjectId));

		String view = ParamUtil.getString(request, "view");

		if (view.equals("components")) {
			long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

			TestrayRunComparison testrayRunComparison = TestrayRunUtil.compare(testrayRunCompositeA, testrayRunCompositeB, testrayCasePriorities, testrayTeamId);

			renderRequest.setAttribute("testrayRunComparison", testrayRunComparison);

			Map<TestrayComponent, Map<Integer, Map<Integer, Integer>>> testrayComponentsSummaryMap = testrayRunComparison.getTestrayComponentsSummaryMap();

			renderRequest.setAttribute("testrayComponentsSummaryMap", testrayComponentsSummaryMap);

			if (isRespondingTo("json")) {
				JSONArray comparisonJSONArray = _getComparisonJSONArray(testrayComponentsSummaryMap, "testrayComponentId");

				respondWith(comparisonJSONArray);
			}
		}
		else if (view.equals("details")) {
			String testrayCaseName = ParamUtil.getString(request, "testrayCaseName");
			long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId");
			long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");
			long statusA = ParamUtil.getLong(request, "statusA", -1);
			long statusB = ParamUtil.getLong(request, "statusB", -1);

			TestrayRunComparison testrayRunComparison = TestrayRunUtil.compare(testrayRunCompositeA, testrayRunCompositeB, testrayCaseName, testrayComponentId, testrayTeamId, statusA, statusB, testrayCasePriorities);

			renderRequest.setAttribute("testrayRunComparison", testrayRunComparison);

			List<TestrayCaseResultComparison> testrayCaseResultComparisons = new ArrayList<TestrayCaseResultComparison>(testrayRunComparison.getTestrayCaseResultComparisons());

			String orderByCol = ParamUtil.getString(request, "orderByCol", "statusLabelA");

			renderRequest.setAttribute("orderByCol", orderByCol);

			String orderByType = ParamUtil.getString(request, "orderByType", "asc");

			renderRequest.setAttribute("orderByType", orderByType);

			TestrayCompositeComparator testrayCompositeComparator = new TestrayCompositeComparator(orderByCol, orderByType.equals("asc"), "statusLabelA", true, "statusLabelB", true, "priority", false, "testrayComponentName", true, "testrayCaseName", true);

			testrayCaseResultComparisons = ListUtil.sort(testrayCaseResultComparisons, testrayCompositeComparator);

			if (isRespondingTo("json")) {
				JSONArray comparisonJSONArray = JSONFactoryUtil.createJSONArray();

				for (TestrayCaseResultComparison testrayCaseResultComparison : testrayCaseResultComparisons) {
					comparisonJSONArray.put(testrayCaseResultComparison.getJSONObject());
				}

				respondWith(comparisonJSONArray);
			}

			renderRequest.setAttribute("testrayCaseResultComparisons", testrayCaseResultComparisons);

			TestrayRun testrayRunA = TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdA);
			TestrayRun testrayRunB = TestrayRunLocalServiceUtil.getTestrayRun(testrayRunIdB);
			List<TestrayComponent> testrayComponentsA = TestrayComponentUtil.getTestrayComponents(0, "testrayRunId", testrayRunA.getTestrayRunId());
			List<TestrayComponent> testrayComponentsB = TestrayComponentUtil.getTestrayComponents(0, "testrayRunId", testrayRunB.getTestrayRunId());

			List<TestrayComponent> testrayComponents = ListUtil.fromCollection(TestrayUtil.getUnion(testrayComponentsA, testrayComponentsB));

			OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayComponentModelImpl.TABLE_NAME, "name", true);

			renderRequest.setAttribute("testrayComponents", ListUtil.sort(testrayComponents, obc));

			TestrayUtil.setPortletURL(request, portletURL, "statusA", "statusB");
		}
		else {
			long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

			TestrayRunComparison testrayRunComparison = TestrayRunUtil.compare(testrayRunCompositeA, testrayRunCompositeB, testrayCasePriorities, testrayTeamId);

			renderRequest.setAttribute("testrayRunComparison", testrayRunComparison);

			Map<TestrayTeam, Map<Integer, Map<Integer, Integer>>> testrayTeamsSummaryMap = testrayRunComparison.getTestrayTeamsSummaryMap();

			renderRequest.setAttribute("testrayTeamsSummaryMap", testrayTeamsSummaryMap);

			if (isRespondingTo("json")) {
				JSONArray comparisonJSONArray = _getComparisonJSONArray(testrayTeamsSummaryMap, "testrayTeamId");

				respondWith(comparisonJSONArray);
			}
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		TestrayRun testrayRun = _fetchTestrayRun();

		_validateDelete(testrayRun);

		TestrayRunUtil.deleteTestrayRun(testrayRun);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayRun testrayRun = _fetchTestrayRun();

		_validateEdit(testrayRun);

		renderRequest.setAttribute("testrayRun", testrayRun);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "testrayBuildId"}, parameterTypes = {Integer.class, Integer.class, Long.class})
	public void index() throws Exception {
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));
		renderRequest.setAttribute("TestrayRunConstants", getConstantsBean(TestrayRunConstants.class));

		_search();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"jenkinsJobKey", "testrayRoutineId", "testrayTeamId"}, parameterTypes = {Integer.class, Long.class, Long.class})
	public void latestRunResults() throws Exception {
		if (!isRespondingTo()) {
			return;
		}

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		long jenkinsJobKey = ParamUtil.getLong(request, "jenkinsJobKey");

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		TestrayRunComposite testrayRunComposite = new TestrayRunComposite(_getLatestTestrayRun(testrayRoutineId, jenkinsJobKey), themeDisplay, testrayCaseResultProperties);

		respondWith(testrayRunComposite.getDashboardJSONObject());
	}

	public void select() throws Exception {
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));
		renderRequest.setAttribute("TestrayRunConstants", getConstantsBean(TestrayRunConstants.class));

		TestrayRowChecker rowChecker = new TestrayRowChecker(renderResponse);

		rowChecker.setFormName("fm2");

		renderRequest.setAttribute("rowChecker", rowChecker);

		_search();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void update() throws Exception {
		TestrayRun testrayRun = _fetchTestrayRun();

		_validateUpdate(testrayRun);

		updateModel(testrayRun);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRunJSONObject(testrayRun));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayRun testrayRun = _fetchTestrayRun();

		_validateView(testrayRun);

		respondWith(_getTestrayRunJSONObject(testrayRun));
	}

	private TestrayRun _fetchTestrayRun() throws Exception {
		TestrayRun testrayRun = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayRun = TestrayRunLocalServiceUtil.fetchTestrayRun(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

			List<TestrayRun> testrayRuns = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuildId, "number", GetterUtil.getLong(id.substring(1))});

			if (!testrayRuns.isEmpty()) {
				testrayRun = testrayRuns.get(0);
			}
		}

		return testrayRun;
	}

	private JSONArray _getComparisonJSONArray(Map<? extends BaseModel<?>, Map<Integer, Map<Integer, Integer>>> baseModelSummaryMap, String classPKName) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Map.Entry<? extends BaseModel<?>, Map<Integer, Map<Integer, Integer>>> entry : baseModelSummaryMap.entrySet()) {
			JSONObject baseModelJSONObject = JSONFactoryUtil.createJSONObject();

			BaseModel<?> baseModel = entry.getKey();

			baseModelJSONObject.put(classPKName, BeanPropertiesUtil.getLong(baseModel, classPKName));
			baseModelJSONObject.put("name", BeanPropertiesUtil.getString(baseModel, "name"));

			JSONArray statusCountsJSONArray = JSONFactoryUtil.createJSONArray();

			Map<Integer, Map<Integer, Integer>> statusCountsMapA = entry.getValue();

			for (Map.Entry<Integer, Map<Integer, Integer>> statusEntryA : statusCountsMapA.entrySet()) {
				String statusLabelA = TestrayCaseResultConstants.getStatusLabel(statusEntryA.getKey());

				Map<Integer, Integer> statusCountsMapB = statusEntryA.getValue();

				for (Map.Entry<Integer, Integer> statusEntryB : statusCountsMapB.entrySet()) {
					JSONObject statusCountJSONObject = JSONFactoryUtil.createJSONObject();

					statusCountJSONObject.put("count", statusEntryB.getValue());
					statusCountJSONObject.put("statusLabelA", statusLabelA);
					statusCountJSONObject.put("statusLabelB", TestrayCaseResultConstants.getStatusLabel(statusEntryB.getKey()));

					statusCountsJSONArray.put(statusCountJSONObject);
				}
			}

			baseModelJSONObject.put("statusCounts", statusCountsJSONArray);

			jsonArray.put(baseModelJSONObject);
		}

		return jsonArray;
	}

	private void _search() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.getTestrayBuild(testrayBuildId);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		List<Integer> testrayCasePriorities = ListUtil.toList(priorities);

		renderRequest.setAttribute("testrayCasePriorities", testrayCasePriorities);

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long[] testrayCaseTypeIds = ParamUtil.getLongValues(request, "testrayCaseTypeId");

		testrayCaseResultProperties.put("testrayCaseTypeId", StringUtil.merge(testrayCaseTypeIds));

		renderRequest.setAttribute("testrayCaseTypeIds", ListUtil.toList(testrayCaseTypeIds));

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		renderRequest.setAttribute("testrayBuildComposite", TestrayCompositeUtil.getComposite(TestrayBuildComposite.class, new Class<?>[] {TestrayBuild.class, ThemeDisplay.class, Map.class}, new Object[] {testrayBuild, themeDisplay, testrayCaseResultProperties}));

		testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayBuildId", testrayBuild.getTestrayBuildId());
		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));
		testrayCaseResultProperties.put("testrayCaseTypeId", StringUtil.merge(testrayCaseTypeIds));
		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		TestrayCaseResultReporter testrayCaseResultReporter = new TestrayCaseResultReporter(themeDisplay, testrayCaseResultProperties);

		renderRequest.setAttribute("testrayCaseResultReporter", testrayCaseResultReporter);

		TestrayProductVersion testrayProductVersion = TestrayProductVersionLocalServiceUtil.getTestrayProductVersion(testrayBuild.getTestrayProductVersionId());

		renderRequest.setAttribute("testrayProductVersion", testrayProductVersion);

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayRunModelImpl.TABLE_NAME, "number", true);

		List<TestrayRun> testrayRuns = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()}, startAndEnd[0], startAndEnd[1], obc);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRunsJSONArray(testrayRuns));

			return;
		}

		testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));
		testrayCaseResultProperties.put("testrayCaseTypeId", StringUtil.merge(testrayCaseTypeIds));
		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		List<TestrayRunComposite> testrayRunComposites = TestrayCompositeUtil.getComposites(testrayRuns, TestrayRunComposite.class, new Class<?>[] {TestrayRun.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties});

		renderRequest.setAttribute("testrayRunComposites", testrayRunComposites);

		long testrayRunsCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		renderRequest.setAttribute("testrayRunsCount", testrayRunsCount);

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		OrderByComparator testrayCaseTypesOBC = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, testrayCaseTypesOBC);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayProjectId);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	private TestrayRun _getLatestTestrayRun(long testrayRoutineId, long jenkinsJobKey) throws Exception {
		AlloyServiceInvoker testrayBuildAlloyServiceInvoker = new AlloyServiceInvoker(TestrayBuild.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayBuildModelImpl.TABLE_NAME, "createDate", false);

		List<TestrayBuild> testrayBuilds = testrayBuildAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayRoutineId", testrayRoutineId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		for (TestrayBuild testrayBuild : testrayBuilds) {
			List<TestrayRun> testrayRuns = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId(), "jenkinsJobKey", jenkinsJobKey}, 0, 1);

			if (!testrayRuns.isEmpty()) {
				return testrayRuns.get(0);
			}
		}

		throw new AlloyException("there-are-no-results-for-the-latest-build", false);
	}

	private JSONObject _getTestrayRunJSONObject(TestrayRun testrayRun) throws Exception {
		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		if (testrayTeamId != 0) {
			testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);
		}

		TestrayRunComposite testrayRunComposite = new TestrayRunComposite(testrayRun, themeDisplay, testrayCaseResultProperties);

		return testrayRunComposite.getJSONObject();
	}

	private JSONArray _getTestrayRunsJSONArray(List<TestrayRun> testrayRuns) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayRun testrayRun : testrayRuns) {
			jsonArray.put(_getTestrayRunJSONObject(testrayRun));
		}

		return jsonArray;
	}

	private void _validateAdd() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		TestrayBuild testrayBuild = TestrayBuildLocalServiceUtil.fetchTestrayBuild(testrayBuildId);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);

		Set<Long> testrayFactorCategoryIds = new HashSet<Long>();
		Set<Long> testrayFactorOptionIds = new HashSet<Long>();

		String testrayFactors = ParamUtil.getString(request, "testrayFactors");

		TestrayValidator.validateTestrayFactorsJSONArray(testrayFactors, request);

		JSONArray testrayFactorsJSONArray = JSONFactoryUtil.createJSONArray(testrayFactors);

		for (int i = 0; i < testrayFactorsJSONArray.length(); i++) {
			JSONObject testrayFactorJSONObject = testrayFactorsJSONArray.getJSONObject(i);

			TestrayFactorCategory testrayFactorCategory = TestrayFactorCategoryUtil.fetchTestrayFactorCategory(themeDisplay.getCompanyId(), testrayFactorJSONObject);

			if (testrayFactorCategory != null) {
				testrayFactorCategoryIds.add(testrayFactorCategory.getTestrayFactorCategoryId());
			}

			TestrayFactorOption testrayFactorOption = TestrayFactorOptionUtil.fetchTestrayFactorOption(themeDisplay.getCompanyId(), testrayFactorJSONObject);

			if (testrayFactorOption != null) {
				testrayFactorOptionIds.add(testrayFactorOption.getTestrayFactorOptionId());
			}
		}

		List<Long> defaultTestrayFactorCategoryIds = TestrayFactorCategoryUtil.getTestrayFactorCategoryIds(TestrayRoutine.class.getName(), testrayBuild.getTestrayRoutineId());

		if ((defaultTestrayFactorCategoryIds.size() != testrayFactorsJSONArray.length()) || !testrayFactorCategoryIds.containsAll(defaultTestrayFactorCategoryIds)) {
			throw new AlloyException("the-submitted-factors-do-not-match-the-default-environment-factors", false);
		}

		Set<Set<Long>> testrayFactorOptionIdSets = TestrayFactorUtil.getExistingTestrayFactorOptionIdSets(testrayBuildId);

		if (testrayFactorOptionIdSets.contains(testrayFactorOptionIds)) {
			throw new AlloyException("a-run-with-these-factor-options-already-exists", false);
		}
	}

	private void _validateAutoFill() throws Exception {
		TestrayValidator.validateTestrayRuns(request, themeDisplay);
	}

	private void _validateCompare() throws Exception {
		boolean compare = ParamUtil.getBoolean(request, "compare");

		if (compare) {
			TestrayValidator.validateTestrayRuns(request, themeDisplay);
		}
	}

	private void _validateDelete(TestrayRun testrayRun) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayRun, "the-run-with-id-x-does-not-exist", "id");
	}

	private void _validateEdit(TestrayRun testrayRun) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayRun, "the-run-with-id-x-does-not-exist", "id");
	}

	private void _validateUpdate(TestrayRun testrayRun) throws Exception {
		TestrayValidator.validateBaseModel(request, testrayRun, "the-run-with-id-x-does-not-exist", "id");

		if (!isRespondingTo("json")) {
			TestrayValidator.validateName(request, "testrayBuildId", TestrayRun.class.getName(), "testrayRunId", "the-run-name-is-invalid", "the-run-name-already-exists");
		}
	}

	private void _validateView(TestrayRun testrayRun) throws Exception {
		TestrayValidator.validateTestrayRun(request, testrayRun);
	}

}
%>