import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

export default class Button extends React.Component {
	static defaultProps = {
		display: 'primary',
		size: 'md',
		type: 'button'
	};

	static propTypes = {
		children: PropTypes.node.isRequired,
		display: PropTypes.oneOf(['default', 'disabled', 'link', 'outline', 'primary']),
		href: PropTypes.string,
		onClick: PropTypes.oneOfType([PropTypes.func, PropTypes.string]),
		size: PropTypes.oneOf(['lg', 'md', 'sm']),
		type: PropTypes.oneOf(['button', 'reset', 'submit']),
		value: PropTypes.string
	};

	render() {
		const {children, display, href, onClick, size, type, value} = this.props;

		const className = getCN(
			'btn',
			`btn-${size}`,
			`${!href ? `btn-${display}` : ''}`,
			{
				'btn-link': href
			}
		);

		return href ? (
			<a className={className} href={href} onClick={onClick}>
				{children}
			</a>
		) : (
			<button className={className} onClick={onClick} type={type} value={value}>
				{children}
			</button>
		);
	}
}