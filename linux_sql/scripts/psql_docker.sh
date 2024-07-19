#!/bin/bash
cmd=$1
db_username=$2
db_password=$3

docker container inspect jrvs-psql > /dev/null 2>&1
container_status=$?

case $cmd in
  create)
  # Check # of CLI arguments
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi
  # Check if the container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  # Create container
	docker volume create pgdat
	export PGPASSWORD=${db_password}
	export $PGUSERNAME = ${db_username}

  # create a container using psql image with name=jrvs-psql
  # analogy: install psql CD to a computer with name=jrvs-psql
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pg_vol:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
    # Start the container
	docker container start jrvs-psql
  # Make sure you understand what's `$?`
	exit $?
	;;

  start|stop)
  # Check instance status; exit 1 if container has not been created
  if [ $container_status -ne 0 ]; then
   exit 1;
  fi

  # Start or stop the container
	docker container $cmd jrvs-psql
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