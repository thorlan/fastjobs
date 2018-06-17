package br.com.fastjobs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conversa.class)
public abstract class Conversa_ {

	public static volatile SingularAttribute<Conversa, Long> id;
	public static volatile ListAttribute<Conversa, Mensagem> mensagens;

}

