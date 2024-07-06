FROM openjdk:20-slim
#FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu


VOLUME /tmp
RUN export DEBIAN_FRONTEND=noninteractive \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
    && apt-get install -y curl \
    && apt-get install -y procps \
    && apt-get install tzdata \
	&& rm -rf /var/lib/apt/lists/*; \
	echo America/Lima > /etc/timezone; \
    rm /etc/localtime; \
	dpkg-reconfigure -fnoninteractive tzdata


ADD build/libs/graphql-challenge-*-SNAPSHOT.jar app.jar
ADD src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh


ENTRYPOINT ["/entrypoint.sh"]
