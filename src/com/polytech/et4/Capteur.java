package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

public class Capteur {
    ElemRegulationCapteur m_elem;
    
    public ElemRegulationCapteur getM_elem() {
        return m_elem;
    }
    
    public void setM_elem(ElemRegulationCapteur m_elem) {
        this.m_elem = m_elem;
    }
    
    void notifie(){
        if (getnotifie != null){
            m_elem.notifie();
        }
    }
    
    
}
