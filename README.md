# TP_AOC
## Objectif

Ce projet a pour objectif principal de mettre en œuvre le concept d’invocation asynchrone d’opération. 
La motivation c’est que, des plates formes que nous utilisons de nos jours font appel à la programmation parallèle (architecture multicœurs), hétérogènes (données  dans le cloud), réparties et dynamiques. 


## Contexte

Afin de réaliser cet objectif, nous allons donc recourir au pattern de conception Active Object, parce  que ce pattern s’applique sur des langages qui ne prennent pas en charge l’appel asynchrone (comme le langage Java), et qu’on le fera en s’appuyant sur l’appel synchrone.

## Le patron de conception : Active Object

Le pattern active object, est un pattern qui dissocie l’exécution de la méthode invocation (MI) de la méthode pour les objets qui se trouvent chacun dans leur thread de contrôle respectif. Le diagramme du pattern active object comporte neuf rôles, comme nous pouvons le voir sur la figure ci-dessous. 


 ![L'architecture du projet](diagram.png)                   




## Les Différents rôles du diagramme représentatif de Active Objectif

Comme vous pouvez le remarquer, nous avons reparti les rôles de ce grand diagramme en trois parties, qui sont:
    • la partie gauche : la partie gauche implémente le côté client, et il y a un thread qui s’en charge
    • la partie droite : c’est dans le contexte du scheduler, et il y au moins un thread, dans le cas général plusieurs threads, ces derniers sont supervisés par le Scheduler 
    • la partie haute : on a des classes ou des types qui participent à la communication et à  la synchronisations des deux autres parties 







Principes 

Dans ce projet, nous allons évidemment respecter scrupuleusement un certain nombre de princes métiers, dont les deux principaux sont : 

Les algorithmes de diffusions 
    • SOLID


    • ACID : les propriétés ACID (atomcité, cohérence, isolation et durabilité) sont un ensemble de propriétés qui garantissent qu'une transaction informatique est exécutée de façon fiable. (1)

	(https://fr.wikipedia.org/wiki/Propri%C3%A9t%C3%A9s_ACID) (1)



Architecture du projet

Le projet est conçu dans le respect de principe SOLID. Toutes les interfaces sont regroupées dans le même pacquage, et les différentes classes qui implémentent ces interfaces, figurent également dans un pacquage qui leurs est dédié. 
La figure ci-dessous, laisse transparaître la configuration du projet.
