import Component, {Config} from 'metal-jsx';
import {isArray, isPlainObject, keys} from 'lodash';

import Card from './card';
import IconListItem from './IconListItem';
import LoadingWrapper from './LoadingWrapper';

class ListCard extends Component {
	renderItems_(items) {
		return [
			<ul class="category-items" key="categoryItems">
				{
					items.map(
						(item, i) => {
							let retVal = item.content;

							if (item.icon) {
								retVal = (
									<IconListItem
										copyData={item.copyData}
										elementClasses="item"
										icon={item.icon}
										key={i}
										label={item.content}
										url={item.url}
									/>
								);
							}
							else if (!item.icon && item.url) {
								retVal = <a href={item.url}>{item.content}</a>;
							}

							return (
								<li class="item" key={i}>
									{retVal}
								</li>
							);
						}
					)
				}
			</ul>
		];
	}

	setUpCategories_(items) {
		const retObj = {};

		items.forEach(
			item => {
				const {label} = item;

				if (label) {
					const existingItems = retObj[label] ? retObj[label] : [];

					retObj[label] = [
						...existingItems,
						item
					];
				}
			}
		);

		return keys(retObj).length ? retObj : items;
	}

	render() {
		const {
			items,
			loading,
			title
		} = this.props;

		const categoryObj = this.setUpCategories_(items);

		return (
			<Card {...this.otherProps()} elementClasses="list-card-container">
				<Card.Header>
					{title}
				</Card.Header>

				<Card.Body>
					<LoadingWrapper loading={loading}>
						{isArray(categoryObj) &&
							this.renderItems_(categoryObj)
						}

						{isPlainObject(categoryObj) &&
							keys(categoryObj).map(
								key => {
									const value = categoryObj[key];

									return value && (
										<div class="category" key={key}>
											<div class="category-label">
												{key}
											</div>

											{this.renderItems_(value)}
										</div>
									);
								}
							)
						}
					</LoadingWrapper>
				</Card.Body>
			</Card>
		);
	}
}

ListCard.PROPS = {
	items: Config.array().value([]),
	loading: Config.bool().value(false),
	title: Config.string()
};

export default ListCard;