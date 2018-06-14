package br.com.fastjobs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Foto.class)
public abstract class Foto_ {

	public static volatile SingularAttribute<Foto, Long> id;
	public static volatile SingularAttribute<Foto, Servico> servico;
	public static volatile ListAttribute<Foto, Comentario> comentarios;
	public static volatile SingularAttribute<Foto, String> urlLink;
	public static volatile SingularAttribute<Foto, String> descricao;

}

