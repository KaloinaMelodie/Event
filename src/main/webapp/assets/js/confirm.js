
        function confirmLink() {
            var confirmation = confirm("Etes-vous sur de vouloir supprimer ?");
            if (confirmation) {
                // Continuer vers le lien cible
            } else {
                // Annuler l'action du lien
                return false;
            }
        }
