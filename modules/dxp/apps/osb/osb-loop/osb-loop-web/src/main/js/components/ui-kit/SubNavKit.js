import Component, {Config} from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import SubNav from '../SubNav';

class SubNavKit extends Component {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleChange_(newSubNavId) {
		this.state.subNavId_ = newSubNavId;
	}

	render() {
		const {subNavId_} = this.state;

		return (
			<Kit header="SubNav">
				<div>
					{`Current subNav: ${this.state.subNavId_}`}
				</div>

				<ElementContainer>
					<SubNav align="left">
						<SubNav.Item href="/SubNav" selected={subNavId_ === 'SubNav'}>{'Home'}</SubNav.Item>
						<SubNav.Item href="/one" selected={subNavId_ === 'one'}>{'One'}</SubNav.Item>
						<SubNav.Item href="/two" selected={subNavId_ === 'two'}>{'Two'}</SubNav.Item>
						<SubNav.Item href="/three" selected={subNavId_ === 'three'}>{'Three'}</SubNav.Item>
					</SubNav>
				</ElementContainer>
			</Kit>
		);
	}
}

SubNavKit.STATE = {
	subNavId_: Config.value('SubNav')
};

export default SubNavKit;