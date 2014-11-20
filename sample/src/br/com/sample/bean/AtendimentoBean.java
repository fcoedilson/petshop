package br.com.sample.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Atendimento;
import br.com.sample.entity.Especie;
import br.com.sample.entity.Raca;
import br.com.sample.entity.Servico;
import br.com.sample.service.AnimalService;
import br.com.sample.service.AtendimentoService;
import br.com.sample.service.BaseService;
import br.com.sample.service.ServicoService;
import br.com.sample.type.StatusAtendimento;
 

@SessionScoped
@Component("atendimentoBean")
public class AtendimentoBean extends EntityBean<Long, Atendimento>{
 
	@Autowired
	private AtendimentoService service;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private AnimalService animalService;
	
	private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private List<Animal> animais = new ArrayList<Animal>();
    private List<Servico> servicos = new ArrayList<Servico>();

    public static final String list = "/pages/atendimentos/atendimento/atendimentoLista.xhtml";
	
    @PostConstruct
    public void init() {
    	animais = animalService.retrieveAll();
    	servicos = servicoService.retrieveAll();
        eventModel = new DefaultScheduleModel();
        carregarAtendimentos();
    }
     
	private void carregarAtendimentos() {
		for(Atendimento atd : service.retrieveAll()){
			eventModel.addEvent(new DefaultScheduleEvent(atd.getNome(), atd.getDataInicial(), atd.getDataFinal()));
		}
	}

	@Override
	public String search() {
		super.search();
		return list;
	}

    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
    	setEntity(getAtendimento());
        if(event.getId() == null){
            eventModel.addEvent(event);
            super.save();
        }else{
            eventModel.updateEvent(event);
            super.update();
        }
        event = new DefaultScheduleEvent();
        setEntity(new Atendimento());
    }
     
    private Atendimento getAtendimento() {
    	Atendimento atendimento = service.buscaPorNome(event.getTitle());
    	if(atendimento==null){
    		atendimento = new Atendimento();
    		atendimento.setNome(event.getTitle());
    		atendimento.setDataFinal(event.getEndDate());
    		atendimento.setDataInicial(event.getStartDate());
    		atendimento.setAnimal(getEntity().getAnimal());
    		atendimento.setServico(getEntity().getServico());
    		atendimento.setStatus(getEntity().getStatus());
    	}
		return atendimento;
	}

	public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atendimento Alterado", "Dia:" + event.getDayDelta() + ", Minuto:" + event.getMinuteDelta());
    	alterarAtendimento();
        addMessage(message);
    }

	private void alterarAtendimento() {
		setEntity(getAtendimento()); 
		getEntity().setDataFinal(this.event.getEndDate());
		getEntity().setDataInicial(this.event.getStartDate());
	}
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atendimento Alterado", "Dia:" + event.getDayDelta() + ", Minuto:" + event.getMinuteDelta());
        alterarAtendimento(); 
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	@Override
	protected BaseService<Long, Atendimento> retrieveEntityService() {
		return this.service;
	}

	@Override
	protected Long retrieveEntityId(Atendimento entity) {
		return entity.getId();
	}

	@Override
	protected Atendimento createNewEntity() {
		Atendimento atendimento = new Atendimento();
		atendimento.setAnimal(getAnimal());
		atendimento.setStatus(StatusAtendimento.AGENDADO);
		atendimento.setQuantidade(1);
		return atendimento;
	}

	private Animal getAnimal() {
		Animal animal = new Animal();
		animal.setRaca(new Raca());
		animal.setEspecie(new Especie());
		return animal;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
}