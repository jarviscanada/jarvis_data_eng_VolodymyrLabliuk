# Dockerfile for trading-psql

# Use the official Postgres image as the base image
FROM postgres:9.6-alpine

# Copy the initialization script to the container
COPY ./init.sql /docker-entrypoint-initdb.d/

# Expose the default Postgres port
EXPOSE 5432
