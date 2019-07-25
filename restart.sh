mvn clean install
docker-compose down
docker rm -f retail-industry
docker-compose up --build