package com.polytech.et4;

import com.polytech.et4.Updatable;

public abstract class ElemRegulation implements Updatable
{
	public ElemRegulation()
	{}

	/* \brief Permet de gérer les évènements*/
	public abstract void etreNotifie();
}
