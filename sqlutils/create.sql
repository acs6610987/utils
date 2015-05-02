create database genomic_raw_data;
use genomic_raw_data;
create table encrypted_short_read
(
	position bigint,
	encrypted_cigar_content varbinary(128),
	random_salt varbinary(8),
	patient_id varbinary(11)
);
create index position_idx on encrypted_short_read(position);
create index patient_position_idx on encrypted_short_read(patient_id, position);

