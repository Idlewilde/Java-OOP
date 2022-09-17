package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import barracksWars.models.units.*;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		// TODO: implement for problem 3
		if(unitType.equals("Gunner")){
			Class<Gunner> Gunner = barracksWars.models.units.Gunner.class;
			Constructor<Gunner> constructor = Gunner.getDeclaredConstructor();
			return constructor.newInstance();}
		else if(unitType.equals("Swordsman")){
			Class<Swordsman> Swordsman = barracksWars.models.units.Swordsman.class;
			Constructor<Swordsman> constructor = Swordsman.getDeclaredConstructor();
			return constructor.newInstance();}
		else if(unitType.equals("Archer")){
			Class<Archer> Archer = barracksWars.models.units.Archer.class;
			Constructor<Archer> constructor = Archer.getDeclaredConstructor();
			return constructor.newInstance();}
		else if(unitType.equals("Horseman")){
			Class<Horseman> Horseman = barracksWars.models.units.Horseman.class;
			Constructor<Horseman> constructor = Horseman.getDeclaredConstructor();
			return constructor.newInstance();}
		else if(unitType.equals("Pikeman")){
			Class<Pikeman> Pikeman = barracksWars.models.units.Pikeman.class;
			Constructor<Pikeman> constructor = Pikeman.getDeclaredConstructor();
			return constructor.newInstance();}

		throw new ExecutionControl.NotImplementedException("message");
	}
}
