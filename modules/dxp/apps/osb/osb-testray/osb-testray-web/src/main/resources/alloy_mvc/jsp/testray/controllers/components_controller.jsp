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

<%@ include file="/alloy_mvc/jsp/util/testray_component_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayComponent.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayProjectId", "testrayTeamId"}, parameterTypes = {String.class, Long.class, Long.class})
	public void add() throws Exception {
		TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.createTestrayComponent(0);

		_validateAdd();

		long testrayComponentId = increment();

		testrayComponent.setOriginationKey(testrayComponentId);

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		if (testrayTeamId > 0) {
			TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.getTestrayTeam(testrayTeamId);

			testrayComponent.setTestrayProjectId(testrayTeam.getTestrayProjectId());
		}

		updateModel(testrayComponent, "testrayComponentId", testrayComponentId);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayComponentJSONObject(testrayComponent));

			return;
		}

		TestrayCaseUtil.indexTestrayCases(this, testrayComponent.getTestrayComponentId());

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayComponent.getTestrayComponentId(), "testrayProjectId", testrayComponent.getTestrayProjectId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.createTestrayComponent(0);

		testrayComponent.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testrayComponent", testrayComponent);

		List<TestrayTeam> testrayTeams = _getTestrayTeams(testrayComponent);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {Long.class, Long.class})
	public void delete() throws Exception {
		TestrayComponent testrayComponent = _fetchTestrayComponent();

		_validateDelete(testrayComponent);

		TestrayComponentLocalServiceUtil.deleteTestrayComponent(testrayComponent);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayComponent testrayComponent = _fetchTestrayComponent();

		_validateEdit(testrayComponent);

		renderRequest.setAttribute("testrayComponent", testrayComponent);

		List<TestrayTeam> testrayTeams = _getTestrayTeams(testrayComponent);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "name", "orderByCol", "orderByType", "start", "testrayProjectId", "testrayTeamId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, String.class, Integer.class, Long.class, Long.class})
	public void index() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayProjectId", testrayProjectId);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "testrayTeamName_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("name_sortable", false)
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		renderRequest.setAttribute("testrayComponentComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayComponentComposite.class));

		if (isRespondingTo("json")) {
			respondWith(_getTestrayComponentsJSONArray(TestrayUtil.getSubclassList(alloySearchResult.getBaseModels(), TestrayComponent.class)));

			return;
		}

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	public void metrics() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);

		renderRequest.setAttribute("TestrayBuildConstants", getConstantsBean(TestrayBuildConstants.class));
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayComponentConstants", getConstantsBean(TestrayComponentConstants.class));

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long testrayCaseTypeId = ParamUtil.getLong(request, "testrayCaseTypeId");

		if (testrayCaseTypeId > 0) {
			testrayCaseResultProperties.put("testrayCaseTypeId", testrayCaseTypeId);
		}

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		long testrayRunId = ParamUtil.getLong(request, "testrayRunId");

		if (testrayRunId > 0) {
			testrayCaseResultProperties.put("testrayRunId", testrayRunId);

			renderRequest.setAttribute("testrayRunComposite", new TestrayRunComposite(TestrayRunLocalServiceUtil.fetchTestrayRun(testrayRunId), themeDisplay, testrayCaseResultProperties));
		}

		TestrayBuildComposite testrayBuildComposite = new TestrayBuildComposite(testrayBuild, themeDisplay, testrayCaseResultProperties);

		renderRequest.setAttribute("testrayBuildComposite", testrayBuildComposite);
		renderRequest.setAttribute("testrayCaseResultReporter", testrayBuildComposite.getTestrayCaseResultReporter());

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Set<Long> testrayComponentIds = new HashSet<>();

		if (testrayRunId > 0) {
			testrayComponentIds = SetUtil.fromCollection(TestrayCaseResultUtil.getTestrayComponentIds(TestrayRunLocalServiceUtil.fetchTestrayRun(testrayRunId), testrayTeamId));
		}
		else {
			AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

			List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

			renderRequest.setAttribute("testrayRuns", testrayRuns);

			List<Long> testrayRunTestrayComponentIds = new ArrayList<>();

			for (TestrayRun testrayRun : testrayRuns) {
				testrayRunTestrayComponentIds.addAll(TestrayCaseResultUtil.getTestrayComponentIds(testrayRun, testrayTeamId));
			}

			testrayComponentIds = SetUtil.fromCollection(testrayRunTestrayComponentIds);
		}

		testrayCaseResultProperties.put("testrayBuildId", testrayBuild.getTestrayBuildId());

		List<TestrayComponentComposite> testrayComponentComposites = TestrayCompositeUtil.getComposites(ListUtil.fromCollection(testrayComponentIds), TestrayComponentComposite.class, new Class<?>[] {Long.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties});

		TestrayCompositeComparator testrayCompositeComparator = new TestrayCompositeComparator(orderByCol, orderByType.equals("asc"), "name", true);

		testrayComponentComposites = TestrayUtil.paginate(testrayComponentComposites, portletRequest, portletURL, testrayCompositeComparator);

		renderRequest.setAttribute("testrayComponentComposites", testrayComponentComposites);

		renderRequest.setAttribute("testrayComponentsCount", testrayComponentIds.size());

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayProjectId);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name", "testrayProjectId"}, parameterTypes = {Long.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayComponent testrayComponent = _fetchTestrayComponent();

		_validateUpdate(testrayComponent);

		updateModel(testrayComponent);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayComponentJSONObject(testrayComponent));

			return;
		}

		TestrayCaseUtil.indexTestrayCases(this, testrayComponent.getTestrayComponentId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {Long.class, Long.class})
	public void view() throws Exception {
		TestrayComponent testrayComponent = _fetchTestrayComponent();

		_validateView(testrayComponent);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayComponentJSONObject(testrayComponent));

			return;
		}
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayComponentIndexer.getInstance();
	}

	private TestrayComponent _fetchTestrayComponent() throws Exception {
		TestrayComponent testrayComponent = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayComponent = TestrayComponentLocalServiceUtil.fetchTestrayComponent(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestrayComponent> testrayComponents = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", id.substring(1)});

			if (!testrayComponents.isEmpty()) {
				testrayComponent = testrayComponents.get(0);
			}
		}

		return testrayComponent;
	}

	private JSONObject _getTestrayComponentJSONObject(TestrayComponent testrayComponent) throws Exception {
		TestrayComponentComposite testrayComponentComposite = new TestrayComponentComposite(testrayComponent);

		return testrayComponentComposite.getJSONObject();
	}

	private JSONArray _getTestrayComponentsJSONArray(List<TestrayComponent> testrayComponents) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayComponent testrayComponent : testrayComponents) {
			jsonArray.put(_getTestrayComponentJSONObject(testrayComponent));
		}

		return jsonArray;
	}

	private List<TestrayTeam> _getTestrayTeams(TestrayComponent testrayComponent) throws Exception {
		AlloyServiceInvoker testrayTeamAlloyServiceInvoker = new AlloyServiceInvoker(TestrayTeam.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayTeamModelImpl.TABLE_NAME, "name", true);

		List<TestrayTeam> testrayTeams = testrayTeamAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayComponent.getTestrayProjectId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		return testrayTeams;
	}

	private void _validateAdd() throws Exception {
		_validateName(0);
		_validateNameRequired();
		_validateProjectOrTeamRequired();
	}

	private void _validateDelete(TestrayComponent testrayComponent) throws Exception {
		_validateTestrayComponent(testrayComponent);

		AlloyServiceInvoker testrayCaseAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCase.class.getName());

		long testrayCasesCount = testrayCaseAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayComponentId", testrayComponent.getTestrayComponentId()});

		if ((testrayCasesCount > 0) || TestrayCaseLocalServiceUtil.hasTestrayComponentTestrayCases(testrayComponent.getTestrayComponentId())) {
			throw new AlloyException("the-component-cannot-be-deleted-because-it-has-associated-cases", false);
		}
	}

	private void _validateEdit(TestrayComponent testrayComponent) throws Exception {
		_validateTestrayComponent(testrayComponent);
	}

	private void _validateName(long defaultTestrayProjectId) throws Exception {
		String name = ParamUtil.getString(request, "name", null);

		if (name == null) {
			return;
		}

		if (name.equals(StringPool.BLANK)) {
			throw new AlloyException("the-component-name-is-invalid", false);
		}

		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", defaultTestrayProjectId);

		TestrayComponent existingTestrayComponent = TestrayComponentUtil.fetchTestrayComponent(testrayProjectId, name);

		if (existingTestrayComponent != null) {
			TestrayComponent testrayComponent = _fetchTestrayComponent();

			if ((testrayComponent == null) || testrayComponent.isNew() || (testrayComponent.getTestrayComponentId() != testrayComponent.getTestrayComponentId())) {
				throw new AlloyException("the-component-name-already-exists", false);
			}
		}
	}

	private void _validateNameRequired() throws Exception {
		String name = ParamUtil.getString(request, "name", null);

		if (name == null) {
			throw new AlloyException("a-name-is-required-to-add-a-component", false);
		}
	}

	private void _validateProject() throws Exception {
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", -1);

		if (testrayProjectId < 0) {
			return;
		}

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		if (testrayProject == null) {
			throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
		}
	}

	private void _validateProjectOrTeamRequired() throws Exception {
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", -1);
		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId", -1);

		if ((testrayProjectId < 0) && (testrayTeamId < 0)) {
			throw new AlloyException("a-project-id-or-a-team-id-is-required-to-add-a-component", false);
		}

		_validateProject();
		_validateTeam();
	}

	private void _validateTeam() throws Exception {
		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		if (testrayTeamId <= 0) {
			return;
		}

		TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.fetchTestrayTeam(testrayTeamId);

		if (testrayTeam == null) {
			throw new AlloyException(translate("the-team-with-id-x-does-not-exist", testrayTeamId), false);
		}
	}

	private void _validateTestrayComponent(TestrayComponent testrayComponent) throws Exception {
		if (testrayComponent != null) {
			return;
		}

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			throw new AlloyException(translate("the-component-with-id-x-does-not-exist", id), false);
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

			if (testrayProject == null) {
				throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
			}
			else {
				throw new AlloyException(translate("the-component-x-does-not-exist-in-project-x", id.substring(1), testrayProject.getName()), false);
			}
		}
		else if (Validator.isNotNull(id)) {
			throw new AlloyException(translate("the-component-identifier-x-is-invalid", id), false);
		}
		else {
			throw new AlloyException("a-component-id-or-component-name-and-project-id-is-required");
		}
	}

	private void _validateUpdate(TestrayComponent testrayComponent) throws Exception {
		_validateTestrayComponent(testrayComponent);

		_validateName(testrayComponent.getTestrayProjectId());
		_validateProject();
		_validateTeam();
	}

	private void _validateView(TestrayComponent testrayComponent) throws Exception {
		_validateTestrayComponent(testrayComponent);
	}

}
%>