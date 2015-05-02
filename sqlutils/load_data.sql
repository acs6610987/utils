select * into outfile 'E:\\Lab\\LCA1\\genome_data\\data.txt'
	fields terminated by ',' optionally enclosed by '"' escaped by '\\'
	lines terminated by '\n'
	from encrypted_short_read;

load data infile '/home/lca1/workspace/data/NA18602.mapped.ILLUMINA.bwa.CHB.low_coverage.20120522.bam.enc.dat' into table encrypted_short_read
	fields terminated by ',' optionally enclosed by '"' escaped by '\\'
	lines terminated by '\n';

select * into outfile 'E:\\Lab\\LCA1\\genome_data\\data1.txt'
	fields terminated by ',' optionally enclosed by '"' escaped by '\\'
	lines terminated by '\n'
	from encrypted_short_read;

load data infile 'E:\\Lab\\LCA1\\genome_data\\HG02274.mapped.ILLUMINA.bwa.PEL.low_coverage.20121211.bam.enc.dat' into table encrypted_short_read
	fields terminated by ',' optionally enclosed by '"' escaped by '\\'
	lines terminated by '\n';
	