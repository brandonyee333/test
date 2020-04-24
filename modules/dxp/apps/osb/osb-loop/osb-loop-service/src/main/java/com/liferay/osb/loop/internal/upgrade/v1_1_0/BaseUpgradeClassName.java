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

package com.liferay.osb.loop.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopDivisionRel;
import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.osb.loop.model.LoopStream;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.LoopTopicAssignment;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Calvin Keum
 */
public abstract class BaseUpgradeClassName extends UpgradeProcess {

	protected static String getStaleClassName(String className) {
		return StringUtil.replace(className, ".osb.", StringPool.PERIOD);
	}

	protected static final String[] CLASS_NAMES = {
		LoopAuditEntry.class.getName(), LoopDivision.class.getName(),
		LoopDivisionRel.class.getName(),
		LoopExternalReferenceRel.class.getName(), LoopJobTitle.class.getName(),
		LoopParticipantAssignment.class.getName(), LoopPerson.class.getName(),
		LoopPersonRel.class.getName(), LoopStatsEntry.class.getName(),
		LoopStream.class.getName(), LoopStreamEntry.class.getName(),
		LoopTopic.class.getName(), LoopTopicAssignment.class.getName(),
		LoopUserNotificationEvent.class.getName(),
		LoopUserNotificationRecord.class.getName(),
		LoopUserNotificationSubscription.class.getName()
	};

}