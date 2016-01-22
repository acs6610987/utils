#!/bin/sh
mysql -uhzc -pYOUR_PASSWORD <<SCRIPT
use genomic_raw_data;
create table encrypted_NA06984(position bigint, encrypted_cigar_content varbinary(128), random_salt varbinary(8));
load data infile '/home/lca1/workspace/data/NA06984.mapped.ILLUMINA.bwa.CEU.low_coverage.20120522.bam.enc.dat' into table encrypted_NA06984 fields terminated by ',' optionally enclosed by '"' escaped by '\\\\' lines terminated by '\\n' (position, encrypted_cigar_content, random_salt);
create index position_idx on encrypted_NA06984(position);
quit
SCRIPT

