# TP_AOC
## Objectif

Ce projet a pour objectif principal de mettre en œuvre le concept d’invocation asynchrone d’opération. 
La motivation c’est que, des plates formes que nous utilisons de nos jours font appel à la programmation parallèle (architecture multicœurs), hétérogènes (données  dans le cloud), réparties et dynamiques. 


## Contexte

Afin de réaliser cet objectif, nous allons donc recourir au pattern de conception Active Object, parce  que ce pattern s’applique sur des langages qui ne prennent pas en charge l’appel asynchrone (comme le langage Java), et qu’on le fera en s’appuyant sur l’appel synchrone.

## Le patron de conception : Active Object

Le pattern active object, est un pattern qui dissocie l’exécution de la méthode invocation (MI) de la méthode pour les objets qui se trouvent chacun dans leur thread de contrôle respectif. Le diagramme du pattern active object comporte neuf rôles, comme nous pouvons le voir sur la figure ci-dessous. 


 ![L'architecture du projet](diagram.png)                   




## Les Différents rôles de diagramme représentatif du pattern Active Objectif

Comme vous pouvez le remarquer, nous avons reparti les rôles de ce grand diagramme en trois parties, qui sont:
    • la partie gauche : la partie gauche implémente le côté client, et il y a un thread qui s’en charge
    • la partie droite : c’est dans le contexte du scheduler, et il y au moins un thread, dans le cas général plusieurs threads, ces derniers sont supervisés par le Scheduler 
    • la partie haute : on a des classes ou des types qui participent à la communication et à  la synchronisations des deux autres parties 







## Zoom sur les différentes fonctionnalités 

Dans la classe concrète CapteurImpl, getValue : avec et sans paramètres … il faut faire la différence 

Canal joue le rôle de proxy pour le capteur, donc il fait se comporte comme un capteur. Par conséquent, il implémente les interfaces Subject et Capteur. 
Pour ce qui concerne les trois stratégies, nous avons implémenté la classe énumérée Stratégie, dont les éléments sont : DiffusionAtomique, DiffusionSequentielle et DiffusionEpoque



## Récapitulatif de quelques méthodes de Javadoc

Dans tableau ci-dessous (https://www.jmdoudoux.fr/java/dej/chap-executor.htm), nous avons repris quelques unes des méthodes de la classe Executors, auxquelles nous avons recouru. 




## Architecture du projet

Le projet est conçu dans le respect de principe SOLID. Toutes les interfaces sont regroupées dans le même pacquage, et les différentes classes qui implémentent ces interfaces, figurent également dans un pacquage qui leurs est dédié. 
La figure ci-dessous, laisse transparaître la configuration du projet.





## Package Test

Parce qu’on ne doit pas utiliser la méthode main(), il est donc recommandé de faire des tests sous Junit 5. On va implémenter une classe TEST.

Dans @BeforEach, on note  l’instanciation pour chaque algorithme de diffusion :

* 1 capteur
* canaux (canals)
* affichicheurs (faire du câblage, donc de la connexion pour afficher les données)
* scheduled ExecutorService (ES) pour faire l’injection de dépendance.

Dans la partie @Test, on exécute les tests 

* instanciation de stratégie 
* injecter pour chaque cas de test une stratégie dans le capteur
* faire une demande auprès de scheduledExecutorService d’exécuter périodiquement une méthode invocation qui appelle tick() sur la capteur 
* laisser le temps de simulation pendant que les threads s’exécutent
* arrêter les  tick() avec le lock() et unlock, donc assurer la gestion des verrous
* awaitTermination sur le  scheduledExecutorService (pour la resynchronisation).

