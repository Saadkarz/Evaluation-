📚 Évaluation - Projet Java Hibernate/JPA

## 📋 Table des matières
- [Vue d'ensemble](#vue-densemble)
- [Technologies utilisées](#technologies-utilisées)
- [Exercice 1 - Gestion de Stock](#exercice-1---gestion-de-stock)
- [Exercice 2 - Gestion de Projets](#exercice-2---gestion-de-projets)
- [Exercice 3 - Gestion de l'État Civil](#exercice-3---gestion-de-létat-civil)
- [Installation et Configuration](#installation-et-configuration)

---

## 🎯 Vue d'ensemble

Ce repository contient trois exercices d'évaluation portant sur l'utilisation d'Hibernate/JPA avec MySQL pour la gestion de données dans différents contextes métier. Chaque exercice démontre l'implémentation de relations entre entités, la couche DAO/Service, et des requêtes personnalisées.

## 🛠️ Technologies utilisées

| Technologie | Version | Description |
|-------------|---------|-------------|
| **Java** | 8 - 21 | Langage de programmation |
| **Hibernate** | 5.6.15 - 6.2.7 | Framework ORM |
| **JPA** | 2.2 | Java Persistence API |
| **MySQL** | 8.0+ | Base de données relationnelle |
| **Maven** | 3.x | Gestionnaire de dépendances |

---

# 📦 Exercice 1 - Gestion de Stock

## 🎯 Objectif
Développer une application de gestion de produits et commandes avec Hibernate/JPA en implémentant une couche Service complète.

## 📊 Modèle de données

### Entités principales

#### 🏷️ Produit
- `id` : Identifiant unique (Long)
- `reference` : Référence du produit (String)
- `prix` : Prix unitaire (double)
- `categorie` : Catégorie associée (ManyToOne)

#### 📁 Categorie
- `id` : Identifiant unique (Long)
- `code` : Code de la catégorie (String)
- `libelle` : Libellé (String)
- `produits` : Liste des produits (OneToMany)

#### 🛒 Commande
- `id` : Identifiant unique (Long)
- `date` : Date de commande (Date)
- `ligneCommandes` : Lignes de commande (OneToMany)

#### 📝 LigneCommande
- `id` : Identifiant unique (Long)
- `quantite` : Quantité commandée (int)
- `commande` : Commande associée (ManyToOne)
- `produit` : Produit commandé (ManyToOne)

## 🔧 Fonctionnalités implémentées

### Interface générique IDao<T>
```java
- boolean create(T o)
- boolean update(T o)
- boolean delete(T o)
- T findById(int id)
- List<T> findAll()
```

### Services développés

#### 📦 ProduitService
- ✅ CRUD complet
- ✅ `findByCategorie(int categorieId)` - Afficher les produits d'une catégorie
- ✅ `findProduitsAvecPrixSuperieurA(double prix)` - Produits avec prix supérieur à un montant donné

#### 📁 CategorieService
- ✅ CRUD complet

#### 🛒 CommandeService
- ✅ CRUD complet
- ✅ `findProduitsCommandesEntreDates(Date debut, Date fin)` - Produits commandés entre deux dates
- ✅ `findProduitsByCommande(int commandeId)` - Produits d'une commande spécifique

#### 📝 LigneCommandeService
- ✅ CRUD complet

## 📸 Captures d'écran des résultats


<img width="1905" height="1193" alt="Screenshot 2025-10-26 211043" src="https://github.com/user-attachments/assets/44d66d90-a135-4061-a3a3-9b6e46690f5b" />


## 🚀 Exécution
```bash
cd "Evaluation ex1"
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.test.TestGestionStock"
```

---

# 👥 Exercice 2 - Gestion de Projets

## 🎯 Objectif
Créer une application de gestion de projets avec suivi des tâches et affectation d'employés.

## 📊 Modèle de données

### Entités principales

#### 👤 Employe
- `id` : Identifiant unique (Long)
- `matricule` : Matricule unique (String)
- `nom` : Nom de famille (String)
- `prenom` : Prénom (String)
- `telephone` : Numéro de téléphone (String)
- `email` : Adresse email (String)
- `adresse` : Adresse postale (String)
- `dateNaissance` : Date de naissance (String)

#### 📋 Projet
- `id` : Identifiant unique (Long)
- `nom` : Nom du projet (String)
- `dateDebut` : Date de début (Date)
- `dateFin` : Date de fin (Date)
- `chefProjet` : Chef de projet (ManyToOne avec Employe)

#### ✅ Tache
- `id` : Identifiant unique (Long)
- `nom` : Nom de la tâche (String)
- `dateDebut` : Date de début (Date)
- `dateFin` : Date de fin (Date)
- `prix` : Prix de la tâche (double)
- `projet` : Projet associé (ManyToOne)

#### 🔗 EmployeTache
- `id` : Clé composite (EmployeTachePK)
- `employe` : Employé assigné (ManyToOne)
- `tache` : Tâche assignée (ManyToOne)
- `dateDebutReelle` : Date de début réelle (Date)
- `dateFinReelle` : Date de fin réelle (Date)

## 🔧 Fonctionnalités implémentées

### Services développés

#### 👤 EmployeService
- ✅ CRUD complet
- ✅ `findByMatricule(String matricule)` - Recherche par matricule

#### 📋 ProjetService
- ✅ CRUD complet
- ✅ Gestion des relations avec le chef de projet

#### ✅ TacheService
- ✅ CRUD complet
- ✅ `findByProjet(int projetId)` - Tâches d'un projet
- ✅ `findTachesPlanifiees(Date dateDebut, Date dateFin)` - Tâches planifiées sur une période
- ✅ `findTachesRealisees(int employeId, Date dateDebut, Date dateFin)` - Tâches réalisées par un employé

#### 🔗 EmployeTacheService
- ✅ CRUD complet
- ✅ Gestion des affectations employé-tâche
- ✅ Suivi des dates réelles d'exécution

## 📸 Captures d'écran des résultats

<img width="1910" height="1128" alt="Screenshot 2025-10-26 212817" src="https://github.com/user-attachments/assets/9222c863-fa91-4d35-a71f-623b42ec6f52" />


## 🚀 Exécution
```bash
cd "Evaluation ex2"
mvn clean install
mvn test
```

---

#  Exercice 3 - Gestion de l'État Civil

## 🎯 Objectif
Développer une application de gestion des mariages entre hommes et femmes avec JPA et MySQL.

## 📊 Modèle de données

### Entités principales

#### 👨 Homme
- `id` : Identifiant unique (Long)
- `nom` : Nom de famille (String)
- `prenom` : Prénom (String)
- `telephone` : Numéro de téléphone (String)
- `adresse` : Adresse (String)
- `dateNaissance` : Date de naissance (Date)
- `mariages` : Liste des mariages (OneToMany)

#### 👩 Femme
- `id` : Identifiant unique (Long)
- `nom` : Nom de famille (String)
- `prenom` : Prénom (String)
- `telephone` : Numéro de téléphone (String)
- `adresse` : Adresse (String)
- `dateNaissance` : Date de naissance (Date)
- `mariages` : Liste des mariages (OneToMany)

#### 💍 Mariage
- `id` : Identifiant unique (Long)
- `dateDebut` : Date de début du mariage (Date)
- `dateFin` : Date de fin du mariage (Date, nullable)
- `nbrEnfants` : Nombre d'enfants (int)
- `homme` : Époux (ManyToOne)
- `femme` : Épouse (ManyToOne)

## 🔧 Fonctionnalités implémentées

### Services développés

#### 👨 HommeService
- ✅ CRUD complet avec Generic DAO
- ✅ `afficherFemmesEpousees(int hommeId)` - Liste des épouses d'un homme

#### 👩 FemmeService
- ✅ CRUD complet avec Generic DAO
- ✅ Gestion des femmes dans le système

#### 💍 MariageService
- ✅ CRUD complet avec Generic DAO
- ✅ `afficherMariages(int personneId, Date dateDebut, Date dateFin)` - Mariages d'une personne sur une période
- ✅ `getNombreEnfants(int hommeId, int femmeId)` - Nombre d'enfants d'un couple
- ✅ `afficherFemmesEpouseesEntreDeuxDates(int hommeId, Date debut, Date fin)` - Épouses d'un homme sur une période
- ✅ `afficherNombreEpouses(int hommeId)` - Nombre total d'épouses
- ✅ Gestion des mariages en cours et terminés

### Architecture
- **Generic DAO** : Pattern DAO générique pour réduire la duplication de code
- **Persistence Unit** : Configuration JPA avec `persistence.xml`
- **Transactions** : Gestion automatique des transactions

## 📸 Captures d'écran des résultats

<img width="1910" height="1195" alt="Screenshot 2025-10-26 214403" src="https://github.com/user-attachments/assets/950e62c0-8f38-41e1-a30a-cb2d4301735d" />


## 🚀 Exécution
```bash
cd "Evaluation ex3"
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.main.TestProgram"
```

---

## 💻 Installation et Configuration

### Prérequis
- ☕ Java JDK 8 ou supérieur
- 🗄️ MySQL Server 8.0+
- 📦 Maven 3.x
- 🔧 IDE (IntelliJ IDEA, Eclipse, NetBeans)

### Configuration de la base de données

1. **Créer les bases de données MySQL**
```sql
-- Pour l'exercice 1
CREATE DATABASE IF NOT EXISTS gestion_stock;

-- Pour l'exercice 2
CREATE DATABASE IF NOT EXISTS gestion_projets;

-- Pour l'exercice 3
CREATE DATABASE IF NOT EXISTS etat_civil;
```

2. **Configurer les identifiants**
   
Modifier les fichiers de configuration selon vos paramètres MySQL :
- **Exercice 1** : `src/main/resources/application.properties`
- **Exercice 2** : `src/main/resources/hibernate.cfg.xml`
- **Exercice 3** : `src/main/resources/META-INF/persistence.xml`

```properties
# Exemple de configuration
spring.datasource.url=jdbc:mysql://localhost:3306/nom_base
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### Installation

```bash
# Cloner le projet
git clone <url_du_repository>

# Accéder au dossier
cd Evaluation

# Pour chaque exercice, installer les dépendances
cd "Evaluation ex1"
mvn clean install

cd "../Evaluation ex2"
mvn clean install

cd "../Evaluation ex3"
mvn clean install
```

---

## 📁 Structure du projet

```
Evaluation/
├── README.md (ce fichier)
├── screenshots/              # Dossier pour les captures d'écran
│   ├── ex1_*.png
│   ├── ex2_*.png
│   └── ex3_*.png
├── Evaluation ex1/           # Gestion de Stock
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── Evaluation ex2/           # Gestion de Projets
│   ├── src/
│   ├── pom.xml
│   └── README.md
└── Evaluation ex3/           # Gestion de l'État Civil
    ├── src/
    ├── pom.xml
    └── README.md
```

---

## 🎓 Concepts clés démontrés

### Relations JPA/Hibernate
- ✅ **@OneToMany** / **@ManyToOne** : Relations unidirectionnelles et bidirectionnelles
- ✅ **@ManyToMany** : Relations plusieurs-à-plusieurs avec table de jointure
- ✅ **@Embedded** / **@EmbeddedId** : Clés composites
- ✅ **Cascade** : Propagation des opérations

### Architecture
- ✅ **Pattern DAO** : Séparation de la logique d'accès aux données
- ✅ **Pattern Service** : Logique métier
- ✅ **Generic DAO** : Réutilisabilité du code

### Requêtes
- ✅ **JPQL** : Requêtes orientées objet
- ✅ **Named Queries** : Requêtes nommées réutilisables
- ✅ **Criteria API** : Requêtes dynamiques

### Bonnes pratiques
- ✅ Gestion des transactions
- ✅ Gestion des ressources (SessionFactory, EntityManager)
- ✅ Séparation des couches (DAO, Service, Entités)
- ✅ Configuration externalisée

---


## 🐛 Dépannage

### Erreurs courantes

**Problème** : `ClassNotFoundException: com.mysql.cj.jdbc.Driver`
```bash
Solution : Vérifier que mysql-connector-java est dans le pom.xml
mvn clean install -U
```

**Problème** : `Table doesn't exist`
```bash
Solution : Vérifier que hibernate.hbm2ddl.auto=create ou update
```

**Problème** : `Access denied for user`
```bash
Solution : Vérifier les identifiants MySQL dans les fichiers de configuration
```

---

## 📚 Ressources

- [Documentation Hibernate](https://hibernate.org/orm/documentation/)
- [Spécification JPA](https://jakarta.ee/specifications/persistence/)
- [Guide MySQL](https://dev.mysql.com/doc/)
- [Maven Repository](https://mvnrepository.com/)

---

## 📄 Licence

Ce projet est réalisé Par Karzouz Saad.

---

## ✅ Checklist de validation

- [ ] ✅ Tous les exercices compilent sans erreur
- [ ] ✅ Les bases de données sont correctement configurées
- [ ] ✅ Toutes les captures d'écran sont ajoutées
- [ ] ✅ Les résultats correspondent aux exigences
- [ ] ✅ Le code est propre et commenté
- [ ] ✅ Les tests sont exécutables

---

*README généré pour l'évaluation des projets Hibernate/JPA - Octobre 2025*

