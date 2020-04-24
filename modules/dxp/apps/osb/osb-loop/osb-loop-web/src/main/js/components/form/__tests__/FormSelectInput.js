import Component from 'metal-jsx';
import {noop, range} from 'lodash';

import FormSelectInput from '../FormSelectInput';

describe(
	'FormSelectInput',
	() => {
		const ITEMS = range(8).map(LoopAssets.getPerson);

		class TestFormSelectInput extends Component {
			created() {
				this.renderItem = this.renderItem.bind(this);
			}

			getId(entity) {
				return `${entity.entityClassNameId}_${entity.entityClassPK}`;
			}

			renderItem(item, selected) {
				return [<div class="display-item" key={this.getId(item)}>{item.name}</div>];
			}

			render() {
				return (
					<FormSelectInput
						getId={this.getId}
						initialValue={ITEMS[0]}
						itemRenderer={this.renderItem}
						items={ITEMS}
						label="test"
						name="test"
						onSelect={this.handleSelect}
					/>
				);
			}
		}

		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new TestFormSelectInput();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set `value_` state to new value',
			() => {
				component = new FormSelectInput(
					{
						initialValue: {},
						itemRenderer: noop,
						items: [],
						name: 'test'
					}
				);

				const newVal = {val: 'newVal'};

				component.handleSelect_(newVal);

				expect(component.state.value_).toEqual(newVal);
			}
		);
	}
);