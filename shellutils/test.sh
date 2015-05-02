#!/bin/sh
mysql -uhzc -pacs524400951 <<SCRIPT
use test
source /home/lca1/workspace/data/test.sql
quit
SCRIPT

