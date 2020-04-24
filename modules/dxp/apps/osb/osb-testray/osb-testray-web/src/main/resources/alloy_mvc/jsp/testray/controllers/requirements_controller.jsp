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
<%@ include file="/alloy_mvc/jsp/util/testray_requirement_indexer.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/testray_requirement_scheduler_message_listener.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayRequirement.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"components", "linkTitle", "linkURL", "summary", "testrayCases", "testrayComponentId", "testrayComponentName", "testrayProjectId"}, parameterTypes = {String.class, String.class, String.class, String.class, String.class, Long.class, String.class, Long.class})
	public void add() throws Exception {
		TestrayRequirement testrayRequirement = TestrayRequirementLocalServiceUtil.createTestrayRequirement(0);

		_validateAdd();

		testrayRequirement.setKey(TestrayRequirementUtil.getKey(testrayProjectId));

		long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId");
		String testrayComponentName = ParamUtil.getString(request, "testrayComponentName");

		if ((testrayComponentId <= 0) && Validator.isNotNull(testrayComponentName)) {
			TestrayComponent testrayComponent = TestrayComponentUtil.fetchTestrayComponent(testrayProjectId, testrayComponentName);

			testrayRequirement.setTestrayComponentId(testrayComponent.getTestrayComponentId());
		}

		testrayRequirement.setTestrayProjectId(testrayProjectId);

		updateModel(testrayRequirement);

		String testrayCasesJSON = ParamUtil.getString(request, "testrayCases");

		if (Validator.isNotNull(testrayCasesJSON)) {
			long[] testrayCaseIds = _getTestrayCaseIds(testrayCasesJSON);

			TestrayCaseLocalServiceUtil.setTestrayRequirementTestrayCases(testrayRequirement.getTestrayRequirementId(), testrayCaseIds);

			for (long testrayCaseId : testrayCaseIds) {
				TestrayCase testrayCase = TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId);

				indexModel(testrayCase);
			}
		}

		indexModel(testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	public void create() throws Exception {
		_validateCreate();

		renderRequest.setAttribute("TestrayRichTextConstants", getConstantsBean(TestrayRichTextConstants.class));

		TestrayRequirement testrayRequirement = TestrayRequirementLocalServiceUtil.createTestrayRequirement(0);

		testrayRequirement.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testrayRequirement", testrayRequirement);

		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayComponentModelImpl.TABLE_NAME, "name", true);

		List<TestrayComponent> testrayComponents = testrayComponentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayComponents", testrayComponents);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void delete() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateDelete(testrayRequirement);

		TestrayRequirementLocalServiceUtil.deleteTestrayRequirement(testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	public void edit() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateEdit(testrayRequirement);

		renderRequest.setAttribute("TestrayRichTextConstants", getConstantsBean(TestrayRichTextConstants.class));

		renderRequest.setAttribute("testrayRequirement", testrayRequirement);

		AlloyServiceInvoker testrayComponentAlloyServiceInvoker = new AlloyServiceInvoker(TestrayComponent.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayComponentModelImpl.TABLE_NAME, "name", true);

		List<TestrayComponent> testrayComponents = testrayComponentAlloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayComponents", testrayComponents);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"issueKeys", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	@RequireIssueEngineAuthorization
	public void importIssues() throws Exception {
		_validateTestrayProject();

		List<String> failedIssueKeys = new ArrayList<String>();

		String[] issueKeys = ParamUtil.getStringValues(request, "issueKeys");

		for (String issueKey : issueKeys) {
			TestrayRequirement testrayRequirement = null;

			try {
				testrayRequirement = TestrayRequirementUtil.fetchOrCreateTestrayRequirementFromIssue(testrayProjectId, issueKey.trim(), user);
			}
			catch (Exception e) {
				log.info(e, e);

				failedIssueKeys.add(issueKey.trim());

				continue;
			}

			boolean newRequirement = testrayRequirement.isNew();

			updateModelIgnoreRequest(testrayRequirement);

			if (newRequirement) {
				String url = TestrayUtil.getURL(this, "requirements", null, testrayRequirement.getTestrayRequirementId());

				TestrayIssueEngineUtil.addLink(url, testrayRequirement.getKey(), issueKey.trim(), user);
			}
		}

		String pattern = "the-issues-x-failed-to-import";

		if (failedIssueKeys.size() == 1) {
			pattern = "the-issue-x-failed-to-import";
		}

		String message = translate(pattern, StringUtil.merge(failedIssueKeys, StringPool.COMMA_AND_SPACE));

		if (isRespondingTo("json")) {
			if (!failedIssueKeys.isEmpty()) {
				respondWith(message);
			}
			else {
				respondWith(translate("your-request-completed-successfully"));
			}

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (!failedIssueKeys.isEmpty()) {
			renderError(message);
		}
		else if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	private String _getTestrayRequirementsCSV(List<TestrayRequirementComposite> testrayRequirementComposites) throws Exception {
		TestrayCSVObject testrayCSVObject = new TestrayCSVObject(3, testrayRequirementComposites.size());

		testrayCSVObject.addLocalizedCells(request, "summary", "linkURL", "case-name");

		testrayCSVObject.startNewRow();

		for (TestrayRequirementComposite testrayRequirementComposite : testrayRequirementComposites) {
			for (TestrayCase testrayCase : testrayRequirementComposite.getTestrayCases()) {
				testrayCSVObject.addCells(testrayRequirementComposite.getSummary(), testrayRequirementComposite.getLinkURL(), testrayCase.getName());

				testrayCSVObject.startNewRow();
			}
		}

		return testrayCSVObject.toString();
	}

	@RequireIssueEngineAuthorization
	public void importRequirements() throws Exception {
		_validateImportRequirements();

		Map<String, TestrayRequirement> testrayRequirementsMap = new HashMap<>();

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		File requirementsFile = uploadPortletRequest.getFile("requirements");

		TestrayCSVObject testrayCSVObject = new TestrayCSVObject(FileUtil.read(requirementsFile));

		for (int rowIndex = 1; rowIndex < testrayCSVObject.getRowsCount(); rowIndex++) {
			TestrayRequirement testrayRequirement = null;

			String summary = GetterUtil.getString(testrayCSVObject.get(rowIndex, 0));
			String linkURL = GetterUtil.getString(testrayCSVObject.get(rowIndex, 1));

			if (testrayRequirementsMap.containsKey(linkURL)) {
				testrayRequirement = testrayRequirementsMap.get(linkURL);
			}
			else {
				testrayRequirement = TestrayRequirementUtil.fetchOrCreateTestrayRequirement(testrayProjectId, summary, linkURL, user);

				if (testrayRequirement.isNew()) {
					updateModelIgnoreRequest(testrayRequirement);
				}

				testrayRequirementsMap.put(linkURL, testrayRequirement);
			}

			String testrayCaseName = GetterUtil.getString(testrayCSVObject.get(rowIndex, 2));

			TestrayCase testrayCase = TestrayCaseUtil.fetchTestrayCase(testrayProjectId, testrayCaseName);

			if (testrayCase == null) {
				throw new AlloyException("the-case-with-name-x-does-not-exist-for-the-project-with-id-x", new Object[] {testrayCaseName, testrayProjectId}, false);
			}

			TestrayCaseLocalServiceUtil.addTestrayRequirementTestrayCase(testrayRequirement.getTestrayRequirementId(), testrayCase);

			indexModel(testrayCase);
		}

		for (TestrayRequirement testrayRequirement : testrayRequirementsMap.values()) {
			indexModel(testrayRequirement);
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "orderByCol", "orderByType", "start", "testrayProjectId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Long.class})
	public void index() throws Exception {
		_validateIndex();

		renderRequest.setAttribute("TestrayRichTextConstants", getConstantsBean(TestrayRichTextConstants.class));

		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		boolean testrayCaseNameBlankOnly = ParamUtil.getBoolean(request, "testrayCaseNameBlankOnly");

		if (testrayCaseNameBlankOnly) {
			attributes.put("hasTestrayCases", false);
		}

		String orderByCol = ParamUtil.getString(request, "orderByCol", "key_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(indexer, alloyServiceInvoker, request, portletRequest, attributes, null, new Sort[] {new Sort(orderByCol, orderByType.equals("desc"))}, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		List<TestrayRequirementComposite> testrayRequirementComposites = TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayRequirementComposite.class);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementsJSONArray(TestrayUtil.getSubclassList(alloySearchResult.getBaseModels(), TestrayRequirement.class)));
		}
		else if (isRespondingTo("csv")) {
			respondWith(null, _getTestrayRequirementsCSV(testrayRequirementComposites));
		}
		else {
			renderRequest.setAttribute("TestrayRequirementConstants", getConstantsBean(TestrayRequirementConstants.class));

			renderRequest.setAttribute("testrayRequirementComposites", testrayRequirementComposites);

			TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

			renderRequest.setAttribute("testrayProject", testrayProject);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayCaseId", "testrayProjectId"}, parameterTypes = {String.class, Long.class, Long.class})
	public void removeCase() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateRemoveCase(testrayRequirement);

		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId");

		TestrayCaseLocalServiceUtil.deleteTestrayRequirementTestrayCase(testrayRequirement.getTestrayRequirementId(), testrayCaseId);

		indexModel(TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId));
		indexModel(testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	public void select() throws Exception {
		_validateSelect();

		renderRequest.setAttribute("TestrayRichTextConstants", getConstantsBean(TestrayRichTextConstants.class));

		renderRequest.setAttribute("TestrayRequirementConstants", getConstantsBean(TestrayRequirementConstants.class));

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("testrayCaseId", null);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "key_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(indexer, alloyServiceInvoker, request, portletRequest, attributes, null, new Sort[] {new Sort(orderByCol, orderByType.equals("desc"))}, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId");

		portletURL.setParameter("testrayCaseId", String.valueOf(testrayCaseId));

		renderRequest.setAttribute("rowChecker", new TestrayRequirementChecker(renderResponse, testrayCaseId));

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);

		renderRequest.setAttribute("testrayRequirementComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayRequirementComposite.class));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	@RequireIssueEngineAuthorization
	public void sync() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateSync(testrayRequirement);

		TestrayRequirementUtil.updateTestrayRequirementFromIssue(this, testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"components", "id", "key", "linkTitle", "linkURL", "summary", "testrayComponentId", "testrayComponentName", "testrayProjectId"}, parameterTypes = {String.class, String.class, String.class, String.class, String.class, String.class, Long.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateUpdate(testrayRequirement);

		long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId");
		String testrayComponentName = ParamUtil.getString(request, "testrayComponentName");

		if ((testrayComponentId <= 0) && Validator.isNotNull(testrayComponentName)) {
			TestrayComponent testrayComponent = TestrayComponentUtil.fetchTestrayComponent(testrayProjectId, testrayComponentName);

			testrayRequirement.setTestrayComponentId(testrayComponent.getTestrayComponentId());
		}

		updateModel(testrayRequirement);

		String testrayCasesJSON = ParamUtil.getString(request, "testrayCases");

		if (Validator.isNotNull(testrayCasesJSON)) {
			long[] newTestrayCaseIds = _getTestrayCaseIds(testrayCasesJSON);

			long[] oldTestrayCaseIds = TestrayRequirementLocalServiceUtil.getTestrayCasePrimaryKeys(testrayRequirement.getTestrayRequirementId());

			TestrayCaseLocalServiceUtil.setTestrayRequirementTestrayCases(testrayRequirement.getTestrayRequirementId(), newTestrayCaseIds);

			for (long testrayCaseId : newTestrayCaseIds) {
				if (ArrayUtil.contains(oldTestrayCaseIds, testrayCaseId)) {
					continue;
				}

				TestrayCase testrayCase = TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId);

				indexModel(testrayCase);
			}

			for (long testrayCaseId : oldTestrayCaseIds) {
				if (ArrayUtil.contains(newTestrayCaseIds, testrayCaseId)) {
					continue;
				}

				TestrayCase testrayCase = TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId);

				indexModel(testrayCase);
			}
		}

		indexModel(testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"addTestrayCaseIds", "id"}, parameterTypes = {String.class, Long.class})
	public void updateCases() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateUpdateCases(testrayRequirement);

		long[] addTestrayCaseIds = ParamUtil.getLongValues(request, "addTestrayCaseIds");

		if (ArrayUtil.isNotEmpty(addTestrayCaseIds)) {
			TestrayCaseLocalServiceUtil.addTestrayRequirementTestrayCases(testrayRequirement.getTestrayRequirementId(), addTestrayCaseIds);

			for (long testrayCaseId : addTestrayCaseIds) {
				TestrayCase testrayCase = TestrayCaseLocalServiceUtil.getTestrayCase(testrayCaseId);

				indexModel(testrayCase);
			}

			indexModel(testrayRequirement);
		}

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void view() throws Exception {
		TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

		_validateView(testrayRequirement);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRequirementJSONObject(testrayRequirement));

			return;
		}

		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayRichTextConstants", getConstantsBean(TestrayRichTextConstants.class));

		TestrayRequirementComposite testrayRequirementComposite = new TestrayRequirementComposite(testrayRequirement);

		renderRequest.setAttribute("testrayRequirementComposite", testrayRequirementComposite);

		Map<String, Serializable> attributes = _getTestrayCaseAttributes(testrayRequirementComposite);

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "createDate_sortable");
		String orderByType = ParamUtil.getString(request, "orderByType", "desc");

		renderRequest.setAttribute("orderByCol", orderByCol);
		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
				new Sort(orderByCol, orderByType.equals("desc")),
				new Sort("status_sortable", true),
				new Sort("errors_sortable", true),
				new Sort("testrayCasePriority_sortable", true),
				new Sort("testrayTeamName_sortable", true),
				new Sort("testrayCaseName_sortable", true)
		};

		AlloySearchResult alloySearchResult = search(IndexerRegistryUtil.nullSafeGetIndexer(TestrayCase.class), new AlloyServiceInvoker(TestrayCase.class.getName()), request, portletRequest, attributes, null, sorts, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("testrayCaseCompositesTotal", alloySearchResult.getSize());

		List<TestrayCase> testrayCases = TestrayUtil.getSubclassList(alloySearchResult.getBaseModels(), TestrayCase.class);

		renderRequest.setAttribute("testrayCaseComposites", TestrayCompositeUtil.getComposites(testrayCases, TestrayCaseComposite.class, new Class<?>[] {TestrayCase.class}, new Object[0]));
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayRequirementIndexer.getInstance();
	}

	@Override
	protected MessageListener buildSchedulerMessageListener() {
		return TestrayRequirementSchedulerMessageListener.getInstance(this);
	}

	@Override
	protected Trigger getSchedulerTrigger() {
		return TriggerFactoryUtil.createTrigger(getSchedulerJobName(), getMessageListenerGroupName(), PortletPropsValues.TESTRAY_CRON_TRIGGER_REQUIREMENTS_CONTROLLER);
	}

	private TestrayRequirement _fetchTestrayRequirement() throws Exception {
		TestrayRequirement testrayRequirement = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayRequirement = TestrayRequirementLocalServiceUtil.fetchTestrayRequirement(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestrayRequirement> testrayRequirements = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "key", id.substring(1)});

			if (!testrayRequirements.isEmpty()) {
				testrayRequirement = testrayRequirements.get(0);
			}
		}

		return testrayRequirement;
	}

	private String _getCSVField(String value) {
		if ((value.charAt(0) == CharPool.QUOTE) || (value.charAt(0) == CharPool.APOSTROPHE)) {
			return value.substring(1, value.length() - 1);
		}

		return value;
	}

	private Map<String, Serializable> _getTestrayCaseAttributes(TestrayRequirementComposite testrayRequirementComposite) throws Exception {
		Map<String, Serializable> attributes = new HashMap<String, Serializable>();

		attributes.put("testrayProjectId", testrayRequirementComposite.getTestrayProjectId());
		attributes.put("testrayRequirementId", testrayRequirementComposite.getTestrayRequirementId());

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		if (ArrayUtil.isNotEmpty(priorities)) {
			attributes.put("testrayCasePriority", StringUtil.merge(priorities));
		}

		long testrayCaseTypeId = ParamUtil.getLong(request, "testrayCaseTypeId");

		if (testrayCaseTypeId > 0) {
			attributes.put("testrayCaseTypeId", testrayCaseTypeId);
		}

		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId");
		String testrayCaseName = ParamUtil.getString(request, "testrayCaseName");

		if ((testrayCaseId > 0) || Validator.isNotNull(testrayCaseName)) {
			TestrayCase testrayCase = TestrayCaseUtil.getTestrayCase(request);

			attributes.put("testrayCaseId", testrayCase.getTestrayCaseId());
		}

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		if (testrayTeamId > 0) {
			attributes.put("testrayTeamId", testrayTeamId);
		}

		long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId");

		if (testrayComponentId > 0) {
			attributes.put("testrayComponentId", testrayComponentId);
		}

		String description = ParamUtil.getString(request, "description");
		boolean descriptionBlankOnly = ParamUtil.getBoolean(request, "descriptionBlankOnly");

		if (descriptionBlankOnly) {
			attributes.put("blankDescription", true);
		}
		else if (Validator.isNotNull(description)) {
			attributes.put("description", description);
		}

		String issues = ParamUtil.getString(request, "issues");
		boolean issuesBlankOnly = ParamUtil.getBoolean(request, "issuesBlankOnly");

		if (issuesBlankOnly) {
			attributes.put("blankIssues", true);
		}
		else if (Validator.isNotNull(issues)) {
			attributes.put("issues", issues);
		}

		return attributes;
	}

	private long[] _getTestrayCaseIds(String testrayCasesJSON) throws Exception {
		JSONArray jsonArray = null;

		try {
			jsonArray = JSONFactoryUtil.createJSONArray(testrayCasesJSON);
		}
		catch (JSONException jsone) {
			throw new AlloyException("the-testrayCases-parameter-must-be-a-json-array-of-json-objects", false);
		}

		long[] testrayCaseIds = new long[jsonArray.length()];

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (jsonObject == null) {
				throw new AlloyException("the-testrayCases-parameter-must-be-a-json-array-of-json-objects", false);
			}

			if (jsonObject.has("testrayCaseId")) {
				long testrayCaseId = jsonObject.getLong("testrayCaseId");

				TestrayCase testrayCase = TestrayCaseLocalServiceUtil.fetchTestrayCase(testrayCaseId);

				if (testrayCase == null) {
					throw new AlloyException("the-case-with-id-x-does-not-exist", false);
				}

				testrayCaseIds[i] = testrayCaseId;
			}
			else if (jsonObject.has("name")) {
				String name = jsonObject.getString("name");

				TestrayCase testrayCase = TestrayCaseUtil.fetchTestrayCase(testrayProjectId, name);

				if (testrayCase == null) {
					throw new AlloyException("the-case-with-name-x-does-not-exist-for-the-project-with-id-x", new Object[] {name, testrayProjectId}, false);
				}

				testrayCaseIds[i] = testrayCase.getTestrayCaseId();
			}
			else {
				throw new AlloyException("a-name-or-id-must-be-provided-for-each-case", false);
			}
		}

		return testrayCaseIds;
	}

	private JSONObject _getTestrayRequirementJSONObject(TestrayRequirement testrayRequirement) throws Exception {
		TestrayRequirementComposite testrayRequirementComposite = new TestrayRequirementComposite(testrayRequirement);

		return testrayRequirementComposite.getJSONObject();
	}

	private JSONArray _getTestrayRequirementsJSONArray(List<TestrayRequirement> testrayRequirements) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayRequirement testrayRequirement : testrayRequirements) {
			jsonArray.put(_getTestrayRequirementJSONObject(testrayRequirement));
		}

		return jsonArray;
	}

	private void _validateAdd() throws Exception {
		_validateLinkURL();
		_validateSummary();
		_validateSummaryRequired();
		_validateTestrayComponent();
		_validateTestrayProject();
		_validateTestrayProjectRequired();
	}

	private void _validateCreate() throws Exception {
		_validateTestrayProject();
		_validateTestrayProjectRequired();
	}

	private void _validateDelete(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);
	}

	private void _validateEdit(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);
	}

	private void _validateImportRequirements() throws Exception {
		_validateTestrayProject();
		_validateTestrayProjectRequired();
	}

	private void _validateIndex() throws Exception {
		_validateTestrayProject();
		_validateTestrayProjectRequired();
	}

	private void _validateLinkURL() throws Exception {
		String linkURL = ParamUtil.getString(request, "linkURL");

		if (Validator.isNotNull(linkURL) && !Validator.isUrl(linkURL)) {
			throw new AlloyException("the-link-url-must-be-a-valid-url", false);
		}

		List<TestrayRequirement> testrayRequirements = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "linkURL", linkURL});

		if (!testrayRequirements.isEmpty()) {
			TestrayRequirement existingTestrayRequirement = testrayRequirements.get(0);
			TestrayRequirement testrayRequirement = _fetchTestrayRequirement();

			if ((testrayRequirement == null) || testrayRequirement.isNew() || (testrayRequirement.getTestrayRequirementId() != existingTestrayRequirement.getTestrayRequirementId())) {
				throw new AlloyException("the-link-url-must-be-unique", false);
			}
		}
	}

	private void _validateRemoveCase(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);

		TestrayValidator.validateClassIdentifier(request, TestrayCase.class, "testrayCaseId", "the-case-with-id-x-does-not-exist");
	}

	private void _validateSelect() throws Exception {
		_validateTestrayCase();
		_validateTestrayCaseRequired();
		_validateTestrayProject();
		_validateTestrayProjectRequired();
	}

	private void _validateSummary() throws Exception {
		String summary = ParamUtil.getString(request, "summary", null);

		if ((summary != null) && summary.equals(StringPool.BLANK)) {
			throw new AlloyException("the-summary-is-invalid", false);
		}
	}

	private void _validateSummaryRequired() throws Exception {
		String summary = ParamUtil.getString(request, "summary");

		if (Validator.isNull(summary)) {
			throw new AlloyException("please-supply-a-summary", false);
		}
	}

	private void _validateTestrayCase() throws Exception {
		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId", -1);
		String testrayCaseName = ParamUtil.getString(request, "testrayCaseName", null);

		if (testrayCaseId >= 0) {
			TestrayCase testrayCase = TestrayCaseLocalServiceUtil.fetchTestrayCase(testrayCaseId);

			if (testrayCase == null) {
				throw new AlloyException("the-case-with-id-x-does-not-exist", new Object[] {testrayCaseId}, false);
			}
		}
		else if (testrayCaseName != null) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			TestrayCase testrayCase = TestrayCaseUtil.fetchTestrayCase(testrayProjectId, testrayCaseName);

			if (testrayCase == null) {
				throw new AlloyException("the-case-x-does-not-exist-in-project-x", new Object[] {testrayCaseName, testrayProjectId}, false);
			}
		}
	}

	private void _validateTestrayCaseRequired() throws Exception {
		long testrayCaseId = ParamUtil.getLong(request, "testrayCaseId", -1);
		String testrayCaseName = ParamUtil.getString(request, "testrayCaseName", null);
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", -1);

		if ((testrayCaseId < 0) && ((testrayCaseName == null) || (testrayProjectId < 0))) {
			throw new AlloyException("a-case-id-or-case-name-and-project-id-is-required", false);
		}
	}

	private void _validateTestrayComponent() throws Exception {
		long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId", -1);
		String testrayComponentName = ParamUtil.getString(request, "testrayComponentName", null);

		if (testrayComponentId >= 0) {
			TestrayComponent testrayComponent = TestrayComponentLocalServiceUtil.fetchTestrayComponent(testrayComponentId);

			if (testrayComponent == null) {
				throw new AlloyException("the-component-with-id-x-does-not-exist", new Object[] {testrayComponentId}, false);
			}
		}
		else if (testrayComponentName != null) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			TestrayComponent testrayComponent = TestrayComponentUtil.fetchTestrayComponent(testrayProjectId, testrayComponentName);

			if (testrayComponent == null) {
				throw new AlloyException("the-component-x-does-not-exist-in-project-x", new Object[] {testrayComponentName, testrayProjectId}, false);
			}
		}
	}

	private void _validateTestrayComponentRequired() throws Exception {
		long testrayComponentId = ParamUtil.getLong(request, "testrayComponentId", -1);
		String testrayComponentName = ParamUtil.getString(request, "testrayComponentName", null);
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId", -1);

		if ((testrayComponentId < 0) && ((testrayComponentName == null) || (testrayProjectId < 0))) {
			throw new AlloyException("a-component-id-or-component-name-and-project-id-is-required", false);
		}
	}

	private void _validateTestrayProject() throws Exception {
		if (testrayProjectId <= 0) {
			return;
		}

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		if (testrayProject == null) {
			throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
		}
	}

	private void _validateTestrayProjectRequired() throws Exception {
		if (testrayProjectId <= 0) {
			throw new AlloyException("a-project-id-is-required", false);
		}
	}

	private void _validateTestrayRequirement(TestrayRequirement testrayRequirement) throws Exception {
		if ((testrayRequirement == null) || testrayRequirement.isNew()) {
			String id = ParamUtil.getString(request, "id");

			if (Validator.isNumber(id)) {
				throw new AlloyException(translate("the-requirement-with-id-x-does-not-exist", GetterUtil.getLong(id)), false);
			}
			else if (id.indexOf(StringPool.UNDERLINE) == 0) {
				long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

				throw new AlloyException(translate("the-requirement-with-key-x-does-not-exist-for-the-project-with-id-x", id.substring(1), testrayProjectId), false);
			}
		}
	}

	private void _validateSync(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);

		List<TestrayIssueEngine.ExternalLinkHelper> externalLinkHelpers = TestrayIssueEngineUtil.getExternalLinkHelpers();

		TestrayIssueEngine.ExternalLinkHelper externalLinkHelper = externalLinkHelpers.get(0);

		String linkURL = externalLinkHelper.getIssueLinkURL(testrayRequirement.getLinkTitle());

		if (!Objects.equals(linkURL, testrayRequirement.getLinkURL())) {
			throw new AlloyException("only-requirements-from-jira-can-be-synced", false);
		}
	}

	private void _validateUpdate(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);

		_validateLinkURL();
		_validateSummary();
		_validateTestrayComponent();
		_validateTestrayProject();
	}

	private void _validateUpdateCases(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);
	}

	private void _validateView(TestrayRequirement testrayRequirement) throws Exception {
		_validateTestrayRequirement(testrayRequirement);
	}

}
%>