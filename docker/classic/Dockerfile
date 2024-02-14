# Utilise une image Docker avec JDK HotSpot 17
FROM eclipse-temurin:17-alpine

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie les fichiers nécessaires dans le conteneur
COPY . /app

# Commande par défaut pour l'exécution de l'application (à adapter selon les besoins de votre application)
CMD ["java", "-jar", "target/spring-boot-native-on-gcp-appengine-0.0.1-SNAPSHOT.jar"]