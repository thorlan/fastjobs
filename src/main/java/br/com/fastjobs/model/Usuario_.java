package br.com.fastjobs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile ListAttribute<Usuario, Servico> servicos;
	public static volatile SingularAttribute<Usuario, String> telefone;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile ListAttribute<Usuario, Conversa> conversas;
	public static volatile SingularAttribute<Usuario, Endereco> endereco;
	public static volatile ListAttribute<Usuario, Servico> maoDeObra;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile ListAttribute<Usuario, Comentario> comentarios;
	public static volatile SingularAttribute<Usuario, String> email;

}

