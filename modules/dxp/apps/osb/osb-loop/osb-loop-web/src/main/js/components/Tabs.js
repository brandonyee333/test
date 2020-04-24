import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {noop} from 'lodash';

export class Content extends Component {
	render() {
		const {children, hide, name} = this.props;

		return <div {...this.otherProps()} class={getCN('content', {hide})} key={name}>{children}</div>;
	}
}

Content.PROPS = {
	hide: Config.bool().value(false),
	name: Config.string()
};

class Tab extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		const {index, onIndexChange} = this.props;

		onIndexChange(index);
	}

	render() {
		const {
			count,
			currentIndex,
			href,
			index,
			name
		} = this.props;

		const classes = getCN(
			'tab',
			{
				active: index === currentIndex
			}
		);

		return (
			<li class={classes} role="presentation">
				<a
					href={href ? href : 'javascript:;'}
					onClick={href ? null : this.handleClick_}
					role="tab"
				>
					{count ? `${name} ${count}` : name}
				</a>
			</li>
		);
	}
}

Tab.PROPS = {
	count: Config.number(),
	currentIndex: Config.number(),
	href: Config.string(),
	index: Config.number(),
	name: Config.string(),
	onIndexChange: Config.func()
};

export class Tabs extends Component {
	render() {
		const {
			align,
			children,
			index,
			onIndexChange,
			renderAll
		} = this.props;

		const classes = getCN(
			'tabs-container',
			{
				[`align-${align}`]: align
			}
		);

		return (
			<div class={classes}>
				<ul class="tabs">
					{
						children.map(
							(child, i) => {
								const {count, href, name} = child.props;

								return (
									<Tab
										count={count}
										currentIndex={index}
										href={href}
										index={i}
										key={i}
										name={name}
										onIndexChange={onIndexChange}
									/>
								);
							}
						)
					}
				</ul>

				{!renderAll &&
					children[index]
				}

				{renderAll &&
					children.map(
						(child, i) => {
							if (index !== i) {
								child.props.hide = true;
							}

							return child;
						}
					)
				}
			</div>
		);
	}
}

Tabs.PROPS = {
	align: Config.oneOf(['left', 'top']).value('top'),
	index: Config.number().value(0),
	onIndexChange: Config.func().value(noop),
	renderAll: Config.bool().value(false)
};

Tabs.Content = Content;

export default Tabs;