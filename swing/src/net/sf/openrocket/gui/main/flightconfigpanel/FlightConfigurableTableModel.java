package net.sf.openrocket.gui.main.flightconfigpanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import net.sf.openrocket.l10n.Translator;
import net.sf.openrocket.rocketcomponent.ComponentChangeEvent;
import net.sf.openrocket.rocketcomponent.ComponentChangeListener;
import net.sf.openrocket.rocketcomponent.FlightConfigurableComponent;
import net.sf.openrocket.rocketcomponent.FlightConfigurationID;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.util.Pair;

public class FlightConfigurableTableModel<T extends FlightConfigurableComponent> extends AbstractTableModel  implements ComponentChangeListener{

	private static final long serialVersionUID = 3168465083803936363L;
	private static final Translator trans = Application.getTranslator();
	private static final String CONFIGURATION = trans.get("edtmotorconfdlg.col.configuration");

	protected final Rocket rocket;
	protected final Class<T> clazz;
	private final List<T> components = new ArrayList<T>();
	private Vector<FlightConfigurationID> ids = new Vector<FlightConfigurationID>();
	
	public FlightConfigurableTableModel(Class<T> clazz, Rocket rocket) {
		super();
		this.rocket = rocket;
		this.clazz = clazz;
		this.rocket.addComponentChangeListener(this);
		initialize();
	}

	@Override
	public void componentChanged(ComponentChangeEvent e) {
		if ( e.isMotorChange() || e.isTreeChange() ) {
			initialize();
			fireTableStructureChanged();
		}
	}

	/**
	 * Return true if this component should be included in the table.
	 * @param component
	 * @return
	 */
	protected boolean includeComponent( T component ) {
		return true;
	}
	
	protected void initialize() {
		components.clear();
		Iterator<RocketComponent> it = rocket.iterator();
		while (it.hasNext()) {
			RocketComponent c = it.next();
			if (clazz.isAssignableFrom(c.getClass()) && includeComponent( (T) c) ) {
				components.add( (T) c);
			}
		}
	}

	@Override
	public int getRowCount() {
		return rocket.getConfigurationSet().size() - 1;
	}

	@Override
	public int getColumnCount() {
		return components.size() + 1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		FlightConfigurationID id = getConfiguration(row);
		switch (column) {
		case 0: {
			return id;
		}
		default: {
			int index = column - 1;
			T d = components.get(index);
			return new Pair<String, T>(id.toString(), d);
		}
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: {
			return CONFIGURATION;
		}
		default: {
			int index = column - 1;
			T d = components.get(index);
			return d.toString();
	
		}
		}
	}

	private FlightConfigurationID getConfiguration(int rowNum) {
		this.ids = rocket.getSortedConfigurationIDs();
		
		FlightConfigurationID id = this.ids.get(rowNum + 1);
		return id;
	}

}