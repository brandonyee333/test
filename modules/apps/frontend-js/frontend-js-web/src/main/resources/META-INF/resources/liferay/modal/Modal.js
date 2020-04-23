/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayModal, {useModal} from '@clayui/modal';
import classNames from 'classnames';
import {render} from 'frontend-js-react-web';
import dom from 'metal-dom';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import './Modal.scss';

const openModal = props => {
	if (
		props &&
		props.url &&
		props.bodyHTML &&
		process.env.NODE_ENV === 'development'
	) {
		console.warn(
			'url and bodyHTML props are both set. bodyHTML will be ignored. Please use one or another.'
		);
	}

	// Mount in detached node; Clay will take care of appending to `document.body`.
	// See: https://github.com/liferay/clay/blob/master/packages/clay-shared/src/Portal.tsx
	render(Modal, props, document.createElement('div'));
};

const openPortletModal = ({
	iframeBodyCssClass,
	portletSelector,
	subTitle,
	title,
	url,
}) => {
	const portlet = document.querySelector(portletSelector);

	if (portlet && url) {
		const titleElement =
			portlet.querySelector('.portlet-title') ||
			portlet.querySelector('.portlet-title-default');

		if (titleElement) {
			if (portlet.querySelector('#cpPortletTitle')) {
				const titleTextElement = titleElement.querySelector(
					'.portlet-title-text'
				);

				if (titleTextElement) {
					title = `${titleTextElement.outerHTML} - ${title}`;
				}
			}
			else {
				title = `${titleElement.textContent} - ${title}`;
			}
		}

		let headerHTML;

		if (subTitle) {
			headerHTML = `${title}<div class="portlet-configuration-subtitle small"><span class="portlet-configuration-subtitle-text">${subTitle}</span></div>`;
		}

		openModal({
			headerHTML,
			iframeBodyCssClass,
			title,
			url,
		});
	}
};

/**
 * A utility with API that matches Liferay.Portlet.openWindow. The purpose of
 * this utility is backwards compatibility.
 * @deprecated As of Athanasius (7.3.x), replaced by Liferay.Portlet.openModal
 */
const openPortletWindow = ({bodyCssClass, portlet, uri, ...otherProps}) => {
	openPortletModal({
		iframeBodyCssClass: bodyCssClass,
		portletSelector: portlet,
		url: uri,
		...otherProps,
	});
};

const Modal = ({
	bodyHTML,
	buttons,
	headerHTML,
	id,
	iframeBodyCssClass,
	onClose,
	size,
	title,
	url,
}) => {
	const [visible, setVisible] = useState(true);

	const {observer} = useModal({
		onClose: () => processClose(),
	});

	const onButtonClick = ({formId, type}) => {
		if (type === 'cancel') {
			processClose();
		}
		else if (url && type === 'submit') {
			const iframe = document.querySelector('.liferay-modal iframe');

			if (iframe) {
				const iframeDocument = iframe.contentWindow.document;

				const forms = iframeDocument.querySelectorAll('form');

				if (
					forms.length !== 1 &&
					process.env.NODE_ENV === 'development'
				) {
					console.warn('There should be one form within a modal.');
				}

				if (formId) {
					const form = iframeDocument.getElementById(formId);

					if (form) {
						form.submit();
					}
				}
				else if (forms.length >= 1) {
					forms[0].submit();
				}
			}
		}
	};

	const processClose = () => {
		setVisible(false);

		if (onClose) {
			onClose();
		}
	};

	const Body = ({html}) => {
		const bodyRef = useRef();

		useEffect(() => {
			const fragment = document
				.createRange()
				.createContextualFragment(html);

			bodyRef.current.innerHTML = '';

			bodyRef.current.appendChild(fragment);
		}, [html]);

		return <div ref={bodyRef}></div>;
	};

	const Iframe = ({url}) => {
		const iframeRef = useRef();
		const loadingIndicatorRef = useRef();

		const iframeURL = new URL(url);

		const namespace = iframeURL.searchParams.get('p_p_id');

		let bodyCssClass = 'dialog-iframe-popup';

		if (iframeBodyCssClass) {
			bodyCssClass = `${bodyCssClass} ${iframeBodyCssClass}`;
		}

		iframeURL.searchParams.set(`_${namespace}_bodyCssClass`, bodyCssClass);

		const src = iframeURL.toString();

		let delegateHandler;

		const onLoadHandler = () => {
			delegateHandler = dom.delegate(
				iframeRef.current.contentWindow.document,
				'click',
				'.btn-cancel',
				() => processClose()
			);

			loadingIndicatorRef.current.classList.add('hide');

			iframeRef.current.classList.remove('hide');
		};

		useEffect(() => {
			return () => {
				if (delegateHandler) {
					delegateHandler.removeListener();
				}
			};
		});

		return (
			<>
				<ClayLoadingIndicator ref={loadingIndicatorRef} />
				<iframe
					className="hide"
					onLoad={onLoadHandler}
					ref={iframeRef}
					src={src}
					title={src}
				/>
			</>
		);
	};

	return (
		<>
			{visible && (
				<ClayModal
					className="liferay-modal"
					id={id}
					observer={observer}
					size={url && !size ? 'full-screen' : size}
				>
					<ClayModal.Header withTitle={false}>
						<div className="modal-title">
							{headerHTML ? (
								<div
									dangerouslySetInnerHTML={{
										__html: headerHTML,
									}}
								></div>
							) : (
								title
							)}
						</div>
						<ClayButton
							aria-label="close"
							className="close"
							displayType="unstyled"
							onClick={processClose}
						>
							<ClayIcon symbol="times" />
						</ClayButton>
					</ClayModal.Header>
					<div
						className={classNames('modal-body', {
							'modal-body-iframe': url,
						})}
					>
						{url && <Iframe url={url} />}
						{!url && bodyHTML && <Body html={bodyHTML} />}
					</div>
					{buttons && (
						<ClayModal.Footer
							last={
								<ClayButton.Group spaced>
									{buttons.map(
										(
											{
												displayType,
												formId,
												id,
												label,
												type,
											},
											index
										) => (
											<ClayButton
												displayType={displayType}
												id={id}
												key={index}
												onClick={() => {
													onButtonClick({
														formId,
														type,
													});
												}}
												type={
													type === 'cancel'
														? 'button'
														: type
												}
											>
												{label}
											</ClayButton>
										)
									)}
								</ClayButton.Group>
							}
						/>
					)}
				</ClayModal>
			)}
		</>
	);
};

Modal.propTypes = {
	bodyHTML: PropTypes.string,
	buttons: PropTypes.arrayOf(
		PropTypes.shape({
			displayType: PropTypes.oneOf([
				'link',
				'primary',
				'secondary',
				'unstyled',
			]),
			formId: PropTypes.string,
			id: PropTypes.string,
			label: PropTypes.string,
			type: PropTypes.oneOf(['cancel', 'submit']),
		})
	),
	headerHTML: PropTypes.string,
	id: PropTypes.string,
	onClose: PropTypes.func,
	size: PropTypes.oneOf(['full-screen', 'lg', 'sm']),
	title: PropTypes.string,
	url: PropTypes.string,
};

export {Modal, openModal, openPortletModal, openPortletWindow};
