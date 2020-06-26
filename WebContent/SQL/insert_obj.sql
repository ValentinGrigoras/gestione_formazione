insert into docenti values('Francesco','Rossi','',1);
insert into docenti values('Gianni','Verdi','',2);
insert into docenti values('Maria','Gialli','',3);
insert into docenti values('Gianluca','Cosmini','',4);
insert into docenti values('Roberto','Negri','',5);
insert into docenti values('Luca','Gianni','',6);
insert into docenti values('Nicola','Massimo','',7);


insert into corsisti values('Marco','Blu',corsisti_seq.nextval,1);
insert into corsisti values('Roberta','Viola',corsisti_seq.nextval,0);
insert into corsisti values('Gaetano','Rossi',corsisti_seq.nextval,1);
insert into corsisti values('Enrico','Verdi',corsisti_seq.nextval,0);
insert into corsisti values('Federico','Russo',corsisti_seq.nextval,0);
insert into corsisti values('Roberta','Conti',corsisti_seq.nextval,0);
insert into corsisti values('Giorgio','Fontana',corsisti_seq.nextval,1);


insert into corsi values(corsi_seq.nextval,'Matematica','01-GEN-2000','15-GEN-2000',150,'commento','Aula 1B',1);
insert into corsi values(corsi_seq.nextval,'Filosofia','15-GEN-2001','30-GEN-2001',180,'commento','Aula 2B',2);
insert into corsi values(corsi_seq.nextval,'Letteratura','15-FEB-2010','15-MAR-2010',140,'commento','Aula 4C',3);
insert into corsi values(corsi_seq.nextval,'Informatica','11-DIC-2012','22-DIC-2012',200,'commentoInfo','Aula 1C',1);
insert into corsi values(corsi_seq.nextval,'Database','1-FEB-2012','4-FEB-2012',100,'commentoDb','Aula 4C',1);
insert into corsi values(corsi_seq.nextval,'Italiano','25-MAR-2012','28-MAR-2012',400,'commentoIta','Aula 3C',2);
insert into corsi values(corsi_seq.nextval,'Statistica','10-AGO-2012','25-AGO-2012',500,'commentoStati','Aula 1C',2);
insert into corsi values(corsi_seq.nextval,'Analisi','10-FEB-2021','25-MAR-2032',200,'commentoAnalisi','Aula 2A',1);
insert into corsi values(corsi_seq.nextval,'Probabilità','22-LUG-2021','24-SET-2021',300,'commentoProb','Aula 1D',2);

insert into amministratori values('Sara','Bianchi','admin','aed546a58b4ddc28fbff34a798d3c429');

insert into iscrizioni values(1, 1, 'admin');
insert into iscrizioni values(1, 2, 'admin');
insert into iscrizioni values(2, 1, 'admin');
insert into iscrizioni values(2, 5, 'admin');
insert into iscrizioni values(2, 3, 'admin');
insert into iscrizioni values(2, 4, 'admin');
insert into iscrizioni values(3, 5, 'admin');
insert into iscrizioni values(5, 6, 'admin');
insert into iscrizioni values(6, 6, 'admin');
insert into iscrizioni values(6, 7, 'admin');
