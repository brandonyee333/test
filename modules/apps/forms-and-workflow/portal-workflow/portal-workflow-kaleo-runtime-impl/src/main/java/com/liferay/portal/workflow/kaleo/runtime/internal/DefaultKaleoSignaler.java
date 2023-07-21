/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.KaleoSignaler;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.internal.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.util.ExecutionContextHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.SUPPORTS,
	rollbackFor = Exception.class
)
public class DefaultKaleoSignaler
	extends BaseKaleoBean implements KaleoSignaler {

	public void afterPropertiesSet() {
		_singleDestinationMessageSender =
			_singleDestinationMessageSenderFactory.
				createSingleDestinationMessageSender(_destinationName);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	@Override
	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		PathElement startPathElement = new PathElement(
			null, kaleoInstanceToken.getCurrentKaleoNode(), executionContext);

		_singleDestinationMessageSender.send(startPathElement);
	}

	@Override
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRED,
		rollbackFor = Exception.class
	)
	public void signalExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException {

		NodeExecutor nodeExecutor = _nodeExecutorFactory.getNodeExecutor(
			currentKaleoNode.getType());

		List<PathElement> remainingPathElements = new ArrayList<>();

		nodeExecutor.execute(
			currentKaleoNode, executionContext, remainingPathElements);

		_executionContextHelper.checkKaleoInstanceComplete(executionContext);

		for (PathElement remainingPathElement : remainingPathElements) {
			_singleDestinationMessageSender.send(remainingPathElement);
		}
	}

	@Override
	public void signalExit(
			String transitionName, ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		PathElement pathElement = new PathElement(
			currentKaleoNode, null, executionContext);

		_singleDestinationMessageSender.send(pathElement);
	}

	private String _destinationName;

	@ServiceReference(type = ExecutionContextHelper.class)
	private ExecutionContextHelper _executionContextHelper;

	@ServiceReference(type = NodeExecutorFactory.class)
	private NodeExecutorFactory _nodeExecutorFactory;

	private SingleDestinationMessageSender _singleDestinationMessageSender;

	@ServiceReference(type = SingleDestinationMessageSenderFactory.class)
	private SingleDestinationMessageSenderFactory
		_singleDestinationMessageSenderFactory;

}