package com.academy.architecture.dao;

public interface DAOConstants {

	String SELECT_CORSI = "select * from corsi";
	String SELECT_DOCENTI = "select * from docenti";
	String SELECT_CORSISTI = "select * from corsisti";
	String SELECT_ISCRIZIONI = "select * from iscrizioni";
	
	String SELECT_ADMINPASS = "Select password from amministratori where username = ?";
	
	String UPDATE_CORSI = "update corsi set nomecorso = ?, datainiziocorso = ?, datafinecorso = ?, costocorso = ?, commenticorso = ?, aulacorso = ?, coddocente = ? where codcorso = ?";
	String UPDATE_CORSISTI = "update corsisti set nomecorsista = ?, cognomecorsista = ?, precedentiformativi = ? where codcorsista = ?";

	String DELETE_CORSI = "delete from corsi where codcorso = ?";
	String DELETE_CORSISTI = "delete from corsisti where codcorsista = ?";
	String DELETE_ISCRIZIONI = "delete from iscrizioni where codcorso = ? and codcorsista = ?";

	String SELECT_CORSI_BYKEY = "select * from corsi where codcorso = ?";
	String SELECT_CORSISTI_BYKEY = "select * from corsisti where codcorsista = ?";
	String SELECT_DOCENTE_BYKEY = "select * from docenti where coddocente = ?";
	String SELECT_CORSISTI_ORDINATI = "select * from corsisti order by codcorsista";

	String SELECT_ISCRIZIONI_BYCORSISTA = "select * from corsi, iscrizioni where corsi.codcorso = iscrizioni.codcorso and iscrizioni.codcorsista = ?";

	String SELECT_CORSI_BYDATE = "select * from corsi where datainiziocorso > ? order by datainiziocorso";

	String SELECT_CORSISTI_SEQ = "Select corsisti_seq.nextval from dual";
	String SELECT_CORSI_SEQ = "Select corsi_seq.nextval from dual";
	
//1
	String COUNT_CORSISTI = "select count(*) from corsisti";
//2
	String SELECT_CORSI_FREQUENZA = "select nomecorso, numero_iscritti from count_iscrizioni where numero_iscritti = (select max(numero_iscritti) from count_iscrizioni) order by nomecorso";
//3
	String DATA_ULTIMO_CORSO = "select * from corsi where datainiziocorso < ? order by datainiziocorso desc";
//4
	String SELECT_DURATA_CORSI = "select datainiziocorso, datafinecorso from corsi order by nomecorso";
//5
	String COUNT_COMMENTI = "select count(commenticorso) from corsi";
//6
	String SELECT_INFO_CORSISTI = "select nomecorsista, cognomecorsista, codcorsista from corsisti order by nomecorsista, cognomecorsista";
//7
	String SELECT_DOCENTI_PIU_CORSI = "select d.nomedocente, d.cognomedocente, d.cvdocente, d.coddocente "
			+ "from docenti d, corsi c "
			+ "where d.coddocente = c.coddocente "
			+ "group by d.coddocente, d.nomedocente, d.cognomedocente, d.cvdocente, d.coddocente "
			+ "having count(*) >= 2";
//8	la funzione coalesce(a,b) ritorna b se a=null
	String SELECT_CORSI_DISPONIBILI = "select c.codcorso, coalesce(i.numero_iscritti,0) as numero_iscritti, c.nomecorso " + 
			"from corsi c left outer join count_iscrizioni i  on c.codcorso = i.codcorso " + 
			"where i.numero_iscritti < 12 or i.numero_iscritti is null " + 
			"order by numero_iscritti desc, nomecorso";

}