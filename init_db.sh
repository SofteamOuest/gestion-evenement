#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE eventdb WITH TEMPLATE = template0 OWNER = postgres ;
EOSQL