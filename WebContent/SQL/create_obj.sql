create table docenti(
nomedocente varchar2(30) not null,
cognomedocente varchar2(30) not null,
cvdocente varchar2(50),
coddocente int,
constraint p_coddocente primary key(coddocente)
);

create table corsi(
codcorso int,
nomecorso varchar2(30) not null,
datainiziocorso date not null,
datafinecorso date not null,
costocorso number(7,2) not null,
commenticorso varchar2(200),
aulacorso varchar2(30) not null,
coddocente int,
constraint p_codcorso primary key(codcorso),
constraint f_coddocente foreign key(coddocente) references docenti(coddocente)
);

create table corsisti(
nomecorsista varchar2(30) not null,
cognomecorsista  varchar2(30) not null,
codcorsista int,
precedentiformativi number(1),
constraint p_codcorsista primary key(codcorsista)
);

create sequence corsi_seq;
create sequence corsisti_seq;

-- Amministratore
create table amministratori(
nomeadmin varchar2(30) not null,
cognomeadmin varchar2(30) not null,
username varchar2(30),
password varchar2(32) not null,
constraint p_adminusername primary key(username)
);

create table iscrizioni(
codcorso int,
codcorsista int,
username varchar2(30),
constraint f_username foreign key(username) references amministratori(username) on delete cascade,
constraint f_codcorso foreign key(codcorso) references corsi(codcorso) on delete cascade,
constraint f_codcorsista foreign key(codcorsista) references corsisti(codcorsista) on delete cascade,
constraint p_iscrizione primary key(codcorso, codcorsista)
);

create or replace view count_iscrizioni as
select c.codcorso, c.nomecorso, c.datainiziocorso, c.datafinecorso, c.costocorso, c.commenticorso, c.aulacorso, c.coddocente, count(*) as numero_iscritti
from  corsi c join iscrizioni i  on c.codcorso = i.codcorso
group by c.codcorso, c.nomecorso, c.datainiziocorso, c.datafinecorso, c.costocorso, c.commenticorso, c.aulacorso, c.coddocente
order by numero_iscritti desc;
