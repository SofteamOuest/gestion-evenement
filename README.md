# gestion-evenement

Gestion des événements pouvant être associés au parcours d'intégration et autre (visite médicale, anniversaire, etc..)

GET /evenement : Récupération de tous les événements

GET /evenement/limite params:{limite: dateLimite String} : récupération des évenements après une date 

POST /evenement params:{evenementDto evenementDto} : ajout d'un évenement

GET /evenement/{idEvenement} : récupération d'un événement par son id

PUT /evenement/{idEvenement} params{evenementDto evenementDto} : Modification d'un événement 

DELETE /evenement/{idEvenement} : Suppression d'un événement
