import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, isArray} from 'lodash';

import Avatar from './Avatar';
import Card from './card';
import LoadingWrapper from './LoadingWrapper';

class EntityCategoryCard extends Component {
	created() {
		bindAll(
			this,
			'renderColumnEntity_',
			'renderRowEntity_'
		);
	}

	renderItems_(items, column) {
		let renderMethod = this.renderRowEntity_;

		if (column) {
			renderMethod = this.renderColumnEntity_;
		}

		let retVal;

		if (isArray(items)) {
			retVal = items.map(renderMethod);
		}
		else {
			retVal = renderMethod(items);
		}

		return retVal;
	}

	renderColumnEntity_(item) {
		let avatarSize = 'smallest';

		if (this.props.displaySize === 'medium') {
			avatarSize = 'small';
		}

		return [
			<div class="entity" key={item.entityClassPK}>
				<Avatar entity={item} size={avatarSize} summary={true} />

				<a class="name" href={item.displayURL}>{item.name}</a>
			</div>
		];
	}

	renderRowEntity_(item) {
		let avatarSize = 'smallest';

		if (this.props.displaySize === 'medium') {
			avatarSize = 'small';
		}

		return [<Avatar entity={item} key={item.entityClassPK} size={avatarSize} summary={true} />];
	}

	render() {
		const {
			categories,
			displayDirection,
			displaySize,
			entityCount,
			loading,
			seeMoreLinkData,
			title
		} = this.props;

		const {link, message} = seeMoreLinkData;

		const column = displayDirection === 'column';

		return (
			<Card {...this.otherProps()} elementClasses={getCN('entity-category-card-container', displaySize)}>
				<Card.Header>
					{title}

					{!!entityCount &&
						<div class="entity-count">{entityCount}</div>
					}
				</Card.Header>

				<LoadingWrapper loading={loading} mask={true}>
					{
						categories.map(
							(category, index) => {
								const {
									items,
									label,
									lineBottom,
									lineTop
								} = category;

								const categoryClassNames = getCN(
									'category',
									{
										'line-bottom': lineBottom,
										'line-top': lineTop,
										reverse: !label && displaySize === 'medium'
									}
								);

								const contentClassNames = getCN(
									'category-content',
									{
										'category-column': column,
										'category-row': displayDirection === 'row'
									}
								);

								return (
									<div class={categoryClassNames} key={`category${index}`}>
										{label &&
											<div class="category-label">{label}</div>
										}

										<div class={contentClassNames}>
											{this.renderItems_(items, column)}
										</div>
									</div>
								);
							}
						)
					}
				</LoadingWrapper>

				{message && link &&
					<Card.Footer>
						<a href={link}>{message}</a>
					</Card.Footer>
				}
			</Card>
		);
	}
}

EntityCategoryCard.PROPS = {
	categories: Config.array(),
	displayDirection: Config.oneOf(['column', 'row']).value('row'),
	displaySize: Config.oneOf(['medium', 'small']).value('small'),
	entityCount: Config.number(),
	loading: Config.bool().value(true),
	seeMoreLinkData: Config.shapeOf(
		{
			link: Config.string(),
			message: Config.string()
		}
	),
	title: Config.string()
};

export default EntityCategoryCard;