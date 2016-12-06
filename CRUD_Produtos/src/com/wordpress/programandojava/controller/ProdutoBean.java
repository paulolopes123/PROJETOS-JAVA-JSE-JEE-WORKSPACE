/** Produtos - com.wordpress.programandojava.controller */
package com.wordpress.programandojava.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import com.wordpress.programandojava.dao.FotoDAO;
import com.wordpress.programandojava.dao.ProdutoDAO;
import com.wordpress.programandojava.model.Foto;
import com.wordpress.programandojava.model.Produto;

/**
 * @author - Felipe
 * @since - 08/08/2012
 */
@ManagedBean(name = "mbProduto")
@SessionScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private Produto produto = new Produto();
	private Produto produtoSelecionado = new Produto();;
	private ProdutoDAO produtoDAO;

	private List<Foto> fotos;
	private Foto foto = new Foto();
	private FotoDAO fotoDAO = new FotoDAO();

	public ProdutoBean() {
		produtoDAO = new ProdutoDAO();
		fotoDAO = new FotoDAO();

		produtos = produtoDAO.listAll();
	}

	public void salvaProduto() {

		try {
			produtoDAO.save(produto);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			produto = new Produto();
			produtos = produtoDAO.listAll();

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Produto adicionado", "Produto adicionado"));
		}
	}

	public void salvaFoto() {

		try {
			fotoDAO.save(foto);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			foto = new Foto();

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Foto adicionada", "Foto adicionada"));
		}

	}

	public void processFileUpload(FileUploadEvent uploadEvent) {

		try {
			foto.setProduto(produtoSelecionado);
			foto.setImagem(uploadEvent.getFile().getContents());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void listaFotosProduto() {

		try {
			ServletContext sContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			fotos = fotoDAO.listByProdutos(produtoSelecionado.getId());

			File folder = new File(sContext.getRealPath("/temp"));
			if (!folder.exists())
				folder.mkdirs();

			for (Foto f : fotos) {
				String nomeArquivo = f.getId() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator
						+ nomeArquivo;

				criaArquivo(f.getImagem(), arquivo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Foto> getFotos() {
		return fotos;
	}
}