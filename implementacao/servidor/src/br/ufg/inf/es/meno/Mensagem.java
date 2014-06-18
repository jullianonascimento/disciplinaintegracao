package br.ufg.inf.es.meno;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mensagem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<String> registration_ids;
	private Map<String,String> data;
	private int time_to_live = 86400; //24 horas
	
	public Mensagem() {
		
	}
	
	public Mensagem(String regId, String title, String message){
		this.addRegId(regId);
		this.createData(title, message);
	}
	
	public Mensagem(String title, String message){
		this.createData(title, message);
	}

	public void addRegId(String regId){
		if(registration_ids == null)
			registration_ids = new LinkedList<String>();
		registration_ids.add(regId);
	}
	
	public void createData(String title, String message){
		if(data == null)
			data = new HashMap<String,String>();
	
		data.put("title", title);
		data.put("message", message);
	}
	
	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public int getTime_to_live() {
		return time_to_live;
	}

	public void setTime_to_live(int time_to_live) {
		this.time_to_live = time_to_live;
	}
	
	public int getTamanhoMensagem(){
		int tamanhoTitulo = data.get("title").getBytes().length;
		int tamanhoTexto = data.get("message").getBytes().length;
		int tamanhoMensagem = tamanhoTitulo + tamanhoTexto;
		
		return tamanhoMensagem;
	}
}
