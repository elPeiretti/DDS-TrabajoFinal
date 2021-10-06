package interfaces.misc;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.List;

public class TabOrder extends FocusTraversalPolicy {

	List<Component> componentes;
	
	public TabOrder(List<Component> comps) {
		componentes = comps;
	}
	
	@Override
	public Component getComponentAfter(Container aContainer, Component aComponent) {
		int i;
		if((i = componentes.indexOf(aComponent)) == componentes.size() - 1) return componentes.get(0);
		if(i == -1) return componentes.get(0);
		return componentes.get(i+1);
	}
	@Override
	public Component getComponentBefore(Container aContainer, Component aComponent) {
		int i;
		if((i = componentes.indexOf(aComponent)) == 0) return componentes.get(componentes.size()-1);
		if(i == -1) return componentes.get(0);
		return componentes.get(i-1);
	}
	@Override
	public Component getFirstComponent(Container aContainer) {
		return componentes.get(0);
	}

	@Override
	public Component getLastComponent(Container aContainer) {
		return componentes.get(componentes.size());
	}

	@Override
	public Component getDefaultComponent(Container aContainer) {
		return getFirstComponent(aContainer);
	}

}
