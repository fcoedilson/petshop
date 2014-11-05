package br.com.sample.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
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


	public void upload() {
		if(file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public void fileUpload(FileUploadEvent uploadEvent) {

		UploadedFile file;

		try {

			file = uploadEvent.getFile();
			long size = file.getSize();
			InputStream stream = file.getInputstream();

			byte[] buffer = new byte[(int) size];  
			stream.read(buffer, 0, (int) size);  
			stream.close();  

			String filename = FilenameUtils.getBaseName(file.getFileName()); 
			String extension = FilenameUtils.getExtension(file.getFileName());
			File fileNew = File.createTempFile(filename, "." + extension);

			this.entity.setImage(buffer);
			this.entity.setPath("/tmp/"+filename+"."+extension);

			System.out.println(uploadEvent.getFile().getFileName());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void criaArquivo(byte[] bytes, String file) {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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