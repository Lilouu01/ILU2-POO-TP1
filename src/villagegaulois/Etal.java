package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		StringBuilder chaine = new StringBuilder();
		try {
			
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append("il a vendu " + produitVendu + " parmi " + produit
						+ ".\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
			etalOccupe = false;
		} catch (NullPointerException e) {
			System.err.println("Erreur : impossible de libérer un étal qui n'a jamais été occupé.");
			e.printStackTrace();
			return "";
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		if (quantiteAcheter < 1) {
			throw new IllegalArgumentException(
					"La quantité achetée doit être positive.");
		}
		if (!etalOccupe) {
			throw new IllegalStateException(
					"Impossible d'acheter à un étal non occupé.");
		}
		try {
			StringBuilder chaine = new StringBuilder();
			// acheteur null -> NullPointerException
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ acheteur.getNom() + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + acheteur.getNom()
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "";
		}
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}