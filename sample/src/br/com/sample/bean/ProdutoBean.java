package br.com.sample.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Produto;
import br.com.sample.service.ProdutoService;

@Scope("session")
@Component("produtoBean")
public class ProdutoBean extends EntityBean<Long, Produto> {

	@Autowired
	private ProdutoService service;

	private UploadedFile file;
	
	public static final String list = "/pages/cadastros/produto/produtoList.xhtml";
	public static final String single = "/pages/cadastros/produto/produto.xhtml";


	protected Long retrieveEntityId(Produto entity) {
		return entity.getId();
	}

	protected ProdutoService retrieveEntityService() {
		return this.service;
	}

	protected Produto createNewEntity() {
		Produto produto = new Produto();
		return produto;
	}


	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		super.save();
		return list;
	}

	public String update(){
		super.update();
		return list;
	}
	
	public String prepareSave(){
		super.prepareSave();
		return single;
	}
	
	public String prepareUpdate(){
		super.prepareUpdate();
		return single;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public void handleFileUpload(FileUploadEvent event) {  

		try {

			if(event != null){

				UploadedFile file = event.getFile();
				String fileName = file.getFileName();
				long fileSize = file.getSize();
				
				InputStream inputStream = file.getInputstream();

				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];
				inputStream.read(buffer);
				inputStream.close();

//				try {
//					for (int readNum; (readNum = inputStream.read(buf)) != -1;) {
//						bos.write(buf, 0, readNum);      
//					}
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//
//				byte[] bytes = bos.toByteArray();

				this.entity.setImage(buffer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}