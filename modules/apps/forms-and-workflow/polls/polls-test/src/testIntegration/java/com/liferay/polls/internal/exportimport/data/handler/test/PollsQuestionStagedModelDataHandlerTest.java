/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.polls.util.test.PollsTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.lar.test.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@RunWith(Arquillian.class)
public class PollsQuestionStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		return PollsTestUtil.addQuestion(group.getGroupId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return PollsQuestionLocalServiceUtil.
				getPollsQuestionByUuidAndGroupId(uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return PollsQuestion.class;
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		super.validateImportedStagedModel(stagedModel, importedStagedModel);

		PollsQuestion question = (PollsQuestion)stagedModel;
		PollsQuestion importedQuestion = (PollsQuestion)importedStagedModel;

		Assert.assertEquals(question.getTitle(), importedQuestion.getTitle());
		Assert.assertEquals(
			question.getDescription(), importedQuestion.getDescription());
		Assert.assertEquals(
			question.getExpirationDate(), importedQuestion.getExpirationDate());
		Assert.assertEquals(
			question.getLastVoteDate(), importedQuestion.getLastVoteDate());
	}

}