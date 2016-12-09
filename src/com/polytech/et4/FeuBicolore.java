package semaphores;

public class FeuBicolore extends Semaphore {
	
	Bicolore couleurFeu = Bicolore.ROUGE;
	
	public void changerCouleur(Bicolore b) {
		couleurFeu = b;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Bicolore.ROUGE) return 0;
		return 1;
	}
	
	@Override
	public boolean peutPasser() {
		return couleurFeu != Bicolore.ROUGE;
	}
	

}
