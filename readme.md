# CarHibou Backend

## CLI Launch
```
cd carhibou/backend/folder/
mvnw spring-boot:run
```

## IDE
Importer le projet dans STS en tant qu'un projet Maven existant puis le lancer comme "Spring Boot App"

## Architecture
com.ingesup.java.carhibou -> Le coeur de notre appli, juste une fonction main qui lance le backend
com.ingesup.java.carhibou.controller -> Tout nos controleurs pour le routing
com.ingesup.java.carhibou.data.dto -> Des versions personnalisées de nos objets en BDD
com.ingesup.java.carhibou.data.entities -> Les entités JPA correspondantes à nos enregistrement en BDD
com.ingesup.java.carhibou.models -> Nos classes personnalisées qui n'ont rien à voir avec la BDD
com.ingesup.java.carhibou.repositories -> Toutes nos DAO
com.ingesup.java.carhibou.services -> Les services qui servent à nos controleurs à consulter la base de données

