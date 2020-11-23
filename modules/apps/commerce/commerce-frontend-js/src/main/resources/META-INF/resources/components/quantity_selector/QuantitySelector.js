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
import React, {useEffect, useState} from 'react';

import Datalist from '../datalist/Datalist';
import {generateOptions, getInputStyle} from './utils/index';

function QuantitySelector(props) {
	const [selectedQuantity, setSelectedQuantity] = useState(props.quantity);
	const quantitiesList = generateOptions(props.settings);
	const onUpdate = (quantity) => {
		const qty = parseInt(quantity, 10);
		if (qty) {
			setSelectedQuantity(qty);
			try {
				props.updateQuantity('select', qty);
			}
			catch (event) {
				// eslint-disable-next-line no-console
				console.log('stand alone component');
			}
		}
	};
	const inputStyle = getInputStyle(props.inputSize);
	const content = (
		<div
			className="input-group input-group-sm quantity-selector simple"
			data-testid="quantity-selector"
			style={inputStyle}
		>
			{(props.prependedIcon || props.prependedText) && (
				<div className="input-group-item input-group-item-shrink input-group-prepend">
					<span className="input-group-text">
						{props.prependedIcon ? (
							<ClayIcon symbol={props.prependedIcon} />
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
						data-testid="select-option"
						disabled={props.disabled}
						id="quantitySelect"
						size={props.size}
						updateQuantity={onUpdate}
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
						data-testid="select"
						disabled={props.disabled}
						id="quantitySelect"
						onChange={(e) => {
							onUpdate(e.target.value);
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
							<ClayIcon symbol={props.appendedIcon} />
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
	inputSize: 'default',
	settings: {
		allowedQuantity: [],
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
	}).isRequired,
	size: PropTypes.oneOf(['large', 'medium', 'small']),
	spritemap: PropTypes.string.isRequired,
	style: PropTypes.oneOf(['select', 'datalist']),
	updateQuantity: PropTypes.func,
};

export default QuantitySelector;
