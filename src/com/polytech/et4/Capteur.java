package tp_Muscle;
import tp_Muscle.ElemRegulationCapteur;

public class Capteur extends Element implements Updatable {
    ElemRegulationCapteur m_elem=null;
	int c_position =0;
    private SegmentRoute m_segmentRoute = null;
    private int m_sens=0;
    
    public Capteur(int position, int sens)
    {
    	super(position, sens);
		c_position = position;
		m_sens=sens;
    }
    
    public ElemRegulationCapteur getElemRegulation() 
    {
        return m_elem;
    }
    
    public void setElemRegulation(ElemRegulationCapteur m_elem) 
    {
        this.m_elem = m_elem;
    }

	@Override
	public void prochaineEtape() {
		// Maj de la position du capteur vers le prochain capteur
		c_position = c_position + ((m_sens == ARRIERE) ? -1 : 1);
		
		SegmentRoute prochaineRoute = m_segmentRoute.setPosition(this);
		
		if(prochaineRoute != m_segmentRoute)
		{
			m_position = (m_sens == ARRIERE) ? prochaineRoute.getPositionFin() : prochaineRoute.getPositionDebut();
			m_segmentRoute = prochaineRoute
		}
		
	}    


}
