#  Kindergarten Absence Management

Application web de gestion des absences pour école maternelle développée avec **Spring Boot** et **Thymeleaf**.

## 🚀 Fonctionnalités

### 👥 Gestion des Étudiants
- ✅ Ajouter de nouveaux étudiants
- ✅ Modifier les informations existantes  
- ✅ Supprimer des étudiants
- ✅ Rechercher par nom ou prénom

### 📅 Gestion des Absences
- ✅ Enregistrer les absences
- ✅ Marquer comme justifiée/non-justifiée
- ✅ Consulter l'historique des absences
- ✅ Recherche par date ou période

### 👨‍👩‍👧‍👦 Gestion des Parents
- ✅ Associer un parent à chaque étudiant
- ✅ Informations de contact (email, téléphone)

## 🛠 Technologies Utilisées

- **Backend**: Spring Boot 3, Spring Data JPA, Spring MVC
- **Base de données**: MySQL
- **Frontend**: Thymeleaf, Bootstrap 5, Font Awesome
- **Build**: Maven

## 🗄 Structure de la Base

```sql
Students (id, firstName, lastName, birthDate, className)
Parents (id, firstName, lastName, email, phone, student_id)  
Absences (id, date, reason, justified, student_id)
