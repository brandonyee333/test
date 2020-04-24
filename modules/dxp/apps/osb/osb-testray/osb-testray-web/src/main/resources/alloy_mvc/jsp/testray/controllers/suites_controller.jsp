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

<%@ include file="/alloy_mvc/jsp/util/testray_suite_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestraySuite.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"description", "name", "testrayProjectId"}, parameterTypes = {String.class, String.class, Long.class})
	public void add() throws Exception {
		TestraySuite testraySuite = TestraySuiteLocalServiceUtil.createTestraySuite(0);

		_validateAdd();

		boolean smartSuite = ParamUtil.getBoolean(request, "smartSuite");

		if (smartSuite) {
			String caseParametersString = ParamUtil.getString(request, "caseParametersString");

			testraySuite.setCaseParameters(caseParametersString);
		}

		updateModel(testraySuite);

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		if (addTestrayCaseIds.length > 0) {
			TestraySuiteUtil.addTestrayCases(testraySuite.getTestraySuiteId(), addTestrayCaseIds);
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testraySuite.getTestraySuiteId(), "testrayProjectId", testraySuite.getTestrayProjectId());

		if (isRespondingTo("json")) {
			respondWith(_getTestraySuiteJSONObject(testraySuite));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestraySuite testraySuite = TestraySuiteLocalServiceUtil.createTestraySuite(0);

		testraySuite.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testraySuite", testraySuite);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void delete() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateDelete(testraySuite);

		TestraySuiteLocalServiceUtil.deleteTestraySuite(testraySuite);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateEdit(testraySuite);

		renderRequest.setAttribute("testraySuite", testraySuite);

		renderRequest.setAttribute("caseParametersString", testraySuite.getCaseParameters());

		List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestraySuiteTestrayCases(testraySuite.getTestraySuiteId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute("testrayCases", testrayCases);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Integer.class})
	public void index() throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayProjectId", testrayProjectId);

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);

		if (isRespondingTo("json")) {
			String orderByCol = ParamUtil.getString(request, "orderByCol", "name_sortable");
			String orderByType = ParamUtil.getString(request, "orderByType", "asc");

			Sort[] sorts = {
				new Sort(orderByCol, orderByType.equals("desc")),
				new Sort("name_sortable", false)
			};

			int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

			AlloySearchResult alloySearchResult = search(attributes, null, sorts, startAndEnd[0], startAndEnd[1]);

			respondWith(_getTestraySuitesJSONArray(alloySearchResult.getBaseModels()));

			return;
		}

		_search(attributes);
	}

	public void refreshCases() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateRefreshCases(testraySuite);

		String testraySuiteCaseParameters = testraySuite.getCaseParameters();

		if (Validator.isNotNull(testraySuiteCaseParameters)) {
			TestraySuiteUtil.refreshCases(testraySuite);
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void refreshSuites() throws Exception {
		long[] testraySuiteIds = ParamUtil.getLongValues(request, "testraySuiteIds");

		for (long testraySuiteId : testraySuiteIds) {
			TestraySuite testraySuite = TestraySuiteLocalServiceUtil.getTestraySuite(testraySuiteId);

			if (Validator.isNotNull(testraySuite.getCaseParameters())) {
				TestraySuiteUtil.refreshCases(testraySuite);
			}
		}

		String redirect = ParamUtil.getString(request, "redirect");

		String testraySuiteIdsString = ParamUtil.getString(request, "testraySuiteIds");

		redirectTo(HttpUtil.addParameter(redirect, "testraySuiteIds", testraySuiteIdsString));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayCaseId", "testrayProjectId"}, parameterTypes = {String.class, Long.class, Long.class})
	public void removeCase() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateRemoveCase(testraySuite);

		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId");

		TestrayCaseLocalServiceUtil.deleteTestraySuiteTestrayCase(testraySuite.getTestraySuiteId(), testrayCaseId);

		indexModel(TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId));
		indexModel(testraySuite);

		if (isRespondingTo("json")) {
			respondWith(_getTestraySuiteJSONObject(testraySuite));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void select() throws Exception {
		TestrayRowChecker rowChecker = new TestrayRowChecker(renderResponse);

		rowChecker.setFormName("fm2");

		renderRequest.setAttribute("rowChecker", rowChecker);

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("hasTestrayCases", true);

		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId", -1);

		renderRequest.setAttribute("testrayBuildId", testrayBuildId);

		attributes.put("testrayProjectId", testrayProjectId);

		_search(attributes);
	}

	public void selectCaseParameters() throws Exception {
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayPortletKeys", getConstantsBean(TestrayPortletKeys.class));

		TestraySuite testraySuite = _fetchTestraySuite();

		List<KeyValuePair> availablePriorities = new ArrayList<>();

		for (int priority = 1; priority <= 5; priority++) {
			availablePriorities.add(new KeyValuePair(String.valueOf(priority), String.valueOf(priority)));
		}

		List<KeyValuePair> currentPriorities = new ArrayList<>();

		String caseParametersString = ParamUtil.getString(request, "caseParametersString");

		if ((testraySuite != null) && Validator.isNull(caseParametersString)) {
			caseParametersString = testraySuite.getCaseParameters();
		}

		JSONArray caseParametersJSONArray = JSONFactoryUtil.createJSONArray(caseParametersString);

		for (String priorityString : _getCaseParameterValues(caseParametersJSONArray, "priority")) {
			KeyValuePair keyValuePair = new KeyValuePair(priorityString, priorityString);

			availablePriorities.remove(keyValuePair);
			currentPriorities.add(keyValuePair);
		}

		renderRequest.setAttribute("availablePriorities", availablePriorities);
		renderRequest.setAttribute("currentPriorities", currentPriorities);

		AlloyServiceInvoker testrayTeamAlloyServiceInvoker = new AlloyServiceInvoker(TestrayTeam.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayTeamModelImpl.TABLE_NAME, "name", true);

		List<TestrayTeam> testrayTeams = testrayTeamAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		List<KeyValuePair> availableTestrayTeams = new ArrayList<>();

		for (TestrayTeam testrayTeam : testrayTeams) {
			availableTestrayTeams.add(new KeyValuePair(String.valueOf(testrayTeam.getTestrayTeamId()), testrayTeam.getName()));
		}

		List<KeyValuePair> currentTestrayTeams = new ArrayList();

		for (String testrayTeamIdString : _getCaseParameterValues(caseParametersJSONArray, "testrayTeamId")) {
			TestrayTeam testrayTeam = TestrayTeamLocalServiceUtil.getTestrayTeam(GetterUtil.getLong(testrayTeamIdString));

			KeyValuePair keyValuePair = new KeyValuePair(String.valueOf(testrayTeam.getTestrayTeamId()), testrayTeam.getName());

			availableTestrayTeams.remove(keyValuePair);
			currentTestrayTeams.add(keyValuePair);
		}

		renderRequest.setAttribute("availableTestrayTeams", availableTestrayTeams);
		renderRequest.setAttribute("currentTestrayTeams", currentTestrayTeams);

		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		obc = OrderByComparatorFactoryUtil.create(TestrayComponentModelImpl.TABLE_NAME, "name", true);

		List<TestrayComponent> testrayComponents = testrayComponentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		List<KeyValuePair> availableSubcomponents = new ArrayList<>();

		for (TestrayComponent testrayComponent : testrayComponents) {
			availableSubcomponents.add(new KeyValuePair(String.valueOf(testrayComponent.getTestrayComponentId()), testrayComponent.getName()));
		}

		List<KeyValuePair> currentSubcomponents = new ArrayList<>();

		for (String testrayComponentIdString : _getCaseParameterValues(caseParametersJSONArray, "subcomponentId")) {
			TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.getTestrayComponent(GetterUtil.getLong(testrayComponentIdString));

			KeyValuePair keyValuePair = new KeyValuePair(testrayComponentIdString, testrayComponent.getName());

			availableSubcomponents.remove(keyValuePair);
			currentSubcomponents.add(keyValuePair);
		}

		renderRequest.setAttribute("availableSubcomponents", availableSubcomponents);
		renderRequest.setAttribute("currentSubcomponents", currentSubcomponents);

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		List<KeyValuePair> availableTestrayCaseTypes = new ArrayList<>();

		for (TestrayCaseType testrayCaseType : testrayCaseTypes) {
			availableTestrayCaseTypes.add(new KeyValuePair(String.valueOf(testrayCaseType.getTestrayCaseTypeId()), testrayCaseType.getName()));
		}

		List<KeyValuePair> currentTestrayCaseTypes = new ArrayList<>();

		for (String testrayCaseTypeIdString : _getCaseParameterValues(caseParametersJSONArray, "testrayCaseTypeId")) {
			TestrayCaseType testrayCaseType = TestrayCaseTypeLocalServiceUtil.getTestrayCaseType(GetterUtil.getLong(testrayCaseTypeIdString));

			KeyValuePair keyValuePair = new KeyValuePair(testrayCaseTypeIdString, testrayCaseType.getName());

			availableTestrayCaseTypes.remove(keyValuePair);
			currentTestrayCaseTypes.add(keyValuePair);
		}

		renderRequest.setAttribute("availableTestrayCaseTypes", availableTestrayCaseTypes);
		renderRequest.setAttribute("currentTestrayCaseTypes", currentTestrayCaseTypes);

		List<KeyValuePair> availableTestrayComponents = new ArrayList<>();

		for (TestrayComponent testrayComponent : testrayComponents) {
			availableTestrayComponents.add(new KeyValuePair(String.valueOf(testrayComponent.getTestrayComponentId()), testrayComponent.getName()));
		}

		List<KeyValuePair> currentTestrayComponents = new ArrayList<>();

		for (String testrayComponentIdString : _getCaseParameterValues(caseParametersJSONArray, "testrayComponentId")) {
			TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.getTestrayComponent(GetterUtil.getLong(testrayComponentIdString));

			KeyValuePair keyValuePair = new KeyValuePair(testrayComponentIdString, testrayComponent.getName());

			availableTestrayComponents.remove(keyValuePair);
			currentTestrayComponents.add(keyValuePair);
		}

		renderRequest.setAttribute("availableTestrayComponents", availableTestrayComponents);
		renderRequest.setAttribute("currentTestrayComponents", currentTestrayComponents);

		AlloyServiceInvoker testrayRequirementAlloyServiceInvoker = new AlloyServiceInvoker(TestrayRequirement.class.getName());

		List<TestrayRequirement> testrayRequirements = testrayRequirementAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId});

		List<KeyValuePair> availableTestrayRequirements = new ArrayList<>();

		for (TestrayRequirement testrayRequirement : testrayRequirements) {
			availableTestrayRequirements.add(new KeyValuePair(String.valueOf(testrayRequirement.getTestrayRequirementId()), _getTestrayRequirementDisplayName(testrayRequirement)));
		}

		List<KeyValuePair> currentTestrayRequirements = new ArrayList<>();

		for (String testrayRequirementIdString : _getCaseParameterValues(caseParametersJSONArray, "testrayRequirementId")) {
			TestrayRequirement testrayRequirement = TestrayRequirementLocalServiceUtil.getTestrayRequirement(GetterUtil.getLong(testrayRequirementIdString));

			KeyValuePair keyValuePair = new KeyValuePair(testrayRequirementIdString, _getTestrayRequirementDisplayName(testrayRequirement));

			availableTestrayRequirements.remove(keyValuePair);
			currentTestrayRequirements.add(keyValuePair);
		}

		renderRequest.setAttribute("availableTestrayRequirements", availableTestrayRequirements);
		renderRequest.setAttribute("currentTestrayRequirements", currentTestrayRequirements);

		render("suites/select_case_parameters");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"description", "id", "name", "testrayProjectId"}, parameterTypes = {String.class, String.class, String.class, Long.class})
	public void update() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateUpdate(testraySuite);

		boolean smartSuite = ParamUtil.getBoolean(request, "smartSuite");

		if (smartSuite) {
			String caseParametersString = ParamUtil.getString(request, "caseParametersString");

			testraySuite.setCaseParameters(caseParametersString);
		}

		updateModel(testraySuite);

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		if (addTestrayCaseIds.length > 0) {
			TestraySuiteUtil.removeTestrayCases(testraySuite.getTestraySuiteId());

			TestraySuiteUtil.addTestrayCases(testraySuite.getTestraySuiteId(), addTestrayCaseIds);
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testraySuite.getTestraySuiteId(), "testrayProjectId", testraySuite.getTestrayProjectId());

		if (isRespondingTo("json")) {
			respondWith(_getTestraySuiteJSONObject(testraySuite));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void updateCaseParameters() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateCaseParameters(testraySuite);

		JSONArray caseParametersJSONArray = JSONFactoryUtil.createJSONArray();

		TestraySuiteUtil.addCaseParameter(request, "priority", "int", caseParametersJSONArray);
		TestraySuiteUtil.addCaseParameter(request, "subcomponentId", "long", caseParametersJSONArray);
		TestraySuiteUtil.addCaseParameter(request, "testrayCaseTypeId", "long", caseParametersJSONArray);
		TestraySuiteUtil.addCaseParameter(request, "testrayComponentId", "long", caseParametersJSONArray);
		TestraySuiteUtil.addCaseParameter(request, "testrayRequirementId", "long", caseParametersJSONArray);
		TestraySuiteUtil.addCaseParameter(request, "testrayTeamId", "long", caseParametersJSONArray);

		testraySuite.setCaseParameters(caseParametersJSONArray.toString());

		updateModelIgnoreRequest(testraySuite);

		TestraySuiteUtil.refreshCases(testraySuite);

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

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"addTestrayCaseIds", "id", "testrayProjectId"}, parameterTypes = {String.class, String.class, Long.class})
	public void updateCases() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateUpdateCases(testraySuite);

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		TestraySuiteUtil.addTestrayCases(testraySuite.getTestraySuiteId(), addTestrayCaseIds);

		if (isRespondingTo("json")) {
			respondWith(_getTestraySuiteJSONObject(testraySuite));

			return;
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

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void view() throws Exception {
		TestraySuite testraySuite = _fetchTestraySuite();

		_validateView(testraySuite);

		if (Validator.isNotNull(testraySuite.getCaseParameters())) {
			TestraySuiteUtil.refreshCases(testraySuite);

			JSONArray caseParametersJSONArray = JSONFactoryUtil.createJSONArray(testraySuite.getCaseParameters());

			renderRequest.setAttribute("caseParametersMap", _getCaseParameterMap(caseParametersJSONArray));
		}

		if (isRespondingTo("json")) {
			respondWith(_getTestraySuiteJSONObject(testraySuite));

			return;
		}

		renderRequest.setAttribute("testraySuite", testraySuite);

		SearchContainer<TestrayCase> searchContainer = new SearchContainer<TestrayCase>(portletRequest, portletURL, null, null);

		List<TestrayCase> testrayCases = TestrayCaseLocalServiceUtil.getTestraySuiteTestrayCases(testraySuite.getTestraySuiteId(), searchContainer.getStart(), searchContainer.getEnd());

		renderRequest.setAttribute("testrayCases", testrayCases);

		long testrayCasesCount = TestrayCaseLocalServiceUtil.getTestraySuiteTestrayCasesCount(testraySuite.getTestraySuiteId());

		renderRequest.setAttribute("testrayCasesCount", testrayCasesCount);
	}

	@Override
	protected Indexer buildIndexer() {
		return TestraySuiteIndexer.getInstance();
	}

	private TestraySuite _fetchTestraySuite() throws Exception {
		TestraySuite testraySuite = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testraySuite = TestraySuiteLocalServiceUtil.fetchTestraySuite(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestraySuite> testraySuites = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", id.substring(1)});

			if (!testraySuites.isEmpty()) {
				testraySuite = testraySuites.get(0);
			}
		}

		return testraySuite;
	}

	private Map<String, String> _getCaseParameterMap(JSONArray caseParametersJSONArray) throws Exception {
		Map<String, String> caseParametersMap = new HashMap<>();

		List<String> priorities = _getCaseParameterValues(caseParametersJSONArray, "priority");

		caseParametersMap.put("priority", StringUtil.merge(priorities, StringPool.COMMA_AND_SPACE));

		List<String> testrayComponentNames = new ArrayList<>();

		List<String> testrayComponentIdStrings = _getCaseParameterValues(caseParametersJSONArray, "testrayComponentId");

		for (String testrayComponentIdString : testrayComponentIdStrings) {
			TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.getTestrayComponent(GetterUtil.getLong(testrayComponentIdString));

			testrayComponentNames.add(testrayComponent.getName());
		}

		caseParametersMap.put("testrayComponentNames", StringUtil.merge(testrayComponentNames, StringPool.COMMA_AND_SPACE));

		List<String> testraySubcomponentNames = new ArrayList<>();

		List<String> testraySubcomponentIdStrings = _getCaseParameterValues(caseParametersJSONArray, "subcomponentId");

		for (String testraySubcomponentIdString : testraySubcomponentIdStrings) {
			TestrayComponent testraySubcomponent = TestrayComponentLocalServiceUtil.getTestrayComponent(GetterUtil.getLong(testraySubcomponentIdString));

			testraySubcomponentNames.add(testraySubcomponent.getName());
		}

		caseParametersMap.put("testraySubcomponentNames", StringUtil.merge(testraySubcomponentNames, StringPool.COMMA_AND_SPACE));

		List<String> testrayCaseTypeNames = new ArrayList<>();

		List<String> testrayCaseTypeIdStrings = _getCaseParameterValues(caseParametersJSONArray, "testrayCaseTypeId");

		for (String testrayCaseTypeIdString : testrayCaseTypeIdStrings) {
			TestrayCaseType testrayCaseType = TestrayCaseTypeLocalServiceUtil.getTestrayCaseType(GetterUtil.getLong(testrayCaseTypeIdString));

			testrayCaseTypeNames.add(testrayCaseType.getName());
		}

		caseParametersMap.put("testrayCaseTypeNames", StringUtil.merge(testrayCaseTypeNames, StringPool.COMMA_AND_SPACE));

		List<String> testrayRequirementKeys = new ArrayList<>();

		List<String> testrayRequirementIdStrings = _getCaseParameterValues(caseParametersJSONArray, "testrayRequirementId");

		for (String testrayRequirementIdString : testrayRequirementIdStrings) {
			TestrayRequirement testrayRequirement = TestrayRequirementLocalServiceUtil.getTestrayRequirement(GetterUtil.getLong(testrayRequirementIdString));

			testrayRequirementKeys.add(testrayRequirement.getKey());
		}

		caseParametersMap.put("testrayRequirementKeys", StringUtil.merge(testrayRequirementKeys, StringPool.COMMA_AND_SPACE));

		return caseParametersMap;
	}

	private List<String> _getCaseParameterValues(JSONArray caseParametersJSONArray, String parameter) {
		for (int i = 0; i < caseParametersJSONArray.length(); i++) {
			JSONObject caseParameterJSONObject = caseParametersJSONArray.getJSONObject(i);

			if (parameter.equals(caseParameterJSONObject.getString("fieldName"))) {
				JSONArray fieldValuesJSONArray = caseParameterJSONObject.getJSONArray("fieldValues");

				List<String> caseParameterValues = new ArrayList<>(fieldValuesJSONArray.length());

				for (int j = 0; j < fieldValuesJSONArray.length(); j++) {
					caseParameterValues.add(fieldValuesJSONArray.getString(j));
				}

				return caseParameterValues;
			}
		}

		return Collections.emptyList();
	}

	private String _getTestrayRequirementDisplayName(TestrayRequirement testrayRequirement) {
		StringBundler sb = new StringBundler(5);

		sb.append(testrayRequirement.getKey());
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(testrayRequirement.getSummary());
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private JSONObject _getTestraySuiteJSONObject(TestraySuite testraySuite) throws Exception {
		TestraySuiteComposite testraySuiteComposite = new TestraySuiteComposite(testraySuite);

		return testraySuiteComposite.getJSONObject();
	}

	private JSONArray _getTestraySuitesJSONArray(List<BaseModel<?>> baseModels) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (BaseModel<?> baseModel : baseModels) {
			jsonArray.put(_getTestraySuiteJSONObject((TestraySuite)baseModel));
		}

		return jsonArray;
	}

	private void _search(Map<String, Serializable> attributes) throws Exception {
		String orderByCol = ParamUtil.getString(request, "orderByCol", "name_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		AlloySearchResult alloySearchResult = search(attributes, null, new Sort(orderByCol, orderByType.equals("desc")));

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);
	}

	private void _validateAdd() throws Exception {
		_validateName();
	}

	private void _validateCaseParameters(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateDelete(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateEdit(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-suite-name-is-invalid", false);
		}

		List<TestraySuite> testraySuites = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", name});

		if (!testraySuites.isEmpty()) {
			long testraySuiteId = ParamUtil.getLong(request, "id");

			TestraySuite testraySuite = testraySuites.get(0);

			if (testraySuite.getTestraySuiteId() != testraySuiteId) {
				throw new AlloyException("the-suite-name-already-exists", false);
			}
		}
	}

	private void _validateRefreshCases(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateRemoveCase(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateSelectCaseParameters(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateTestraySuite(TestraySuite testraySuite) throws Exception {
		if ((testraySuite == null) || testraySuite.isNew()) {
			long testraySuiteId = ParamUtil.getLong(request, "id");

			throw new AlloyException(translate("the-suite-with-id-x-does-not-exist", testraySuiteId), false);
		}
	}

	private void _validateUpdate(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);

		_validateName();
	}

	private void _validateUpdateCases(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

	private void _validateView(TestraySuite testraySuite) throws Exception {
		_validateTestraySuite(testraySuite);
	}

}
%>