#!/bin/sh
mysql -uhzc -pYOUR_PASSWORD <<SCRIPT
use test
source /home/lca1/workspace/data/test.sql
quit
SCRIPT

