#!/bin/bash
cmd=$1
db_username=$2
db_password=$3

sudo systemctl status docker || systemctl start docker
status=$?

docker container inspect jrvs-psql
container_status=$?

case $cmd in
  create)

  # Check if the container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  # Check # of CLI arguments
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  # Create container
	docker volume create pgdata
	# psql docker docs https://hub.docker.com/_/postgres
  # psql docker docs https://hub.docker.com/_/postgres
  # set password for default user `postgres`
  export PGPASSWORD='password'

  # create a container using psql image with name=jrvs-psql
  # analogy: install psql CD to a computer with name=jrvs-psql
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=PGPASSWORD  -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine

  # Start the container
	docker container start jrvs-psql
  # Make sure you understand what's `$?`
	exit $?
	;;

  start|stop)
  # Check instance status; exit 1 if container has not been created
  if [ "$container_status" != "create" ]; then
   exit 1;
  fi
  # Start or stop the container
	docker container "$cmd" jrvs-psql
	#docker exec -it $container_name_or_id psql -U $db_username -W $db_password
  psql -h localhost -U postgres -d postgres -W
  exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac
exit 0