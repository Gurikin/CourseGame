package ru.biv.msgSystem;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.GameMechanic;
import ru.biv.base.Msg;

public abstract class MsgToGM extends Msg {

	public MsgToGM(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if (abonent instanceof GameMechanic) {
			exec((GameMechanic)abonent);
		}
	}
	
	public abstract void exec(GameMechanic gameMechanic);
}
