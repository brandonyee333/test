import Component, {Config} from 'metal-jsx';

class EntityPillList extends Component {
	render() {
		return (
			<div class="entity-pill-list-container">
				{
					this.props.entities.map(
						entity => <a class="entity-pill" href={entity.displayURL} key={entity.entityClassPK}>{entity.name}</a>
					)
				}
			</div>
		);
	}
}

EntityPillList.PROPS = {
	entities: Config.array()
};

export default EntityPillList;