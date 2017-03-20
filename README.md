# FReveilAndroid
Application Android créée par Fayçal Bousmaha permettant de gérer le FReveil.

## Introduction
### Le concept
Cette application Android a été conçue pour gérer le FReveil (<https://github.com/bousmahafaycal/FReveil>). 
Cette application permet, sans être connecté sur le serveur, de gérer les rappels si le FReveil est disponible sur le même réseau
local que l'application.  
En effet, grâce à elle, on peut ajouter/modifier/supprimer des rappels. 
Elle devient quasiment indispensable lorsque le serveur est placé sur un Raspberry Pi (un micro ordinateur pas cher) sans écran par exemple.
Aisni, plus besoin de se connecter en SSH sur le serveur afin de gérer ses rappels.


De plus, cette application permet de gérer la configuration du FReveil.
C'est à dire qu'on peut définir si on est dans le mode présent (à la maison) ou absent.
On peut également appuyé sur le bouton ou enlever l'appui sur le bouton.


Si vous souhaitez plus d'informations sur la configuration ou le FReveil en général, merci de regarder le README.md du lien suivant :
<https://github.com/bousmahafaycal/FReveil>

### Contexte de création
C'est un projet personnel que j'ai réalisé seul. 
Mes parents souhaitant que je me lève seul, il me fallait trouver une solution pour me réveiller.



Comptant me faire accepter à la licence CDAISI de Valenciennes, et ayant lu sur internet qu'ils recrutaient 
des personnes sachant "bricoler" de leur coté. 
Lors des vidéos de présentation,  ils montrent qu'ils utilisent l'Arduino.
J'ai donc décider de montrer certaines de mes compétences informatiques via ce projet.


Me rappelant que j'avais déja réaliser un réveil pour mon ancien assistant virtuel (pas encore mit sur github), j'ai décidé de recréer
un réveil. Cette fois-ci, il devrait toutefois être de meilleur qualité. 
J'ai alors tout recommencer depuis le début et je l'ai recoder plus "proprement". 
Cette fois, il peut gérer de l'éléctronique. 
En effet, dans l'ancien réveil, il fallait que je me connecte chaque matin en ssh pour pouvoir l'arreter. 
Via un bouton physique permettant d'arreter la phase ou le réveil sonne en boucle, cela ne sera plus nécéssaire. 


De plus, il pourra être rapidement améliorable par des développeurs tiers. 
Pour cela, un système de modules a été réalisé. 
Chaque développeur peut créer son propre module (via le FReveilModuleCreator : <https://github.com/bousmahafaycal/FReveilModuleCreator>).
On peut ensuite facilement ajouter son module au FReveil.


## Test
Afin de tester cette application, vous devez disposé d'Android Studio. 
Après avoir cloné ce projet sur votre disque dur, vous pourrez l'ouvrir avec Android Studio.
Une fois ouvert, vous pourrez appuyez sur la flèche verte (Run) afin de la lancer sur un émulateur ou sur un de vos appareils.


On doit tout d'abord entrer l'adresse ip et le port du serveur (12800 par défaut). Cela étant fait, on peut soit gérer la configuration du FReveil, soit ajouter/modifier/supprimer un rappel.


## A améliorer
- Arreter tous les thread à chaque fois que l'activité est détruite.
- Message d'erreur si le serveur refuse d'ajouter un rappel (par exemple parce qu'il y a déja un rappel existant).
- Sécurisation : Crypter les échanges serait une bonne idée afin d'éviter que quelqu'un s'introduisant dans le réseau local puisse
supprimer les rappels facilement.
- Créer mon propre log de l'application Android afin de savoir tout ce qu'elle a fait et les raisons d'un bug si il y en a un.
- Design général de l'application
- Ajouter des modules via l'application Android



## Captures d'écran
### Première capture

### Seconde capture

