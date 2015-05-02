load data infile '/home/lca1/workspace/data/HG02274.mapped.ILLUMINA.bwa.PEL.low_coverage.20121211.bam.enc.dat' into table encrypted_short_read
fields terminated by ',' optionally enclosed by '"' escaped by '\\'
lines terminated by '\n';
load data infile '/home/lca1/workspace/data/NA06984.mapped.ILLUMINA.bwa.CEU.low_coverage.20120522.bam.enc.dat' into table encrypted_short_read fields terminated by ',' optionally enclosed by '"' escaped by '\\' lines terminated by '\n';

