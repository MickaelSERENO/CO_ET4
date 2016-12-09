package com.polytech.et4;

import com.polytech.et4.ElemRegulationCapteur;

public class Capteur {
    ElemRegulationCapteur m_elem=null;
    
    public ElemRegulationCapteur getElemRegulation() 
    {
        return m_elem;
    }
    
    public void setElemRegulation(ElemRegulationCapteur m_elem) 
    {
        this.m_elem = m_elem;
    }    
}
