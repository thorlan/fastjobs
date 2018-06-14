package br.com.fastjobs.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comentario.class)
public abstract class Comentario_ {

	public static volatile SingularAttribute<Comentario, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Comentario, Foto> foto;
	public static volatile SingularAttribute<Comentario, Usuario> usuario;
	public static volatile SingularAttribute<Comentario, Long> id;
	public static volatile SingularAttribute<Comentario, String> comentario;
	public static volatile SingularAttribute<Comentario, Boolean> foiVisto;

}

