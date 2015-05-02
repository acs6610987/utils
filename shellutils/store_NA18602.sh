#!/bin/sh
mysql -uhzc -pacs524400951 <<SCRIPT
use genomic_raw_data;
create table encrypted_NA18602(position bigint, encrypted_cigar_content varbinary(128), random_salt varbinary(8));
load data infile '/home/lca1/workspace/data/NA18602.mapped.ILLUMINA.bwa.CHB.low_coverage.20120522.bam.enc.dat' into table encrypted_NA18602 fields terminated by ',' optionally enclosed by '"' escaped by '\\\\' lines terminated by '\\n' (position, encrypted_cigar_content, random_salt);
create index position_idx on encrypted_NA18602(position);
quit
SCRIPT

