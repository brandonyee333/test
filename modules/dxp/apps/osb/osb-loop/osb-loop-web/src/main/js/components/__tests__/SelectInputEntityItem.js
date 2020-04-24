import LoopAssets from '../../tests/loop-assets';
import SelectInputEntityItem from '../SelectInputEntityItem';

describe(
	'SelectInputEntityItem',
	() => {
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
				component = new SelectInputEntityItem(
					{
						entity: LoopAssets.getPerson()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);