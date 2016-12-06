/** Produtos - com.wordpress.programandojava.util */
package com.wordpress.programandojava.util;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.wordpress.programandojava.model.Foto;
import com.wordpress.programandojava.model.Produto;

/**
 * @author - Felipe
 * @since - 08/08/2012
 */
public class HibernateUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.addPackage("com.wordpress.programandojava.model")
					.addAnnotatedClass(Foto.class)
					.addAnnotatedClass(Produto.class).buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}