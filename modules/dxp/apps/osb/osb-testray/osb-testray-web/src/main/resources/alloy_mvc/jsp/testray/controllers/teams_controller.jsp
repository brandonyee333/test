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

<%@ include file="/alloy_mvc/jsp/util/testray_team_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayTeam.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void add() throws Exception {
		TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.createTestrayTeam(0);

		_validateAdd();

		updateModel(testrayTeam);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTeamJSONObject(testrayTeam));

			return;
		}

		long[] testrayComponentIds = ParamUtil.getLongValues(request, "testrayComponentIds");

		_updateTestrayComponents(testrayTeam.getTestrayTeamId(), testrayComponentIds);

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayTeam.getTestrayTeamId(), "testrayProjectId", testrayTeam.getTestrayProjectId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.createTestrayTeam(0);

		testrayTeam.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testrayTeam", testrayTeam);

		_setTestrayComponentParameters(testrayTeam);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void delete() throws Exception {
		TestrayTeam testrayTeam = _fetchTestrayTeam();

		_validateDelete(testrayTeam);

		TestrayTeamLocalServiceUtil.deleteTestrayTeam(testrayTeam);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayTeam testrayTeam = _fetchTestrayTeam();

		_validateEdit(testrayTeam);

		renderRequest.setAttribute("testrayTeam", testrayTeam);

		_setTestrayComponentParameters(testrayTeam);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "name", "orderByCol", "orderByType", "start", "testrayProjectId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, String.class, Integer.class, Long.class})
	public void index() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayProjectId", testrayProjectId);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc"))
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTeamsJSONArray(TestrayUtil.getSubclassList(alloySearchResult.getBaseModels(), TestrayTeam.class)));

			return;
		}

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	public void metrics() throws Exception {
		TestrayBuild testrayBuild = TestrayBuildUtil.fetchTestrayBuild(request);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);

		renderRequest.setAttribute("TestrayBuildConstants", getConstantsBean(TestrayBuildConstants.class));
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayTeamConstants", getConstantsBean(TestrayTeamConstants.class));

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long testrayRunId = ParamUtil.getLong(request, "testrayRunId");

		if (testrayRunId > 0) {
			testrayCaseResultProperties.put("testrayRunId", testrayRunId);

			TestrayRun testrayRun = TestrayRunLocalServiceUtil.fetchTestrayRun(testrayRunId);

			renderRequest.setAttribute("testrayRunComposite", new TestrayRunComposite(testrayRun, themeDisplay, testrayCaseResultProperties));
		}

		TestrayBuildComposite testrayBuildComposite = new TestrayBuildComposite(testrayBuild, themeDisplay, testrayCaseResultProperties);

		renderRequest.setAttribute("testrayBuildComposite", testrayBuildComposite);
		renderRequest.setAttribute("testrayCaseResultReporter", testrayBuildComposite.getTestrayCaseResultReporter());

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayProjectId", testrayBuildComposite.getTestrayProjectId());

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc"))
		};

		AlloySearchResult alloySearchResult = search(IndexerRegistryUtil.nullSafeGetIndexer(TestrayTeam.class), new AlloyServiceInvoker(TestrayTeam.class.getName()), request, portletRequest, null, attributes, null, sorts);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);
		renderRequest.setAttribute("testrayTeams", alloySearchResult.getBaseModels());

		long testrayCaseTypeId = ParamUtil.getLong(request, "testrayCaseTypeId");

		if (testrayCaseTypeId > 0) {
			testrayCaseResultProperties.put("testrayCaseTypeId", testrayCaseTypeId);
		}

		testrayCaseResultProperties.put("testrayBuildId", testrayBuild.getTestrayBuildId());

		List<TestrayTeamComposite> testrayTeamComposites = TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayTeamComposite.class, new Class<?>[] {TestrayTeam.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties});

		TestrayCompositeComparator testrayCompositeComparator = new TestrayCompositeComparator(orderByCol, orderByType.equals("asc"), "name", true);

		testrayTeamComposites = TestrayUtil.paginate(testrayTeamComposites, portletRequest, portletURL, testrayCompositeComparator);

		renderRequest.setAttribute("testrayTeamComposites", testrayTeamComposites);

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		AlloyServiceInvoker testrayRunAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRun.class.getName());

		List<TestrayRun> testrayRuns = testrayRunAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayBuildId", testrayBuild.getTestrayBuildId()});

		renderRequest.setAttribute("testrayRuns", testrayRuns);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name", "testrayProjectId"}, parameterTypes = {String.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayTeam testrayTeam = _fetchTestrayTeam();

		_validateUpdate(testrayTeam);

		updateModel(testrayTeam);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTeamJSONObject(testrayTeam));

			return;
		}

		long[] testrayComponentIds = ParamUtil.getLongValues(request, "testrayComponentIds");

		_updateTestrayComponents(testrayTeam.getTestrayTeamId(), testrayComponentIds);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void view() throws Exception {
		TestrayTeam testrayTeam = _fetchTestrayTeam();

		_validateView(testrayTeam);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayTeamJSONObject(testrayTeam));

			return;
		}
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayTeamIndexer.getInstance();
	}

	private TestrayTeam _fetchTestrayTeam() throws Exception {
		TestrayTeam testrayTeam = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayTeam = TestrayTeamLocalServiceUtil.fetchTestrayTeam(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestrayTeam> testrayTeams = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", id.substring(1)});

			if (!testrayTeams.isEmpty()) {
				testrayTeam = testrayTeams.get(0);
			}
		}

		return testrayTeam;
	}

	private List<Long> _getTestrayComponentIds(long testrayTeamId) throws Exception {
		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		DynamicQuery testrayComponentDynamicQuery = testrayComponentAlloyServiceInvoker.buildDynamicQuery(new Object[] {"testrayTeamId", testrayTeamId});

		Projection testrayComponentIdProjection = ProjectionFactoryUtil.property("testrayComponentId");

		testrayComponentDynamicQuery.setProjection(testrayComponentIdProjection);

		return testrayComponentAlloyServiceInvoker.executeDynamicQuery(testrayComponentDynamicQuery);
	}

	private JSONObject _getTestrayTeamJSONObject(TestrayTeam testrayTeam) throws Exception {
		TestrayTeamComposite testrayTeamComposite = new TestrayTeamComposite(testrayTeam, themeDisplay);

		return testrayTeamComposite.getJSONObject();
	}

	private JSONArray _getTestrayTeamsJSONArray(List<TestrayTeam> testrayTeams) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayTeam testrayTeam : testrayTeams) {
			jsonArray.put(_getTestrayTeamJSONObject(testrayTeam));
		}

		return jsonArray;
	}

	private void _setTestrayComponentParameters(TestrayTeam testrayTeam) throws Exception {
		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayComponentModelImpl.TABLE_NAME, "name", true);

		List<TestrayComponent> testrayComponents = testrayComponentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayTeam.getTestrayProjectId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		List<KeyValuePair> unassignedTestrayComponents = new ArrayList<KeyValuePair>();

		List<KeyValuePair> currentTestrayComponents = new ArrayList<KeyValuePair>();

		for (TestrayComponent testrayComponent : testrayComponents) {
			if (testrayComponent.getTestrayTeamId() <= 0) {
				unassignedTestrayComponents.add(new KeyValuePair(String.valueOf(testrayComponent.getTestrayComponentId()), translate(testrayComponent.getName())));
			}
			else if (testrayComponent.getTestrayTeamId() == testrayTeam.getTestrayTeamId()) {
				currentTestrayComponents.add(new KeyValuePair(String.valueOf(testrayComponent.getTestrayComponentId()), translate(testrayComponent.getName())));
			}
		}

		renderRequest.setAttribute("unassignedTestrayComponents", unassignedTestrayComponents);

		renderRequest.setAttribute("currentTestrayComponents", currentTestrayComponents);
	}

	private void _updateTestrayComponents(long testrayTeamId, long[] testrayComponentIds) throws Exception {
		List<Long> newTestrayComponentIds = ListUtil.toList(testrayComponentIds);

		List<Long> oldTestrayComponentIds = _getTestrayComponentIds(testrayTeamId);

		Set<Long> addableTestrayComponentIds = TestrayUtil.getRelativeComplement(newTestrayComponentIds, oldTestrayComponentIds);

		TestrayComponentUtil.updateTestrayTeamId(this, addableTestrayComponentIds, testrayTeamId);

		Set<Long> removableTestrayComponentIds = TestrayUtil.getRelativeComplement(oldTestrayComponentIds, newTestrayComponentIds);

		TestrayComponentUtil.updateTestrayTeamId(this, removableTestrayComponentIds, 0);

		Set<Long> indexableTestrayComponentIds = TestrayUtil.getUnion(addableTestrayComponentIds, removableTestrayComponentIds);

		for (long indexableTestrayComponentId : indexableTestrayComponentIds) {
			TestrayCaseUtil.indexTestrayCases(this, indexableTestrayComponentId);
		}
	}

	private void _validateAdd() throws Exception {
		_validateName(0L);

		_validateProject();
	}

	private void _validateDelete(TestrayTeam testrayTeam) throws Exception {
		_validateTestrayTeam(testrayTeam);

		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		long testrayComponentCount = testrayComponentAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayTeamId", testrayTeam.getTestrayTeamId()});

		if (testrayComponentCount > 0) {
			throw new AlloyException("the-team-cannot-be-deleted-because-it-has-associated-components", false);
		}
	}

	private void _validateEdit(TestrayTeam testrayTeam) throws Exception {
		_validateTestrayTeam(testrayTeam);
	}

	private void _validateName(long defaultTestrayProjectId) throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-team-name-is-invalid", false);
		}

		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", defaultTestrayProjectId);

		List<TestrayTeam> testrayTeams = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", name});

		if (!testrayTeams.isEmpty()) {
			long testrayTeamId = ParamUtil.getLong(request, "id");

			TestrayTeam testrayTeam = testrayTeams.get(0);

			if (testrayTeam.getTestrayTeamId() != testrayTeamId) {
				throw new AlloyException("the-team-name-already-exists", false);
			}
		}
	}

	private void _validateProject() throws Exception {
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		if (testrayProject == null) {
			throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
		}
	}

	private void _validateTestrayTeam(TestrayTeam testrayTeam) throws Exception {
		if (testrayTeam != null) {
			return;
		}

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			throw new AlloyException(translate("the-team-with-id-x-does-not-exist", id), false);
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

			if (testrayProject == null) {
				throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
			}
			else {
				throw new AlloyException(translate("the-team-x-does-not-exist-in-project-x", id.substring(1), testrayProject.getName()), false);
			}
		}
		else if (Validator.isNotNull(id)) {
			throw new AlloyException(translate("the-team-identifier-x-is-invalid", id), false);
		}
		else {
			throw new AlloyException("a-team-id-or-team-name-and-project-id-is-required");
		}
	}

	private void _validateUpdate(TestrayTeam testrayTeam) throws Exception {
		_validateTestrayTeam(testrayTeam);

		_validateName(testrayTeam.getTestrayProjectId());
	}

	private void _validateView(TestrayTeam testrayTeam) throws Exception {
		_validateTestrayTeam(testrayTeam);
	}

}
%>