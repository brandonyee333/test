import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {DataSet, Network} from 'vis';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import {indexActivities} from '../actions/activities';
import {indexAddresses} from '../actions/addresses';
import {indexIncidents} from '../actions/incidents';
import {indexPeople} from '../actions/people';
import {indexResources} from '../actions/resources';
import {indexVehicles} from '../actions/vehicles';

class RelationshipsView extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonIncidentId} = props;

		if (watsonIncidentId) {
			props.indexActivities(watsonIncidentId);
			props.indexAddresses(watsonIncidentId);
			props.indexPeople(watsonIncidentId);
			props.indexResources(watsonIncidentId);
			props.indexVehicles(watsonIncidentId);
		}
	}

	created() {
		bindAll(
			this,
			'_updateNetwork'
		);
	}

	detached() {
		const {network} = this.state;

		if (network) {
			network.destroy();
		}
	}

	render() {
		return (
			<div class="network-container" />
		);
	}

	rendered() {
		if (!this.props.loading && this.state.willUpdate) {
			this._updateNetwork();

			this.state.willUpdate = false;
		}
	}

	shouldUpdate() {
		return false;
	}

	syncWatsonRelationships() {
		if (this.props.loading) {
			this.state.willUpdate = true;
		}
		else {
			this._updateNetwork();
		}
	}

	_updateNetwork() {
		const {props} = this;

		const {defaultOptions, modelTypes} = this.state;

		const unformattedConnections = [];
		const unformattedNodes = [];

		const allNodes = [];

		modelTypes.forEach(
			model => {
				const modelData = props[`${model}Data`];

				modelData.forEach(
					watsonModel => {
						const id = watsonModel.get('id');

						const watsonModelNode = {
							font: {
								color: 'darkgray',
								size: 14
							},
							group: model,
							id,
							label: watsonModel.get('name'),
							physics: false
						};

						allNodes.push(id);

						unformattedNodes.push(watsonModelNode);
					}
				);
			}
		);

		const {incidentName, watsonIncidentId, watsonRelationships = new Map()} = props;

		const incidentNode = {
			font: {
				color: 'darkgray',
				size: 13
			},
			group: 'incidents',
			id: watsonIncidentId,
			label: incidentName,
			physics: false
		};

		unformattedNodes.push(incidentNode);

		const relationshipPrimaryKeys = [];

		watsonRelationships.forEach(
			watsonRelationship => {
				const watsonModel1ClassPK = watsonRelationship.get('classPK1');
				const watsonModel2ClassPK = watsonRelationship.get('classPK2');
				const watsonModel1ProperName = watsonRelationship.get('selectedModel1');
				const watsonModel2ProperName = watsonRelationship.get('selectedModel2');
				const watsonRelationshipType = watsonRelationship.get('typeWatsonListTypeId');

				let typeLabel = '';

				if (watsonModel1ProperName && watsonModel2ProperName && watsonRelationshipType) {
					const typeOption = WatsonConstants.inputConfig[watsonModel1ProperName].relationshipObjectOptions[watsonModel2ProperName].options[watsonRelationshipType];

					if (typeOption) {
						typeLabel = typeOption.label;
					}
				}

				const relationshipConnection = {
					color: 'SandyBrown',
					font: {
						color: 'darkgray',
						size: 11
					},
					from: watsonModel1ClassPK,
					label: typeLabel,
					to: watsonModel2ClassPK
				};

				const watsonModel1Index = allNodes.indexOf(watsonModel1ClassPK);

				if (watsonModel1Index > -1) {
					allNodes.splice(watsonModel1Index, 1);
				}

				const watsonModel2Index = allNodes.indexOf(watsonModel2ClassPK);

				if (watsonModel2Index > -1) {
					allNodes.splice(watsonModel2Index, 1);
				}

				unformattedConnections.push(relationshipConnection);

				if (!relationshipPrimaryKeys.includes(watsonModel1ClassPK)) {
					const incidentConnection1 = {
						color: 'LightGray',
						dashes: true,
						from: watsonIncidentId,
						to: watsonModel1ClassPK
					};

					unformattedConnections.push(incidentConnection1);
				}

				relationshipPrimaryKeys.push(watsonModel1ClassPK, watsonModel2ClassPK);
			}
		);

		allNodes.forEach(
			(item, index) => {
				const orphanedModelConnection = {
					color: 'LightGray',
					dashes: true,
					from: watsonIncidentId,
					to: item
				};

				unformattedConnections.push(orphanedModelConnection);
			}
		);

		const edges = new DataSet(unformattedConnections);
		const nodes = new DataSet(unformattedNodes);

		const options = {};

		const network = new Network(
			this.element,
			{edges, nodes},
			Object.assign(defaultOptions, options)
		);

		this.setState(
			{
				network
			}
		);
	}
}

