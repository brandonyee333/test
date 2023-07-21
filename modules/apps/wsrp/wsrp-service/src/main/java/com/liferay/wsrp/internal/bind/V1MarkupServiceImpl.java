/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.bind;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.wsrp.internal.proxy.TypeConvertorUtil;

import java.rmi.RemoteException;

import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Markup_PortType;
import oasis.names.tc.wsrp.v1.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v1.types.Extension;
import oasis.names.tc.wsrp.v1.types.GetMarkup;
import oasis.names.tc.wsrp.v1.types.InitCookie;
import oasis.names.tc.wsrp.v1.types.MarkupResponse;
import oasis.names.tc.wsrp.v1.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v1.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;

/**
 * @author Michael Young
 */
public class V1MarkupServiceImpl
	extends BaseServiceImpl implements WSRP_v1_Markup_PortType {

	@Override
	public MarkupResponse getMarkup(GetMarkup v1GetMarkup)
		throws RemoteException {

		try {
			return doGetMarkup(v1GetMarkup);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public Extension[] initCookie(InitCookie v1InitCookie)
		throws RemoteException {

		try {
			return doInitCookie(v1InitCookie);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public BlockingInteractionResponse performBlockingInteraction(
			PerformBlockingInteraction v1PerformBlockingInteraction)
		throws RemoteException {

		try {
			return doPerformBlockingInteraction(v1PerformBlockingInteraction);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public Extension[] releaseSessions(ReleaseSessions v1ReleaseSessions)
		throws RemoteException {

		try {
			return doReleaseSessions(v1ReleaseSessions);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	protected MarkupResponse doGetMarkup(GetMarkup v1GetMarkup)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.GetMarkup v2GetMarkup =
			(oasis.names.tc.wsrp.v2.types.GetMarkup)TypeConvertorUtil.convert(
				v1GetMarkup, 1);

		oasis.names.tc.wsrp.v2.types.MarkupResponse v2MarkupResponse =
			_v2MarkupService.getMarkup(v2GetMarkup);

		return (MarkupResponse)TypeConvertorUtil.convert(v2MarkupResponse, 2);
	}

	protected Extension[] doInitCookie(InitCookie v1InitCookie)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.InitCookie v2InitCookie =
			(oasis.names.tc.wsrp.v2.types.InitCookie)TypeConvertorUtil.convert(
				v1InitCookie, 1);

		oasis.names.tc.wsrp.v2.types.Extension[] v2Extensions =
			_v2MarkupService.initCookie(v2InitCookie);

		return (Extension[])TypeConvertorUtil.convert(v2Extensions, 2);
	}

	protected BlockingInteractionResponse doPerformBlockingInteraction(
			PerformBlockingInteraction v1PerformBlockingInteraction)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction
			v2PerformBlockingInteraction =
				(oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction)
					TypeConvertorUtil.convert(v1PerformBlockingInteraction, 1);

		oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse
			v2BlockingInteractionResponse =
				_v2MarkupService.performBlockingInteraction(
					v2PerformBlockingInteraction);

		return (BlockingInteractionResponse)TypeConvertorUtil.convert(
			v2BlockingInteractionResponse, 2);
	}

	protected Extension[] doReleaseSessions(ReleaseSessions v1ReleaseSessions)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.ReleaseSessions v2ReleaseSessions =
			(oasis.names.tc.wsrp.v2.types.ReleaseSessions)
				TypeConvertorUtil.convert(v1ReleaseSessions, 1);

		oasis.names.tc.wsrp.v2.types.Extension[] v2Extensions =
			_v2MarkupService.releaseSessions(v2ReleaseSessions);

		return (Extension[])TypeConvertorUtil.convert(v2Extensions, 2);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		V1MarkupServiceImpl.class);

	private static final WSRP_v2_Markup_PortType _v2MarkupService =
		new V2MarkupServiceImpl();

}