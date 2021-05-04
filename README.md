# Bibliotheque-Java

Mini projet - Java FX - MVC - EclipseLink 



La base de données  
On utilise une base de données bibliothèque. Il faut créer cette base de données puis exécuter les scripts sql fournis pour la création et le peuplement des 2 seules tables auteur et livre.  La relation entre auteur et livre : un auteur peut écrire plusieurs livres, mais un livre n'est écrit  que par un seul auteur.  
La table auteur 

La table livre 
Attention: le code ISBN d'un livre doit contenir 13 caractères.  
Le cahier des charges de l'application  
Il est ici assez simplifié.  
On veut :  
− afficher tous les auteurs,  
− afficher pour chaque auteur la liste des livres dans la bibliothèque,  
− ajouter un livre pour un auteur présent dans la bibliothèque,  
− supprimer un livre,  
− ajouter un auteur,  
− générer un QRCode pour un livre et l'imprimer, le texte du QRCode peut être stocké dans  la base mais ce champ devra alors être rajouté dans la table livre.  
Exemple de réalisation de l'interface demandée  
La figure page suivante montre l'interface obtenue au démarrage de l'application.  
Un auteur est sélectionné la ListView de gauche et sa liste des livres est affichée dans la  ListView de droite. 
1  
La ComboBox affiche tous les auteurs dans sa liste déroulante. 

Travail demandé  
1. La base de données 
  
Créer une base de données bibliothèque. Utiliser les scripts auteur.sql et livre.sql fournis  pour la création et le peuplement des 2 seules tables auteur et livre.  
La relation entre auteur et livre : un auteur peut écrire plusieurs livres, mais un livre n'est écrit  que par un seul auteur.  
Attention : le code ISBN contient 13 caractères (y compris les tirets).  
2. Les entities JPA 
Créer un package modele dans le projet.  
Faire ce qu'il faut pour créer les classes entities Auteur et Livre.  
3. La classe Dao
2  
3.1 A partir des fonctionnalités demandées, écrire la classe Dao dans un package application.  3.2 Ecrire une classe de test de la classe Dao.  
4. Réalisation de l'interface principale 
  
 ListView  
 TextField  

