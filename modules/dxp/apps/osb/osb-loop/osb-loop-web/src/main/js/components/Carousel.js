import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import Transition from 'metal-css-transitions';
import {bindAll, times} from 'lodash';

import Card from './card';
import Icon from './Icon';

class Item extends Component {
	render() {
		return <div class="item">{this.props.children}</div>;
	}
}

class PaginationItem extends Component {
	created() {
		this.handleItemClick_ = this.handleItemClick_.bind(this);
	}

	handleItemClick_() {
		this.props.onClick(this.props.index);
	}

	render() {
		return (
			<li
				class={
					getCN(
						'page',
						{active: this.props.active}
					)
				}
				onClick={this.handleItemClick_}
			/>
		);
	}
}

PaginationItem.PROPS = {
	active: Config.bool(),
	index: Config.number(),
	onClick: Config.func()
};

class Carousel extends Component {
	created() {
		bindAll(
			this,
			'handleDecrementPage_',
			'handleIncrementPage_',
			'handleSetPage_'
		);
	}

	handleDecrementPage_(event) {
		event.preventDefault();

		event.stopImmediatePropagation();

		const {currentPage_} = this.state;

		this.setState(
			{
				currentPage_: currentPage_ - 1,
				previousPage_: currentPage_
			}
		);
	}

	handleIncrementPage_(event) {
		event.preventDefault();

		event.stopImmediatePropagation();

		const {currentPage_} = this.state;

		this.setState(
			{
				currentPage_: currentPage_ + 1,
				previousPage_: currentPage_
			}
		);
	}

	handleSetPage_(page) {
		this.setState(
			{
				currentPage_: page,
				previousPage_: this.state.currentPage_
			}
		);
	}

	render() {
		const {handleDecrementPage_, handleIncrementPage_, handleSetPage_} = this;

		const {arrowTitles, children, href, title} = this.props;

		const {currentPage_, previousPage_} = this.state;

		const numOfPages = children.length;

		const showLeftArrow = currentPage_ > 0;
		const showRightArrow = (currentPage_ + 1) < numOfPages;

		return (
			<div class="carousel-container">
				<Card>
					<div class="header">
						{title}

						{href &&
							<a class="see-all" href={href}>
								{Liferay.Language.get('see-all')}
							</a>
						}
					</div>

					<Transition elementClasses="slider" name={currentPage_ > previousPage_ ? 'transition-slide-left' : 'transition-slide-right'}>
						{children[currentPage_]}
					</Transition>

					{numOfPages > 1 &&
						<div>
							<a
								class={
									getCN(
										'arrow-nav',
										{empty: !showLeftArrow}
									)
								}
								data-tooltip
								href={href}
								onClick={showLeftArrow ? handleDecrementPage_ : null}
								title={showLeftArrow ? arrowTitles.previous : ''}
							>
								<Icon name="arrow-left-rod" />
							</a>

							<a
								class={
									getCN(
										'arrow-nav',
										{empty: !showRightArrow}
									)
								}
								data-tooltip
								href={href}
								onClick={showRightArrow ? handleIncrementPage_ : null}
								title={showRightArrow ? arrowTitles.next : ''}
							>
								<Icon name="arrow-right-rod" />
							</a>
						</div>
					}
				</Card>

				{numOfPages > 1 &&
					<ul class="page-index text-center">
						{
							times(
								numOfPages,
								index => (
									<PaginationItem
										active={currentPage_ === index}
										index={index}
										key={index}
										onClick={handleSetPage_}
									/>
								)
							)
						}
					</ul>
				}
			</div>
		);
	}
}

Carousel.PROPS = {
	arrowTitles: {
		validator: Config.shapeOf(
			{
				next: Config.string(),
				previous: Config.string()
			}
		),
		value: {
			next: Liferay.Language.get('next'),
			previous: Liferay.Language.get('previous')
		}
	},
	href: Config.string(),
	maxItems: Config.number(),
	title: Config.oneOfType([Config.array(), Config.string()])
};

Carousel.STATE = {
	currentPage_: Config.value(0),
	previousPage_: Config.value(0)
};

Carousel.Item = Item;

export default Carousel;