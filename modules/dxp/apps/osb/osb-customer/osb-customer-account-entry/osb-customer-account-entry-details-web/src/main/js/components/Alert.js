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
		switch(type) {
		case 'danger':
			return (
				<svg className="lexicon-icon lexicon-icon-exclamation-full">
					<use xlinkHref="#exclamation-full" />
				</svg>
			);
		case 'info':
			return (
				<svg className="lexicon-icon lexicon-icon-info-circle">
					<use xlinkHref="#info-circle" />
				</svg>
			);
		case 'warning':
			return (
				<svg className="lexicon-icon lexicon-icon-warning-full">
					<use xlinkHref="#warning-full" />
				</svg>
			);
		default:
			return;
		}
	};

	render() {
		const {children, onClose, type} = this.props;

		const className = getCN(
			'alert',
			{
				[`alert-${type}`]: type,
				'alert-dismissible': onClose
			}
		);

		return (
			<div className={className} role="alert">
				<span className="alert-indicator">
					{this.displayAlertIndicator(type)}
				</span>

				{children}

				{onClose && (
					<button
						aria-label="Close"
						className="close"
						onClick={onClose}
						type="button"
					>
						<svg className="lexicon-icon lexicon-icon-times">
							<use xlinkHref="#times" />
						</svg>
					</button>
				)}
			</div>
		);
	}
}