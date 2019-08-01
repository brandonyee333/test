import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

export default class Alert extends React.Component {
	static propTypes = {
		children: PropTypes.node.isRequired,
		onClose: PropTypes.func,
		type: PropTypes.oneOf(['danger', 'info', 'warning']).isRequired
	};

	displayAlertIndicator = type => {
		let retVal = null;

		if (type === 'danger') {
			retVal = (
				<svg
					className='lexicon-icon lexicon-icon-exclamation-full'
					role='img'
				>
					<use xlinkHref='#exclamation-full' />
				</svg>
			);
		} else if (type === 'info') {
			retVal = (
				<svg
					className='lexicon-icon lexicon-icon-info-circle'
					role='img'
				>
					<use xlinkHref='#info-circle' />
				</svg>
			);
		} else if (type === 'warning') {
			retVal = (
				<svg
					className='lexicon-icon lexicon-icon-warning-full'
					role='img'
				>
					<use xlinkHref='#warning-full' />
				</svg>
			);
		}

		return retVal;
	};

	render() {
		const {children, onClose, type} = this.props;

		const className = getCN('alert', {
			[`alert-${type}`]: type,
			'alert-dismissible': onClose
		});

		return (
			<div className={className} role='alert'>
				<span className='alert-indicator'>
					{this.displayAlertIndicator(type)}
				</span>

				{children}

				{onClose && (
					<button
						aria-label='Close'
						className='close'
						onClick={onClose}
						type='button'
					>
						<svg className='lexicon-icon lexicon-icon-times'>
							<use xlinkHref='#times' />
						</svg>
					</button>
				)}
			</div>
		);
	}
}
