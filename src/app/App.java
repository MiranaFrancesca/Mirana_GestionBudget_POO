package app;

import java.util.Scanner;

import tools.BudgetTools;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choix;

        System.out.println("Bienvenue dans la console budgétaire Java.");

        do {

            afficherMenu();
            choix = BudgetTools.lireIntSecurise(sc, "Votre choix : ");

            switch (choix) {

                case 1:
                    BudgetTools.ajouterOperation(sc);
                    break;

                case 2:
                    BudgetTools.afficherBilan(sc);
                    break;

                case 3:
                    BudgetTools.afficherHistorique(sc);
                    break;

                case 4:
                    BudgetTools.filtrerHistorique(sc);
                    break;

                case 5:
                    BudgetTools.rechercher(sc);
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);

        sc.close();
    }

    public static void afficherMenu() {

        System.out.println();
        System.out.println("=== GESTIONNAIRE BUDGET ===");
        System.out.println("1. Ajouter une opération");
        System.out.println("2. Afficher le bilan");
        System.out.println("3. Afficher l'historique");
        System.out.println("4. Filtrer l'historique");
        System.out.println("5. Rechercher");
        System.out.println("--------------------------");
        System.out.println("0. Quitter");
        System.out.println("--------------------------");
    }
}
