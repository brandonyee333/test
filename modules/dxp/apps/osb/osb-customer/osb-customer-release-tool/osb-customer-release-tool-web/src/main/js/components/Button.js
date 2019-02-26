import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

export default class Button extends React.Component {
	static defaultProps = {
		disabled: false,
		display: 'primary',
		icon: false,
		size: 'md',
		type: 'button'
	};

	static propTypes = {
		children: PropTypes.node.isRequired,
		disabled: PropTypes.bool,
		display: PropTypes.oneOf(['default', 'link', 'outline', 'primary']),
		href: PropTypes.string,
		icon: PropTypes.bool,
		onClick: PropTypes.oneOfType([PropTypes.func, PropTypes.string]),
		size: PropTypes.oneOf(['lg', 'md', 'sm']),
		type: PropTypes.oneOf(['button', 'reset', 'submit']),
		value: PropTypes.string
	};

	render() {
		const {
			children,
			disabled,
			display,
			href,
			icon,
			onClick,
			size,
			type,
			value
		} = this.props;

		const className = getCN(
			'btn',
			`btn-${size}`,
			`${!href ? `btn-${display}` : ''}`,
			{
				'btn-link': href,
				'btn-monospaced': icon
			}
		);

		return href ? (
			<a className={className} href={href} onClick={onClick} role="link">
				{children}
			</a>
		) : (
			<button className={className} disabled={disabled} onClick={onClick} type={type} value={value}>
				{children}
			</button>
		);
	}
}