import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import AutocompleteInput from './AutocompleteInput';
import Button from './Button';
import EntityDisplay from './EntityDisplay';
import LoadingWrapper from './LoadingWrapper';
import LoopConstants from '../lib/loop-constants';
import Modal from './modal';
import NoResultsDisplay from './NoResultsDisplay';
import sendRequest from '../lib/request';
import {addAlert, alertTypes} from '../actions/alerts';
import {addFeaturedTopic, fetchFeaturedTopics, removeFeaturedTopic} from '../actions/topics';
import {getRel} from '../lib/selectors';
import {lang} from '../lib/lang-util';

class FeaturedTopicsSettingsModal extends Component {
	created() {
		this.handleTopicSearch = this.handleTopicSearch.bind(this);
		this.handleAdd = this.handleAdd.bind(this);

		this._request = this.props.fetchFeaturedTopics().catch(noop);
	}

	disposed() {
		this._request.cancel();
	}

	handleAdd({entityClassPK}) {
		const {addAlert, addFeaturedTopic} = this.props;

		addFeaturedTopic(entityClassPK).then(
			({data}) => {
				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('x-was-set-as-featured'), [data.name])
					}
				);
			}
		).catch(
			({message}) => {
				addAlert(
					{
						alertType: alertTypes.ERROR,
						message
					}
				);
			}
		);
	}

	handleRemove(id) {
		const {addAlert, removeFeaturedTopic} = this.props;

		return () => removeFeaturedTopic(id).then(
			({data}) => {
				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: data
					}
				);
			}
		).catch(
			({data}) => {
				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: data
					}
				);
			}
		);
	}

	handleTopicSearch(query) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: query,
					searchLimit: 5,
					type: LoopConstants.types.topic
				}
			}
		).then(
			items => {
				const currentIds = this.props.topicsIList.toJS().map(topic => topic.entityClassPK);

				return items.filter(
					item => currentIds.indexOf(item.entityClassPK) === -1
				);
			}
		);
	}

	getRemoveButton(id) {
		return [
			<Button key="removeButton" onClick={this.handleRemove(id)} role="secondary">
				{Liferay.Language.get('remove')}
			</Button>
		];
	}

	render() {
		const {hideModal, loading, topicsIList} = this.props;

		return (
			<div class="featured-topics-settings-modal-container">
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('manage-featured-topics')}
				</Modal.Header>

				<Modal.Body scrollable={true}>
					<div>
						<AutocompleteInput
							dataSource={this.handleTopicSearch}
							focusOnAttached={true}
							icon="magnifier"
							onSelect={this.handleAdd}
							placeholder={Liferay.Language.get('search-topics')}
							row={true}
						/>
					</div>

					<LoadingWrapper loading={loading}>
						{
							topicsIList.toJS().map(
								(item, i) => (
									<EntityDisplay
										entity={item}
										inlineFollow={true}
										key={i}
										rightDisplay={this.getRemoveButton(item.entityClassPK)}
										summary={false}
									/>
								)
							)
						}

						{!topicsIList.size &&
							<NoResultsDisplay title={Liferay.Language.get('no-featured-topics')} />
						}
					</LoadingWrapper>
				</Modal.Body>
			</div>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	fetchFeaturedTopics: Config.func(),
	loading: Config.bool().value(true),
	topicsIList: Config.instanceOf(List)
};

FeaturedTopicsSettingsModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

export default connect(
	state => (
		{
			loading: state.getIn(['lists', 'featuredTopics', 'loading']),
			topicsIList: getRel('topics', state, state.getIn(['lists', 'featuredTopics', 'data'], OrderedSet()))
		}
	),
	{
		addAlert,
		addFeaturedTopic,
		fetchFeaturedTopics,
		removeFeaturedTopic
	}
)(FeaturedTopicsSettingsModal);