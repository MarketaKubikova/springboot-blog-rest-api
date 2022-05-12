# springboot-blog-rest-api
##Run the database
Create docker container</br>
``
docker run --name myblogdb -p 3306:3306 -eMYSQL_ROOT_PASSWORD=root -d mysql:latest
``
<br>or use docker-compose</br>
``
docker compose up
``
