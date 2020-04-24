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

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.BaseAlloyControllerImpl;
import com.liferay.alloy.mvc.MockAlloyControllerImpl;
import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopStatsEntryLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopStatsEntryScoreDecayMessageListener
	extends BaseMessageListener {

	public static LoopStatsEntryScoreDecayMessageListener getInstance(
		AlloyController alloyController) {

		_instance.setAlloyController(
			new MockAlloyControllerImpl(
				(BaseAlloyControllerImpl)alloyController));

		return _instance;
	}

	public void setAlloyController(AlloyController alloyController) {
		_alloyController = alloyController;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		AlloyServiceInvoker loopStatsEntryAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopStatsEntry.class.getName());

		DynamicQuery loopStatsEntryDynamicQuery =
			loopStatsEntryAlloyServiceInvoker.buildDynamicQuery();

		Property modifiedTimeProperty = PropertyFactoryUtil.forName(
			"modifiedTime");

		long time =
			System.currentTimeMillis() -
				(LoopWebConfigurationValues.LOOP_STATS_ENTRY_SCORE_DECAY_TIME *
					Time.MINUTE);

		loopStatsEntryDynamicQuery.add(modifiedTimeProperty.lt(time));

		List<LoopStatsEntry> loopStatsEntries =
			loopStatsEntryAlloyServiceInvoker.executeDynamicQuery(
				loopStatsEntryDynamicQuery);

		for (LoopStatsEntry loopStatsEntry : loopStatsEntries) {
			double score =
				loopStatsEntry.getScore() *
					LoopWebConfigurationValues.
						LOOP_STATS_ENTRY_SCORE_DECAY_MULTIPLIER;

			if (score < LoopWebConfigurationValues.
					LOOP_STATS_ENTRY_SCORE_DECAY_THRESHOLD) {

				LoopStatsEntryLocalServiceUtil.deleteLoopStatsEntry(
					loopStatsEntry);
			}
			else {
				_alloyController.updateModelIgnoreRequest(
					loopStatsEntry, "modifiedTime", System.currentTimeMillis(),
					"score", score);
			}

			if (loopStatsEntry.getClassNameId() == PortalUtil.getClassNameId(
					LoopTopic.class)) {

				LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
					loopStatsEntry.getClassPK());

				_alloyController.indexModel(loopTopic);
			}
		}
	}

	private static final LoopStatsEntryScoreDecayMessageListener _instance =
		new LoopStatsEntryScoreDecayMessageListener();

	private AlloyController _alloyController;

}