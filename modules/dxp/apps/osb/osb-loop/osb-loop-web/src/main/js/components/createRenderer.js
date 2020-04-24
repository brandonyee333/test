import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

export default store => (Comp, options, element, replaceInner, children) => {
	if (replaceInner) {
		element.innerHTML = '';
	}

	return Component.render(
		<Provider store={store}>
			<Comp {...options}>
				{children}
			</Comp>
		</Provider>,
		replaceInner ? element : {element}
	);
};