import Component, {Config} from 'metal-jsx';
import {List} from 'immutable';

import Card from './card';
import LoadingWrapper from './LoadingWrapper';
import PageListItem from './PageListItem';

class PageListCard extends Component {
	render() {
		const {
			loading,
			pagesIList,
			seeMoreLink,
			seeMoreMessage,
			title,
			total
		} = this.props;

		return (
			<Card {...this.otherProps()} elementClasses="entity-list-card-container">
				<Card.Header>
					{title}

					{!!total &&
						<span>{total}</span>
					}
				</Card.Header>

				<LoadingWrapper loading={loading && pagesIList.size === 0}>
					<ul>
						{
							pagesIList.toJS().map(
								entity => <PageListItem entity={entity} key={entity.entityClassPK} />
							)
						}
					</ul>
				</LoadingWrapper>

				{seeMoreMessage && seeMoreLink &&
					<Card.Footer>
						<a href={seeMoreLink}>
							{seeMoreMessage}
						</a>
					</Card.Footer>
				}
			</Card>
		);
	}
}

PageListCard.PROPS = {
	loading: Config.bool().value(true),
	pagesIList: Config.instanceOf(List).value(List()),
	seeMoreLink: Config.string(),
	seeMoreMessage: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	title: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	total: Config.number()
};

export default PageListCard;