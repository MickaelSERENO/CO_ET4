package com.polytech.et4;

import com.polytech.et4.ElemRegulationCapteur;

public abstract class Capteur extends Element implements Updatable {
    ElemRegulationCapteur m_elem=null;
    
    public Capteur(int position, int sens)
    {
    	super(position, sens);
    }
    
    public ElemRegulationCapteur getElemRegulation() 
    {
        return m_elem;
    }
    
    public void setElemRegulation(ElemRegulationCapteur m_elem) 
    {
        this.m_elem = m_elem;
    }    
}
