import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {noop} from 'lodash';

class Item extends Component {
	render() {
		const {
			active,
			children,
			displayOnly,
			href,
			justifyBetween,
			onClick
		} = this.props;

		const classes = getCN(
			'menu-item-container',
			{
				active,
				'display-only': displayOnly,
				'justify-between': justifyBetween,
				'menu-link': href
			}
		);

		let content = children;

		if (href) {
			content = <a href={href}>{content}</a>;
		}

		return (
			<li
				{...this.otherProps()}
				class={classes}
				onClick={onClick}
			>
				{content}
			</li>
		);
	}
}

Item.PROPS = {
	active: Config.bool(),
	displayOnly: Config.bool(),
	href: Config.string(),
	justifyBetween: Config.bool().value(false),
	onClick: Config.func().value(noop)
};

export default Item;