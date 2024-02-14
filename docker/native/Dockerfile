# Utilise une image Docker avec GraalVM 17
FROM ghcr.io/graalvm/graalvm-ce:ol8-java17

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie les fichiers nécessaires dans le conteneur
COPY . /app

# Commande par défaut pour l'exécution de l'application native (à adapter selon les besoins de votre application)
CMD ["/app/target/spring-boot-native-on-gcp-appengine"]
