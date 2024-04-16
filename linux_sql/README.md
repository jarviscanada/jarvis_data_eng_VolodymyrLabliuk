# Introduction

This project leverages Bash scripting and Docker to create and manage a system monitoring solution. The goal is to collect and store system information and resource usage in a PostgreSQL database. The users of this project are system administrators or anyone interested in monitoring and analyzing hardware specifications and usage over time. Key technologies used include Bash scripting for data collection, Docker for containerization, and PostgreSQL for data storage.

# Quick Start

- Start a PostgreSQL instance using psql_docker.sh
  ./psql_docker.sh start

- Create tables using ddl.sql
  psql -h localhost -U postgres -d host_agent -a -f sql/ddl.sql

- Insert hardware specs data into the DB using host_info.sh
  ./host_info.sh localhost 5432 host_agent postgres password

- Insert hardware usage data into the DB using host_usage.sh
  ./host_usage.sh localhost 5432 host_agent postgres password

- Set up crontab to run scripts every minute
    * * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/ localhost 5432 host_agent postgres password > /tmp/host_usage.log


# Implemenation

The project implementation revolves around a series of Bash scripts and SQL queries designed to seamlessly collect, store, and analyze system information and resource usage. This section delves deeper into the key components of the implementation.


## Architecture
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website). Image must be saved to the `assets` directory.


## Scripts
- psql_docker.sh - script that creates and run container with psql data base
- host_info.sh - retrieves information about current host and store it in data base
- host_usage.sh - retrieves real-time information about hardware and resources used and store it in data base
- crontab - retrieve data every minute and call script to add new record to data base every minute
- ddl.sql contains sql to create two tables host_info and host_usage

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)

- `host_info`
```
                                          Table "public.host_info"
          Column      |            Type             |                       Modifiers                        
    ------------------+-----------------------------+--------------------------------------------------------
     id               | integer                     | not null default nextval('host_info_id_seq'::regclass)
     hostname         | character varying           | not null
     cpu_number       | smallint                    | not null
     cpu_architecture | character varying           | not null
     cpu_model        | character varying           | not null
     cpu_mhz          | double precision            | not null
     l2_cache         | integer                     | not null
     timestamp        | timestamp without time zone | 
     total_mem        | integer                     | 
    Indexes:
        "host_info_pk" PRIMARY KEY, btree (id)
        "host_info_un" UNIQUE CONSTRAINT, btree (hostname)
    Referenced by:
        TABLE "host_usage" CONSTRAINT "host_usage_host_info_fk" FOREIGN KEY (host_id) REFERENCES host_info(id)
```  
- `host_usage`

```
                                              Table "public.host_usage"
     Column     |            Type             |                          Modifiers                           
----------------+-----------------------------+--------------------------------------------------------------
 timestamp      | timestamp without time zone | not null
 host_id        | integer                     | not null default nextval('host_usage_host_id_seq'::regclass)
 memory_free    | integer                     | not null
 cpu_idle       | smallint                    | not null
 cpu_kernel     | smallint                    | not null
 disk_io        | integer                     | not null
 disk_available | integer                     | not null
Foreign-key constraints:
    "host_usage_host_info_fk" FOREIGN KEY (host_id) REFERENCES host_info(id)
```

# Test

The bash scripts and DDL were tested by running them manually and ensuring that data was successfully inserted into the PostgreSQL database. The test results were successful.

# Deployment

The application is deployed using Docker for containerization. The scripts are scheduled using crontab for periodic data collection.

# Improvements

- Implement a mechanism to handle hardware updates gracefully, ensuring that the database reflects the most recent hardware specifications.
- Enhance error handling and logging in the bash scripts to provide better visibility into the data collection process.
- Implement data retention policies to manage the size of the database and optimize query performance over time.
