# Budget Manager — Java (Version POO)

Application développée individuellement en Java en utilisant la Programmation Orientée Objet.

## Description

Application console permettant de gérer un budget personnel via un terminal.

Cette version du projet repose sur une architecture orientée objet avec des classes dédiées pour structurer les données et la logique métier.

L’utilisateur peut ajouter des recettes et des dépenses, consulter un bilan financier, afficher l’historique des opérations, filtrer les données et effectuer une recherche par mot-clé.

## Architecture Orientée Objet

Le projet est structuré autour de plusieurs classes :

- Operation : représente une opération financière
- BudgetManager : gère la logique métier (ajout, calculs, filtres, recherche)
- Enum TypeOperation : RECETTE / DEPENSE
- Enum Categorie : ALIMENTATION, LOYER, TRANSPORT, LOISIRS, AUTRE…
- Main : point d’entrée du programme

Les opérations sont stockées dans une collection ou un tableau d’objets Operation.

## Fonctionnalités

- Menu interactif en boucle
- Ajout d’une opération (recette ou dépense)
- Calcul du bilan :
  - Total des recettes
  - Total des dépenses
  - Solde actuel
  - Moyenne, minimum et maximum
- Affichage de l’historique
- Filtrage par type
- Filtrage par catégorie
- Recherche par mot-clé
- Gestion robuste des erreurs utilisateur

## Concepts POO utilisés

- Classes et objets
- Encapsulation (attributs privés + getters)
- Enums
- Méthodes dédiées à la logique métier
- Séparation des responsabilités

## Technologies utilisées

- Java
- Collections (si utilisées)
- Enums
- Scanner
