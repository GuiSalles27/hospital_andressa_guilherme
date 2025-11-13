# Usa imagem oficial do Tomcat com Java 17
FROM tomcat:9.0-jdk17

# Define fallback para a variável PORT (Railway fornece automaticamente)
ENV PORT=8080

# Remove o app padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado para o Tomcat como ROOT.war
COPY target/hospital_andressa_guilherme-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expõe a porta que o Railway vai usar
EXPOSE ${PORT}

# Ajusta o Connector do Tomcat para usar a porta dinâmica
RUN sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml

# Comando padrão para manter o Tomcat em execução
CMD ["catalina.sh", "run"]
