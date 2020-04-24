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
		setAlloyServiceInvokerClass(TestrayFactor.class);
		setPermissioned(true);
	}

	public void addTestrayFactors() throws Exception {
		String className = ParamUtil.getString(request, "className");
		long classPK = ParamUtil.getLong(request, "classPK");

		try {
			if (className.equals(TestrayRun.class.getName()) && (classPK == 0)) {
				TestrayRun testrayRun = _addTestrayRun();

				classPK = testrayRun.getTestrayRunId();
			}

			_addTestrayFactors(className, classPK);

			if (className.equals(TestrayRun.class.getName())) {
				updateModelIgnoreRequest(TestrayRunLocalServiceUtil.getTestrayRun(classPK), "environmentHash", TestrayRunUtil.getEnvironmentHash(classPK));
			}
		}
		catch (Exception e) {
			throw new AlloyException(e.getMessage());
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

	public void addTestrayRuns() throws Exception {
		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");
		int testrayFactorCategoriesCount = ParamUtil.getInteger(request, "testrayFactorCategoriesCount");

		Set<Set<Long>> testrayFactorOptionIdSets = TestrayFactorUtil.getExistingTestrayFactorOptionIdSets(testrayBuildId);

		int autoTestrayRunsCount = ParamUtil.getInteger(request, "autoTestrayRunsCount");

		for (int i = 0; i < autoTestrayRunsCount; i++) {
			TestrayRunUtil.addTestrayRun(this, testrayBuildId, testrayFactorCategoriesCount, testrayFactorOptionIdSets, "_auto", i);
		}

		int[] testrayFactorIndexes = ParamUtil.getIntegerValues(request, "testrayFactorIndexes");

		for (int testrayFactorIndex : testrayFactorIndexes) {
			TestrayRunUtil.addTestrayRun(this, testrayBuildId, testrayFactorCategoriesCount, testrayFactorOptionIdSets, "_custom", testrayFactorIndex);
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
		String className = ParamUtil.getString(request, "className");

		long testrayBuildId = ParamUtil.getLong(request, "classPK");

		if (className.equals(TestrayBuild.class.getName()) && (testrayBuildId > 0)) {
			portletRequest.setAttribute("testrayBuildId", testrayBuildId);

			try {
				_addTestrayFactors(TestrayBuild.class.getName(), testrayBuildId);
			}
			catch (Exception e) {
				throw new AlloyException(e.getMessage());
			}
		}

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		Map<TestrayFactorCategory, List<TestrayFactorOption>> testrayFactorCategoryMap = TestrayFactorCategoryUtil.getTestrayFactorCategoryMap(TestrayRoutine.class.getName(), testrayRoutineId);

		portletRequest.setAttribute("testrayFactorCategoryMap", testrayFactorCategoryMap);

		String redirect = ParamUtil.getString(request, "redirect");

		portletRequest.setAttribute("redirect", redirect);

		List<List<TestrayFactor>> testrayFactorCombinations = TestrayFactorUtil.getTestrayFactorCombinations(this);

		portletRequest.setAttribute("testrayFactorCombinations", testrayFactorCombinations);

		if (renderRequest == null) {
			render("factors/select");
		}
	}

	private void _addTestrayFactors(String className, long classPK) throws Exception {
		TestrayFactorUtil.deleteTestrayFactors(className, classPK);

		int testrayFactorCategoriesCount = ParamUtil.getInteger(request, "testrayFactorCategoriesCount");

		for (int i = 0; i < testrayFactorCategoriesCount; i++) {
			long testrayFactorCategoryId = ParamUtil.getLong(request, "testrayFactorCategoryId" + i);
			String testrayFactorCategoryName = ParamUtil.getString(request, "testrayFactorCategoryName" + i);
			long[] testrayFactorOptionIds = ParamUtil.getLongValues(request, "testrayFactorOptionId" + i);

			_validateSave(testrayFactorCategoryId, testrayFactorOptionIds);

			for (long testrayFactorOptionId : testrayFactorOptionIds) {
				if (testrayFactorOptionId == 0) {
					continue;
				}

				TestrayFactorUtil.addTestrayFactor(this, className, classPK, testrayFactorCategoryId, testrayFactorCategoryName, testrayFactorOptionId, null);
			}
		}
	}

	private TestrayRun _addTestrayRun() throws Exception {
		TestrayRun testrayRun = TestrayRunLocalServiceUtil.createTestrayRun(increment());

		testrayRun.setExternalReferencePK(String.valueOf(testrayRun.getTestrayRunId()));
		testrayRun.setName(String.valueOf(testrayRun.getTestrayRunId()));

		long testrayBuildId = ParamUtil.getLong(request, "testrayBuildId");

		testrayRun.setTestrayBuildId(testrayBuildId);

		Set<Long> testrayFactorOptionIds = new HashSet<Long>();

		int testrayFactorCategoriesCount = ParamUtil.getInteger(request, "testrayFactorCategoriesCount");

		for (int i = 0; i < testrayFactorCategoriesCount; i++) {
			long testrayFactorOptionId = ParamUtil.getLong(request, "testrayFactorOptionId" + i);

			testrayFactorOptionIds.add(testrayFactorOptionId);
		}

		Set<Set<Long>> testrayFactorOptionIdSets = TestrayFactorUtil.getExistingTestrayFactorOptionIdSets(testrayBuildId);

		if (testrayFactorOptionIdSets.contains(testrayFactorOptionIds)) {
			throw new AlloyException("a-run-with-these-factor-options-already-exists", false);
		}

		long number = TestrayRunUtil.getNumber(testrayFactorOptionIds, testrayBuildId);

		testrayRun.setNumber(number);

		updateModel(testrayRun, "testrayRunId", testrayRun.getTestrayRunId());

		return testrayRun;
	}

	private void _validateSave(long testrayFactorCategoryId, long[] testrayFactorOptionIds) throws Exception {
		if ((testrayFactorCategoryId <= 0) || (testrayFactorOptionIds.length == 0)) {
			throw new AlloyException("please-choose-an-option-for-every-category", false);
		}
	}

}
%>