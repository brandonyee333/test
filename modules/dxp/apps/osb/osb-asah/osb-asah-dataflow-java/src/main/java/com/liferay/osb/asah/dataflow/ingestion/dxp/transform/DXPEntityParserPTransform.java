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

package com.liferay.osb.asah.dataflow.ingestion.dxp.transform;

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntity;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;

/**
 * @author Marcos Martins
 * @author Rachael Koestartyo
 */
public class DXPEntityParserPTransform extends BaseParserPTransform<DXPEntity> {

	@Override
	protected DXPEntity doParse(DXPEntityPubsubMessage dxpEntityPubsubMessage)
		throws Exception {

		DXPEntity dxpEntity = ObjectMapperUtil.readValue(
			DXPEntity.class, dxpEntityPubsubMessage.getPayload());

		DXPEntityPubsubMessage.Attributes attributes =
			dxpEntityPubsubMessage.getAttributes();

		dxpEntity.classPK = dxpEntity.id;
		dxpEntity.dataSourceId = attributes.getDataSourceId();
		dxpEntity.projectId = attributes.getProjectId();
		dxpEntity.uploadDate = attributes.getUploadTime();
		dxpEntity.uploadType = attributes.getUploadType();

		return dxpEntity;
	}

}