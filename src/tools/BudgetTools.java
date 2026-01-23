package tools;

import java.util.Scanner;

import models.Categorie;
import models.Operation;
import models.TypeOperation;

public class BudgetTools {

    public static int lireIntSecurise(Scanner sc, String message) {

        System.out.print(message);

        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Saisie invalide, réessayez : ");
        }

        return sc.nextInt();
    }

    public static double lireDoubleSecurise(Scanner sc, String message) {

        System.out.print(message);

        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Saisie invalide, réessayez : ");
        }

        return sc.nextDouble();
    }

    public static void ajouterOperation(Scanner sc) {

        viderFinLigne(sc);

        String rep = "O";

        while (rep.equals("O")) {

            if (Storage.nbOperations >= Storage.MAX_OPS) {
                System.out.println("Tableau plein : impossible d'ajouter une opération.");
                return;
            }

            System.out.println();
            System.out.println("=== AJOUT OPERATION ===");

            String date;
            do {
                System.out.print("Date (ex: 12/01) : ");
                date = sc.nextLine().trim();
            } while (date.length() < 3);

            System.out.println();
            System.out.println("Type :");
            System.out.println("1. RECETTE");
            System.out.println("2. DEPENSE");

            int choixType;
            do {
                choixType = lireIntSecurise(sc, "Votre choix : ");
            } while (choixType != 1 && choixType != 2);

            TypeOperation type = (choixType == 1) ? TypeOperation.RECETTE : TypeOperation.DEPENSE;

            double montant;
            do {
                montant = lireDoubleSecurise(sc, "Montant : ");
                if (montant <= 0) {
                    System.out.println("Montant invalide (doit être > 0).");
                }
            } while (montant <= 0);

            System.out.println();
            System.out.println("Catégorie :");
            System.out.println("1. ALIMENTATION");
            System.out.println("2. LOYER");
            System.out.println("3. TRANSPORT");
            System.out.println("4. LOISIRS");
            System.out.println("5. AUTRE");

            int choixCat;
            do {
                choixCat = lireIntSecurise(sc, "Votre choix : ");
            } while (choixCat < 1 || choixCat > 5);

            Categorie categorie = convertirCategorie(choixCat);

            viderFinLigne(sc);

            String libelle;
            do {
                System.out.print("Libellé (3 à 40 caractères) : ");
                libelle = sc.nextLine().trim();
            } while (libelle.length() < 3 || libelle.length() > 40);

            int index = Storage.nbOperations;

            Operation op = new Operation(date, type, montant, categorie, libelle);
            Storage.operations[index] = op;
            Storage.nbOperations++;

            System.out.println();
            System.out.println("Opération enregistrée : " + type + " - "
                    + String.format("%.2f", montant) + " - "
                    + categorie + " - " + libelle + ".");

            do {
                System.out.print("Continuer ? (O/N) : ");
                rep = sc.nextLine().trim().toUpperCase();
            } while (!rep.equals("O") && !rep.equals("N"));
        }
    }

    public static void afficherBilan(Scanner sc) {

        if (Storage.nbOperations == 0) {
            System.out.println("Aucune opération enregistrée.");
            return;
        }

        double totalRecettes = 0;
        double totalDepenses = 0;
        double somme = 0;

        double min = Storage.operations[0].getMontant();
        double max = Storage.operations[0].getMontant();

        for (int i = 0; i < Storage.nbOperations; i++) {

            Operation op = Storage.operations[i];
            double montant = op.getMontant();
            somme += montant;

            if (montant < min) {
                min = montant;
            }
            if (montant > max) {
                max = montant;
            }

            if (op.getType() == TypeOperation.RECETTE) {
                totalRecettes += montant;
            } else {
                totalDepenses += montant;
            }
        }

        double solde = totalRecettes - totalDepenses;
        double moyenne = somme / Storage.nbOperations;

        System.out.println();
        System.out.println("=== BILAN ===");
        System.out.println("Total recettes : " + String.format("%.2f", totalRecettes) + " €");
        System.out.println("Total dépenses : " + String.format("%.2f", totalDepenses) + " €");
        System.out.println("Solde : " + String.format("%.2f", solde) + " €");
        System.out.println("Moyenne : " + String.format("%.2f", moyenne) + " €");
        System.out.println("Minimum : " + String.format("%.2f", min) + " €");
        System.out.println("Maximum : " + String.format("%.2f", max) + " €");

        if (solde < 0) {
            System.out.println("[ALERTE] Attention, votre solde est négatif !");
        } else {
            System.out.println("Bravo, votre compte est sain.");
        }

        pause(sc);
    }

    public static void afficherHistorique(Scanner sc) {

        if (Storage.nbOperations == 0) {
            System.out.println("Aucune opération enregistrée.");
            return;
        }

        viderFinLigne(sc);

        System.out.println();
        System.out.println("=== HISTORIQUE ===");
        afficherEntete();

        int lignes = 0;

        for (int i = 0; i < Storage.nbOperations; i++) {

            afficherLigne(i);
            lignes++;

            if (Storage.nbOperations > 10 && lignes % 10 == 0 && i < Storage.nbOperations - 1) {
                System.out.print("Voir la suite ? (Entrée) ");
                sc.nextLine();
            }
        }

        pause(sc);
    }

    public static void filtrerHistorique(Scanner sc) {

        if (Storage.nbOperations == 0) {
            System.out.println("Aucune opération enregistrée.");
            return;
        }

        viderFinLigne(sc);

        System.out.println();
        System.out.println("=== FILTRER HISTORIQUE ===");
        System.out.println("1. Par Type");
        System.out.println("2. Par Catégorie");

        int choix = lireIntSecurise(sc, "Votre choix : ");

        if (choix == 1) {

            System.out.println();
            System.out.println("Type :");
            System.out.println("1. RECETTE");
            System.out.println("2. DEPENSE");

            int t;
            do {
                t = lireIntSecurise(sc, "Votre choix : ");
            } while (t != 1 && t != 2);

            TypeOperation type = (t == 1) ? TypeOperation.RECETTE : TypeOperation.DEPENSE;

            System.out.println();
            System.out.println("=== HISTORIQUE (Type : " + type + ") ===");
            afficherEntete();

            int nb = 0;
            for (int i = 0; i < Storage.nbOperations; i++) {
                if (Storage.operations[i].getType() == type) {
                    afficherLigne(i);
                    nb++;
                }
            }
            System.out.println("Lignes trouvées : " + nb);

        } else if (choix == 2) {

            System.out.println();
            System.out.println("Catégorie :");
            System.out.println("1. ALIMENTATION");
            System.out.println("2. LOYER");
            System.out.println("3. TRANSPORT");
            System.out.println("4. LOISIRS");
            System.out.println("5. AUTRE");

            int c;
            do {
                c = lireIntSecurise(sc, "Votre choix : ");
            } while (c < 1 || c > 5);

            Categorie cat = convertirCategorie(c);

            System.out.println();
            System.out.println("=== HISTORIQUE (Catégorie : " + cat + ") ===");
            afficherEntete();

            int nb = 0;
            for (int i = 0; i < Storage.nbOperations; i++) {
                if (Storage.operations[i].getCategorie() == cat) {
                    afficherLigne(i);
                    nb++;
                }
            }
            System.out.println("Lignes trouvées : " + nb);

        } else {
            System.out.println("Choix invalide.");
        }

        pause(sc);
    }

    public static void rechercher(Scanner sc) {

        if (Storage.nbOperations == 0) {
            System.out.println("Aucune opération enregistrée.");
            return;
        }

        viderFinLigne(sc);

        String motCle;
        do {
            System.out.print("Entrez un mot-clé (3 chars min) : ");
            motCle = sc.nextLine().trim();
        } while (motCle.length() < 3);

        String mc = motCle.toLowerCase();

        System.out.println();
        System.out.println("-- RÉSULTATS POUR \"" + motCle + "\" --");
        afficherEntete();

        int nb = 0;

        for (int i = 0; i < Storage.nbOperations; i++) {
            String lib = Storage.operations[i].getLibelle();
            if (lib != null && lib.toLowerCase().contains(mc)) {
                afficherLigne(i);
                nb++;
            }
        }

        System.out.println("Total trouvé : " + nb + " opération(s)");
        pause(sc);
    }

    private static Categorie convertirCategorie(int c) {
        switch (c) {
            case 1:
                return Categorie.ALIMENTATION;
            case 2:
                return Categorie.LOYER;
            case 3:
                return Categorie.TRANSPORT;
            case 4:
                return Categorie.LOISIRS;
            default:
                return Categorie.AUTRE;
        }
    }

    private static void afficherEntete() {
        System.out.printf("%-5s %-8s %-10s %-14s %-25s %-10s%n",
                "[ID]", "DATE", "TYPE", "CATEGORIE", "LIBELLE", "MONTANT");
    }

    private static void afficherLigne(int i) {

        Operation op = Storage.operations[i];

        System.out.printf("%-5s %-8s %-10s %-14s %-25s %-10.2f €%n",
                "[" + i + "]",
                op.getDate(),
                op.getType(),
                op.getCategorie(),
                op.getLibelle(),
                op.getMontant());
    }

    private static void pause(Scanner sc) {
        System.out.println();
        System.out.print("Appuyez sur Entrée pour revenir...");
        sc.nextLine();
    }

    private static void viderFinLigne(Scanner sc) {
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
    }
}
