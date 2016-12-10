package com.polytech.et4;

public class JonctionSimple extends Jonction {
	private SegmentRoute segmentRoute1;
	private SegmentRoute segmentRoute2;
	
	public JonctionSimple(SegmentRoute r1, SegmentRoute r2){
		segmentRoute1 = r1;
		segmentRoute2 = r2;
	}
	
	public SegmentRoute getNextSegmentRoute(SegmentRoute origine) throws OrigineJonctionException {
		if(origine.equals(this.segmentRoute1))
			return this.segmentRoute2;
		else if (origine.equals(this.segmentRoute2))
			return this.segmentRoute1;
		else
			throw new OrigineJonctionException();
	}
	
	public boolean peutPasser(Voiture v){
		try{
			if(this.getNextSegmentRoute(v.getSegmentRoute())!=null)
				return true;
		} catch(OrigineJonctionException e) {
			return false;
		}
		return false;
	}
}