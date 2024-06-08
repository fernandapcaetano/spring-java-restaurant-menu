# Use uma imagem base do Tomcat
FROM tomcat:11.0.0-jdk17-corretto

# Remova a aplicação padrão do Tomcat (opcional, mas recomendado)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copie o WAR gerado para o diretório webapps do Tomcat
COPY target/restaurante.war /usr/local/tomcat/webapps/

# Exponha a porta padrão do Tomcat
EXPOSE 8080

# Inicie o Tomcat
CMD ["catalina.sh", "run"]
