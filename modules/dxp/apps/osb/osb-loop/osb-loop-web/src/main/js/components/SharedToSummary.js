import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {connect} from 'metal-redux';
import {is, List} from 'immutable';

import {
	at,
	compact,
	flow,
	groupBy,
	map,
	reject
} from 'lodash/fp.js';

import EntityLink from './EntityLink';
import LoopConstants from '../lib/loop-constants';
import {classNameIdToSchema} from '../lib/util';
import {getItemsList, lang} from '../lib/lang-util';
import {getSharedTo} from '../lib/selectors';
import {modalTypes, showModal} from '../actions/modals';

const {classNameIds} = LoopConstants;

const groupEntities = flow(
	reject(
		item => item.type === LoopConstants.types.removed
	),
	groupBy(
		item => classNameIdToSchema(item.entityClassNameId)
	),
	at(
		['people', 'divisions', 'topics']
	),
	compact
);

const itemTypeMap = {
	[classNameIds.divisions]: {
		className: 'groups',
		langKey: Liferay.Language.get('x-groups')
	},
	[classNameIds.people]: {
		className: 'people',
		langKey: Liferay.Language.get('x-people')
	},
	[classNameIds.topics]: {
		className: 'topics',
		langKey: Liferay.Language.get('x-topics')
	}
};

class SharedToGroup extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		const {entity, onClick} = this.props;

		onClick(entity.entityClassNameId);
	}

	render() {
		return <EntityLink entity={this.props.entity} onClick={this.handleClick_} showTrigger={false} />;
	}
}

SharedToGroup.PROPS = {
	entity: Config.object(),
	onClick: Config.func()
};

class SharedToSummary extends Component {
	created() {
		this.handleShowModal_ = this.handleShowModal_.bind(this);
	}

	handleShowModal_(classNameId) {
		this.props.showModal(
			{
				modalProps: {
					activeClassNameId: classNameId,
					entities: this.processEntities_()
				},
				modalType: modalTypes.SHARED_TO
			}
		);
	}

	processEntities_() {
		return groupEntities(this.props.entitiesIList.toJS());
	}

	renderCreatorLink_() {
		return <EntityLink entity={this.props.creator} />;
	}

	renderSharedToSummary_(entities) {
		return flow(
			map(
				item => this.renderSummaryLink_(this.handleShowModal_, item)
			),
			items => {
				let summary = null;

				if (items && items.length) {
					const sharedToVal = getItemsList(items);

					summary = lang(
						this.props.verbiage,
						[
							this.renderCreatorLink_(),
							sharedToVal
						]
					);
				}

				return summary;
			}
		)(entities);
	}

	renderSummaryLink_(onClick, data) {
		let retVal = null;

		const arrayLength = data.length;

		if (arrayLength) {
			const firstItem = data[0];

			if (arrayLength > 1) {
				const {entityClassNameId} = firstItem;

				const {className, langKey} = itemTypeMap[entityClassNameId];

				const linkClassName = `shared-to-list ${className}`;

				retVal = (
					<SharedToGroup
						elementClasses={linkClassName}
						entity={{
							displayURL: 'javascript:;',
							entityClassNameId,
							name: lang(langKey, [arrayLength], true)
						}}
						key={entityClassNameId}
						onClick={onClick}
					/>
				);
			}
			else {
				retVal = <EntityLink entity={firstItem} summary={true} />;
			}
		}

		return retVal;
	}

	shouldUpdate(nextState, nextProps) {
		return !is(this.props.entitiesIList, nextProps.entitiesIList);
	}

	render() {
		const {nowrap} = this.props;

		const entities = this.processEntities_();

		const empty = !compact(entities).length;

		return (
			<span class={getCN('shared-to-summary-container', {nowrap})}>
				{empty &&
					this.renderCreatorLink_()
				}

				{!empty &&
					<span class="summary">
						{this.renderSharedToSummary_(entities)}
					</span>
				}
			</span>
		);
	}
}

const STORE = {
	entitiesIList: Config.instanceOf(List),
	showModal: Config.func()
};

SharedToSummary.PROPS = {
	...STORE,
	creator: Config.object(),
	id: Config.number(),
	nowrap: Config.bool(),
	verbiage: Config.string().value(Liferay.Language.get('x-shared-with-x'))
};

export default connect(
	(state, ownProps) => (
		{
			entitiesIList: ownProps.entitiesIList || getSharedTo(state, ownProps.id)
		}
	),
	{
		showModal
	}
)(SharedToSummary);