RelationshipsView.PROPS = {
	disabled: Config.bool().value(false),
	incidentName: Config.string(),
	loading: Config.bool().value(false),
	watsonIncidentId: Config.value(''),
	watsonRelationships: Config.value(new Map())
};

RelationshipsView.STATE = {
	classPKArray: Config.array().value([]),
	defaultOptions: Config.object().value(
		{
			groups: {
				activities: {
					icon: {
						code: '\uE0D8',
						color: 'gray',
						face: 'Material Icons',
						size: 20
					},
					shape: 'icon'
				},
				addresses: {
					icon: {
						code: '\uE88A',
						color: 'gray',
						face: 'Material Icons',
						size: 20
					},
					shape: 'icon'
				},
				incidents: {
					color: {
						background: 'white',
						border: 'gray',
						highlight: {
							background: 'white',
							border: 'darkgray'
						},
						hover: {
							background: 'white',
							border: 'darkgray'
						}
					},
					shape: 'circle',
					size: 45
				},
				people: {
					icon: {
						code: '\uE7FD',
						color: 'gray',
						face: 'Material Icons',
						size: 20
					},
					shape: 'icon'
				},
				resources: {
					icon: {
						code: '\uE161',
						color: 'gray',
						face: 'Material Icons',
						size: 20
					},
					shape: 'icon'
				},
				vehicles: {
					icon: {
						code: '\uE531',
						color: 'gray',
						face: 'Material Icons',
						size: 20
					},
					shape: 'icon'
				}
			},
			height: '100%',
			interaction: {
				dragView: true,
				hoverConnectedEdges: true,
				keyboard: {
					enabled: true
				},
				navigationButtons: true,
				zoomView: true
			},
			layout: {
				improvedLayout: true
			},
			physics: {
				repulsion: {
					centralGravity: 0,
					nodeDistance: 150
				},
				solver: 'repulsion'
			},
			width: '100%'
		}
	),
	modelTypes: Config.array().value(
		[
			'activities',
			'addresses',
			'people',
			'resources',
			'vehicles'
		]
	),
	network: Config.any(),
	willUpdate: Config.bool()
};

function mapStateToProps(state) {
	const activitiesData = state.getIn(['activities', 'data']) || new Map();
	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesData = state.getIn(['addresses', 'data']) || new Map();
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const incidentsData = state.getIn(['incidents', 'data']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const peopleData = state.getIn(['people', 'data']) || new Map();
	const peopleLoading = state.getIn(['people', 'loading']);
	const relationshipsLoading = state.getIn(['relationships', 'loading']);
	const resourcesData = state.getIn(['resources', 'data']) || new Map();
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesData = state.getIn(['vehicles', 'data']) || new Map();
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	const loading = activitiesLoading || addressesLoading || incidentsLoading || peopleLoading || relationshipsLoading || resourcesLoading || vehiclesLoading;

	return {
		activitiesData,
		activitiesLoading,
		addressesData,
		addressesLoading,
		incidentsData,
		incidentsLoading,
		loading,
		peopleData,
		peopleLoading,
		resourcesData,
		resourcesLoading,
		vehiclesData,
		vehiclesLoading
	};
}

function mapDispatchToProps(dispatch) {
	return {
		indexActivities: id => {
			dispatch(
				indexActivities({id})
			);
		},
		indexAddresses: id => {
			dispatch(
				indexAddresses({id})
			);
		},
		indexIncidents: id => {
			dispatch(
				indexIncidents({id})
			);
		},
		indexPeople: id => {
			dispatch(
				indexPeople({id})
			);
		},
		indexResources: id => {
			dispatch(
				indexResources({id})
			);
		},
		indexVehicles: id => {
			dispatch(
				indexVehicles({id})
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(RelationshipsView);