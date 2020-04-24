import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {Provider} from 'metal-redux';

import AffixHeader from '../AffixHeader';
import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import SelectInput from '../SelectInput';
import SelectInputEntityItem from '../SelectInputEntityItem';
import {getEntityId} from '../../lib/util';

class HeaderExample extends Component {
	created() {
		bindAll(
			this,
			'handleClick_',
			'handleScroll_'
		);
	}

	attached() {
		window.addEventListener('scroll', this.handleScroll_);
	}

	detached() {
		window.removeEventListener('scroll', this.handleScroll_);
	}

	handleClick_() {
		this.state.showAffixHeader_ = !this.state.showAffixHeader_;
	}

	handleScroll_() {
		const elementPosition = this.element.getBoundingClientRect().bottom;

		const lastVisiblePosition = elementPosition - 60;

		let showAffixHeader = false;

		if (lastVisiblePosition < 0) {
			showAffixHeader = true;
		}

		this.state.showAffixHeader_ = showAffixHeader;
	}

	render() {
		const {showAffixHeader_} = this.state;

		const style = {
			backgroundColor: '#1F7AFF',
			height: '300px',
			marginTop: '20px'
		};

		return (
			<div>
				<Button onClick={this.handleClick_} role="primary">{'Toggle AffixHeader'}</Button>

				<div style={style}>
					<AffixHeader active={showAffixHeader_} entity={this.props.entity} />
				</div>
			</div>
		);
	}
}

HeaderExample.PROPS = {
	entity: Config.object()
};

HeaderExample.STATE = {
	showAffixHeader_: Config.value(false)
};

class AffixHeaderKit extends Component {
	created() {
		bindAll(
			this,
			'handleSelect_',
			'renderItem_'
		);

		this.state.selectedItem_ = this.state.entity_[0];
	}

	handleSelect_(entity) {
		this.state.selectedItem_ = entity;
	}

	renderItem_(item, selected) {
		return [<SelectInputEntityItem entity={item} key={getEntityId(item)} selected={selected} />];
	}

	render() {
		const {entity_, selectedItem_} = this.state;

		const style = {
			height: '3000px',
			width: '100%'
		};

		return (
			<Kit header="AffixHeader">
				<ElementContainer header="Change AffixHeader Entity">
					<Provider store={mockStore()}>
						<div style={style}>
							<div style={{marginBottom: '20px'}}>
								<SelectInput
									getId={getEntityId}
									itemRenderer={this.renderItem_}
									items={entity_}
									onSelect={this.handleSelect_}
									selectedItem={selectedItem_}
								/>
							</div>

							<HeaderExample entity={selectedItem_} />
						</div>
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

AffixHeaderKit.STATE = {
	entity_: Config.value(
		[
			LoopAssets.getDivision(),
			LoopAssets.getPerson(),
			LoopAssets.getTopic()
		]
	),
	selectedItem_: Config.value({})
};

export default AffixHeaderKit;