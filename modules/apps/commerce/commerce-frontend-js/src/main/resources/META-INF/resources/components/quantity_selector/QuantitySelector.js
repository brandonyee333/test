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

import {ClaySelect} from '@clayui/form';
import ClayIcon, {ClayIconSpriteContext} from '@clayui/icon';
import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {createRef, useEffect, useState} from 'react';

import Datalist from '../datalist/Datalist';

function QuantitySelector(props) {
	const [selectedQuantity, setSelectedQuantity] = useState(props.quantity);

	const generateOptions = (allowed, max, min, multiple) => {
		const multi = multiple || 1;
		const quantitiesList = [];
		if (allowed !== [-1] && allowed !== undefined) {
			Array.from({length: allowed.length}).map((_, i) =>
				quantitiesList.push(i)
			);
		}
		else {
			for (let i = min; i <= max; i++) {
				quantitiesList.push(i * multi);
			}
		}

		return quantitiesList;
	};
	const quantitiesList = generateOptions(
		props.settings.allowedQuantity,
		props.settings.maxQuantity,
		props.settings.minQuantity,
		props.settings.multipleQuantity
	);

	const updateQuantity = (q) => {
		if (q !== null) {
			setSelectedQuantity(q);
			try {
				props.updateQuantity(q);
			}
			catch (e) {
				// eslint-disable-next-line no-console
				console.log('stand alone component');
			}
		}
	};

	let inputStyle;
	if (props.inputSize === 'block') {
		inputStyle = {
			display: 'block',
			width: '100%',
		};
	}
	else if (props.inputSize === 'limited') {
		inputStyle = {
			display: 'block',
			width: '176px',
		};
	}
	else {
		const n = parseInt(props.size, 10);
		const wid = n + 'px';
		inputStyle = {
			display: 'block',
			width: wid,
		};
	}

	useEffect(() => {
		// eslint-disable-next-line no-unused-expressions
		props.quantity
			? updateQuantity(props.quantity)
			: updateQuantity(selectedQuantity);
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [props, selectedQuantity, setSelectedQuantity, props.quantity]);

	const content = (
		<div
			className="input-group input-group-sm quantity-selector simple"
			style={inputStyle}
		>
			{(props.prependedIcon || props.prependedText) && (
				<div className="input-group-item input-group-item-shrink input-group-prepend">
					<span className="input-group-text">
						{props.prependedIcon ? (
							<ClayIcon
								spritemap={props.spritemap}
								symbol={props.prependedIcon}
							/>
						) : (
							props.prependedText
						)}
					</span>
				</div>
			)}
			<div
				className={classnames(
					'input-group-item input-group-item-shrink',
					(props.appendedIcon || props.appendedText) &&
						'input-group-prepend'
				)}
			>
				{props.style === 'datalist' && (
					<Datalist
						className="quantity-selector-input"
						disabled={props.disabled}
						inputSize={props.inputSize}
						size={props.size}
						updateQuantity={updateQuantity}
					>
						{quantitiesList.map((item) => (
							<option key={item} label={item} value={item} />
						))}
					</Datalist>
				)}

				{props.style === 'select' && (
					<ClaySelect
						aria-label="Select Label"
						classnames={classnames(
							'quantity-selector-input',
							props.size === 'small' && 'form-control-sm',
							props.size === 'large' && 'form-control-lg'
						)}
						disabled={props.disabled}
						id="quantitySelect"
						onChange={(e) => {
							updateQuantity(e.target.value);
						}}
					>
						{quantitiesList.map((item) => (
							<ClaySelect.Option
								key={item}
								label={item}
								value={item}
							/>
						))}
					</ClaySelect>
				)}
			</div>

			{(props.appendedIcon || props.appendedText) && (
				<div className="input-group-append input-group-item input-group-item-shrink">
					<span className="input-group-text">
						{props.appendedIcon ? (
							<ClayIcon
								spritemap={props.spritemap}
								symbol={props.appendedIcon}
							/>
						) : (
							props.appendedText
						)}
					</span>
				</div>
			)}
		</div>
	);

	return props.spritemap ? (
		<ClayIconSpriteContext.Provider value={props.spritemap}>
			{content}
		</ClayIconSpriteContext.Provider>
	) : (
		content
	);
}

QuantitySelector.defaultProps = {
	disabled: false,
	inputSize: '200',
	settings: {

		// allowedQuantity: [-1],

		maxQuantity: 99,
		minQuantity: 1,
		multipleQuantity: 1,
	},
	style: 'select',
};

QuantitySelector.propTypes = {
	appendedIcon: PropTypes.string,
	appendedText: PropTypes.string,
	disableAddToCartButton: PropTypes.bool,
	disableQuantitySelector: PropTypes.bool,
	disabled: PropTypes.bool,
	inputName: PropTypes.string,
	inputSize: PropTypes.string,
	prependedIcon: PropTypes.string,
	prependedText: PropTypes.string,
	quantity: PropTypes.number,
	rtl: PropTypes.bool,
	settings: PropTypes.shape({
		allowedQuantity: PropTypes.arrayOf(PropTypes.number),
		maxQuantity: PropTypes.number,
		minQuantity: PropTypes.number,
		multipleQuantity: PropTypes.number,
	}),
	size: PropTypes.oneOf(['large', 'medium', 'small']),
	spritemap: PropTypes.string.isRequired,
	style: PropTypes.oneOf(['select', 'datalist']),
	updateQuantity: PropTypes.func,
};

export default QuantitySelector;
