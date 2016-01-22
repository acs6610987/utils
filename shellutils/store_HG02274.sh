#!/bin/sh
mysql -uhzc -pYOUR_PASSWORD <<SCRIPT
use genomic_raw_data;
create table encrypted_HG02274(position bigint, encrypted_cigar_content varbinary(128), random_salt varbinary(8));
load data infile '/home/lca1/workspace/data/HG02274.mapped.ILLUMINA.bwa.PEL.low_coverage.20121211.bam.enc.dat' into table encrypted_HG02274 fields terminated by ',' optionally enclosed by '"' escaped by '\\\\' lines terminated by '\\n' (position, encrypted_cigar_content, random_salt);
create index position_idx on encrypted_HG02274(position);
quit
SCRIPT
