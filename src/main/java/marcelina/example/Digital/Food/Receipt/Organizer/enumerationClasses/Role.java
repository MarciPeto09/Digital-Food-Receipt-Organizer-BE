package marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses;

public enum Role {
    ROLE_USER,//può caricare ricevute, visualizzare la cronologia, scaricare PDF
    ROLE_VENDOR,//Fornitore: può inviare ricevute, aggiornare dati su prodotti e prezzi
    ROLE_ADMIN,//gestione completa di utenti, ruoli, configurazioni e database
    ROLE_ANALYST,//può accedere a dashboard, esportare dati, monitorare sprechi e costi
}
