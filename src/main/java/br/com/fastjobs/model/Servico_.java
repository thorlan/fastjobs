package br.com.fastjobs.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servico.class)
public abstract class Servico_ {

	public static volatile SingularAttribute<Servico, TipoServico> tipoServico;
	public static volatile SingularAttribute<Servico, LocalDate> data;
	public static volatile SingularAttribute<Servico, Endereco> endereco;
	public static volatile SingularAttribute<Servico, Usuario> usuario;
	public static volatile SingularAttribute<Servico, String> nome;
	public static volatile SingularAttribute<Servico, Long> id;
	public static volatile ListAttribute<Servico, Foto> fotos;
	public static volatile SingularAttribute<Servico, String> descricao;

}

