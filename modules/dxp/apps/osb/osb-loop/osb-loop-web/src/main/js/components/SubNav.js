import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Card from './card';

class Item extends Component {
	render() {
		const {children, href, selected} = this.props;

		const classes = getCN(
			'item',
			{selected}
		);

		return (
			<li class={classes} role="presentation">
				<a href={href} role="tab">
					{children}
				</a>
			</li>
		);
	}
}

Item.PROPS = {
	href: Config.string(),
	selected: Config.bool()
};

export class SubNav extends Component {
	render() {
		const {align, bottomContent, children} = this.props;

		const classes = getCN(
			'sub-nav-container',
			{
				[`align-${align}`]: align
			}
		);

		return (
			<Card elementClasses={classes}>
				<ul class="sub-nav">
					{children}
				</ul>

				{bottomContent &&
					<div class="bottom-content">
						{bottomContent}
					</div>
				}
			</Card>
		);
	}
}

SubNav.PROPS = {
	align: Config.oneOf(['left', 'top']).value('top'),
	bottomContent: Config.array()
};

SubNav.Item = Item;

export default SubNav;