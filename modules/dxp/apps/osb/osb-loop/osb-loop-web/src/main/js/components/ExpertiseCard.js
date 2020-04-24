import Component, {Config} from 'metal-jsx';

import Card from './card';
import EntityPillList from './EntityPillList';
import IconLabel from './IconLabel';
import LoadingWrapper from './LoadingWrapper';
import sendRequest from '../lib/request';

export class ExpertiseCard extends Component {
	render() {
		const {
			editURL,
			loading,
			seeMoreURL,
			topics
		} = this.props;

		const topicsLength = topics.length;

		return (
			<Card elementClasses="expertise-card-container">
				<Card.Header>
					<a href={seeMoreURL}>
						<IconLabel
							label={Liferay.Language.get('expertise')}
							name="hash"
							size="small"
							spacing="medium"
						/>
					</a>
				</Card.Header>

				<LoadingWrapper elementClasses="content" loading={loading}>
					{!!topicsLength &&
						<EntityPillList entities={topics} />
					}

					{!topicsLength &&
						<span>
							{Liferay.Language.get('none')}

							{editURL &&
								<a class="edit" href={editURL}>
									{Liferay.Language.get('edit')}
								</a>
							}
						</span>
					}
				</LoadingWrapper>
			</Card>
		);
	}
}

ExpertiseCard.PROPS = {
	editURL: Config.string(),
	id: Config.number(),
	loading: Config.bool(),
	seeMoreURL: Config.string(),
	topics: Config.array()
};

class ExpertiseCardContainer extends Component {
	created() {
		this._request = sendRequest(
			{
				controller: 'people',
				controllerMethod: 'viewVerifiedLoopTopics.json',
				data: {
					id: this.props.id
				},
				method: 'GET'
			}
		).then(
			({data}) => {
				this.setState(
					{
						loading_: false,
						topics_: data
					}
				);
			}
		);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		const {loading_, topics_} = this.state;

		return (
			<ExpertiseCard {...this.otherProps()} loading={loading_} topics={topics_} />
		);
	}
}

ExpertiseCardContainer.PROPS = {
	id: Config.number()
};

ExpertiseCardContainer.STATE = {
	loading_: Config.value(true),
	topics_: Config.value([])
};

export default ExpertiseCardContainer;