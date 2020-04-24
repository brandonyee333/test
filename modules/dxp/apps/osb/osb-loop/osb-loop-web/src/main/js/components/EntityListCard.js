import Component, {Config} from 'metal-jsx';
import {List} from 'immutable';

import Card from './card';
import EntityDisplay from './EntityDisplay';
import ModalLink from './ModalLink';
import Icon from './Icon';
import LoadingWrapper from './LoadingWrapper';

class EntityListCard extends Component {
	render() {
		const {
			entitiesIList,
			loading,
			onSettingsClick,
			seeMoreLink,
			seeMoreMessage,
			seeMoreModalConfig,
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

					{onSettingsClick &&
						<Icon
							elementClasses="settings"
							name="gear"
							onClick={onSettingsClick}
						/>
					}
				</Card.Header>

				<LoadingWrapper loading={loading && entitiesIList.size === 0}>
					<ul>
						{
							entitiesIList.toJS().map(
								entity => (
									<li key={entity.entityClassPK}>
										<EntityDisplay entity={entity} size="smallest" summary={true} />
									</li>
								)
							)
						}
					</ul>
				</LoadingWrapper>

				{seeMoreMessage &&
					<Card.Footer>
						{seeMoreLink &&
							<a href={seeMoreLink}>
								{seeMoreMessage}
							</a>
						}

						{seeMoreModalConfig &&
							<ModalLink config={seeMoreModalConfig}>
								{seeMoreMessage}
							</ModalLink>
						}
					</Card.Footer>
				}
			</Card>
		);
	}
}

EntityListCard.PROPS = {
	entitiesIList: Config.instanceOf(List).value(List()),
	loading: Config.bool().value(true),
	onSettingsClick: Config.func(),
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

export default EntityListCard;