package br.com.fastjobs.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mensagem.class)
public abstract class Mensagem_ {

	public static volatile SingularAttribute<Mensagem, Long> destinatarioId;
	public static volatile SingularAttribute<Mensagem, Conversa> conversa;
	public static volatile SingularAttribute<Mensagem, Long> remetenteId;
	public static volatile SingularAttribute<Mensagem, String> mensagem;
	public static volatile SingularAttribute<Mensagem, LocalDateTime> diaEHora;
	public static volatile SingularAttribute<Mensagem, Boolean> foiVista;
	public static volatile SingularAttribute<Mensagem, Long> id;

}