5. Réalisation de l'application 
1. Ecrire la classe Main dans application qui exécute cette interface. Tester.  2. La classe contrôleur de nom ControleurBibliotheque de la vue principale.  
2.1 Réaliser la partie de cette classe pour afficher l'interface donnée lorsque l'application  est exécutée. Faire en sorte que :  
− la liste des livres de chaque auteur sélectionné dans la ListView de gauche est  affichée dans la ListView de droite,  
− la sélection d'un autre auteur entraine l'affichage de sa liste de livres,  − la ComboBox affiche tous les auteurs dans sa liste déroulante. 
2.2 Coder la gestion du bouton Ajouter un livre. Effectuer les vérifications élémentaires  avant de créer un nouvel objet Livre. 
3  
2.3 Ajouter la gestion du bouton Supprimer un livre. Le livre à supprimer est sélectionné  dans la ListView de droite.  
2.4 Ajouter une fenêtre "popup" demandant la confirmation de la suppression quand on  clique sur le bouton Supprimer.  
3. La création de nouveaux auteurs  
La création de nouveaux auteurs passe par la gestion du bouton Ajouter un auteur.  
Voici un exemple de formulaire pour créer un nouvel auteur après avoir cliqué sur le  bouton Ajouter un auteur, avec un GridPane comme gestionnaire de disposition.  
Cela suppose que le formulaire de création s'appelle VueAuteur.fxml et est rangé dans le  package vue.  
La méthode montrerLaVue() sera exécutée quand on clique sur bouton Ajouter un auteur.  Il faut pour cela que le contrôleur de la vue principale dispose de la référence sur l'objet de  type MainAuteur. 
3.1 Réaliser le formulaire VueAuteur avec SceneBuilder (ou d'une autre façon...), à placer  dans un package vue.  
3.2 Coder la gestion du bouton Ajouter un auteur dans le contrôleur principal pour ouvrir  le formulaire de saisie d'un nouvel auteur.  
Il faut maintenant coder la classe contrôleur du formulaire. Ne pas hésiter à modifier le  code proposé ci-dessus si nécessaire. 
5  
3.3 Coder la gestion du bouton Annuler dans le contrôleur du formulaire. Ce bouton doit  simplement entrainer la fermeture du formulaire en exécutant la méthode close() de la  fenêtre formulaire.  
3.4 Coder la gestion du bouton Ok dans le contrôleur du formulaire pour ajouter un nouvel  auteur dans la base de données et fermer le formulaire. Vérifier le fonctionnement.  
4. La génération des QRCodes  
L'utilisateur doit pouvoir générer un QRCode avec une courte présentation de l'auteur et de  l'œuvre.  
Il est nécessaire d'utiliser une librairie pour générer les QRCodes, on présente dans  l'annexe l'installation de la librairie Zxing et un exemple d'utilisation.  
Voici une vue possible pour générer le QRCode pour un livre donné. Cette vue est ouverte  après avoir dans la vue principale sélectionné un livre et cliqué sur le bouton Créer.  
La vue précise le livre sélectionné et signale qu'il n'y a pas encore de QRCode pour ce  livre.  
− Le texte du QRCode est saisi dans un composant, par exemple de type TextArea.  − Le QRCode est généré en cliquant sur le bouton Générer le QRCode.  − Le QRCode produit est alors mémorisé dans un fichier et affiché dans un composant ImageView.  
TextArea ImageView
Si on sélectionne un livre alors que le QRCode est déjà généré, la vue l'indique "QRCode créé  pour cette œuvre" et affiche le QRCode correspondant.  
6  
4.1 Réaliser le formulaire CreerQRcode avec SceneBuilder (ou d'une autre façon...), à  placer dans un package vue.  
4.2 Coder la gestion du bouton Créer dans le contrôleur principal pour ouvrir la vue  CreerQRcode. Attention à ce que la vue fonctionne bien dans les 2 cas:  
− le QRCode n'existe pas, la vue l'indique "Pas de QRCode pour cette œuvre",  − le QRCode est déjà créé, la vue indique "QRCode créé pour cette œuvre" et  affiche le QRCode dans le composant ImageView.  
4.3 Coder la gestion du bouton Générer le QRCode. Ce bouton doit générer le QRCode,  sauver l'image et afficher cette image dans le composant ImageView.  
4.4 Coder la gestion du bouton Imprimer, le QRCode doit alors être imprimé.  
Toutes les améliorations apportées à l'interface et aux  fonctionnalités de l'application sont acceptées.  
Annexe 



1) Téléchargement des jar utiles zxing pour JavaSe  
►Aller sur l'url:  
 https://repo1.maven.org/maven2/com/google/zxing/  
►Télécharger javase-3.3.0.jar dans javase/3.3.0  
►Télécharger core-3.3.0.jar dans core/3.3.0  
2) Réalisation d'un projet de test.  
►Sélectionner le projet | clic droit | Build Path | Configure Build Path  
►Onglet Libraries  
►Cliquer sur Add External JARs…
7  

►Rechercher javase-3.3.0.jar et core-3.3.0.jar et les ajouter au projet.  ►Puis Apply et OK.  
4) Analyse d'une image QRcode en ligne de commande sous Windows  
► Il faut télécharger le jar jcommander.jar.  
► Ouvrir une invite de commande, se placer dans le dossier où se trouve l'image du  QRcode à analyser. Il faut taper la ligne de commande suivante (sur 1 seule ligne):  
java ‗ -cp ‗ c:\NomDuDossierContenantZXING\zxing-javase\core-3.3.0.jar;   c:\ NomDuDossierContenantZXING \zxing-javase\javase-3.3.0.jar;   c\Users\Guy\Downloads\jcommander-1.7.jar\jcommander-1.7.jar‗   com.google.zxing.client.j2se.CommandLineRunner ‗ qrcode-01.png  
Le signe ‗ symbolise un espace à taper dans la commande.  
Par exemple:  
java ‗ -cp ‗ c:\Users\Louis\Downloads\zxing-javase\core-3.3.0.jar;  
 c:\Users\ Louis \Downloads\zxing-javase\javase-3.3.0.jar;  
 c\Users\ Louis \Downloads\jcommander-1.7.jar\jcommander-1.7.jar‗   com.google.zxing.client.j2se.CommandLineRunner ‗ qrcode-01.png 
9  
